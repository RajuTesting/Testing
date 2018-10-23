/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.solrfacetsearch.indexer.cron.SolrExtIndexerJob;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SslSolrExtIndexerCronJobModel;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Nagarro-Dev
 *
 */
public class SslSolrExtIndexerCronJob extends SolrExtIndexerJob<SslSolrExtIndexerCronJobModel> {

    private static final Logger LOG = Logger.getLogger(SslSolrExtIndexerCronJob.class);

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
     */
    @Override
    public PerformResult perform(final SslSolrExtIndexerCronJobModel cronJob) {
        LOG.info("Starting SslSolrExtIndexer CronJob : " + new Date());

        final PerformResult result = super.perform(cronJob);
        if (null != result) {
            LOG.info(" SslSolrExtIndexerCronJob Result: " + result.getResult() + " Status: " + result.getStatus());
            if (result.getResult().equals(CronJobResult.SUCCESS)) {
                cronJob.setLastSuccessfullyExecutedTime(new Date());
                LOG.info("Updated Last successfully updated start time : " + new Date());
                modelService.save(cronJob);
            }
        }
        return result;
    }

}
