package com.borngroup.ssl.core.util;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.impl.DefaultSolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.model.config.SolrEndpointUrlModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrURIRedirectModel;
import de.hybris.platform.solrfacetsearch.search.KeywordRedirectValue;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SolrKeywordRedirectService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.borngroup.ssl.core.autosuggest.SuggestionComparator;
import com.borngroup.ssl.facades.product.data.SuggestionResponse;
import com.borngroup.ssl.facades.product.data.SuggestionType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 *
 * @author osheen.gulati @nagarro.com
 **/
@Service("AutosuggestUtil")
public class AutosuggestUtil {

	//
	private static final String PRODUCT_BRAND = "brandsImprovedSearch_text_mv";
	private static final String PRODUCT_CATEGORY_CODE = "category_string_mv";
	private static final String PRODUCT_COLOR_CODE = "swatchColors_string_mv";
	private static final String PRODUCT_SIZE_CODE = "sizeSearch_text_mv";
	private static final String PRODUCT_L3_CATEGORY_CODE = "l3categorySearch_text_mv";
	private static final String PRODUCT_L3_CATEGORY = "l3categoryImprovedsearch_string_mv";
	private static final String PRODUCT_GENDER = "gender_string_mv";

	private static final String AUTOSUGGESTION_CATEGORY = "genderAutoSuggestionImprovedSearch_string_mv";
	private static final String AUTOSUGGESTION_BRANDS = "genderInBrandsAutoSuggestionImprovedSearch_string_mv";
	private static final String AUTOSUGGESTION_COLOR = "colorInAutoSuggestion_text_mv";
	private static final String AUTOSUGGESTION_BRANDS_URL = "genderInBrandsAutoSuggestionImprovedSearch";
	private static final String AUTOSUGGESTION_COLOR_URL = "swatchColors";
	private static final String AUTOSUGGESTION_BRAND_REFINE_URL = "brandAutosuggestRefine_string";

	// Maximum number of total suggestions
	private static final int MAX_TOTAL_SUGGESTIONS = 8;
	private static final int MAX_CATEGORY_SUGGESTIONS = 8;
	private static final int MAX_BRAND_SUGGESTIONS = 8;

	@Resource(name = "solrKeywordRedirectService")
	private SolrKeywordRedirectService solrKeywordRedirectService;

	private static final Logger LOG = Logger.getLogger(AutosuggestUtil.class);

	@Resource(name = "defaultSolrFacetSearchConfigSelectionStrategy")
	DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy;

	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;

	@Resource(name = "facetSearchConfigService")
	private FacetSearchConfigService facetSearchConfigService;

	private URL getUrl(String searchTerm, final SuggestionType suggestionType, final CMSSiteModel cmsSiteModel)
			throws MalformedURLException {

		String urlqueryprefix = "";
		final List<SolrEndpointUrlModel> endPointUrlList = cmsSiteModel.getSolrFacetSearchConfiguration()
				.getSolrServerConfig().getSolrEndpointUrls();
		if (CollectionUtils.isNotEmpty(endPointUrlList)) {
			for (final SolrEndpointUrlModel solrEndpointUrlModel : endPointUrlList) {
				if (solrEndpointUrlModel.isMaster()) {
					urlqueryprefix = solrEndpointUrlModel.getUrl();
					break;
				}
			}
		}
		LOG.info("AutosuggestUtil 2" + urlqueryprefix + " _ " + cmsSiteModel);
		String url = null;
		if (searchTerm.contains("&")) {
			searchTerm = searchTerm.replace("&", "%26");
		}
		if (searchTerm.contains(",")) {
			searchTerm = searchTerm.replace(",", "%2C");
		}
		if (searchTerm.contains(" ")) {
			searchTerm = searchTerm.replace(",", "%20");
		}
		searchTerm = searchTerm.toLowerCase();
		if (SuggestionType.CATEGORY.equals(suggestionType)) {
			if (!searchTerm.contains(" ")) {
				url = urlqueryprefix + "master_ssl_Product/select?q=" + PRODUCT_L3_CATEGORY + "%3A\"" + searchTerm
						+ "\"^1000+OR+" + PRODUCT_L3_CATEGORY + "%3A" + searchTerm + "?*^750+OR+" + PRODUCT_L3_CATEGORY
						+ "%3A*" + searchTerm + "*^20+OR+" + PRODUCT_L3_CATEGORY_CODE + ":" + searchTerm + "&fl="
						+ AUTOSUGGESTION_CATEGORY + "%2C" + PRODUCT_CATEGORY_CODE
						+ "%2Cscore&rows=1000&wt=json&indent=true";
			} else {
				url = urlqueryprefix + "master_ssl_Product/select?q=" + PRODUCT_L3_CATEGORY + "%3A\"" + searchTerm
						+ "\"^1000+OR+" + PRODUCT_L3_CATEGORY + "%3A" + searchTerm + "?*^750+OR+" + PRODUCT_L3_CATEGORY
						+ "%3A*" + searchTerm + "*^20&fl=" + AUTOSUGGESTION_CATEGORY + "%2C" + PRODUCT_CATEGORY_CODE
						+ "%2Cscore&rows=1000&wt=json&indent=true";
			}
		} else if (SuggestionType.MATCHINGKEYWORDS.equals(suggestionType)) {
			url = urlqueryprefix + "master_ssl_Product/select?q=" + PRODUCT_COLOR_CODE + "%3A*" + searchTerm + "*"
					+ "+OR+" + PRODUCT_SIZE_CODE + "%3A*" + searchTerm + "*" + "+OR+" + PRODUCT_BRAND + "%3A\""
					+ searchTerm + "\"^1000+OR+" + PRODUCT_BRAND + "%3A" + searchTerm + "?*^750+OR+" + PRODUCT_BRAND
					+ "%3A*" + searchTerm + "*^20+OR+" + PRODUCT_GENDER + "%3A*" + searchTerm + "*" + "&fl="
					+ AUTOSUGGESTION_BRANDS + "%2C" + AUTOSUGGESTION_COLOR + "%2C" + AUTOSUGGESTION_BRAND_REFINE_URL
					+ "%2Cscore&rows=1000&wt=json&indent=true";
		}
		if (null != url) {
			return new URL(url.replace(" ", "%20"));
		} else {
			return null;
		}
	}

	private LinkedHashSet<SuggestionResponse> getSuggestionsFromSolr(final URL url, final SuggestionType suggestionType,
			final String searchTerm) throws IOException

	{
		int totalResultFound;

		HttpURLConnection conn;
		LinkedHashSet<SuggestionResponse> suggestionList = null;
        if (null == url) {
            return (LinkedHashSet<SuggestionResponse>) CollectionUtils.EMPTY_COLLECTION;
        }
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		final ObjectMapper mapper = new ObjectMapper();
		final JsonNode root = mapper.readTree(new InputStreamReader(conn.getInputStream()));
		final JsonNode docs = root.get("response").get("docs");

		totalResultFound = Integer.parseInt(root.get("response").path("numFound").asText());
		LOG.debug("AutosuggestUtil 3" + url + " _ " + totalResultFound);

		if (totalResultFound > 0) {
			suggestionList = new LinkedHashSet();
			for (final JsonNode objNode : docs) {
				if (suggestionType == SuggestionType.CATEGORY) {
					populateCategorySuggestion(suggestionType, searchTerm, suggestionList, objNode);

				} else if (suggestionType == SuggestionType.MATCHINGKEYWORDS) {
					populateBrandSuggestion(suggestionType, searchTerm, suggestionList, objNode);
				}

			}

		}

		conn.disconnect();
		return suggestionList;
	}

	/**
	 *
	 * @param suggestionType
	 * @param searchTerm
	 * @param suggestionList
	 * @param objNode
	 */
	private void populateCategorySuggestion(final SuggestionType suggestionType, final String searchTerm,
			final LinkedHashSet<SuggestionResponse> suggestionList, final JsonNode objNode) {

		final JsonNode catObj = objNode.get(AUTOSUGGESTION_CATEGORY);
		if (catObj != null) {
			for (int position = 0; position < catObj.size(); position++) {
				if (catObj.get(position) != null) {
					final SuggestionResponse suggestion = new SuggestionResponse();
					suggestion.setType(suggestionType);
					suggestion.setChildProductCount(1);
					suggestion.setScore(objNode.get("score").intValue());
					final String[] terms = catObj.get(position).textValue().split("_");
					suggestion.setCategorySuggestionName(WordUtils.capitalize(terms[0].toLowerCase()) + " in "
							+ WordUtils.capitalize(terms[1].toLowerCase()) + " for "
							+ WordUtils.capitalize(terms[2].toLowerCase()));
					LOG.info("AutosuggestUtil 4" + WordUtils.capitalize(terms[0].toLowerCase()) + " in "
							+ WordUtils.capitalize(terms[1].toLowerCase()) + " for "
							+ WordUtils.capitalize(terms[2].toLowerCase()));
					suggestion.setCategorySuggestionCode(objNode.get(PRODUCT_CATEGORY_CODE).get(position).textValue());
					if (terms[0].toLowerCase().contains(" ")) {
						terms[0].toLowerCase().replace(" ", "+");
					}
					String category = terms[0].toLowerCase();
					if (category.contains("&")) {
						category = category.replace("&", "%26");
					}
					if (category.contains(",")) {
						category = category.replace(",", "%2C");
					}
					String genderCategory = catObj.get(position).textValue();
					if (genderCategory.contains("&")) {
						genderCategory = genderCategory.replace("&", "%26");
					}
					if (genderCategory.contains(",")) {
						genderCategory = genderCategory.replace(",", "%2C");
					}
					final String url = "/search?q=" + category + "%3Arelevance%3A"
							+ "genderAutoSuggestionImprovedSearch%3A" + genderCategory;
					suggestion.setCategorySuggestionURL(url);
					suggestionList.add(suggestion);
				}
			}
		}
	}

	/**
	 *
	 * @param suggestionType
	 * @param searchTerm
	 * @param suggestionList
	 * @param objNode
	 */
	private void populateBrandSuggestion(final SuggestionType suggestionType, final String searchTerm,
			final LinkedHashSet<SuggestionResponse> suggestionList, final JsonNode objNode) {

		JsonNode catObj1;
		JsonNode catObj2;
		JsonNode catObj3;
		String brandRefined = null;
		final List<String> matchingKeywordType = new ArrayList<String>();

		final JsonNode brandFormatted = objNode.get(AUTOSUGGESTION_BRAND_REFINE_URL);
		if (null != brandFormatted) {
			brandRefined = brandFormatted.toString().replace("[\"", "").replace("\"]", "").replace("\"", "");
		}
		catObj1 = objNode.get(AUTOSUGGESTION_BRANDS);
		if (catObj1 != null && !catObj1.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
			catObj1 = null;
		} else {
			matchingKeywordType.add("brands");
		}

		catObj2 = objNode.get(AUTOSUGGESTION_COLOR);
		if (catObj2 != null && !catObj2.toString().toLowerCase().contains(searchTerm)) {
			catObj2 = null;
		} else {
			matchingKeywordType.add("color");
		}

		catObj3 = objNode.get(AUTOSUGGESTION_CATEGORY);
		if (catObj3 != null) {
			final String[] terms = catObj3.toString().replace("[\"", "").replace("\"]", "").replace("\"", "")
					.toLowerCase().split(",");
			final StringBuffer stringBuffer = new StringBuffer();
			for (final String term : terms) {
				stringBuffer.append(term + " ");
			}
			if (!stringBuffer.toString().contains(searchTerm.toLowerCase())) {
				catObj3 = null;
			} else {
				matchingKeywordType.add("gender");
			}
		}

		if (catObj1 != null && catObj1.toString().replace("[\"", "").replace("\"]", "").toLowerCase()
				.contains(searchTerm.toLowerCase()) && matchingKeywordType.contains("brands")) {

			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			suggestion.setScore(objNode.get("score").intValue());
			final String[] terms = catObj1.toString().split("_");
			String brandWithSpecialCharacter = terms[0].replace("[\"", "");
			if (brandWithSpecialCharacter.contains("&")) {
				brandWithSpecialCharacter = brandWithSpecialCharacter.replace("&", "%26");
			}
			if (brandWithSpecialCharacter.contains(",")) {
				brandWithSpecialCharacter = brandWithSpecialCharacter.replace(",", "%2C");
			}
			suggestion.setBrandSuggestionName(WordUtils.capitalize(brandWithSpecialCharacter.toLowerCase()) + " in "
					+ WordUtils.capitalize(terms[1].replace("\"]", "").toLowerCase()));
			String brandURLWithSpecialCharacter = catObj1.toString().replace("[\"", "").replace("\"]", "");
			if (brandURLWithSpecialCharacter.contains("&")) {
				brandURLWithSpecialCharacter = brandWithSpecialCharacter.replace("&", "%26");
			}
			if (brandURLWithSpecialCharacter.contains(",")) {
				brandURLWithSpecialCharacter = brandWithSpecialCharacter.replace(",", "%2C");
			}
			final String newURL = "/search?q=" + brandWithSpecialCharacter + "%3Arelevance%3A"
					+ AUTOSUGGESTION_BRANDS_URL + "%3A" + brandURLWithSpecialCharacter;
			suggestion.setBrandSuggestionURL(newURL);
			final SuggestionResponse suggestionOnlyBrand = new SuggestionResponse();
			suggestionOnlyBrand.setType(suggestionType);
			String brand = WordUtils.capitalize(terms[0].replace("[\"", "").toLowerCase());
			if (brand.contains("&")) {
				brand = brand.replace("&", "%26");
			}
			if (brand.contains(",")) {
				brand = brand.replace(",", "%2C");
			}

			String url = null;
			try {
				final SolrFacetSearchConfigModel solrFacetSearchConfig = defaultSolrFacetSearchConfigSelectionStrategy
						.getCurrentSolrFacetSearchConfig();
				if (null != solrFacetSearchConfig && null != solrFacetSearchConfig.getName()) {
					final FacetSearchConfig facetSearchConfig = facetSearchConfigService
							.getConfiguration(solrFacetSearchConfig.getName());
					final SearchQuery searchQuery = new SearchQuery(facetSearchConfig, null);
					searchQuery.setUserQuery(brand);
					final List<KeywordRedirectValue> redirects = solrKeywordRedirectService
							.getKeywordRedirect(searchQuery);
					if (CollectionUtils.isNotEmpty(redirects)) {
						final KeywordRedirectValue redirect = redirects.iterator().next();
						if (redirect.getRedirect() instanceof SolrURIRedirectModel) {
							url = ((SolrURIRedirectModel) (redirect.getRedirect())).getUrl();
						}
					}
				}

			} catch (final NoValidSolrConfigException | FacetConfigServiceException e) {
				LOG.error(e.toString());
			}

			if (null == url) {
				// create below like url

				url = "/search?q=" + brand + "%3Arelevance%3AinStockFlag%3Atrue%3AbrandAutosuggestRefine%3A"
						+ brandRefined;
			}

			suggestionOnlyBrand.setBrandSuggestionName(brand);
			suggestionOnlyBrand.setBrandSuggestionURL(url);
			suggestionList.add(suggestionOnlyBrand);
			suggestionList.add(suggestion);
		}

		if (catObj2 != null && catObj2.toString().replace("[\"", "").replace("\"]", "").toLowerCase()
				.contains(searchTerm.toLowerCase()) && matchingKeywordType.contains("color")) {
			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			final String[] terms = catObj2.toString().replace("[\"", "").replace("\"]", "").replace("\"", "")
					.split(",");
			String[] colorSuggestion = null;
			String url = "";
			for (final String colorTerm : terms) {
				if (colorTerm.contains(searchTerm)) {
					colorSuggestion = colorTerm.split("_");
					suggestion.setBrandSuggestionName(WordUtils.capitalize(colorSuggestion[1].toLowerCase()) + " "
							+ WordUtils.capitalize(colorSuggestion[0].toLowerCase()));
					url = "/search?q=" + colorSuggestion[0].toLowerCase() + "%3Arelevance%3AinStockFlag%3Atrue%3A"
							+ AUTOSUGGESTION_COLOR_URL + "%3A" + colorSuggestion[1].toLowerCase() + "&text="
							+ colorSuggestion[0].toLowerCase() + "&startRange=&endRange=&showType=#";
					suggestion.setBrandSuggestionURL(url);
					break;
				}
			}
			suggestionList.add(suggestion);
		}

		if (catObj3 != null && catObj3.toString().replace("[\"", "").replace("\"]", "").toLowerCase()
				.contains(searchTerm.toLowerCase()) && matchingKeywordType.contains("gender")) {
			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			final String[] terms = catObj3.toString().replace("[\"", "").replace("\"]", "").replace("\"", "")
					.toLowerCase().split(",");
			String[] genderSuggestion = null;
			String url = "";
			for (final String genderTerm : terms) {
				if (genderTerm.contains(searchTerm)) {
					genderSuggestion = genderTerm.split("_");
					suggestion.setCategorySuggestionName(
							WordUtils.capitalize(WordUtils.capitalize(genderSuggestion[2].toLowerCase()) + " "
									+ genderSuggestion[0].toLowerCase()));
					url = "/search?q=" + genderSuggestion[0].toLowerCase() + "%3Arelevance%3A" + "l1category%3A"
							+ WordUtils.capitalize(genderSuggestion[2]);
					suggestion.setBrandSuggestionURL(url);
					suggestionList.add(suggestion);
				}
			}

		}
	}

	/**
	 *
	 * @param searchTerm
	 * @param cmsSiteModel
	 * @param model
	 * @return
	 * @throws IOException
	 */
	public List<SuggestionResponse> getInSuggestions(final String searchTerm, final CMSSiteModel cmsSiteModel,
			final Model model) throws IOException {

		LOG.info("AutosuggestUtil 1" + searchTerm);
		final SuggestionComparator comparator = new SuggestionComparator();
		final LinkedHashSet<SuggestionResponse> suggestionList = getSuggestionsFromSolr(
				getUrl(searchTerm, SuggestionType.CATEGORY, cmsSiteModel), SuggestionType.CATEGORY, searchTerm);

		List<SuggestionResponse> collectiveSortedList = new ArrayList();

		if (suggestionList != null && !suggestionList.isEmpty() && suggestionList.size() > 0) {
			final HashSet<SuggestionResponse> categorySuggestionSet = new HashSet<>();
			for (final SuggestionResponse suggestion : suggestionList) {
				addValue(categorySuggestionSet, suggestion, SuggestionType.CATEGORY, model);
			}

			final List<SuggestionResponse> categorySortedList = new ArrayList(categorySuggestionSet);
			Collections.sort(categorySortedList, comparator);

			if (categorySortedList.size() > MAX_CATEGORY_SUGGESTIONS) {
				final List<SuggestionResponse> categoryList = new ArrayList<>();
				for (int i = 0; i < MAX_CATEGORY_SUGGESTIONS; i++) {
					categoryList.add(categorySortedList.get(i));
				}
				return categoryList;
			} else {

				collectiveSortedList.addAll(categorySortedList);

				getMatchingKeywordsSuggestionFromSolr(searchTerm, comparator, collectiveSortedList, cmsSiteModel,
						model);

				if (collectiveSortedList.size() > MAX_TOTAL_SUGGESTIONS) {
					collectiveSortedList = collectiveSortedList.subList(0, MAX_TOTAL_SUGGESTIONS);
				}

				return collectiveSortedList;
			}
		} else {
			getMatchingKeywordsSuggestionFromSolr(searchTerm, comparator, collectiveSortedList, cmsSiteModel, model);
			return collectiveSortedList;
		}
	}

	/**
	 *
	 * @param searchTerm
	 * @param comparator
	 * @param collectiveSortedList
	 * @param cmsSiteModel
	 * @param model
	 * @throws IOException
	 */
	private void getMatchingKeywordsSuggestionFromSolr(final String searchTerm, final SuggestionComparator comparator,
			final List<SuggestionResponse> collectiveSortedList, final CMSSiteModel cmsSiteModel, final Model model)
			throws IOException {
;		final LinkedHashSet<SuggestionResponse> brandSuggestionList = getSuggestionsFromSolr(
				getUrl(searchTerm, SuggestionType.MATCHINGKEYWORDS, cmsSiteModel), SuggestionType.MATCHINGKEYWORDS,
				searchTerm);
		if (brandSuggestionList != null && !brandSuggestionList.isEmpty()) {
			final HashSet<SuggestionResponse> brandSuggestionSet = new HashSet<>();
			for (final SuggestionResponse suggestion : brandSuggestionList) {
				addValue(brandSuggestionSet, suggestion, SuggestionType.MATCHINGKEYWORDS, model);
			}

			final List<SuggestionResponse> brandSortedList = new ArrayList(brandSuggestionSet);
			Collections.sort(brandSortedList, comparator);

			if (brandSortedList.size() > MAX_BRAND_SUGGESTIONS) {
				for (int i = 0; i < MAX_BRAND_SUGGESTIONS; i++) {
					collectiveSortedList.add(brandSortedList.get(i));
				}
			} else {
				collectiveSortedList.addAll(brandSortedList);
			}
		}
	}

	/**
	 *
	 * @param suggestionSet
	 * @param suggestion
	 * @param suggestionType
	 * @param model
	 */
	private void addValue(final HashSet<SuggestionResponse> suggestionSet, final SuggestionResponse suggestion,
			final SuggestionType suggestionType, final Model model) {
		final Iterator<SuggestionResponse> iterator = suggestionSet.iterator();
		boolean isIdentical = false;
		while (iterator.hasNext()) {
			final SuggestionResponse existingSuggestion = iterator.next();
			if (SuggestionType.CATEGORY.equals(suggestionType)) {
				if (existingSuggestion.getCategorySuggestionName()
						.equalsIgnoreCase(suggestion.getCategorySuggestionName())) {
					isIdentical = true;
					existingSuggestion.setChildProductCount(existingSuggestion.getChildProductCount() + 1);
					suggestionSet.add(existingSuggestion);
				}
			} else if (SuggestionType.MATCHINGKEYWORDS.equals(suggestionType)) {
				if (SuggestionType.MATCHINGKEYWORDS.equals(existingSuggestion.getType())
						&& existingSuggestion.getBrandSuggestionName() != null && existingSuggestion
								.getBrandSuggestionName().equalsIgnoreCase(suggestion.getBrandSuggestionName())) {
					isIdentical = true;
					existingSuggestion.setChildProductCount(existingSuggestion.getChildProductCount() + 1);
					suggestionSet.add(existingSuggestion);
				} else if (SuggestionType.MATCHINGKEYWORDS.equals(existingSuggestion.getType())
						&& existingSuggestion.getBrandSuggestionName() == null && existingSuggestion
								.getCategorySuggestionName().equalsIgnoreCase(suggestion.getCategorySuggestionName())) {
					isIdentical = true;
					existingSuggestion.setChildProductCount(existingSuggestion.getChildProductCount() + 1);
					suggestionSet.add(existingSuggestion);
					model.addAttribute("gender", 1);
				}
			}

		}

		if (!isIdentical) {
			suggestionSet.add(suggestion);
		}

	}

	/**
	 *
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
		final List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		final Map<K, V> result = new LinkedHashMap<>();
		for (final Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
