/**
 *
 */
package com.borngroup.ssl.core.services.notifycustomer.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.enums.SSLNotifyCustomerTypeEnum;
import com.borngroup.ssl.core.model.NotifyCustomerModel;
import com.borngroup.ssl.core.services.notifycustomer.SSLCustomerNotificationService;
import com.borngroup.ssl.facades.data.SSLCustomerNotificationData;

/**
 * @author Nagarro Dev
 *
 */
public class SSLCustomerNotificationServiceImpl implements SSLCustomerNotificationService {

    private static final Logger LOG = Logger.getLogger(SSLCustomerNotificationServiceImpl.class);
    private ModelService modelService;

    @Resource(name = "notifyCustomerDao")
    private GenericDao<NotifyCustomerModel> notifyCustomerDao;

    @Resource(name = "productService")
    private ProductService productService;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.notifycustomer.SSLCustomerNotificationService#notifyCustomer(com.borngroup.ssl.facades.data.
     * SSLCustomerNotificationData)
     */
    @Override
    public void notifyCustomer(final SSLCustomerNotificationData customerNotificationData, final ProductModel product)
            throws EmailException {
        LOG.debug("Saving details about Customer notify request for product : " + customerNotificationData.getProductCode());
        final NotifyCustomerModel notifyCustomerModel = modelService.create(NotifyCustomerModel.class);
        notifyCustomerModel.setProduct(product);
        notifyCustomerModel.setType(SSLNotifyCustomerTypeEnum.valueOf(customerNotificationData.getType()));
        notifyCustomerModel.setEmail(customerNotificationData.getEmail());
        notifyCustomerModel.setMobile(customerNotificationData.getMobile());
        modelService.save(notifyCustomerModel);
    }

    /**
     * @param modelService
     *        the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.notifycustomer.SSLCustomerNotificationService#isCustomerNotificationDataValid(com.borngroup.ssl.
     * facades.data.SSLCustomerNotificationData)
     */
    @Override
    public boolean isCustomerAlreadyRequestedForNotification(final SSLCustomerNotificationData sslCustomerNotificationData) {
        final Map<String, Object> params = new HashMap<>();
        params.put(NotifyCustomerModel.EMAIL, sslCustomerNotificationData.getEmail());
//        params.put(NotifyCustomerModel.MOBILE, sslCustomerNotificationData.getMobile());
        params.put(NotifyCustomerModel.TYPE, SSLNotifyCustomerTypeEnum.valueOf(sslCustomerNotificationData.getType()));
        params.put(NotifyCustomerModel.PRODUCT, productService.getProductForCode(sslCustomerNotificationData.getProductCode()));
        final List<NotifyCustomerModel> recordEntries = this.notifyCustomerDao.find(params);
        return CollectionUtils.isNotEmpty(recordEntries);
    }

}
