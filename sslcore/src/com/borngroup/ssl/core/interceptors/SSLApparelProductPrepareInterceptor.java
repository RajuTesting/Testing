/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;
import de.hybris.platform.servicelayer.model.ModelContextUtils;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.BrandMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author midhun.bose
 *
 */
public class SSLApparelProductPrepareInterceptor implements PrepareInterceptor<ApparelProductModel> {
    private static final Logger LOG = Logger.getLogger(SSLApparelProductPrepareInterceptor.class);

    @Resource(name = "sslLookupService")
    private SSLLookupService sslLookupService;

    @Resource(name = "productService")
    private ProductService productService;

    /** The model service. */
    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public void onPrepare(final ApparelProductModel productModel, final InterceptorContext ctx) throws InterceptorException {
        final CatalogVersionModel catalogVersion = productModel.getCatalogVersion();
        if (SslCoreConstants.CATALOG_VERSION.equalsIgnoreCase(catalogVersion.getVersion())) {
            boolean brandCodeOK = false;

            if (!StringUtils.isEmpty(productModel.getBrandCode())) {
                final BrandMappingModel lookedUpBrandCode = sslLookupService.getBrandModelForInputString(productModel.getBrandCode());

                if (lookedUpBrandCode != null) {
                    productModel.setBrandCode(lookedUpBrandCode.getAltBrandDesc());
                    productModel.setAltBrandCode(lookedUpBrandCode.getAltBrandCode());
                    brandCodeOK = true;
                }
            }

            if (!brandCodeOK && productModel.getAltBrandCode() == null) {
                productModel.setBrandCode(SslCoreConstants.NormalizedLookup.BrandNotMapped);
            }
        }

        // If product status is getting changed to APPROVED then set the Approval Date
        // SSLM-5202 : Updating approval date only if a change is made.
        ItemModelContext iMC;
        try {
            iMC = ModelContextUtils.getItemModelContext(productModel);
        } catch (final Exception e) {
            iMC = null;
            LOG.error("Execption occured while trying to load item model context for ProductModel with message " + e.getMessage());
        }

		final List<String> wordPressTags = productModel.getWordPressTags();
		if (CollectionUtils.isNotEmpty(wordPressTags) && null != iMC) {
			final List<String> oldWordPressTagsList = ((ItemModelContextImpl) iMC).getValueHistory() == null ? null
					: (List<String>) ((ItemModelContextImpl) iMC).getValueHistory()
							.getOriginalValue(ApparelProductModel.WORDPRESSTAGS);
			if (CollectionUtils.isEmpty(oldWordPressTagsList) || !wordPressTags.equals(oldWordPressTagsList)) {
				final List<String> newWordPressTagsList = new ArrayList<String>(wordPressTags);
				Collections.sort(newWordPressTagsList);
				productModel.setWordPressTags(newWordPressTagsList);
			}
		}

        if (ctx != null && ctx.isNew(productModel)) {
            productModel.setApprovalDate(new Date());
		} else if (ctx != null && ctx.isModified(productModel, ApparelProductModel.APPROVALSTATUS) && null != iMC
				&& ArticleApprovalStatus.APPROVED.equals(productModel.getApprovalStatus())) {
            try {
                final ArticleApprovalStatus oldStatus = ((ItemModelContextImpl) iMC).getValueHistory() == null ? null
                        : (ArticleApprovalStatus) ((ItemModelContextImpl) iMC).getValueHistory()
                                .getOriginalValue(ApparelProductModel.APPROVALSTATUS);
                if (oldStatus != null && !oldStatus.equals(ArticleApprovalStatus.APPROVED)) {
                    productModel.setApprovalDate(new Date());
                } else {
                    // Sync would always modify the approval date so we need to keep the latest one.
                    final Date olderApprovalDate = (Date) ((ItemModelContextImpl) iMC).getValueHistory()
                            .getOriginalValue(ApparelProductModel.APPROVALDATE);
                    final Date approvalDate = olderApprovalDate == null || olderApprovalDate.compareTo(productModel.getApprovalDate()) == -1
                            ? productModel.getApprovalDate() : olderApprovalDate;
                    productModel.setApprovalDate(approvalDate);
                }
            } catch (final Exception e) {
                // Exception caused when history is not available. We will update approval time and add status to history.
                productModel.setApprovalDate(new Date());
                try {
                    ((ItemModelContextImpl) iMC).getValueHistory().loadOriginalValue(ApparelProductModel.APPROVALSTATUS,
                            productModel.getApprovalStatus());
                    ((ItemModelContextImpl) iMC).getValueHistory().loadOriginalValue(ApparelProductModel.APPROVALDATE,
                            productModel.getApprovalDate());
                } catch (final Exception ex) {
                    LOG.error("Error saving value history for product " + productModel.getCode() + " with message: " + ex.getMessage());
                }
                LOG.error("Error occured in SSLApparelProductPrepareInterceptor with message: " + e.getMessage());
            }
        } else {
            LOG.error("Either InterceptorContext or ItemModelContext was/were null.");
        }
    }
}