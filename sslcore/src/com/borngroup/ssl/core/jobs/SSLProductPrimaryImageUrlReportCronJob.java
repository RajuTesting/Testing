package com.borngroup.ssl.core.jobs;

import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SSLProductPrimaryImageUrlReportCronJobModel;
import com.borngroup.ssl.core.product.service.SslProductService;

/**
 * Cron Job : Cron Job to create report having primary image url of all products in online catalogue.
 * <p/>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public class SSLProductPrimaryImageUrlReportCronJob extends AbstractJobPerformable<SSLProductPrimaryImageUrlReportCronJobModel> {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SSLProductPrimaryImageUrlReportCronJob.class);

    /** The Constant CSV_TEXT_SEPARATOR. **/
    private static final String CSV_TEXT_SEPARATOR = ",";

    /** The Constant Format. **/
    private static final String FORMAT = "desktop";

    /** The Constant Report Name. **/
    private static final String REPORT_NAME = "REPORT.csv";

    @Resource(name = "sslProductService")
    private SslProductService sslProductService;

    @Resource(name = "commerceStockService")
    private CommerceStockService commerceStockService;

    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    @Override
    public PerformResult perform(final SSLProductPrimaryImageUrlReportCronJobModel cronJobModel) {

        LOG.info("Product Primary Image Url Report Cron Job");
        final List<ApparelStyleVariantProductModel> styleProductList = sslProductService.getProductsForCategory(
                cronJobModel.getCategoryIds(), cronJobModel.getSeasonCode(), cronJobModel.getApprovalStatus(),cronJobModel.getProductCreationDate());
        LOG.info("Total Media :" + styleProductList.size());
        this.createImageUrlReport(styleProductList, cronJobModel);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void createImageUrlReport(final List<ApparelStyleVariantProductModel> productList,
            final SSLProductPrimaryImageUrlReportCronJobModel cronJobModel) {
        final String fileName = Config.getParameter("images.report.path") + File.separator + REPORT_NAME;
        try {
            final PrintWriter printWriter = new PrintWriter(new File(fileName));
            final StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Master SKU Code");
            stringbuilder.append(CSV_TEXT_SEPARATOR);
            stringbuilder.append("Style Code");
            stringbuilder.append(CSV_TEXT_SEPARATOR);
            stringbuilder.append("Style Wise Url");
            stringbuilder.append(CSV_TEXT_SEPARATOR);
            stringbuilder.append("Image Identifier");
            stringbuilder.append(System.lineSeparator());
            for (final ApparelStyleVariantProductModel product : productList) {
                if (cronJobModel.getInventory() == null
                        || checkIfProductHasRequiredInventory(product, cronJobModel.getInventory().intValue())) {
                    for (final MediaContainerModel container : product.getGalleryImages()) {
                        for (final MediaModel media : container.getMedias()) {
                            if (media.getMediaFormat() != null && media.getMediaFormat().getQualifier().equalsIgnoreCase(FORMAT)) {
                                stringbuilder.append(product.getBaseProduct().getCode());
                                stringbuilder.append(CSV_TEXT_SEPARATOR);
                                stringbuilder.append(product.getCode());
                                stringbuilder.append(CSV_TEXT_SEPARATOR);
                                stringbuilder.append(media.getURL());
                                stringbuilder.append(CSV_TEXT_SEPARATOR);
                                stringbuilder.append(media.getCode());
                                stringbuilder.append(System.lineSeparator());
                                break;
                            }

                        }

                    }
                }
            }
            printWriter.write(stringbuilder.toString());
            printWriter.close();

        } catch (final FileNotFoundException e) {
            LOG.error("Error Occurred while creating Report :", e);
        }

    }

    private boolean checkIfProductHasRequiredInventory(final ApparelStyleVariantProductModel styleProduct, final int requestedInventory) {
        int inventoryPresent = 0;
        final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("ssl");
        for (final VariantProductModel sizeVariant : styleProduct.getVariants()) {
            if (sizeVariant instanceof ApparelSizeVariantProductModel) {
                inventoryPresent = inventoryPresent
                        + commerceStockService.getStockLevelForProductAndBaseStore(sizeVariant, baseStore).intValue();

            }
        }

        return inventoryPresent >= requestedInventory;
    }

}
