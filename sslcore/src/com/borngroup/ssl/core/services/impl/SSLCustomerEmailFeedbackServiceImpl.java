package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSContentSlotService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SslCustomerEmailFeedbackDao;
import com.borngroup.ssl.core.model.CustomerFeedbackModel;
import com.borngroup.ssl.core.services.SSLCustomerEmailFeedbackService;

/**
 * Customer email feedback service implementation.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class SSLCustomerEmailFeedbackServiceImpl implements SSLCustomerEmailFeedbackService {

    /**
     * DefaultCMSContentSlotService instance.
     */
    @Resource(name = "cmsContentSlotService")
    private DefaultCMSContentSlotService defaultCMSContentSlotService;

    /**
     * ModelService instance.
     */
    @Resource(name = "modelService")
    private ModelService modelService;

    /**
     * SslCustomerEmailFeedbackDao instance.
     */
    @Resource(name = "sslCustomerEmailFeedbackDao")
    private SslCustomerEmailFeedbackDao sslCustomerEmailFeedbackDao;
    /**
     * SSLCustomerEmailFeedbackServiceImpl Logger Instance.
     */
    private static final Logger LOG = Logger.getLogger(SSLCustomerEmailFeedbackServiceImpl.class);

    /**
     * This method get the feedback component list based on slot id.
     * 
     * @param slotId - Instance of String
     * @param httpRequest - Instance of HttpServletRequest
     * @return List of {@link SimpleCMSComponentModel}
     */
    @Override
    public List<SimpleCMSComponentModel> getFeedbackComponentList(final String slotId, final HttpServletRequest httpRequest) {
        try {
            ContentSlotModel slotModel = this.defaultCMSContentSlotService.getContentSlot(slotId);
             return this.defaultCMSContentSlotService.getSimpleCMSComponents(slotModel, true, httpRequest);
        } catch (Exception e) {
            LOG.error("Error inside method getFeedbackComponentList with exception  " + e.getMessage());
        }

        return ListUtils.EMPTY_LIST;
    }

    /**
     * This method check for feedback response in database.
     * 
     * @param orderNo - Instance of String
     * @param starRating - Instance of String
     * @return Boolean
     */
    @Override
    public boolean checkForFeedbackResponse(final String orderNo, final String starRating) {
        try {
            if (StringUtils.isNotEmpty(orderNo) && StringUtils.isNotEmpty(starRating)) {
                final List<CustomerFeedbackModel> customerFeedbackList = this.sslCustomerEmailFeedbackDao
                        .getCustomerFeedbackDetails(orderNo);
                if (CollectionUtils.isNotEmpty(customerFeedbackList)) {
                    return StringUtils.isNotEmpty(customerFeedbackList.get(0).getFeedbackResponse());
                } else {
                    final CustomerFeedbackModel customerFeedbackModel = new CustomerFeedbackModel();
                    customerFeedbackModel.setOrderNo(orderNo);
                    customerFeedbackModel.setStarRating(starRating);
                    this.modelService.save(customerFeedbackModel);
                }
                return false;
            }
        } catch (Exception e) {
            LOG.error("Error inside method saveCustomerDetailsIfRequired with exception  " + e.getMessage());
        }
       return true;
    }

    /**
     * This method save the feedback to database.
     * 
     * @param orderNo - Instance of String
     * @param feedbackJson - Instance of String
     */
    @Override
    public void saveFeedbackResponse(final String orderNo, final String feedbackJson) {
        try {
            final List<CustomerFeedbackModel> customerFeedbackList = this.sslCustomerEmailFeedbackDao.getCustomerFeedbackDetails(orderNo);
            if (CollectionUtils.isNotEmpty(customerFeedbackList) && customerFeedbackList.get(0) != null) {
                final CustomerFeedbackModel customerFeedbackModel = customerFeedbackList.get(0);
                customerFeedbackModel.setFeedbackResponse(feedbackJson);
                this.modelService.save(customerFeedbackModel);
            }
        } catch (Exception e) {
            LOG.error("Error inside method saveCustomerDetailsIfRequired with exception  " + e.getMessage());
        }
    }

}
