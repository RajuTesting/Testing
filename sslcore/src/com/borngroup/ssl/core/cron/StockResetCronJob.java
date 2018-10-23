/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;


/**
 * Class that resets the stock levels for online damaged and online suspense counts for all the stock levels. This
 * should be run once a day nightly.
 */
public class StockResetCronJob extends AbstractJobPerformable<CronJobModel>
{
    public static final Logger    LOG = Logger.getLogger(StockResetCronJob.class);
    @Resource(name = "modelService")
    private ModelService          modelService;
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService searchService;

    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
     * )
     */
    @Override
    public PerformResult perform(final CronJobModel cronModel)
    {
        final List<StockLevelModel> stockLevels = getStockLevels();
        for (final StockLevelModel stock : stockLevels)
        {
            stock.setOnlineSuspense(0);
        }
        LOG.info(String.format("STOCK RESET: Reset suspense count for %s stocklevels", Integer.valueOf(stockLevels.size())));
        modelService.saveAll(stockLevels);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @return list of stock levels that have suspense count.
     */
    private List<StockLevelModel> getStockLevels()
    {
        return searchService
                .<StockLevelModel> search(new FlexibleSearchQueryBuilder()
                        .from(StockLevelModel._TYPECODE, "s")
                        .join(WarehouseModel._TYPECODE, "w", StockLevelModel.WAREHOUSE)
                        .select("s", ItemModel.PK)
                        .select("s", StockLevelModel.ONLINESUSPENSE)
                        .whereEquals("w", WarehouseModel.ENABLEDFORONLINE, Boolean.TRUE)
                        .whereMoreThanEquals("s", StockLevelModel.ONLINESUSPENSE, Boolean.TRUE)
                        .build())
                .getResult();
    }
}
