package com.ssl.core.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author techouts
 *
 */
public class SSLUserDomainRestriction extends GeneratedSSLUserDomainRestriction
{
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger
            .getLogger(SSLUserDomainRestriction.class.getName());

    @Override
    protected Item createItem(final SessionContext ctx,
            final ComposedType type, final ItemAttributeMap allAttributes)
            throws JaloBusinessException
    {
        final SSLUserDomainRestriction item = (SSLUserDomainRestriction) super
                .createItem(ctx, type, allAttributes);
        return item;
    }

    @Override
    protected boolean isFulfilledInternal(final AbstractOrder order)
    {
        return isValidCustomerDomain(order) && isVerifiedEmail(order);
    }

    @Override
    protected boolean isFulfilledInternal(final Product arg0)
    {
        return false;
    }

    /**
     * Method to check is valid customer domain from SSLCustomer domainsList
     *
     * @param order
     * @return true if valid domain otherwise return false
     */
    private boolean isValidCustomerDomain(final AbstractOrder order)
    {
        if (order.getUser() instanceof Customer)
        {
            final Customer customer = (Customer) order.getUser(getSession()
                    .getSessionContext());
            try
            {
                if (!getDomainsList().isEmpty())
                {
                    final Collection<String> userdomains = getDomainsList();
                    final String[] parts = customer.getUid().split("@");
                    if (parts.length == 2)
                    {
                        return userdomains.contains(parts[1]);
                    }
                }
                else
                {
                    LOG.info("Domain Should Not Be Empty In Voucher Restriction");
                }
            }
            catch (final Exception exception)
            {
                LOG.warn(exception);
            }
        }
        return false;
    }

    /**
     * Method to check customer verfied through email or not
     *
     * @param order
     * @return true if customer verifed
     */
    private boolean isVerifiedEmail(final AbstractOrder order)
    {
        if (order.getUser() instanceof Customer)
        {
            return isVerifiedEmail().booleanValue()
                    && isCustomerEmailVerified();
        }

        return false;
    }

    @SuppressWarnings("boxing")
    private boolean isCustomerEmailVerified()
    {
        final UserService customer = Registry.getApplicationContext().getBean(
                "userService", UserService.class);
        try
        {
            return Boolean.TRUE.equals(((CustomerModel) customer
                    .getCurrentUser()).getVerifiedEmail().booleanValue());
        }
        catch (final Exception exception)
        {
            LOG.warn(exception);
        }
        return false;
    }
}
