/**
 *
 */
package com.ss.core.report.dao.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ss.core.report.dao.UserReportDao;

public class DefauldUserReportDao extends AbstractItemDao implements UserReportDao {

    /**
     * Constant Logger.
     */
    Logger LOG = Logger.getLogger(DefauldUserReportDao.class);

    /**
     * Constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    // private FlexibleSearchService flexibleSearchService;

    /*
     * @Override public FlexibleSearchService getFlexibleSearchService() { return flexibleSearchService; }
     *
     * @Override public void setFlexibleSearchService( final FlexibleSearchService flexibleSearchService) { this.flexibleSearchService =
     * flexibleSearchService; }
     */

    @Override
    public List<CustomerModel> getAllCustomers() {

        LOG.info("################## Calling DAO for Abandoned Cart User ##################");

        // getFlexibleSearchService().search(arg0)
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -1);
        calendar2.set(Calendar.HOUR, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        final Date date2 = calendar2.getTime();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date currentDate = calendar.getTime();
        final String todayDate = getProperDate(currentDate, DATE_FORMAT);
        final String dateOfOneDayBack = getProperDate(date2, DATE_FORMAT);
        LOG.info("################## Preparing QUERY for Abandoned Cart Users ##################");

        List<CustomerModel> custList = new ArrayList<CustomerModel>();
        final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT distinct {Customer :" + CustomerModel.PK + "} FROM {"
                + CustomerModel._TYPECODE + "},{" + CartModel._TYPECODE + "} WHERE {Customer :" + CustomerModel.PK + "} = {Cart:"
                + CartModel.USER + "} AND " + "{Cart:" + CartModel.LASTUPDATEDTIME + "} >= TO_DATE ('" + dateOfOneDayBack + "','"
                + DATE_FORMAT + "')" + " AND {Cart:" + CartModel.LASTUPDATEDTIME + "} < TO_DATE ('" + todayDate + "','" + DATE_FORMAT + "')"
                + "AND {Customer:" + CustomerModel.NAME + "} != 'Anonymous'");
        LOG.info("################## Prepared QUERY for Abandoned Cart Users ##################");
        query.setResultClassList(Collections.singletonList(CustomerModel.class));
        LOG.info("################## Firing QUERY for Abandoned Cart Users ##################");

        LOG.info("################## QUERY START " + query + " QUERY END ##################");

        final SearchResult<CustomerModel> result = getFlexibleSearchService().search(query);

        if (result != null && result.getResult() != null) {
            custList = result.getResult();
            LOG.info("################## Got some results for Abandoned Cart Users ##### " + result.getCount() + "#############");
            return custList;
        } else {
            LOG.info("################## Got no results for Abandoned Cart Users ##################");
            return null;
        }
    }

    /**
     * This method interacts with database to extract all the customers with abandoned cart.
     *
     * @return List<CustomerModel> List of customers with abandoned cart.
     */
    @Override
    public List<CustomerModel> getAllCustomersAbandonedCartForOutbound() {

        LOG.info("################## Calling DAO for Abandoned Cart User ##################");

        LOG.info("################## Preparing QUERY for Abandoned Cart Users ##################");

        List<CustomerModel> custList = new ArrayList<CustomerModel>();
        final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT distinct {Customer :" + CustomerModel.PK + "} FROM {"
                + CustomerModel._TYPECODE + "},{" + CartModel._TYPECODE + "} WHERE {Customer :" + CustomerModel.PK + "} = {Cart:"
                + CartModel.USER + "} AND {Customer:" + CustomerModel.NAME + "} != 'Anonymous'" + " AND {Customer:" + CustomerModel.MOBILE
                + "} IS NOT NULL" + " AND {Customer:" + CustomerModel.DEFAULTSHIPMENTADDRESS + "} IS NOT NULL");
        LOG.info("################## Prepared QUERY for Abandoned Cart Users ##################");
        query.setResultClassList(Collections.singletonList(CustomerModel.class));
        LOG.info("################## Firing QUERY for Abandoned Cart Users ##################");

        LOG.info("################## QUERY START " + query + " QUERY END ##################");

        final SearchResult<CustomerModel> result = getFlexibleSearchService().search(query);

        if (result != null && result.getResult() != null) {
            custList = result.getResult();
            LOG.info("################## Got some results for Abandoned Cart Users ##### " + result.getCount() + "#############");
            return custList;
        } else {
            LOG.info("################## Got no results for Abandoned Cart Users ##################");
            return null;
        }
    }

    @Override
    public List<CustomerModel> getAllCustomersOfLastSixMonthOrders() {

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date date = calendar.getTime();

        final String properDate = getProperDate(date, DATE_FORMAT);

        final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE
                + "},{" + OrderModel._TYPECODE + "} WHERE {Customer :" + CustomerModel.PK + "} = {Order:" + OrderModel.USER + "} AND "
                + "{Order:" + OrderModel.STATUS + "} = 'completed'  AND " + "{Order:" + OrderModel.CREATIONTIME + "} > TO_DATE('"
                + properDate + "','" + DATE_FORMAT + "')" + "AND {Customer:" + CustomerModel.NAME + "} != 'Anonymous'");

        query.setResultClassList(Collections.singletonList(CustomerModel.class));
        final SearchResult<CustomerModel> result = getFlexibleSearchService().search(query);

        return result.getResult();

    }

    public String getProperDate(final Date date, final String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

}
