/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class SslImageValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
    private static final Logger   LOG = Logger.getLogger(SslImageValueProvider.class);

    private String                mediaFormat;
    private MediaService          mediaService;
    private MediaContainerService mediaContainerService;
    private FieldNameProvider     fieldNameProvider;

    protected String getMediaFormat()
    {
        return this.mediaFormat;
    }

    @Required
    public void setMediaFormat(final String mediaFormat)
    {
        this.mediaFormat = mediaFormat;
    }

    protected MediaService getMediaService()
    {
        return this.mediaService;
    }

    @Required
    public void setMediaService(final MediaService mediaService)
    {
        this.mediaService = mediaService;
    }

    protected MediaContainerService getMediaContainerService()
    {
        return this.mediaContainerService;
    }

    @Required
    public void setMediaContainerService(final MediaContainerService mediaContainerService)
    {
        this.mediaContainerService = mediaContainerService;
    }

    protected FieldNameProvider getFieldNameProvider()
    {
        return this.fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
            final Object model) throws FieldValueProviderException
    {
        if (model instanceof ProductModel)
        {
            final MediaFormatModel mediaFormatModel = getMediaService().getFormat(getMediaFormat());
            if (mediaFormatModel != null)
            {
                final MediaModel media = findMedia((ProductModel) model, mediaFormatModel);
                if (media != null)
                {
                    return createFieldValues(indexedProperty, media);
                }
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("No [" + mediaFormatModel.getQualifier() + "] image found for product ["
                            + ((ProductModel) model).getCode() + "]");
                }
            }
        }
        return Collections.emptyList();
    }

    protected MediaModel findMedia(final ProductModel product, final MediaFormatModel mediaFormat)
    {
        if ((product != null) && (mediaFormat != null))
        {
            final List<MediaContainerModel> galleryImages = product.getGalleryImages();
            if ((galleryImages != null) && (!(galleryImages.isEmpty())))
            {
                try
                {
                    for (final MediaContainerModel container : galleryImages)
                    {

                        if (!container.getQualifier().contains("alt"))
                        {
                            final MediaModel media = getMediaContainerService().getMediaForFormat(container, mediaFormat);
                            if (media != null)
                            {
                                return media;
                            }
                        }
                    }

                    final MediaModel mediaModel = getMediaContainerService().getMediaForFormat(galleryImages.get(0), mediaFormat);
                    if (mediaModel != null)
                    {
                        return mediaModel;
                    }
                }
                catch (final ModelNotFoundException localModelNotFoundException)
                {
                    LOG.info(localModelNotFoundException.getMessage());
                }

            }

            if (product instanceof VariantProductModel)
            {
                return findMedia(((VariantProductModel) product).getBaseProduct(), mediaFormat);
            }
        }
        return null;
    }

    protected Collection<FieldValue> createFieldValues(final IndexedProperty indexedProperty, final MediaModel media)
    {
        return createFieldValues(indexedProperty, media.getURL());
    }

    protected Collection<FieldValue> createFieldValues(final IndexedProperty indexedProperty, final String value)
    {
        final List<FieldValue> fieldValues = new ArrayList();

        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, value));
        }

        return fieldValues;
    }
}
