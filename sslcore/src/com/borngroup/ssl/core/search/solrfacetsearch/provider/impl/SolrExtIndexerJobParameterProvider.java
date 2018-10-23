/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.indexer.SolrIndexStatisticsProvider;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrExtIndexerCronJobModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SslSolrExtIndexerCronJobModel;
import de.hybris.platform.solrfacetsearch.provider.CronJobAwareParameterProvider;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nagarro-Dev
 *
 */
public class SolrExtIndexerJobParameterProvider implements CronJobAwareParameterProvider {

    private static final Logger LOG = Logger.getLogger(SolrExtIndexerJobParameterProvider.class);
    private static final String LAST_INDEX_TIME = "lastIndexTime";

    @Autowired
    private SolrIndexStatisticsProvider indexStatisticsProvider;

    @Autowired
    private FacetSearchConfigService facetSearchConfigService;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.solrfacetsearch.provider.CronJobAwareParameterProvider#createParameters(de.hybris.platform.cronjob.model.
     * CronJobModel, de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedType)
     */
    @Override
    public Map<String, Object> createParameters(final CronJobModel cronJob, final IndexConfig indexConfig, final IndexedType indexedType) {

        final HashMap parameters = new HashMap();
        try {
            final SslSolrExtIndexerCronJobModel sslSolrExtIndexerCronJob = (SslSolrExtIndexerCronJobModel) cronJob;
            if (null != sslSolrExtIndexerCronJob.getLastSuccessfullyExecutedTime()) {
                parameters.put(LAST_INDEX_TIME, sslSolrExtIndexerCronJob.getLastSuccessfullyExecutedTime());
            }
            if (null == parameters.get(LAST_INDEX_TIME) && cronJob instanceof SolrExtIndexerCronJobModel) {
                final String facetSearchConfigName = ((SslSolrExtIndexerCronJobModel) cronJob).getFacetSearchConfig().getName();
                FacetSearchConfig facetSearchConfig;
                facetSearchConfig = this.facetSearchConfigService.getConfiguration(facetSearchConfigName);
                parameters.put(LAST_INDEX_TIME, this.indexStatisticsProvider.getLastIndexTime(facetSearchConfig, indexedType));
            }
        } catch (final FacetConfigServiceException e) {
            LOG.error("Error while populating query parameters for SolrExtIndexerJob: " + e.getLocalizedMessage());
        }
        return parameters;
    }

}
