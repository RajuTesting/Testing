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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * SSLLatestApprovedValueProvider: This class indexes the ApprovalDate field of ApparelProductModel. If the value is null then a default
 * value as 01/01/2017 is indexed so that all the null field values have the same indexed value.
 *
 * Created by harleen.chadha@nagarro.com
 *
 * @author SSL
 */
public class SSLLatestApprovedValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /** The Constant LOG. */
    protected static final Logger LOG = Logger.getLogger(SSLLatestApprovedValueProvider.class);

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
                Date approvalDate = null;
                if (apparelModel.getApprovalDate() != null) {
                    final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        approvalDate = dateFormatter.parse(dateFormatter.format(apparelModel.getApprovalDate()));
                    } catch (final ParseException e) {
                        LOG.debug("Error occurred during parsing Approval Date. So, indexing default date for it." + e);
                        approvalDate = new Date("01/01/1990");
                    }
                } else {
                    approvalDate = new Date("01/01/1990");
                }
                fieldValues.addAll(createFieldValue(approvalDate, indexedProperty));
            }
        }
        return fieldValues;
    }

    /**
     * Creates the field value.
     *
     * @param creationDate
     *        the creation date
     * @param indexedProperty
     *        the indexed property
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
     * Gets the field name provider.
     *
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * Sets the field name provider.
     *
     * @param fieldNameProvider
     *        the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}