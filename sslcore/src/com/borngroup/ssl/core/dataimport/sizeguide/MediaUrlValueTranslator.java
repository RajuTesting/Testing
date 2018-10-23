/**
 * 
 */
package com.borngroup.ssl.core.dataimport.sizeguide;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.media.MediaService;

import org.apache.commons.lang.StringUtils;


/**
 * @author Viji
 * 
 */
public class MediaUrlValueTranslator extends AbstractValueTranslator
{
    private static final String IMAGE_TAG      = "<img src=";
    private static final String CONTENTCATALOG = "sslContentCatalog";
    private static final String CATALOGVERSION = "Online";


    @Override
    public String exportValue(final Object value) throws JaloInvalidParameterException
    {
        return value == null ? "" : value.toString();
    }

    /**
     * This method replaces the media code with the media url for the media code specified in the img src tag.
     */
    @Override
    public Object importValue(final String valueExpr, final Item paramItem) throws JaloInvalidParameterException
    {
        clearStatus();
        final String result = null;
        if (!StringUtils.isBlank(valueExpr))
        {
            try
            {
                final int start = valueExpr.indexOf(IMAGE_TAG);
                final int end = valueExpr.indexOf("\"", start + 10);
                final String mediaCode = valueExpr.substring(start + 10, end);
                final MediaService mediaService = Registry.getApplicationContext().getBean("mediaService", MediaService.class);
                final CatalogVersionService catalogVersionService = Registry.getApplicationContext().getBean(
                        "catalogVersionService", CatalogVersionService.class);
                final MediaModel mediaModel = mediaService.getMedia(getCatalogVersionModel(catalogVersionService), mediaCode);

                if (mediaModel != null)
                {
                    return valueExpr.replace(mediaCode, mediaModel.getURL2());
                }

            }
            catch (final NumberFormatException exc)
            {
                setError();
            }
        }
        return result;
    }

    private CatalogVersionModel getCatalogVersionModel(final CatalogVersionService catalogVersionService)
    {
        return catalogVersionService.getCatalogVersion(CONTENTCATALOG, CATALOGVERSION);
    }

}
