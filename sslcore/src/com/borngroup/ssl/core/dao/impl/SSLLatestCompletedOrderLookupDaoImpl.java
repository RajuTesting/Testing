package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SSLLatestCompletedOrderLookupDao;
import com.borngroup.ssl.core.model.CustomerFeedbackModel;

/**
 * The Class SSLLatestCompletedOrderLookupDaoImpl. DAO implementation to access
 * latest completed orders
 *
 * created by : ashish.sabal@nagarro.com
 *
 * @author SSL
 */
public class SSLLatestCompletedOrderLookupDaoImpl implements SSLLatestCompletedOrderLookupDao {

	/** The LOG4j logger. */
	private static final Logger LOG = Logger.getLogger(SSLLatestCompletedOrderLookupDaoImpl.class);

	/** FlexibleSearch Service. */
	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<OrderModel> getCompletedOrderLatest(final Date startDate, final OrderStatus orderStatus) {
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {").append(OrderModel.PK).append("} FROM {").append(OrderModel._TYPECODE)
				.append(" AS ORDR JOIN ").append(OrderStatus._TYPECODE)
				.append(" AS ORDSTTS ON {ORDSTTS.pk}={ORDR.status}} WHERE {ORDSTTS.code}= \'" + orderStatus.toString()
						+ "\' AND ");
		builder.append(" to_char({ORDR.modifiedtime},'dd-MM-yyyy') >=\'"
				+ new SimpleDateFormat("dd-MM-yyyy").format(startDate) + "\'")
				.append(" AND {ORDR.orderCompleteMailsent}=\'0\'");

		LOG.debug("Q: " + builder.toString());
		return this.getResultFromSqlQuery(builder.toString()).getResult();
	}

	@Override
	public List<CustomerFeedbackModel> getOldOrderFeedbackData(final Date startDate) {
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {").append(CustomerFeedbackModel.PK).append("} FROM {")
				.append(CustomerFeedbackModel._TYPECODE).append(" AS FDBCK}").append(" WHERE")
				.append(" to_char({FDBCK.modifiedtime},'dd-MM-yyyy') <=\'")
				.append(new SimpleDateFormat("dd-MM-yyyy").format(startDate)).append("\'");

		LOG.debug("Q: " + builder.toString());
		return this.getResultFromSqlQuery(builder.toString()).getResult();
	}

	private SearchResult getResultFromSqlQuery(final String query) {
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		return this.getFlexibleSearchService().search(flexibleSearchQuery);
	}

	/**
	 * flexibleSearchService getter method.
	 *
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

}
