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

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.CategoryNameValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.Collections;
import java.util.List;



public class SSLCategoryNameValueProvider extends CategoryNameValueProvider
{
    /* (non-Javadoc)
     * @see de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.CategoryCodeValueProvider#createFieldValue(de.hybris.platform.category.model.CategoryModel, de.hybris.platform.core.model.c2l.LanguageModel, de.hybris.platform.solrfacetsearch.config.IndexedProperty)
     */
    @Override
    protected List<FieldValue> createFieldValue(final CategoryModel category, final LanguageModel language,
            final IndexedProperty indexedProperty)
    {
        if (!"categories".equalsIgnoreCase(category.getCode()))
        {
            return super.createFieldValue(category, language, indexedProperty);
        }
        else
        {
            return Collections.emptyList();
        }
    }

}
