/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductFixedPricePromotionModel;
import de.hybris.platform.promotions.model.ProductPercentageDiscountPromotionModel;
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
import org.springframework.beans.factory.annotation.Required;

/**
 * @author pankajaggarwal
 *
 */
public class PromotionsNameValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;
    private PromotionsService promotionService;

    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected PromotionsService getPromotionsService() {
        return this.promotionService;
    }

    @Required
    public void setPromotionsService(final PromotionsService promotionService) {
        this.promotionService = promotionService;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            final ProductModel product = (ProductModel) model;
            final Collection fieldValues = new ArrayList();
            fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty));
            return fieldValues;
        }
        throw new FieldValueProviderException("Cannot get promotion codes of non-product item");
    }

    protected List<FieldValue> createFieldValues(final ProductModel product, final IndexConfig indexConfig,
            final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList();
        final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
        if ((baseSiteModel != null) && (baseSiteModel.getDefaultPromotionGroup() != null)) {
            final List<ProductPromotionModel> promotionsApplicable = getPromotionsService()
                    .getProductPromotions(Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product);
            if (CollectionUtils.isNotEmpty(promotionsApplicable)) {
                for (final ProductPromotionModel promotion : promotionsApplicable) {
                    if (!(promotion instanceof ProductFixedPricePromotionModel)
                            && !(promotion instanceof ProductPercentageDiscountPromotionModel)) {
                        if (StringUtils.isNotEmpty(promotion.getName())) {
                            addFieldValues(fieldValues, indexedProperty, null, promotion.getName());
                        } else {
                            addFieldValues(fieldValues, indexedProperty, null, promotion.getDescription());
                        }
                    }
                }
            }
        }

        return fieldValues;
    }

    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty, final LanguageModel language,
            final Object value) {
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
                (language == null) ? null : language.getIsocode());
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

}
