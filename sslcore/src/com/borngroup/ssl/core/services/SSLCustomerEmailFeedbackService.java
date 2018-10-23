package com.borngroup.ssl.core.services;

import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface : Customer email feedback service.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public interface SSLCustomerEmailFeedbackService {
    /**
     * This method get the feedback component list based on slot id.
     * 
     * @param slotId - Instance of String
     * @param httpRequest - Instance of HttpServletRequest
     * @return List of {@link SimpleCMSComponentModel}
     */
    List<SimpleCMSComponentModel> getFeedbackComponentList(String slotId, HttpServletRequest httpRequest);

    /**
     * This method check for feedback response in database.
     * 
     * @param orderNo - Instance of String
     * @param starRating - Instance of String
     * @return boolean
     */
    boolean checkForFeedbackResponse(String orderNo, String starRating);

    /**
     * This method save the feedback to database.
     * 
     * @param orderNo - Instance of String
     * @param feedbackJson - Instance of String
     */
    void saveFeedbackResponse(String orderNo, String feedbackJson);
}
