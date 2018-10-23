package com.borngroup.ssl.core.crm.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.PasswordMismatchException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.borngroup.ssl.core.crm.dao.SSLCustomerDao;
import com.borngroup.ssl.core.crm.service.SSLCustomerService;

/**
 * @author DHEERAJ KUMAR
 * @version 1.0
 * @since 31-May-2016
 */

@Service("sslDefaultCustomerService")
public class SSLDefaultCustomerService implements SSLCustomerService {

    private final static Logger LOG = Logger.getLogger(SSLDefaultCustomerService.class);

    // Start dependencies

    @Resource(name = "sslDefaultCustomerDao")
    private SSLCustomerDao sslDefaultCustomerDao;

    @Resource(name = "customerConverter")
    private Converter<CustomerModel, CustomerData> customerConverter;

    /** Base Site Service. */
    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    /** Event Service. */
    @Resource(name = "eventService")
    private EventService eventService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "passwordEncoderService")
    private PasswordEncoderService passwordEncoderService;

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    private CommonI18NService commonI18NService;

    // End dependencies

    // Start methods

    /*
     * Return the CustomerData list based on email id or mobile number
     *
     * @Param String email id/mobile no
     */

    @Override
    public List<CustomerData> getCustomerByMobileOrEmail(final String parameter) {

        final List<CustomerData> custDataList = new LinkedList<CustomerData>();
        try {
            final List<CustomerModel> custModelList = sslDefaultCustomerDao.getCustomerByMobileOrEmail(parameter);
            if (!CollectionUtils.isEmpty(custModelList)) {
                final Iterator<CustomerModel> custModelIterator = custModelList.iterator();
                while (custModelIterator.hasNext()) {
                    custDataList.add(customerConverter.convert(custModelIterator.next()));
                }
            }
        } catch (final Exception ex) {
            LOG.info(ex);
        }
        return custDataList;
    }

    /*
     * Return the CustomerMode based on order number
     *
     * @Param String orderNumber
     */

    @Override
    public List<CustomerData> getCustomerByOrderNo(final String orderNo) {

        final List<CustomerModel> customerModelList;
        final List<CustomerData> custDataList = new LinkedList<CustomerData>();

        try {

            customerModelList = sslDefaultCustomerDao.getCustomerByOrderNo(orderNo);
            if (!CollectionUtils.isEmpty(customerModelList)) {
                for (final Iterator<CustomerModel> iterator = customerModelList.iterator(); iterator.hasNext();) {
                    custDataList.add(customerConverter.convert(iterator.next()));
                }
            }
            return custDataList;
        } catch (final Exception ex) {
            LOG.info(ex);
        }
        return null;
    }

    /*
     * Return the CustomerModels which are not sync with CRM
     */
    @Override
    public List<CustomerModel> getCustomersForCRM() {

        try {
            final List<CustomerModel> customerModelList = sslDefaultCustomerDao.getCustomersForCRM();
            return customerModelList != null && customerModelList.isEmpty() ? null : customerModelList;
        } catch (final Exception e) {
            LOG.error("Error Msg " + e.getMessage() + " Error Cause: " + e.getCause());
        }
        return null;
    }

    /**
     * Return the CustomerModels by mobileno and emailId
     */
    @Override
    public List<CustomerModel> getCustomerByMobileAndEmail(final String mobileNo) {
        final List<CustomerModel> customerModelList = sslDefaultCustomerDao.getCustomerByMobileAndEmail(mobileNo);
        return customerModelList;
    }

    @Override
    public boolean checkPassword(final String currentPassword, final UserModel userModel) throws PasswordMismatchException {
        boolean isValidPwd = false;
        validateParameterNotNullStandardMessage("customerModel", userModel);
        if (!getUserService().isAnonymousUser(userModel)) {
            final String encodedCurrentPassword = getPasswordEncoderService().encode(userModel, currentPassword,
                    userModel.getPasswordEncoding());
            if (encodedCurrentPassword.equals(userModel.getEncodedPassword())) {
                isValidPwd = true;
            } else {
                throw new PasswordMismatchException(userModel.getUid());
            }
        }
        return isValidPwd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getUnverifiedCustomerWithSuccOrder(final Date ordersFrom, final Date ordersUpto) {
        try {
            return sslDefaultCustomerDao.getUnverifiedCustomerWithSuccOrder(ordersFrom, ordersUpto);
        } catch (final Exception e) {
            LOG.error("Error Msg " + e.getMessage() + " Error Cause: " + e.getCause());
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getCustomersWithNConsecutiveRTVOrder(final Date ordersFrom, final Date ordersUpto, final int rtvCount) {
        return sslDefaultCustomerDao.getCustomersWithNConsecutiveRTVOrder(ordersFrom, ordersUpto, rtvCount);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getCustomersWithMoreRTVShipments(final Date ordersFrom, final Date ordersUpto, final int successPercentage, final int rtvCount) {
        return sslDefaultCustomerDao.getCustomersWithMoreRTVShipments(ordersFrom, ordersUpto, successPercentage, rtvCount);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getCustomersWithMoreDeliveredShipments(final Date ordersFrom, final Date ordersUpto, final int successPercentage, final int rtvCount) {
        return sslDefaultCustomerDao.getCustomersWithMoreDeliveredShipments(ordersFrom, ordersUpto, successPercentage, rtvCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerModel> getVerifiedCustomersWithoutSuccOrder(final Date ordersFrom, final Date ordersUpto) {
        try {
            return sslDefaultCustomerDao.getVerifiedCustomersWithoutSuccOrder(ordersFrom, ordersUpto);
        } catch (final Exception e) {
            LOG.error("Error Msg " + e.getMessage() + " Error Cause: " + e.getCause());
        }
        return null;
    }

    // End methods

    /**
     * Return the CustomerModels with failed wallet creation status.
     */
    @Override
    public List<CustomerModel> getCustomersWithFailedWalletCreationStatus() {
        return sslDefaultCustomerDao.getCustomersWithFailedWalletCreationStatus();

    }

    /**
     * @return the baseSiteService
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * @param baseSiteService the baseSiteService to set
     */
    public void setBaseSiteService(final BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /**
     * @return the eventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * @param eventService the eventService to set
     */
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * @return the commonI18NService
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    /**
     * @param commonI18NService the commonI18NService to set
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    /**
     * @return the passwordEncoderService
     */
    public PasswordEncoderService getPasswordEncoderService() {
        return passwordEncoderService;
    }

    /**
     * @param passwordEncoderService the passwordEncoderService to set
     */
    public void setPasswordEncoderService(final PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public int getNumberOfGuestUsersWithCODVerifiedFlag(final String emailID) {
        return sslDefaultCustomerDao.getNumberOfGuestUsersWithCODVerifiedFlag(emailID);
    }
}
