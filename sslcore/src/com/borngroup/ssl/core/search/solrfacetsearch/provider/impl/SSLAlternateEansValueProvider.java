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
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.EanModel;

/*
 * This Value provider is used to index all EAN's attached to product size variants
 */
public class SSLAlternateEansValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable {

	private FieldNameProvider fieldNameProvider;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#
	 * getFieldValues(de.hybris.platform.solrfacetsearch.config.IndexConfig,
	 * de.hybris.platform.solrfacetsearch.config.IndexedProperty,
	 * java.lang.Object)
	 */
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig config, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException {
		final List<FieldValue> fieldValues = new ArrayList();
		populateEanList(config, indexedProperty, model, fieldValues);
		return fieldValues;
	}

	/**
	 * @param config
	 * @param indexedProperty
	 * @param model
	 * @param fieldValues
	 * @throws FieldValueProviderException
	 */
	private void populateEanList(final IndexConfig config, final IndexedProperty indexedProperty, final Object model,
			final List<FieldValue> fieldValues) throws FieldValueProviderException {
		if (model instanceof ApparelProductModel
				&& CollectionUtils.isNotEmpty(((ApparelProductModel) model).getVariants())) {
			for (final VariantProductModel variantProduct : ((ApparelProductModel) model).getVariants()) {
				if (variantProduct instanceof ApparelStyleVariantProductModel) {
					populateEanList(config, indexedProperty, variantProduct, fieldValues);
				}
			}
		} else if (model instanceof ApparelStyleVariantProductModel
				&& CollectionUtils.isNotEmpty(((ApparelStyleVariantProductModel) model).getVariants())) {
			for (final VariantProductModel variantProduct : ((ApparelStyleVariantProductModel) model).getVariants()) {
				if (variantProduct instanceof ApparelSizeVariantProductModel) {
					populateEanList(config, indexedProperty, variantProduct, fieldValues);
				}
			}
		} else if (model instanceof ApparelSizeVariantProductModel
				&& CollectionUtils.isNotEmpty(((ApparelSizeVariantProductModel) model).getAlternateEans())) {
			final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
			for (final String fieldName : fieldNames) {
				for (final EanModel eanModel : ((ApparelSizeVariantProductModel) model).getAlternateEans()) {
					fieldValues.add(new FieldValue(fieldName, eanModel.getEanNumber()));
				}
			}
		}
	}

	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider() {
		return fieldNameProvider;
	}

	/**
	 * @param fieldNameProvider
	 *            the fieldNameProvider to set
	 */
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
		this.fieldNameProvider = fieldNameProvider;
	}

}
