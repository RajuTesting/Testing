/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.stock.strategies.CommerceAvailabilityCalculationStrategy;
import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.ProductUploadValidationDTO;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.services.ProductUploadAutomationService;

/*
 * Cronjob will validate all variant products those have status as 'Enriched' for validation if style variant has image for every color
 * variant and Size variant has price and inventory and set its status to 'Approved'
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class VariantProductAutoApprovalCronjob extends AbstractJobPerformable<CronJobModel> {

    /**
     *
     */
    private static final String THUMBNAIL = "Thumbnail";
    private static final String GALLERY_IMAGES = "Gallery Images";
    private static final String PARENT_FAIL = "ParentFail";
    private static final String SUCCESS = "SUCCESS";

    private static final Logger LOG = Logger.getLogger(VariantProductAutoApprovalCronjob.class);

    private static final Locale currentLocale = Locale.ENGLISH;
    private static final String BASE = "Base";
    private static final String STYLE = "Style";
    private static final String SIZE = "Size";

    private static List<String> parentApprovalFailureList = Arrays.asList(PARENT_FAIL);

    /** Product Upload Automation Service */
    @Resource(name = "sslProductUploadAutomationService")
    ProductUploadAutomationService productUploadAutomationService;

    /** Catalog Version Service */
    @Resource
    CatalogVersionService catalogVersionService;

    @Autowired
    private BaseStoreService baseStoreService;

    @Autowired
    private StockService stockService;
    @Autowired
    private WarehouseSelectionStrategy warehouseSelectionStrategy;
    @Autowired
    private CommerceAvailabilityCalculationStrategy commerceStockLevelCalculationStrategy;

    /** SetupSyncJob Service */

    @Override
    public PerformResult perform(final CronJobModel paramT) {
        LOG.info("### Variant Product auto approval cronjob started ###");
        final List<ApparelStyleVariantProductModel> styleVariantsWithCheckStatus = this.getProductUploadAutomationService()
                .getStyleVariantsWithSizeVariantStatus(this.getCatalogVersionModel(), ArticleApprovalStatus.CHECK);
        if (CollectionUtils.isNotEmpty(styleVariantsWithCheckStatus)) {

            final List<ProductUploadValidationDTO> productBaseRecords = new ArrayList<>();
            final List<ProductUploadValidationDTO> productStyleVariantRecords = new ArrayList<>();
            final List<ProductUploadValidationDTO> productSizeVariantRecords = new ArrayList<>();

            final List<WarehouseModel> warehouses = warehouseSelectionStrategy.getWarehousesForBaseStore(baseStoreService
                    .getBaseStoreForUid(SslCoreConstants.SSL_BASE_STORE));

            try {
                for (final ApparelStyleVariantProductModel styleProductToCheck : styleVariantsWithCheckStatus) {

                    if (ArticleApprovalStatus.UNAPPROVED.equals(styleProductToCheck.getApprovalStatus())) {
                        continue; // No need to validate Size variants if style is unapproved
                    }
                    final List<ItemModel> items = new ArrayList<>();

                    final ApparelProductModel masterSKU = (ApparelProductModel) styleProductToCheck.getBaseProduct();
                    // No Validation of Size and Style if Master is unapproved.
                    if (masterSKU != null && !ArticleApprovalStatus.UNAPPROVED.equals(masterSKU.getApprovalStatus())) {
                        ApparelStyleVariantProductModel validStyleVariantProduct = null;
                        ApparelProductModel approvedBaseProduct = null;
                        final Collection<VariantProductModel> sizeVariantProducts = styleProductToCheck.getVariants();
                        if (CollectionUtils.isNotEmpty(sizeVariantProducts)) {

                            final List<ApparelSizeVariantProductModel> validSizeVariantProducts = new ArrayList<>();
                            final List<Pair<ApparelSizeVariantProductModel, List<String>>> invalidSizeVariantMissingFiledsPairs = new ArrayList<>();

                            if (ArticleApprovalStatus.APPROVED.equals(masterSKU.getApprovalStatus())) {

                                this.validateSizeVariantProduct(sizeVariantProducts, validSizeVariantProducts,
                                        invalidSizeVariantMissingFiledsPairs, warehouses);

                                // Means valid Size Variant Found
                                if (CollectionUtils.isNotEmpty(validSizeVariantProducts)) {
                                    validStyleVariantProduct = this.validateStyleVariantProduct(styleProductToCheck,
                                            productStyleVariantRecords, masterSKU.getCode());

                                    final boolean isStyleVariantValid = ArticleApprovalStatus.APPROVED.equals(styleProductToCheck
                                            .getApprovalStatus());
                                    if (!isStyleVariantValid) {
                                        invalidateSizeVariants(validSizeVariantProducts, invalidSizeVariantMissingFiledsPairs);
                                    }
                                }

                            } else if (ArticleApprovalStatus.ENRICHED.equals(masterSKU.getApprovalStatus())) {

                                this.validateSizeVariantProduct(sizeVariantProducts, validSizeVariantProducts,
                                        invalidSizeVariantMissingFiledsPairs, warehouses);

                                if (CollectionUtils.isNotEmpty(validSizeVariantProducts)) {
                                    // Means Size variant is valid
                                    validStyleVariantProduct = this.validateStyleVariantProduct(styleProductToCheck,
                                            productStyleVariantRecords, masterSKU.getCode());

                                    final boolean isStyleVariantValid = ArticleApprovalStatus.APPROVED.equals(styleProductToCheck
                                            .getApprovalStatus());

                                    if (!isStyleVariantValid) {
                                        invalidateSizeVariants(validSizeVariantProducts, invalidSizeVariantMissingFiledsPairs);
                                    } else {
                                        // Means Both Style & Size are valid,
                                        approvedBaseProduct = this.validateAndApproveBaseProduct(masterSKU, productBaseRecords);

                                        final boolean isMasterSKUValid = ArticleApprovalStatus.APPROVED.equals(masterSKU
                                                .getApprovalStatus());
                                        if (!isMasterSKUValid) {
                                            // Master SKU invalid. Change status of both Style and Size.
                                            if (validStyleVariantProduct != null) {
                                                invalidateStyleVariants(productStyleVariantRecords, validStyleVariantProduct);
                                            }
                                            invalidateSizeVariants(validSizeVariantProducts, invalidSizeVariantMissingFiledsPairs);
                                        }
                                    }
                                }
                            }

                            if (approvedBaseProduct != null) {
                                items.add(approvedBaseProduct);
                            }
                            if (validStyleVariantProduct != null
                                    && ArticleApprovalStatus.APPROVED.equals(validStyleVariantProduct.getApprovalStatus())) {
                                items.add(validStyleVariantProduct);
                            }
                            if (!validSizeVariantProducts.isEmpty()) {
                                items.addAll(validSizeVariantProducts);
                            }

                            try {
                                if (!items.isEmpty()) {
                                    modelService.saveAll(items);
                                }
                            } catch (final ModelSavingException ex) {
                                LOG.error("Exception occured while approving  products having style variant code "
                                        + styleProductToCheck.getCode() + " and base product code " + masterSKU.getCode() + "Reason:"
                                        + ex.getMessage());

                            } catch (final Exception e) {
                                LOG.error("Exception occured while approving  products having style variant code "
                                        + styleProductToCheck.getCode() + " and base product code " + masterSKU.getCode() + "Reason:"
                                        + e.getMessage());
                            }

                            generateVariantSizeReport(invalidSizeVariantMissingFiledsPairs, validSizeVariantProducts,
                                    styleProductToCheck.getCode(), productSizeVariantRecords);
                        }
                    }
                }
            } catch (final Exception ex) {

                LOG.error("Exception occured while approving the product Reason:" + ex.getMessage());
            }
            List<String> fileNames = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(productBaseRecords)) {
                // Generate and mail error report
            	String fileName = this.getProductUploadAutomationService().generateSendXlsReport(productBaseRecords, paramT,
                        SslCoreConstants.VARIANT_PRODUCT_APPROVAL_JOB, BASE);
            	fileNames.add(fileName);
            }
            if (CollectionUtils.isNotEmpty(productStyleVariantRecords)) {
                // Generate and mail error report
                String fileName = this.getProductUploadAutomationService().generateSendXlsReport(productStyleVariantRecords, paramT,
                        SslCoreConstants.VARIANT_PRODUCT_APPROVAL_JOB, STYLE);
                fileNames.add(fileName);
            }
            if (CollectionUtils.isNotEmpty(productSizeVariantRecords)) {
                // Generate and mail error report
                String fileName = this.getProductUploadAutomationService().generateSendXlsReport(productSizeVariantRecords, paramT,
                        SslCoreConstants.VARIANT_PRODUCT_APPROVAL_JOB, SIZE);
                fileNames.add(fileName);
            }
            this.getProductUploadAutomationService().sendErrorReport(paramT.getCode(), paramT.getStartTime(), CronJobResult.SUCCESS, CronJobStatus.FINISHED, fileNames);
        }
        LOG.info("### Variant Product auto approval cronjob end ###");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @param productStyleVariantRecords
     * @param validStyleVariantProduct
     */
    private static void invalidateStyleVariants(final List<ProductUploadValidationDTO> productStyleVariantRecords,
            final ApparelStyleVariantProductModel validStyleVariantProduct) {
        validStyleVariantProduct.setApprovalStatus(ArticleApprovalStatus.CHECK);
        // mark ParentFail in report
        for (final ProductUploadValidationDTO validationDTO : productStyleVariantRecords) {
            if (validationDTO.getProductSKU().equals(validStyleVariantProduct.getCode())) {
                if (SUCCESS.equals(validationDTO.getErrorType())) {
                    validationDTO.setErrorType(PARENT_FAIL);
                    validationDTO.setProdSKUStatus(validStyleVariantProduct.getApprovalStatus().name());
                }
                break;
            }
        }
    }

    /**
     * @param validSizeVariantProducts
     * @param invalidSizeVariantMissingFiledsPairs
     */
    private void invalidateSizeVariants(final List<ApparelSizeVariantProductModel> validSizeVariantProducts,
            final List<Pair<ApparelSizeVariantProductModel, List<String>>> invalidSizeVariantMissingFiledsPairs) {
        // style invalid, do not approve size variants mark in report as parent failure for size.
        for (final ApparelSizeVariantProductModel sizeVariant : validSizeVariantProducts) {
            sizeVariant.setApprovalStatus(ArticleApprovalStatus.CHECK);
            invalidSizeVariantMissingFiledsPairs.add(new Pair<>(sizeVariant, parentApprovalFailureList));
        }
        validSizeVariantProducts.clear();
    }

    /**
     * Method to generate detailed report for size variant
     *
     * @param invalidSizeVariantMissingFiledsPairs
     *        : List of invalid size variant with missing fields Pair
     * @param validSizeVariants
     *        : List of Valid size variants
     * @param masterSku
     *        : ApperalProduct SKU
     */
    private void generateVariantSizeReport(
            final List<Pair<ApparelSizeVariantProductModel, List<String>>> invalidSizeVariantMissingFiledsPairs,
            final List<ApparelSizeVariantProductModel> validSizeVariants, final String masterSku,
            final List<ProductUploadValidationDTO> productSizeVariantRecords) {
        for (final Pair<ApparelSizeVariantProductModel, List<String>> invalidSizeVariantMissingFiledsPair : invalidSizeVariantMissingFiledsPairs) {
            // Prepared Invalid size report
            productSizeVariantRecords.add(loadSizeData(invalidSizeVariantMissingFiledsPair.getKey(), masterSku, true,
                    invalidSizeVariantMissingFiledsPair.getValue()));
        }
        for (final ApparelSizeVariantProductModel apparelSizeVariantProductModel : validSizeVariants) {
            // Prepared Valid size report
            productSizeVariantRecords.add(loadSizeData(apparelSizeVariantProductModel, masterSku, false, null));
        }
    }

    /**
     * Prepare Base data
     *
     * @param baseProduct
     * @param missingFields
     * @param isfail
     * @return : ProductUploadValidationDTO
     */
    private ProductUploadValidationDTO loadBaseData(final ProductModel baseProduct, final List<String> missingFields, final boolean isfail) {
        if (baseProduct instanceof ApparelProductModel) {
            final ApparelProductModel currentBaseProduct = (ApparelProductModel) baseProduct;
            final ProductUploadValidationDTO errorRecord = new ProductUploadValidationDTO();
            errorRecord.setProductSKU(currentBaseProduct.getCode());
            errorRecord.setProdSKUStatus(currentBaseProduct.getApprovalStatus().name());
            errorRecord.setProductSKUDateCreated(currentBaseProduct.getCreationtime());
            if (isfail) {
                errorRecord.setErrorType("Missing fields for base");
                errorRecord.setMissingFields(missingFields);
            } else {
                errorRecord.setErrorType(SUCCESS);
                errorRecord.setMissingFields(new ArrayList<String>(0));
            }
            return errorRecord;
        }
        return new ProductUploadValidationDTO();
    }

    /**
     * Prepare style data
     *
     * @param currentVariant
     * @param masterSKU
     * @param isThumbnailValid
     * @return : ProductUploadValidationDTO
     */
    private ProductUploadValidationDTO loadStyleData(final VariantProductModel currentVariant, final String masterSKU,
            final boolean isThumbnailValid, final boolean isGalleryImagesValid) {
        if (currentVariant instanceof ApparelStyleVariantProductModel) {
            final ApparelStyleVariantProductModel currentStyleVariant = (ApparelStyleVariantProductModel) currentVariant;
            final ProductUploadValidationDTO errorRecord = new ProductUploadValidationDTO();
            errorRecord.setProductSKU(currentStyleVariant.getCode());
            // errorRecord.setProductType(ApparelStyleVariantProductModel._TYPECODE);
            errorRecord.setBaseSKU(masterSKU);
            errorRecord.setBaseSKUStatus(currentStyleVariant.getBaseProduct().getApprovalStatus().name());
            errorRecord.setProdSKUStatus(currentStyleVariant.getApprovalStatus().name());
            errorRecord.setProductSKUDateCreated(currentStyleVariant.getCreationtime());

            final List<String> missingAttributesList = new ArrayList<>(3);

            if (isThumbnailValid && isGalleryImagesValid) {
                errorRecord.setErrorType(SUCCESS);
            } else {
                if (!isThumbnailValid) {
                    missingAttributesList.add(THUMBNAIL);
                }
                if (!isGalleryImagesValid) {
                    missingAttributesList.add(GALLERY_IMAGES);
                }
                errorRecord.setErrorType("Missing fields for variant");
            }
            checkColorSwatchAvailability(currentStyleVariant, missingAttributesList);
            errorRecord.setMissingFields(missingAttributesList);
            return errorRecord;
        }
        return new ProductUploadValidationDTO();
    }

    /**
     * @param styleVariant
     * @return
     */
    private void checkColorSwatchAvailability(final ApparelStyleVariantProductModel styleVariant, final List<String> missingAttributesList) {

        if (StringUtils.isBlank(styleVariant.getColourCode())) {
            missingAttributesList.add("Missing ColorCode");
        }
        if (CollectionUtils.isEmpty(styleVariant.getSwatchColors())) {
            missingAttributesList.add("Missing SwatchColors");
        }
    }

    private ProductUploadValidationDTO loadSizeData(final VariantProductModel currentVariant, final String masterSKU, final boolean isfail,
            final List<String> missingSizeVariantFields) {
        if (currentVariant instanceof ApparelSizeVariantProductModel) {
            final ApparelSizeVariantProductModel currentSizeVariant = (ApparelSizeVariantProductModel) currentVariant;
            final ProductUploadValidationDTO errorRecord = new ProductUploadValidationDTO();
            errorRecord.setProductSKU(currentSizeVariant.getCode());
            errorRecord.setBaseSKU(masterSKU);
            errorRecord
                    .setBaseSKUStatus(((ApparelStyleVariantProductModel) currentSizeVariant.getBaseProduct()).getApprovalStatus().name());
            errorRecord.setProdSKUStatus(currentSizeVariant.getApprovalStatus().name());
            errorRecord.setProductSKUDateCreated(currentSizeVariant.getCreationtime());
            if (isfail) {
                if (!missingSizeVariantFields.isEmpty() && PARENT_FAIL.equals(missingSizeVariantFields.get(0))) {
                    errorRecord.setErrorType("");
                } else {
                    errorRecord.setErrorType("Missing fields for Size Variant");
                }
                errorRecord.setMissingFields(missingSizeVariantFields);
            } else {
                errorRecord.setErrorType(SUCCESS);
                errorRecord.setMissingFields(new ArrayList<String>(1));
            }
            return errorRecord;
        }
        return new ProductUploadValidationDTO();
    }

    /**
     * method to generate error records for size variants
     *
     * @param invalidSizeVariants
     * @param invalidProductVariantRecords
     * @param masterSKU
     */
    // private void generateErrorSizeRecords(final List<VariantProductModel>
    // invalidSizeVariants,
    // final List<ProductUploadValidationDTO> invalidProductVariantRecords,
    // final String masterSKU) {
    // for (final VariantProductModel currentVariant : invalidSizeVariants) {
    // if (currentVariant instanceof ApparelSizeVariantProductModel) {
    // final ApparelSizeVariantProductModel currentSizeVariant =
    // (ApparelSizeVariantProductModel) currentVariant;
    // final ArrayList<String> missingSizeVariantFields = new
    // ArrayList<String>();
    // if (currentSizeVariant.getEurope1Prices() == null ||
    // currentSizeVariant.getEurope1Prices().isEmpty()) {
    // missingSizeVariantFields.add("Price");
    // }
    //
    // Boolean isStockMissing = Boolean.FALSE;
    // if
    // (CollectionUtils.isNotEmpty(stockService.getAllStockLevels(currentSizeVariant)))
    // {
    // final int totalActualAmount =
    // stockService.getTotalStockLevelAmount(currentSizeVariant);
    // if (totalActualAmount <= 0) {
    // isStockMissing = Boolean.TRUE;
    // } else {
    // isStockMissing = Boolean.FALSE;
    // break;
    // }
    // } else {
    // isStockMissing = Boolean.TRUE;
    // }
    //
    // if (Boolean.TRUE.equals(isStockMissing)) {
    // missingSizeVariantFields.add("Inventory");
    // }
    //
    // final ProductUploadValidationDTO errorRecord = new
    // ProductUploadValidationDTO();
    // errorRecord.setProductSKU(currentSizeVariant.getCode());
    // // errorRecord.setProductType(ApparelSizeVariantProductModel._TYPECODE);
    // errorRecord.setBaseSKU(masterSKU);
    // errorRecord.setProductSKUDateCreated(currentSizeVariant.getCreationtime());
    // errorRecord.setErrorType("Missing fields for Size Variant");
    // errorRecord.setMissingFields(missingSizeVariantFields);
    //
    // invalidProductVariantRecords.add(errorRecord);
    // }
    // }
    // }

    /**
     * method to validate size variant products
     *
     * @param sizeVariantProducts
     * @param validSizeVariantProducts
     * @param invalidSizeVariantMissingFiledsPairs
     */
    private void validateSizeVariantProduct(final Collection<VariantProductModel> sizeVariantProducts,
            final List<ApparelSizeVariantProductModel> validSizeVariantProducts,
            final List<Pair<ApparelSizeVariantProductModel, List<String>>> invalidSizeVariantMissingFiledsPairs,
            final List<WarehouseModel> warehouses) {

        for (final VariantProductModel currentVariant : sizeVariantProducts) {
            if (currentVariant instanceof ApparelSizeVariantProductModel
                    && (ArticleApprovalStatus.UNAPPROVED.equals(currentVariant.getApprovalStatus()))) {
                // No need to check for inventory and price
                continue;

            }

            if (currentVariant instanceof ApparelSizeVariantProductModel
                    && (ArticleApprovalStatus.CHECK.equals(currentVariant.getApprovalStatus()))) {
                final List<String> missingSizeVariantFields = new ArrayList<>();
                final ApparelSizeVariantProductModel currentSizeVariant = (ApparelSizeVariantProductModel) currentVariant;

                if (CollectionUtils.isEmpty(currentSizeVariant.getEurope1Prices())) {
                    missingSizeVariantFields.add("Price");
                }

                final Collection<StockLevelModel> stockLevels = stockService.getStockLevels(currentSizeVariant, warehouses);

                if (CollectionUtils.isNotEmpty(stockLevels)) {
                    final long totalActualAmount = commerceStockLevelCalculationStrategy.calculateAvailability(stockLevels).longValue();
                    if (totalActualAmount <= 0) {
                        missingSizeVariantFields.add("Inventory");
                    }
                } else {
                    missingSizeVariantFields.add("Inventory");
                }

                if (StringUtils.isBlank(currentSizeVariant.getTaxCategoryCode())) {
                    missingSizeVariantFields.add("Tax Category Code");
                }
                if (StringUtils.isBlank(currentSizeVariant.getHsnCode())) {
                    missingSizeVariantFields.add("HSN Code");
                }
                if (CollectionUtils.isEmpty(missingSizeVariantFields)) {
                    currentSizeVariant.setApprovalStatus(ArticleApprovalStatus.APPROVED);
                    validSizeVariantProducts.add(currentSizeVariant);
                } else if (CollectionUtils.isNotEmpty(missingSizeVariantFields)) {
                    invalidSizeVariantMissingFiledsPairs.add(new Pair<>(currentSizeVariant, missingSizeVariantFields));
                }
            }
        }
    }

    /**
     * method to validate Style variant product
     *
     * @param currentVariant
     * @param productStyleVariantRecords
     * @param masterSKUCode
     */
    private ApparelStyleVariantProductModel validateStyleVariantProduct(final VariantProductModel currentVariant,
            final List<ProductUploadValidationDTO> productStyleVariantRecords, final String masterSKUCode) {
        if (currentVariant instanceof ApparelStyleVariantProductModel
                && (ArticleApprovalStatus.CHECK.equals(currentVariant.getApprovalStatus()))) {
            final ApparelStyleVariantProductModel currentStyleVariant = (ApparelStyleVariantProductModel) currentVariant;
            final boolean isThumbnailValid = isValidThumbnail(currentStyleVariant);
            final boolean isGalleryContainerValid = CollectionUtils.isNotEmpty(currentStyleVariant.getGalleryImages());
            if (isThumbnailValid && isGalleryContainerValid) {
                currentStyleVariant.setApprovalStatus(ArticleApprovalStatus.APPROVED);
                productStyleVariantRecords.add(loadStyleData(currentStyleVariant, masterSKUCode, true, true));
                return currentStyleVariant;
            } else {
                productStyleVariantRecords
                        .add(loadStyleData(currentStyleVariant, masterSKUCode, isThumbnailValid, isGalleryContainerValid));
                return null;
            }
        }
        productStyleVariantRecords.add(loadStyleData(currentVariant, currentVariant.getBaseProduct().getCode(), true, true));
        return null;
    }

    private boolean isValidThumbnail(final ProductModel currentVariant) {
        return null != currentVariant.getThumbnail() && currentVariant.getThumbnail().getURL() != null
                && currentVariant.getThumbnail().getURL().startsWith("https://");
    }

    /**
     * method to validate base product
     *
     * @param product
     * @param productBaseRecords
     */
    private ApparelProductModel validateAndApproveBaseProduct(final ProductModel product,
            final List<ProductUploadValidationDTO> productBaseRecords) {
        if (product instanceof ApparelProductModel) {
            final ApparelProductModel productToCheck = (ApparelProductModel) product;
            final List<String> missingFields = getAllMissingFieldsBaseProduct(productToCheck);
            if (CollectionUtils.isEmpty(missingFields)) {
                productToCheck.setApprovalStatus(ArticleApprovalStatus.APPROVED);
                productBaseRecords.add(loadBaseData(productToCheck, missingFields, false));
                return productToCheck;
            }
            productBaseRecords.add(loadBaseData(productToCheck, missingFields, true));
        }
        return null;
    }

    /**
     * method to list down all mandatory fields that have missing values
     *
     * @param productToCheck
     *        : Base Product
     * @return all missing mandatory fields in a list
     */
    public List<String> getAllMissingFieldsBaseProduct(final ApparelProductModel productToCheck) {
        final List<String> missingFields = new ArrayList<>();
        if (StringUtils.isBlank(productToCheck.getCode())) {
            missingFields.add("Code");
        }
        if (StringUtils.isBlank(productToCheck.getName(currentLocale))) {
            missingFields.add("Name");
        }
        if (StringUtils.isBlank(productToCheck.getShortName())) {
            missingFields.add("ShortName");
        }
        if (StringUtils.isBlank(productToCheck.getSummary(currentLocale))) {
            missingFields.add("Summary");
        }
        if (CollectionUtils.isEmpty(productToCheck.getSupercategories())) {
            missingFields.add("Supercategories");
        }
        if (StringUtils.isBlank(productToCheck.getReturnPolicy())) {
            missingFields.add("ReturnPolicy");
        }
        if (StringUtils.isBlank(productToCheck.getShippingInfo())) {
            missingFields.add("ShippingInfo");
        }
        if (productToCheck.getIsAvailableForCoD() == null) {
            missingFields.add("IsAvailableForCoD");
        }
        if (CollectionUtils.isEmpty(productToCheck.getDeliveryPinCodes())) {
            missingFields.add("DeliveryPinCodes");
        }
        if (productToCheck.getMaxOrderQuantity() == null) {
            missingFields.add("MaxOrderQuantity");
        }
        if (productToCheck.getMinOrderQuantity() == null) {
            missingFields.add("MinOrderQuantity");
        }
        if (productToCheck.getOutOfStockNotice() == null) {
            missingFields.add("OutOfStockNotice");
        }
        if (productToCheck.getUnit() == null) {
            missingFields.add("Unit");
        }
        if (CollectionUtils.isEmpty(productToCheck.getCodPinCodes())) {
            missingFields.add("CodPinCodes");
        }
        if (productToCheck.getApprovalStatus() == null) {
            missingFields.add("ApprovalStatus");
        }
        if (!isValidThumbnail(productToCheck)) {
            missingFields.add("image");
        }
        if (CollectionUtils.isEmpty(productToCheck.getGalleryImages())) {
            missingFields.add(GALLERY_IMAGES);
        }
        return missingFields;
    }

    /**
     * catalog version getter method
     *
     * @return staged product catalog version
     */
    private CatalogVersionModel getCatalogVersionModel() {
        return catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION);
    }

    /**
     * ProductUploadAutomationService getter method
     *
     * @return the productUploadAutomationService
     */
    public ProductUploadAutomationService getProductUploadAutomationService() {
        return productUploadAutomationService;
    }
}
