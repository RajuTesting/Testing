/**
 * 
 */
package com.borngroup.ssl.core.decorator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.BrandMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;


/**
 * @author Deepak
 * 
 */
public class BrandLogoDecorator implements CSVCellDecorator
{
    private static final Logger LOG = Logger.getLogger(BrandLogoDecorator.class);

    @Override
    public String decorate(final int position, final Map<Integer, String> srcLine)
    {
        try
        {
            final String parsedValue = srcLine.get(position);
            final BrandMappingModel brandMappingModel = getSslLookupService().getBrandModelForInputString(parsedValue);
            if (brandMappingModel != null)
            {
                return brandMappingModel.getBrandLogo();
            }
        }
        catch (final Exception e)
        {
            // YTODO Auto-generated catch block
        }
        return "";
    }

    /**
     * @return the sslLookupService
     */
    public SSLLookupService getSslLookupService()
    {
        return (SSLLookupService) Registry.getApplicationContext().getBean("sslLookupService");
    }
}
