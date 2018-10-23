/**
 *
 */
package com.borngroup.ssl.core.commerceservices.delivery.dao;

import de.hybris.platform.commerceservices.delivery.dao.CountryZoneDeliveryModeDao;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Interface: For delivery mode and shipping charges.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public interface SSLCountryZoneDeliveryModeDao extends CountryZoneDeliveryModeDao {

    /**
     * This method used to find delivery modes.
     * 
     * @param paramAbstractOrderModel - Instance of AbstractOrderModel.
     * @param country - Instance of CountryModel
     * @return Collection of {@link DeliveryModeModel}
     */

    Collection<DeliveryModeModel> findDeliveryModes(AbstractOrderModel paramAbstractOrderModel, CountryModel country);

    /**
     * This method used to find delivery cost and maximum threshold for the given shipping mode.
     * 
     * @param deliveryModeCode - Code for delivery mode.
     * @param maxThreshold - To on/off calculation to calculate max threshold.
     * @return Map of {@link String , BigDecimal}.
     */

    Map<String, BigDecimal> getDeliveryChargeForShippingMode(String deliveryModeCode, Boolean maxThreshold);

}
