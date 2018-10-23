/**
 *
 */
package com.borngroup.ssl.core.event;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.events.BankDetailsOTPEvent;
import com.borngroup.ssl.core.services.ODCReturnService;
import com.borngroup.ssl.core.sms.service.SslCustomerTouchPointService;

/**
 * @author tejsharma
 *
 */
public class BankDetailsOTPEventListener extends AbstractSiteEventListener<BankDetailsOTPEvent> {

    private static final Logger LOG = Logger.getLogger(BankDetailsOTPEventListener.class);

    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    private ODCReturnService odcReturnService;

    /**
     * @return the odcReturnService
     */
    public ODCReturnService getOdcReturnService() {
        return odcReturnService;
    }

    /**
     * @param odcReturnService the odcReturnService to set
     */
    public void setOdcReturnService(final ODCReturnService odcReturnService) {
        this.odcReturnService = odcReturnService;
    }

    /**
     * SslCustomerTouchPointService instance.
     */
    private SslCustomerTouchPointService sslCustomerTouchPointService;

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    /**
     * @return the modelService
     */
    protected ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * Get instance of SslCustomerTouchPointService.
     *
     * @return sslCustomerTouchPointService - SslCustomerTouchPointService instance
     */
    public SslCustomerTouchPointService getSslCustomerTouchPointService() {
        return sslCustomerTouchPointService;
    }

    /**
     * Set instance of SslCustomerTouchPointService.
     *
     * @param sslCustomerTouchPointService1 - SslCustomerTouchPointService instance.
     */
    public void setSslCustomerTouchPointService(final SslCustomerTouchPointService sslCustomerTouchPointService1) {
        this.sslCustomerTouchPointService = sslCustomerTouchPointService1;
    }

    @Override
    protected void onSiteEvent(final BankDetailsOTPEvent event) {
        LOG.debug(String.format("Attempting to start SendBankDetailsOtpEmailProcess for order %s", event.getProcess().getOrder().getCode()));
        String retReqNum = null;
        // Here we would only have one return request number in set.
        final Iterator<String> it = event.getProcess().getReturnReqNumber().iterator();
        retReqNum = it.next();
        final OrderModel orderModel = event.getProcess().getOrder();
        final ReturnRequestModel returnRequest = odcReturnService.getReturnRequestByRMA(retReqNum);
        if (returnRequest != null) {

            // Start process to send otp over email.
            final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                    "sendBankDetailsOtpEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                    "sendBankDetailsOtpEmailProcess");
            orderProcessModel.setOrder(orderModel);
            orderProcessModel.setReturnReqNumber(new HashSet<String>(event.getProcess().getReturnReqNumber()));
            getModelService().save(orderProcessModel);
            getBusinessProcessService().startProcess(orderProcessModel);
            // Send OTP to mobile.
            final String otpStr = returnRequest.getBankDetailsOTP();
            final String message = "Your Otp to add/edit bank details is %s";
            getSslCustomerTouchPointService().sendAddEditBankDetailsOTPSms(orderModel, message, otpStr);
        } else {
            // Can we throw error here so that caller knows OTP was not sent ?
            LOG.error(String.format("Unknown return request %s for order with code %s!", retReqNum, orderModel.getCode()));
        }
    }

    @Override
    protected boolean shouldHandleEvent(final BankDetailsOTPEvent event) {
        final OrderModel order = event.getProcess().getOrder();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
        final BaseSiteModel site = order.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        validateIfSingleResult(event.getProcess().getReturnReqNumber(), String.format(
                "Return request id not set in BankDetailsOTPEvent for order %s", event.getProcess().getOrder().getCode()), String.format(
                "Multiple return request ids on OrderProcessModel are set while otp is requested on BankDetailsOTPEvent for order %s",
                event.getProcess().getOrder().getCode()));
        return SiteChannel.B2C.equals(site.getChannel());
    }
}
