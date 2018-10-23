package com.borngroup.ssl.core.dao;

import com.borngroup.ssl.core.model.SslCCAvenueCustomerCardDetailModel;

/**
 * Dao for SslCCAvenueCustomerCardDetailModel
 * <p>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */

public interface SslCustomerCCAvenueCardDao {

    /**
     * fetches SslCCAvenueCustomerCardDetailModel for passed code
     * 
     * @param code
     * @return SslCCAvenueCustomerCardDetailModel for passed code
     */
    SslCCAvenueCustomerCardDetailModel getCustomerCardDetailForCode(String code);
}
