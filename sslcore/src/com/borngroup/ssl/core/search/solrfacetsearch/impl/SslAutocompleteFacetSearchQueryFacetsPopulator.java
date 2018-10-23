/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.impl;

import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetField;
import de.hybris.platform.solrfacetsearch.search.FacetValueField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.impl.populators.FacetSearchQueryFacetsPopulator;
import de.hybris.platform.solrfacetsearch.search.impl.populators.FacetSearchQueryFilterQueriesPopulator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author ashishsabal
 *
 */
public class SslAutocompleteFacetSearchQueryFacetsPopulator extends FacetSearchQueryFacetsPopulator {
	private static final Logger LOG = Logger.getLogger(FacetSearchQueryFilterQueriesPopulator.class);

	@Override
	protected Map<String, FacetInfo> prepareFacets(final SearchQuery searchQuery) {
		final Map results = new HashMap();

		String categoryCode = "";
		for (final FacetValueField queryField : searchQuery.getFacetValues()) {
			if (queryField.getField().equalsIgnoreCase("allCategories")) {
				if (queryField.getValues() != null && queryField.getValues().size() > 0) {
					categoryCode = (String) queryField.getValues().toArray()[0];
				}
			}
		}

		final IndexedType indexedType = searchQuery.getIndexedType();

		for (final FacetField facet : searchQuery.getFacets()) {
			if (facet.getField().equalsIgnoreCase(SslCoreConstants.AUTOCOMPLETE_CATEGORY_L3_INDEDXED_PROPERTY)) {
				final FacetInfo facetInfo = new FacetInfo(facet.getField(), facet.getFacetType());
				final IndexedProperty indexedProperty = indexedType.getIndexedProperties().get(facet.getField());

				// LOG.info("My message: " + facet.getField()
				// + "\nINFO: IndexedProperty: ");
				if (indexedProperty != null && isClassificationFacetValid(indexedProperty, categoryCode)) {
					results.put(facet.getField(), facetInfo);
				}
			}

			if (facet.getField().equalsIgnoreCase(SslCoreConstants.AUTOSUGGEST_GENDER_IN_CATEGORIES)) {
				final FacetInfo facetInfo = new FacetInfo(facet.getField(), facet.getFacetType());
				final IndexedProperty indexedProperty = indexedType.getIndexedProperties().get(facet.getField());

				// LOG.info("My message: " + facet.getField()
				// + "\nINFO: IndexedProperty: ");
				if (indexedProperty != null && isClassificationFacetValid(indexedProperty, categoryCode)) {
					results.put(facet.getField(), facetInfo);
				}
			}

			if (facet.getField().equalsIgnoreCase(SslCoreConstants.AUTOSUGGEST_GENDER_IN_BRANDS)) {
				final FacetInfo facetInfo = new FacetInfo(facet.getField(), facet.getFacetType());
				final IndexedProperty indexedProperty = indexedType.getIndexedProperties().get(facet.getField());

				// LOG.info("My message: " + facet.getField()
				// + "\nINFO: IndexedProperty: ");
				if (indexedProperty != null && isClassificationFacetValid(indexedProperty, categoryCode)) {
					results.put(facet.getField(), facetInfo);
				}
			}
		}

		for (final FacetValueField facetValue : searchQuery.getFacetValues()) {
			final FacetInfo facetInfo = (FacetInfo) results.get(facetValue.getField());
			if (facetInfo != null) {
				if (!(CollectionUtils.isNotEmpty(facetValue.getValues()))) {
					continue;
				}
				facetInfo.getValues().addAll(facetValue.getValues());
			} else {
				LOG.warn("Search query contains value for facet but facet was not added [field: "
						+ facetValue.getField() + "]");
			}
		}

		return results;
	}

	private boolean isClassificationFacetValid(final IndexedProperty indexedProperty, final String categoryCode) {
		final ClassAttributeAssignmentModel classAttrAssignModel = indexedProperty.getClassAttributeAssignment();
		if (classAttrAssignModel != null) {

			if (classAttrAssignModel.getClassificationClass() != null) {
				for (final CategoryModel category : classAttrAssignModel.getClassificationClass()
						.getAllSubcategories()) {
					if (categoryCode.equals(category.getCode())) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
}
