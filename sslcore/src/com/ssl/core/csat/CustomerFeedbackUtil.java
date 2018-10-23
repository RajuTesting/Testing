package com.ssl.core.csat;

import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Util Class : Customer Feedback Util.
 *
 */
public class CustomerFeedbackUtil {
	
	private CustomerFeedbackUtil() {
	   	 //
	    }

    private static final String MOBILE = "mobile";

    /**
     * Gets UI Experience level on basis of the device.
     *
     * @param request {@link HttpServletRequest}.
     * @return {@link UiExperienceLevel#MOBILE OR {@link UiExperienceLevel#DESKTOP}}
     */
    public static UiExperienceLevel getMediumTypeRequest(final HttpServletRequest request) {
        final String sourceType = request.getHeader("User-Agent");
        UiExperienceLevel uiExperience = null;

        if (StringUtils.isNotEmpty(sourceType) && sourceType.toLowerCase().indexOf(MOBILE) >= 0) {
            uiExperience = UiExperienceLevel.MOBILE;
        } else {
            uiExperience = UiExperienceLevel.DESKTOP;
        }
        return uiExperience;
    }
}
