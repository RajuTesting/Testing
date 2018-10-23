package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.enums.Gender;
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

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * SslImprovedSearchGenderValueProvider. Index gender value with the product
 * value which is used in improved search.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SslImprovedSearchGenderValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable {

	/** The field name provider. */
	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException {
		final Collection fieldValues = new ArrayList();

		if (model instanceof ApparelProductModel) {
			final ApparelProductModel apparelProductModel = (ApparelProductModel) model;
			fieldValues.addAll(createFieldValue(apparelProductModel, indexedProperty));
			return fieldValues;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Creates the field values for gender.
	 *
	 * @param product the product
	 * @param indexedProperty the indexed property
	 * @return the list of values indexed
	 */

	private Collection<? extends FieldValue> createFieldValue(final ApparelProductModel product,
			final IndexedProperty indexedProperty) {

		final List<Gender> genderList = product.getGenders();
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		if (null != genderList) {
			final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
			for (final String fieldName : fieldNames) {
				fieldValues.add(new FieldValue(fieldName, genderList));
			}
		}
		return fieldValues;
	}

	/**
	 * Sets the fieldNameProvider.
	 *
	 * @param fieldNameProvider Field Name Provider.
	 */
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
		this.fieldNameProvider = fieldNameProvider;
	}

	/**
	 * Gets FieldNameProvider.
	 *
	 * @return the FieldNameProvider
	 */
	protected FieldNameProvider getFieldNameProvider() {
		return this.fieldNameProvider;
	}
}
