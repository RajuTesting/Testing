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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * SSLBrandRedirectValueProvider. Index values which are used in brand redirect.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SSLBrandRedirectValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /**
     * Gets FieldNameProvider.
     *
     * @return the FieldNameProvider
     */
    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    /**
     * Sets the fieldNameProvider.
     *
     * @param fieldNameProvider
     *        Field Name Provider.
     */
    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            ProductModel product = (ProductModel) model;
            if (product instanceof ApparelStyleVariantProductModel) {
                product = ((ApparelStyleVariantProductModel) product).getBaseProduct();
            }

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

        String brand = product.getBrandCode();
        if (null != brand) {
            brand = brand.toLowerCase().replaceAll("[^\\p{L}]", "");
        }
        addFieldValues(fieldValues, indexedProperty, language, brand);

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
