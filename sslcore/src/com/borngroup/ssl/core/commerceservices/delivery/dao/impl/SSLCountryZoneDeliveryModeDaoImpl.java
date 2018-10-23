package com.borngroup.ssl.core.commerceservices.delivery.dao.impl;

import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.commerceservices.delivery.dao.impl.DefaultCountryZoneDeliveryModeDao;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.deliveryzone.jalo.ZoneDeliveryModeValue;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.VoucherModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.commerceservices.delivery.dao.SSLCountryZoneDeliveryModeDao;
import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * Controller : To get delivery mode and shipping charges.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class SSLCountryZoneDeliveryModeDaoImpl extends DefaultCountryZoneDeliveryModeDao implements SSLCountryZoneDeliveryModeDao {

    /**
     * DeliveryService instance.
     */
    private DeliveryService deliveryService;
    /**
     * DEFAULT COUNTRY ISO code.
     */
    private static final String DEFAULT_COUNTRY_ISO = "IN";
    /**
     * CommonI18NService instance.
     */
    private CommonI18NService commonI18NService;
    /**
     * CartService instance.
     */
    private CartService cartService;

    /**
     * VoucherService instance.
     */
    private VoucherService voucherService;

    /**
     * Logger instance.
     */

    private static final Logger LOG = Logger.getLogger(SSLCountryZoneDeliveryModeDaoImpl.class);

    /**
     * This method used to find delivery modes.
     * 
     * @param abstractOrder - Instance of AbstractOrderModel.
     * @param country - Instance of CountryModel
     * @return Collection of {@link DeliveryModeModel}
     */

    @Override
    public Collection<DeliveryModeModel> findDeliveryModes(final AbstractOrderModel abstractOrder, final CountryModel country) {

        final StringBuilder query = new StringBuilder("SELECT DISTINCT {zdm:").append("pk").append("}");
        query.append(" FROM { ").append("ZoneDeliveryModeValue").append(" AS val");
        query.append(" JOIN ").append("ZoneDeliveryMode").append(" AS zdm");
        query.append(" ON {val:").append("deliveryMode").append("}={zdm:").append("pk").append('}');
        query.append(" JOIN ").append("ZoneCountryRelation").append(" AS z2c");
        query.append(" ON {val:").append("zone").append("}={z2c:").append("source").append('}');
        query.append(" JOIN ").append("BaseStore2DeliveryModeRel").append(" AS s2d");
        query.append(" ON {val:").append("deliveryMode").append("}={s2d:").append("target").append('}');
        query.append(" } WHERE {val:").append("currency").append("}=?currency");
        query.append(" AND {z2c:").append("target").append("}=?deliveryCountry");
        query.append(" AND {s2d:").append("source").append("}=?store");
        query.append(" AND {zdm:").append("net").append("}=?net");
        query.append(" AND {zdm:").append("active").append("}=?active");

        final Map params = new HashMap();
        params.put("deliveryCountry", country);
        params.put("currency", abstractOrder.getCurrency());
        params.put("net", abstractOrder.getNet());
        params.put("active", Boolean.TRUE);
        params.put("store", abstractOrder.getStore());

        return doSearch(query.toString(), params, DeliveryModeModel.class);

    }

    /**
     * This method used to find delivery cost and maximum threshold for the given shipping mode.
     * 
     * @param deliveryModeCode - Code for delivery mode.
     * @param maxThreshold - To on/off calculation to calculate max threshold.
     * @return Map of {@link String , BigDecimal}.
     */
    @Override
    public Map<String, BigDecimal> getDeliveryChargeForShippingMode(final String deliveryModeCode, final Boolean maxThreshold) {
        final Map<String, BigDecimal> deliveryCostMap = new HashMap<>();
        try {
            PriceValue priceValue = null;
            CartModel cartModel = null;
            Double deliveryCost = 0d;
            Double totalPrice = 0d;
            DeliveryModeModel deliveryMode = null;
            if (this.getCartService().hasSessionCart()) {
                cartModel = this.getCartService().getSessionCart();
                deliveryCost = cartModel.getDeliveryCost();
                totalPrice = cartModel.getTotalPrice();
                deliveryMode = cartModel.getDeliveryMode();
            }
            final DeliveryModeModel deliveryModeModel = (cartModel != null && deliveryMode != null) ?
                    deliveryMode : this.getDeliveryService().getDeliveryModeForCode(deliveryModeCode);
            final CurrencyModel curr = this.commonI18NService.getCurrency(SslCoreConstants.COUNTRY_CURRENCY_INDIA);
            final Map<String, Object> params = new HashMap<>();
            params.put("country", this.commonI18NService.getCountry(DEFAULT_COUNTRY_ISO).getPk());
            params.put("me", deliveryModeModel);
            params.put("curr", curr.getPk());
            final List<Object> values = this.getDataFromFlexiSearch(params);
            if (CollectionUtils.isNotEmpty(values) && values.get(0) instanceof ZoneDeliveryModeValueModel) {
                final ZoneDeliveryModeValueModel bestMatch = (ZoneDeliveryModeValueModel) values.get(0);
                priceValue = new PriceValue(curr.getIsocode(), bestMatch.getValue(), false);
                deliveryCostMap.put(SslCoreConstants.SSL_SHIPPING_DELIVERY_COST, this.getRoundedPrice(priceValue.getValue()));
                if (maxThreshold == Boolean.TRUE && values.size() > 1 && values.get(1) != null) {
                    final ZoneDeliveryModeValueModel bestMatchDeliveryMode = (ZoneDeliveryModeValueModel) values.get(1);
                    priceValue = new PriceValue(curr.getIsocode(), bestMatchDeliveryMode.getMinimum(), false);
                    double cartTotal;
                    cartTotal = isVoucherWithFreeShippingFees(cartModel) ? new BigDecimal(totalPrice).doubleValue()
                            : new BigDecimal(totalPrice).subtract(new BigDecimal(deliveryCost)).doubleValue();
                    cartTotal = this.getRoundedPrice(cartTotal).doubleValue();
                    deliveryCostMap.put(SslCoreConstants.SSL_FREE_SHIPPING, this.getRoundedPrice(priceValue.getValue()));
                    if ((Double.compare(priceValue.getValue(), cartTotal)) > 0) {
                        priceValue = new PriceValue(curr.getIsocode(), priceValue.getValue() - cartTotal, false);
                        deliveryCostMap.put(SslCoreConstants.SSL_SHIPPING_MAX_THRESHOLD, this.getRoundedPrice(priceValue.getValue()));
                    }
                }
            }

        } catch (Exception e) {
            LOG.error("Error inside method getDeliveryChargeForShippingMode with exception " + e.getMessage());
        }
        return deliveryCostMap;
    }

    /**
     * This method checks for shipping fees in voucher applied on cart model.
     * 
     * @param cartModel - Instance of CartModel.
     * @return boolean
     */
    private boolean isVoucherWithFreeShippingFees(final CartModel cartModel) {
        if(cartModel != null) {
            final Collection<String> voucherCodes = getVoucherService().getAppliedVoucherCodes(cartModel);
            for (final String code : voucherCodes) {
                try {
                    final VoucherModel voucher = getVoucherService().getVoucher(code);
                    if (voucher != null && voucher.getFreeShipping()) {
                        return true;
                    }
                } catch (final Exception e) {
                    LOG.error("Error while checks for shipping fees in voucher applied on cart mode with exception " + e.getMessage());
                }
            }
        }
        return false;
    }

    /**
     * This method used to get data from flexible search.
     * 
     * @param params - Input map of parameters.
     * @return List - flexible search data.
     */
    private List getDataFromFlexiSearch(final Map params) {
        final StringBuilder query = new StringBuilder(StringUtils.EMPTY).append("SELECT {v.pk} ").append("FROM {")
                .append("ZoneDeliveryModeValue").append(" AS v ").append("JOIN ").append("ZoneCountryRelation").append(" AS z2cRel ")
                .append("ON {v.").append("zone").append("}={z2cRel.").append("source").append("} } ").append("WHERE ").append("{v.")
                .append("deliveryMode").append("} = ?me AND ").append("{v.").append("currency").append("} = ?curr AND ").append("{z2cRel.")
                .append("target").append("} = ?country ").append("ORDER BY {v.").append("minimum").append("} ASC ");

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query.toString(), params);
        final List<Class> resultClassList = new ArrayList<>();
        resultClassList.add(ZoneDeliveryModeValue.class);
        flexibleSearchQuery.setResultClassList(resultClassList);
        return getFlexibleSearchService().search(flexibleSearchQuery).getResult();
    }

    /**
     * This method used to find round off price.
     * 
     * @param value - Input to be rounded.
     * @return BigDecimal - rounded price.
     */
    private BigDecimal getRoundedPrice(final double value) {
        final BigDecimal finalPrice = new BigDecimal(value);
        return finalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Sets Delivery Service.
     *
     * @param deliveryService1 {@link DeliveryService}.
     */
    public void setDeliveryService(final DeliveryService deliveryService1) {
        this.deliveryService = deliveryService1;
    }

    /**
     * Get Delivery Service.
     *
     * @return {@link DeliveryService}.
     */
    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    /**
     * Get CommonI18NService Service.
     *
     * @return {@link CommonI18NService}.
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
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
     * Get Cart Service.
     *
     * @return {@link CartService}.
     */
    public CartService getCartService() {
        return cartService;
    }

    /**
     * Sets Cart Service.
     *
     * @param cartService1 {@link CartService}.
     */
    public void setCartService(final CartService cartService1) {
        this.cartService = cartService1;
    }

    /**
     * Get Voucher Service.
     *
     * @return {@link VoucherService}.
     */
    public VoucherService getVoucherService() {
        return voucherService;
    }

    /**
     * Sets Voucher Service.
     *
     * @param voucherService1 {@link VoucherService}.
     */
    public void setVoucherService(final VoucherService voucherService1) {
        this.voucherService = voucherService1;
    }

}