/**
 *
 */
package com.borngroup.ssl.core.services.evaluator.impl;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.enums.SslGenderRestriction;
import com.borngroup.ssl.core.model.restrictions.CMSSslGenderRestrictionModel;

/**
 * The Class CMSSslGenderRestrictionEvaluator.
 *
 */
public class CMSSslGenderRestrictionEvaluator implements CMSRestrictionEvaluator<CMSSslGenderRestrictionModel> {

    private static final Logger LOG = Logger.getLogger(CMSSslGenderRestrictionEvaluator.class);

    /** The user service. */
    @Autowired
    private UserService userService;

    @Override
    public boolean evaluate(final CMSSslGenderRestrictionModel cmsSslGenderRestriction, final RestrictionData context) {
        final CustomerModel currentUser = (CustomerModel) userService.getCurrentUser();
        SslGenderRestriction gender = SslGenderRestriction.GUEST;
        if (userService.isAnonymousUser(currentUser)) {
            gender = SslGenderRestriction.GUEST;
        } else if (Gender.MALE.equals(currentUser.getGender())) {
            gender = SslGenderRestriction.MALE;
        } else if (Gender.FEMALE.equals(currentUser.getGender())) {
            gender = SslGenderRestriction.FEMALE;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("User Name: " + currentUser.getName() + " Gender: " + currentUser.getGender());
            LOG.debug("CMSSslGenderRestriction applied for " + gender.getCode());
        }
        return cmsSslGenderRestriction.getGender().equals(gender);
    }
}