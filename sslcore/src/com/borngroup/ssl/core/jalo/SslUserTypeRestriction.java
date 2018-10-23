package com.borngroup.ssl.core.jalo;

import de.hybris.platform.commerceservices.jalo.CommerceServicesManager;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.enums.UserType;

/**
 * The Jalo Implementation SslUserTypeRestriction
 *
 * @author shilpaverma
 *
 */
@SuppressWarnings("deprecation")
public class SslUserTypeRestriction extends GeneratedSslUserTypeRestriction {
    @SuppressWarnings("unused")
    /**
     * The Logger
     */
    private final static Logger LOG = Logger.getLogger(SslUserTypeRestriction.class.getName());

    @Override
    protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
            throws JaloBusinessException {
        // business code placed here will be executed before the item is created
        // then create the item
        final Item item = super.createItem(ctx, type, allAttributes);
        // business code placed here will be executed after the item was created
        // and return the item
        return item;
    }

    /**
     * Returns true or false based on the match of user type of Restriction and logged In user
     */
    @SuppressWarnings("deprecation")
    @Override
    protected boolean isFulfilledInternal(final AbstractOrder order) {
        if (order.getUser() instanceof Customer) {
            final Customer customer = (Customer) order.getUser(getSession().getSessionContext());

            String customerType = null;
            if (CommerceServicesManager.getInstance().getType(customer) != null) {
                customerType = CommerceServicesManager.getInstance().getType(customer).getCode();
            }

            final String userType = this.getUserType(getSession().getSessionContext()).getCode();
            if (userType.equals(UserType.GUEST.getCode())) {
                if ((UserType.GUEST.getCode().equals(customerType)) || customer.isAnonymousCustomer()) {
                    return true;
                }
            } else if (userType.equals(UserType.SIGNED_IN.getCode())) {
                if (customerType == null && !customer.isAnonymousCustomer()) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Implementation for abstract method from Restriction, returns true for all passed products.
     */
    @Override
    protected boolean isFulfilledInternal(final Product arg0) {
        return false;
    }

}
