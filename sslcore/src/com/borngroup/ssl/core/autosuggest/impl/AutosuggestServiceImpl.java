package com.borngroup.ssl.core.autosuggest.impl;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.borngroup.ssl.core.autosuggest.AutoSuggestService;
import com.borngroup.ssl.core.autosuggest.AutosuggestConstants;
import com.borngroup.ssl.core.autosuggest.SuggestionComparator;
import com.borngroup.ssl.facades.product.data.SuggestionResponse;
import com.borngroup.ssl.facades.product.data.SuggestionType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Autosuggest Service Impl : Service which displays the auto suggestions.
 * <p/>
 * Created by osheen.gulati@nagarro.com.
 *
 * @author SSL
 */
public class AutosuggestServiceImpl implements AutoSuggestService {
	private static final Logger LOG = Logger.getLogger(AutosuggestServiceImpl.class);

	/**
	 * Solr Keyword Redirect Service {@link SolrKeywordRedirectService}.
	 */
	@Resource(name = "solrKeywordRedirectService")
	private SolrKeywordRedirectService solrKeywordRedirectService;

	/**
	 * Default Solr Facet Search Config Selection Strategy
	 * {@link DefaultSolrFacetSearchConfigSelectionStrategy} .
	 */
	@Resource(name = "defaultSolrFacetSearchConfigSelectionStrategy")
	DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy;

	/**
	 * CMS Site Service {@link CMSSiteService}.
	 */
	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;

	/**
	 * Facet Search Config Service {@link FacetSearchConfigService}.
	 */
	@Resource(name = "facetSearchConfigService")
	private FacetSearchConfigService facetSearchConfigService;

	/**
	 * This method constructs the URL which is hit on the SOLR to extract the
	 * suggestions.
	 *
	 * @param searchTerm Term entered by the user on which the suggestions have
	 *            to be found.
	 * @param suggestionType Suggestion Type i.e. category or matching
	 *            keyword(brand,color,gender).
	 * @param cmsSiteModel CMS Site Model.
	 * @return URL
	 * @throws MalformedURLException
	 */
	private URL getUrl(String searchTerm, final SuggestionType suggestionType, final CMSSiteModel cmsSiteModel)
			throws MalformedURLException {

		String urlqueryprefix = StringUtils.EMPTY;
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
		if (LOG.isDebugEnabled()) {
			LOG.debug("AutosuggestUtil 2" + urlqueryprefix + " _ " + cmsSiteModel);
		}
		String url = null;
		if (searchTerm.contains(AutosuggestConstants.AND)) {
			searchTerm = searchTerm.replace(AutosuggestConstants.AND, AutosuggestConstants.AND_ENCODED);
		}
		if (searchTerm.contains(AutosuggestConstants.COMMA)) {
			searchTerm = searchTerm.replace(AutosuggestConstants.COMMA, AutosuggestConstants.COMMA_ENCODED);
		}
		if (searchTerm.contains(AutosuggestConstants.SPACE)) {
			searchTerm = searchTerm.replace(AutosuggestConstants.SPACE, AutosuggestConstants.SPACE_URL_ENCODED);
		}
		searchTerm = searchTerm.toLowerCase(Locale.ENGLISH);

		final StringBuilder builder = new StringBuilder();
		if (SuggestionType.CATEGORY.equals(suggestionType)) {
			if (!searchTerm.contains(AutosuggestConstants.SPACE)) {
				builder.append(urlqueryprefix).append("master_ssl_Product/select?hl=on&q=")
						.append(AutosuggestConstants.PRODUCT_L3_CATEGORY).append("%3A\"").append(searchTerm)
						.append("\"^1020+OR+").append(AutosuggestConstants.PRODUCT_L3_CATEGORY).append("%3A")
						.append(searchTerm).append("?*^750+OR+").append(AutosuggestConstants.PRODUCT_L3_CATEGORY)
						.append("%3A*").append(searchTerm).append("*^20+OR+")
						.append(AutosuggestConstants.PRODUCT_L3_CATEGORY_CODE).append(":" + searchTerm).append("&fl=")
						.append(AutosuggestConstants.AUTOSUGGESTION_CATEGORY).append("%2C")
						.append(AutosuggestConstants.PRODUCT_CATEGORY_CODE)
						.append("%2Cscore&rows=1000&wt=json&indent=true" + "&hl.fl="
								+ AutosuggestConstants.AUTOSUGGESTION_CATEGORY);
			} else {
				builder.append(urlqueryprefix).append("master_ssl_Product/select?hl=on&q=")
						.append(AutosuggestConstants.PRODUCT_L3_CATEGORY).append("%3A\"").append(searchTerm)
						.append("\"^1020+OR+").append(AutosuggestConstants.PRODUCT_L3_CATEGORY).append("%3A")
						.append(searchTerm).append("?*^750+OR+").append(AutosuggestConstants.PRODUCT_L3_CATEGORY)
						.append("%3A*").append(searchTerm).append("*^20&fl=")
						.append(AutosuggestConstants.AUTOSUGGESTION_CATEGORY).append("%2C")
						.append(AutosuggestConstants.PRODUCT_CATEGORY_CODE)
						.append("%2Cscore&rows=1000&wt=json&indent=true" + "&hl.fl="
								+ AutosuggestConstants.AUTOSUGGESTION_CATEGORY);
			}
			url = builder.toString();
		} else if (SuggestionType.MATCHINGKEYWORDS.equals(suggestionType)) {
			builder.append(urlqueryprefix).append("master_ssl_Product/select?hl=on&q=")
					.append(AutosuggestConstants.PRODUCT_COLOR_CODE).append("%3A*").append(searchTerm + "*")
					.append("+OR+").append(AutosuggestConstants.PRODUCT_SIZE_CODE).append("%3A*").append(searchTerm)
					.append("*+OR+").append(AutosuggestConstants.PRODUCT_BRAND).append("%3A\"").append(searchTerm)
					.append("\"^1020+OR+").append(AutosuggestConstants.PRODUCT_BRAND).append("%3A").append(searchTerm)
					.append("?*^750+OR+").append(AutosuggestConstants.PRODUCT_BRAND).append("%3A*").append(searchTerm)
					.append("*^20+OR+").append(AutosuggestConstants.PRODUCT_GENDER).append("%3A*")
					.append(searchTerm + "*").append("&fl=").append(AutosuggestConstants.AUTOSUGGESTION_BRANDS)
					.append("%2C").append(AutosuggestConstants.AUTOSUGGESTION_COLOR).append("%2C")
					.append(AutosuggestConstants.AUTOSUGGESTION_BRAND_REFINE_URL)
					.append("%2Cscore&rows=1000&wt=json&indent=true");
			url = builder.toString();
		}
		if (null != url) {
			return new URL(url.replace(AutosuggestConstants.SPACE, AutosuggestConstants.SPACE_URL_ENCODED2));
		} else {
			return null;
		}
	}

	/**
	 * This method extracts the suggestions from SOLR using the URL and also
	 * populates the results.
	 *
	 * @param url URL to hit the SOLR and extract the suggestions.
	 * @param suggestionType Suggestion Type i.e. category or matching
	 *            keyword(brand,color,gender).
	 * @param searchTerm Term entered by the user on which the suggestions have
	 *            to be found.
	 * @return LinkedHashSet<SuggestionResponse> List of responses from SOLR.
	 * @throws IOException
	 */
	private TreeSet getSuggestionsFromSolr(final URL url, final SuggestionType suggestionType, final String searchTerm)
			throws IOException

	{
		int totalResultFound;

		HttpURLConnection conn;
		TreeSet suggestionList = null;
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(AutosuggestConstants.REQUEST_METHOD_TYPE);
		conn.setRequestProperty(AutosuggestConstants.REQUEST_PROPERTY_KEY, AutosuggestConstants.REQUEST_PROPERTY_VALUE);
		final ObjectMapper mapper = new ObjectMapper();
		final JsonNode root = mapper.readTree(new InputStreamReader(conn.getInputStream()));
		final JsonNode docs = root.get(AutosuggestConstants.RESPONSE).get(AutosuggestConstants.DOCS_FOUND);

		totalResultFound = Integer.parseInt(
				root.get(AutosuggestConstants.RESPONSE).path(AutosuggestConstants.NUMBER_OF_ELEMENTS_FOUND).asText());
		if (LOG.isDebugEnabled()) {
			LOG.debug("AutosuggestUtil 3" + url + " _ " + totalResultFound);
		}

		if (totalResultFound > 0) {
			suggestionList = new TreeSet(new Comparator<SuggestionResponse>() {
				@Override
				public int compare(final SuggestionResponse sr1, final SuggestionResponse sr2) {
					if (suggestionType.equals(SuggestionType.MATCHINGKEYWORDS)) {
						if (null != sr1.getBrandSuggestionName() && null != sr2.getBrandSuggestionName()) {

							if (sr1.getBrandSuggestionName().equals(sr2.getBrandSuggestionName())) {
								return 0;
							}
						}
					} else if (suggestionType.equals(SuggestionType.CATEGORY)) {
						if (null != sr1.getCategorySuggestionName() && null != sr2.getCategorySuggestionName()) {
							if (sr1.getCategorySuggestionName().equals(sr2.getCategorySuggestionName())) {
								return 0;
							}
						}
					}
					return 1;
				}
			});

			for (final JsonNode objNode : docs) {
				if (suggestionType == SuggestionType.CATEGORY) {
					populateCategorySuggestion(suggestionType, suggestionList, objNode);

				} else if (suggestionType == SuggestionType.MATCHINGKEYWORDS) {
					populateBrandSuggestion(suggestionType, searchTerm, suggestionList, objNode);
				}
				if (suggestionList.size() >= 8) {
					break;
				}
			}
		}
		conn.disconnect();
		return suggestionList;
	}

	/**
	 * This method populates the category suggestions.
	 *
	 * @param suggestionType Suggestion Type i.e. category or matching
	 *            keyword(brand,color,gender).
	 * @param suggestionSet Set which has the category suggestions.
	 * @param objNode One JsonNode in the docs extracted from SOLR.
	 */
	private void populateCategorySuggestion(final SuggestionType suggestionType,
			final Set<SuggestionResponse> suggestionSet, final JsonNode objNode) {

		final JsonNode catObj = objNode.get(AutosuggestConstants.AUTOSUGGESTION_CATEGORY);
		if (catObj != null) {
			for (int position = 0; position < catObj.size(); position++) {
				if (catObj.get(position) != null) {
					final SuggestionResponse suggestion = new SuggestionResponse();
					suggestion.setType(suggestionType);
					suggestion.setChildProductCount(1);
					suggestion.setScore(objNode.get(AutosuggestConstants.SCORE).intValue());
					final String[] terms = catObj.get(position).textValue()
							.split(AutosuggestConstants.SPLIT_CAHARCHTER);
					suggestion.setCategorySuggestionName(WordUtils.capitalize(terms[0].toLowerCase())
							+ AutosuggestConstants.AUTOSUGGESTION_NAME_IN + WordUtils.capitalize(terms[1].toLowerCase())
							+ AutosuggestConstants.AUTOSUGGESTION_NAME_FOR
							+ WordUtils.capitalize(terms[2].toLowerCase()));
					if (LOG.isDebugEnabled()) {
						LOG.debug("AutosuggestUtil 4" + WordUtils.capitalize(terms[0].toLowerCase())
								+ AutosuggestConstants.AUTOSUGGESTION_NAME_IN
								+ WordUtils.capitalize(terms[1].toLowerCase())
								+ AutosuggestConstants.AUTOSUGGESTION_NAME_FOR
								+ WordUtils.capitalize(terms[2].toLowerCase()));
					}
					suggestion.setCategorySuggestionCode(
							objNode.get(AutosuggestConstants.PRODUCT_CATEGORY_CODE).get(position).textValue());
					if (terms[0].toLowerCase().contains(AutosuggestConstants.SPACE)) {
						terms[0].toLowerCase().replace(AutosuggestConstants.SPACE, AutosuggestConstants.SPACE_ENCODED);
					}
					String category = terms[0].toLowerCase();
					if (category.contains(AutosuggestConstants.AND)) {
						category = category.replace(AutosuggestConstants.AND, AutosuggestConstants.AND_ENCODED);
					}
					if (category.contains(AutosuggestConstants.COMMA)) {
						category = category.replace(AutosuggestConstants.COMMA, AutosuggestConstants.COMMA_ENCODED);
					}
					String genderCategory = catObj.get(position).textValue();
					if (genderCategory.contains(AutosuggestConstants.AND)) {
						genderCategory = genderCategory.replace(AutosuggestConstants.AND,
								AutosuggestConstants.AND_ENCODED);
					}
					if (genderCategory.contains(AutosuggestConstants.COMMA)) {
						genderCategory = genderCategory.replace(AutosuggestConstants.COMMA,
								AutosuggestConstants.COMMA_ENCODED);
					}
					final StringBuilder builder = new StringBuilder();
					builder.append("/search?q=").append(category).append("%3Arelevance%3A")
							.append("genderAutoSuggestionImprovedSearch%3A").append(genderCategory);
					final String url = builder.toString();
					suggestion.setCategorySuggestionURL(url);
					suggestionSet.add(suggestion);
				}
			}
		}
	}

	/**
	 * This method populates the brands,color or gender suggestions.
	 *
	 * @param suggestionType Suggestion Type i.e. category or matching
	 *            keyword(brand,color,gender).
	 * @param searchTerm Term entered by the user on which the suggestions have
	 *            to be found.
	 * @param suggestionSet Set which has the brands,color or gender
	 *            suggestions.
	 * @param objNode One JsonNode in the docs extracted from SOLR.
	 */
	private void populateBrandSuggestion(final SuggestionType suggestionType, final String searchTerm,
			final Set<SuggestionResponse> suggestionSet, final JsonNode objNode) {

		JsonNode catObj1;
		JsonNode catObj2;
		JsonNode catObj3;
		String brandRefined = null;
		final List<String> matchingKeywordType = new ArrayList<String>();

		final JsonNode brandFormatted = objNode.get(AutosuggestConstants.AUTOSUGGESTION_BRAND_REFINE_URL);
		if (null != brandFormatted) {
			brandRefined = brandFormatted.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.QUOTES, StringUtils.EMPTY);
		}
		catObj1 = objNode.get(AutosuggestConstants.AUTOSUGGESTION_BRANDS);
		if (catObj1 != null
				&& !catObj1.toString().toLowerCase(Locale.ENGLISH).contains(searchTerm.toLowerCase(Locale.ENGLISH))) {
			catObj1 = null;
		} else {
			matchingKeywordType.add(AutosuggestConstants.BRANDS);
		}

		catObj2 = objNode.get(AutosuggestConstants.AUTOSUGGESTION_COLOR);
		if (catObj2 != null && !catObj2.toString().toLowerCase().contains(searchTerm)) {
			catObj2 = null;
		} else {
			matchingKeywordType.add(AutosuggestConstants.COLOR);
		}

		catObj3 = objNode.get(AutosuggestConstants.AUTOSUGGESTION_CATEGORY);
		if (catObj3 != null) {
			final String[] terms = catObj3.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.QUOTES, StringUtils.EMPTY).toLowerCase()
					.split(AutosuggestConstants.COMMA);
			final StringBuffer stringBuffer = new StringBuffer();
			for (final String term : terms) {
				stringBuffer.append(term + AutosuggestConstants.SPACE);
			}
			if (!stringBuffer.toString().contains(searchTerm.toLowerCase(Locale.ENGLISH))) {
				catObj3 = null;
			} else {
				matchingKeywordType.add(AutosuggestConstants.GENDER);
			}
		}

		if (catObj1 != null
				&& catObj1.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
						.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY).toLowerCase()
						.contains(searchTerm.toLowerCase(Locale.ENGLISH))
				&& matchingKeywordType.contains(AutosuggestConstants.BRANDS)) {

			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			suggestion.setScore(objNode.get(AutosuggestConstants.SCORE).intValue());
			final String[] multipleBrands = catObj1.toString().split(AutosuggestConstants.COMMA);
			for (int i = 0; i < multipleBrands.length; i++) {
				final String[] terms = multipleBrands[i].split(AutosuggestConstants.SPLIT_CAHARCHTER);
				String brandWithSpecialCharacter = terms[0].replace(AutosuggestConstants.JSON_PREFIX,
						StringUtils.EMPTY);
				if (brandWithSpecialCharacter.contains("\"")) {
					brandWithSpecialCharacter = brandWithSpecialCharacter.replace("\"", "");
				}
				// if
				// (brandWithSpecialCharacter.contains(AutosuggestConstants.COMMA))
				// {
				// brandWithSpecialCharacter =
				// brandWithSpecialCharacter.replace(AutosuggestConstants.COMMA,
				// AutosuggestConstants.COMMA_ENCODED);
				// }
				suggestion.setBrandSuggestionName(
						WordUtils.capitalize(brandWithSpecialCharacter.toLowerCase(Locale.ENGLISH)) + " in "
								+ WordUtils.capitalize(
										terms[1].replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY)
												.toLowerCase(Locale.ENGLISH)));
				String brandURLWithSpecialCharacter = multipleBrands[i]
						.replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
						.replace(AutosuggestConstants.JSON_POSTFIX, "").replace("\"", "");
				if (brandURLWithSpecialCharacter.contains(AutosuggestConstants.AND)) {
					brandURLWithSpecialCharacter = brandWithSpecialCharacter.replace(AutosuggestConstants.AND,
							AutosuggestConstants.AND_ENCODED);
				}
				if (brandURLWithSpecialCharacter.contains(AutosuggestConstants.COMMA)) {
					brandURLWithSpecialCharacter = brandWithSpecialCharacter.replace(AutosuggestConstants.COMMA,
							AutosuggestConstants.COMMA_ENCODED);
				}
				final StringBuilder builder = new StringBuilder();
				builder.append("/search?q=").append(brandWithSpecialCharacter).append("%3Arelevance%3A")
						.append(AutosuggestConstants.AUTOSUGGESTION_BRANDS_URL).append("%3A")
						.append(brandURLWithSpecialCharacter);
				final String newURL = builder.toString();
				suggestion.setBrandSuggestionURL(newURL);
				final SuggestionResponse suggestionOnlyBrand = new SuggestionResponse();
				suggestionOnlyBrand.setType(suggestionType);
				final String brand = WordUtils.capitalize(terms[0]
						.replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY).replace("\"", "").toLowerCase());

				String url = null;
				try {
					final SolrFacetSearchConfigModel solrFacetSearchConfig = getDefaultSolrFacetSearchConfigSelectionStrategy()
							.getCurrentSolrFacetSearchConfig();
					if (null != solrFacetSearchConfig && null != solrFacetSearchConfig.getName()) {
						final FacetSearchConfig facetSearchConfig = getFacetSearchConfigService()
								.getConfiguration(solrFacetSearchConfig.getName());
						final SearchQuery searchQuery = new SearchQuery(facetSearchConfig, null);
						searchQuery.setUserQuery(brand);
						final List<KeywordRedirectValue> redirects = getSolrKeywordRedirectService()
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
					final StringBuilder defaultUrlBuilder = new StringBuilder();
					defaultUrlBuilder.append("/search?q=").append(brand)
							.append("%3Arelevance%3AinStockFlag%3Atrue%3AbrandAutosuggestRefine%3A")
							.append(brandRefined);
					url = defaultUrlBuilder.toString();
				}
				suggestionOnlyBrand.setBrandSuggestionName(brand);
				suggestionOnlyBrand.setBrandSuggestionURL(url);
				suggestionSet.add(suggestionOnlyBrand);
				suggestionSet.add(suggestion);
			}

		}

		if (catObj2 != null
				&& catObj2.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
						.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY).toLowerCase()
						.contains(searchTerm.toLowerCase(Locale.ENGLISH))
				&& matchingKeywordType.contains(AutosuggestConstants.COLOR)) {
			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			final String[] terms = catObj2.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.QUOTES, StringUtils.EMPTY).split(AutosuggestConstants.COMMA);
			String[] colorSuggestion = null;
			String url = StringUtils.EMPTY;
			for (final String colorTerm : terms) {
				if (colorTerm.contains(searchTerm)) {
					colorSuggestion = colorTerm.split(AutosuggestConstants.SPLIT_CAHARCHTER);
					suggestion.setBrandSuggestionName(WordUtils.capitalize(colorSuggestion[1].toLowerCase())
							+ AutosuggestConstants.SPACE + WordUtils.capitalize(colorSuggestion[0].toLowerCase()));
					final StringBuilder builder = new StringBuilder();
					builder.append("/search?q=").append(colorSuggestion[0].toLowerCase())
							.append("%3Arelevance%3AinStockFlag%3Atrue%3A")
							.append(AutosuggestConstants.AUTOSUGGESTION_COLOR_URL).append("%3A")
							.append(colorSuggestion[1].toLowerCase()).append("&text=")
							.append(colorSuggestion[0].toLowerCase()).append("&startRange=&endRange=&showType=#");
					url = builder.toString();
					suggestion.setBrandSuggestionURL(url);
					break;
				}
			}
			suggestionSet.add(suggestion);
		}

		if (catObj3 != null
				&& catObj3.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
						.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY).toLowerCase(Locale.ENGLISH)
						.contains(searchTerm.toLowerCase(Locale.ENGLISH))
				&& matchingKeywordType.contains(AutosuggestConstants.GENDER)) {
			final SuggestionResponse suggestion = new SuggestionResponse();
			suggestion.setType(suggestionType);
			suggestion.setChildProductCount(1);
			final String[] terms = catObj3.toString().replace(AutosuggestConstants.JSON_PREFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.JSON_POSTFIX, StringUtils.EMPTY)
					.replace(AutosuggestConstants.QUOTES, StringUtils.EMPTY).toLowerCase()
					.split(AutosuggestConstants.COMMA);
			String[] genderSuggestion = null;
			String url = StringUtils.EMPTY;
			for (final String genderTerm : terms) {
				if (genderTerm.contains(searchTerm)) {
					genderSuggestion = genderTerm.split(AutosuggestConstants.SPLIT_CAHARCHTER);
					suggestion.setCategorySuggestionName(
							WordUtils.capitalize(WordUtils.capitalize(genderSuggestion[2].toLowerCase())
									+ AutosuggestConstants.SPACE + genderSuggestion[0].toLowerCase()));
					final StringBuilder builder = new StringBuilder();
					builder.append("/search?q=").append(genderSuggestion[0].toLowerCase()).append("%3Arelevance%3A")
							.append("l1category%3A").append(WordUtils.capitalize(genderSuggestion[2]));
					url = builder.toString();
					suggestion.setBrandSuggestionURL(url);
					suggestionSet.add(suggestion);
				}
			}

		}
	}

	/**
	 * This method extracts suggestions.
	 *
	 * @param searchTerm Term entered by the user on which the suggestions have
	 *            to be found.
	 * @param cmsSiteModel CMS Site Model
	 * @param model
	 * @return List<SuggestionResponse> List of suggestion responses.
	 * @throws IOException
	 */
	@Override
	public List<SuggestionResponse> getInSuggestions(final String searchTerm, final CMSSiteModel cmsSiteModel,
			final Model model) throws IOException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Search Term:%s", searchTerm));
		}
		List<SuggestionResponse> collectiveSortedList = new ArrayList();
		List<SuggestionResponse> categorySuggestionList = getSuggestionListFromSolr(
				searchTerm, cmsSiteModel, model, SuggestionType.CATEGORY, AutosuggestConstants.MAX_CATEGORY_SUGGESTIONS);
		collectiveSortedList.addAll(categorySuggestionList);
		if(collectiveSortedList.size() < AutosuggestConstants.MAX_TOTAL_SUGGESTIONS){
			List<SuggestionResponse> brandSuggestionList = getSuggestionListFromSolr(searchTerm, cmsSiteModel, model,
					SuggestionType.MATCHINGKEYWORDS, AutosuggestConstants.MAX_BRAND_SUGGESTIONS);
			collectiveSortedList.addAll(brandSuggestionList);
		}
			//sort also before subListing.
		collectiveSortedList = sortAndSubList(collectiveSortedList, AutosuggestConstants.MAX_TOTAL_SUGGESTIONS);
		return collectiveSortedList;
	}

	/**
	 * This method extracts the brands,gender or color suggestions from SOLR.
	 * @param searchTerm Term entered by the user on which the suggestions have
	 *            to be found.
	 * @param cmsSiteModel CMS Site Model.
	 * @param model
	 * @throws IOException
	 */
	private List<SuggestionResponse> getSuggestionListFromSolr(final String searchTerm,
			final CMSSiteModel cmsSiteModel, final Model model, SuggestionType suggestionType, int max)
			throws IOException {
		final Set<SuggestionResponse> suggestionSet = getSuggestionsFromSolr(
				getUrl(searchTerm, suggestionType, cmsSiteModel), suggestionType,
				searchTerm);
		if(CollectionUtils.isNotEmpty(suggestionSet)) {
			//final SuggestionComparator comparator = new SuggestionComparator();
			final HashSet<SuggestionResponse> brandSuggestionSet = new HashSet<>();
			for (final SuggestionResponse suggestion : suggestionSet) {
				addValue(brandSuggestionSet, suggestion, suggestionType, model);
			}
			List<SuggestionResponse> brandSortedList = new ArrayList(brandSuggestionSet);

			brandSortedList = sortAndSubList(brandSortedList, max);
			return brandSortedList;
		}
		return Collections.emptyList();
	}

	/**
	 * Returns sorted sublist using max.
	 * @param responseList
	 * @param max
	 * @return
	 */
	public List<SuggestionResponse> sortAndSubList(List<SuggestionResponse> responseList, int max) {
		Collections.sort(responseList, new SuggestionComparator());
		if(responseList.size() > max) {
			responseList = responseList.subList(0, max);
		}
		return responseList;
	}

	/**
	 * This method removes the redundancies in the suggestions.
	 *
	 * @param suggestionSet Set of suggestions.
	 * @param suggestion Each suggestion response.
	 * @param suggestionType Suggestion Type i.e. category or matching
	 *            keyword(brand,color,gender).
	 * @param model
	 */
	private void addValue(final Set<SuggestionResponse> suggestionSet, final SuggestionResponse suggestion,
			final SuggestionType suggestionType, final Model model) {
		final Iterator<SuggestionResponse> iterator = suggestionSet.iterator();
		boolean isIdentical = false;
		while (iterator.hasNext()) {
			final SuggestionResponse existingSuggestion = iterator.next();
			if (SuggestionType.CATEGORY.equals(suggestionType)) {
				if (StringUtils.equalsIgnoreCase(existingSuggestion.getCategorySuggestionName(),
						suggestion.getCategorySuggestionName())) {
					isIdentical = true;
				}
			} else if (SuggestionType.MATCHINGKEYWORDS.equals(suggestionType)) {
				if (SuggestionType.MATCHINGKEYWORDS.equals(existingSuggestion.getType())
						&& existingSuggestion.getBrandSuggestionName() != null
						&& StringUtils.equalsIgnoreCase(existingSuggestion.getBrandSuggestionName(),
						suggestion.getBrandSuggestionName())) {
					isIdentical = true;
				} else if (SuggestionType.MATCHINGKEYWORDS.equals(existingSuggestion.getType())
						&& existingSuggestion.getBrandSuggestionName() == null
						&& existingSuggestion.getCategorySuggestionName() != null
						&& StringUtils.equalsIgnoreCase(existingSuggestion.getCategorySuggestionName(),
						suggestion.getCategorySuggestionName())) {
					isIdentical = true;
					model.addAttribute(AutosuggestConstants.GENDER, Integer.valueOf(1));
				}
			}
			if(isIdentical){
				existingSuggestion.setChildProductCount(existingSuggestion.getChildProductCount() + 1);
			}
		}

		if (!isIdentical) {
			suggestionSet.add(suggestion);
			if(LOG.isDebugEnabled()){
				LOG.debug(String.format("Added suggestion categoryName: %s, brandName: %s",
						suggestion.getCategorySuggestionName(), suggestion.getBrandSuggestionName()));
			}
		}
	}

	/**
	 *
	 * @param map
	 * @return sortedListByScore
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
		final List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(final Map.Entry<K, V> object1, final Map.Entry<K, V> object2) {
				return object2.getValue().compareTo(object1.getValue());
			}
		});

		final Map<K, V> result = new LinkedHashMap<>();
		for (final Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * @return the solrKeywordRedirectService
	 */
	public SolrKeywordRedirectService getSolrKeywordRedirectService() {
		return solrKeywordRedirectService;
	}

	/**
	 * @param solrKeywordRedirectService the solrKeywordRedirectService to set
	 */
	public void setSolrKeywordRedirectService(final SolrKeywordRedirectService solrKeywordRedirectService) {
		this.solrKeywordRedirectService = solrKeywordRedirectService;
	}

	/**
	 * @return the defaultSolrFacetSearchConfigSelectionStrategy
	 */
	public DefaultSolrFacetSearchConfigSelectionStrategy getDefaultSolrFacetSearchConfigSelectionStrategy() {
		return defaultSolrFacetSearchConfigSelectionStrategy;
	}

	/**
	 * @param defaultSolrFacetSearchConfigSelectionStrategy the
	 *            defaultSolrFacetSearchConfigSelectionStrategy to set
	 */
	public void setDefaultSolrFacetSearchConfigSelectionStrategy(
			final DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy) {
		this.defaultSolrFacetSearchConfigSelectionStrategy = defaultSolrFacetSearchConfigSelectionStrategy;
	}

	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService() {
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return the facetSearchConfigService
	 */
	public FacetSearchConfigService getFacetSearchConfigService() {
		return facetSearchConfigService;
	}

	/**
	 * @param facetSearchConfigService the facetSearchConfigService to set
	 */
	public void setFacetSearchConfigService(final FacetSearchConfigService facetSearchConfigService) {
		this.facetSearchConfigService = facetSearchConfigService;
	}

}
