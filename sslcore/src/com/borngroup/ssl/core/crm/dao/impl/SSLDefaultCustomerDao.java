package com.borngroup.ssl.core.crm.dao.impl;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.crm.dao.SSLCustomerDao;

/**
 * @author DHEERAJ KUMAR
 * @version 1.0
 * @since 31-May-2016
 *
 */
@Component("sslDefaultCustomerDao")
public class SSLDefaultCustomerDao implements SSLCustomerDao {

    private static final Logger LOG = Logger.getLogger(SSLDefaultCustomerDao.class);
    private static final String ORDERSFROMDATE = "ordersFromDate";
    private static final String ORDERSUPTODATE = "ordersUptoDate";
    

    // Start dependencies

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    // End dependencies

    // Start methods

    /*
     * Returns the CustomerModel list based on email id or mobile no.
     *
     * @Param String email id/mobile no
     */
    @Override
    public List<CustomerModel> getCustomerByMobileOrEmail(final String parameter) {

        SearchResult<CustomerModel> result = null;
        final StringBuilder queryBuilder = new StringBuilder();

        if (parameter.contains("@")) {
            queryBuilder.append("select {c.pk} from {customer as c} where {uid} like '%" + parameter + "'");
        } else {
            queryBuilder.append(
                    "select {c.pk} from {customer as c} where {mobile} = '" + parameter + "' or {contactmobile} = '" + parameter + "'");
        }

        try {

            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryBuilder);
            flexibleSearchQuery.setResultClassList(Collections.singletonList(UserModel.class));
            LOG.info("CustomerByMobileOrEmail================" + flexibleSearchQuery.getQuery() + " parameter: " + parameter);
            result = flexibleSearchService.search(flexibleSearchQuery);
        } catch (final Exception ex) {
            LOG.info(ex);
        }
        return result != null ? result.getResult(): Collections.emptyList();
    }

    /*
     * Return the CustomerMode based on order number
     *
     * @Param String orderNumber
     */
    @Override
    public List<CustomerModel> getCustomerByOrderNo(final String orderNo) {

        final StringBuilder queryBuilder = new StringBuilder(
                "select {pk} from {customer} where {pk} in ({{select {user} from {order} where {code} like '%" + orderNo + "%' }})");

        try {

            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryBuilder);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
            LOG.info("CustomerByOrderNo===========" + flexibleSearchQuery.getQuery() + " parameter: " + orderNo);
            return searchResult.getResult();
        } catch (final Exception ex) {
            LOG.info(ex);
        }
        return null;
    }

    /*
     * Return the CustomerModels which are not sync with CRM
     */

    @Override
    public List<CustomerModel> getCustomersForCRM() {

        final StringBuilder queryBuilder = new StringBuilder(" select {c.pk} from {customer as c } where {c.crmStatusFlag} ='0'");

        try {
            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryBuilder);
            LOG.info("getCustomersForCRM===============" + flexibleSearchQuery);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }
        return null;
    }

    @Override
    public List<CustomerModel> getCustomerByMobileAndEmail(final String mobileNo) {
        SearchResult<CustomerModel> result = null;
        final StringBuilder queryBuilder = new StringBuilder("select {c.pk} from {customer as c} where {mobile} = ('" + mobileNo + "')"
        // check only registered users, skip guest users.
                + "and {type} is null");
        try {
            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryBuilder);
            flexibleSearchQuery.setResultClassList(Collections.singletonList(UserModel.class));
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Fetching registered customers by mobileNumber: %s, query: %s", flexibleSearchQuery.getQuery(),
                        mobileNo));
            }
            result = flexibleSearchService.search(flexibleSearchQuery);
            return result.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<CustomerModel> getCustomersWithFailedWalletCreationStatus() {
        final StringBuilder queryBuilder = new StringBuilder(" select {c.pk} from {customer as c } where {c.autoWalletCreationFail} ='1'");
        return this.executeQuery(queryBuilder);
    }

    /**
     * Returns List of CustomerModel satisfying the passed query.
     *
     * @param queryBuilder
     *        the queryBuilder
     * @return list of CustomerModel.
     */
    private List<CustomerModel> executeQuery(final StringBuilder queryBuilder) {
        try {
            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryBuilder);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }
        return Collections.emptyList();
    }

    @Override
    public int getNumberOfGuestUsersWithCODVerifiedFlag(final String emailID) {
        final StringBuilder queryBuilder = new StringBuilder(
                "select count({c.pk}) from {customer as c left join CustomerType as ct on {ct.pk} = {c.type}} where {c.verifiedForCOD} ='1' and SUBSTR({c.uid}, 38 ) = '"
                        + emailID + "' and {ct.code} = 'GUEST' ");
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryBuilder);
        query.setResultClassList(Arrays.asList(Integer.class));
        query.setFailOnUnknownFields(false);
        final SearchResult<Integer> result = flexibleSearchService.search(query);

        if (result.getCount() == 1) {
            return result.getResult().get(0).intValue();
        }
        return 0;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getUnverifiedCustomerWithSuccOrder(final Date ordersFrom, final Date ordersUpto) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "Select distinct{c.pk} from {Customer as c join Order as o on {o.user}={c.pk} left join OrderStatus as os on {o.status}={os.pk} } where {os.code} = 'COMPLETED' and {o.creationTime} >= ?ordersFromDate and {o.pk} not in ({{select distinct {r.order} from {ReturnRequest as r}}}) and {o.creationTime} <= ?ordersUptoDate and ({c.verifiedForCod} = ?verForCodFalse or {c.verifiedForCod} is null)");
            query.addQueryParameter("verForCodFalse", Boolean.FALSE);
            query.addQueryParameter(ORDERSFROMDATE, ordersFrom);
            query.addQueryParameter(ORDERSUPTODATE, ordersUpto);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }

        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getVerifiedCustomersWithoutSuccOrder(final Date ordersFrom, final Date ordersUpto) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "Select distinct{c.pk} from {Customer as c} where {c.verifiedForCod} = ?verForCodTrue and {c.verificationDate} <= ?ordersFromDate and {c.pk} not in ({{Select {o.user} from {Order as o join OrderStatus as os on {o.status} = {os.pk}} where {os.code} = 'COMPLETED' and {o.creationTime} >= ?ordersFromDate and {o.creationTime} <= ?ordersUptoDate and {o.pk} not in ({{select distinct {r.order} from {ReturnRequest as r}}}) }})");
            query.addQueryParameter("verForCodTrue", Boolean.TRUE);
            query.addQueryParameter(ORDERSFROMDATE, ordersFrom);
            query.addQueryParameter(ORDERSUPTODATE, ordersUpto);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }

        return Collections.emptyList();
    }

    @Override
    public List<CustomerModel> getCustomersWithNConsecutiveRTVOrder(final Date ordersFrom, final Date ordersUpto, final int rtvCount) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "select uuid from ({{select distinct uuid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}} minus {{select distinct uuid from ({{select uuid, oid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}}) IQ where EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid and {c.returnrequest} is null and {c.status} != ?status}}) or NOT EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid}})}})");
            query.addQueryParameter(ORDERSFROMDATE, ordersFrom);
            query.addQueryParameter(ORDERSUPTODATE, ordersUpto);
            query.addQueryParameter(SslCoreConstants.STATUS, ConsignmentStatus.RETURN_TO_VENDOR);
            query.addQueryParameter(SslCoreConstants.RTV_COUNT, rtvCount);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }

        return Collections.emptyList();
    }
    
    @Override
    public List<CustomerModel> getCustomersWithMoreRTVShipments(final Date ordersFrom, final Date ordersUpto, final int successPercentage, final int rtvCount) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "select uuid from ({{select {u.pk} as uuid, count(CASE when {c.status} in (?rtvStatus, ?retIssuedStatus, ?retInitiatedStatus) then 1 end) as rtvs, count(CASE when {c.status} in (?delStatus, ?comStatus) then 1 end) as dels from {customer as u join order as o on {o.user} = {u.pk} join consignment as c on {c.order} = {o.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate and {c.returnrequest} is null group by {u.pk}}}) where (rtvs + dels) > 0 and dels / (rtvs + dels) * 100 < ?successPercentage and uuid not in ({{select uuid from ({{select distinct uuid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}} minus {{select distinct uuid from ({{select uuid, oid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}}) IQ where EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid and {c.returnrequest} is null and {c.status} != ?status}}) or NOT EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid}})}})}})");
            query.addQueryParameter(ORDERSFROMDATE, ordersFrom);
            query.addQueryParameter(ORDERSUPTODATE, ordersUpto);
            query.addQueryParameter("rtvStatus", ConsignmentStatus.RETURN_TO_VENDOR);
            query.addQueryParameter("retIssuedStatus", ConsignmentStatus.RETURN_ISSUED);
            query.addQueryParameter("retInitiatedStatus", ConsignmentStatus.RETURN_INITIATED);
            query.addQueryParameter("delStatus", ConsignmentStatus.DELIVERED);
            query.addQueryParameter("comStatus", ConsignmentStatus.COMPLETE);
            query.addQueryParameter("successPercentage", successPercentage);
            query.addQueryParameter(SslCoreConstants.STATUS, ConsignmentStatus.RETURN_TO_VENDOR);
            query.addQueryParameter(SslCoreConstants.RTV_COUNT, rtvCount);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }

        return Collections.emptyList();
    }
    
    @Override
    public List<CustomerModel> getCustomersWithMoreDeliveredShipments(final Date ordersFrom, final Date ordersUpto, final int successPercentage, final int rtvCount) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "select uuid from ({{select {u.pk} as uuid, count(CASE when {c.status} in (?rtvStatus, ?retIssuedStatus, ?retInitiatedStatus) then 1 end) as rtvs, count(CASE when {c.status} in (?delStatus, ?comStatus) then 1 end) as dels from {customer as u join order as o on {o.user} = {u.pk} join consignment as c on {c.order} = {o.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate and {c.returnrequest} is null group by {u.pk}}}) where (rtvs + dels) > 0 and dels / (rtvs + dels) * 100 >= ?successPercentage and uuid not in ({{select uuid from ({{select distinct uuid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}} minus {{select distinct uuid from ({{select uuid, oid from ({{select {u.pk} as uuid, {o.pk} as oid, {o.creationtime} as odate, row_number() over (partition by {u.pk} order by {o.creationtime} desc) as ono from {customer as u join order as o on {o.user} = {u.pk}} where {u.type} is null and {o.versionid} is null and {o.creationtime} >= ?ordersFromDate and {o.creationtime} <= ?ordersUptoDate}}) where ono <= ?rtvCount}}) IQ where EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid and {c.returnrequest} is null and {c.status} != ?status}}) or NOT EXISTS ({{select {c.pk} from {consignment as c} where {c.order} = iq.oid}})}})}})");
            query.addQueryParameter(ORDERSFROMDATE, ordersFrom);
            query.addQueryParameter(ORDERSUPTODATE, ordersUpto);
            query.addQueryParameter("rtvStatus", ConsignmentStatus.RETURN_TO_VENDOR);
            query.addQueryParameter("retIssuedStatus", ConsignmentStatus.RETURN_ISSUED);
            query.addQueryParameter("retInitiatedStatus", ConsignmentStatus.RETURN_INITIATED);
            query.addQueryParameter("delStatus", ConsignmentStatus.DELIVERED);
            query.addQueryParameter("comStatus", ConsignmentStatus.COMPLETE);
            query.addQueryParameter("successPercentage", successPercentage);
            query.addQueryParameter(SslCoreConstants.STATUS, ConsignmentStatus.RETURN_TO_VENDOR);
            query.addQueryParameter(SslCoreConstants.RTV_COUNT, rtvCount);
            final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            logException(ex);
        }

        return Collections.emptyList();
    }
    
    
	@Override
	public List<CustomerModel> getCustomerByPk(List<String> customerPks) {
		final FlexibleSearchQuery query = new FlexibleSearchQuery(
				"select {pk} from {customer} where {pk} in (?customerPks)");
		query.addQueryParameter("customerPks", customerPks);
		final SearchResult<CustomerModel> searchResult = flexibleSearchService
				.search(query);
		return searchResult.getResult();

	}
    
    private static void logException(Exception ex) {
        LOG.error("Error Message: " + ex + " Error cause: " + ex.getCause());
    }



}