package com.borngroup.ssl.core.strategies.impl;

import com.borngroup.ssl.core.events.OrderFlagsUpdateAsyncEvent;
import com.borngroup.ssl.core.events.PreOrderQuantityUpdateEvent;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.order.impl.DefaultCommercePlaceOrderStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CCAvenueWalletPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CODPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.DebitCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.FreeChargePaymentInfoModel;
import de.hybris.platform.core.model.order.payment.GiftCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.LoyaltyCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.NetBankingPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PayUPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.SSCreditsPaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.promotions.model.PromotionResultModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.variants.model.VariantProductModel;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;
import com.borngroup.ssl.core.product.service.impl.SslDefaultProductService;
import com.borngroup.ssl.core.services.SslCommerceStockService;

import com.ssl.core.payments.helper.SSLPaymentsHelper;
import de.hybris.platform.servicelayer.event.EventService;

public class DefaultSSLCommercePlaceOrderStrategy extends DefaultCommercePlaceOrderStrategy {
    private static final Logger LOG = Logger.getLogger(DefaultSSLCommercePlaceOrderStrategy.class);
    private SslDefaultProductService sslProductService;
    private CatalogVersionService catalogVersionService;
    private SearchRestrictionService searchRestrictionService;
    private SslCommerceStockService sslStockService;
    @Resource(name = "coreGSTTaxCalculationService")
    private CoreGSTTaxCalculationService coreGSTTaxCalculationService;
    /** Event Service. */
    @Resource(name = "eventService")
    private EventService eventService;

    @Override
    public CommerceOrderResult placeOrder(final CommerceCheckoutParameter parameter) throws InvalidCartException {
        CartModel cartModel;
        CommerceOrderResult result;
        cartModel = parameter.getCart();
        ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
        result = new CommerceOrderResult();
        beforePlaceOrder(parameter);
        if (getCalculationService().requiresCalculation(cartModel)) {
            LOG.warn(String.format("CartModel's [%s] requires calculation", cartModel.getCode()));
        }
        LOG.warn("Check for Price mismatch before converting to order from cart");
        if (!isCartValid(cartModel)) {
            throw new InvalidCartException("Order placement failed because cart has price mismatch: " + cartModel.getCode()
                    + "  between Total Price: " + cartModel.getTotalPrice().doubleValue() + " and Captured Price");
        }
        LOG.warn("No Price Mismatch");
        final CustomerModel customer = (CustomerModel) cartModel.getUser();
        ServicesUtil.validateParameterNotNull(customer, "Customer model cannot be null");
        final OrderModel orderModel = getOrderService().createOrderFromCart(cartModel);
        if (orderModel != null) {
            orderModel.setDate(new Date());
            orderModel.setSite(getBaseSiteService().getBaseSiteForUID(SslCoreConstants.SITE));
            orderModel.setStore(getBaseStoreService().getBaseStoreForUid(SslCoreConstants.SITE));
            orderModel.setLanguage(getCommonI18NService().getLanguage("en"));

            if (parameter.getSalesApplication() != null) {
                orderModel.setSalesApplication(parameter.getSalesApplication());
            }

            final Set<PromotionResultModel> emptySet = new HashSet<>();
            orderModel.setAllPromotionResults(emptySet);
            getModelService().saveAll(new Object[] { customer, orderModel });
            if (cartModel.getPaymentInfo() != null && cartModel.getPaymentInfo().getBillingAddress() != null) {
                final AddressModel billingAddress = cartModel.getPaymentInfo().getBillingAddress();
                orderModel.setPaymentAddress(billingAddress);
                orderModel.getPaymentInfo().setBillingAddress(getModelService().clone(billingAddress));
                getModelService().save(orderModel.getPaymentInfo());
            }

            getModelService().save(orderModel);
            getPromotionsService().transferPromotionsToOrder(cartModel, orderModel, false);
            try {
                getCalculationService().calculateTotals(orderModel, false);
                getExternalTaxesService().calculateExternalTaxes(orderModel);
            } catch (final CalculationException ex) {
                LOG.error((new StringBuilder("Failed to calculate order [")).append(orderModel).append("]").toString(), ex);
            }
            getModelService().refresh(orderModel);
            //getModelService().refresh(customer);
            result.setOrder(orderModel);
            beforeSubmitOrder(parameter, result);
            getOrderService().submitOrder(orderModel);
        } else {
            throw new IllegalArgumentException(
                    String.format("Order was not properly created from cart %s", cartModel.getCode()));
        }
        getExternalTaxesService().clearSessionTaxDocument();
        afterPlaceOrder(parameter, result);
        final PreOrderQuantityUpdateEvent preOrderQuantityUpdateEvent = new PreOrderQuantityUpdateEvent(orderModel);
        final OrderFlagsUpdateAsyncEvent orderFlagsUpdateAsyncEvent = new OrderFlagsUpdateAsyncEvent(orderModel, cartModel.getFccCardNumber());
        eventService.publishEvent(preOrderQuantityUpdateEvent);
        eventService.publishEvent(orderFlagsUpdateAsyncEvent);
        return result;
    }

    /**
     * Checks for if order is COD or not
     */
    public boolean isCODOrder(final CartModel cartModel) {

        for (final PaymentTransactionModel transacction : cartModel.getPaymentTransactions()) {
            if (transacction.getInfo() instanceof CODPaymentInfoModel) {
                return true;
            }
        }

        return false;
    }

    protected boolean isCartValid(final CartModel cartModel) {
        if (cartModel != null) {
            final String user = cartModel.getUser().getUid();
            final boolean forbiddenUser = SslCoreConstants.ADMIN.equalsIgnoreCase(user)
                    || SslCoreConstants.ANONYMOUS.equalsIgnoreCase(user);
            if (forbiddenUser) {
                LOG.error("Order placement failed because cart has invalid user: " + cartModel.getCode());
                return false;
            }

            final boolean cartHasEntries = cartModel.getEntries() != null && !cartModel.getEntries().isEmpty();

            if (!cartHasEntries) {
                LOG.error("Order placement failed because cart has no entries: " + cartModel.getCode());
                return false;
            }

            for (final AbstractOrderEntryModel entry : cartModel.getEntries()) {
                if (entry.getBasePrice() == null || entry.getBasePrice().doubleValue() == 0) {
                    LOG.error("Order placement failed because cart has  entry with 0 : " + cartModel.getCode());
                    return false;
                }
                if (entry.getTotalPrice() == null || entry.getTotalPrice().doubleValue() <= 0) {
                    LOG.error("Order placement failed because cart has entry with 0 as the total price is 0 or negative : "
                            + cartModel.getCode());
                    return false;
                }
            }

            final boolean isEgv = isEGiftOrder(cartModel);
            final boolean hasDeliveryAddress = isEgv || cartModel.getDeliveryAddress() != null;

            if (!hasDeliveryAddress) {
                LOG.error("Order placement failed because cart has no DeliveryAddress: " + cartModel.getCode());
                return false;
            }

            final Double totalPrice = cartModel.getTotalPrice();
            double collectedPriceFromTransactions = 0D;

            final List<PaymentTransactionModel> paymentTransactions = cartModel.getPaymentTransactions();

            if (CollectionUtils.isEmpty(paymentTransactions)) {
                LOG.error("Order placement failed because cart :" + cartModel.getCode() + " has no Payment Transactions ");
                return false;
            }

            for (final PaymentTransactionModel paymentTransactionModel : paymentTransactions) {
                final List<PaymentTransactionEntryModel> paymentTransactionEntries = paymentTransactionModel.getEntries();
                for (final PaymentTransactionEntryModel paymentTransactionEntryModel : paymentTransactionEntries) {
                    if (isGiftOrLoyaltyOrSsCredits(paymentTransactionModel) && paymentTransactionModel.getInfo().isCaptured()
                            && isAuthorized(paymentTransactionModel, paymentTransactionEntryModel)) {
                        collectedPriceFromTransactions += paymentTransactionModel.getPlannedAmount().doubleValue();
                        break;
                    } else if (((isCCAvenuePayment(paymentTransactionModel) || isCodPayment(paymentTransactionModel)) && isAuthorized(
                            paymentTransactionModel, paymentTransactionEntryModel))
                            || (SSLPaymentsHelper.isPaytmPaymentAuthorized(paymentTransactionModel, paymentTransactionEntryModel))) {
                        collectedPriceFromTransactions += paymentTransactionModel.getPlannedAmount().doubleValue();
                        break;
                    } else if (paymentTransactionModel.getInfo() instanceof PayUPaymentInfoModel
                            && isAuthorized(paymentTransactionModel, paymentTransactionEntryModel)) {
                        collectedPriceFromTransactions += paymentTransactionModel.getPlannedAmount().doubleValue();
                    }
                }
            }

            // This is path fix for upgrade release. When we return from
            // CCAvenue with order having multiple EGVs/GCs the order total
            // match fails if decimal not ignored.
            final boolean isOrderAmountCaptured = ((Math.abs(collectedPriceFromTransactions - totalPrice.doubleValue())) < 0.01);

            if (isOrderAmountCaptured) {
                return true;
            } else {
                LOG.error("Order placement failed because cart has price mismatch: " + cartModel.getCode() + " Total: "
                        + totalPrice.doubleValue() + " Captured: " + collectedPriceFromTransactions);
                return false;
            }

        }

        return false;

    }

    private boolean isGiftOrLoyaltyOrSsCredits(final PaymentTransactionModel paymentTransactionModel) {
        return paymentTransactionModel.getInfo() instanceof GiftCardPaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof LoyaltyCardPaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof SSCreditsPaymentInfoModel;
    }

    private boolean isAuthorized(final PaymentTransactionModel paymentTransactionModel,
            final PaymentTransactionEntryModel paymentTransactionEntryModel) {
        if (isGiftOrLoyaltyOrSsCredits(paymentTransactionModel)) {
            return PaymentTransactionType.CAPTURE.equals(paymentTransactionEntryModel.getType())
                    && (SslCoreConstants.SUCCESS.equalsIgnoreCase(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.ACCEPTED
                            .name().equals(paymentTransactionEntryModel.getTransactionStatus()));
        } else if (isCCAvenuePayment(paymentTransactionModel)) {
            return (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntryModel.getType())
            // SSL-3226
            // This is part of the Order drop task runner.
            // AUTHORIZATION_PENDING is for awaited status which should
            // also be
            // considered internally as authorized transaction to place
            // an order.
                    || PaymentTransactionType.AUTHORIZATION_PENDING.equals(paymentTransactionEntryModel.getType()))
                    && (SslCoreConstants.SUCCESS.equalsIgnoreCase(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.ACCEPTED
                            .name().equals(paymentTransactionEntryModel.getTransactionStatus()));
        } else if (isCodPayment(paymentTransactionModel)) {
            return PaymentTransactionType.COD_PENDING.equals(paymentTransactionEntryModel.getType())
                    && (SslCoreConstants.SUCCESS.equalsIgnoreCase(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.ACCEPTED
                            .name().equals(paymentTransactionEntryModel.getTransactionStatus()));
        } else if (paymentTransactionModel.getInfo() instanceof PayUPaymentInfoModel) {
            return (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntryModel.getType())
                    || PaymentTransactionType.AUTHORIZATION_PENDING.equals(paymentTransactionEntryModel.getType())
                    || PaymentTransactionType.CAPTURE.equals(paymentTransactionEntryModel.getType()))
                    && (SslCoreConstants.SUCCESS.equalsIgnoreCase(paymentTransactionEntryModel.getTransactionStatus())
                            || TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
        }
        return false;
    }

    private boolean isCodPayment(final PaymentTransactionModel paymentTransactionModel) {
        return paymentTransactionModel.getInfo() instanceof CODPaymentInfoModel;
    }

    private boolean isCCAvenuePayment(final PaymentTransactionModel paymentTransactionModel) {
        return paymentTransactionModel.getInfo() instanceof CreditCardPaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof DebitCardPaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof NetBankingPaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof FreeChargePaymentInfoModel
                || paymentTransactionModel.getInfo() instanceof CCAvenueWalletPaymentInfoModel;
    }

    private boolean isEGiftOrder(final CartModel cart) {
        final boolean hasEmail = StringUtils.isNotEmpty(cart.getEGiftRecipientEmail());
        final boolean singleEntry = cart.getEntries().size() == 1;
        final ProductModel product = cart.getEntries().iterator().next().getProduct();
        final boolean isegv = isEGiftProduct(product);
        return hasEmail && singleEntry && isegv;
    }

    private boolean isEGiftProduct(final ProductModel product) {
        if (Boolean.TRUE.equals(product.getEGift())) {
            return true;
        } else if (Boolean.TRUE.equals(getBaseProduct(product).getEGift())) {
            return true;
        }
        return false;
    }

    private ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }



    /**
     * @return the sslProductService
     */
    public SslDefaultProductService getSslProductService() {
        return sslProductService;
    }

    /**
     * @param sslProductService
     *        the sslProductService to set
     */
    public void setSslProductService(final SslDefaultProductService sslProductService) {
        this.sslProductService = sslProductService;
    }

    /**
     * @return the catalogVersionService
     */
    public CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }

    /**
     * @param catalogVersionService
     *        the catalogVersionService to set
     */
    public void setCatalogVersionService(final CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    /**
     * @return the searchRestrictionService
     */
    public SearchRestrictionService getSearchRestrictionService() {
        return searchRestrictionService;
    }

    /**
     * @param searchRestrictionService
     *        the searchRestrictionService to set
     */
    public void setSearchRestrictionService(final SearchRestrictionService searchRestrictionService) {
        this.searchRestrictionService = searchRestrictionService;
    }

    /**
     * @return the sslStockService
     */
    public SslCommerceStockService getSslStockService() {
        return sslStockService;
    }

    /**
     * @param sslStockService
     *        the sslStockService to set
     */
    public void setSslStockService(final SslCommerceStockService sslStockService) {
        this.sslStockService = sslStockService;
    }

}
