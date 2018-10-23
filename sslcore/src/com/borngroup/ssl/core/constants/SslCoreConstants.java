/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.constants;

import de.hybris.platform.util.Config;

/**
 * Global class for all SslCore constants. You can add global constants for your extension into this class.
 *
 * @author SSL
 */
public final class SslCoreConstants extends GeneratedSslCoreConstants {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String EXTENSIONNAME = "sslcore";
    public static final String PROMOTION_GROUP_SSL = "sslPromoGrp";
    public static final String COUNTRY_CURRENCY_INDIA = "INR";
    public static final String ROOT_CATEGORY = "categories";

    // constant defining path of file to be picked for writing details of unapproved products
    public static final String PRODUCT_FILE_PATH = "ssl.unapprovedproductsmailcronjob.file.path";
    public static final String UNAPPROVED_MEDIA_CONATINERS = "Media container of the Product not converted";
    public static final String EMPTY_GALLERY_IMAGE = "Media container ERROR: Product does not contain any gallery images, media conatiner null";
    public static final String APPROVALSTATUS = "approvalStatus";
    public static final String PRODUCT_CODE = "PRODUCT Code: ";
    public static final String MEDIA_CONTAINER_PK = "  MEDIA CONTAINER PK: ";
    public static final String MEDIA_CONTAINER_QUALIFIER = " Qualifier: ";
    public static final String REASON = " Reason: ";
    public static final String PRODUCT_LIST_MAIL_FROM = "ssl.unapprovedproductsmailcronjob.from";
    public static final String PRODUCT_LIST_MAIL_TO = "ssl.unapprovedproductsmailcronjob.to";

    // constants defined for Product Upload Automation Cronjob
    public static final String CRONJOB_LOG_STORE_LOCATION = "cronjob";
    public static final String CRONJOB_LOG_FORMAT = "desktop";
    public static final String ZIP_MIME_TYPE = "text/plain";
    public static final String LOG_REPORT_FIELD_SEPERATOR = " | ";
    public static final String BASE_PRODUCT_ENRICHMENT_JOB = "BaseProductEnrichmentCronjob";
    public static final String VARIANT_PRODUCT_APPROVAL_JOB = "VariantProductAutoApprovalCronjob";

    // constants defined for Customer Product Reviews Report CronJob
    public static final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FILE_PATH = Config
            .getParameter("ssl.sslcustomerproductreviewsreportcronJob.file.path");
    public static final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS = Config
            .getParameter("ssl.sslcustomerproductreviewsreportcronJob.to");
    public static final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS = Config
            .getParameter("ssl.sslcustomerproductreviewsreportcronJob.from");
    public static final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_FROM_ADDRESS_FALLBACK = "customercare@shoppersstop.com";
    public static final String SEND_CUSTOMER_PRODUCT_REVIEWS_REPORT_TO_ADDRESS_FALLBACK = "alvvina.sayed@shoppersstop.com";
    public static final String CUSTOMER_PRODUCT_REVIEWS_EMAIL_SUBJECT = "Customer Product Reviews Report";

    // constants defined for Image Loading
    public static final String IMAGE_STORE_LOCATION = "images";
    public static final String VIDEO_STORE_LOCATION = "videos";
    public static final String IMAGE_FORMAT = "desktop";
    public static final String VIDEO_FORMAT = "desktop";
    public static final String SEPARATOR_UNDERSCORE = "_";
    public static final String SEPARATOR_ALT = "alt";
    public static final String SEPARATOR_SWATCH = "_s";
    public static final String SEPARATOR_VIDEO = "_v";
    public static final String FORMAT_LARGE = "515x515";
    public static final String FORMAT_NORMAL = "300x300";
    public static final String FORMAT_SMALL = "136Wx204H";
    public static final String FORMAT_VERYSMALL = "65x65";
    public static final String FORMAT_TINY = "30x30";
    public static final String MEDIA_DONE_PATH = "images.load.done.path";
    public static final String MEDIA_FEED_PATH = "images.load.feed.path";
    public static final String MEDIA_ERROR_PATH = "images.load.error.path";
    public static final String MEDIA_PATTERN = "jpg";
    public static final String MEDIA_PATTERN_PNG = "png";
    public static final String VIDEO_PATTERN = "mp4";
    public static final String MIME_TYPE = "image/png";
    public static final String VIDEO_MIME_TYPE = "video/mp4";
    public static final String CONVERSION_GROUP_CODE = "sslConversionGroup";
    public static final String CATALOG_NAME = "sslProductCatalog";
    public static final String CATALOG_VERSION = "Staged";
    public static final String ONLINE_CATALOG_VERSION = "Online";
    public static final String UNCONVERTED = "UNCONVERTED";
    public static final String CONVERTED = "CONVERTED";
    public static final String SEPARATOR = "/";
    public static final String SUCCESS = "Success";
    public static final String ONLINE_PAYMENT_MODE = "online";
    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final String ADMIN = "admin";
    public static final String ANONYMOUS = "anonymous";
    public static final String MAIN_MEDIA_WIDTH = "images.type.main.width";
    public static final String MAIN_MEDIA_HEIGTH = "images.type.main.heigth";
    public static final String ALT_MEDIA_WIDTH = "images.type.alt.width";
    public static final String ALT_MEDIA_HEIGTH = "images.type.alt.heigth";
    public static final String SWATCH_MEDIA_WIDTH = "images.type.swatch.width";
    public static final String SWATCH_MEDIA_HEIGTH = "images.type.swatch.heigth";

    // Sorting Options {For solr and frontend}.
    public static final String SORT_BY_PRICE_ASC = "price-asc";
    public static final String SORT_BY_TOPRATED = "topRated";
    public static final String SORT_BY_RELEVANCE = "relevance";
    public static final String SORT_BY_PRICE_DESC = "price-desc";
    public static final String SORT_BY_DISCOUNT_ASC = "discount-asc";
    public static final String SORT_BY_DISCOUNT_DESC = "discount-desc";
    public static final String SORT_BY_CATEGORY_RELEVANCE = "categoryRelevance";
    public static final String SORT_BY_NEWARRIVAL = "newArrival-desc";

    // constants defined for sending email for product uploading status.
    public static final String BASE_PRODUCT = "ssl_base";
    public static final String VARIANT_PRODUCT = "ssl_variant";
    public static final String PRODUCT_PRICE_CSV_NAME = "ssl_product_price";
    public static final String PRODUCT_STOCK_CSV_NAME = "ssl_product_stock";
    public static final String PRODUCT_DISCOUNT_CSV_NAME = "ssl_product_discount";
    public static final String SIZE_MAPPING_CSV_NAME = "ssl_sizeMapping";
    public static final String BRAND_MAPPING_CSV_NAME = "ssl_brandMapping";
    public static final String COLOR_MAPPING_CSV_NAME = "ssl_colorMapping";
    public static final String HIRE_VARIANT = "hire_variant";
    public static final String HIREBUNDLE_VARIANT = "hirebundle_variant";
    public static final String SALE_VARIANT = "sale_variant";
    public static final String UNRESOLVED_FILE_PATH = "\\media\\sys_master\\";
    public static final String IGNORE = "<ignore>";
    public static final int PROD_CODE_COLUMN = 1;
    public static final int PROD_SIZEVARIANT_CODE_COLUMN = 2;
    public static final String PARAGRAPH_TAG_START = "<p>";
    public static final String PARAGRAPH_TAG_END = "</p>";
    public static final String EMAIL_BODY_FIRST_LINE = "Hi All,";
    public static final String EMAIL_BODY_SECOND_LINE = "The products in the attached list have been uploaded into Hybris system through Hot Folder. Please embellish them.";
    public static final String EMAIL_BODY_ALT_SECOND_LINE = "No products were updated into Hybris system in the latest Hot Folder update.";
    public static final String EMAIL_BODY_ALT2_SECOND_LINE = "The products in the second attached list have not been uploaded into Hybris system through Hot Folder.";
    public static final String EMAIL_BODY_THANKS = "<b>Thanks.</b>";
    public static final String EMAIL_SUBJECT = " new products imported into Hybris system through Hot Folder";
    public static final String EMAIL_BODY_THIRD_LINE = "Note : Junk values for product codes indicate that the Product code was not recieved in the feed.";
    public static final String UPLOADED_PRODUCTS_FILE_NAME = "UploadedProductsList.csv";
    public static final String UPLOADED_FAILED_PRODUCTS_FILE_NAME = "FailedUploadedProductsList.csv";
    public static final String NEW_LINE_CHARACTER = "\n";
    public static final String HTML_BREAK_LINE_TAG = "<br>";
    public static final String HTML_BOLD_TAG_START = "<b>";
    public static final String HTML_BOLD_TAG_END = "</b>";

    public static final String PRICE_IMPORT_FROM_ADDRESS = "priceImport.mail.fromAddress";
    public static final String PRICE_IMPORT_TO_ADDRESS = "priceImport.mail.toAddress";

    public static final String STOCK_IMPORT_FROM_ADDRESS = "stockImport.mail.fromAddress";
    public static final String STOCK_IMPORT_TO_ADDRESS = "stockImport.mail.toAddress";

    public static final String DISCOUNT_IMPORT_TO_ADDRESS = "discountImport.mail.toAddress";
    public static final String DISCOUNT_IMPORT_FROM_ADDRESS = "discountImport.mail.fromAddress";

    public static final String SIZE_IMPORT_FROM_ADDRESS = "sizeMappingImport.mail.fromAddress";
    public static final String SIZE_IMPORT_TO_ADDRESS = "sizeMappingImport.mail.toAddress";

    public static final String BRAND_IMPORT_FROM_ADDRESS = "brandMappingImport.mail.fromAddress";
    public static final String BRAND_IMPORT_TO_ADDRESS = "brandMappingImport.mail.toAddress";

    public static final String COLOR_IMPORT_FROM_ADDRESS = "colorMappingImport.mail.fromAddress";
    public static final String COLOR_IMPORT_TO_ADDRESS = "colorMappingImport.mail.toAddress";

    public static final String PRICE_CSV_IMPORT_TYPE = "Price CSV Import";
    public static final String STOCK_CSV_IMPORT_TYPE = "Stock CSV Import";
    public static final String DISCOUNT_CSV_IMPORT_TYPE = "Discount CSV Import";
    public static final String SIZE_CSV_IMPORT_TYPE = "Size Mapping CSV Import";
    public static final String BRAND_CSV_IMPORT_TYPE = "Brand Mapping CSV Import";
    public static final String COLOR_CSV_IMPORT_TYPE = "Color Mapping CSV Import";

    public static final String STOCK_IMPORT_EMAIL_SUBJECT = "Stock CSV Hotfolder Import Cronjob";
    public static final String PRICE_IMPORT_EMAIL_SUBJECT = "Price CSV Hotfolder Import Cronjob";
    public static final String DISCOUNT_IMPORT_EMAIL_SUBJECT = "Discount CSV Hotfolder Import Cronjob";
    public static final String SIZE_IMPORT_EMAIL_SUBJECT = "Size Mapping CSV Hotfolder Import Cronjob";
    public static final String BRAND_IMPORT_EMAIL_SUBJECT = "Brand Mapping CSV Hotfolder Import Cronjob";
    public static final String COLOR_IMPORT_EMAIL_SUBJECT = "Color Mapping CSV Hotfolder Import Cronjob";

    public static final String PRODUCT_IMPORT_FROM_ADDRESS = "productImport.mail.fromAddress";

    // TODO: Change this to proper email
    public static final String PRODUCT_IMPORT_FROM_ADDRESS_FALLBACK = "pankit.bhanushali@shoppersstop.com";

    public static final String PRODUCT_IMPORT_TO_ADDRESS = "productImport.mail.toAddress";

    // TODO: Change this to proper email
    public static final String PRODUCT_IMPORT_TO_ADDRESS_FALLBACK = "pankit.bhanushali@shoppersstop.com";

    // Hot folder dummy file path
    public static final String HOT_FOLDER_DUMMY_FILE = "/acceleratorservices/import/master/hss/dummy.txt";

    // constants defined for sending email for image uploading status.
    public static final String UPLOADED_IMAGE_FILE_NAME = "UploadedImagesList.csv";
    public static final String UPLOADED_FAILED_IMAGE_FILE_NAME = "FailedUploadedImagesList.csv";
    public static final String EMAIL_BODY_IMAGE_SECOND_LINE = "The Images in the attached list have been uploaded for the product. Please embellish them.";
    public static final String EMAIL_BODY_IMAGE_ALT_SECOND_LINE = "No Images were updated into Hybris system in the latest Media CronJob update.";
    public static final String EMAIL_BODY_IMAGE_ALT2_SECOND_LINE = "The images in the FailedUploadedImagesList attached file have not been uploaded for the product.";
    public static final String EMAIL_SUBJECT_IMAGE = " new Images imported into Hybris system through Media CronJob";

    public static final int MAX_PAGE_LIMIT = 100; // should be configured

    public static final String IMAGE_IMPORT_FROM_ADDRESS = "imageImport.mail.fromAddress";

    // TODO: Change this to proper email
    public static final String IMAGE_IMPORT_FROM_ADDRESS_FALLBACK = "pankit.bhanushali@shoppersstop.com";

    public static final String IMAGE_IMPORT_TO_ADDRESS = "imageImport.mail.toAddress";

    // TODO: Change this to proper email
    public static final String IMAGE_IMPORT_TO_ADDRESS_FALLBACK = "pankit.bhanushali@shoppersstop.com";

    // Interval on CCAvenue check
    public static final String ORDER_DROP_CHECK_INTERVAL = "orderdrop.check.interval";

    public static final long ONE_MINUTE_IN_MILLIS = 60000; // millisecs

    public static final String WEBSITE_SSL_HTTPS = "website.ssl.https";

    // constants defined for Abandoned Cart Report For Outbound CronJob
    public static final String EMAIL_BODY_ABANDONED_CART_REPORT = "Hi,";
    public static final String ABANDONED_CART_REPORT_FILE_NAME = "AbandonedCartReport.xlsx";
    public static final String EMAIL_BODY_ABANDONED_CART_REPORT_SECOND_LINE = "Please find the attached Abandoned Cart report.";
    public static final String EMAIL_SUBJECT_ABANDONED_CART_REPORT = "Abandoned Cart Report for Outbound";
    public static final String EMAIL_BODY_ABANDONED_CART_REPORT_ALT_SECOND_LINE = "No customers found.";
    public static final String ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS = "abandonedcartreport.mail.fromAddress";
    public static final String ABANDONED_CART_REPORT_MAIL_TO_ADDRESS = "abandonedcartreport.mail.toAddress";
    public static final String ABANDONED_CART_REPORT_MAIL_FROM_ADDRESS_FALLBACK = "pankit.bhanushali@shoppersstop.com";

    public static final String CREDIT_NOTE_PREFIX = "gst.creditNote.prefix";
    /**
     * Magento existing user login verification API url
     */
    public static final String MAGENTOLOGINAPIURL = Config.getParameter("magento.login.api.url");
    /**
     * Magento API Post form encoded format
     */
    public static final String MAGENTOLOGINAPIFORMENCODEDFORMAT = Config.getParameter("magento.login.api.formencodedformat");

    /**
     * Email Cron Job Code.
     */
    public static final String EMAIL_CRON_JOB_CODE = "Cron Job Code:";

    /**
     * Email Cron Job Type.
     */
    public static final String EMAIL_CRON_JOB_TYPE = "Job Type:";

    /**
     * Email Cron Start Time.
     */
    public static final String EMAIL_CRON_JOB_START_TIME = "Start Time:";

    /**
     * Email Cron Job End Time.
     */
    public static final String EMAIL_CRON_JOB_END_TIME = "End Time:";

    /**
     * Email Cron Job Result.
     */
    public static final String EMAIL_CRON_JOB_STATUS = "Job Result:";

    /**
     * Email Cron Job Current Status.
     */
    public static final String EMAIL_CRON_JOB_CURRENT_STATUS = "Job Current Status:";

    /**
     * Related to Consignment delivery
     */
    public static final String CONSIGNMENT_DELIVERED = "ConsignmentDelivered";
    public static final String SITE = "ssl";

    public static final String NOT_UPDATED = "NOT_UPDATED";

    public static final String UPDATED = "UPDATED";

    public static final String ALREADY_UPDATED = "ALREADY_UPDATED";

    public static final String FCC_PRODUCT_CODE = "fcc.product.code";

    public static final String FCC_PRODUCT_UNIT_CODE = "fcc.product.unit.code";

    public static final String MASTER_TENANT = "master";

    public static final String WISHLIST_SIZE = "ssl.wishlist.products.limit";

    public static final String WISHLIST_DISCLAIMER = "ssl.wishlist.disclaimer.text";

    public static final String BILLINGTEMPLATENAME = "invoice";

    public static final String CONTENTCATALOG = "sslContentCatalog";

    public static final String APPLICATION_PDF = "application/pdf";
    public static final String APPLICATION_XLSX = "application/xlsx";
    public static final String PDF = ".pdf";

    // constants for cron job SSLCustomerVerifiedForCodPopulatingJob
    /**
     * Parameter to be fetched from local.properties. Window in number of days, only order placed before this window will be considered. /**
     * To identify COD order confirmation action
     */
    public static final String COD_ORDER_CONFIRMATION_EVENT_ARRIVED = "CODOrderConfirmationEventArrived";

    /**
     * Cod Order confirmation task expiry time
     */
    public static final String COD_ORDER_CONFIRMATION_TASK_EXPIRY_TIME = "cod.order.confirmation.task.expirytime";
    /**
     * Typical value can be 20 (14/15 days return window + 6/5 days fulfillment period).
     */
    public static final String RETURN_WIN_CLOSING_PERIOD_PARAM = "sslm.order.return.window.inclfulfillment";
    public static final String ORDER_WIN_FOR_COD_VERIFICATION = "sslm.order.window.for.codVerification";
    public static final String COD_VERIFICATION_RTV_COUNT = "sslm.order.codVerification.rtv.count";
    public static final String COD_VERIFICATION_SUCCESS_PERCENTAGE = "sslm.order.codVerification.success.percentage";
    public static final String COD_VERIFICATION_STRATEGY = "sslm.order.codVerification.strategy";
    public static final String STATUS = "status";
    public static final String RTV_COUNT = "rtvCount";

	/** Constant for fetching voucher specific error. */
	public static final String VOUCHER_ERROR_KEY = "com.ssl.voucher.error.message.";

	/** Constant for fetching common voucher error. */
	public static final String VOUCHER_COMMON_ERROR_KEY = "commonError";

    private SslCoreConstants() {
        // empty
    }

    public interface NormalizedLookup {
        public static final String BrandNotMapped = "Error Brand";
        public static final String ColorNotMapped = "Error Color";
        public static final String SizeNotMapped = "Error Size";
    }

    // implement here constants used by this extension
    public interface CoreConstants {
        public static final String StagedCatalogVersion = "staged";
        public static final String workflowTemplateCode = "ProductEnrichmentWorkflowTemplate";

    }

    public interface FlexiSearchConstants {
        public static final String AND = " AND ";

        public interface BrandCodeLookup {
            //
        }

    }

    // implement here constants used by workflow tasks/action classes
    public interface WorkflowConstants {
        public static final String DECISION_SUCCESS = "SUCCESS";
        public static final String DECISION_FAILURE = "FAILURE";

    }

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
        public final static String CCAVENUE_FREECHARGE = "OPTWLT";

        /** The Constant CCAVENUE_FREECHARGE_CARDNAME. */
        String CCAVENUE_FREECHARGE_CARDNAME = "FreeCharge";

        /** The Constant CCAVENUE_WALLET. */
        String CCAVENUE_WALLET = "OPTWLT";

        public final static String CCAVENUE_CREDITCARD_PAYMENTMODE = "credit card";
        public final static String CCAVENUE_DEBITCARD_PAYMENTMODE = "debit card";
        public final static String CCAVENUE_NETBANKING_PAYMENTMODE = "Net Banking";
        public final static String CCAVENUE_FREECHARGE_PAYMENTMODE = "Wallet";

        /** The Constant CCAVENUE_WALLET_PAYMENTMODE. */
        String CCAVENUE_WALLET_PAYMENTMODE = "Wallet";

        public final static String CCAVENUE_WEB_PAYMENTMODE = "web";
        public final static String CCAVENUE_IVRS_PAYMENTMODE = "ivrs";
        public final static String CCAVENUE_EMI_PAYMENTMODE = "emi";

    }

    // Added for SSLM 794
    /**
     * SSL SHIPPING DELIVERY COST code.
     */
    public static final String SSL_SHIPPING_DELIVERY_COST = "deliveryCost";
    /**
     * SSL SHIPPING MAX THRESHOLD code.
     */
    public static final String SSL_SHIPPING_MAX_THRESHOLD = "maxThreshold";
    /**
     * SSL FREE SHIPPING code.
     */
    public static final String SSL_FREE_SHIPPING = "freeShipping";
    // End for SSLM 794.

    // Added for SSLM-711 Delivery Confirmation Cron Job constants
    public static final String DELIEVERY_CONFIRMATION_SUBJECT = "Logs for DeliveryConfirmationCronJob will be attached in mail";
    public static final String DELIEVERY_JOB_CODE = " Cron Job Code:";
    public static final String DELIEVERY_JOB_TYPE = " Job Type: ";
    public static final String DELIEVERY_START_TIME = " Start Time: ";
    public static final String DELIEVERY_END_TIME = " End Time:  ";
    public static final String DELIEVERY_JOB_RESULT = " Job Result: ";
    public static final String DELIEVERY_JOB_STATUS = " Job Current Status:";
    public static final String SUCCESS_DELIEVERY = "successDelivery.log";
    public static final String FAIL_DELIEVERY = "failDelivery.log";
    public static final String DATE_FORMATTER = "sms.date.format";

    /** The Constant LEFT_LABEL. */
    public static final String LEFT_LABEL = "left";
    /**
     * ORDER CONFIRMATION MESSAGE code.
     */
    public static final String ORDER_CONFIRMATION_MSG_REGISTERED = "sslm.order.confirmation.msg";
    /**
     * ORDER SHIPPED MESSAGE code.
     */
    public static final String ORDER_SHIPPED_MSG = "sslm.order.shipped.msg";
    /**
     * ORDER DELIVERED MESSAGE code.
     */
    public static final String ORDER_DELIVERED_MSG = "sslm.order.delivered.msg";
    /**
     * ORDER CANCELLATION MESSAGE code.
     */
    public static final String ORDER_CANCELLATION_MSG = "sslm.order.cancel.msg";
    /**
     * SEND POST ORDER MESSAGE code.
     */
    public static final String SEND_POST_ORDER_MSG = "sslm.send.postorder.msg";

    public static final String REFRESH_TOKEN_VALIDITY = Config.getParameter("refresh.token.validity.seconds");

    public static final String ACCESS_TOKEN_VALIDITY = Config.getParameter("access.token.validity.seconds");

    public static final String IMAGE_MIME_TYPE = "image/jpeg";

    public static final String MEDIA_CONTAINER = "media.container.backup";
    public static final String MEDIAS = "medias.backup";

    // For search improvement
    public static final String AUTOCOMPLETE_CATEGORY_L1_INDEDXED_PROPERTY = "l1category";
    public static final String AUTOCOMPLETE_CATEGORY_L2_INDEDXED_PROPERTY = "l2category";
    public static final String AUTOCOMPLETE_CATEGORY_L3_INDEDXED_PROPERTY = "l3category";
    public static final String AUTOSUGGEST_GENDER_IN_CATEGORIES = "genderAutoSuggestionImprovedSearch";
    public static final String AUTOSUGGEST_GENDER_IN_BRANDS = "genderInBrandsAutoSuggestionImprovedSearch";
    public static final String AUTOCOMPLETE_COLLATIONS_COUNT = "5";

    public interface RecommendationConstants {
        public static final String InfiniteAnalyticsUrl = Config.getParameter("ia.recommendations.url");
    }

    // Media Conversion Cronjob
    public static final String FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY = "Hi,";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_FILE_NAME = "FailedMediaContainerReport.xlsx";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY_SECOND_LINE = "Please find the attached Failed Media Container report.";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_EMAIL_SUBJECT = "Failed Media Container Report";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_EMAIL_BODY_ALT_SECOND_LINE = "No media containers found.";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS = "failedmediacontainerreport.mail.fromAddress";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_TO_ADDRESS = "failedmediacontainerreport.mail.toAddress";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REPORT_MAIL_FROM_ADDRESS_FALLBACK = "guneet.singh@nagarro.com";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REASON_FIRST = "Base Image not found for conversion in container";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REASON_SECOND = "Conversion failed for container";
    public static final String FAILED_MEDIA_CONTAINER_REPORT_REASON_THIRD = "No medias in DB for Current Media Container";
    public static final String Media_Container = "media.container.backup";
    public static final String Medias = "medias.backup";

    /**
     * Method to checkSterlingSwitch
     *
     * @return Switch On / Off
     */
    public static boolean getSterlingSwitch() {
        boolean switchOn = false;
        final String sterlingSwitch = Config.getParameter("sterling.switch.on");
        if (sterlingSwitch != null && sterlingSwitch.equalsIgnoreCase("YES")) {
            switchOn = true;
        }
        return switchOn;
    }

    /**
     * Method for checking netcore sms api switch
     *
     * @return On / Off
     */
    public static boolean getNetCoreSMSAPISwitch() {
        return Config.getBoolean("netcore.sms.switch.on", true);
    }

    public static final String ORDER_CONFIRMATION_MSG_GUEST = "sslm.order.confirmation.sms.msg.guest";
    public static final String COD_ORDER_CONFIRMATION_MESSAGE = "sslm.cod.order.confirmation.sms.msg";
    public static final String COD_ORDER_CONFIRMATION_MESSAGE_GUEST = "sslm.cod.order.confirmation.sms.msg.guest";
    public static final String NETCORE_COD_ORDER_CONFIRMATION_MESSAGE = "ssnetcore.cod.order.confirmation.sms.msg";
    public static final String COD_ORDER_SMS_VERIFICATION_CODE = "sslm.cod.order.sms.verification.code";
    public static final String COD_VERIFICATION_DONE_SMS_MSG = "sslm.cod.verification.done.msg";
    public static final String ORDER_BILLED_SMS_MSG = "sslm.order.billed.sms.msg";
    public static final String PARTIAL_ORDER_SHIPPED_EARLY_SMS_MSG = "sslm.partial.order.shipped.early.sms.msg";
    public static final String COMPLETE_ORDER_SHIPPED_EARLY_SMS_MSG = "sslm.complete.order.shipped.early.sms.msg";
    public static final String PARTIAL_ORDER_SHIPPED_SMS_MSG = "sslm.partial.order.shipped.sms.msg";
    public static final String COMPLETE_ORDER_SHIPPED_SMS_MSG = "sslm.complete.order.shipped.sms.msg";
    public static final String ORDER_DISPATCHED_THRESHHOLD = "sslm.order.dispatched.threshhold";
    public static final String PARTIAL_ORDER_CANCELLED_SMS_MSG = "sslm.partial.order.cancelled.sms.msg";
    public static final String ORDER_RETURN_REQUEST_CREATED_SMS = "sslm.order.return.request.created.sms.msg";
    public static final String ORDER_RETURN_REQUEST_RMA_RECEIVED_SMS = "sslm.order.return.request.rma.received.sms.msg";
    public static final String ORDER_EXCHANGE_REQUEST_RMA_RECIEVED_SMS = "sslm.order.exchange.request.rma.recieved.sms.msg";

    public static final String ORDER_RETURN_REFUND_INITIATED_SMS_MSG = "sslm.order.return.refund.initiated.sms.msg";
    public static final String ORDER_RETURN_REFUND_PROCESSED_SMS_MSG = "sslm.order.return.refund.processed.sms.msg";
    public static final String ORDER_RETURN_INSTORE_SMS_MSG = "sslm.order.return.instore.sms.msg";

    // Constants for cron job MaskRmaBankDetailsJob.
    /**
     * Number of ending digits to display in masked account number. Parameter key to be fetched from local.properties
     */
    public static final String DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_END = Config
            .getParameter("sslm.return.bank.ac.mask.end.digits.count") != null
                    ? Config.getParameter("sslm.return.bank.ac.mask.end.digits.count") : "4";
    /**
     * Number of starting digits to display in masked account number. Parameter key to be fetched from local.properties
     */
    public static final String DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_START = Config
            .getParameter("sslm.return.bank.ac.mask.start.digits.count") != null
                    ? Config.getParameter("sslm.return.bank.ac.mask.start.digits.count") : "0";
    public static final String PERIOD_TO_KEEP_UNMASKED_BANK_DETAILS = Config
            .getParameter("sslm.return.period.to.keep.unmasked.bank.details") != null
                    ? Config.getParameter("sslm.return.period.to.keep.unmasked.bank.details") : "30";

    public static final String DATE_FORMAT_MMS_TIMESTAMP = "YYYY-MM-dd HH:mm:ss";

    public interface CustomerNotificationConstants {
        public static final String CUSTOMER_NOTIFICATIONS_NOTIFYME_EMAIL_FROM = "ssl.mail.smtp.notifyme.from";
        public static final String CUSTOMER_NOTIFICATIONS_NOTIFYME_EMAIL_BODY = "ssl_customer_notifications_notifyme_email_body";
        public static final String CUSTOMER_NOTIFICATIONS_NOTIFYME_EMAIL_SUBJECT = "ssl.mail.smtp.notifyme.subject";
    }

    public interface CustomerStockAvailableNotificationConstants {
        public static final String CUSTOMER_NOTIFICATIONS_EMAIL_FROM = "ssl.mail.smtp.notifyme.from";
        public static final String CUSTOMER_NOTIFICATIONS_EMAIL_BODY = "ssl.mail.notify.customer.email.body";
        public static final String CUSTOMER_NOTIFICATIONS_EMAIL_SUBJECT = "ssl.mail.smtp.notify.customer.subject";
    }

    public static final String DUPLICATE_UID_ERROR = "An account already exists for this email address";

    public class SterlingConstants {
        public static final String ITEMPROMOTIONS = "itemPromotions";
        public static final String ORDERPROMOTIONS = "orderPromotions";
        public static final String VOUCHERS = "vouchers";
        public static final String PIECES = "PIECES";
        public static final String PICK = "PICK";
        public static final String SSL_STORE_PICKUP = "SSL_STORE_PICKUP";
        public static final String GIFT_MSG = "GiftMsg";
        public static final String ONE = "1";
        public static final String TWO = "2";
        public static final String THREE = "3";
        public static final String TO = "To";
        public static final String FROM = "From";
        public static final String Y = "Y";
        public static final String LINE_DISCOUNT = "LINE_DISCOUNT";
        public static final String HEADER_DISCOUNT = "HEADER_DISCOUNT";
        public static final String VOUCHER_DISCOUNT = "VOUCHER_DISCOUNT";
        public static final String ORDER_TYPE = "0001";
        public static final String WALLET_PREFIX = "SSCW_%s";
        public static final String AUTHORIZATION = "AUTHORIZATION";
        public static final String CHARGE = "CHARGE";
        public static final String GIFT_CARD = "GiftCard";
        public static final String EGV = "EGV";
        public static final String LOYALTY_CARD = "LoyaltyCard";
        public static final String CC_AVENUE_WALLET = "CCAvenueWallet";
        public static final String FREE_CHARGE = "FreeCharge";
        public static final String NET_BANKING = "NetBanking";
        public static final String CREDIT_CARD = "CreditCard";
        public static final String DEBIT_CARD = "DebitCard";
        public static final String ALLOCATION_RULE_ID = "SSL_PICK";
        public static final String SSL = "SSL";
        public static final String ITEM_PROMOTION_CHARGE_NAME = "ITEMPRM";
        public static final String ORDER_PROMOTION_CHARGE_NAME = "ORDERPRM";
        public static final String VOUCHER_PROMOTION_CHARGE_NAME = "VCR";
        public static final String SHIPPING_CHARGE_CATEGORY = "SSC";
        public static final String STERLING_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        public static final String SS_WALLET_TENDER = "SS Credits";
        public static final String GC_TENDER = "Gift Card";
        public static final String EGV_TENDER = "E-Gift Card";
        public static final String SS_WALLET_PREFIX = "SSCW_";
    }

    public static final String COLOR_SWATCH_NOTFOUND = "SwatchColor_NotFound";
    public static final String STYLE_VARAINT_CODE = "Style Variant Code";
    public static final String COLOR_CODE = "Color Code";
    public static final String COLOR_FAMILY = "Color Family";
    public static final String ERROR_MSG = "Error Meassge";
    public static final String DATE_TIME = "Date time";
    public static final String TEMP_DIR = Config.getParameter("HYBRIS_DATA_DIR");
    public static final String LOG_DIR_NAME = "SwatchColorErrorProducts";
    public static final String LOG_FILE_NAME_TIMESTAMP = "yyyy-MM-dd";
    public static final String XLSX = ".xlsx";
    public static final String SWATCHCOLOR_ERROR_VARIANTS_REPORT = "SWATCHCOLOR_ERROR_VARIANTS_REPORT";
    public static final String SWATCHCOLOR_ERROR_EMAIL_SUB = Config.getParameter("ssl.mail.swatchcolor.error.subject");
    public static final String SWATCHCOLOR_ERROR_EMAIL_BODY = Config.getParameter("ssl.mail.swatchcolor.error.body");
    public static final String HTTPS = "https://";
    public static final String SSL_BASE_STORE = "ssl";

    public class PaytmConstants {

        public static final String TRANSACTION_SUCCESS = "TXN_SUCCESS";
        public static final String TRANSACTION_FAILURE = "TXN_FAILURE";
        public static final String TRANSACTION_PENDING = "PENDING";
        public static final String PAYTM_ORDER_DROP_CHECK_INTERVAL = "paytm.orderdrop.check.interval";

        private PaytmConstants() {
            // do nothing
        }
    }

    public class PayuConstants {

        public static final String RESPONSE_API_SUCCESS_STATUS = "1";
        public static final String STATUS_SUCCESS = "success";
        public static final String STATUS = "status";
        public static final String PAYU_ORDER_DROP_CHECK_INTERVAL = "ssl.payu.orderdrop.check.interval";
        public static final String STATUS_FAILURE = "failure";
		public static final String UNMAPPED_STATUS_DROPPED = "dropped";
        public static final String STATUS_PENDING = "pending";
        public static final String PAYU_PENDING_ORDER_ENABLED = "ssl.payu.pending.order.enabled";

        private PayuConstants() {
            // do nothing
        }
    }
}