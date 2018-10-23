/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.crm.service.SSLCustomerService;

/**
 * Job to populate verifiedForCod attribute of customer if one has completed at least one order without any returns within a window.
 *
 * @author tejsharma
 *
 */
public class SSLCustomerVerifiedForCodPopulatingJob extends AbstractJobPerformable<CronJobModel> {
    /**
     * Class Logger.
     */
    private static final Logger LOG = Logger.getLogger(SSLCustomerVerifiedForCodPopulatingJob.class);

    @Resource(name = "sslDefaultCustomerService")
    SSLCustomerService sslDefaultCustomerService;

    /**
     *
     */
    @Override
    public PerformResult perform(final CronJobModel arg0) {
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        final int returnWinInclFulfillment = Config.getInt(SslCoreConstants.RETURN_WIN_CLOSING_PERIOD_PARAM, 20);
        final int durForOrdersToBeChecked = Config.getInt(SslCoreConstants.ORDER_WIN_FOR_COD_VERIFICATION, 365);
        final int codVerificationStrategy = Config.getInt(SslCoreConstants.COD_VERIFICATION_STRATEGY, 1);

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * returnWinInclFulfillment);
        final Date ordersUpto = cal.getTime();
        cal.add(Calendar.DATE, -1 * durForOrdersToBeChecked);
        final Date ordersFrom = cal.getTime();
        boolean success = true;

        if (1 == codVerificationStrategy) {
            final int rtvCount = Config.getInt(SslCoreConstants.COD_VERIFICATION_RTV_COUNT, 2);
            executeStepOne(ordersFrom, ordersUpto, rtvCount);
            executeStepTwo(ordersFrom, ordersUpto, rtvCount);
        } else {
            success = success && processUnverifiedCustomers(ordersFrom, ordersUpto);
            success = success && processVerifiedCustomers(ordersFrom, ordersUpto);
        }

        if (!success) {
            result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }

        return result;
    }

    /**
     * @param ordersFrom
     * @param ordersUpto
     * @param rtvCount
     */
    private void executeStepOne(final Date ordersFrom, final Date ordersUpto, final int rtvCount) {
        final List<CustomerModel> customersWithNConsecutiveRTVOrders = sslDefaultCustomerService
                .getCustomersWithNConsecutiveRTVOrder(ordersFrom, ordersUpto, rtvCount);
        if (CollectionUtils.isNotEmpty(customersWithNConsecutiveRTVOrders)) {
            LOG.info("COD Verification Step 1. Found customers: " + customersWithNConsecutiveRTVOrders.size());
            logListOfItems(customersWithNConsecutiveRTVOrders);
            updateVerifiedForCodForCustomers(customersWithNConsecutiveRTVOrders, Boolean.FALSE);
        } else {
            LOG.info("COD Verification Step 1. No customers found.");
        }
    }

    /**
     * @param ordersFrom
     * @param ordersUpto
     * @param rtvCount
     */
    private void executeStepTwo(final Date ordersFrom, final Date ordersUpto, final int rtvCount) {
        final int successPercentage = Config.getInt(SslCoreConstants.COD_VERIFICATION_SUCCESS_PERCENTAGE, 10);
        final List<CustomerModel> customersWithMoreDeliveredShipments = sslDefaultCustomerService
                .getCustomersWithMoreDeliveredShipments(ordersFrom, ordersUpto, successPercentage, rtvCount);
        if (CollectionUtils.isNotEmpty(customersWithMoreDeliveredShipments)) {
            LOG.info("COD Verification Step 2 Phase 1. Found customers: " + customersWithMoreDeliveredShipments.size());
            logListOfItems(customersWithMoreDeliveredShipments);
            updateVerifiedForCodForCustomers(customersWithMoreDeliveredShipments, Boolean.TRUE);
        } else {
            LOG.info("COD Verification Step 2 Phase 1. No customers found.");
        }

        final List<CustomerModel> customersWithMoreRTVShipments = sslDefaultCustomerService.getCustomersWithMoreRTVShipments(ordersFrom,
                ordersUpto, successPercentage, rtvCount);
        if (CollectionUtils.isNotEmpty(customersWithMoreRTVShipments)) {
            LOG.info("COD Verification Step 2 Phase 2. Found customers: " + customersWithMoreRTVShipments.size());
            logListOfItems(customersWithMoreRTVShipments);
            updateVerifiedForCodForCustomers(customersWithMoreRTVShipments, Boolean.FALSE);
        } else {
            LOG.info("COD Verification Step 2 Phase 2. No customers found.");
        }
    }

    /**
     * @param ordersFrom
     * @param ordersUpto
     */
    private boolean processVerifiedCustomers(final Date ordersFrom, final Date ordersUpto) {
        final List<CustomerModel> verifiedCustomersWithoutSuccOrder = sslDefaultCustomerService
                .getVerifiedCustomersWithoutSuccOrder(ordersFrom, ordersUpto);
        boolean retVal = true;
        if (verifiedCustomersWithoutSuccOrder == null) {
            LOG.error(
                    "NULL was returned from flexible search service when searching for verified customers without successful order/s in specified period.");
            retVal = false;
        } else {
            updateVerifiedForCodForCustomers(verifiedCustomersWithoutSuccOrder, Boolean.FALSE);
        }
        return retVal;
    }

    /**
     * @param ordersFrom
     * @param ordersUpto
     */
    private boolean processUnverifiedCustomers(final Date ordersFrom, final Date ordersUpto) {
        final List<CustomerModel> unverifiedCustomersWithSuccOrder = sslDefaultCustomerService
                .getUnverifiedCustomerWithSuccOrder(ordersFrom, ordersUpto);
        boolean retVal = true;
        if (unverifiedCustomersWithSuccOrder == null) {
            LOG.error(
                    "NULL was returned from flexible search service when searching for not verified customers with successful order/s in specified period.");
            retVal = false;
        } else {
            updateVerifiedForCodForCustomers(unverifiedCustomersWithSuccOrder, Boolean.TRUE);
        }
        return retVal;
    }

    /**
     * @param customers
     * @param verifiedForCOD
     */
    private void updateVerifiedForCodForCustomers(final List<CustomerModel> customers, final Boolean verifiedForCOD) {
        if (CollectionUtils.isEmpty(customers)) {
            LOG.info("No customer updated!.");
        }
        for (final CustomerModel customer : customers) {
            final Date currentDate = new Date();
            customer.setVerifiedForCOD(verifiedForCOD);
            customer.setVerificationDate(currentDate);
            try {
                modelService.save(customer);
            } catch (final ModelSavingException mse) {
                LOG.error("Couldn't save customer with uid: " + customer.getUid());
            }
        }
    }
    
    private static void logListOfItems(List items) {
        if (LOG.isDebugEnabled()) {
            items.stream().forEach(e -> LOG.debug(((ItemModel)e).getPk().toString() + "-"));
        }
    }

}