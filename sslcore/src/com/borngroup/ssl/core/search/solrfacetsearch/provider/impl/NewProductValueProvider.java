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

import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.util.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

public class NewProductValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;
    private static final String NEWPROUDCT_DAYS = Config.getParameter("newproduct.valid.days");

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        final Collection fieldValues = new ArrayList();

        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            fieldValues.addAll(createFieldValue(apparelModel, indexedProperty));
            return fieldValues;
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) ((ApparelStyleVariantProductModel) model).getBaseProduct();
            fieldValues.addAll(createFieldValue(apparelModel, indexedProperty));
            return fieldValues;
        } else {
            return Collections.emptyList();
        }

    }

    private Collection<? extends FieldValue> createFieldValue(final ApparelProductModel product, final IndexedProperty indexedProperty) {

        Boolean newProduct = Boolean.FALSE;
        if (product.getApprovalDate() != null) {
            final Calendar onlineDate = Calendar.getInstance();
            onlineDate.setTime(product.getApprovalDate());
            final Calendar today = Calendar.getInstance();
            final LocalDate d1 = new LocalDate(onlineDate.getTimeInMillis());
            final LocalDate d2 = new LocalDate(today.getTimeInMillis());
            final int days = Days.daysBetween(d1, d2).getDays();
            if (days < Integer.parseInt(NEWPROUDCT_DAYS)) {
                newProduct = Boolean.TRUE;
            }
        }

        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, newProduct));
        }

        return fieldValues;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }
}
