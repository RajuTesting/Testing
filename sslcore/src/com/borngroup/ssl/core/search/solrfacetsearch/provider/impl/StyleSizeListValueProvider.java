/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.commerceservices.util.AbstractComparator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.europe1.model.PriceRowModel;
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
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.data.VariantDTO;
import com.borngroup.ssl.core.data.VariantStyleImagesDTO;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * The Class StyleSizeListValueProvider.
 */
public class StyleSizeListValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /** The media service. */
    @Resource
    private MediaService mediaService;

    /** The media container service. */
    @Resource
    private MediaContainerService mediaContainerService;

    /** The commerce stock service. */
    @Resource
    private CommerceStockService commerceStockService;

    /** The base store service. */
    @Resource
    private BaseStoreService baseStoreService;

    /**
     * SMALL IAMGE URL for mobile view.
     */

    public static final String SMALL_IAMGE_URL = "136Wx204H";

    /**
     * MEDIUM IAMGE URL for tablet view.
     */

    public static final String MEDIUM_IAMGE_URL = "276Wx400H";

    /**
     * LARGE IAMGE URL for desktop view.
     */

    public static final String LARGE_IAMGE_URL = "230Wx334H";

    /**
     * THUMB IAMGE URL for thumb view.
     */

    public static final String THUMB_IAMGE_URL = "30Wx30H";

    /**
     * ALT IAMGE TAG for alt image.
     */

    public static final String ALT_IAMGE_TAG = "alt";

    /**
     * This method creates the field value and set data in solr.
     *
     * @param indexConfig
     *        - Instance of IndexConfig
     * @param indexedProperty
     *        - Instance of IndexedProperty
     * @param model
     *        - Instance of Object
     * @return List of FieldValue
     */
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        final Collection fieldValues = new ArrayList();
        final List<VariantDTO> variantOptions = new ArrayList<>();
        List<VariantProductModel> variantProductModels = null;
        if (model instanceof ApparelProductModel || model instanceof ApparelStyleVariantProductModel) {
            if (model instanceof ApparelStyleVariantProductModel) {
                variantProductModels = (List<VariantProductModel>) ((ApparelProductModel) ((ApparelStyleVariantProductModel) model)
                        .getBaseProduct()).getVariants();
            } else {
                variantProductModels = (List<VariantProductModel>) ((ApparelProductModel) model).getVariants();
            }
            for (final VariantProductModel variantProductModel : variantProductModels) {
                if (variantProductModel instanceof ApparelStyleVariantProductModel
                        && CollectionUtils.isNotEmpty(variantProductModel.getGalleryImages())) {
                    final VariantDTO styleData = new VariantDTO();
                    final ApparelStyleVariantProductModel styleModel = ((ApparelStyleVariantProductModel) variantProductModel);
                    final List<VariantStyleImagesDTO> styleImagesDTOList = new ArrayList<>();
                    final List<VariantStyleImagesDTO> altStyleImagesDTOList = new ArrayList<>();
                    styleData.setCode(styleModel.getCode());
                    final List<MediaContainerModel> mediaContainers = new ArrayList<MediaContainerModel>(
                            variantProductModel.getGalleryImages());
                    Collections.sort(mediaContainers, new Comparator<MediaContainerModel>() {
                        @Override
                        public int compare(final MediaContainerModel mediaContainerModel1, final MediaContainerModel mediaContainerModel2) {
                            if (null == mediaContainerModel1.getQualifier()) {
                                return 1;
                            } else if (null == mediaContainerModel2.getQualifier()) {
                                return -1;
                            } else {
                                return mediaContainerModel1.getQualifier().compareTo(mediaContainerModel2.getQualifier());
                            }

                        }
                    });

                    for (final MediaContainerModel containerModel : mediaContainers) {
                        final VariantStyleImagesDTO styleImagesDTO = new VariantStyleImagesDTO();
                        this.setSwatchUrlForMulitpleImage(styleModel, containerModel, styleData);
                        styleImagesDTO.setSmallImageUrl(getStyleImage(containerModel, SMALL_IAMGE_URL));
                        styleImagesDTO.setMediumImageUrl(getStyleImage(containerModel, MEDIUM_IAMGE_URL));
                        styleImagesDTO.setLargeImageUrl(getStyleImage(containerModel, LARGE_IAMGE_URL));
                        this.variantStyleImageList(styleImagesDTOList, altStyleImagesDTOList, containerModel, styleImagesDTO);
                    }
                    styleData.setColor(styleModel.getStyle());
                    styleData.setSizes(getSizesForStyle(styleModel.getVariants()));
                    styleImagesDTOList.addAll(altStyleImagesDTOList);
                    styleData.setStyleImages(styleImagesDTOList);
                    variantOptions.add(styleData);
                }
            }
        }

        fieldValues.addAll(createFieldValue(variantOptions, indexedProperty));
        return fieldValues;
    }

    /**
     * This method add data to variant style image list.
     *
     * @param styleImagesDTOList
     *        - Collection of VariantStyleImagesDTO
     * @param altStyleImagesDTOList
     *        - Collection of VariantStyleImagesDTO
     * @param containerModel
     *        - Instance of MediaContainerModel
     * @param styleImagesDTO
     *        - Instance of VariantStyleImagesDTO
     */
    private void variantStyleImageList(final List<VariantStyleImagesDTO> styleImagesDTOList,
            final List<VariantStyleImagesDTO> altStyleImagesDTOList, final MediaContainerModel containerModel,
            final VariantStyleImagesDTO styleImagesDTO) {
        if (null != containerModel && !containerModel.getQualifier().contains(ALT_IAMGE_TAG)) {
            styleImagesDTOList.add(styleImagesDTO);
        } else {
            altStyleImagesDTOList.add(styleImagesDTO);
        }
    }

    /**
     * This method set swatch url in to VariantDTO.
     *
     * @param styleModel
     *        - Instance of ApparelStyleVariantProductModel
     * @param containerModel
     *        - Instance of MediaContainerModel
     * @param styleData
     *        - Instance of VariantDTO
     */
    private void setSwatchUrlForMulitpleImage(final ApparelStyleVariantProductModel styleModel, final MediaContainerModel containerModel,
            final VariantDTO styleData) {
        if (StringUtils.isEmpty(styleData.getSwatchUrl())) {
            if (styleModel.getColorSwatchImage() != null) {
                styleData.setSwatchUrl(styleModel.getColorSwatchImage().getUrl());
            } else {
                styleData.setSwatchUrl(getStyleImage(containerModel, THUMB_IAMGE_URL));
            }
        }
    }

    /**
     * This method set swatch url in to VariantDTO.
     *
     * @param styleModel
     *        - Instance of ApparelStyleVariantProductModel
     * @param variantProductModel
     *        - Instance of VariantProductModel
     * @param styleData
     *        - Instance of VariantDTO
     */
    private void setSwatchUrlNoMainImage(final ApparelStyleVariantProductModel styleModel, final VariantProductModel variantProductModel,
            final VariantDTO styleData) {
        if (styleModel.getColorSwatchImage() != null) {
            styleData.setSwatchUrl(styleModel.getColorSwatchImage().getUrl());
        } else {
            styleData.setSwatchUrl(getStyleImage(variantProductModel.getGalleryImages().get(0), THUMB_IAMGE_URL));
        }
    }

    /**
     * Creates the field value.
     *
     * @param styleAndSizesValues
     *        the style and sizes values
     * @param indexedProperty
     *        the indexed property
     * @return the collection<? extends field value>
     */
    private Collection<? extends FieldValue> createFieldValue(final List<VariantDTO> styleAndSizesValues,
            final IndexedProperty indexedProperty) {

        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, getJsonObjectForMap(styleAndSizesValues)));
        }

        return fieldValues;
    }

    /**
     * Gets the sizes for style.
     *
     * @param variantProductModels
     *        the variant product models
     * @return the sizes for style
     */
    private List<VariantDTO> getSizesForStyle(final Collection<VariantProductModel> variantProductModels) {
        final List<VariantDTO> sizes = new ArrayList<VariantDTO>();

        if (variantProductModels != null) {
            for (final VariantProductModel variantProductModel : variantProductModels) {
                if (variantProductModel instanceof ApparelSizeVariantProductModel) {
                    final VariantDTO sizeData = new VariantDTO();

                    final ApparelSizeVariantProductModel sizeVariantProductModel = (ApparelSizeVariantProductModel) variantProductModel;
                    sizeData.setCode(sizeVariantProductModel.getCode());
                    sizeData.setSize(sizeVariantProductModel.getSize());
                    sizeData.setSizeDesc(sizeVariantProductModel.getAlt_size_desc());
                    sizeData.setPrice(getProductPrice(variantProductModel).doubleValue());
                    getStocksForProduct(variantProductModel, sizeData);
                    sizes.add(sizeData);
                }
            }
        }

        return sizes;
    }

    /**
     * Gets the stocks for product.
     *
     * @param variantProductModel
     *        the variant product model
     * @param sizeData
     *        the size data
     */
    private void getStocksForProduct(final VariantProductModel variantProductModel, final VariantDTO sizeData) {
        final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();

        if (!isStockSystemEnabled(baseStore)) {
            sizeData.setStock(0);
        } else {
            final Long stock = getCommerceStockService().getStockLevelForProductAndBaseStore(variantProductModel, baseStore);
            if (stock != null) {
                sizeData.setStock(stock);
            }
        }

    }

    /**
     * Checks if is stock system enabled.
     *
     * @param baseStore
     *        the base store
     * @return true, if is stock system enabled
     */
    protected boolean isStockSystemEnabled(final BaseStoreModel baseStore) {
        return commerceStockService.isStockSystemEnabled(baseStore);
    }

    /**
     * Gets the style image.
     *
     * @param mediaContainer
     *        the media container
     * @param imageFormat
     *        the image format
     * @return the style image
     */
    private String getStyleImage(final MediaContainerModel mediaContainer, final String imageFormat) {
        MediaModel media = null;
        if (null != mediaContainer && mediaContainer.getQualifier() != null) {

            media = getMediaWithImageFormat(mediaContainer, imageFormat);
            if (media != null) {
                return media.getUrl();
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * Gets the media with image format.
     *
     * @param mediaContainer
     *        the media container
     * @param imageFormat
     *        the image format
     * @return the media with image format
     */
    protected MediaModel getMediaWithImageFormat(final MediaContainerModel mediaContainer, final String imageFormat) {
        if (mediaContainer != null && imageFormat != null) {
            final MediaFormatModel mediaFormat = getMediaService().getFormat(imageFormat);

            if (mediaFormat != null) {
                try {
                    return getMediaContainerService().getMediaForFormat(mediaContainer, mediaFormat);
                } catch (final ModelNotFoundException modelNotFoundException) {
                    LOG.warn(modelNotFoundException);
                }
            }
        }
        return null;
    }

    /**
     * Gets the json object for map.
     *
     * @param styleAndSizesValues
     *        the style and sizes values
     * @return the json object for map
     */
    private String getJsonObjectForMap(final List<VariantDTO> styleAndSizesValues) {
        String mapAsJson = "";
        try {
            mapAsJson = new ObjectMapper().writeValueAsString(styleAndSizesValues);
        } catch (final IOException e) {
            LOG.error(e);
        }
        return mapAsJson;
    }

    /**
     * This method gets the least price price of the product from all its variants.
     *
     * @param product
     *        the product
     * @return the product price
     */
    private Double getProductPrice(final VariantProductModel product) {
        if (product.getEurope1Prices() != null && !(product.getEurope1Prices().isEmpty())) {
            return getPriceRangeString(new ArrayList<>(product.getEurope1Prices()));
        } else {
            return new Double(0.00);
        }

    }

    /**
     * Gets the price range string.
     *
     * @param allPricesInfos
     *        the all prices infos
     * @return the price range string
     */
    protected Double getPriceRangeString(final List<PriceRowModel> allPricesInfos) {
        Collections.sort(allPricesInfos, PriceRangeComparator.INSTANCE);
        return allPricesInfos.get(0).getPrice();
    }

    /**
     * The Class PriceRangeComparator.
     */
    public static class PriceRangeComparator extends AbstractComparator<PriceRowModel> {

        /** The Constant INSTANCE. */
        public static final PriceRangeComparator INSTANCE = new PriceRangeComparator();

        @Override
        protected int compareInstances(final PriceRowModel price1, final PriceRowModel price2) {
            if (price1 == null || price1.getPrice() == null) {
                return BEFORE;
            }
            if (price2 == null || price2.getPrice() == null) {
                return AFTER;
            }

            return Double.compare(price1.getPrice(), price2.getPrice());
        }
    }

    /**
     * Sets the field name provider.
     *
     * @param fieldNameProvider1
     *        the new field name provider
     */
    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider1) {
        this.fieldNameProvider = fieldNameProvider1;
    }

    /**
     * Gets the field name provider.
     *
     * @return the field name provider
     */
    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    /**
     * Gets the media service.
     *
     * @return the media service
     */
    public MediaService getMediaService() {
        return mediaService;
    }

    /**
     * Sets the media service.
     *
     * @param mediaService1
     *        the new media service
     */
    public void setMediaService(final MediaService mediaService1) {
        this.mediaService = mediaService1;
    }

    /**
     * Gets the media container service.
     *
     * @return the media container service
     */
    public MediaContainerService getMediaContainerService() {
        return mediaContainerService;
    }

    /**
     * Sets the media container service.
     *
     * @param mediaContainerService1
     *        the new media container service
     */
    public void setMediaContainerService(final MediaContainerService mediaContainerService1) {
        this.mediaContainerService = mediaContainerService1;
    }

    /**
     * Gets the commerce stock service.
     *
     * @return the commerce stock service
     */
    public CommerceStockService getCommerceStockService() {
        return commerceStockService;
    }

    /**
     * Sets the commerce stock service.
     *
     * @param commerceStockService1
     *        the new commerce stock service
     */
    public void setCommerceStockService(final CommerceStockService commerceStockService1) {
        this.commerceStockService = commerceStockService1;
    }

    /**
     * Gets the base store service.
     *
     * @return the base store service
     */
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * Sets the base store service.
     *
     * @param baseStoreService1
     *        the new base store service
     */
    public void setBaseStoreService(final BaseStoreService baseStoreService1) {
        this.baseStoreService = baseStoreService1;
    }

}
