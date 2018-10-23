/**
 *
 */
package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.commerceservices.delivery.dao.PickupDeliveryModeDao;
import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.commerceservices.strategies.DeliveryModeLookupStrategy;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.commerceservices.delivery.dao.SSLCountryZoneDeliveryModeDao;

/**
 * Controller : To get delivery mode strategy for SSL.
 * <p/>
 * Created by ravi.yadav@nagarro.com for SSLM- 794.
 *
 * @author SSL
 */
public class SSLDeliveryModeLookupStrategy implements DeliveryModeLookupStrategy {
    /**
     * SSLCountryZoneDeliveryModeDao instance.
     */
    private SSLCountryZoneDeliveryModeDao countryZoneDeliveryModeDao;
    /**
     * PickupDeliveryModeDao instance.
     */
    private PickupDeliveryModeDao pickupDeliveryModeDao;
    /**
     * CommonI18NService instance.
     */
    private CommonI18NService commonI18NService;
    /**
     * DEFAULT COUNTRY ISO code.
     */
    private static final String DEFAULT_COUNTRY_ISO = "IN";

    /**
     * Logger instance.
     */
    private static final Logger LOG = Logger.getLogger(SSLDeliveryModeLookupStrategy.class);

    /**
     * This method used to get selected delivery modes based on order model.
     *
     * @param abstractOrderModel - Instance of AbstractOrderModel.
     * @return List of {@link DeliveryModeModel}
     */

    @Override
    public List<DeliveryModeModel> getSelectableDeliveryModesForOrder(final AbstractOrderModel abstractOrderModel) {
        try {
            final boolean isEgvOrder = this.checkEGiftOrder(abstractOrderModel);
            if (this.isPickUpOnlyOrder(abstractOrderModel)) {
                return new ArrayList<DeliveryModeModel>(
                        getPickupDeliveryModeDao().findPickupDeliveryModesForAbstractOrder(abstractOrderModel));
            } else if (!isEgvOrder) {
                if (CollectionUtils.isNotEmpty(abstractOrderModel.getEntries())
                        && abstractOrderModel.getEntries().iterator().next().getDeliveryMode() instanceof PickUpDeliveryModeModel) {
                    final List<DeliveryModeModel> pickupDeliveryModes = new ArrayList<>();
                    pickupDeliveryModes.add(abstractOrderModel.getEntries().iterator().next().getDeliveryMode());
                    return pickupDeliveryModes;
                }
                final List<DeliveryModeModel> deliveryModes = new ArrayList<DeliveryModeModel>();
                final AddressModel deliveryAddress = abstractOrderModel.getDeliveryAddress();
                final CurrencyModel currency = abstractOrderModel.getCurrency();
                if (currency != null && deliveryAddress != null && deliveryAddress.getCountry() != null) {
                    deliveryModes.addAll(getCountryZoneDeliveryModeDao().findDeliveryModes(abstractOrderModel));
                    return deliveryModes;
                } else if (currency != null) {
                    deliveryModes.addAll(getCountryZoneDeliveryModeDao().findDeliveryModes(abstractOrderModel,
                            commonI18NService.getCountry(DEFAULT_COUNTRY_ISO)));

                }
                return deliveryModes;
            }
        } catch (final Exception e) {
            LOG.error("Error while getting delivery mode for order with exception " + e.getMessage());
        }
        return ListUtils.EMPTY_LIST;
    }

    /**
     * @param abstractOrderModel
     * @return
     */
    @SuppressWarnings("boxing")
    private boolean checkEGiftOrder(final AbstractOrderModel abstractOrderModel) {
        for (final AbstractOrderEntryModel entry : abstractOrderModel.getEntries()) {
            if (entry.getProduct().getEGift()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method used to find pick up only order.
     *
     * @param abstractOrderModel - Instance of AbstractOrderModel.
     * @return boolean - Found or not.
     */
    protected boolean isPickUpOnlyOrder(final AbstractOrderModel abstractOrderModel) {
        if (CollectionUtils.isNotEmpty(abstractOrderModel.getEntries())) {
            for (final AbstractOrderEntryModel entry : abstractOrderModel.getEntries()) {
                if (entry.getDeliveryPointOfService() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get Country Zone Delivery Mode.
     *
     * @return {@link SSLCountryZoneDeliveryModeDao}.
     */

    public SSLCountryZoneDeliveryModeDao getCountryZoneDeliveryModeDao() {
        return countryZoneDeliveryModeDao;
    }

    /**
     * Get Pickup Delivery Mode.
     *
     * @return {@link PickupDeliveryModeDao}.
     */
    public PickupDeliveryModeDao getPickupDeliveryModeDao() {
        return pickupDeliveryModeDao;
    }

    /**
     * Sets Country Zone Delivery Mode.
     *
     * @param countryZoneDeliveryModeDao1 {@link SSLCountryZoneDeliveryModeDao}.
     */
    public void setCountryZoneDeliveryModeDao(final SSLCountryZoneDeliveryModeDao countryZoneDeliveryModeDao1) {
        this.countryZoneDeliveryModeDao = countryZoneDeliveryModeDao1;
    }

    /**
     * Sets Pickup Delivery Mode.
     *
     * @param pickupDeliveryModeDao1 {@link PickupDeliveryModeDao}.
     */
    public void setPickupDeliveryModeDao(final PickupDeliveryModeDao pickupDeliveryModeDao1) {
        this.pickupDeliveryModeDao = pickupDeliveryModeDao1;
    }

    /**
     * Sets CommonI18NService service.
     *
     * @param commonI18NService1 {@link CommonI18NService}.
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService1) {
        this.commonI18NService = commonI18NService1;
    }

}