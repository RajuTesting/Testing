package com.borngroup.ssl.core.sms.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import com.borngroup.ssl.core.model.PSAppointmentModel;

/**
 * Interface : Customer touch points post order placement.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public interface SslCustomerTouchPointService {

    /**
     * This method used to send order confirmation sms.
     *
     * @param orderProcessModel
     *        - Instance of OrderProcessModel.
     * @param messageFormat
     *        - Instance of String.
     */

    void orderConfirmationSMS(final OrderProcessModel orderProcessModel, final String messageFormat);

    /**
     * This method used to send order shipped sms.
     *
     * @param cnmtProcessModel
     *        - Instance of ConsignmentProcessModel.
     * @param messageFormat
     *        - Instance of String.
     */

    void orderShippedSMS(final ConsignmentProcessModel cnmtProcessModel, final String messageFormat);

    /**
     * This method used to send order delivered sms.
     *
     * @param cnmtProcessModel
     *        - Instance of ConsignmentProcessModel.
     * @param messageFormat
     *        - Instance of String.
     */

    void orderDeliveredSMS(final ConsignmentProcessModel cnmtProcessModel, final String messageFormat);

    /**
     * This method used to send order cancellation sms.
     *
     * @param orderProcessModel
     *        - Instance of OrderProcessModel.
     * @param messageFormat
     *        - Instance of String.
     */

    void orderCancellationSMS(final OrderProcessModel orderProcessModel, final String messageFormat);

    /**
     * This method used to send SMS.
     *
     * @param messageFormat
     *        - Instance of String.
     * @param mobileNo
     *        - Instance of String.
     */

    void sendSMS(final String messageFormat, final String mobileNo);

    /**
     * This method used to send a dynamic type SMS with parameters passed as a dynamic length argument.
     *
     * @param messageFormat
     *        - Instance of String.
     * @param mobileNo
     *        - Instance of String.
     * @param params
     *        - Parameters to format message string.
     */
    void sendDynamicSMS(final String messageFormat, final String mobileNo, Object... params);

    /**
     * This method will send SMS for COD Confirmation order
     *
     * @param orderModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */

    void orderConfirmationSMSForCod(final AbstractOrderModel orderModel, final String messageFormat);

    /**
     * Method will send otp to mobile for add/edit of bank details for a return request.
     *
     * @param order
     * @param messageFormat
     * @param otpStr
     */
    void sendAddEditBankDetailsOTPSms(final AbstractOrderModel order, final String messageFormat, final String otpStr);

    /**
     * This method will send cod verification done sms
     *
     * @param orderModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendCODVerificationSMS(final AbstractOrderModel orderModel, final String messageFormat);

    /**
     * This method will send order billed sms
     *
     * @param orderModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendOrderBilledSMS(final AbstractOrderModel orderModel, final String messageFormat);

    /**
     * This method will send return created sms
     *
     * @param orderModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendReturnCreatedSMS(final AbstractOrderModel orderModel, final String messageFormat);

    /**
     * This method will send return received sms
     *
     * @param returnModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendReturnRecievedSMS(final ReturnRequestModel returnModel, final String messageFormat);

    /**
     * This method will send exchange received sms
     *
     * @param returnModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendExchangeReceivedSMS(final ReturnRequestModel returnModel, final String messageFormat);

    /**
     * This method will send refund initiated sms
     *
     * @param returnModel
     *        - Instance of OrderModel.
     * @param messageFormat
     *        - Instance of String.
     */
    void sendRefundInitiatedSMS(final ReturnRequestModel returnModel, final String messageFormat);

    /**
     * This method will send refund completed sms
     *
     * @param paymentTransactionEntry
     *        - Instance of paymentTransactionEntry.
     *
     */
    void sendRefundCompleteSMS(final PaymentTransactionEntryModel paymentTransactionEntry);

    /**
	 * This method will be triggered when return is done at Store.
	 * 
	 * @param returnModel
	 * @param messageFormat
	 */
    public void sendReturnInStoreSMS(final ReturnRequestModel returnModel, final String messageFormat);

	/**
	 * Method for sending PS booking appointment SMS
	 * @param messageTemp
	 * @param pSAppointment
	 */
	void sendPSBookingAppointmentSMS(String messageTemp,
			PSAppointmentModel pSAppointment);

	/**
	 * Method for sending Link or Delink confirmation SMS
	 * 
	 * @param customerModel
	 * @param key
	 */
	void sendFccCardLinkedOrUnlinkSuccessSMS(final CustomerModel customerModel,
			final String key);
}