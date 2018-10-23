/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.populators;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider.FieldType;
import de.hybris.platform.solrfacetsearch.search.BoostField;
import de.hybris.platform.solrfacetsearch.search.BoostField.BoostType;
import de.hybris.platform.solrfacetsearch.search.CoupledQueryField;
import de.hybris.platform.solrfacetsearch.search.FreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.search.FreeTextQueryBuilderFactory;
import de.hybris.platform.solrfacetsearch.search.QueryField;
import de.hybris.platform.solrfacetsearch.search.RawQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SearchQuery.Operator;
import de.hybris.platform.solrfacetsearch.search.impl.SearchQueryConverterData;
import de.hybris.platform.solrfacetsearch.search.impl.populators.AbstractFacetSearchQueryPopulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author shashank2484
 *
 */
public class SslBasicSolrQueryPopulator extends AbstractFacetSearchQueryPopulator {
    public static final String ALL_QUERY = "*:*";
    public static final String SEPARATOR = ",";
    private FreeTextQueryBuilderFactory freeTextQueryBuilderFactory;

    public FreeTextQueryBuilderFactory getFreeTextQueryBuilderFactory() {
        return this.freeTextQueryBuilderFactory;
    }

    @Required
    public void setFreeTextQueryBuilderFactory(final FreeTextQueryBuilderFactory freeTextQueryBuilderFactory) {
        this.freeTextQueryBuilderFactory = freeTextQueryBuilderFactory;
    }

    @Override
    public void populate(final SearchQueryConverterData source, final SolrQuery target) throws ConversionException {
        final SearchQuery searchQuery = source.getSearchQuery();
        final ArrayList queries = new ArrayList();
        final List<String> terms = Arrays.asList(searchQuery.getUserQuery().split(" "));
        for (final String term : terms) {
            searchQuery.setUserQuery(term);
            this.addQueryFieldQueries(searchQuery, queries);
            this.addFreeTextQuery(searchQuery, queries);
            this.addRawQueries(searchQuery, queries);
            this.addCoupledFieldQueries(searchQuery, queries);
        }
        final String query = this.buildQuery(searchQuery, queries);
        final String collectiveQuery = this.addBoostFieldQueries(searchQuery, query);
        target.setQuery(collectiveQuery);
    }

    protected void addQueryFieldQueries(final SearchQuery searchQuery, final List<String> queries) {
        final List filteredQueries = searchQuery.getQueries().stream().filter((queryField) -> {
            return !"catalogId".equals(queryField.getField()) && !"catalogVersion".equals(queryField.getField());
        }).collect(Collectors.toList());
        final Iterator arg4 = filteredQueries.iterator();

        while (arg4.hasNext()) {
            final QueryField filteredQuery = (QueryField) arg4.next();
            final String query = this.convertQueryField(searchQuery, filteredQuery);
            queries.add(query);
        }

    }

    protected void addFreeTextQuery(final SearchQuery searchQuery, final List<String> queries) {
        final FreeTextQueryBuilder freeTextQueryBuilder = this.getFreeTextQueryBuilderFactory()
                .createQueryBuilder(searchQuery);
        final String freeTextQuery = freeTextQueryBuilder.buildQuery(searchQuery);
        if (StringUtils.isNotBlank(freeTextQuery)) {
            final StringBuilder query = new StringBuilder();
            query.append("_query_:\"");
            query.append(ClientUtils.escapeQueryChars(freeTextQuery));
            query.append('\"');
            queries.add(query.toString());
        }

    }

    protected void addRawQueries(final SearchQuery searchQuery, final List<String> queries) {
        final Iterator arg3 = searchQuery.getRawQueries().iterator();

        while (arg3.hasNext()) {
            final RawQuery rawQuery = (RawQuery) arg3.next();
            final String query = convertRawQuery(searchQuery, rawQuery);
            queries.add(query);
        }

    }

    protected String addBoostFieldQueries(final SearchQuery searchQuery, final String query) {
        final ArrayList multiplicativeBoosts = new ArrayList();
        final ArrayList additiveBoosts = new ArrayList();
        Iterator additiveBoostsQuery = searchQuery.getBoosts().iterator();

        StringBuilder collectiveQuery;
        while (additiveBoostsQuery.hasNext()) {
            final BoostField multiplicativeBoostsQuery = (BoostField) additiveBoostsQuery.next();
            collectiveQuery = new StringBuilder();
            collectiveQuery.append("map(query({!v=\'");
            final QueryField boostQuery = new QueryField(multiplicativeBoostsQuery.getField(), Operator.OR,
                    multiplicativeBoostsQuery.getQueryOperator(),
                    new String[] { String.valueOf(multiplicativeBoostsQuery.getValue()) });
            final String boostQuery1 = this.convertQueryField(searchQuery, boostQuery);
            collectiveQuery.append(ClientUtils.escapeQueryChars(boostQuery1));
            if (BoostType.MULTIPLICATIVE == multiplicativeBoostsQuery.getBoostType()) {
                collectiveQuery.append("\'}),0,0,1,");
                collectiveQuery.append(multiplicativeBoostsQuery.getBoostValue());
                collectiveQuery.append(')');
                multiplicativeBoosts.add(collectiveQuery.toString());
            } else {
                collectiveQuery.append("\'}),0,0,0,");
                collectiveQuery.append(multiplicativeBoostsQuery.getBoostValue());
                collectiveQuery.append(')');
                additiveBoosts.add(collectiveQuery.toString());
            }
        }

        additiveBoostsQuery = searchQuery.getBoostFields().iterator();

        while (true) {
            List collectiveQuery1;
            do {
                if (!additiveBoostsQuery.hasNext()) {
                    final String multiplicativeBoostsQuery2 = this.generateMultiplicativeBoosts(multiplicativeBoosts);
                    final String additiveBoostsQuery1 = this.generateAdditiveBoosts(additiveBoosts);
                    collectiveQuery = new StringBuilder();
                    collectiveQuery.append(multiplicativeBoostsQuery2);
                    collectiveQuery.append(query);
                    collectiveQuery.append(additiveBoostsQuery1);
                    return collectiveQuery.toString();
                }

                final QueryField multiplicativeBoostsQuery1 = (QueryField) additiveBoostsQuery.next();
                collectiveQuery1 = this.convertLegacyBoostField(searchQuery, multiplicativeBoostsQuery1);
            } while (!CollectionUtils.isNotEmpty(collectiveQuery1));

            final Iterator boostQuery3 = collectiveQuery1.iterator();

            while (boostQuery3.hasNext()) {
                final String boostQuery2 = (String) boostQuery3.next();
                final String[] queryParts = boostQuery2.split("\\^");
                if (queryParts.length == 2) {
                    final StringBuilder boost = new StringBuilder();
                    boost.append("map(query({!v=\'");
                    boost.append(ClientUtils.escapeQueryChars(queryParts[0]));
                    boost.append("\'}),0,0,0,");
                    boost.append(queryParts[1]);
                    boost.append(')');
                    additiveBoosts.add(boost.toString());
                }
            }
        }
    }

    protected List<String> convertLegacyBoostField(final SearchQuery searchQuery, final QueryField queryField) {
        final String convertedField = getFieldNameTranslator().translate(searchQuery, queryField.getField(),
                FieldType.INDEX);
        final ArrayList convertedBoostQueries = new ArrayList();
        if (CollectionUtils.isNotEmpty(queryField.getValues())) {
            final Iterator arg5 = queryField.getValues().iterator();

            while (arg5.hasNext()) {
                final String value = (String) arg5.next();
                final StringBuilder query = new StringBuilder();
                query.append(convertedField);
                query.append(':');
                query.append(value);
                convertedBoostQueries.add(query.toString());
            }
        }

        return convertedBoostQueries;
    }

    protected String generateMultiplicativeBoosts(final List<String> boostFields) {
        if (CollectionUtils.isNotEmpty(boostFields)) {
            final StringBuilder query = new StringBuilder();
            query.append("{!boost b=\"product(");
            query.append(StringUtils.join(boostFields, ","));
            query.append(")\"} ");
            return query.toString();
        } else {
            return "";
        }
    }

    protected String generateAdditiveBoosts(final List<String> boostFields) {
        if (CollectionUtils.isNotEmpty(boostFields)) {
            final StringBuilder query = new StringBuilder();
            query.append(" AND ({!func v=\"sum(");
            query.append(StringUtils.join(boostFields, ","));
            query.append(")\"})");
            return query.toString();
        } else {
            return "";
        }
    }

    protected void addCoupledFieldQueries(final SearchQuery searchQuery, final List<String> queries) {
        final HashMap couples = new HashMap();
        final HashMap operatorMapping = new HashMap();
        Iterator arg5 = searchQuery.getCoupledFields().iterator();

        while (arg5.hasNext()) {
            final CoupledQueryField coupleId = (CoupledQueryField) arg5.next();
            final StringBuilder couple = new StringBuilder();
            couple.append('(');
            couple.append(this.convertQueryField(searchQuery, coupleId.getField1()));
            couple.append(coupleId.getInnerCouplingOperator().getName());
            couple.append(this.convertQueryField(searchQuery, coupleId.getField2()));
            couple.append(')');
            Object operator = couples.get(coupleId.getCoupleId());
            if (operator == null) {
                operator = new ArrayList();
            }

            ((List) operator).add(couple.toString());
            couples.put(coupleId.getCoupleId(), operator);
            operatorMapping.put(coupleId.getCoupleId(), coupleId.getOuterCouplingOperator());
        }

        arg5 = couples.keySet().iterator();

        while (arg5.hasNext()) {
            final String coupleId1 = (String) arg5.next();
            final List couple1 = (List) couples.get(coupleId1);
            final Operator operator1 = (Operator) operatorMapping.get(coupleId1);
            final String separator = " " + operator1.getName() + " ";
            final String coupleQuery = StringUtils.join(couple1.toArray(), separator);
            final StringBuilder query = new StringBuilder();
            query.append('(');
            query.append(coupleQuery);
            query.append(')');
            queries.add(query.toString());
        }

    }

    protected String buildQuery(final SearchQuery searchQuery, final List<String> queries) {
        String query;
        if (queries.isEmpty()) {
            query = "*:*";
        } else {
            final String separator = " " + "AND" + " ";
            query = StringUtils.join(queries, separator);
        }

        return query;
    }
}
