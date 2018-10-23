/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
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
import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author ashish2483
 *
 */
public class SSLLatestUploadedValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private FieldNameProvider fieldNameProvider;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues
     * (de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, Object model)
            throws FieldValueProviderException {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        ApparelProductModel apparelModel = null;

        if (model instanceof ApparelStyleVariantProductModel) {
            model = ((ApparelStyleVariantProductModel) model).getBaseProduct();
        }

        if (model instanceof ApparelProductModel) {
            apparelModel = (ApparelProductModel) model;
            if (apparelModel.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED)) {
                fieldValues.addAll(createFieldValue(
                        apparelModel.getOnlineDate() != null ? apparelModel.getOnlineDate() : apparelModel.getCreationtime(),
                        indexedProperty));
            }
        }
        return fieldValues;
    }

    /**
     * @param creationDate
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final Date creationDate, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, creationDate));
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
