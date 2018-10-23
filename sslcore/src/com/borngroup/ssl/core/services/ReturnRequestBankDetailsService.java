/**
 *
 */
package com.borngroup.ssl.core.services;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;

/**
 * @author tejsharma
 *
 */
public interface ReturnRequestBankDetailsService {
    /**
     * Bank Details of RMA processed before the date passed as argument which are not already masked.
     *
     * @param beforeDate
     * @return : <code>List<ReturnRequestBankDetailsModel></code>
     */
    public List<ReturnRequestBankDetailsModel> getReturnRequestBankDetailsToMask(final Date beforeDate);
}
