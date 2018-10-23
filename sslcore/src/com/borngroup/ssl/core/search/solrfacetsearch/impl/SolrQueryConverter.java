package com.borngroup.ssl.core.search.solrfacetsearch.impl;

import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.solrfacetsearch.config.FacetType;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.FacetValueField;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultSolrQueryConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author kanagaraj.k
 *
 */
public class SolrQueryConverter extends DefaultSolrQueryConverter {
	private static final Logger LOG = Logger.getLogger(DefaultSolrQueryConverter.class);

	@Override
	public SolrQuery convertSolrQuery(final SearchQuery searchQuery) throws FacetSearchException {
		final SolrQuery solrQuery = super.convertSolrQuery(searchQuery);
		String query = solrQuery.getQuery();
		// solrQuery.addFilterQuery(searchQuery.getUserQuery());
		if (StringUtils.isNotEmpty(searchQuery.getUserQuery())
				&& searchQuery.getUserQuery().contains("priceValue_inr_double:")) {
			query = "(" + query + ") AND NOT (priceValue_inr_double:0) AND" + searchQuery.getUserQuery();
		} else {
			query = "(" + query + ") AND  NOT (priceValue_inr_double:0)";
		}
		solrQuery.setQuery(query);
		return solrQuery;
	}

	@Override
	protected Map<String, IndexedFacetInfo> getFacetInfo(final SearchQuery searchQuery) throws FacetSearchException {
		final Map<String, IndexedFacetInfo> results = new HashMap();
		int index = 0;
		String categoryCode = "";
		for (final FacetValueField queryField : searchQuery.getFacetValues()) {
			if (queryField.getField().equalsIgnoreCase("allCategories")) {
				if (queryField.getValues() != null && queryField.getValues().size() > 0) {
					categoryCode = (String) queryField.getValues().toArray()[0];
				}
			}

		}
		final IndexedType indexedType = searchQuery.getIndexedType();
		// searchQuery.get
		final Set<String> facets = indexedType.getTypeFacets();
		for (final String facetName : facets) {
			final IndexedProperty indexedProperty = indexedType.getIndexedProperties().get(facetName);

			if (indexedProperty != null && isClassificationFacetValid(indexedProperty, categoryCode)) {
				final IndexedFacetInfo facetInfo = new IndexedFacetInfo();
				final FacetType facetType = indexedProperty.getFacetType();
				if (FacetType.MULTISELECTAND.equals(facetType)) {
					facetInfo.setMultiSelect(true);
					facetInfo.setMultiSelectAnd(true);
				} else if (FacetType.MULTISELECTOR.equals(facetType)) {
					facetInfo.setMultiSelect(true);
					facetInfo.setMultiSelectOr(true);
				}
				facetInfo.setTranslatedFieldName(
						getFieldNameTranslator().translate(searchQuery, facetName, FieldNameProvider.FieldType.INDEX));
				// facetInfo.setTranslatedFieldName(translateFieldName(facetName,
				// FieldNameProvider.FieldType.INDEX, searchQuery));
				facetInfo.setKey("fk" + index);
				results.put(facetInfo.getTranslatedFieldName(), facetInfo);
			}
			index++;
		}
		return results;
	}

	/**
	 * @param indexedType
	 * @return
	 */
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
