/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;
import de.hybris.platform.servicelayer.model.ModelContextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.SizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;


/**
 * @author midhun.bose
 *
 */
public class SSLApparelSizeVariantProductPrepareInterceptor implements PrepareInterceptor<ApparelSizeVariantProductModel>
{

	private static final Logger LOG = Logger.getLogger(SSLApparelSizeVariantProductPrepareInterceptor.class);

    @Resource(name = "sslLookupService")
    private SSLLookupService sslLookupService;


    /*
     * (non-Javadoc)
     *
     * @see Checks for valid Size Code when the product gets saved
     */
    @Override
    public void onPrepare(final ApparelSizeVariantProductModel productModel, final InterceptorContext arg1)
            throws InterceptorException
    {

		ItemModelContext iMC;
		try {
			iMC = ModelContextUtils.getItemModelContext(productModel);
		} catch (final Exception e) {
			iMC = null;
			LOG.error("Execption occured while trying to load item model context for ProductModel with message "
					+ e.getMessage());
		}

		final List<String> wordPressTags = productModel.getWordPressTags();
		if (CollectionUtils.isNotEmpty(wordPressTags) && null != iMC) {
			final List<String> oldWordPressTagsList = ((ItemModelContextImpl) iMC).getValueHistory() == null ? null
					: (List<String>) ((ItemModelContextImpl) iMC).getValueHistory()
							.getOriginalValue(ApparelSizeVariantProductModel.WORDPRESSTAGS);
			if (CollectionUtils.isEmpty(oldWordPressTagsList) || !wordPressTags.equals(oldWordPressTagsList)) {
				final List<String> newWordPressTagsList = new ArrayList<String>(wordPressTags);
				Collections.sort(newWordPressTagsList);
				productModel.setWordPressTags(newWordPressTagsList);
			}
		}

        final CatalogVersionModel catalogVersion = productModel.getCatalogVersion();
        if (SslCoreConstants.CATALOG_VERSION.equalsIgnoreCase(catalogVersion.getVersion()))
        {

            boolean sizeCodeOK = false;
            final String deptCode = productModel.getBaseProduct().getDepartmentCode();
            final String subDeptCode = productModel.getBaseProduct().getSubDepartmentCode();
            final String brandCode = productModel.getBaseProduct().getBrandCode();
            String altBrandCode = productModel.getBaseProduct().getAltBrandCode();
            altBrandCode = altBrandCode != null && !altBrandCode.isEmpty() ? altBrandCode : "";

            productModel.setBrandCode(brandCode);
            productModel.setAltBrandCode(altBrandCode);
            productModel.setDepartmentCode(deptCode);
            productModel.setSubDepartmentCode(subDeptCode);

            if (!StringUtils.isEmpty(productModel.getSize()))
            {
                SizeMappingModel lookedUpSizeCode = sslLookupService.getSizeCodeForInputStrings(productModel.getSize(), deptCode,
                        altBrandCode, subDeptCode);
                if (lookedUpSizeCode == null)
                {
                    lookedUpSizeCode = sslLookupService.getSizeCodeForActualCode(productModel.getSize(), deptCode, altBrandCode,
                            subDeptCode);
                }

                if (lookedUpSizeCode != null)
                {
                    final SizeMappingModel actualSizeCode = sslLookupService.getSizeCodeForActualCode(
                            lookedUpSizeCode.getAltSizeCode(), deptCode, altBrandCode, subDeptCode);
                    if (!StringUtils.isEmpty(actualSizeCode))
                    {
                        productModel.setSize(actualSizeCode.getAltSizeCode());
                        productModel.setAlt_size_desc(actualSizeCode.getAltSizeDesc());
                        sizeCodeOK = true;
                    }
                }
            }

            if (!sizeCodeOK)
            {
                productModel.setSize(SslCoreConstants.NormalizedLookup.SizeNotMapped);
            }
        }

    }
}
