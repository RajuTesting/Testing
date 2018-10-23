/**
 *
 */
package com.borngroup.ssl.core.jalo;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.deliveryzone.jalo.ZoneDeliveryMode;
import de.hybris.platform.deliveryzone.jalo.ZoneDeliveryModeValue;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.delivery.JaloDeliveryModeException;
import de.hybris.platform.jalo.user.Address;
import de.hybris.platform.order.strategies.calculation.OrderRequiresCalculationStrategy;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.PriceValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.google.common.collect.ImmutableMap;

/**
 * Controller : To get delivery cost based on discount values.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class SSLZoneDeliveryMode extends ZoneDeliveryMode {

    /**
     * DEFAULT COUNTRY ISO code.
     */
    private static final String DEFAULT_COUNTRY_ISO = "IN";
    /**
     * CommonI18NService instance.
     */
    private CommonI18NService commonI18NService;
    /**
     * OrderRequiresCalculationStrategy instance.
     */
    private OrderRequiresCalculationStrategy orderRequiresCalculationStrategy;
    /**
     * ModelService instance.
     */
    private ModelService modelService;
    /**
     * FlexibleSearchService instance.
     */
    private FlexibleSearchService flexibleSearchService;

    /**
     * Instance of Logger.
     */
    private static final Logger LOG = Logger.getLogger(SSLZoneDeliveryMode.class);

    /**
     * This method used to get shipping cost based on applicable discount applied over it.
     * 
     * @param ctx - Instance of SessionContext.
     * @param order - Instance of AbstractOrder.
     * @return PriceValue.
     */
    @Override
    public PriceValue getCost(final SessionContext ctx, final AbstractOrder order) throws JaloDeliveryModeException {
        try {
            final Map<String, Object> params = new HashMap<String, Object>();
            final Address addr = order.getDeliveryAddress();
            if (addr == null) {
                params.put("country", commonI18NService.getCountry(DEFAULT_COUNTRY_ISO).getPk());
            } else {
                final Country country = addr.getCountry();
                if (country == null) {
                    throw new JaloDeliveryModeException(
                            "getCost():country of delivery address " + addr + " " + "was NULL in order" + order, 0);
                }
                params.put("country", country);
            }
            final AbstractOrderModel orderModel = modelService.get(order);
            final CurrencyModel curr = orderModel.getCurrency();
            if (curr == null) {
                throw new JaloDeliveryModeException("getCost(): currency was NULL in order " + order, 0);
            }
            final String propName = getPropertyName();
            if (propName == null) {
                throw new JaloDeliveryModeException("missing propertyname in deliverymode " + this, 0);
            }
            double amount = getCalculationBaseValue(ctx, order, propName);
            if (amount == 0.0) {
                return new PriceValue(curr.getIsocode(), 0.0, isNetAsPrimitive(ctx));
            }
            final double totalDiscounts = this.calculateDiscountValues(orderModel, false);
            final int digits = curr.getDigits();
            final double roundedTotalDiscounts = commonI18NService.roundCurrency(totalDiscounts, digits);
            amount = amount - roundedTotalDiscounts;
            // Cant put check for equals zero here as we can get zero amount after applying coupon in that case we add shipping fees.
            if (amount < 0.0) {
                amount = 0.0;
            }
            params.put("me", this);
            params.put("curr", curr.getPk());
            params.put("amount", new Double(amount));
            return this.getDataFromFlexiSearch(params, curr, amount, ctx);
        } catch (Exception e) {
            LOG.error("Error comes in method getCost inside class SSLZoneDeliveryMode with exception" + e.getMessage());
            final CurrencyModel curr = commonI18NService.getCurrency(SslCoreConstants.COUNTRY_CURRENCY_INDIA);
            return new PriceValue(curr.getIsocode(), 0.0, isNetAsPrimitive(ctx));
        }
    }

    /**
     * This method used to get data from flexible search.
     * 
     * @param params - Input map of parameters.
     * @param curr - CurrencyModel instance.
     * @param amount - double.
     * @param ctx - SessionContext.
     * @return List - flexible search data.
     */
    private PriceValue getDataFromFlexiSearch(final Map<String, Object> params, final CurrencyModel curr, final double amount,
            final SessionContext ctx) {
        final StringBuilder query = new StringBuilder(StringUtils.EMPTY).append("SELECT {v.pk} ").append("FROM {")
                .append("ZoneDeliveryModeValue").append(" AS v ").append("JOIN ").append("ZoneCountryRelation").append(" AS z2cRel ")
                .append("ON {v.").append("zone").append("}={z2cRel.").append("source").append("} } ").append("WHERE ").append("{v.")
                .append("deliveryMode").append("} = ?me AND ").append("{v.").append("currency").append("} = ?curr AND ").append("{v.")
                .append("minimum").append("} <= ?amount AND ").append("{z2cRel.").append("target").append("} = ?country ")
                .append("ORDER BY {v.").append("minimum").append("} DESC ");

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query.toString(), params);
        final List<Class> resultClassList = new ArrayList<>();
        resultClassList.add(ZoneDeliveryModeValue.class);
        flexibleSearchQuery.setResultClassList(resultClassList);
        List<Object> values = getFlexibleSearchService().search(flexibleSearchQuery).getResult();
        if ((values.isEmpty()) && (!(curr.getBase())) && (this.getBaseCurrency() != null)) {
            params.put("curr", this.getBaseCurrency());
            values = getFlexibleSearchService().search(query.toString(), params).getResult();
        }
        if (values.isEmpty()) {
            LOG.error("No delivery price defined for mode " + this + ", currency " + curr + " and amount " + amount);
            return new PriceValue(curr.getIsocode(), 0.0, isNetAsPrimitive(ctx));
        }
        final ZoneDeliveryModeValueModel bestMatch = (ZoneDeliveryModeValueModel) values.get(0);
        if (bestMatch != null) {
            return new PriceValue(curr.getIsocode(), bestMatch.getValue(), isNetAsPrimitive(ctx));
        }
        return new PriceValue(curr.getIsocode(), 0.0, isNetAsPrimitive(ctx));
    }

    /**
     * Returns the base currency.
     * 
     * @return Currency
     */
    private Currency getBaseCurrency() {
        final Map value = ImmutableMap.of("base", Boolean.TRUE);
        final SearchResult res = getSession().getFlexibleSearch().search(
                "SELECT {" + Item.PK + "} FROM {Currency} WHERE {" + "base" + "} = ?" + "base", value,
                Collections.singletonList(Currency.class), false, true, 0, -1);
        final Collection coll = res.getResult();
        if (!(coll.isEmpty())) {
            return ((Currency) coll.iterator().next());
        }
        return null;
    }

    /**
     * Returns the total discount for this abstract order.
     * 
     * @param order - Instance of AbstractOrderModel.
     * @param recalculate <code>true</code> forces a recalculation
     * @return totalDiscounts
     */
    private double calculateDiscountValues(final AbstractOrderModel order, final boolean recalculate) {
        try {
            if (recalculate || orderRequiresCalculationStrategy.requiresCalculation(order)) {
                final List<DiscountValue> discountValues = order.getGlobalDiscountValues();
                if (discountValues != null && !discountValues.isEmpty()) {
                    // clean discount value list -- do we still need it?
                    // removeAllGlobalDiscountValues();
                    //
                    final CurrencyModel curr = order.getCurrency();
                    final String iso = curr.getIsocode();

                    final int digits = curr.getDigits();
                    final double discountablePrice = order.getSubtotal()
                            + (order.isDiscountsIncludeDeliveryCost() ? order.getDeliveryCost() : 0.0)
                            + (order.isDiscountsIncludePaymentCost() ? order.getPaymentCost() : 0.0);
                    /*
                     * apply discounts to this order's total
                     */
                    final List appliedDiscounts = DiscountValue.apply(1.0, discountablePrice, digits,
                            this.convertDiscountValues(order, discountValues), iso);
                    // store discount values
                    // order.setGlobalDiscountValues(appliedDiscounts);
                    return DiscountValue.sumAppliedValues(appliedDiscounts);
                }
                return 0.0;
            } else {
                return DiscountValue.sumAppliedValues(order.getGlobalDiscountValues());
            }
        } catch (Exception e) {
            LOG.error("Error inside method calculateDiscountValues with exception " + e.getMessage());
        }
        return 0.0;
    }

    /**
     * Returns the list of converted discount values..
     *
     * @param order - Instance of AbstractOrderModel.
     * @param dvs - List of discount values.
     * @return totalDiscounts
     */
    private List convertDiscountValues(final AbstractOrderModel order, final List<DiscountValue> dvs) {
        if (dvs == null) {
            return null;
        }
        if (dvs.isEmpty()) {
            return dvs;
        }
        final CurrencyModel curr = order.getCurrency();
        final String iso = curr.getIsocode();
        final List<DiscountValue> tmp = new ArrayList(dvs);
        /*
         * convert absolute discount values to order currency is needed
         */
        final Map<String, CurrencyModel> currencyMap = new HashMap<String, CurrencyModel>();
        for (int i = 0; i < tmp.size(); i++) {
            final DiscountValue discountValue = tmp.get(i);
            if (discountValue.isAbsolute() && !iso.equals(discountValue.getCurrencyIsoCode())) {
                // get currency
                CurrencyModel dCurr = currencyMap.get(discountValue.getCurrencyIsoCode());
                if (dCurr == null) {
                    dCurr = commonI18NService.getCurrency(discountValue.getCurrencyIsoCode());
                    currencyMap.put(discountValue.getCurrencyIsoCode(), dCurr);
                }
                // replace old value in temp list
                tmp.set(i,
                        new DiscountValue(discountValue.getCode(), commonI18NService.convertAndRoundCurrency(dCurr.getConversion()
                                .doubleValue(), curr.getConversion().doubleValue(), curr.getDigits().intValue(), discountValue.getValue()),
                                true, iso));
            }
        }
        return tmp;
    }

    /**
     * Sets CommonI18NService Service.
     *
     * @param commonI18NService1 {@link CommonI18NService}.
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService1) {
        this.commonI18NService = commonI18NService1;
    }

    /**
     * Sets Order Calculation Strategy.
     *
     * @param orderRequiresCalculationStrategy1 {@link OrderRequiresCalculationStrategy}.
     */

    public void setOrderRequiresCalculationStrategy(final OrderRequiresCalculationStrategy orderRequiresCalculationStrategy1) {
        this.orderRequiresCalculationStrategy = orderRequiresCalculationStrategy1;
    }

    /**
     * Sets Model Service.
     *
     * @param modelService1 {@link ModelService}.
     */

    public void setModelService(final ModelService modelService1) {
        this.modelService = modelService1;
    }

    /**
     * Sets Flexible Search.
     *
     * @param flexibleSearchService1 {@link FlexibleSearchService}.
     */

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService1) {
        this.flexibleSearchService = flexibleSearchService1;
    }

    /**
     * Get Flexible Search.
     *
     * @return {@link FlexibleSearchService}.
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

}
