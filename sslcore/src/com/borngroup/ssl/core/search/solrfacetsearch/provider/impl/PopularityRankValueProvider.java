package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

/**
 * Value Provider : Calculates the product popularity on the basis of certain parameters.
 *
 * @author nikhilbarar
 */
public class PopularityRankValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private static final Logger LOG = Logger.getLogger(PopularityRankValueProvider.class);
    private static final String DD_MM_YYYY = "MM/dd/yyyy";
    private static final String YY_MM_DD = "yyMMdd";
    private static final int DEFAULT_POPULARITY_APPROVAL_DAYS = 7;

    private static final String POPULARITY_RANK_DAYS = "solr.sort.popularity.days";
    private static final String POPULARITY_APPROVAL_DAYS = "solr.sort.popularity.approvaldate.days";
    private static final String SEASON_CODE = "solr.sort.popularity.seasoncode";
    private static final String BOOST_SEASON_CODE_VALUE = "solr.boost.popularity.seasoncode";
    private static final String BOOST_APPROVAL_DATE_VALUE = "solr.boost.popularity.approvaldate";
    private static final String BOOST_BEST_SELLER_VALUE = "solr.boost.popularity.bestseller";
    private static final String BOOST_CUT_SIZES_VALUE = "solr.sort.popularity.boost.cutSizes";
    private static final double DEFAULT_BOOST_VALUE_FOR_SEASON_CODE = 1.3;

    private static final double DEFAULT_BOOST_VALUE_FOR_NEW_ARRIVAL = 1.2;
    private static final double DEFAULT_BOOST_VALUE_FOR_BEST_SELLER = 1.2;
    private static final double DEFAULT_BOOST_VALUE_FOR_CUT_SIZES = 1.3;
    private static final int DEFAULT_POPULARITY_RANK_DAYS = 10;
    private FieldNameProvider fieldNameProvider;

    /**
     * The commerce stock service.
     */
    @Resource
    private CommerceStockService commerceStockService;

    /**
     * The base store service.
     */
    @Resource
    private BaseStoreService baseStoreService;

    @Resource(name = "stockService")
    private StockService stockService;

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        try {
            return this.getPopularity((ApparelStyleVariantProductModel) model, indexedProperty);
        } catch (final ParseException e) {
            LOG.error(ExceptionUtils.getFullStackTrace(e));
        }
        return Collections.emptyList();
    }

    /**
     * Calculates the popularity rank on the basis of season codes, new arrival and best seller products.
     *
     * @param apparelModel    {@link ApparelStyleVariantProductModel}.
     * @param indexedProperty {@link IndexedProperty}.
     * @return fieldValues collection of {@link FieldValue}.
     * @throws ParseException {@link ParseException}.
     */
    private Collection<FieldValue> getPopularity(final ApparelStyleVariantProductModel apparelModel, final IndexedProperty indexedProperty)
            throws ParseException {
        final Collection<FieldValue> fieldValues = new ArrayList<>();
        LOG.debug("Calculating Popularity for Product: " + apparelModel.getCode());
        if (null != apparelModel.getPopularityRank() && !apparelModel.getPopularityRank().isEmpty()) {

            final LocalDate endDate = LocalDate.now().plusDays(1);
            final LocalDate startDate = endDate.minusDays((Config.getInt(POPULARITY_RANK_DAYS, DEFAULT_POPULARITY_RANK_DAYS))).minusDays(1);
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YY_MM_DD);
            final Map<String, Long> dates = apparelModel.getPopularityRank();

            double popularity = dates.entrySet().stream().filter(Objects::nonNull)
                    .filter(map -> LocalDate.parse(map.getKey(), formatter).isAfter(startDate))
                    .filter(map -> LocalDate.parse(map.getKey(), formatter).isBefore(endDate)).mapToDouble(Entry::getValue).sum();

            LOG.debug("Basic Popularity: " + popularity);
            popularity = this.getPopularityForForCutSizes(apparelModel, popularity);
            LOG.debug("Popularity With Cut Sizes: " + popularity);
            popularity = this.getPopularityForMatchedSeasonCode(apparelModel, popularity);
            LOG.debug("Popularity With Season Code: " + popularity);
            popularity = this.updatePopularityForBestSeller(apparelModel, popularity);
            LOG.debug("Popularity With Best Seller: " + popularity);
            popularity = this.getPopularityForNewArrival(apparelModel, popularity);
            LOG.debug("Popularity With New Arrival: " + popularity);
            fieldValues.addAll(createFieldValue(popularity, indexedProperty));
        }
        return fieldValues;
    }

    /**
     * Calculate new popularity for product whom have size variant's stocks greater than 50%.
     *
     * @param apparelModel {@link ApparelStyleVariantProductModel}.
     * @param popularity   cumulated popularity till now.
     * @return new calculated popularity.
     */
    private double getPopularityForForCutSizes(final ApparelStyleVariantProductModel apparelModel, double popularity) {
        final Collection<VariantProductModel> variants = apparelModel.getVariants();
        long count = variants.stream().filter(Objects::nonNull).filter(v -> this.getStock(v) > 0).count();
        final double boostValue = Config.getDouble(BOOST_CUT_SIZES_VALUE, DEFAULT_BOOST_VALUE_FOR_CUT_SIZES);
        return (CollectionUtils.isNotEmpty(variants) && (count > (variants.size() / 2.0))) ? (popularity * boostValue) : popularity;
    }

    /**
     * If product the eligible for best seller then product popularity will be boost by multiplying boosting value from HAC configurable
     * {@link PopularityRankValueProvider#BOOST_BEST_SELLER_VALUE}.
     *
     * @param apparelModel     {@link ApparelStyleVariantProductModel}.
     * @param actualPopularity original product popularity.
     * @return new product popularity.
     */
    private double updatePopularityForBestSeller(final ApparelStyleVariantProductModel apparelModel, final double actualPopularity) {
        final double boostValue = Config.getDouble(BOOST_BEST_SELLER_VALUE, DEFAULT_BOOST_VALUE_FOR_BEST_SELLER);
        return (apparelModel.getBestSeller() != null && apparelModel.getBestSeller()) ? actualPopularity * boostValue : actualPopularity;
    }

    /**
     * Gets the popularity for products which have matching season code with listed season codes in
     * {@link PopularityRankValueProvider#SEASON_CODE}.
     *
     * @param apparelModel       {@link ApparelStyleVariantProductModel}.
     * @param originalPopularity original popularity.
     * @return new popularity rank.
     */
    private double getPopularityForMatchedSeasonCode(ApparelStyleVariantProductModel apparelModel, double originalPopularity) {
        double popularity = originalPopularity;
        final String seasonCode = apparelModel.getBaseProduct().getSeasonCode();
        if (StringUtils.isNotEmpty(Config.getParameter(SEASON_CODE)) && StringUtils.isNotEmpty(seasonCode)) {
            boolean flag = Stream.of(Config.getParameter(SEASON_CODE).split(",")).anyMatch(seasonCode::contains);
            popularity = flag ? popularity * Config.getDouble(BOOST_SEASON_CODE_VALUE, DEFAULT_BOOST_VALUE_FOR_SEASON_CODE) : popularity;
        }
        return popularity;
    }

    /**
     * Gets new value for popularity for new arrival products.
     *
     * @param apparelModel       {@link ApparelStyleVariantProductModel}.
     * @param originalPopularity current popularity.
     * @return new updated popularity.
     * @throws ParseException {@link ParseException}.
     */
    private double getPopularityForNewArrival(final ApparelStyleVariantProductModel apparelModel, final double originalPopularity)
            throws ParseException {
        double popularity = originalPopularity;
        final LocalDate localDateTo = LocalDate.now().plusDays(1);
        final LocalDate localDateFrom = localDateTo.minusDays((Config.getInt(POPULARITY_APPROVAL_DAYS, DEFAULT_POPULARITY_APPROVAL_DAYS)))
                .minusDays(1);
        final Date date = ((ApparelProductModel) apparelModel.getBaseProduct()).getApprovalDate();
        if (date != null) {
            final LocalDate dateEntry = LocalDate.parse(DateFormatUtils.format(date, DD_MM_YYYY), DateTimeFormatter.ofPattern(DD_MM_YYYY));
            if (dateEntry != null && dateEntry.isAfter(localDateFrom) && dateEntry.isBefore(localDateTo)) {
                popularity = popularity * Config.getDouble(BOOST_APPROVAL_DATE_VALUE, DEFAULT_BOOST_VALUE_FOR_NEW_ARRIVAL);
            }
        }

        return popularity;
    }

    /**
     * @param popularity
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final double popularity, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, popularity));
        }
        return fieldValues;
    }

    /**
     * Gets the stocks for the {@link ProductModel}.
     *
     * @param sizeVariantProductModel {@link ProductModel}.
     * @return stock for size variant.
     */
    private int getStock(final ProductModel sizeVariantProductModel) {
        int stock = 0;
        try {
            stock = stockService.getTotalStockLevelAmount(sizeVariantProductModel);
        } catch (final Exception e) {
            LOG.debug(e);
        }
        return stock;
    }

    /**
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * @param fieldNameProvider the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    /**
     * @return the commerceStockService
     */
    public CommerceStockService getCommerceStockService() {
        return commerceStockService;
    }

    /**
     * @param commerceStockService the commerceStockService to set
     */
    public void setCommerceStockService(final CommerceStockService commerceStockService) {
        this.commerceStockService = commerceStockService;
    }

    /**
     * @return the baseStoreService
     */
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * @param baseStoreService the baseStoreService to set
     */
    public void setBaseStoreService(final BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

}
