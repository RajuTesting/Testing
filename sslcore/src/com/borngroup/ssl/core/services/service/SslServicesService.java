package com.borngroup.ssl.core.services.service;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.model.SSLCheckoutMessagesModel;
import com.borngroup.ssl.core.model.news.NewsLetterSubscribeForCustomerModel;
import com.borngroup.ssl.facades.mobile.data.MobileAppReleaseData;
import com.ssl.paymentgateways.model.PaymentGatewayMappingModel;

public interface SslServicesService {

    String checkDeliverable(Integer pincode);

    String addNewsLetter(String email, List<String> serviceList);

    /**
     * @param email
     * @return NewsLetterSubscribeForCustomerModel
     */
    public NewsLetterSubscribeForCustomerModel getNewsLetterServicesForCustomer(String email);

    /**
     * @param email
     * @param serviceLinks
     * @param frequencyCode
     * @return String
     */
    public String updateNewsLetter(String email, List<String> serviceLinks, String frequencyCode);

    public OrderData getOrderByCode(final String email, final String orderCode);

    Boolean isExistingCustomer(final String email);

    Boolean isOrderAvailable(final String email, final String orderCode);

    OrderModel getOrderByEmailAndId(final String email, final String orderCode);

    MobileAppReleaseData getLatestBuildDetails(String mobileType);

    /**
     * Method to get mobile app version update details
     *
     * @param mobileType
     * @param serviceType
     * @param versionNumber
     * @return MobileAppReleaseData
     */
    MobileAppReleaseData getLatestBuildDetailsMobile(String mobileType, String serviceType, String versionNumber);

    List<SSLCheckoutMessagesModel> getAllCheckoutMessages();

    /**
     * @param mobileType
     * @param serviceType
     * @param currentVersionID
     * @param currentOsVersion
     * @return
     */
    MobileAppReleaseData getLatestBuildDetailsMobile(String mobileType, String serviceType, String currentVersionID,
            String currentOsVersion);

    List<SSLCheckoutMessagesModel> getMessage(final String code);
    
   /**
    * To retrieve the subtitles for payment methods.
    * 
    * @return sslPaymentTenderModelList
    */
   List<PaymentGatewayMappingModel> getAllCheckoutSubtitles();

	Map<String, String> getProductDetailLinkMessages(final List<String> codes);
}
