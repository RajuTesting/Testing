/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.ReturnRequestBankDetailsDao;
import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;

/**
 * @author tejsharma
 *
 */
public class SSLDefaultReturnRequestBankDetailsDao implements ReturnRequestBankDetailsDao {
    private final static Logger LOG = Logger.getLogger(SSLDefaultReturnRequestBankDetailsDao.class);

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReturnRequestBankDetailsModel> getReturnRequestBankDetailsToMask(final Date beforeDate) {
        try {
            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "Select distinct{rrbd.pk} from {ReturnRequest as rr join ReturnRequestBankDetails as rrbd on {rrbd.pk}={rr.bankDetails} left join ReturnStatus as rs on {rr.status}={rs.pk} } where {rs.code} = 'COMPLETE' and {rrbd.accountNumber} is not null and {rrbd.accountNumber} not like '%*%' and ({rr.rmaCompletionDate} <= ?beforeDate or {rr.rmaCompletionDate} is null )");
            query.addQueryParameter("beforeDate", beforeDate);
            final SearchResult<ReturnRequestBankDetailsModel> searchResult = flexibleSearchService.search(query);
            return searchResult.getResult();
        } catch (final Exception ex) {
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
        }

        return ListUtils.EMPTY_LIST;
    }

}
