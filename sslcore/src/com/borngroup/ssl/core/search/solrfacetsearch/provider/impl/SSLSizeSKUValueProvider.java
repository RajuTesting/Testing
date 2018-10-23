/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
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
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author midhun.bose
 *
 */
public class SSLSizeSKUValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;

    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            final ProductModel product = (ProductModel) model;

            final Collection fieldValues = new ArrayList();

            if (indexedProperty.isLocalized()) {
                final Collection<LanguageModel> languages = indexConfig.getLanguages();
                for (final LanguageModel language : languages) {
                    fieldValues.addAll(createFieldValue(product, language, indexedProperty));
                }
            } else {
                fieldValues.addAll(createFieldValue(product, null, indexedProperty));
            }
            return fieldValues;
        }

        throw new FieldValueProviderException("Cannot evaluate rating of non-product item");
    }

    protected List<FieldValue> createFieldValue(final ProductModel product, final LanguageModel language,
            final IndexedProperty indexedProperty) {
        final List fieldValues = new ArrayList();

        final List<String> displayCodeList = getDisplayCodeList(product);

        if (displayCodeList != null) {
            for (final String displayCode : displayCodeList) {
                addFieldValues(fieldValues, indexedProperty, language, displayCode);
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

    protected List<String> getDisplayCodeList(final ProductModel product) {
        final List<String> displayCodeList = new ArrayList<>();
        for (final VariantProductModel variant : product.getVariants()) {
            if (variant instanceof ApparelSizeVariantProductModel) {
                displayCodeList.add(variant.getCode());
            } else if (variant instanceof ApparelStyleVariantProductModel) {
                for (final VariantProductModel sizeVariant : ((ApparelStyleVariantProductModel) variant).getVariants()) {
                    displayCodeList.add(sizeVariant.getCode());
                }
            }
        }
        return displayCodeList;
    }

}
