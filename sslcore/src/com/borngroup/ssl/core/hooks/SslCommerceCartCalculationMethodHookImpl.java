package com.borngroup.ssl.core.hooks;

import de.hybris.platform.commerceservices.order.hook.CommerceCartCalculationMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.PriceValue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;
import com.borngroup.ssl.core.strategies.impl.SslFindDeliveryCostStrategy;
import com.borngroup.ssl.core.util.GSTUtil;

/**
 * Hook : To perform cart calculation.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */

public class SslCommerceCartCalculationMethodHookImpl implements CommerceCartCalculationMethodHook {
    /**
     * SslFindDeliveryCostStrategy instance.
     */
    private SslFindDeliveryCostStrategy findDeliveryCostStrategy;
    /**
     * CommonI18NService instance.
     */
    private CommonI18NService commonI18NService;
    /**
     * ModelService instance.
     */
    private ModelService modelService;

    private CoreGSTTaxCalculationService coreGSTTaxCalculationService;

    /**
     * Logger instance.
     */
    private static final Logger LOG = Logger.getLogger(SslCommerceCartCalculationMethodHookImpl.class);

    /**
     * This method calculates the delivery cost and add to cart model.
     *
     * @param paramCommerceCartParameter - Instance of CommerceCartParameter.
     */
    @Override
    public void afterCalculate(final CommerceCartParameter paramCommerceCartParameter) {
        try {
            final CartModel cartModel = paramCommerceCartParameter.getCart();
            final CurrencyModel curr = cartModel.getCurrency();
            final int digits = curr.getDigits().intValue();
            final Double delCostBeforeProm = cartModel.getDeliveryCost();
            cartModel.setTotalPrice(Double.valueOf(commonI18NService.roundCurrency(cartModel.getTotalPrice().doubleValue()
                    - delCostBeforeProm.doubleValue(), digits)));
            final PriceValue deliCost = this.findDeliveryCostStrategy.getDeliveryCost(cartModel);
            double deliveryCost = 0.0;
            if (GSTUtil.isGSTSwitchOn()) {
                final List<String> cartEntries = new ArrayList<String>();
                for (final AbstractOrderEntryModel entry : cartModel.getEntries()) {
                    cartEntries.add(entry.getProduct().getCode());
                }
                if (!CollectionUtils.isEmpty(cartEntries)) {
                    // Check if all items are excluded from shipping charges no need to charge delivery charges
                    final List<String> excludedItems = getCoreGSTTaxCalculationService().getExcludedShippingSKU(cartEntries);
                    boolean needtoSkipDelivery = false;
                    for (final AbstractOrderEntryModel entry : cartModel.getEntries()) {
                        boolean needExclusion = false;
                        for (final String excludedItem : excludedItems) {
                            if (excludedItem.equals(entry.getProduct().getCode())) {
                                needExclusion = true;
                                needtoSkipDelivery = true;
                                break;
                            }
                        }
                        if (!needExclusion) {
                            needtoSkipDelivery = false;
                            break;
                        }
                    }
                    if (!needtoSkipDelivery) {
                        deliveryCost = deliCost.getValue();
                    }
                } else {
                    deliveryCost = deliCost.getValue();
                }
            } else {
                deliveryCost = deliCost.getValue();
            }
            cartModel.setDeliveryCost(Double.valueOf(deliveryCost));
            final double total = cartModel.getTotalPrice().doubleValue() + deliveryCost;
            final double totalRounded = this.commonI18NService.roundCurrency(total, digits);
            cartModel.setTotalPrice(Double.valueOf(totalRounded));
            modelService.save(cartModel);
        } catch (final Exception e) {
            LOG.error("Error while calculates the delivery cost and add to cart model with exception " + e.getMessage());
        }

    }

    @Override
    public void beforeCalculate(final CommerceCartParameter paramCommerceCartParameter) {
        // YTODO Auto-generated method stub

    }

    /**
     * Sets SslFindDeliveryCostStrategy.
     *
     * @param findDeliveryCostStrategy1 {@link SslFindDeliveryCostStrategy}.
     */
    public void setFindDeliveryCostStrategy(final SslFindDeliveryCostStrategy findDeliveryCostStrategy1) {
        this.findDeliveryCostStrategy = findDeliveryCostStrategy1;
    }

    /**
     * Sets CommonI18NService.
     *
     * @param commonI18NService1 {@link CommonI18NService}.
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService1) {
        this.commonI18NService = commonI18NService1;
    }

    /**
     * Sets ModelService.
     *
     * @param modelService1 {@link ModelService}.
     */
    public void setModelService(final ModelService modelService1) {
        this.modelService = modelService1;
    }

    /**
     * @return the coreGSTTaxCalculationService
     */
    public CoreGSTTaxCalculationService getCoreGSTTaxCalculationService() {
        return coreGSTTaxCalculationService;
    }

    /**
     * @param coreGSTTaxCalculationService the coreGSTTaxCalculationService to set
     */
    public void setCoreGSTTaxCalculationService(final CoreGSTTaxCalculationService coreGSTTaxCalculationService) {
        this.coreGSTTaxCalculationService = coreGSTTaxCalculationService;
    }

}
