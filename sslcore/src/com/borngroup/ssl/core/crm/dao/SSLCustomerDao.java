/**
 *
 */
package com.borngroup.ssl.core.crm.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;
import java.util.List;

/**
 * @author S52880
 *
 */
public interface SSLCustomerDao {

    /**
     * @param parameter - Mobile no or EmailId.
     * @return list of customer by mobile or email
     */
    List<CustomerModel> getCustomerByMobileOrEmail(String parameter);

    /**
     * Fetches list of <b>Registered</b> Customers with given mobile number.
     * Returns empty list in exception cases.
     * @param mobileNo
     * @return list of customers
     *
     */
    List<CustomerModel> getCustomerByMobileAndEmail(String mobileNo);

    /**
     * @param orderNo
     * @return list of customer by order number
     */
    List<CustomerModel> getCustomerByOrderNo(String orderNo);

    /**
     * @return list of customers for crm
     */
    List<CustomerModel> getCustomersForCRM();

    /**
     * @return list of customers with failed wallet creation status.
     */
    List<CustomerModel> getCustomersWithFailedWalletCreationStatus();

    /**
     * Returns Number of Guest User with passed email id having COD Verified flag set
     *
     * @param emailID
     * @return int
     */
    int getNumberOfGuestUsersWithCODVerifiedFlag(final String emailID);

    /**
     * Returns List of unverified customers who are not verified for COD and have completed an order between the dates passed as argument.
     *
     * @param ordersFrom : Start date, orders placed on or after this date will be considered for checking successful order/s.
     * @param ordersUpto : Last date, orders placed on or before this date will be considered for checking successful order/s.
     * @return List of customers
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

    /**
     * Method for finding customers by passing customers pks's
     * @param customerPks
     * @return {@link List of CustomerModel}
     */
    List<CustomerModel> getCustomerByPk(List<String> customerPks);

}
