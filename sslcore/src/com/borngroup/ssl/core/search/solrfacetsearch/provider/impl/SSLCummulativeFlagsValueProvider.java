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
import java.util.List;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author ashish2483
 *
 */
public class SSLCummulativeFlagsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

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
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            if (isTrue(apparelModel.getLimited()) || isTrue(apparelModel.getExclusive()) || isTrue(apparelModel.getComingSoon())
                    || isTrue(apparelModel.getIsAvailableForCoD()) || isTrue(apparelModel.getIsAvailable())
                    || isTrue(apparelModel.getPreOrder()) || isTrue(apparelModel.getSameDayDelivery())
                    || isTrue(apparelModel.getIsGiftProduct()) || isTrue(apparelModel.getInternationalShipping())
                    || isTrue(apparelModel.getNewArrival())) {
                fieldValues.addAll(createFieldValue(Boolean.TRUE, indexedProperty));
            }
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) ((ApparelStyleVariantProductModel) model).getBaseProduct();
            if (isTrue(apparelModel.getLimited()) || isTrue(apparelModel.getExclusive()) || isTrue(apparelModel.getComingSoon())
                    || isTrue(apparelModel.getIsAvailableForCoD()) || isTrue(apparelModel.getIsAvailable())
                    || isTrue(apparelModel.getPreOrder()) || isTrue(apparelModel.getSameDayDelivery())
                    || isTrue(apparelModel.getIsGiftProduct()) || isTrue(apparelModel.getInternationalShipping())
                    || isTrue(apparelModel.getNewArrival())) {
                fieldValues.addAll(createFieldValue(Boolean.TRUE, indexedProperty));
            }
        }
        return fieldValues;
    }

    /**
     * @param value
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final Boolean value, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, value));
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

    boolean isTrue(final Boolean flag) {
        if (flag != null && flag.booleanValue()) {
            return true;
        } else {
            return false;
        }
    }

}
