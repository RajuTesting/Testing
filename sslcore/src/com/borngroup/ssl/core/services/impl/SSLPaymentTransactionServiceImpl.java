/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CCAvenueWalletPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CODPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.DebitCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.FreeChargePaymentInfoModel;
import de.hybris.platform.core.model.order.payment.GiftCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.LoyaltyCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.NetBankingPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PayUPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaytmPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.SSCreditsPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCCAvenueConstants;
import com.borngroup.ssl.core.services.SSLPaymentTransactionService;
import com.ssl.paymentgateways.enums.PayuPaymentMode;

/**
 * The Class SSLPaymentTransactionServiceImpl.
 *
 * @author midhun.bose
 */
public class SSLPaymentTransactionServiceImpl implements SSLPaymentTransactionService {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SSLPaymentTransactionServiceImpl.class);

    private static final String NON_SEAMLESS_CCAVENUE_MERCHANT_ID = "nonseamless.ccavenue.merchant_id";

    /** The Constant ONLINE_PAYMENT_MODE. */
    private static final String ONLINE_PAYMENT_MODE = "online";

    /** The Constant COD_PAYMENT_MODE. */
    private static final String COD_PAYMENT_MODE = "cod";

    /** The cart service. */
    @Resource(name = "cartService")
    private CartService cartService;

    /** The model service. */
    @Resource(name = "modelService")
    private ModelService modelService;

    /** The payment mode service. */
    @Resource(name = "paymentModeService")
    private PaymentModeService paymentModeService;

    /** The user service. */
    @Resource(name = "userService")
    private UserService userService;

    /** The common i18 n service. */
    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;

    /**
     * @return the commonI18NService
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    /**
     * @param commonI18NService
     *        the commonI18NService to set
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    /** The common i18 n service. */
    @Resource
    private EnumerationService enumerationService;

    /**
     * @return the enumerationService
     */
    public EnumerationService getEnumerationService() {
        return enumerationService;
    }

    /**
     * @param enumerationService
     *        the enumerationService to set
     */
    public void setEnumerationService(final EnumerationService enumerationService) {
        this.enumerationService = enumerationService;
    }

    @Override
    public void saveCreditCardTransaction(final Map<String, String> resultMap) {
        final CartModel cartModel = cartService.getSessionCart();
        saveCreditCard(cartModel, resultMap);
    }

    @Override
    public void saveCreditCard(final CartModel cartModel, final Map<String, String> resultMap) {
        final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final CreditCardPaymentInfoModel cardPaymentInfoModel = (CreditCardPaymentInfoModel) modelService
                .create(CreditCardPaymentInfoModel.class);
        cardPaymentInfoModel.setTypeOfPayment("Credit Card Payment");
        cardPaymentInfoModel.setMerchantIdUsed(Config.getParameter(NON_SEAMLESS_CCAVENUE_MERCHANT_ID));
        transaction.setInfo(createCreditCardPaymentInfo(customerModel, resultMap, cardPaymentInfoModel));
        transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        transaction.setCurrency(commonI18NService.getCurrency(resultMap.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CURRENCY)));
        transaction.setOrder(cartModel);
        transaction.setOwner(customerModel);
        transaction.setPlannedAmount(new BigDecimal(resultMap.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_AMOUNT)));
        transaction.setRequestToken(resultMap.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        transaction.setRequestId(resultMap.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        modelService.saveAll(transaction, cardPaymentInfoModel);
        createPaymentTransactionEntry(transaction, resultMap);
        logTransactionDetails(transaction);
    }

    @Override
    public void saveDebitCardTransaction(final Map<String, String> response) {
        final CartModel cartModel = cartService.getSessionCart();
        saveDebitCard(cartModel, response);
    }

    @Override
    public void saveDebitCard(final CartModel cartModel, final Map<String, String> response) {
        final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final DebitCardPaymentInfoModel cardPaymentInfoModel = (DebitCardPaymentInfoModel) modelService
                .create(DebitCardPaymentInfoModel.class);
        cardPaymentInfoModel.setTypeOfPayment("Debit Card Payment");
        cardPaymentInfoModel.setMerchantIdUsed(Config.getParameter(NON_SEAMLESS_CCAVENUE_MERCHANT_ID));
        transaction.setInfo(createDebitCardPaymentInfo(customerModel, response, cardPaymentInfoModel));
        transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        transaction.setCurrency(commonI18NService.getCurrency(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CURRENCY)));
        transaction.setOrder(cartModel);
        transaction.setOwner(customerModel);
        transaction.setPlannedAmount(new BigDecimal(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_AMOUNT)));
        transaction.setRequestToken(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        transaction.setRequestId(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        modelService.saveAll(transaction, cardPaymentInfoModel);
        createPaymentTransactionEntry(transaction, response);
        logTransactionDetails(transaction);
    }

    @Override
    public void saveNetbankingTransaction(final Map<String, String> response) {
        final CartModel cartModel = cartService.getSessionCart();
        saveNetBanking(cartModel, response);
    }

    @Override
    public void saveNetBanking(final CartModel cartModel, final Map<String, String> response) {
        final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final NetBankingPaymentInfoModel netBankingPaymentInfoModel = modelService.create(NetBankingPaymentInfoModel.class);
        netBankingPaymentInfoModel.setTypeOfPayment("Net Banking Payment");
        netBankingPaymentInfoModel.setUser(customerModel);
        netBankingPaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        netBankingPaymentInfoModel.setCaptured(true);
        netBankingPaymentInfoModel.setMerchantIdUsed(Config.getParameter(NON_SEAMLESS_CCAVENUE_MERCHANT_ID));
        transaction.setInfo(netBankingPaymentInfoModel);
        transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        transaction.setCurrency(commonI18NService.getCurrency(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CURRENCY)));
        transaction.setOrder(cartModel);
        transaction.setOwner(customerModel);
        transaction.setPlannedAmount(new BigDecimal(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_AMOUNT)));
        transaction.setRequestToken(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        transaction.setRequestId(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
        modelService.saveAll(transaction, netBankingPaymentInfoModel);
        createPaymentTransactionEntry(transaction, response);
        logTransactionDetails(transaction);
    }

    @Override
    public void savePaymentMode(final String paymentMode) {
        final CartModel cartModel = cartService.getSessionCart();
        cartModel.setPaymentMode(paymentModeService.getPaymentModeForCode(paymentMode));
        modelService.save(cartModel);
    }

    @Override
    public void saveCODTransaction(final double amount) {
        final CartModel cartModel = cartService.getSessionCart();

        final CustomerModel user = (CustomerModel) userService.getCurrentUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final CODPaymentInfoModel codPaymentInfoModel = modelService.create(CODPaymentInfoModel.class);
        codPaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        codPaymentInfoModel.setUser(user);
        codPaymentInfoModel.setTypeOfPayment("COD Payment");
        transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        transaction.setPlannedAmount(BigDecimal.valueOf(amount));
        transaction.setCurrency(cartModel.getCurrency());
        transaction.setOrder(cartModel);
        transaction.setInfo(codPaymentInfoModel);
        transaction.setRequestToken(UUID.randomUUID().toString());
        transaction.setRequestId(UUID.randomUUID().toString());
        modelService.saveAll(transaction, codPaymentInfoModel);
        createPaymentTransactionEntry(transaction, null);
        logTransactionDetails(transaction);
        modelService.refresh(cartModel);
    }

    @Override
    public void saveFCCTransaction(final String amount) {
        final CartModel cartModel = cartService.getSessionCart();
        final BigDecimal redeemAmount = new BigDecimal(amount);
        final UserModel currentCustomer = userService.getCurrentUser();

        final LoyaltyCardPaymentInfoModel loyaltyCardPaymentInfoModel = modelService.create(LoyaltyCardPaymentInfoModel.class);

        loyaltyCardPaymentInfoModel.setTypeOfPayment("Loyalty Card Payment");
        loyaltyCardPaymentInfoModel.setUser(currentCustomer);
        loyaltyCardPaymentInfoModel.setSslAmountRedeemed(amount);
        loyaltyCardPaymentInfoModel.setPrimaryCardNumber(((CustomerModel) currentCustomer).getSslLoyaltyDetail().getPrimaryCardNumber());
        loyaltyCardPaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        transaction.setPlannedAmount(redeemAmount);
        transaction.setCurrency(cartModel.getCurrency());
        transaction.setOrder(cartModel);
        transaction.setInfo(loyaltyCardPaymentInfoModel);
        transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        transaction.setRequestToken(UUID.randomUUID().toString());
        transaction.setRequestId(UUID.randomUUID().toString());
        modelService.saveAll(transaction, loyaltyCardPaymentInfoModel);
        createPaymentTransactionEntry(transaction, null);
        logTransactionDetails(transaction);
        modelService.refresh(cartModel);
    }

    /**
     * Sets the card type and owner.
     *
     * @param info the info
     * @param response the response
     */
    private void setCardTypeAndOwner(final CreditCardPaymentInfoModel info, final Map<String, String> response) {
        // SSL-2866 - Thirumalai - 15 Mar 2016
        // CCAVENUE_CARD_TYPE value got from orderOptionType is now stored in
        // CardPaymentInfoModel.TypeOfPayment.
        // In case of non-available card name received, default to SWITCH
        // This is causing an error when creating the order.
        final String cardType = response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CARD_TYPE);
        final String cardName = response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CARD_NAME);

        info.setTypeOfPayment(cardType == null ? "****" : cardType);
        if (cardName != null) {
            try {
                info.setType(CreditCardType.valueOf(cardName.toUpperCase()));
            } catch (final Exception e) {
                LOG.warn("Unable to determine the card type for the value received - " + cardName + ". Error: " + e.getMessage());
                info.setType(CreditCardType.SWITCH);
            }
            info.setCcOwner(cardName);
        } else {
            info.setType(CreditCardType.SWITCH);
            info.setCcOwner("****");
        }
    }

    /**
     * Creates the credit card payment info.
     *
     * @param customerModel the customer model
     * @param response the response
     * @param cardPaymentInfoModel the card payment info model
     * @return CreditCardPaymentInfoModel
     */
    private CreditCardPaymentInfoModel createCreditCardPaymentInfo(final CustomerModel customerModel, final Map<String, String> response,
            final CreditCardPaymentInfoModel cardPaymentInfoModel) {
        cardPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        cardPaymentInfoModel.setUser(customerModel);
        setCardTypeAndOwner(cardPaymentInfoModel, response);
        cardPaymentInfoModel.setSubscriptionId("****");
        cardPaymentInfoModel.setNumber("*************");
        cardPaymentInfoModel.setValidToMonth("**");
        cardPaymentInfoModel.setValidToYear("**");
        cardPaymentInfoModel.setValidFromMonth("**");
        cardPaymentInfoModel.setSubscriptionId("****");
        cardPaymentInfoModel.setSaved(false);
        cardPaymentInfoModel.setCaptured(true);
        return cardPaymentInfoModel;
    }

    /**
     * Creates the debit card payment info.
     *
     * @param customerModel the customer model
     * @param response the response
     * @param cardPaymentInfoModel the card payment info model
     * @return DebitCardPaymentInfoModel
     */
    private DebitCardPaymentInfoModel createDebitCardPaymentInfo(final CustomerModel customerModel, final Map<String, String> response,
            final DebitCardPaymentInfoModel cardPaymentInfoModel) {
        cardPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        cardPaymentInfoModel.setUser(customerModel);
        setCardTypeAndOwner(cardPaymentInfoModel, response);
        cardPaymentInfoModel.setSubscriptionId("****");
        cardPaymentInfoModel.setNumber("*************");
        cardPaymentInfoModel.setValidToMonth("**");
        cardPaymentInfoModel.setValidToYear("**");
        cardPaymentInfoModel.setValidFromMonth("**");
        cardPaymentInfoModel.setSubscriptionId("****");
        cardPaymentInfoModel.setSaved(false);
        cardPaymentInfoModel.setCaptured(true);
        return cardPaymentInfoModel;
    }

    @Override
    public void saveGiftCardTransaction(final String amount, final String cardNumber, final String cardPin, final boolean egv) {
        final CartModel cartModel = cartService.getSessionCart();
        final BigDecimal redeemAmount = new BigDecimal(amount);
        final UserModel user = userService.getCurrentUser();

        final GiftCardPaymentInfoModel giftCardPaymentInfoModel = modelService.create(GiftCardPaymentInfoModel.class);
        if (egv) {
            giftCardPaymentInfoModel.setTypeOfPayment("EGift Card Payment");
        } else {
            giftCardPaymentInfoModel.setTypeOfPayment("Gift Card Payment");

        }
        giftCardPaymentInfoModel.setUser(user);
        giftCardPaymentInfoModel.setCode(user.getUid() + "_" + UUID.randomUUID());
        giftCardPaymentInfoModel.setGiftCardNumber(cardNumber);
        giftCardPaymentInfoModel.setPin(cardPin);
        giftCardPaymentInfoModel.setEgv(egv);

        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        transaction.setPlannedAmount(redeemAmount);
        transaction.setCurrency(cartModel.getCurrency());
        transaction.setOrder(cartModel);
        transaction.setInfo(giftCardPaymentInfoModel);
        transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        transaction.setRequestToken(UUID.randomUUID().toString());
        transaction.setRequestId(UUID.randomUUID().toString());
        modelService.saveAll(transaction, giftCardPaymentInfoModel);
        createPaymentTransactionEntry(transaction, null);
        logTransactionDetails(transaction);
        modelService.refresh(cartModel);
    }

    @Override
    public void cancelFCCTransaction() {
        final CartModel cartModel = cartService.getSessionCart();
        LOG.info("Removing FCC Transaction for User : " + cartModel.getUser().getUid());
        if (cartModel.getPaymentTransactions() != null) {
            for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
                if (transaction.getInfo() instanceof LoyaltyCardPaymentInfoModel) {
                    modelService.remove(transaction);
                    modelService.refresh(cartModel);
                }
            }
        }
    }

    @Override
    public void cancelGCTransaction(final String cardNumber) {
        final CartModel cartModel = cartService.getSessionCart();
        LOG.info("Removing GC Transaction for User : " + cartModel.getUser().getUid() + " for Card Number: " + cardNumber);

        for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
            if (transaction.getInfo() instanceof GiftCardPaymentInfoModel) {
                final GiftCardPaymentInfoModel giftCardPaymentInfoModel = (GiftCardPaymentInfoModel) transaction.getInfo();
                if (giftCardPaymentInfoModel.getGiftCardNumber().equals(cardNumber)) {
                    modelService.remove(transaction);
                    modelService.refresh(cartModel);
                }
            }
        }
    }

    @Override
    public void createPaymentTransactionEntry(final PaymentTransactionModel transaction, final Map<String, String> response) {
        final PaymentTransactionEntryModel paymentTransactionEntryModel = modelService.create(PaymentTransactionEntryModel.class);
        paymentTransactionEntryModel.setAmount(transaction.getPlannedAmount());
        paymentTransactionEntryModel.setCode(UUID.randomUUID().toString());
        paymentTransactionEntryModel.setCurrency(transaction.getCurrency());
        paymentTransactionEntryModel.setPaymentTransaction(transaction);
        paymentTransactionEntryModel.setRequestId(transaction.getRequestId());
        paymentTransactionEntryModel.setRequestToken(transaction.getRequestToken());
        paymentTransactionEntryModel.setTime(new Date());

        logTransactionDetails(transaction);

        // When the response has value and the CC Avenue order status is in Awaited status, then
        if (response != null && SslCCAvenueConstants.CCAvenue.OrderStatus.AWAITED
                .equalsIgnoreCase(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_ORDER_STATUS))) {
            paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
            paymentTransactionEntryModel.setType(PaymentTransactionType.AUTHORIZATION_PENDING);
        } else {
            paymentTransactionEntryModel.setTransactionStatus(SslCCAvenueConstants.CCAvenue.OrderStatus.SUCCESS);

            if (transaction.getInfo() instanceof CreditCardPaymentInfoModel || transaction.getInfo() instanceof DebitCardPaymentInfoModel
                    || transaction.getInfo() instanceof NetBankingPaymentInfoModel
                    || transaction.getInfo() instanceof FreeChargePaymentInfoModel
                    || transaction.getInfo() instanceof CCAvenueWalletPaymentInfoModel) {
                paymentTransactionEntryModel.setType(PaymentTransactionType.AUTHORIZATION);
            } else if (transaction.getInfo() instanceof CODPaymentInfoModel) {
                paymentTransactionEntryModel.setType(PaymentTransactionType.COD_PENDING);
            } else if (transaction.getInfo() instanceof PaytmPaymentInfoModel) {
                // will create paymentTransactionEntry for Paytm only if it in SUCCESS status
                paymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
            } else {
                paymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
            }
        }

        modelService.save(paymentTransactionEntryModel);
    }

    @Override
    public void savePaymentMode(final String onlinePaymentMode, final CartModel cart) {
        LOG.info("Inside savePaymentMode...");
        cart.setPaymentMode(paymentModeService.getPaymentModeForCode(onlinePaymentMode));
        modelService.save(cart);
    }

    @Override
    public void saveFreeChargeTransaction(final Map<String, String> resultMap) {
        final CartModel cartModel = cartService.getSessionCart();
        this.saveFreeCharge(cartModel, resultMap);
    }

    @Override
    public void saveFreeCharge(final CartModel cartModel, final Map<String, String> response) {
        if (cartModel.getUser() instanceof CustomerModel) {
            final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
            final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
            final FreeChargePaymentInfoModel freeChargePaymentInfoModel = modelService.create(FreeChargePaymentInfoModel.class);
            freeChargePaymentInfoModel.setTypeOfPayment("FreeCharge");
            freeChargePaymentInfoModel.setUser(customerModel);
            freeChargePaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
            freeChargePaymentInfoModel.setMerchantIdUsed(Config.getParameter(NON_SEAMLESS_CCAVENUE_MERCHANT_ID));
            transaction.setInfo(freeChargePaymentInfoModel);
            transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
            transaction.setCurrency(commonI18NService.getCurrency(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CURRENCY)));
            transaction.setOrder(cartModel);
            transaction.setOwner(customerModel);
            transaction.setPlannedAmount(new BigDecimal(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_AMOUNT)));
            transaction.setRequestToken(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
            transaction.setRequestId(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
            modelService.saveAll(transaction, freeChargePaymentInfoModel);
            this.createPaymentTransactionEntry(transaction, response);
            logTransactionDetails(transaction);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User associated with cart is not a Customer.");
            }
        }
    }

    @Override
    public void saveSslWalletTransaction(final String amount, final String walletOTP) {
        boolean paymentTransactionCreated = false;
        final CartModel cartModel = cartService.getSessionCart();
        final BigDecimal redeemAmount = new BigDecimal(amount);
        final UserModel user = userService.getCurrentUser();
        String walletNumber = StringUtils.EMPTY;
        SSCreditsPaymentInfoModel ssCreditsPaymentInfoModel = null;
        PaymentTransactionModel transaction = null;
        if (user instanceof CustomerModel) {
            final CustomerModel customer = (CustomerModel) user;
            walletNumber = customer.getWalletNumber();

            if (CollectionUtils.isNotEmpty(cartModel.getPaymentTransactions())) {
                for (final PaymentTransactionModel paymentTransaction : cartModel.getPaymentTransactions()) {
                    if (paymentTransaction.getInfo() instanceof SSCreditsPaymentInfoModel) {
                        ssCreditsPaymentInfoModel = (SSCreditsPaymentInfoModel) paymentTransaction.getInfo();
                        ssCreditsPaymentInfoModel.setWalletOTP(walletOTP);
                        transaction = paymentTransaction;
                        transaction.setPlannedAmount(paymentTransaction.getPlannedAmount().add(redeemAmount));
                        if (CollectionUtils.isNotEmpty(paymentTransaction.getEntries()) && paymentTransaction.getEntries().size() == 1) {
                            final PaymentTransactionEntryModel paymentTransactionEntryModel = paymentTransaction.getEntries().get(0);
                            paymentTransactionEntryModel.setAmount(transaction.getPlannedAmount());
                            modelService.saveAll(transaction, ssCreditsPaymentInfoModel, paymentTransactionEntryModel);
                        }
                        modelService.refresh(cartModel);
                        paymentTransactionCreated = true;
                        break;
                    }
                }
            }

            if (!paymentTransactionCreated) {
                ssCreditsPaymentInfoModel = modelService.create(SSCreditsPaymentInfoModel.class);
                ssCreditsPaymentInfoModel.setTypeOfPayment("SS Credits Payment");
                ssCreditsPaymentInfoModel.setUser(user);
                ssCreditsPaymentInfoModel.setCode(user.getUid() + "_" + UUID.randomUUID());
                ssCreditsPaymentInfoModel.setWalletNumber(walletNumber);
                ssCreditsPaymentInfoModel.setWalletOTP(walletOTP);

                transaction = modelService.create(PaymentTransactionModel.class);
                transaction.setPlannedAmount(redeemAmount);
                transaction.setCurrency(cartModel.getCurrency());
                transaction.setOrder(cartModel);
                transaction.setInfo(ssCreditsPaymentInfoModel);
                transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
                transaction.setRequestToken(UUID.randomUUID().toString());
                transaction.setRequestId(UUID.randomUUID().toString());
                modelService.saveAll(transaction, ssCreditsPaymentInfoModel);
                createPaymentTransactionEntry(transaction, null);
                logTransactionDetails(transaction);

                modelService.refresh(cartModel);
            }
        }
    }

    @Override
    public void cancelSslWalletTransaction() {
        final CartModel cartModel = cartService.getSessionCart();
        if (cartModel.getPaymentTransactions() != null) {
            for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
                if (transaction.getInfo() instanceof SSCreditsPaymentInfoModel) {
                    modelService.remove(transaction);
                    modelService.refresh(cartModel);
                }
            }
        }
    }

    @Override
    public void saveCSCockpitCODTransaction(final CartModel cartModel, final double amount) {
        this.savePaymentMode(COD_PAYMENT_MODE, cartModel);

        final CustomerModel user = (CustomerModel) cartModel.getUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final CODPaymentInfoModel codPaymentInfoModel = modelService.create(CODPaymentInfoModel.class);
        codPaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        codPaymentInfoModel.setUser(user);
        codPaymentInfoModel.setTypeOfPayment("COD Payment");
        transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        transaction.setPlannedAmount(BigDecimal.valueOf(amount));
        transaction.setCurrency(cartModel.getCurrency());
        transaction.setOrder(cartModel);
        transaction.setInfo(codPaymentInfoModel);
        transaction.setRequestToken(UUID.randomUUID().toString());
        transaction.setRequestId(UUID.randomUUID().toString());
        modelService.saveAll(transaction, codPaymentInfoModel);
        createPaymentTransactionEntry(transaction, null);
        logTransactionDetails(transaction);

        modelService.refresh(cartModel);
    }

    @Override
    public void saveCSCockpitSslWalletTransaction(final CartModel cartModel, final double amount) {
        this.savePaymentMode(ONLINE_PAYMENT_MODE, cartModel);

        final BigDecimal redeemAmount = new BigDecimal(amount);
        final UserModel user = cartModel.getUser();
        String walletNumber = StringUtils.EMPTY;
        if (user instanceof CustomerModel) {
            final CustomerModel customer = (CustomerModel) user;
            walletNumber = customer.getWalletNumber();
        }

        final SSCreditsPaymentInfoModel ssCreditsPaymentInfoModel = modelService.create(SSCreditsPaymentInfoModel.class);
        ssCreditsPaymentInfoModel.setTypeOfPayment("SS Credits Payment");
        ssCreditsPaymentInfoModel.setUser(user);
        ssCreditsPaymentInfoModel.setCode(user.getUid() + "_" + UUID.randomUUID());
        ssCreditsPaymentInfoModel.setWalletNumber(walletNumber);

        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        transaction.setPlannedAmount(redeemAmount);
        transaction.setCurrency(cartModel.getCurrency());
        transaction.setOrder(cartModel);
        transaction.setInfo(ssCreditsPaymentInfoModel);
        transaction.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
        transaction.setRequestToken(UUID.randomUUID().toString());
        transaction.setRequestId(UUID.randomUUID().toString());
        modelService.saveAll(transaction, ssCreditsPaymentInfoModel);
        createPaymentTransactionEntry(transaction, null);
        logTransactionDetails(transaction);

        modelService.refresh(cartModel);
    }

    @Override
    public void saveCCAvenueWallet(final CartModel cartModel, final Map<String, String> response) {
        if (cartModel.getUser() instanceof CustomerModel) {
            final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
            final PaymentTransactionModel transaction = this.modelService.create(PaymentTransactionModel.class);
            final CCAvenueWalletPaymentInfoModel walletPaymentInfoModel = this.modelService.create(CCAvenueWalletPaymentInfoModel.class);
            walletPaymentInfoModel.setTypeOfPayment(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CARD_NAME));
            walletPaymentInfoModel.setUser(customerModel);
            walletPaymentInfoModel.setCode(cartModel.getCode() + "_" + UUID.randomUUID());
            walletPaymentInfoModel.setMerchantIdUsed(Config.getParameter(NON_SEAMLESS_CCAVENUE_MERCHANT_ID));
            transaction.setInfo(walletPaymentInfoModel);
            transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
            transaction.setCurrency(this.commonI18NService.getCurrency(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_CURRENCY)));
            transaction.setOrder(cartModel);
            transaction.setOwner(customerModel);
            transaction.setPlannedAmount(new BigDecimal(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_AMOUNT)));
            transaction.setRequestToken(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
            transaction.setRequestId(response.get(SslCCAvenueConstants.CCAvenue.CCAVENUE_TRAKING_ID));
            this.modelService.saveAll(transaction, walletPaymentInfoModel);
            this.createPaymentTransactionEntry(transaction, response);
            logTransactionDetails(transaction);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User associated with cart is not a Customer.");
            }
        }

    }

    @Override
    public void saveCCAvenueWalletTransaction(final Map<String, String> response) {
        final CartModel cartModel = this.cartService.getSessionCart();
        this.saveCCAvenueWallet(cartModel, response);
    }

    @Override
    public void cancelSslWalletTransaction(final CartModel cartModel) {
        if (cartModel.getPaymentTransactions() != null) {
            for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
                if (transaction.getInfo() instanceof SSCreditsPaymentInfoModel) {
                    modelService.remove(transaction);
                    modelService.refresh(cartModel);
                }
            }
        }
    }

    @Override public void cancelTransactionsForGiftProducts() {
        final CartModel cartModel = cartService.getSessionCart();
        LOG.info(String.format("Removing Transactions from cart: %s because of gift product for User: %s",
                cartModel.getCode(), cartModel.getUser().getUid()));
        List<ItemModel> itemsToRemove = new ArrayList<>();
        for (final PaymentTransactionModel transaction : cartModel.getPaymentTransactions()) {
            PaymentInfoModel paymentInfo;
            paymentInfo = transaction.getInfo();
            if (paymentInfo instanceof GiftCardPaymentInfoModel || paymentInfo instanceof LoyaltyCardPaymentInfoModel ||
                    paymentInfo instanceof SSCreditsPaymentInfoModel)
            {
                itemsToRemove.add(transaction);
                itemsToRemove.add(paymentInfo);
            }
        }
        modelService.removeAll(itemsToRemove);
        modelService.refresh(cartModel);
    }

    public void logTransactionDetails(final PaymentTransactionModel transaction) {
        LOG.info("##### Transaction: code " + transaction.getCode() + " cart id: " + transaction.getOrder().getGuid() + " order code: "
                + transaction.getOrder().getCode() + " user id: " + transaction.getOrder().getUser().getUid() + " amount: "
                + transaction.getPlannedAmount());

    }

    @Override
    public void savePayuTransaction(final CartModel cartModel, final Map<String, String[]> response) {

        final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
        final PaymentTransactionModel transaction = modelService.create(PaymentTransactionModel.class);
        final PayuPaymentMode paymentMode = getEnumerationService().getEnumerationValue(PayuPaymentMode.class, response.get("mode")[0]);
        final PayUPaymentInfoModel payUPaymentInfoModel = (PayUPaymentInfoModel) modelService.create(PayUPaymentInfoModel.class);
        payUPaymentInfoModel.setTypeOfPayment(setTypeOfPaymentForPayu(paymentMode, response));
        payUPaymentInfoModel.setMihPayId(response.get("mihpayid")[0]);
        payUPaymentInfoModel.setSaved(false);
        payUPaymentInfoModel.setCaptured(true);
        payUPaymentInfoModel.setPaymentGatewayType(response.get("PG_TYPE")[0]);
        payUPaymentInfoModel.setBankCode(response.get("bankcode")[0]);
        payUPaymentInfoModel.setBankReferenceNumber(response.get("bank_ref_num")[0]);
        payUPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        payUPaymentInfoModel.setUser(customerModel);
        payUPaymentInfoModel.setPaymentMode(paymentMode);
        transaction.setInfo(payUPaymentInfoModel);
        transaction.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        transaction.setCurrency(getCommonI18NService().getCurrentCurrency());
        transaction.setOrder(cartModel);
        transaction.setOwner(customerModel);
        transaction.setPlannedAmount(new BigDecimal(response.get("amount")[0]));
        transaction.setRequestToken(response.get("mihpayid")[0]);
        transaction.setRequestId(response.get("mihpayid")[0]);
        modelService.saveAll(transaction, payUPaymentInfoModel);
        createPayuPaymentTransactionEntry(transaction, response);
        logTransactionDetails(transaction);

    }

    private String setTypeOfPaymentForPayu(final PayuPaymentMode paymentMode, final Map<String, String[]> response) {

        if (PayuPaymentMode.CC.equals(paymentMode)) {
            return "Credit Card";
        } else if (PayuPaymentMode.DC.equals(paymentMode)) {
            return "Debit Card";
        } else if (PayuPaymentMode.NB.equals(paymentMode)) {
            return "Netbanking";
        } else if (PayuPaymentMode.CASH.equals(paymentMode)) {
            return response.get("PG_TYPE")[0];
        }
        return response.get("mode")[0];
    }

    public void createPayuPaymentTransactionEntry(final PaymentTransactionModel transaction, final Map<String, String[]> response) {
        {
            final PaymentTransactionEntryModel paymentTransactionEntryModel = modelService.create(PaymentTransactionEntryModel.class);
            paymentTransactionEntryModel.setAmount(transaction.getPlannedAmount());
            paymentTransactionEntryModel.setCode(UUID.randomUUID().toString());
            paymentTransactionEntryModel.setCurrency(transaction.getCurrency());
            paymentTransactionEntryModel.setPaymentTransaction(transaction);
            paymentTransactionEntryModel.setRequestId(transaction.getRequestId());
            paymentTransactionEntryModel.setRequestToken(transaction.getRequestToken());
            paymentTransactionEntryModel.setTime(new Date());
            logTransactionDetails(transaction);
            if (response.get("unmappedstatus") != null && response.get("unmappedstatus")[0].equalsIgnoreCase("captured")) {
                paymentTransactionEntryModel.setTransactionStatus(SslCCAvenueConstants.CCAvenue.OrderStatus.SUCCESS);
                paymentTransactionEntryModel.setType(PaymentTransactionType.CAPTURE);
            } else {
                paymentTransactionEntryModel.setTransactionStatus(SslCCAvenueConstants.CCAvenue.OrderStatus.SUCCESS);
                paymentTransactionEntryModel.setType(PaymentTransactionType.AUTHORIZATION_PENDING);
            }
            modelService.save(paymentTransactionEntryModel);
        }

    }
}