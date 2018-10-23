/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

/**
 * @author sonamkaushik
 *
 *         Comparator to compare two objects of type PointOfServiceModel; extensible to any sort of object
 */
public class SSLLocationsComparator implements Comparator<Object> {

    private static String REGEX = "[^\\w\\s]";

    @Override
    public int compare(final Object o1, final Object o2) {
        if (null != o1 && null != o2) {
            if (o1 instanceof PointOfServiceDistanceData && o2 instanceof PointOfServiceDistanceData) {
                if (((PointOfServiceDistanceData) o1).getPointOfService() != null
                        && StringUtils.isNotEmpty(((PointOfServiceDistanceData) o1).getPointOfService().getDisplayName())
                        && ((PointOfServiceDistanceData) o2).getPointOfService() != null
                        && StringUtils.isNotEmpty(((PointOfServiceDistanceData) o2).getPointOfService().getDisplayName())) {
                    return ((PointOfServiceDistanceData) o1)
                            .getPointOfService()
                            .getDisplayName()
                            .replaceAll(REGEX, " ")
                            .trim()
                            .compareToIgnoreCase(
                                    ((PointOfServiceDistanceData) o2).getPointOfService().getDisplayName().replaceAll(REGEX, " ").trim());
                }
            } else if (o1 instanceof String && o2 instanceof String) {
                return ((String) o1).replaceAll(REGEX, "").trim().compareToIgnoreCase(((String) o2).replaceAll(REGEX, "").trim());
            }
        }
        return 0;
    }

}
