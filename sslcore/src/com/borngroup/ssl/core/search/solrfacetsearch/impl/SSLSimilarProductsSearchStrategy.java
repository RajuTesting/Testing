/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator;
import de.hybris.platform.solrfacetsearch.search.SearchResult;
import de.hybris.platform.solrfacetsearch.search.context.FacetSearchContext;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultFacetSearchStrategy;
import de.hybris.platform.solrfacetsearch.search.impl.SearchQueryConverterData;
import de.hybris.platform.solrfacetsearch.search.impl.SearchResultConverterData;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.autosuggest.AutosuggestConstants;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.product.service.SslProductService;
import com.borngroup.ssl.core.search.solrfacetsearch.data.SimilarProductsData;

public class SSLSimilarProductsSearchStrategy extends DefaultFacetSearchStrategy {
    private static final Logger LOG = Logger.getLogger(SSLSimilarProductsSearchStrategy.class);

    private static final String FACET_COUNT = "facet.limit.default";

    private static final Map<String, List<String>> CATEGORY_ATTRIBUTE_MAP;
    private static final Map<String, String> ATTRIBUTE_QUALIFIER_MAP;
    private static final Map<String, String> ATTRIBUTE_SOLR_MAP;
    private static final Map<String, List<SimilarProductsData>> CATEGORY_ATTRIBUTE_PRIORITY_MAP;
    static {
        final Map<String, List<String>> tmp = new HashMap<>();
        tmp.put("Dresses", Arrays.asList("Product Type", "Occasion", "Color", "Neckline", "Sleeves"));
        tmp.put("Tops & Tees", Arrays.asList("Product Type", "Occasion", "Color", "Neckline", "Sleeves"));
        tmp.put("Shirts", Arrays.asList("Product Type", "Occasion", "Pattern", "Color", "Neckline", "Sleeves"));
        tmp.put("Kurtas", Arrays.asList("Product Type", "Occasion", "Type of work", "Length", "Sleeves"));
        tmp.put("Lingerie", Arrays.asList("Product Type", "Occasion", "Category", "Strap", "Padded/Non Padded", "Wired/Non wired"));
        tmp.put("Watches", Arrays.asList("Product Type", "Occasion", "Display Type", "Dial Shape", "Strap Style", "Strap Type"));
        CATEGORY_ATTRIBUTE_MAP = Collections.unmodifiableMap(tmp);
    }

    static {

        final Map<String, List<SimilarProductsData>> tmp = new HashMap<>();

        final List<SimilarProductsData> similarProductsData = new ArrayList<SimilarProductsData>();
        similarProductsData.add(new SimilarProductsData("Color", 400, "Filter"));
        similarProductsData.add(new SimilarProductsData("Brand", 20, "Filter"));
        similarProductsData.add(new SimilarProductsData("Fit", 1, "Match"));
        tmp.put("Jeans", similarProductsData);

        final List<SimilarProductsData> similarProductsData2 = new ArrayList<SimilarProductsData>();
        similarProductsData2.add(new SimilarProductsData("Product Type", 1700, "Filter"));
        similarProductsData2.add(new SimilarProductsData("Color", 400, "Filter"));
        similarProductsData2.add(new SimilarProductsData("Fit", 20, "Filter"));
        similarProductsData2.add(new SimilarProductsData("Brand", 4, "Filter"));
        similarProductsData2.add(new SimilarProductsData("Length", 1, "Match"));
        tmp.put("Jeans & Leggings", similarProductsData2);

        final List<SimilarProductsData> similarProductsData3 = new ArrayList<SimilarProductsData>();
        similarProductsData3.add(new SimilarProductsData("Category", 400, "Filter"));
        similarProductsData3.add(new SimilarProductsData("Color", 20, "Filter"));
        similarProductsData3.add(new SimilarProductsData("Fit", 1, "Match"));
        similarProductsData3.add(new SimilarProductsData("Pattern", 1, "Filter"));
        similarProductsData3.add(new SimilarProductsData("Neckline", 1, "Filter"));
        similarProductsData3.add(new SimilarProductsData("Sleeves", 1, "Match"));
        tmp.put("T-shirts", similarProductsData3);

        final List<SimilarProductsData> similarProductsData4 = new ArrayList<SimilarProductsData>();
        similarProductsData4.add(new SimilarProductsData("Product Type", 400, "Filter"));
        similarProductsData4.add(new SimilarProductsData("Occasion", 20, "Filter"));
        similarProductsData4.add(new SimilarProductsData("Length", 1, "Match"));
        similarProductsData4.add(new SimilarProductsData("Neckline", 1, "Filter"));
        similarProductsData4.add(new SimilarProductsData("Pattern", 1, "Filter"));
        similarProductsData4.add(new SimilarProductsData("Sleeves", 1, "Match"));
        tmp.put("Palazzos & Jumpsuits", similarProductsData4);

        final List<SimilarProductsData> similarProductsData5 = new ArrayList<SimilarProductsData>();
        similarProductsData5.add(new SimilarProductsData("Occasion", 400, "Filter"));
        similarProductsData5.add(new SimilarProductsData("Color", 20, "Filter"));
        similarProductsData5.add(new SimilarProductsData("Length", 1, "Match"));
        similarProductsData5.add(new SimilarProductsData("Fit", 1, "Filter"));
        tmp.put("Pants", similarProductsData5);

        final List<SimilarProductsData> similarProductsData6 = new ArrayList<SimilarProductsData>();
        similarProductsData6.add(new SimilarProductsData("Category", 400, "Filter"));
        similarProductsData6.add(new SimilarProductsData("Occasion", 20, "Filter"));
        similarProductsData6.add(new SimilarProductsData("Product Type", 4, "Filter"));
        similarProductsData6.add(new SimilarProductsData("Color", 1, "Match"));
        similarProductsData6.add(new SimilarProductsData("Pattern", 1, "Filter"));
        similarProductsData6.add(new SimilarProductsData("Fastening/Pattern", 1, "Filter"));
        tmp.put("Women-Shoes", similarProductsData6);

        final List<SimilarProductsData> similarProductsData7 = new ArrayList<SimilarProductsData>();
        similarProductsData7.add(new SimilarProductsData("Category", 1900, "Filter"));
        similarProductsData7.add(new SimilarProductsData("Product Type", 400, "Filter"));
        similarProductsData7.add(new SimilarProductsData("Color", 40, "Match"));
        similarProductsData7.add(new SimilarProductsData("Pattern", 1, "Filter"));
        similarProductsData7.add(new SimilarProductsData("Fastening/Pattern", 1, "Filter"));
        tmp.put("Men-Shoes", similarProductsData7);

        CATEGORY_ATTRIBUTE_PRIORITY_MAP = Collections.unmodifiableMap(tmp);
    }

    static {
        final Map<String, String> tmp = new HashMap<>();
        tmp.put("Product Type", "sslClassification/1.0/Product Type.product type");
        tmp.put("Occasion", "sslClassification/1.0/Occasion.occasion");
        tmp.put("Neckline", "sslClassification/1.0/Neckline.neckline");
        tmp.put("Sleeves", "sslClassification/1.0/Sleeves.sleeves");
        tmp.put("Type of work", "sslClassification/1.0/Type of work.type of work");
        tmp.put("Length", "sslClassification/1.0/Length.length");
        tmp.put("Category", "sslClassification/1.0/Category.category");
        tmp.put("Strap", "sslClassification/1.0/Strap.strap");
        tmp.put("Padded/Non Padded", "sslClassification/1.0/Padded/Non Padded.padded/non padded");
        tmp.put("Wired/Non wired", "sslClassification/1.0/Wired/Non wired.wired/non wired");
        tmp.put("Display Type", "sslClassification/1.0/Display Type.display type");
        tmp.put("Dial Shape", "sslClassification/1.0/Dial Shape.dial shape");
        tmp.put("Strap Style", "sslClassification/1.0/Strap Style.strap style");
        tmp.put("Strap Type", "sslClassification/1.0/Strap Type.strap type");
        tmp.put("Pattern", "sslClassification/1.0/Pattern.pattern");
        tmp.put("Fit", "sslClassification/1.0/Fit.fit");
        tmp.put("Fastening/Pattern", "sslClassification/1.0/Fastening/Pattern.fastening/pattern");
        ATTRIBUTE_QUALIFIER_MAP = Collections.unmodifiableMap(tmp);
    }

    static {
        final Map<String, String> tmp = new HashMap<>();
        tmp.put("Product Type", "ProductType_string_mv");
        tmp.put("Occasion", "Occasion_string_mv");
        tmp.put("Neckline", "Neckline_string_mv");
        tmp.put("Sleeves", "Sleeves_string_mv");
        tmp.put("Type of work", "Typeofwork_string_mv");
        tmp.put("Length", "Length_string_mv");
        tmp.put("Category", "category_string_mv");
        tmp.put("Strap", "Strap_string_mv");
        tmp.put("Padded/Non Padded", "Padded/NonPadded_string_mv");
        tmp.put("Wired/Non wired", "Wired/Nonwired_string_mv");
        tmp.put("Display Type", "DisplayType_string_mv");
        tmp.put("Dial Shape", "DialShape_string_mv");
        tmp.put("Strap Style", "StrapStyle_string_mv");
        tmp.put("Strap Type", "StrapType_string_mv");
        tmp.put("Pattern", "Pattern_string_mv");
        tmp.put("Fit", "Fit_string_mv");
        tmp.put("Fastening/Pattern", "Fastening/Pattern_string_mv");
        ATTRIBUTE_SOLR_MAP = Collections.unmodifiableMap(tmp);
    }

    @Resource(name = "sslProductService")
    private SslProductService sslProductService;

    @Resource
    private ConfigurationService configurationService;

    public SearchResult search(final SearchQuery searchQuery, final Map<String, String> searchHints, final ProductModel product,
            final CategoryModel category, final boolean onlyCategory) throws FacetSearchException {

        checkQuery(searchQuery);

        try {
            final FacetSearchConfig facetSearchConfig = searchQuery.getFacetSearchConfig();
            final IndexedType indexedType = searchQuery.getIndexedType();

            final FacetSearchContext facetSearchContext = getFacetSearchContextFactory().createContext(facetSearchConfig, indexedType,
                    searchQuery);
            facetSearchContext.getSearchHints().putAll(searchHints);
            getFacetSearchContextFactory().initializeContext();

            final SolrClient solrClient = getSolrService().getSolrClient(facetSearchConfig, indexedType);
            if (!onlyCategory) {
                this.addQueryFieldsToSearchQuery(searchQuery, product, category);
            }

            final SearchQueryConverterData searchQueryConverterData = new SearchQueryConverterData();
            searchQueryConverterData.setFacetSearchContext(facetSearchContext);
            searchQueryConverterData.setSearchQuery(searchQuery);

            final SolrQuery solrQuery = getFacetSearchQueryConverter().convert(searchQueryConverterData);
            solrQuery.addFilterQuery("allCategories_string_mv:" + category.getCode());
            solrQuery.addFilterQuery("itemtype_string:ApparelStyleVariantProduct");
            solrQuery.addFilterQuery("NOT priceValue_inr_double:0");
            solrQuery.addFilterQuery("inStockFlag_boolean:true");
            solrQuery.set("fl", "code_string");

            final int count = configurationService.getConfiguration().getInt(FACET_COUNT, solrQuery.getFacetLimit());
            solrQuery.setFacetLimit(count);
            if (LOG.isDebugEnabled()) {
                LOG.debug(solrQuery);
            }
            final String query = solrQuery.getQuery().replace("\\\\\\", "");
            solrQuery.set("q", query);

            final QueryResponse queryResponse = solrClient.query(solrQuery);

            final SearchResultConverterData searchResultConverterData = new SearchResultConverterData();
            searchResultConverterData.setFacetSearchContext(facetSearchContext);
            searchResultConverterData.setQueryResponse(queryResponse);

            final SearchResult searchResult = getFacetSearchResultConverter().convert(searchResultConverterData);

            getFacetSearchContextFactory().getContext().setSearchResult(searchResult);
            getFacetSearchContextFactory().destroyContext();

            return searchResult;
        } catch (final Exception e) {
            getFacetSearchContextFactory().destroyContext(e);
            throw new FacetSearchException(e.getMessage(), e);
        }

    }

    private void addColorCondition(final SearchQuery searchQuery, final ProductModel product) {
        final String[] colorList = new String[product.getVariants().size()];

        int count = 0;
        for (final VariantProductModel style : product.getVariants()) {
            if (style instanceof ApparelStyleVariantProductModel && ((ApparelStyleVariantProductModel) style).getColourCode() != null) {
                colorList[count] = handleSpecialCharactersInQuery(((ApparelStyleVariantProductModel) style).getColourCode().toLowerCase())
                        + "\\^20";
                count++;
            }
        }

        if (count > 0) {
            searchQuery.addQuery("swatchColors_string_mv", Operator.OR, colorList);
        }
    }

    private void addQueryFieldsToSearchQuery(final SearchQuery searchQuery, final ProductModel product, final CategoryModel category) {
        LOG.debug("In addQueryFieldsToSearchQuery");
        if (CATEGORY_ATTRIBUTE_MAP.get(category.getName()) != null) {
            for (final String solrProperty : CATEGORY_ATTRIBUTE_MAP.get(category.getName())) {
                if (solrProperty.equalsIgnoreCase("Color")) {
                    addColorCondition(searchQuery, product);
                } else {
                    addQueryFieldCondition(searchQuery, product, solrProperty);
                }
            }
        }
        LOG.debug("Phase 2" + category.getName());

        boolean L2category = false;
        String L1category = "";
        if (CATEGORY_ATTRIBUTE_PRIORITY_MAP.get(category.getName()) == null) {
            final Collection<CategoryModel> superCategories = category.getAllSupercategories();
            for (final CategoryModel categoryModel : superCategories) {
                if (categoryModel.getName().contains("Shoes")) {
                    for (final CategoryModel supercategory : categoryModel.getAllSupercategories()) {
                        if (supercategory.getCode().contains("A10")) {
                            L1category = "Men-Shoes";
                            L2category = true;
                        } else if (category.getCode().contains("A20")) {
                            L1category = "Women-Shoes";
                            L2category = true;
                        }
                    }
                    break;
                }
            }
        }

        if (CATEGORY_ATTRIBUTE_PRIORITY_MAP.get(category.getName()) != null || L2category) {
            String categoryName = category.getName();
            if (L2category) {
                categoryName = L1category;
            }
            LOG.debug("Category name is " + categoryName);
            for (final SimilarProductsData solrProperty : CATEGORY_ATTRIBUTE_PRIORITY_MAP.get(categoryName)) {
                LOG.debug("Phase 2" + solrProperty.getAttribute() + " : " + solrProperty.getBoostValue());
                if (solrProperty.getAttribute().equalsIgnoreCase("Color")) {
                    final String[] colorList = new String[product.getVariants().size()];
                    int count = 0;
                    for (final VariantProductModel style : product.getVariants()) {
                        if (style instanceof ApparelStyleVariantProductModel
                                && ((ApparelStyleVariantProductModel) style).getColourCode() != null) {
                            colorList[count] = handleSpecialCharactersInQuery(
                                    ((ApparelStyleVariantProductModel) style).getColourCode().toLowerCase()) + "\\^"
                                    + solrProperty.getBoostValue();
                        }
                        count++;
                    }

                    if (count > 0) {
                        searchQuery.addQuery("swatchColors_string_mv", Operator.OR, colorList);
                    }
                } else if (solrProperty.getAttribute().equalsIgnoreCase("Brand")) {
                    final String brandCode = handleSpecialCharactersInQuery(product.getBrandCode());
                    searchQuery.addQuery("brands_en_string ", brandCode + "\\^" + solrProperty.getBoostValue());
                } else {
                    final String propertyValue = handleSpecialCharactersInQuery(sslProductService
                            .getProductFeatureValueByCode(ATTRIBUTE_QUALIFIER_MAP.get(solrProperty.getAttribute()), product.getPk()));
                    LOG.debug("Property Value is : " + propertyValue);
                    if (!StringUtils.isEmpty(propertyValue)) {
                        searchQuery.addQuery(ATTRIBUTE_SOLR_MAP.get(solrProperty.getAttribute()),
                                propertyValue + "\\^" + solrProperty.getBoostValue());
                    }
                }
                LOG.debug("Phase 2 Query is : " + searchQuery);
            }
        }
    }

    /**
     * @param searchQuery
     * @param product
     * @param solrProperty
     */
    private void addQueryFieldCondition(final SearchQuery searchQuery, final ProductModel product, final String solrProperty) {
        final String propertyValue = handleSpecialCharactersInQuery(
                sslProductService.getProductFeatureValueByCode(ATTRIBUTE_QUALIFIER_MAP.get(solrProperty), product.getPk()));
        if (!StringUtils.isEmpty(propertyValue)) {

            if (solrProperty.equalsIgnoreCase("Product Type")) {
                searchQuery.addQuery(ATTRIBUTE_SOLR_MAP.get(solrProperty), propertyValue + "\\^500");
                // searchQuery.addBoost(ATTRIBUTE_SOLR_MAP.get(solrProperty),
                // SearchQuery.QueryOperator.EQUAL_TO, propertyValue, 500f,
                // BoostField.BoostType.MULTIPLICATIVE);
            }

            else if (solrProperty.equalsIgnoreCase("Pattern")) {
                searchQuery.addQuery(ATTRIBUTE_SOLR_MAP.get(solrProperty), propertyValue + "\\^20");
                // searchQuery.addBoost(ATTRIBUTE_SOLR_MAP.get(solrProperty),
                // SearchQuery.QueryOperator.EQUAL_TO, propertyValue, 20f,
                // BoostField.BoostType.MULTIPLICATIVE);
            }

            else if (solrProperty.equalsIgnoreCase("Occasion")) {
                searchQuery.addQuery(ATTRIBUTE_SOLR_MAP.get(solrProperty), propertyValue + "\\^4");
            }

            else {
                searchQuery.addQuery(ATTRIBUTE_SOLR_MAP.get(solrProperty), propertyValue);
            }

        }
    }

    public boolean isValidCategory(final CategoryModel category) {
        LOG.debug("Valid Category Name is " + category.getName());
        if (CATEGORY_ATTRIBUTE_MAP.get(category.getName()) == null && CATEGORY_ATTRIBUTE_PRIORITY_MAP.get(category.getName()) == null) {
            final Collection<CategoryModel> superCategories = category.getAllSupercategories();
            for (final CategoryModel categoryModel : superCategories) {
                if (categoryModel.getName().contains("Shoes")) {
                    LOG.debug(categoryModel.getName() + " : Valid Category");
                    return true;
                }
            }
            LOG.debug(category.getName() + " : Not a valid Category");
            return false;
        }
        LOG.debug(category.getName() + " : Valid Category");
        return true;
    }

    private String handleSpecialCharactersInQuery(String searchTerm) {
        LOG.debug("Search Term is 1 " + searchTerm);
        if (searchTerm != null) {
            if (searchTerm.contains(AutosuggestConstants.AND)) {
                searchTerm = searchTerm.replace(AutosuggestConstants.AND, AutosuggestConstants.AND_ENCODED);
            }
            if (searchTerm.contains(AutosuggestConstants.COMMA)) {
                searchTerm = searchTerm.replace(AutosuggestConstants.COMMA, AutosuggestConstants.COMMA_ENCODED);
            }
            if (searchTerm.contains(AutosuggestConstants.SPACE)) {
                searchTerm = searchTerm.replace(AutosuggestConstants.SPACE, AutosuggestConstants.SPACE_URL_ENCODED);
            }
        }
        LOG.debug("Search Term is 2 " + searchTerm);
        return searchTerm;
    }
}
