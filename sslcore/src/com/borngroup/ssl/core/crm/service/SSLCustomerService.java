package com.borngroup.ssl.core.crm.service;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.PasswordMismatchException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Date;
import java.util.List;

/**
 * @author DHEERAJ KUMAR
 * @version 1.0
 * @since 01-June-2016
 */

public interface SSLCustomerService {

    /**
     * @param parameter - Mobile no or EmailId.
     * @return list of customer by mobile or email
     */
    List<CustomerData> getCustomerByMobileOrEmail(String parameter);

    /**
     * @param mobileNo
     * @return list of customer correspond to mobile
     */
    List<CustomerModel> getCustomerByMobileAndEmail(String mobileNo);

    /**
     * @param orderNo
     * @return list of customer by order number
     */
    List<CustomerData> getCustomerByOrderNo(String orderNo);

    /**
     * @return list of customers for crm
     */
    List<CustomerModel> getCustomersForCRM();

    /**
     *
     * @return list of customers with failed wallet creation status.
     */
    List<CustomerModel> getCustomersWithFailedWalletCreationStatus();

    /**
     * @param currentPassword : Customer's current password
     * @param userModel : Current customer model
     * @return true of matches else false
     * @throws PasswordMismatchException : Invalid password
     */
    boolean checkPassword(String currentPassword, UserModel userModel) throws PasswordMismatchException;

    /**
     * Gets number of Guest Customers with passed email id having COD verified flag set.
     *
     * @param emailID
     * @return
     */
    int getNumberOfGuestUsersWithCODVerifiedFlag(final String emailID);

    /**
     * Returns List of unverified customers who are not verified for COD and have completed an order before the date passed as argument.
     *
     * @param ordersFrom : Start date, orders placed on or after this date will be considered for checking successful order/s.
     * @param ordersUpto : Last date, orders placed on or before this date will be considered for checking successful order/s.
     * @return List of Customers.
     */
    List<CustomerModel> getUnverifiedCustomerWithSuccOrder(Date ordersFrom, Date ordersUpto);

    /**
     * Returns List of verified customers who are verified for COD and but don't have completed order/s between the dates passed as
     * argument.
     *
     * @param ordersFrom : Start date, orders placed on or after this date will be considered for checking successful order/s.
     * @param ordersUpto : Last date, orders placed on or before this date will be considered for checking successful order/s.
     * @return List of customers
     */
    List<CustomerModel> getVerifiedCustomersWithoutSuccOrder(Date ordersFrom, Date ordersUpto);

    /**
     * @param ordersFrom
     * @param ordersUpto
     * @param rtvCount
     * @return List of customers with last n consecutive orders as RTV
     */
    List<CustomerModel> getCustomersWithNConsecutiveRTVOrder(Date ordersFrom, Date ordersUpto, int rtvCount);

    /**
     * @param ordersFrom
     * @param ordersUpto
     * @param successPercentage
     * @param rtvCount
     * @return List of customers with percentage of delivered shipments with RTV shipments less than successPercentage
     */
    List<CustomerModel> getCustomersWithMoreRTVShipments(Date ordersFrom, Date ordersUpto, int successPercentage, int rtvCount);

    /**
     * @param ordersFrom
     * @param ordersUpto
     * @param successPercentage
     * @param rtvCount
     * @return List of customers with percentage of delivered shipments with RTV shipments more than successPercentage
     */
    List<CustomerModel> getCustomersWithMoreDeliveredShipments(Date ordersFrom, Date ordersUpto, int successPercentage, int rtvCount);
}
