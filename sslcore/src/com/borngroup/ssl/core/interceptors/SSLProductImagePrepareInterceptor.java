/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.util.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;

/**
 * @author dikshabhatia
 *
 */
public class SSLProductImagePrepareInterceptor implements PrepareInterceptor<ProductModel> {

    private static final Logger LOG = Logger.getLogger(SSLProductImagePrepareInterceptor.class);

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
     * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
     */
    @Override
    public void onPrepare(final ProductModel productModel, final InterceptorContext context) throws InterceptorException {

        if (!(productModel instanceof ApparelSizeVariantProductModel)) {
            final CatalogVersionModel catalogVersion = productModel.getCatalogVersion();

            if (SslCoreConstants.CATALOG_VERSION.equalsIgnoreCase(catalogVersion.getVersion())) {
                if (context.isModified(productModel, SslCoreConstants.APPROVALSTATUS)
                        && ArticleApprovalStatus.APPROVED.equals(productModel.getApprovalStatus())) {

                    final List<MediaContainerModel> mediaContainers = productModel.getGalleryImages();
                    final List<MediaContainerModel> unapprovedMediaContainers = new ArrayList<MediaContainerModel>();
                    if (!mediaContainers.isEmpty()) {
                        for (final MediaContainerModel mediaContainer : mediaContainers) {

                            if (!SslCoreConstants.CONVERTED.equalsIgnoreCase(mediaContainer.getConversionStatus().toString())) {
                                unapprovedMediaContainers.add(mediaContainer);
                            }
                        }

                        if (!unapprovedMediaContainers.isEmpty()) {
                            writeProductDetailsToFile(productModel, unapprovedMediaContainers, SslCoreConstants.UNAPPROVED_MEDIA_CONATINERS);
                            throw new InterceptorException(SslCoreConstants.UNAPPROVED_MEDIA_CONATINERS);
                        }
                    } else {
                        writeProductDetailsToFile(productModel, unapprovedMediaContainers, SslCoreConstants.EMPTY_GALLERY_IMAGE);
                        throw new InterceptorException(SslCoreConstants.EMPTY_GALLERY_IMAGE);

                    }
                }
            }

        }
    }

    public void writeProductDetailsToFile(final ProductModel productModel, final List<MediaContainerModel> unapprovedMediaContainers,
            final String reasonForDisapproval) {
        final String alternativePath = Config.getParameter(SslCoreConstants.PRODUCT_FILE_PATH);
        final File productFile = loadPath(alternativePath);
        LOG.info(" productFileFolder : " + productFile);
		try (FileWriter file = new FileWriter(productFile, true); BufferedWriter writer = new BufferedWriter(file)) {
            writer.newLine();
            if (!unapprovedMediaContainers.isEmpty()) {
                for (final MediaContainerModel unapprovedMediaContainer : unapprovedMediaContainers) {

                    writer.write(SslCoreConstants.PRODUCT_CODE + productModel.getCode() + SslCoreConstants.MEDIA_CONTAINER_PK
                            + unapprovedMediaContainer.getPk() + SslCoreConstants.MEDIA_CONTAINER_QUALIFIER
                            + unapprovedMediaContainer.getQualifier() + SslCoreConstants.REASON + reasonForDisapproval);
                }
            } else {

                writer.write(SslCoreConstants.PRODUCT_CODE + productModel.getCode() + SslCoreConstants.MEDIA_CONTAINER_PK + "NULL"
                        + SslCoreConstants.MEDIA_CONTAINER_QUALIFIER + "NULL" + SslCoreConstants.REASON + reasonForDisapproval);
            }
        } catch (final IOException e) {
            LOG.error(e.getStackTrace().toString());
        }

    }

    public File loadPath(final String alternativePath) {
        File productFile = null;

        if (!StringUtils.isEmpty(alternativePath)) {
            productFile = new File(alternativePath);
        } else {
            productFile = new File(Config.getParameter(SslCoreConstants.PRODUCT_FILE_PATH));

            LOG.info("in LOAD PATH: " + productFile);
        }
        return productFile;
    }

}
