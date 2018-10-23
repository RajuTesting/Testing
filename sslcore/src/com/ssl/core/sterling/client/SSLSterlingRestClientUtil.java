/**
 *
 */
package com.ssl.core.sterling.client;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.xml.transform.StringResult;

import com.borngroup.ssl.core.sterling.ibmmq.exception.SSLSterlingMQHandlerException;
import com.borngroup.ssl.core.sterling.ibmmq.util.SSLSterlingMQConnectionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssl.core.sterling.exception.SterlingException;
import com.ssl.core.sterling.orderDetails.dto.OrderDetailsRequestDTO;
import com.ssl.core.sterling.orderDetails.dto.OrderDetailsResponseDTO;
import com.ssl.core.sterling.orderList.dto.AttributeDTO;
import com.ssl.core.sterling.orderList.dto.OrderDetailRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderInputRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderListApiDTO;
import com.ssl.core.sterling.orderList.dto.OrderListRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderListResponseDTO;
import com.ssl.core.sterling.orderList.dto.SterlingResponseError;
import com.ssl.core.sterling.orderList.dto.UserTokenRequest;
import com.ssl.core.sterling.orderList.dto.UserTokenResponse;

/**
 * @author pankajgandhi
 *
 */
public class SSLSterlingRestClientUtil {

    /**
     * 
     */
    private static final String ORDER_DATE = "OrderDate";

    private static final Logger LOG = Logger.getLogger(SSLSterlingRestClientUtil.class);

    private static final String USER_NAME = Config.getParameter("sterling.api.user.name");
    private static final String PASSWORD = Config.getParameter("sterling.api.password");
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String CONTENT_VAL_JSON = "application/json";
    private static final String CONTENT_VAL_XML = "application/xml";
    private static final String HTTP_PROTOCOL = "http://";
    private static final String STERLING_PORT = Config.getParameter("sterling.rest.port");
    private static final String REST_URL = HTTP_PROTOCOL + Config.getParameter("sterling.rest.host")
            + ((StringUtils.isEmpty(STERLING_PORT)) ? StringUtils.EMPTY : ":" + STERLING_PORT);
    private static final String USER_AUTH_URN = "/smcfs/restapi/invoke/login";
    private static final String ORDER_LIST_URN = "/smcfs/restapi/executeFlow/SSLGetOrderListWSWrapper";
    private static final String ORDER_DETAIL_URN = "/smcfs/restapi/executeFlow/SSLGetOrderDetailsWSWrapper";
    private static final String ORDER_LIST_XML_TEMPLATE = "<OrderList TotalOrderList=\"\"><Order OriginalTotalAmount=\"\" BillToID=\"\" CustomerEMailID=\"\" DocumentType=\"\" EntryType=\"\" EnterpriseCode=\"\" OrderDate=\"\" OrderNo=\"\" OrderType=\"\" SellerOrganizationCode=\"\" ReqShipDate=\"\" OrderPurpose=\"\" CustomerContactID=\"\" MinOrderStatus=\"\" MaxOrderStatus=\"\"><OverallTotals GrandCharges=\"\" GrandDiscount=\"\" GrandTax=\"\" GrandTotal=\"\"/></Order></OrderList>";
    private static String USER_TOKEN = StringUtils.EMPTY;
    private static final String STERLING_TOKEN_DEFAULT_ERROR_CODE = Config.getString("sterling.rest.api.token.error.code", "YCP0427");
    private static final String ERROR_CODE = " ,Error Code: ";

    private static final String GENERIC = "GENERIC";
    private static final String NO = "N";
    private static final String YES = "Y";
    private static final String GET_ORDER_LIST = "getOrderList";
    private static final String SSL = "SSL";
    private static final String SALES_ORDER_DOC_TYPE = "0001";

    @Resource(name = "sterlingRestTemplate")
    private RestOperations restOperations;

    @Resource(name = "userService")
    private UserService userService;

    private SSLSterlingMQConnectionManager sslMQConnectionManager;

    private String getUserToken() throws SterlingException {
        if (StringUtils.isNotEmpty(USER_TOKEN)) {
            return USER_TOKEN;
        }
        final UserTokenRequest userTokenRequest = new UserTokenRequest();
        userTokenRequest.setLoginId(USER_NAME);
        userTokenRequest.setPassword(PASSWORD);

        final String uri = REST_URL + USER_AUTH_URN;
        try {
            final HttpEntity<String> httpEntity = getEntity(userTokenRequest, CONTENT_VAL_JSON);
            final ResponseEntity<UserTokenResponse> response = restOperations.exchange(uri, HttpMethod.POST, httpEntity,
                    UserTokenResponse.class);
            final UserTokenResponse responseDto = response.getBody();
            USER_TOKEN = responseDto.getUserToken();

        } catch (final Exception e) {
            LOG.error("----- Exception occured while getting user token from sterling  ---->" + e);
            throw new SterlingException(e.getMessage());
        }

        return USER_TOKEN;
    }

    /**
     * Method to invoke getOrderListAPI to get customer order history details
     *
     * @param orderListRequestDto
     * @param count
     *        - to maintain the recursion in case of user token error
     * @return response
     * @throws SterlingException
     */
    public OrderListResponseDTO getOrderList(final OrderListRequestDTO orderListRequestDto, int count) throws SterlingException {
        setTemplateData(orderListRequestDto);
        final HttpEntity<String> httpEntity = getEntity(orderListRequestDto, CONTENT_VAL_XML);
        final String uri = REST_URL + ORDER_LIST_URN + "?_loginid=" + USER_NAME + "&_token=" + getUserToken();
        LOG.debug("Sterling request URL for getOrderList API:" + uri);
        OrderListResponseDTO responseObj = null;
        SterlingResponseError errorResponseObj = null;
        try {

            final ResponseEntity<OrderListResponseDTO> response = restOperations.exchange(uri, HttpMethod.POST, httpEntity,
                    OrderListResponseDTO.class);
            if (null != response && null != response.getBody()) {
                responseObj = response.getBody();
                LOG.debug("Sterling response for URL : " + uri + " ###### " + responseObj);
                if (null != responseObj.getErrors()) {
                    final String errorCode = getSterlingErrorResponseCode(responseObj.getErrors());
                    if (StringUtils.isNotEmpty(errorCode)) {
                        LOG.error("Error fetching Order List from Sterling for User PK:"
                                + orderListRequestDto.getApiData().getInput().getOrder().getBillToId() + " ,ErrorCode: " + errorCode);
                        throw new SterlingException(errorCode);
                    }
                }
            }
        } catch (final HttpClientErrorException | HttpServerErrorException e) {
            // 400 Error Response is sent back by Sterling if token is expired.
            errorResponseObj = (SterlingResponseError) getUnmarshalledObject(SterlingResponseError.class, e.getResponseBodyAsString());
            final String errorCode = getSterlingErrorResponseCode(errorResponseObj);
            if (count < 1 && STERLING_TOKEN_DEFAULT_ERROR_CODE.equals(errorCode)) {
                USER_TOKEN = StringUtils.EMPTY;
                LOG.error("Token error code detected in sterling Order List Response. Code :" + errorCode + ". ## Retrying with new token"
                        + getUserToken());
                return getOrderList(orderListRequestDto, ++count);
            } else {
                LOG.error("Error code detected in sterling get Order List Response. ErrorCode :" + errorCode);
                throw new SterlingException(errorCode);
            }
        } catch (final Exception e) {
            LOG.error("Exception occured while getting order list from sterling", e);
            throw new SterlingException(e.getMessage());
        }
        return responseObj;
    }

    /**
     * @param errors
     * @return
     */
    private String getSterlingErrorResponseCode(final SterlingResponseError errors) {
        String errorCode = StringUtils.EMPTY;
        if (errors != null && errors.getError() != null) {
            errorCode = errors.getError().getCode();
        }
        return errorCode;
    }

    /**
     * This method sets the required field in the API. Attributes mentioned in request template will be returned in response.
     *
     * @param orderListRequestDto
     *
     */
    private void setTemplateData(final OrderListRequestDTO orderListRequestDto) {
        final OrderListApiDTO api = orderListRequestDto.getApiData();
        api.setTemplate(ORDER_LIST_XML_TEMPLATE);
        orderListRequestDto.setApiData(api);
    }

    /**
     * this methods is used to create the requestDTO for getting the orderDetails
     *
     * @param orderNr
     * @return OrderDetailsRequestDTO
     */
    public OrderDetailsRequestDTO getOrderDetailsRequest(final String orderNr) {
        final OrderDetailsRequestDTO orderDetailsRequestDTO = new OrderDetailsRequestDTO();
        orderDetailsRequestDTO.setEnterpriseCode(SSL);
        orderDetailsRequestDTO.setOrderNo(orderNr);
        orderDetailsRequestDTO.setDocumentType(SALES_ORDER_DOC_TYPE);
        return orderDetailsRequestDTO;
    }

    private HttpEntity<String> getEntity(final Object object, final String headerContentTypeValue) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String contentBody;
        try {
            /*
             * If communication to be processed in xml , request parameters needs to be sent as xml else json
             */
            if (headerContentTypeValue.contains("xml")) {
                contentBody = getMarshalledObject(object);
            } else {
                contentBody = objectMapper.writeValueAsString(object);
            }
        } catch (final IOException e) {
            LOG.error("-------------Exception occured while parsing sterling request DTO ####" + object.getClass() + " #### " + e);
            return null;
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(CONTENT_TYPE, headerContentTypeValue);
        httpHeaders.add(ACCEPT, headerContentTypeValue);
        return new HttpEntity<>(contentBody, httpHeaders);
    }

    /**
     * Method to invoke getOrderDetails sterling API to get customer's order details
     *
     * @param orderDetailsRequest
     * @param count
     *        - to maintain the recursion in case of user token error
     * @return response
     * @throws SterlingException
     */
    public OrderDetailsResponseDTO getOrderDetails(final OrderDetailsRequestDTO orderDetailsRequest, int count) throws SterlingException {
        final HttpEntity<String> httpEntity = getEntity(orderDetailsRequest, CONTENT_VAL_XML);
        final String uri = REST_URL + ORDER_DETAIL_URN + "?_loginid=" + USER_NAME + "&_token=" + getUserToken();
        LOG.debug("Sterling request URL for getOrderDetails API :" + uri);
        OrderDetailsResponseDTO responseObj = null;
        SterlingResponseError errorResponseObj = null;
        try {
            final ResponseEntity<OrderDetailsResponseDTO> response = restOperations.exchange(uri, HttpMethod.POST, httpEntity,
                    OrderDetailsResponseDTO.class);
            if (null != response && null != response.getBody()) {
                LOG.debug("Sterling response for URL : " + uri + " ###### " + response.getBody());
                responseObj = response.getBody();
                if (null != responseObj.getErrors()) {
                    final String errorCode = getSterlingErrorResponseCode(responseObj.getErrors());
                    if (StringUtils.isNotEmpty(errorCode)) {
                        LOG.error("Error fetching Order Details from Sterling for Order No.: " + orderDetailsRequest.getOrderNo()
                                + " ,ErrorCode: " + errorCode);
                        throw new SterlingException(errorCode);
                    }
                }
            }

        } catch (final HttpClientErrorException | HttpServerErrorException e) {
            // 400 Error Response is sent back by Sterling if token is expired.
            // TODO add Error Handler
            errorResponseObj = (SterlingResponseError) getUnmarshalledObject(SterlingResponseError.class, e.getResponseBodyAsString());
            final String errorCode = getSterlingErrorResponseCode(errorResponseObj);
            if (count < 1 && STERLING_TOKEN_DEFAULT_ERROR_CODE.equals(errorCode)) {
                USER_TOKEN = StringUtils.EMPTY;
                LOG.error("Token error code detected in sterling Order List Response. Code :" + errorCode + ". ## Retrying with new token"
                        + getUserToken());
                return getOrderDetails(orderDetailsRequest, ++count);
            } else {
                LOG.error("Error code detected in sterling get Order Details Response. ErrorCode :" + errorCode);
                throw new SterlingException(errorCode);
            }
        } catch (final Exception e) {
            LOG.error("Exception occured while getting order details from sterling", e);
            throw new SterlingException(e.getMessage());
        }
        return responseObj;
    }

    public String getMarshalledObject(final Object object) {
        try {
            final JAXBContext contextObj = JAXBContext.newInstance(object.getClass());
            final Marshaller marshaller = contextObj.createMarshaller();
            final StringResult writer = new StringResult();
            marshaller.marshal(object, writer);
            return StringEscapeUtils.unescapeHtml(writer.toString());
        } catch (final JAXBException e) {
            LOG.error("Exception while parsing the catalog data into XML: ", e);
        }
        return null;
    }

    private Object getUnmarshalledObject(final Class className, final String xmlContent) {
        try {
            final JAXBContext contextObj = JAXBContext.newInstance(className);
            final Unmarshaller unMarshaller = contextObj.createUnmarshaller();
            return unMarshaller.unmarshal(new StringReader(xmlContent));
        } catch (final JAXBException e) {
            LOG.error("Exception while parsing the catalog data into XML: ", e);
        }
        return null;
    }

    public boolean sendRequestToMQ(final String request, final String queueName) {
        Session session = null;
        MessageProducer producer = null;
        if (null != request) {
            try {
                session = getSslMQConnectionManager().getSession();
                final Queue queue = getSslMQConnectionManager().getQueue(queueName);
                producer = session.createProducer(queue);
                final TextMessage text = session.createTextMessage();
                text.setText(request);
                producer.send(text);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Message sent sucessfully to Sterling queue: " + queueName + " on: " + new Date() + " Detailed message -->"
                            + text);
                }
                return true;
            } catch (final SSLSterlingMQHandlerException e) {
                LOG.error("Unable to process Sterling queue: " + queueName + " Exception: " + e.getMessage() + ERROR_CODE
                        + e.getErrorCode());
            } catch (final JMSException e) {
                LOG.error("Exception in handling request with MQ server while sending data to Sterling queue: " + queueName
                        + "  Exception: " + e.getMessage() + ERROR_CODE + e.getErrorCode());
            } finally {
                try {
                    if (producer != null) {
                        producer.close();
                    }
                    if (session != null) {
                        session.close();
                    }
                } catch (final JMSException e) {
                    LOG.error("Exception in handling request with MQ server while sending data to queue: " + queueName + "  Exception: "
                            + e.getMessage() + ERROR_CODE + e.getErrorCode());
                }
            }
        } else {
            return false;
        }

        return false;
    }

    /**
     * Populates the request object to be sent in getOrderList API for getting list of pickup orders
     *
     * @param pageableData
     * @return OrderListRequestDTO: Request object to be sent in getOrderList API for pickup orders
     */
    public OrderListRequestDTO populateGetOrderListRequestDto(final PageableData pageableData) {

        final OrderListRequestDTO requestDTO = new OrderListRequestDTO();
        final OrderListApiDTO api = new OrderListApiDTO();
        final OrderInputRequestDTO input = new OrderInputRequestDTO();
        final OrderDetailRequestDTO order = new OrderDetailRequestDTO();
        final CustomerModel currentCustomer = (CustomerModel) userService.getCurrentUser();

        /**
         * Row number is set to the row number of the first row element of the next page that is to be retrived
         */
        requestDTO.setStartRowNumber(String.valueOf((pageableData.getCurrentPage() * pageableData.getPageSize())));
        requestDTO.setPageSize(String.valueOf(pageableData.getPageSize()));
        requestDTO.setPaginationStrategy(GENERIC);

        api.setInput(input);
        api.setIsFlow(NO);
        api.setName(GET_ORDER_LIST);

        order.setEnterpriseCode(SSL);
        order.setIgnoreOrdering(NO);
        order.setDocumentType(SALES_ORDER_DOC_TYPE);

        final AttributeDTO sortAttributes = order.getOrderBy().getAttribute();
        sortAttributes.setDesc(YES);
        sortAttributes.setName(ORDER_DATE);

        if (currentCustomer != null) {
            order.setBillToId(currentCustomer.getPk().toString());
        }
        input.setOrder(order);
        api.setInput(input);
        requestDTO.setApiData(api);

        return requestDTO;
    }

    public SSLSterlingMQConnectionManager getSslMQConnectionManager() {
        return sslMQConnectionManager;
    }

    public void setSslMQConnectionManager(final SSLSterlingMQConnectionManager sslMQConnectionManager) {
        this.sslMQConnectionManager = sslMQConnectionManager;
    }

}
