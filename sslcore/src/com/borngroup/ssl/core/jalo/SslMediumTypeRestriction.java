package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.enums.MediumType;

/**
 * The Jalo Implementation SslMediumTypeRestriction
 *
 * @author shilpaverma
 *
 */
public class SslMediumTypeRestriction extends GeneratedSslMediumTypeRestriction {
    @SuppressWarnings("unused")
    /**
     * The Logger
     */
    private final static Logger LOG = Logger.getLogger(SslMediumTypeRestriction.class.getName());

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
     * Returns true or false based on whether restriction passed for current source of request.
     */
    @SuppressWarnings("deprecation")
    @Override
    protected boolean isFulfilledInternal(final AbstractOrder arg0) {
        final JaloSession jaloSession = JaloSession.getCurrentSession();
        final MediumType mediumType = (MediumType) jaloSession.getSessionContext().getAttribute("mediumType");
        final EnumerationValue mediumTypeForRestriction = this.getMediumType(getSession().getSessionContext());
        if (MediumType.ALL.getCode().equals(mediumTypeForRestriction.getCode())) {
            return true;
        } else {
            if (MediumType.APP_ONLY.getCode().equals(mediumTypeForRestriction.getCode())) {
                return (MediumType.ANDROID_APP.getCode().equals(mediumType.getCode()) || MediumType.IOS_APP.getCode().equals(
                        mediumType.getCode()));
            }
        }

        return mediumTypeForRestriction.getCode().equals(mediumType.getCode());
    }

    /**
     * Returns false for all passed products.
     */
    @SuppressWarnings("deprecation")
    @Override
    protected boolean isFulfilledInternal(final Product arg0) {
        return false;
    }

}
