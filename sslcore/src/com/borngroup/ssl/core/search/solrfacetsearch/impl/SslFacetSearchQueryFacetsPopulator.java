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
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * @author ashishsabal
 *
 */
public class SslFacetSearchQueryFacetsPopulator extends FacetSearchQueryFacetsPopulator {
	private static final Logger LOG = Logger.getLogger(FacetSearchQueryFilterQueriesPopulator.class);

	@Override
	protected Map<String, FacetInfo> prepareFacets(final SearchQuery searchQuery) {
		final Map results = new HashMap();

		String categoryCode = "";
		String l3CategoryCode = "";
		for (final FacetValueField queryField : searchQuery.getFacetValues()) {
			if (queryField.getField().equalsIgnoreCase("allCategories")) {
				if (queryField.getValues() != null && queryField.getValues().size() > 0) {
					categoryCode = (String) queryField.getValues().toArray()[0];
				}
			}
		}

		for (final FacetValueField queryField : searchQuery.getFacetValues()) {
			if (queryField.getField().equalsIgnoreCase("l3category")) {
				if (queryField.getValues() != null && queryField.getValues().size() > 0) {
					l3CategoryCode = (String) queryField.getValues().toArray()[0];
				}
			}
		}

		final IndexedType indexedType = searchQuery.getIndexedType();

		for (final FacetField facet : searchQuery.getFacets()) {
			final FacetInfo facetInfo = new FacetInfo(facet.getField(), facet.getFacetType());
			final IndexedProperty indexedProperty = indexedType.getIndexedProperties().get(facet.getField());

			// LOG.info("My message: " + facet.getField()
			// + "\nINFO: IndexedProperty: ");
			if (indexedProperty != null
					&& isClassificationFacetValid(indexedProperty, categoryCode, l3CategoryCode, searchQuery)) {
				results.put(facet.getField(), facetInfo);
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

	private boolean isClassificationFacetValid(final IndexedProperty indexedProperty, final String categoryCode,
			final String subCategoryCode, final SearchQuery searchQuery) {
		final ClassAttributeAssignmentModel classAttrAssignModel = indexedProperty.getClassAttributeAssignment();
		if (classAttrAssignModel != null) {
			final List<FacetValueField> list = searchQuery.getFacetValues();
			if (classAttrAssignModel.getClassificationClass() != null) {
				for (final CategoryModel category : classAttrAssignModel.getClassificationClass()
						.getAllSubcategories()) {
					if (categoryCode.equals(category.getCode())) {
						return true;
					}
					if (!list.isEmpty()) {
						int countselectedL3Categories = 0;
						for (final FacetValueField FacetValueField : list) {
							if (FacetValueField.getField().equalsIgnoreCase("l3category")) {
								countselectedL3Categories++;
							}
						}
						if (countselectedL3Categories == 1 && category.getName().equalsIgnoreCase(subCategoryCode)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		return true;
	}
}
