/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CCAvenueWalletPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.DebitCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.FreeChargePaymentInfoModel;
import de.hybris.platform.core.model.order.payment.NetBankingPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.util.Base64;
import de.hybris.platform.util.Config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;
import com.borngroup.ssl.core.exceptions.CustomAuthenticationFailureException;
import com.borngroup.ssl.core.util.models.APIInputObject;
import com.ccavenue.security.AesCryptUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVWriter;

/**
 * The Class CommonHelper.
 *
 * @author t.balagopalan
 */
public class CommonHelper {

    /** The Constant SUCCESS_STATUS. */
    private static final String SUCCESS_STATUS = "Success";

    /** The Constant SSL_WEBSERVICE_LOGGING. */
    private static final Logger LOG = Logger.getLogger(CommonHelper.class);

    /** The Constant SSL_WEBSERVICE_LOGGING. */
    private static final Logger SSL_WEBSERVICE_LOGGING = Logger.getLogger("WebServiceLogging");

    /** Singleton. */
    private static CommonHelper commonHelper = new CommonHelper();

    private static final String ACCESSTOKEN_BUFFERTIME = "ssl.loyalty.accesstoken.expiry.buffertime";

    private final SslLoyaltyAccessToken accessToken = new SslLoyaltyAccessToken();

    /**
     * Internal private enum for the type of response we get from each external api/web service.
     */
    private enum ResponseTypeInternal {

        /** The string. */
        STRING,
        /** The json. */
        JSON,
        /** The xml. */
        XML
    }

    /**
     * Private Singleton contructor.
     */
    private CommonHelper() {
        // Thirumalai - 28 Jul 2015
        // Private constructor to hide the implicit public constructor
        // Done based on the code quality check by Sonar
    }

    /**
     * Thirumalai - 11 Aug 2015 Refactoring the code for Sonar as it reported error for switch case lines of code exceeding 5.
     *
     * @param br - BufferedReader
     * @return String
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private String extractStringResponse(final BufferedReader br) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String output = "";

        while (null != (output = br.readLine())) {
            sb.append(output);
        }

        return sb.toString();
    }

    /**
     * Gets the single instance of CommonHelper.
     *
     * @return CommonHelper - Singleton object
     */
    public static CommonHelper getInstance() {
        // Singleton
        return commonHelper;
    }

    /**
     * Encrypts the passed in text with the AES algorithm The AesCryptUtil is an utility JAS provided by CCAvenue.
     *
     * @param plainText the plain text
     * @param workingKey the working key
     * @return String - Encrypted text.
     * @throws Exception the exception
     */
    public String encrypt(final String plainText, final String workingKey) throws Exception {
        // Using CCAvenue AES Crypt Util encrypt the passed in text
        final AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        return aesUtil.encrypt(plainText);
    }

    /**
     * Decrypts the passed in value with the AES algorithm The AesCryptUtil is an utility JAS provided by CCAvenue.
     *
     * @param encryptedText - Encrypted text.
     * @param workingKey the working key
     * @return String - Decrypted text.
     * @throws Exception the exception
     */
    public String decrypt(final String encryptedText, final String workingKey) throws Exception {
        // Using CCAvenue AES Crypt Util decrypt the passed in encrypted text
        final AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
        return aesUtil.decrypt(encryptedText);
    }

    /**
     * Rounding to specified number of places and return primitive double.
     *
     * @param value the value
     * @param places the places
     * @return double
     */
    public double round(final double value, final int places) {
        return round(BigDecimal.valueOf(value), places).doubleValue();
    }

    /**
     * Round.
     *
     * @param value the value
     * @param places the places
     * @return the big decimal
     */
    public BigDecimal round(final BigDecimal value, final int places) {
        return value.setScale(places < 0 ? 0 : places, RoundingMode.HALF_UP);
    }

    /**
     * Round.
     *
     * @param value the value
     * @return the double
     */
    public double round(final double value) {
        return round(value, 2);
    }

    /**
     * Round.
     *
     * @param value the value
     * @return the big decimal
     */
    public BigDecimal round(final BigDecimal value) {
        return round(value, 2);
    }

    /**
     * Converts a POJO object to a Map object.
     *
     * @param obj - A POJO object.
     * @return ObjectNode (JSON Object)
     * @throws Exception the exception
     */
    public ObjectNode convertObjectToNode(final Object obj) throws Exception {
        final ObjectNode objectNode;
        final ObjectMapper mapper = new ObjectMapper();
        objectNode = mapper.valueToTree(obj);

        return objectNode;
    }

    /**
     * Returns a Map object from a query string.
     *
     * @param queryString the query string
     * @return Map<String, String>
     */
    public Map<String, String> getMapFromQueryString(final String queryString) {
        final Map<String, String> valueMap = new HashMap<String, String>();
        final StringTokenizer tokenizer = new StringTokenizer(queryString.toString(), "&");
        while (tokenizer.hasMoreTokens()) {
            final String vPairValue = tokenizer.nextToken();
            if (vPairValue != null) {
                final StringTokenizer vKeyPair = new StringTokenizer(vPairValue, "=");
                if (vKeyPair.hasMoreTokens()) {
                    final String key = vKeyPair.nextToken();
                    final String value = vKeyPair.nextToken();
                    try {
                        valueMap.put(key, URLDecoder.decode(value, "UTF-8"));
                    } catch (final UnsupportedEncodingException e) {
                        valueMap.put(key, value);
                    }
                }
            }
        }
        return valueMap;
    }

    /**
     * Convert an object node to final query string with key value pair (key=value).
     *
     * @param objectNode - The ObjectNode object.
     * @param ignorePropertyWithoutValue - Boolean to indicate whether to ignore property that are empty.
     * @return String - The final query string.
     */
    public String convertNodeToQueryString(final ObjectNode objectNode, final Boolean ignorePropertyWithoutValue) {
        String valueMap = "";
        final Iterator<Map.Entry<String, JsonNode>> nodeIterator = objectNode.fields();

        // Iterate the Map object to form a query string
        while (nodeIterator.hasNext()) {
            final Map.Entry<String, JsonNode> entry = nodeIterator.next();
            final String key = entry.getKey();
            final String value = entry.getValue().textValue();
            if (ignorePropertyWithoutValue.booleanValue() && null == value) {
                continue;
            }
            valueMap = String.format("%s%s%s=%s", valueMap, "".equals(valueMap) ? "" : "&", key, value);
        }

        return valueMap;
    }

    /**
     * Convert the final query string with key value pair (key=value).
     *
     * @param obj - Object to convert.
     * @param ignorePropertyWithoutValue - Boolean to indicate whether to ignore property that are empty.
     * @return String - Final query string.
     * @throws Exception the exception
     */
    public String convertObjectToQueryString(final Object obj, final Boolean ignorePropertyWithoutValue) throws Exception {
        final ObjectNode objectNode = convertObjectToNode(obj);
        return convertNodeToQueryString(objectNode, ignorePropertyWithoutValue);
    }

    /**
     * Convert the object to JSON String. Internally this method calls ConvertToJSONString(object, hasRequesstRootElementForJSON) method by
     * passing hasRequesstRootElementForJSON as false.
     *
     * @param obj - Request object.
     * @return String - Object converted to JSON string.
     * @throws Exception the exception
     */
    public String convertToJSONString(final Object obj) throws Exception {
        // convert user object to json string
        return convertToJSONString(obj, false);
    }

    /**
     * Convert the object to JSON String.
     *
     * @param obj the obj
     * @param hasRequestRootElementForJSON the has request root element for json
     * @return String
     * @throws Exception the exception
     */
    public String convertToJSONString(final Object obj, final boolean hasRequestRootElementForJSON) throws Exception {
        // convert user object to json string
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, hasRequestRootElementForJSON);
        return mapper.writeValueAsString(obj);
    }

    /**
     * Converts the object to XML string.
     *
     * @param obj - Request object
     * @return String - Object converted to Xml string
     * @throws Exception the exception
     */
    public String convertToXmlString(final Object obj) throws Exception {
        // convert object to xml string
        final JAXBContext context = JAXBContext.newInstance(obj.getClass());
        final Marshaller marshaller = context.createMarshaller();
        final StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        return sw.toString();
    }

    /**
     * Converts a Map to a POJO object for the provided class.
     *
     * @param <T> the generic type
     * @param mapObject the map object
     * @param objectClass the object class
     * @return T
     */
    public <T> T convertMapToPojoObject(final Map<String, String> mapObject, final Class<T> objectClass) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mapObject, objectClass);
    }

    /**
     * Converts a query string to a POJO object for the provided class.
     *
     * @param <T> the generic type
     * @param queryString the query string
     * @param objectClass the object class
     * @return T
     */
    public <T> T convertQueryStringToPojoObject(final String queryString, final Class<T> objectClass) {
        final Map<String, String> objectMap = getMapFromQueryString(queryString);
        return convertMapToPojoObject(objectMap, objectClass);
    }

    /**
     * Creates a Xml to Pojo Unmarshaller from an object class.
     *
     * @param objClass - Input object class to create the Unmarshaller
     * @return Unmarshaller
     * @throws Exception the exception
     */
    public Unmarshaller createXmlToPojoUnmarshaller(final Class<? extends Object> objClass) throws Exception {
        // Convert XML to POJO
        final JAXBContext jaxbContext = JAXBContext.newInstance(objClass);
        return jaxbContext.createUnmarshaller();
    }

    /**
     * Gets the API input object.
     *
     * @param url - Target URL
     * @param queryString - Query String to be appended to the URL
     * @param serializedPostData - Object data to be posted to the URL
     * @param contentType - Content Type
     * @param method - API method
     * @param hasResponseRootElementForJSON - This will be needed only for ContentTypeEnum.JSON. This indicates if the de-serialized
     *        response object class has an attribute decoration as JsonRootName. This means that the properties will be enclosed as an
     *        object and assigned to an attribute, which is the root name.
     *
     *        For example: { "ResponseRootName": { "Property1": "value1", "Property2": "value2" }}
     * @return APIInputObject
     */
    public APIInputObject getAPIInputObject(final String url, final String queryString, final String serializedPostData,
            final ContentTypeEnum contentType, final APIMethodEnum method, final boolean hasResponseRootElementForJSON) {
        final APIInputObject apiInputObject = new APIInputObject();
        apiInputObject.setContentType(contentType);
        apiInputObject.setMethod(method);
        apiInputObject.setQueryString(queryString);
        apiInputObject.setSerializedPostData(serializedPostData);
        apiInputObject.setUrl(url);
        apiInputObject.setResponseRootElementForJSON(hasResponseRootElementForJSON);
        LOG.info("URL is: "+url+"query is :"+queryString);
        return apiInputObject;
    }

    /**
     * Gets the Authorization API input object.
     *
     * @param url - Target URL
     * @param queryString - Query String to be appended to the URL
     * @param serializedPostData - Object data to be posted to the URL
     * @param contentType - Content Type
     * @param method - API method
     * @param hasResponseRootElementForJSON - This will be needed only for ContentTypeEnum.JSON. This indicates if the de-serialized
     *        response object class has an attribute decoration as JsonRootName. This means that the properties will be enclosed as an
     *        object and assigned to an attribute, which is the root name.
     *
     *        For example: { "ResponseRootName": { "Property1": "value1", "Property2": "value2" }}
     * @param basicAuth - Basic Authorization credential
     * @return APIInputObject
     */
    public APIInputObject getAuthorizationAPIInputObject(final String url, final String queryString, final String serializedPostData,
            final ContentTypeEnum contentType, final APIMethodEnum method, final boolean hasResponseRootElementForJSON,
            final String basicAuth) {
        final APIInputObject apiInputObject = new APIInputObject();
        apiInputObject.setContentType(contentType);
        apiInputObject.setMethod(method);
        apiInputObject.setQueryString(queryString);
        apiInputObject.setSerializedPostData(serializedPostData);
        apiInputObject.setUrl(url);
        apiInputObject.setResponseRootElementForJSON(hasResponseRootElementForJSON);
        apiInputObject.setBasicAuthorization(basicAuth);
        return apiInputObject;
    }

    /**
     * Call a RESTful API that returns a String response.
     *
     * @param apiInputObject - APIInputObject that holds data for both GET and POST requests.
     * @return String
     * @throws Exception the exception
     */
    public String callAPIStringResponse(final APIInputObject apiInputObject) throws Exception {
        return callAPI(apiInputObject, ResponseTypeInternal.STRING, null).toString();
    }

    /**
     * Call a RESTful API that returns a JSON response. Casting to the required POJO object should be done at the calling end.
     *
     * @param apiInputObject - APIInputObject that holds data for both GET and POST requests.
     * @param objClass the obj class
     * @return Object
     * @throws Exception the exception
     */
    public Object callAPIJSONResponse(final APIInputObject apiInputObject, final Class<? extends Object> objClass) throws Exception {
        return callAPI(apiInputObject, ResponseTypeInternal.JSON, objClass);
    }

    /**
     * Call a RESTful API that returns a XML response. Casting to the required POJO object should be done at the calling end.
     *
     * @param apiInputObject - APIInputObject that holds data for both GET and POST requests.
     * @param objClass the obj class
     * @return Object
     * @throws Exception the exception
     */
    public Object callAPIXMLResponse(final APIInputObject apiInputObject, final Class<? extends Object> objClass) throws Exception {
        return callAPI(apiInputObject, ResponseTypeInternal.XML, objClass);
    }

    /**
     * Call a RESTful API.
     *
     * @param apiInputObject - APIInputObject that holds data for both GET and POST requests.
     * @param responseTypeInternal the response type internal
     * @param objClass the obj class
     * @return Object
     * @throws Exception the exception
     */
    public Object callAPI(final APIInputObject apiInputObject, final ResponseTypeInternal responseTypeInternal,
            final Class<? extends Object> objClass) throws Exception {

        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;

        // Declare URL
        final URL url = new URL(String.format("%s%s", apiInputObject.getUrl(), apiInputObject.getQueryString()));
        SSL_WEBSERVICE_LOGGING.info("BlueDart URL==" + url);
        SSL_WEBSERVICE_LOGGING.debug("BlueDart URL==" + url);
        SSL_WEBSERVICE_LOGGING.debug("BlueDart Path==" + url.getPath());
        SSL_WEBSERVICE_LOGGING.info("BlueDart Path==" + url.getPath());
        SSL_WEBSERVICE_LOGGING.info("BlueDart toURI==" + url.toURI());
        SSL_WEBSERVICE_LOGGING.debug("BlueDart toURI==" + url.toURI());
        SSL_WEBSERVICE_LOGGING.debug("apiInputObject.getUrl()==" + apiInputObject.getUrl());
        SSL_WEBSERVICE_LOGGING.debug("apiInputObject.getQueryString()==" + apiInputObject.getQueryString());
        try {
            // Instantiate connection
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(apiInputObject.getMethod().toString());
            conn.setRequestProperty("Accept", apiInputObject.getContentType().toString());
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            // Set the content type. This is important to indicate the API about
            // the media type.
            // Else an unsupported media type exception with http status code
            // 415 is thrown.
            conn.setRequestProperty("Content-Type", apiInputObject.getContentType().toString() + "; charset=utf-8");
            // for secure connection
            if (!StringUtils.isEmpty(apiInputObject.getBasicAuthorization())) {
                final String authStr = apiInputObject.getBasicAuthorization();
                final String authEncoded = Base64.encodeBytes(authStr.getBytes());
                conn.setRequestProperty("Authorization", "Basic " + authEncoded);
                conn.setRequestProperty("HTTP_X_MERCHANT_CODE", Config.getParameter("ekart.merchant.code"));
            }

            if ("POST".equalsIgnoreCase(apiInputObject.getMethod().toString())) {
                final String serializedPostData = apiInputObject.getSerializedPostData();
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - Request is: " + serializedPostData);
                if ("".equals(serializedPostData)) {
                    throw new RuntimeException("Failed : Post data is empty : ");
                }

                // setDoOutput(true) is required for post calls
                conn.setDoOutput(true);
                os = conn.getOutputStream();
                os.write(serializedPostData.getBytes());
                os.flush();
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            inputStreamReader = new InputStreamReader(conn.getInputStream());
            br = new BufferedReader(inputStreamReader);
            final Object objectResponse;
            final String response;

            switch (responseTypeInternal) {
            case STRING:
                // Convert response to String
                response = extractStringResponse(br);
                LOG.debug("CommonHelper::API call - STRING response is: " + response);
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - STRING Response is: " + response);
                objectResponse = response;
                break;
            case JSON:
                // Convert JSON to POJO
                response = IOUtils.toString(br);
                LOG.debug("CommonHelper::API call - JSON Response is: " + response);
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - JSON Response is: " + response);
                final ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, apiInputObject.isResponseRootElementForJSON());
                objectResponse = mapper.readValue(response, objClass);
                break;
            case XML:
                // Convert XML to POJO
                response = IOUtils.toString(br);
                LOG.debug("CommonHelper::API call - XML Response is: " + response);
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - XML Response is: " + response);
                objectResponse = createXmlToPojoUnmarshaller(objClass).unmarshal(new ByteArrayInputStream(response.getBytes()));
                break;
            default:
                objectResponse = null;
                break;
            }

            return objectResponse;
        } catch (final Exception ex) {
            LOG.error(ex.getMessage(), ex);
            SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - Response is: " + ex.getMessage());
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(os);
            if (conn != null) {
                IOUtils.close(conn);
            }
        }

        return null;
    }

    /**
     * Returns Formatted date for the given format.
     *
     * @param dateToFormat the date to format
     * @param formatCode the format code
     * @return String
     */
    public String getFormattedDate(final Date dateToFormat, final String formatCode) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatCode);
        return simpleDateFormat.format(dateToFormat);
    }

    /**
     * Gets Date for the provided date string and format.
     *
     * @param formattedDate the formatted date
     * @param formatCode the format code
     * @return Date
     */
    public Date getDateForFormat(final String formattedDate, final String formatCode) {
        final SimpleDateFormat formatter = new SimpleDateFormat(formatCode);
        try {
            return formatter.parse(formattedDate);
        } catch (final ParseException e) {
            return null;
        }
    }

    /**
     * Returns a byte array for the provided String list.
     *
     * @param allLines the all lines
     * @return byte[]
     */
    public byte[] getCsvAsBytes(final List<String[]> allLines) {
        final StringWriter stringWriter = new StringWriter();
        @SuppressWarnings("resource")
        final CSVWriter csvWriter = new CSVWriter(stringWriter);
        csvWriter.writeAll(allLines);
        return stringWriter.toString().getBytes();
    }

    /**
     * Returns the actual paid amount for this transaction. This will iterate through the transaction and get the last captured amount. If
     * no captured amount exists then it returns the planned amount.
     *
     * @param transaction the transaction
     * @return the actual captured amount.
     */
    public BigDecimal getActualPaymentAmount(final PaymentTransactionModel transaction) {
        // Check entries for the captured state and refunded state.
        BigDecimal captured = transaction.getPlannedAmount();
        BigDecimal refunded = BigDecimal.valueOf(0.0D);
        for (final PaymentTransactionEntryModel entry : transaction.getEntries()) {
            if (TransactionStatus.ACCEPTED.name().equals(entry.getTransactionStatus())
                    || SUCCESS_STATUS.equals(entry.getTransactionStatus())) {
                // Partially refunded status is set for gift card when a lesser
                // amount is
                // redeemed after the full amount is captured originally.
                if (PaymentTransactionType.CANCEL.equals(entry.getType()) && this.isCCAvenuePayment(transaction.getInfo())) {
                    // This transaction has been cancelled. Hence its amount is
                    // 0.
                    captured = BigDecimal.valueOf(0.0D);
                    refunded = BigDecimal.valueOf(0.0D);
                    break;
                } else if (this.isPaymentCaptured(captured, entry)) {
                    captured = entry.getAmount();
                } else if (this.isPaymentRefunded(refunded, entry)) {
                    refunded = refunded.add(entry.getAmount());
                }
            }
        }

        return captured.subtract(refunded).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getActualPaymentAmountForReturns(final PaymentTransactionModel transaction) {
        // Check entries for the captured state and refunded state.
        BigDecimal captured = transaction.getPlannedAmount();
        BigDecimal refunded = BigDecimal.valueOf(0.0D);
        for (final PaymentTransactionEntryModel entry : transaction.getEntries()) {
            if (TransactionStatus.ACCEPTED.name().equals(entry.getTransactionStatus())
                    || SUCCESS_STATUS.equals(entry.getTransactionStatus())) {
                // Partially refunded status is set for gift card when a lesser
                // amount is
                // redeemed after the full amount is captured originally.
                if (PaymentTransactionType.CANCEL.equals(entry.getType()) && this.isCCAvenuePayment(transaction.getInfo())) {
                    // This transaction has been cancelled. Hence its amount is
                    // 0.
                    captured = BigDecimal.valueOf(0.0D);
                    refunded = refunded.add(BigDecimal.valueOf(0.0D));
                    break;
                } else if (this.isPaymentCapturedForReturns(captured, entry)) {
                    captured = entry.getAmount();
                } else if (this.isPaymentRefundedForReturns(refunded, entry)) {
                    refunded = refunded.add(entry.getAmount());
                }
            }
        }

        return captured.subtract(refunded).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getActualPaymentAmountForAdvRec(final PaymentTransactionModel transaction) {
        // Check entries for the captured state and refunded state.
        BigDecimal captured = transaction.getPlannedAmount();
        for (final PaymentTransactionEntryModel entry : transaction.getEntries()) {
            if (TransactionStatus.ACCEPTED.name().equals(entry.getTransactionStatus())
                    || SUCCESS_STATUS.equals(entry.getTransactionStatus())) {
                if (this.isPaymentCapturedForReturns(captured, entry) || PaymentTransactionType.AUTHORIZATION.equals(entry.getType())) {
                    captured = entry.getAmount();
                }
            }
        }

        return captured.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total refunded amount for order.
     *
     * @param orderModel
     * @return refunded amount
     */
    public BigDecimal getOrderRefundedAmount(final OrderModel orderModel) {
        // Check entries for the captured state and refunded state.
        BigDecimal totalRefunded = BigDecimal.valueOf(0.0D);
        for (final PaymentTransactionModel paymentTransaction : orderModel.getPaymentTransactions()) {
            BigDecimal refunded = BigDecimal.valueOf(0.0D);
            for (final PaymentTransactionEntryModel entry : paymentTransaction.getEntries()) {
                if (TransactionStatus.ACCEPTED.name().equals(entry.getTransactionStatus())
                        || SUCCESS_STATUS.equals(entry.getTransactionStatus())) {
                    // Partially refunded status is set for gift card when a lesser
                    // amount is
                    // redeemed after the full amount is captured originally.
                    if (PaymentTransactionType.CANCEL.equals(entry.getType()) && this.isCCAvenuePayment(paymentTransaction.getInfo())) {
                        // This transaction has been cancelled. Hence its amount is
                        // 0.
                        refunded = BigDecimal.valueOf(0.0D);
                        break;
                    } else if (this.isPaymentRefunded(refunded, entry)) {
                        refunded = refunded.add(entry.getAmount());
                    }
                }
            }
            totalRefunded = totalRefunded.add(refunded);
        }

        return totalRefunded.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Checks if payment status is refunded.
     *
     * @param refunded {@link BigDecimal}
     * @param entry {@link PaymentTransactionEntryModel}
     * @return boolean
     */
    private boolean isPaymentRefunded(final BigDecimal refunded, final PaymentTransactionEntryModel entry) {
        return (PaymentTransactionType.REFUNDED.equals(entry.getType()) || PaymentTransactionType.PARTIALLY_REFUNDED.equals(entry.getType())
                || PaymentTransactionType.CANCEL.equals(entry.getType()));
    }

    private boolean isPaymentRefundedForReturns(final BigDecimal refunded, final PaymentTransactionEntryModel entry) {
        return (PaymentTransactionType.REFUNDED.equals(entry.getType()) || PaymentTransactionType.PARTIALLY_REFUNDED.equals(entry.getType())
                || PaymentTransactionType.CANCEL.equals(entry.getType())
                || PaymentTransactionType.NEFT_REFUND_PENDING.equals(entry.getType()));
    }

    /**
     * Checks if payment status is captured.
     *
     * @param captured {@link BigDecimal}
     * @param entry {@link PaymentTransactionEntryModel}
     * @return boolean
     */
    private boolean isPaymentCaptured(final BigDecimal captured, final PaymentTransactionEntryModel entry) {
        return (PaymentTransactionType.CAPTURE.equals(entry.getType()) || PaymentTransactionType.PARTIAL_CAPTURE.equals(entry.getType()));
    }

    private boolean isPaymentCapturedForReturns(final BigDecimal captured, final PaymentTransactionEntryModel entry) {
        return (PaymentTransactionType.CAPTURE.equals(entry.getType()) || PaymentTransactionType.PARTIAL_CAPTURE.equals(entry.getType()));
    }

    /**
     * Checks if payment is CC Avenue payment.
     *
     * @param info {@link PaymentInfoModel}
     * @return boolean
     */
    private boolean isCCAvenuePayment(final PaymentInfoModel info) {
        return info instanceof CreditCardPaymentInfoModel || info instanceof NetBankingPaymentInfoModel
                || info instanceof DebitCardPaymentInfoModel || info instanceof FreeChargePaymentInfoModel
                || info instanceof CCAvenueWalletPaymentInfoModel;
    }

    // Thirumalai - 20 Oct 2015
    /**
     * Converts a POJO object to a Map object.
     *
     * @param obj - A POJO object.
     * @return Map<String, Object> - Converted Map object.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     *
     *         public static Map<String, String> convertObjectToMap(final Object obj) throws IllegalAccessException,
     *         IllegalArgumentException, InvocationTargetException { final Method[] methods = obj.getClass().getMethods();
     *
     *         // Declare a Map final Map<String, String> objectMap = new HashMap<String, String>();
     *
     *         // Iterate the methods to fetch properties and its values for (final Method m : methods) { if (m.getName().startsWith("get")
     *         && !m.getName().startsWith("getClass")) { // Get the value of the property final String value = (String) m.invoke(obj); //
     *         Add the property name (as key) and its corresponding value to the objecMap. objectMap.put(m.getName().substring(3), value); }
     *         } return objectMap; }
     *
     *         public static Field getPropertyField(final Class<?> beanClass, final String property) throws NoSuchFieldException { if
     *         (beanClass == null) { throw new IllegalArgumentException( "beanClass cannot be null"); }
     *
     *         Field field = null; try { field = beanClass.getDeclaredField(property); } catch (final NoSuchFieldException e) { if
     *         (beanClass.getSuperclass() == null) { throw e; } // look for the field in the superClass field =
     *         getPropertyField(beanClass.getSuperclass(), property); } return field; }
     *
     *         public <T> T getSortedCollection(final Collection<T> collectionToSort) { if (collectionToSort.size() > 1) { final List
     *         tempCollectionToSort = new ArrayList<T>(collectionToSort);
     *
     *         Collections.sort(tempCollectionToSort, new Comparator<T>() {
     * @Override public int compare(final T p1, final T p2) { if (p1 == null || p2 == null) { return 0; } return
     *           p2.getCreationtime().compareTo(p1.getCreationtime()); } });
     *
     *           return (T) tempCollectionToSort; }
     *
     *           return (T) collectionToSort; }
     */

    public Object callESBAPIResponse(final APIInputObject apiInputObject, final Class<? extends Object> objClass) throws Exception {

        if ((apiInputObject.getContentType()).equals(ContentTypeEnum.JSON)) {
            try {
                return callESBAPI(apiInputObject, ResponseTypeInternal.JSON, objClass, false);
            } catch (final CustomAuthenticationFailureException authenticationFailureException) {
                SSL_WEBSERVICE_LOGGING.error(authenticationFailureException);
                try {
                    SSL_WEBSERVICE_LOGGING.debug("Retrying to update access token for calling ESB API on exception "
                            + authenticationFailureException.getMessage());
                    return this.callESBAPI(apiInputObject, ResponseTypeInternal.JSON, objClass, true);
                } catch (final Exception exception) {
                    SSL_WEBSERVICE_LOGGING.error(exception);
                }
                return null;
            }
        }
        try {
            return callESBAPI(apiInputObject, ResponseTypeInternal.XML, objClass, false);
        } catch (final CustomAuthenticationFailureException authenticationFailureException) {
            SSL_WEBSERVICE_LOGGING.error(authenticationFailureException);
            try {
                SSL_WEBSERVICE_LOGGING.error(
                        "Retrying to update access token for calling ESB API on exception " + authenticationFailureException.getMessage());
                return this.callESBAPI(apiInputObject, ResponseTypeInternal.XML, objClass, true);
            } catch (final Exception exception) {
                SSL_WEBSERVICE_LOGGING.error(exception);
            }
            return null;
        }

    }

    public Object callESBAPI(final APIInputObject apiInputObject, final ResponseTypeInternal responseTypeInternal,
            final Class<? extends Object> objClass, final boolean forceFetchToken) throws Exception {

        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;

        SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - ESB URL: " + apiInputObject.getUrl());
        // SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - Payload: "+apiInputObject.getSerializedPostData());
        SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - ESB getQueryString: " + apiInputObject.getQueryString());
        // Declare URL
        final URL url = new URL(String.format("%s%s", apiInputObject.getUrl(), apiInputObject.getQueryString()));

        try {

            if (Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_ENABLED, true)) {
                // Instantiate connection
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - TLS Trust Every one: "
                        + Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_TRUSTEVERYONE_ENABLED, false));
                if (Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_TRUSTEVERYONE_ENABLED, false)) {
                    trustEveryone();
                }
                conn = (HttpsURLConnection) url.openConnection();
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod(apiInputObject.getMethod().toString());
            conn.setRequestProperty("Accept", apiInputObject.getContentType().toString());
            SSL_WEBSERVICE_LOGGING.info("CommonHelper::callAPI - Connection Timeout : "
                    + Config.getInt(SSLLoyaltyOAuthConstants.SSL_ESBAPI_CONNECTION_TIMEOUT, 60000));
            conn.setConnectTimeout(Config.getInt(SSLLoyaltyOAuthConstants.SSL_ESBAPI_CONNECTION_TIMEOUT, 60000));
            conn.setReadTimeout(60000);

            if (Config.getBoolean(SSLLoyaltyOAuthConstants.API_AUTHENTICATION_ENABLED, true)) {

                // Check access tocken already available and not expired or forcefetch enabled
                if (Config.getBoolean(SSLLoyaltyOAuthConstants.ACCESS_TOKEN_FORCEFETCH_ENABLED, false)
                        || StringUtils.isBlank(accessToken.getAccessToken())
                        || (accessToken.getExpDate() != null && !new Date().before(accessToken.getExpDate())) || forceFetchToken) {
                    JSONObject jsonObj = null;
                    if (StringUtils.isNotBlank(accessToken.getRefreshToken())) {
                        jsonObj = this.getTokenForESBAPI(accessToken.getRefreshToken());
                    } else {
                        jsonObj = this.getTokenForESBAPI();
                    }

                    if (jsonObj.has("access_token")) {
                        updateAcessToken(jsonObj);
                    } else if (jsonObj.has("Error")) {
                        SSL_WEBSERVICE_LOGGING.error(jsonObj.toString());
                        throw new RuntimeException(jsonObj.get("Error").toString());
                    } else {
                        throw new RuntimeException(jsonObj.toString());
                    }

                }

                conn.setRequestProperty("Authorization", "Bearer " + accessToken.getAccessToken());
            }
            // Set the content type. This is important to indicate the API about
            // the media type.
            // Else an unsupported media type exception with http status code
            // 415 is thrown.
            conn.setRequestProperty("Content-Type", apiInputObject.getContentType().toString() + "; charset=utf-8");

            if ("POST".equalsIgnoreCase(apiInputObject.getMethod().toString())) {
                final String serializedPostData = apiInputObject.getSerializedPostData();
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - Request is: " + serializedPostData);

                if ("".equals(serializedPostData)) {
                    throw new RuntimeException("Failed : Post data is empty : ");
                }

                // setDoOutput(true) is required for post calls
                conn.setDoOutput(true);
                os = conn.getOutputStream();
                os.write(serializedPostData.getBytes());
                os.flush();
            }

            final Object objectResponse;
            String response;
            SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI: Response Code : " + conn.getResponseCode());
            if (Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_ACCESSTOKEN_RETRY_ENABLED, false) && conn.getResponseCode() == 401) {
                throw new CustomAuthenticationFailureException("Unauthorised: HTTP error code: " + conn.getResponseCode());
            }
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {

                inputStreamReader = new InputStreamReader(conn.getErrorStream());

                br = new BufferedReader(inputStreamReader);
                response = IOUtils.toString(br);
                SSL_WEBSERVICE_LOGGING.error("CommonHelper::callAPI: Response is : " + response);

                if (null != response && response.contains("fault")) {

                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                else if (null != response && response.trim().startsWith("<")) {
                    return objectResponse = createXmlToPojoUnmarshaller(objClass).unmarshal(new ByteArrayInputStream(response.getBytes()));
                }

                else if (null != response && response.trim().startsWith("{")) {
                    final ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, apiInputObject.isResponseRootElementForJSON());
                    return objectResponse = mapper.readValue(response, objClass);
                }

                else {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

            }

            inputStreamReader = new InputStreamReader(conn.getInputStream());
            br = new BufferedReader(inputStreamReader);

            switch (responseTypeInternal) {

            case JSON:
                // Convert JSON to POJO
                response = IOUtils.toString(br);
                // SSL_WEBSERVICE_LOGGING.debug("CommonHelper::API call - JSON Response is: " + response);
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - JSON Response is: " + response);
                final ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, apiInputObject.isResponseRootElementForJSON());
                objectResponse = mapper.readValue(response, objClass);
                break;

            case XML:
                // Convert XML to POJO
                response = IOUtils.toString(br);
                // SSL_WEBSERVICE_LOGGING.debug("CommonHelper::API call - XML Response is: " + response);
                SSL_WEBSERVICE_LOGGING.debug("CommonHelper::callAPI - XML Response is: " + response);
                objectResponse = createXmlToPojoUnmarshaller(objClass).unmarshal(new ByteArrayInputStream(response.getBytes()));
                break;

            default:
                objectResponse = null;
                break;
            }

            return objectResponse;
        } catch (final CustomAuthenticationFailureException cuException) {
            throw cuException;
        } catch (final Exception ex) {
            LOG.error(ex.getMessage(), ex);
            SSL_WEBSERVICE_LOGGING.error("CommonHelper::callAPI - Response is: " + ex.getMessage());
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(os);
            if (conn != null) {
                IOUtils.close(conn);
            }
        }

        return null;
    }

    /**
     * To fetch access token using refresh token
     *
     * @param refreshToken
     * @return JSONObject
     */
    private JSONObject getTokenForESBAPI(final String refreshToken) throws Exception {

        final Map<String, String> params = new HashMap<String, String>(0);
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_ID_PARAM), Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_ID));
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.SECRET_KEY_PARAM),
                Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_SECRET));
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.GRANT_TYPE_PARAM), SSLLoyaltyOAuthConstants.REFRESH_TOKEN);
        params.put(SSLLoyaltyOAuthConstants.REFRESH_TOKEN, refreshToken);
        return callService(Config.getParameter(SSLLoyaltyOAuthConstants.OAUTH_BASE_URL),
                Config.getParameter(SSLLoyaltyOAuthConstants.ACCESS_TOKEN_URL), "POST", params, null);
    }

    /**
     * This method will update the accessToken bean properties with token data from ESB
     *
     * @param jsonObj
     * @throws JSONException
     */
    private void updateAcessToken(final JSONObject jsonObj) throws JSONException

    {
        SSL_WEBSERVICE_LOGGING.info("Received Access Token is :" + jsonObj.getString("access_token"));
        accessToken.setAccessToken(jsonObj.getString("access_token"));
        if (jsonObj.has("refresh_token")) {
            accessToken.setRefreshToken(jsonObj.getString("refresh_token"));
        }
        if (jsonObj.has("token_type")) {
            accessToken.setTokenType(jsonObj.getString("token_type"));
        }
        if (jsonObj.has("expires_in")) {
            accessToken.setExpiresin(jsonObj.getString("expires_in"));

            if (StringUtils.isNumeric(accessToken.getExpiresin())) {
                final Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(cal.getTimeInMillis() + (Long.parseLong(accessToken.getExpiresin()) * 1000));
                cal.add(Calendar.MINUTE, -(Config.getInt(ACCESSTOKEN_BUFFERTIME, 10)));
                accessToken.setExpDate(cal.getTime());
                SSL_WEBSERVICE_LOGGING.info("OAuth Token expiry Date: " + cal.getTime());

            } else {
                accessToken.setExpDate(Calendar.getInstance().getTime());
            }
            // int min = Integer.parseInt(accessToken.getExpiresin())/60;
            // Date d1 = new Date();
            // d1 = DateUtils.addMinutes(d1, min);
            // accessToken.setExpDate(d1);
        }

    }

    /*
     * public Object getTokenForApp() throws Exception{
     *
     * Map<String,String> params = new HashMap<String, String>(0); params.put(SSLLoyaltyOAuthConstants.CLIENT_ID_PARAM,
     * SSLLoyaltyOAuthConstants.CLIENT_ID); params.put(SSLLoyaltyOAuthConstants.SECRET_KEY_PARAM, SSLLoyaltyOAuthConstants.CLIENT_SECRET);
     * params.put(SSLLoyaltyOAuthConstants.GRANT_TYPE_PARAM, SSLLoyaltyOAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS); params.put("username",
     * SSLLoyaltyOAuthConstants.USER_NAME); params.put("password", SSLLoyaltyOAuthConstants.PASSWORD);
     *
     * return callService(SSLLoyaltyOAuthConstants.BASE_URL,SSLLoyaltyOAuthConstants.ACCESS_TOKEN_URL,"POST",params,null);
     *
     * }
     */

    /**
     * This method will call the OAuth access token url and return the token data
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject getTokenForESBAPI() throws Exception {

        final Map<String, String> params = new HashMap<String, String>(0);
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_ID_PARAM), Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_ID));
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.SECRET_KEY_PARAM),
                Config.getParameter(SSLLoyaltyOAuthConstants.CLIENT_SECRET));
        params.put(Config.getParameter(SSLLoyaltyOAuthConstants.GRANT_TYPE_PARAM),
                Config.getParameter(SSLLoyaltyOAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS));
        // params.put("username", SSLLoyaltyOAuthConstants.USER_NAME);
        // params.put("password", SSLLoyaltyOAuthConstants.PASSWORD);
        return callService(Config.getParameter(SSLLoyaltyOAuthConstants.OAUTH_BASE_URL),
                Config.getParameter(SSLLoyaltyOAuthConstants.ACCESS_TOKEN_URL), "POST", params, null);

    }

    /**
     * This is a generic method will be used to call the webservice
     *
     * @param baseUrl
     * @param path
     * @param method
     * @param params
     * @param payload
     * @return JSONObject
     */
    public static JSONObject callService(final String baseUrl, final String path, final String method, final Map<String, String> params,
            final String payload) {

        // SSL_WEBSERVICE_LOGGING.info("OAuth base Url: "+baseUrl);

        if (StringUtils.isBlank(baseUrl)) {
            return getErrorJsonObject(new Exception("Base URL is missing "));
        }
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        if (!StringUtils.isBlank(path)) {
            builder.path(path);
        }

        if (params != null && !params.isEmpty()) {
            for (final Map.Entry<String, String> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        final URI targetUrl = builder.build().toUri();
        JSONObject jsonObject = null;
        SSL_WEBSERVICE_LOGGING.info(
                "TLS Trust Every one: " + Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_TRUSTEVERYONE_ENABLED, false));
        if (Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_ENABLED, true)
                && Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_TRUSTEVERYONE_ENABLED, false)) {
            trustEveryone();
        }
        SSL_WEBSERVICE_LOGGING.info("Connecting: " + targetUrl.toString());

        HttpURLConnection urlConnection = null;
        InputStreamReader streamReader = null;
        BufferedReader br = null;
        try {
            if (Config.getBoolean(SSLLoyaltyOAuthConstants.SSL_LOYALTY_API_TLS_ENABLED, true)) {
                urlConnection = (HttpsURLConnection) targetUrl.toURL().openConnection();
            } else {
                urlConnection = (HttpURLConnection) targetUrl.toURL().openConnection();
            }
            urlConnection.setRequestMethod(method);
            SSL_WEBSERVICE_LOGGING.info("CommonHelper::call TokenForESBAPI - Connection Timeout : "
                    + Config.getInt(SSLLoyaltyOAuthConstants.SSL_ACCESSTOKEN_CONNECTION_TIMEOUT, 60000));
            urlConnection.setConnectTimeout(Config.getInt(SSLLoyaltyOAuthConstants.SSL_ACCESSTOKEN_CONNECTION_TIMEOUT, 60000));
            final int responseCode = urlConnection.getResponseCode();

            streamReader = new InputStreamReader(urlConnection.getInputStream());
            br = new BufferedReader(streamReader);
            final String response = IOUtils.toString(br);
            SSL_WEBSERVICE_LOGGING.info("Response code: " + responseCode + " Response: " + response);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                jsonObject = new JSONObject(response);

            } else {
                final Map attributes = new HashMap();
                attributes.put("ResponseCode", responseCode);
                if (responseCode == 401) {
                    attributes.put("Error", "Unauthorized");
                } else {
                    attributes.put("Error", response);
                }
                attributes.put("Response", response);
                jsonObject = new JSONObject(attributes);

            }
        } catch (final MalformedURLException e) {
            SSL_WEBSERVICE_LOGGING.info("Error: ", e);
            return getErrorJsonObject(e);
        } catch (final IOException e) {
            SSL_WEBSERVICE_LOGGING.info("Error: ", e);
            return getErrorJsonObject(e);
        } catch (final JSONException e) {
            // YTODO Auto-generated catch block
            SSL_WEBSERVICE_LOGGING.info("Error: ", e);
            return getErrorJsonObject(e);
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(streamReader);
            if (urlConnection != null) {
                IOUtils.close(urlConnection);
            }
            // urlConnection.disconnect();
        }
        return jsonObject;
    }

    /**
     * This method will return the json object for the exception
     *
     * @param e
     * @return JSONObject
     */
    private static JSONObject getErrorJsonObject(final Exception e) {

        final Map attributes = new HashMap();
        attributes.put("Error", e.getMessage());
        final JSONObject jsonObject = new JSONObject(attributes);

        return jsonObject;
    }

    /**
     * This method is to trust all the TLS certificates irrespective of host
     */
    private static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(final String hostname, final SSLSession session) {
                    return true;
                }
            });
            final SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                    //
                }

                public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                    //
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (final Exception e) { // should never happen
            LOG.error("Exception occurred: " + e.getLocalizedMessage());
        }
    }
	/**
	 * encrypting text
	 * 
	 * @param plainText
	 * @return encrypted Text
	 */
	public String encryptText(String plainText) {
		String encryptedText = null;
		try {
			byte[] encryptArray = org.apache.commons.codec.binary.Base64
					.encodeBase64(plainText.getBytes());
			return new String(encryptArray, "UTF-8");
		} catch (Exception exception) {
			LOG.info("An exception occured while encryption {} " + exception);
		}
		return encryptedText;
	}

	/**
	 * Method for decrypting text
	 * 
	 * @param plainText
	 * @return decrypted Text
	 */
	public String decryptText(String plainText) {
		String decreptedText = null;
		try {
			byte[] decarray = org.apache.commons.codec.binary.Base64
					.decodeBase64(plainText.getBytes());
			return new String(decarray, "UTF-8");
		} catch (Exception exception) {
			LOG.info("An exception occured while decreption {} " + exception);
		}
		return decreptedText;
	}

    public double getRoundedAmount(final double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}