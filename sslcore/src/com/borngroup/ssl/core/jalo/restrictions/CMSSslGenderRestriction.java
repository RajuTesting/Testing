package com.borngroup.ssl.core.jalo.restrictions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.localization.Localization;

import org.apache.log4j.Logger;

public class CMSSslGenderRestriction extends GeneratedCMSSslGenderRestriction {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(CMSSslGenderRestriction.class.getName());

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

    @Override
    @Deprecated
    public String getDescription(final SessionContext ctx) {
        final StringBuilder result = new StringBuilder();
        final EnumerationValue gender = getGender();
        if (gender != null) {
            final String localizedString = Localization.getLocalizedString("type.CMSSslGenderRestriction.description.text");
            result.append((localizedString == null) ? "Page only applies on gender:" : localizedString);
            result.append(" ").append(getGender(ctx).getCode());
        }
        return result.toString();
    }
}
