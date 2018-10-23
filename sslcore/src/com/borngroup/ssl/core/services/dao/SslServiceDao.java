/**
 *
 */
package com.borngroup.ssl.core.services.dao;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import com.borngroup.ssl.core.model.DeliveryPostalCodeModel;
import com.borngroup.ssl.core.model.MobileAppReleaseDetailsModel;
import com.borngroup.ssl.core.model.MobileVersionUpdateInfoModel;
import com.borngroup.ssl.core.model.SSLCheckoutMessagesModel;
import com.borngroup.ssl.core.model.news.NewsLetterSubscribeForCustomerModel;
import com.ssl.paymentgateways.model.PaymentGatewayMappingModel;

/**
 *
 *
 */
public interface SslServiceDao {

    DeliveryPostalCodeModel checkPinCodeForDeliverable(Integer Pincode);

    NewsLetterSubscribeForCustomerModel checkForAllReadySubscribe(String email);

    Boolean isOrderAvailableForCustomer(final String orderCode, final CustomerModel customerModel, final BaseStoreModel baseStoreModel);

    OrderModel getOrderDetailsForEmailAndId(String email, String Id);

    MobileAppReleaseDetailsModel getLatestBuildDetails(String mobileType);

    /**
     * @param mobileType
     * @param serviceType
     * @return MobileAppReleaseDetailsModel
     */
    MobileAppReleaseDetailsModel getLatestBuildDetailsMobile(String mobileType, String serviceType);

    /**
     * Method to fetch force and soft update version info from database
     *
     * @param mobileType
     * @return MobileVersionUpdateInfoModel
     */
    MobileVersionUpdateInfoModel getVersionUpdateInfoModel(String mobileType);

    List<SSLCheckoutMessagesModel> getAllCheckoutMessages();

    List<CustomerModel> getUserByEmail(final CustomerModel customerModel);

    List<SSLCheckoutMessagesModel> getMessage(String code);
    
    List<PaymentGatewayMappingModel> getAllCheckoutSubtitles();

	List<SSLCheckoutMessagesModel> getProductDetailLinkMessages(final List<String> codes);
}
