package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.CustomerFeedbackModel;

/**
 * Interface : Customer email feedback dao.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public interface SslCustomerEmailFeedbackDao {
    /**
     * This method used to get customer feedback details based on orderNo.
     * 
     * @param orderNo - Instance of String
     * @return List of {@link CustomerFeedbackModel}
     */
    List<CustomerFeedbackModel> getCustomerFeedbackDetails(String orderNo);
}
