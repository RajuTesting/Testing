/**
 *
 */
package com.borngroup.ssl.core.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.localization.Localization;

import com.borngroup.ssl.core.enums.SslGenderRestriction;
import com.borngroup.ssl.core.model.restrictions.CMSSslGenderRestrictionModel;

/**
 * The Class SslGenderRestrictionDynamicDescription.
 *
 */
public class SslGenderRestrictionDynamicDescription implements DynamicAttributeHandler<String, CMSSslGenderRestrictionModel> {

    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model.AbstractItemModel)
     */
    @Override
    public String get(final CMSSslGenderRestrictionModel model) {
        final StringBuilder result = new StringBuilder();
        final SslGenderRestriction gender = model.getGender();
        if (gender != null) {
            final String localizedString = Localization.getLocalizedString("type.CMSSslGenderRestriction.description.text");
            result.append((localizedString == null) ? "Page only applies on gender:" : localizedString);
            result.append(" ").append(gender.getCode());
        }
        return result.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.AbstractItemModel,
     * java.lang.Object)
     */
    @Override
    public void set(final CMSSslGenderRestrictionModel model, final String value) {
        throw new UnsupportedOperationException();
    }

}
