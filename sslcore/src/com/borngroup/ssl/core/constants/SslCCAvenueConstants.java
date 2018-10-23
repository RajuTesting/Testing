package com.borngroup.ssl.core.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Global class for all B2ccheckoutaddon constants. You can add global constants for your extension into this class.
 *
 * @author SSL Global class for all B2ccheckoutaddon constants. You can add global constants for your extension into this class.
 */
public final class SslCCAvenueConstants {

    public interface CCAvenue {
        public final static String CCAVENUE_WORKING_KEY = "ccavenue.working_key";
        public final static String CCAVENUE_PAYMENT_REDIRECT = "ccavenue.paymentredirect.url";
        public final static String ENCRYPTED_RESPONSE = "encResp";
        public final static String CCAVENUE_ACCESS_CODE = "ccavenue.access_code";
        public final static String CCAVENUE_ENCREQ = "&encRequest=";
        public final static String CCAVENUE_ACCESS = "&access_code=";
        public final static String CCAVENUE_SUCCESS = "Success";
        public final static String CCAVENUE_ORDER_STATUS = "order_status";
        public final static String CCAVENUE_CREDITCARD = "OPTCRDC";
        public final static String CCAVENUE_DEBITCARD = "OPTDBCRD";
        public final static String CCAVENUE_NETBANKING = "OPTNBK";
        public final static String CCAVENUE_CUSTOMER_IDENTIFIER = "customer_identifier";
        public final static String CCAVENUE_VAULT = "vault";
        public final static String CCAVENUE_SAVE_CARD = "saveCard";
        public final static String CCAVENUE_CREDIT_RADIO = "creditCardRadio";
        public final static String CCAVENUE_DEBIT_RADIO = "debitCardRadio";
        public final static String CCAVENUE_CUSTOMER_CARD_ID = "customer_card_id";

        // CCAven request constants
        public final static String CCAVENUE_PAYMENT_OPTION = "payment_option";
        public final static String CCAVENUE_CARD_TYPE = "card_type";
        public final static String CCAVENUE_CARD_NAME = "card_name";
        public final static String CCAVENUE_DATA_ACCEPT = "data_accept";
        public final static String CCAVENUE_CARD_NUMBER = "card_number";
        public final static String CCAVENUE_EXPIRY_MONTH = "expiry_month";
        public final static String CCAVENUE_EXPIRY_YEAR = "expiry_year";
        public final static String CCAVENUE_CVV_NUMBER = "cvv_number";
        public final static String CCAVENUE_SAVECARD_CVV_NUMBER = "savecard_cvv_number";
        public final static String CCAVENUE_ISSUING_BANK = "issuing_bank";
        public final static String CCAVENUE_SAVE_CARD_NUMBER = "customer_card_no";
        public final static String CCAVENUE_CUSTOMER_ID = "customer_id";
        public final static String CCAVENUE_CREDIT_CARD_TYPE = "CRDC";
        public final static String CCAVENUE_DEBIT_CARD_TYPE = "DBCRD";

        // CCAven FreeCharge request constants
        public final static String CCAVENUE_FREECHARGE = "OPTWLT";
        public final static String CCAVENUE_FREECHARGE_CARD_TYPE = "WLT";
        public final static String CCAVENUE_FREECHARGE_CARD_NAME = "FreeCharge";

        /** The Constant CCAVENUE_WALLET. */
        String CCAVENUE_WALLET = "OPTWLT";

        public final static String CCAVENUE_TID = "tid";
        public final static String CCAVENUE_MERCHANT_ID = "merchant_id";
        public final static String CCAVENUE_ORDER_ID = "order_id";
        public final static String CCAVENUE_AMOUNT = "amount";
        public final static String CCAVENUE_CURRENCY = "currency";
        public final static String CCAVENUE_REDIRECT_URL = "redirect_url";
        public final static String CCAVENUE_CANCEL_URL = "cancel_url";
        public final static String CCAVENUE_LANGUAGE = "language";

        public final static String CCAVENUE_BILLING_NAME = "billing_name";
        public final static String CCAVENUE_BILLING_ADDRESS = "billing_address";
        public final static String CCAVENUE_BILLING_CITY = "billing_city";
        public final static String CCAVENUE_BILLING_STATE = "billing_state";
        public final static String CCAVENUE_BILLING_ZIP = "billing_zip";
        public final static String CCAVENUE_BILLING_COUNTRY = "billing_country";
        public final static String CCAVENUE_BILLING_TEL = "billing_tel";
        public final static String CCAVENUE_BILLING_EMAIL = "billing_email";

        public final static String CCAVENUE_DELIVERY_NAME = "delivery_name";
        public final static String CCAVENUE_DELIVERY_ADDRESS = "delivery_address";
        public final static String CCAVENUE_DELIVERY_CITY = "delivery_city";
        public final static String CCAVENUE_DELIVERY_STATE = "delivery_state";
        public final static String CCAVENUE_DELIVERY_ZIP = "delivery_zip";
        public final static String CCAVENUE_DELIVERY_COUNTRY = "delivery_country";
        public final static String CCAVENUE_DELIVERY_TEL = "delivery_tel";

        public final static String CCAVENUE_MERCHANT_PARAM1 = "merchant_param1";
        public final static String CCAVENUE_MERCHANT_PARAM2 = "merchant_param2";
        public final static String CCAVENUE_MERCHANT_PARAM3 = "merchant_param3";
        public final static String CCAVENUE_MERCHANT_PARAM4 = "merchant_param4";
        public final static String CCAVENUE_MERCHANT_PARAM5 = "merchant_param5";

        public final static String CCAVENUE_EMI_PLAN_ID = "emi_plan_id";
        public final static String CCAVENUE_EMI_ID = "emi_tenure_id";
        public final static String CCAVENUE_EMI_BANKS = "emi_banks";
        public final static String CCAVENUE_EMI_TENURE_ID = "emi_tenure_id";
        public final static String CCAVENUE_MOBILE_NUMBER = "mobile_number";
        public final static String CCAVENUE_PROMO_CODE = "promo_code";

        public final static String CCAVENUE_TRAKING_ID = "tracking_id";
        public final static String CCAVENUE_PAYMENT_MODE = "payment_mode";
        public final static String CCAVENUE_DEBIT_CARD_TYPE_VALUE = "DBCRD";
        public final static String CCAVENUE_SAVE_PAYMENT_INFO = "saveCard";

        public final static Set<String> CCAVENUE_RESTRICTED_DEBIT_CARD_OPTIONS = new HashSet<String>(
                Arrays.asList("Andhra Bank", "Canara Bank Debit card", "Indian Bank", "IOB Debit card", "Punjab National Bank", "RuPay",
                        "Union Bank Of India", "Citibank"));
        String NONSEAMLESS_CCAVENUE_WORKING_KEY = "nonseamless.ccavenue.working_key";
        String NONSEAMLESS_CCAVENUE_ACCESS_CODE = "nonseamless.ccavenue.access_code";

        public interface OrderStatus {
            public final static String AWAITED = "Awaited";
            public final static String SUCCESS = "Success";
        }
    }
}
