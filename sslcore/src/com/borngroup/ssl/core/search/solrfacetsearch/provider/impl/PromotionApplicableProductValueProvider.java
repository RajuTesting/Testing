/**
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author Koteswara Rao s52543 It is indexing isPromotionApplicable property value is true in product has promotion
 */
public class PromotionApplicableProductValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /**
     * Field Value Provider.
     */
    private FieldNameProvider fieldNameProvider;

    /**
     * Promotion Service.
     */
    private PromotionsService promotionsService;

    /**
     * Logger for class SSLPromotionTagValueProvider.
     */
    private static final Logger LOG = Logger.getLogger(PromotionApplicableProductValueProvider.class);

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
     * @return instance of fieldNameProvider.
     */
    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    /**
     * setter method for promotionsService.
     *
     * @param promotionsService
     *        promotionService to be set.
     */
    @Required
    public void setPromotionsService(final PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    /**
     * getter method for promotionsService.
     *
     * @return instance of promotionsService.
     */
    protected PromotionsService getPromotionsService() {
        return this.promotionsService;
    }

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexProperty, final Object model)
            throws FieldValueProviderException {

        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            final Collection<FieldValue> fieldValues = new ArrayList();
            fieldValues.addAll(this.createFieldValue(apparelModel, indexConfig, indexProperty));
            return fieldValues;
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelStyleVariantProductModel apparelModel = (ApparelStyleVariantProductModel) model;
            final Collection<FieldValue> fieldValues = new ArrayList();
            fieldValues.addAll(this.createFieldValue(apparelModel.getBaseProduct(), indexConfig, indexProperty));
            return fieldValues;
        }
        return null;
    }

    protected List<FieldValue> createFieldValue(final ProductModel product, final IndexConfig indexConfig,
            final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>(0);
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        final Boolean isProductHasPromotion = getValueForField(product, indexedProperty, indexConfig);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, isProductHasPromotion));
        }
        return fieldValues;
    }

    private Boolean getValueForField(final ProductModel product, final IndexedProperty indexedProperty, final IndexConfig indexConfig) {
        Boolean isPromotionApplicable = false;
        final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
        if ((baseSiteModel != null) && (baseSiteModel.getDefaultPromotionGroup() != null)) {
            final List<ProductPromotionModel> promotionsApplicable = getPromotionsService().getProductPromotions(
                    Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product);
            if (CollectionUtils.isNotEmpty(promotionsApplicable)) {
                isPromotionApplicable = true;
            }
        }
        return isPromotionApplicable;
    }
}
