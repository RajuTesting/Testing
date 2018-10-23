
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
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;
import de.hybris.platform.servicelayer.model.ModelContextUtils;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.enums.SwatchColorEnum;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.services.SSLLookupService;
import com.borngroup.ssl.core.services.SslSwatchColorErrorReportService;
import com.borngroup.ssl.facades.data.SwatchColorErrorData;


/**
 * @author midhun.bose
 *
 */
public class SSLApparelStyleVariantProductPrepareInterceptor implements PrepareInterceptor<ApparelStyleVariantProductModel>
{
	private static final Logger LOG = Logger.getLogger(SSLApparelStyleVariantProductPrepareInterceptor.class);
    @Resource(name = "sslLookupService")
    private SSLLookupService   sslLookupService;

    @Resource(name = "enumerationService")
    private EnumerationService enumerationService;

    @Resource(name = "modelService")
	private ModelService modelService;

    @Resource(name = "sslSwatchColorErrorReportService")
	private SslSwatchColorErrorReportService sslSwatchColorErrorReportService;

    /*
     * (non-Javadoc)
     *
     * @see Checks for valid Style Code when the product gets saved
     */
    @Override
    public void onPrepare(final ApparelStyleVariantProductModel productModel, final InterceptorContext arg1)
            throws InterceptorException
    {
        final CatalogVersionModel catalogVersion = productModel.getCatalogVersion();
		String colorFamily = null;

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
							.getOriginalValue(ApparelStyleVariantProductModel.WORDPRESSTAGS);
			if (CollectionUtils.isEmpty(oldWordPressTagsList) || !wordPressTags.equals(oldWordPressTagsList)) {
				final List<String> newWordPressTagsList = new ArrayList<String>(wordPressTags);
				Collections.sort(newWordPressTagsList);
				productModel.setWordPressTags(newWordPressTagsList);
			}
		}

        if (SslCoreConstants.CATALOG_VERSION.equalsIgnoreCase(catalogVersion.getVersion()))
        {
			boolean colourCodeOK = false;
			if (StringUtils.isNotEmpty(productModel.getColourCode()))
            {
                String lookedUpColourCode = sslLookupService.getColourCodeForInputString(productModel.getColourCode());
                if (StringUtils.isEmpty(lookedUpColourCode))
                {
                    lookedUpColourCode = sslLookupService.getColourCodeForActualCode(productModel.getColourCode());
                }
                if (StringUtils.isNotEmpty(lookedUpColourCode))
                {
					productModel.setColourCode(lookedUpColourCode);
					colourCodeOK = true;
					colorFamily = sslLookupService.getColourFamilyForAltDesc(lookedUpColourCode);
                }else{
					colorFamily = sslLookupService.getColourFamilyForActualCode(productModel.getColourCode());
                }
				if (StringUtils.isNotEmpty(colorFamily)) {
					try {
						enumerationService.getEnumerationValue(SwatchColorEnum.class, colorFamily.toUpperCase());
						final Set<SwatchColorEnum> swatchSet = ((productModel.getSwatchColors() != null) ? new HashSet<>(productModel.getSwatchColors()): new HashSet<SwatchColorEnum>());
						swatchSet.add(SwatchColorEnum.valueOf(colorFamily.toUpperCase()));
						productModel.setSwatchColors(swatchSet);
					} catch (final Exception exception)
                    {
						final String errorMsg="No enum value found in the SwatchColorEnum with the Color Family "+colorFamily;
						LOG.error(errorMsg+"/n"+exception);
						colourCodeOK = false;
						this.prepateData(productModel.getCode(),colorFamily,errorMsg);
                    }
				}
			}
            productModel.setBrandCode(productModel.getBaseProduct().getBrandCode());
            productModel.setAltBrandCode(productModel.getBaseProduct().getAltBrandCode());

            if (!colourCodeOK)
            {
                productModel.setColourCode(SslCoreConstants.NormalizedLookup.ColorNotMapped);

            }
        }

    }
    private void prepateData(final String skuCode,final String colorFamily,final String errorMsg){
    	final SwatchColorErrorData swatchColorErrorData=new SwatchColorErrorData();
    	swatchColorErrorData.setSkuCode(skuCode);
    	if(StringUtils.isNotBlank(skuCode)){
    		final String[] str= skuCode.split("_");
    		if(str.length>1){
    	      swatchColorErrorData.setColorCode(str[1]);
    		}
    	}
    	swatchColorErrorData.setColorFamily(colorFamily);
    	swatchColorErrorData.setErrorMessage(errorMsg);
    	swatchColorErrorData.setEventDate(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(new Date()));
		try {
			sslSwatchColorErrorReportService.generateSendErorsXlsReport(swatchColorErrorData, false);
		} catch (final Exception ex) {
			LOG.error(ex.getMessage());
		}
    }
}