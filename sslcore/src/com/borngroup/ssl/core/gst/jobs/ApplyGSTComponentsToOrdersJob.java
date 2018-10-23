/**
 *
 */
package com.borngroup.ssl.core.gst.jobs;

import de.hybris.platform.core.Registry;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;

/**
 * @author arunverma
 *
 */
public class ApplyGSTComponentsToOrdersJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(ApplyGSTComponentsToOrdersJob.class);
    private FlexibleSearchService flexibleSearchService;
    private ModelService modelService;
    private CoreGSTTaxCalculationService coreGSTTaxCalculationService;

    @Override
    public PerformResult perform(final CronJobModel arg0) {
        LOG.info("Inside Apply GST Components To Order Job");
        final int packageSize = Integer.parseInt(Config.getParameter("gst.thread.batchSize"));
        final int fromIndex = 0;
        final int toIndex = 3;

        final long startTime = System.currentTimeMillis();
        final String threads = StringUtils.isNotEmpty(Config.getParameter("gst.number.threads")) ? Config
                .getParameter("gst.number.threads") : "5";
        final int noOfThreads = Integer.parseInt(threads);

        final ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);

        final String consignmentPks = StringUtils.isNotEmpty(Config.getParameter("gst.consignments.pk")) ? Config
                .getParameter("gst.consignments.pk") : "";
        final String query = "SELECT {pk} from {Consignment} where {PK} IN (" + consignmentPks + ")";

        final FlexibleSearchQuery flexiQuery = new FlexibleSearchQuery(query);
        flexibleSearchService = (FlexibleSearchService) Registry.getApplicationContext().getBean("flexibleSearchService");
        final SearchResult<ConsignmentModel> results = flexibleSearchService.search(flexiQuery);
        final List<ConsignmentModel> consignmentModels = results.getResult();

        // final List<ConsignmentModel> callingConsignmentModels = consignmentModels.subList(fromIndex, toIndex);
        for (final ConsignmentModel consignment : consignmentModels) {
            executorService.execute(executeConsignments(consignment));
        }

        executorService.shutdown();
        boolean isThreadExecuted = false;
        // Waiting for thread to execute their task.
        while (!executorService.isTerminated()) {
            isThreadExecuted = true;
        }

        final long stopTime = System.currentTimeMillis(); // added for measuring the performance
        final long totalTime = stopTime - startTime; // added for measuring the performance

        LOG.info("Finish time for calculating tax for consignments: " + totalTime); // added for measuring the performance

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private Runnable executeConsignments(final ConsignmentModel consignment) {
        return new Runnable() {
            @Override
            public void run() {
                modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
                final ConsignmentModel consignmentModel = modelService.get(consignment.getPk());
                coreGSTTaxCalculationService = (CoreGSTTaxCalculationService) Registry.getApplicationContext().getBean(
                        "coreGSTTaxCalculationService");
                coreGSTTaxCalculationService.calculateGSTTaxOnConsignment(consignmentModel);

            }
        };
    }

}
