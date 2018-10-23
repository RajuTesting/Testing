/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.sterling.util.SSLSterlingUtil;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;

/**
 *
 */
public class StartOrderProcessCronJob extends AbstractJobPerformable<CronJobModel> {
    public static final Logger LOG = Logger.getLogger(StartOrderProcessCronJob.class);
    @Resource(name = "modelService")
    private ModelService modelService;
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService searchService;
    @Resource(name = "businessProcessService")
    private BusinessProcessService processService;
    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
     */
    @Override
    public PerformResult perform(final CronJobModel arg0) {
        final List<OrderModel> orders = getEmptyStatusOrders();
        for (final OrderModel order : orders) {
            modelService.refresh(order);

            if (SSLSterlingUtil.isPickupOrder(order)) {
                continue;
            }

            if (!order.getOrderProcess().isEmpty()) {
                LOG.warn("Order Processing CRON: Order[" + order.getCode() + "] has empty status but order processes exist!!");
                continue;
            }
            BaseStoreModel store = order.getStore();
            if (store == null) {
                store = baseStoreService.getCurrentBaseStore();
            }

            if (store == null) {
                LOG.error("Order Processing CRON: NO BASE STORE DEFINED FOR Order[" + order.getCode() + "]");
                continue;
            }
            final String processDefinitionName = store.getSubmitOrderProcessCode();
            final String processCode = processDefinitionName + "-" + order.getCode() + "-" + System.currentTimeMillis();
            final OrderProcessModel process = processService.<OrderProcessModel> createProcess(processCode, processDefinitionName);
            process.setOrder(order);
            modelService.save(process);
            processService.startProcess(process);
            LOG.info("Order Processing CRON: Successfully started processing for order[" + order.getCode() + "] - " + process.getCode());
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @return list of orders that have empty statuses
     */
    private List<OrderModel> getEmptyStatusOrders() {
        final Calendar cal = Calendar.getInstance();
        cal.set(2015, 10, 26, 0, 0, 0);
        final long createDate = cal.getTime().getTime();
        return searchService.<OrderModel> search(
                new FlexibleSearchQueryBuilder().from(OrderModel._TYPECODE, "o")
                        .join(OrderStatus._TYPECODE, "os", OrderModel.STATUS, FlexibleSearchQueryBuilder.JoinType.LEFT)
                        .select("o", ItemModel.PK).whereNull("o", OrderModel.VERSIONID)
                        .whereMoreThanEqualsDate("o", OrderModel.CREATIONTIME, createDate).whereNull("os", ItemModel.PK)
                        .order("o", OrderModel.CREATIONTIME, true).build()).getResult();
    }
}
