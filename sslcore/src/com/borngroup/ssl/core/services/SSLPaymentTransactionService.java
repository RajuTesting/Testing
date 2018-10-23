/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.Map;

/**
 * The Interface SSLPaymentTransactionService.
 *
 * @author midhun.bose
 */
public interface SSLPaymentTransactionService {

    /**
     * Save credit card transaction.
     *
     * @param response
     *        the response
     */
    void saveCreditCardTransaction(final Map<String, String> response);

    /**
     * Save debit card transaction.
     *
     * @param response
     *        the response
     */
    void saveDebitCardTransaction(final Map<String, String> response);

    /**
     * Save netbanking transaction.
     *
     * @param response
     *        the response
     */
    void saveNetbankingTransaction(final Map<String, String> response);

    /**
     * Save free charge transaction.
     *
     * @param response
     *        the response
     */
    void saveFreeChargeTransaction(final Map<String, String> response);

    /**
     * Save cod transaction.
     *
     * @param amount
     *        the amount
     */
    void saveCODTransaction(double amount);

    /**
     * Save payment mode.
     *
     * @param paymentMode
     *        the payment mode
     */
    void savePaymentMode(String paymentMode);

    /**
     * Save fcc transaction.
     *
     * @param amount
     *        the amount
     */
    void saveFCCTransaction(String amount);

    /**
     * Save gift card transaction.
     *
     * @param amount
     *        the amount
     * @param cardNumber
     *        the card number
     * @param cardPin
     *        the card pin
     * @param egv
     *        the egv
     */
    void saveGiftCardTransaction(String amount, String cardNumber, String cardPin, boolean egv);

    /**
     * Cancel fcc transaction.
     */
    void cancelFCCTransaction();

    /**
     * Cancel gc transaction.
     *
     * @param cardNumber
     *        the card number
     */
    void cancelGCTransaction(String cardNumber);

    /**
     * Creates the payment transaction entry.
     *
     * @param transcation
     *        the transcation
     * @param response
     *        the response
     */
    void createPaymentTransactionEntry(PaymentTransactionModel transcation, Map<String, String> response);

    /**
     * Save payment mode.
     *
     * @param onlinePaymentMode
     *        the online payment mode
     * @param cart
     *        the cart
     */
    void savePaymentMode(String onlinePaymentMode, CartModel cart);

    /**
     * Save debit card.
     *
     * @param cartModel
     *        the cart model
     * @param response
     *        the response
     */
    void saveDebitCard(CartModel cartModel, Map<String, String> response);

    /**
     * Save credit card.
     *
     * @param cartModel
     *        the cart model
     * @param resultMap
     *        the result map
     */
    void saveCreditCard(CartModel cartModel, Map<String, String> resultMap);

    /**
     * Save net banking.
     *
     * @param cartModel
     *        the cart model
     * @param response
     *        the response
     */
    void saveNetBanking(CartModel cartModel, Map<String, String> response);

    /**
     * Save free charge.
     *
     * @param cartModel
     *        the cart model
     * @param response
     *        the response
     */
    void saveFreeCharge(CartModel cartModel, Map<String, String> response);

    /**
     * Save ssl wallet transaction.
     *
     * @param amount
     *        the amount
     * @param walletOTP
     *        the wallet otp
     */
    void saveSslWalletTransaction(final String amount, final String walletOTP);

    /**
     * Cancel ssl wallet transaction.
     */
    void cancelSslWalletTransaction();

    /**
     * Save cs cockpit cod transaction.
     *
     * @param cartModel
     *        the cart model
     * @param amount
     *        the amount
     */
    void saveCSCockpitCODTransaction(final CartModel cartModel, final double amount);

    /**
     * Save cs cockpit ssl wallet transaction.
     *
     * @param cartModel
     *        the cart model
     * @param amount
     *        the amount
     */
    void saveCSCockpitSslWalletTransaction(final CartModel cartModel, final double amount);

    /**
     * Save CC Avenue Wallet.
     *
     * @param cartModel
     *        the cart model
     * @param response
     *        the response
     */
    void saveCCAvenueWallet(CartModel cartModel, Map<String, String> response);

    /**
     * Save CC Avenue Wallet transaction.
     *
     * @param response
     *        the response
     */
    void saveCCAvenueWalletTransaction(final Map<String, String> response);

    /**
     * Cancel ssl wallet transaction.
     *
     * @param cartModel
     *        the cart model
     */
    public void cancelSslWalletTransaction(final CartModel cartModel);

    /**
     * Cancel ssl wallet transaction.
     */
    void cancelTransactionsForGiftProducts();

    /**
     * @param cartModel
     * @param response
     */
    void savePayuTransaction(CartModel cartModel, Map<String, String[]> response);

}