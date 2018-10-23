package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

/**
 * The Jalo Implementation SslFirstCitizenCardLevelRestriction
 *
 * @author shilpaverma
 *
 */
public class SslFirstCitizenCardLevelRestriction extends GeneratedSslMediumTypeRestriction {
    @SuppressWarnings("unused")
    /**
     * The Logger
     */
    private final static Logger LOG = Logger.getLogger(SslFirstCitizenCardLevelRestriction.class.getName());

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
     * Implementation for abstract method from Restriction.
     */
    @SuppressWarnings("deprecation")
    @Override
    protected boolean isFulfilledInternal(final AbstractOrder order) {
        return true;
    }

    /**
     * Implementation for abstract method from Restriction.
     */
    @SuppressWarnings("deprecation")
    @Override
    protected boolean isFulfilledInternal(final Product product) {
        return true;
    }

}
