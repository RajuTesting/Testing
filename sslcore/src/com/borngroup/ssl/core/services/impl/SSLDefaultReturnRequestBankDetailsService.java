/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.ReturnRequestBankDetailsDao;
import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;
import com.borngroup.ssl.core.services.ReturnRequestBankDetailsService;

/**
 * @author tejsharma
 *
 */
public class SSLDefaultReturnRequestBankDetailsService implements ReturnRequestBankDetailsService {
    private final static Logger LOG = Logger.getLogger(SSLDefaultReturnRequestBankDetailsService.class);

    @Resource(name = "returnRequestBankDetailsDao")
    private ReturnRequestBankDetailsDao returnRequestBankDetailsDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReturnRequestBankDetailsModel> getReturnRequestBankDetailsToMask(final Date beforeDate) {
        return beforeDate == null ? null : returnRequestBankDetailsDao.getReturnRequestBankDetailsToMask(beforeDate);
    }

}
