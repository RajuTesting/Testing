/**
 *
 */
package com.borngroup.ssl.core.services.customer.account.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.impl.UniqueAttributesInterceptor;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.dao.SSLCustomerOrderDao;
import com.borngroup.ssl.core.services.customer.account.SSLCustomerAccountService;

/**
 * @author vinaygupta
 *
 */
public class SSLCustomerAccountServiceImpl extends DefaultCustomerAccountService implements SSLCustomerAccountService {

    @Resource
    SSLCustomerOrderDao sslCustomerOrderDao;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.customer.account.SSLCustomerAccountService#updatePasswordAndSetCurrentUser(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void updatePasswordAndSetCurrentUser(final String token, final String newPassword) throws TokenInvalidatedException {

        Assert.hasText(token, "The field [token] cannot be empty");
        Assert.hasText(newPassword, "The field [newPassword] cannot be empty");

        final SecureToken data = getSecureTokenService().decryptData(token);
        if (getTokenValiditySeconds() > 0L) {
            final long delta = new Date().getTime() - data.getTimeStamp();
            if (delta / 1000 > getTokenValiditySeconds()) {
                throw new IllegalArgumentException("token expired");
            }
        }

        final CustomerModel customer = getUserService().getUserForUID(data.getData(), CustomerModel.class);
        if (customer == null) {
            throw new IllegalArgumentException("user for token not found");
        }
        if (!token.equals(customer.getToken())) {
            throw new TokenInvalidatedException("Token is either invalid or expired");
        }
        customer.setToken(null);
        customer.setLoginDisabled(false);
        getModelService().save(customer);

        getUserService().setPassword(data.getData(), newPassword, getPasswordEncoding());
        getUserService().setCurrentUser(customer);
    }

    @Override
    public SearchPageData<OrderModel> getOrderListbyConsignmentStatus(final CustomerModel customerModel, final BaseStoreModel store,
            final ConsignmentStatus[] status, final OrderStatus[] orderStatus, final PageableData pageableData, final boolean statusIn) {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(store, "Store must not be null");
        validateParameterNotNull(status, "Consignment Status cannot be null");
        return getSslCustomerOrderDao().getOrderListbyConsignmentStatus(customerModel, store, status, orderStatus, pageableData, statusIn);
    }

    /**
     * Saves the customer translating model layer exceptions regarding duplicate identifiers
     *
     * @param customerModel
     * @throws DuplicateUidException if the uid is not unique
     */
    @Override
    protected void internalSaveCustomer(final CustomerModel customerModel) throws DuplicateUidException {
        try {
            getModelService().save(customerModel);
        } catch (final ModelSavingException e) {
            if (e.getCause() instanceof InterceptorException
                    && ((InterceptorException) e.getCause()).getInterceptor().getClass().equals(UniqueAttributesInterceptor.class)) {
                throw new DuplicateUidException(SslCoreConstants.DUPLICATE_UID_ERROR, e);
            } else {
                throw e;
            }
        } catch (final AmbiguousIdentifierException e) {
            throw new DuplicateUidException(SslCoreConstants.DUPLICATE_UID_ERROR, e);
        }
    }

    /**
     * Returns the order history of the current user for given statuses.
     *
     * @param pageableData paging information
     * @param statuses array of order statuses to filter the results
     * @return The order history of the current user.
     */
    @Override
    public SearchPageData<OrderModel> getOrderList(final CustomerModel customerModel, final BaseStoreModel store,
            final OrderStatus[] status, final PageableData pageableData) {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(store, "Store must not be null");
        validateParameterNotNull(pageableData, "PageableData must not be null");
        return getSslCustomerOrderDao().findOrdersByCustomerAndStore(customerModel, store, status, pageableData);
    }

    /**
     * @return the sslCustomerOrderDao
     */
    public SSLCustomerOrderDao getSslCustomerOrderDao() {
        return sslCustomerOrderDao;
    }

}
