/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.ProductUploadValidationDTO;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.services.ProductUploadAutomationService;

/**
 * Cronjob will validate all base products those have status as 'Check' for validation if they have all mandatory field values and set its
 * status to 'Enriched' and will set attribution flag to true
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class BaseProductEnrichmentCronjob extends AbstractJobPerformable<CronJobModel> {

    /** Log4j logger */
    private static final Logger LOG = Logger.getLogger(BaseProductEnrichmentCronjob.class);
    private static final String BASE="Base";
    /** Current Locale Variable */
    final Locale currentLocale = Locale.ENGLISH;

    /** The Product Upload Automation Service */
    @Resource(name = "sslProductUploadAutomationService")
    ProductUploadAutomationService productUploadAutomationService;

    /** The Catalog Version service. */
    @Resource
    CatalogVersionService catalogVersionService;

    @Override
    public PerformResult perform(final CronJobModel paramT) {
        LOG.info("### Product enrichment cronjob started ###");

        final List<ApparelProductModel> baseProductsWithCheckStatus = this.getProductUploadAutomationService().getProductsWithStatus(
                this.getCatalogVersionModel(), ArticleApprovalStatus.CHECK);
        if (CollectionUtils.isNotEmpty(baseProductsWithCheckStatus)) {
            final List<ApparelProductModel> validBaseProducts = new ArrayList<ApparelProductModel>();
            final List<ProductUploadValidationDTO> recordsWithError = new ArrayList<ProductUploadValidationDTO>();

            for (final ApparelProductModel productToCheck : baseProductsWithCheckStatus) {
                final List<String> missingFields = this.getAllMissingFieldsBaseProduct(productToCheck);

                if (CollectionUtils.isNotEmpty(missingFields)) {
                    final ProductUploadValidationDTO errorRecord = new ProductUploadValidationDTO();
                    // errorRecord.setProductSKU(productToCheck.getCode());
                    // errorRecord.setProductType(ApparelProductModel._TYPECODE);
                    errorRecord.setBaseSKU(productToCheck.getCode());
                    errorRecord.setBaseSKUStatus(productToCheck.getApprovalStatus().name());
                    errorRecord.setProductSKUDateCreated(productToCheck.getCreationtime());
                    errorRecord.setErrorType("Missing field values");
                    errorRecord.setMissingFields(missingFields);

                    recordsWithError.add(errorRecord);
                } else {
                    // Update base product status to enriched if validated successfully
                    productToCheck.setApprovalStatus(ArticleApprovalStatus.ENRICHED);
                    validBaseProducts.add(productToCheck);
                }
            }

            if (recordsWithError.size() > 0) {
                // Generate and mail error report
                this.getProductUploadAutomationService().generateSendXlsReport(recordsWithError, paramT,
                        SslCoreConstants.BASE_PRODUCT_ENRICHMENT_JOB,BASE);
            }

            if (validBaseProducts.size() > 0) {
                modelService.saveAll(validBaseProducts);
                LOG.debug(validBaseProducts.size() + " Base Products updated.");
            }
        }

        LOG.info("### Product enrichment cronjob end ###");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * method to list down all mandatory fields that have missing values
     *
     * @param productToCheck : A product with check status
     * @return all missing mandatory fields in a list
     */
    public List<String> getAllMissingFieldsBaseProduct(final ApparelProductModel productToCheck) {
        final List<String> missingFields = new ArrayList<String>();

        if (productToCheck.getCode() == null || productToCheck.getCode().isEmpty()) {
            missingFields.add("Code");
        }
        if (productToCheck.getName(currentLocale) == null || productToCheck.getName(currentLocale).isEmpty()) {
            missingFields.add("Name");
        }
        if (productToCheck.getShortName() == null || productToCheck.getShortName().isEmpty()) {
            missingFields.add("ShortName");
        }
        if (productToCheck.getSummary(currentLocale) == null || productToCheck.getSummary(currentLocale).isEmpty()) {
            missingFields.add("Summary");
        }
        if (productToCheck.getDescription(currentLocale) == null || productToCheck.getDescription(currentLocale).isEmpty()) {
            missingFields.add("Description");
        }
        if (productToCheck.getSupercategories() == null || productToCheck.getSupercategories().isEmpty()) {
            missingFields.add("Supercategories");
        }
        if (productToCheck.getReturnPolicy() == null || productToCheck.getReturnPolicy().isEmpty()) {
            missingFields.add("ReturnPolicy");
        }
        if (productToCheck.getShippingInfo() == null || productToCheck.getShippingInfo().isEmpty()) {
            missingFields.add("ShippingInfo");
        }
        if (productToCheck.getIsAvailableForCoD() == null) {
            missingFields.add("IsAvailableForCoD");
        }
        if (productToCheck.getDeliveryPinCodes() == null || productToCheck.getDeliveryPinCodes().isEmpty()) {
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
        if (productToCheck.getCodPinCodes() == null || productToCheck.getCodPinCodes().isEmpty()) {
            missingFields.add("CodPinCodes");
        }
        if (productToCheck.getApprovalStatus() == null) {
            missingFields.add("ApprovalStatus");
        }

        return missingFields;
    }

    /**
     * Catalog version getter method
     *
     * @return staged product catalog version
     */
    private CatalogVersionModel getCatalogVersionModel() {
        return catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION);
    }

    /**
     * Product Upload automation service getter method
     *
     * @return productUploadAutomationService
     */
    public ProductUploadAutomationService getProductUploadAutomationService() {
        return productUploadAutomationService;
    }
}
