/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * <p>
 * ValueProvider : Generic Class for indexing of Boolean fields of Product.
 * <p>
 * Created By : pankaj.sharma03@nagarro.com
 *
 * @author Ssl
 */
public class SSLGenericProductBooleanFieldsFacetValueProvider extends AbstractPropertyFieldValueProvider
        implements FieldValueProvider, Serializable {
    /**
     * Logger for class SSLGenericProductBooleanFieldsFacetValueProvider.
     */
    private static final Logger LOG = Logger.getLogger(SSLGenericProductBooleanFieldsFacetValueProvider.class);
    /**
     * Field Value Provider.
     */
    private FieldNameProvider fieldNameProvider;

    /**
     * Promotion Service.
     */
    private PromotionsService promotionsService;

    /**
     * Method for calculating values for product boolean attributes for solr indexing.
     *
     * @param model
     *        product to be indexed
     * @param indexConfig
     *        object containing solr configurations
     * @param indexedProperty
     *        the property of the product which is to be indexed
     * @return Collection of fieldValues
     * @throws FieldValueProviderException
     */
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        Collection<FieldValue> fieldValues = new ArrayList<>(0);
        if (StringUtils.isNotEmpty(indexedProperty.getValueProviderParameter())) {
            if (model instanceof ApparelProductModel) {
                final ApparelProductModel apparelModel = (ApparelProductModel) model;
                fieldValues.addAll(this.createFieldValue(apparelModel, indexedProperty, indexConfig));
            } else if (model instanceof ApparelStyleVariantProductModel) {
                final ApparelStyleVariantProductModel apparelModel = (ApparelStyleVariantProductModel) model;
                fieldValues.addAll(this.createFieldValue(apparelModel, indexedProperty, indexConfig));
            } else {
                fieldValues = Collections.emptyList();
            }
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("SSLGenericProductBooleanFieldsFacetValueProvider:getFieldValues()");
                LOG.debug("No valueProviderParameter found for this job");
            }
            fieldValues = Collections.emptyList();
        }
        return fieldValues;
    }

    /**
     * Creates the field values for the property which is to be indexed.
     *
     * @param product
     *        product to be indexed
     * @param indexedProperty
     *        the property of the product which is to be indexed
     * @param indexConfig
     *        object containing solr configurations
     * @return the collection of the field values
     */
    private Collection<? extends FieldValue> createFieldValue(final ProductModel product, final IndexedProperty indexedProperty,
            final IndexConfig indexConfig) {
        final List<FieldValue> fieldValues = new ArrayList<>(0);
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        final Boolean productFieldBooleanValue = getValueForField(product, indexedProperty, indexConfig);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, productFieldBooleanValue));
        }
        return fieldValues;
    }

    /**
     * Method for returing the boolean value for indexed field.
     *
     * @param product
     *        product to be indexed
     * @param indexConfig
     *        object containing solr configurations
     * @param indexedProperty
     *        the property of the product which is to be indexed
     * @return boolean value of field
     */
    private Boolean getValueForField(final ProductModel product, final IndexedProperty indexedProperty, final IndexConfig indexConfig) {
        Boolean value = false;
        final TagType tagType = TagType.getTagTypeEnum(indexedProperty.getValueProviderParameter());
        if(null == tagType) {
            return Boolean.FALSE;
        }
        switch (tagType) {
        case NEW_ARRIVAL:
            value = product.getNewArrival();
            break;
        case ONLINE_EXCLUSIVE:
            value = product.getOnlineExclusive();
            break;
        case EXCLUSIVE:
            value = product.getExclusive();
            break;
        case BEST_SELLER:
            value = product.getBestSeller();
            break;
        case LIMITED:
            value = product.getLimited();
            break;
        case GIFT_PRODUCT:
            value = product.getShowGiftProductLabel();
            break;
        case DEAL_OF_THE_DAY:
            value = product.getDealOfTheDay();
            break;
        case MULTIPLE_PROMOTIONS_APPLICABLE:
            final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
            List<ProductPromotionModel> promotionsApplicable = null;
            if ((baseSiteModel != null) && (baseSiteModel.getDefaultPromotionGroup() != null)) {
                promotionsApplicable = this.getPromotionsService()
                        .getProductPromotions(Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product);
                if (CollectionUtils.isNotEmpty(promotionsApplicable) && 1 < promotionsApplicable.size()) {
                    value = true;
                }
            }
            break;
        default:
            value = Boolean.FALSE;
            break;
        }
        this.logForValue(indexedProperty.getValueProviderParameter(), product.getCode());
        return value;
    }

    /**
     * setter method for fieldNameProvider.
     *
     * @param fieldNameProvider
     *        fieldNameProvider to be set.
     */
    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    /**
     * getter method for fieldNameProvider.
     *
     * @return instance of fieldNameProvider
     */
    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    /**
     * setter method for promotionsService.
     *
     * @param promotionsService
     *        promotionsService to be set
     */
    @Required
    public void setPromotionsService(final PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    /**
     * getter method for promotionsService
     *
     * @return instance of promotionsService
     */
    protected PromotionsService getPromotionsService() {
        return this.promotionsService;
    }

    /**
     * Method for logging of the attributes and values.
     *
     * @param value
     *        value that needs to be logged
     * @param productCode
     *        code of the product model
     */
    private void logForValue(final String value, final String productCode) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("SSLGenericProductBooleanFieldsFacetValueProvider:logForValue()");
            LOG.debug("Indexing for code field name : " + value + " and product with code : " + productCode);
        }
    }

    /**
     * Enum of Tag Type for Listing Pages
     */
    private enum TagType {
        NEW_ARRIVAL("new_arrival"), ONLINE_EXCLUSIVE("online_exclusive"), EXCLUSIVE("exclusive"), BEST_SELLER("best_seller"), LIMITED(
                "limited"), GIFT_PRODUCT("gift_product"), DEAL_OF_THE_DAY(
                        "deal_of_the_day"), MULTIPLE_PROMOTIONS_APPLICABLE("multiple_promotions_applicable");

        /**
         * value of enum
         */
        private final String enumValue;

        /**
         * Constructor for assigning value to enum
         *
         * @param enumValue
         */
        private TagType(final String enumValue) {
            this.enumValue = enumValue;
        }

        /**
         * getter method for Enum Value
         *
         * @return String value of enum
         */
        String getEnumValue() {
            return this.enumValue;
        }

        /**
         * Method for returning enum value corresponding to String
         *
         * @param tagTypeStr
         *        value for which enum is to be returned
         * @return EnumValue for string
         */
        static TagType getTagTypeEnum(final String tagTypeStr) {
            TagType tagType = null;
            if (StringUtils.isNotEmpty(tagTypeStr)) {
                for (final TagType type : TagType.values()) {
                    if (type.getEnumValue().equals(tagTypeStr)) {
                        tagType = type;
                        break;
                    }
                }
            }
            return tagType;
        }
    }
}


