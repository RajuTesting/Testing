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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

public class SSLHeroProductValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;
    private static final Logger LOG = Logger.getLogger(SSLHeroProductValueProvider.class.getName());

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {

        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, getPropertyValue(model)));
        }

        return fieldValues;
    }

    /**
     * @param model
     *        which is a product to be indexed
     * @return value of heroProduct attribute present in productModel
     */
    private Object getPropertyValue(final Object model) {
        if (model instanceof ProductModel) {
            ProductModel product = (ProductModel) model;
            if (product instanceof ApparelStyleVariantProductModel) {
                product = ((ApparelStyleVariantProductModel) product).getBaseProduct();
            }
            final Boolean heroProduct = product.getHeroProduct();
            if (null != heroProduct) {
                return heroProduct;
            }
        } else {
            LOG.info("Object passed for indexing is not a product : " + model);
        }
        // The default value of the attribute has been set to be false.
        return Boolean.FALSE;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }
}
