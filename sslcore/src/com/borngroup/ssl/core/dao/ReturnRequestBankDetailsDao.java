/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;

/**
 * @author tejsharma
 *
 */
public interface ReturnRequestBankDetailsDao {
    /**
     * Bank Details of RMA processed before the date passed as argument.
     *
     * @param beforeDate
     * @return
     */
    List<ReturnRequestBankDetailsModel> getReturnRequestBankDetailsToMask(Date beforeDate);
}
