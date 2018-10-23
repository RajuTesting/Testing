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
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.AgeModel;

/**
 * SslImprovedSearchAgeGroupFacetValueProvider. Index age values with the
 * product.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SslImprovedSearchAgeGroupFacetValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable {

	/** The field name provider. */
	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException {
		final Collection fieldValues = new ArrayList();

		if (model instanceof ProductModel) {
			final ProductModel productModel = (ProductModel) model;
			fieldValues.addAll(createFieldValue(productModel, indexedProperty));
			return fieldValues;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Creates the field values.
	 *
	 * @param product the product
	 * @param indexedProperty the indexed property
	 * @return the list of values indexed
	 */
	private Collection<? extends FieldValue> createFieldValue(final ProductModel product,
			final IndexedProperty indexedProperty) {

		final Collection<AgeModel> ageFacets = product.getAgeImprovedSearch();
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		if (!ageFacets.isEmpty()) {
			final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
			for (final String fieldName : fieldNames) {
				for (final AgeModel searchTerms : ageFacets) {
					final String[] searchTermsLists = searchTerms.getSearchTermsLists().split(",");
					for (final String searchTerm : searchTermsLists) {
						fieldValues.add(new FieldValue(fieldName, searchTerm));
					}
				}
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
