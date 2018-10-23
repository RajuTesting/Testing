package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * Solr Custom Value Provider : Index the products which are eligible for Pick from store.
 *
 * @author SSL email id : mayank.gupta@nagarro.com
 */
public class PickFromStoreProductsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {

    private static final String EXPRESS_STORE_PICK_UP = "Express Store Pick up";

    private static final Logger LOG = Logger.getLogger(PickFromStoreProductsValueProvider.class);

    /* {@link FieldNameProvider}. */
    private FieldNameProvider fieldNameProvider;

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        try {
            return this.getProductsAvailableForStorePickUp(((ApparelStyleVariantProductModel) model).getBaseProduct(), indexedProperty);
        } catch (final Exception e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }
        return Collections.emptyList();
    }

    private List<FieldValue> getProductsAvailableForStorePickUp(final ProductModel productModel, final IndexedProperty indexedProperty) {
        return (null != productModel.getIsAvailable() && productModel.getIsAvailable()) ? new ArrayList<>(createFieldValue(indexedProperty)) : Collections
                .emptyList();
    }

    private Collection<? extends FieldValue> createFieldValue(final IndexedProperty indexedProperty) {
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        return fieldNames.stream().filter(Objects::nonNull).map(fieldName -> new FieldValue(fieldName, EXPRESS_STORE_PICK_UP))
                .collect(Collectors.toList());
    }

    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}
