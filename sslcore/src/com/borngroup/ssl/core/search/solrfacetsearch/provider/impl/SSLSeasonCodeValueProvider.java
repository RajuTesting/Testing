/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

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

import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * @author nikhilbarar
 *
 */
public class SSLSeasonCodeValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private FieldNameProvider fieldNameProvider;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues
     * (de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            return getSeasonCode(apparelModel, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * @param apparelModel
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<FieldValue> getSeasonCode(final ApparelProductModel apparelModel, final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        if (StringUtils.isNotEmpty(apparelModel.getSeasonCode())) {
            fieldValues.addAll(createFieldValue(apparelModel.getSeasonCode(), indexedProperty));
        }
        return fieldValues;
    }

    /**
     * @param seasonCode
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final String seasonCode, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, seasonCode));
        }
        return fieldValues;
    }

    /**
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * @param fieldNameProvider
     *        the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}
