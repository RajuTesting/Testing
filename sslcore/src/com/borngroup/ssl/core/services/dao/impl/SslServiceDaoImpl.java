/**
 *
 */
package com.borngroup.ssl.core.services.dao.impl;

import de.hybris.platform.commerceservices.constants.GeneratedCommerceServicesConstants.Enumerations.CustomerType;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.enums.AppType;
import com.borngroup.ssl.core.model.DeliveryPostalCodeModel;
import com.borngroup.ssl.core.model.MobileAppReleaseDetailsModel;
import com.borngroup.ssl.core.model.MobileVersionUpdateInfoModel;
import com.borngroup.ssl.core.model.SSLCheckoutMessagesModel;
import com.borngroup.ssl.core.model.news.NewsLetterSubscribeForCustomerModel;
import com.borngroup.ssl.core.services.dao.SslServiceDao;
import com.ssl.paymentgateways.model.PaymentGatewayMappingModel;

/**
 *
 *
 */
public class SslServiceDaoImpl implements SslServiceDao {
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public DeliveryPostalCodeModel checkPinCodeForDeliverable(final Integer Pincode) {
        final String query = "SELECT {" + DeliveryPostalCodeModel.PK + "} FROM {" + "DeliveryPostalCode" + "} WHERE {" + "pincode"
                + "}=?Pincode";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("Pincode", Pincode);
        fsQuery.setResultClassList(Collections.singletonList(DeliveryPostalCodeModel.class));

        final SearchResult<DeliveryPostalCodeModel> searchResult = getFlexibleSearchService().search(fsQuery);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {

            return searchResult.getResult().get(0);
        }
        return null;

    }

    @Override
    public NewsLetterSubscribeForCustomerModel checkForAllReadySubscribe(final String email) {
        final String query = "SELECT {" + NewsLetterSubscribeForCustomerModel.PK + "} FROM {" + "NewsLetterSubscribeForCustomer"
                + "} WHERE {" + "email" + "}=?Email";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("Email", email);
        fsQuery.setResultClassList(Collections.singletonList(NewsLetterSubscribeForCustomerModel.class));

        final SearchResult<NewsLetterSubscribeForCustomerModel> searchResult = getFlexibleSearchService().search(fsQuery);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {

            return searchResult.getResult().get(0);
        }
        return null;
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService
     *        the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @SuppressWarnings("boxing")
    @Override
    public Boolean isOrderAvailableForCustomer(final String orderCode, final CustomerModel customerModel,
            final BaseStoreModel baseStoreModel) {
        final String query = "SELECT {" + OrderModel.PK + "} FROM {" + "Order"
                + "} WHERE {code} = ?code AND {versionID} IS NULL AND {user} = ?customer AND {store} = ?store";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("code", orderCode);
        fsQuery.addQueryParameter("customer", customerModel);
        fsQuery.addQueryParameter("store", baseStoreModel);
        fsQuery.setResultClassList(Collections.singletonList(OrderModel.class));

        final SearchResult<OrderModel> searchResult = getFlexibleSearchService().search(fsQuery);
        if (CollectionUtils.isNotEmpty(searchResult.getResult())) {

            return true;
        }
        return false;
    }

    @Override
    public OrderModel getOrderDetailsForEmailAndId(final String email, final String Id) {

        final String query = "SELECT {o.PK}" + " FROM {Order AS o" + " LEFT JOIN Customer AS c ON {o.user}={c.pk} }"
                + " WHERE {c.uid}like?email AND {o.code}=?orderId AND {o.VERSIONID} IS NULL";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", (new StringBuilder("%")).append(email).append("%").toString());
        params.put("orderId", Id);

        final FlexibleSearchQuery flexiQuery = new FlexibleSearchQuery(query);
        flexiQuery.addQueryParameters(params);

        final SearchResult<OrderModel> result = getFlexibleSearchService().search(flexiQuery);
        return CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;
    }

    @Override
    public MobileAppReleaseDetailsModel getLatestBuildDetails(final String mobileType) {
        final String query = "select {" + MobileAppReleaseDetailsModel.PK + "} from {" + MobileAppReleaseDetailsModel._TYPECODE
                + " as ma join " + AppType._TYPECODE + " as at on {ma:" + MobileAppReleaseDetailsModel.APPTYPE
                + "}={at:pk}} where {at:code} = ?mobileType order by {" + MobileAppReleaseDetailsModel.CREATIONTIME + "} desc";

        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("mobileType", mobileType);
        fsQuery.setResultClassList(Collections.singletonList(MobileAppReleaseDetailsModel.class));

        final SearchResult<MobileAppReleaseDetailsModel> searchResult = getFlexibleSearchService().search(fsQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult().get(0) : null;
    }

    @Override
    public MobileAppReleaseDetailsModel getLatestBuildDetailsMobile(final String mobileType, final String serviceType) {
        final String query = "select {" + MobileAppReleaseDetailsModel.PK + "} from {" + MobileAppReleaseDetailsModel._TYPECODE
                + " as ma join " + AppType._TYPECODE + " as at on {ma:" + MobileAppReleaseDetailsModel.APPTYPE
                + "}={at:pk}} where {at:code} = ?mobileType AND {serviceType} = ?serviceType order by {"
                + MobileAppReleaseDetailsModel.CREATIONTIME + "} desc";

        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("mobileType", mobileType);
        fsQuery.addQueryParameter("serviceType", serviceType);
        fsQuery.setResultClassList(Collections.singletonList(MobileAppReleaseDetailsModel.class));

        final SearchResult<MobileAppReleaseDetailsModel> searchResult = getFlexibleSearchService().search(fsQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult().get(0) : null;
    }

    /*
     * This method returns the List of CustomerModel
     *
     * @param customerModel customerModel
     *
     * @return list of {@link CustomerModel}
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<CustomerModel> getUserByEmail(final CustomerModel customerModel) {

        String email = customerModel.getUid();
        if (customerModel.getType() != null) {
            if (CustomerType.GUEST.equals(customerModel.getType().getCode())) {
                email = StringUtils.substringAfter(customerModel.getUid(), "|");
            }
        }
        final String query = "SELECT {c.PK}" + " FROM {Customer AS c}" + " WHERE {c.originalUid}like?emailId";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("emailId", (new StringBuilder("%")).append(email).append("%").toString());

        final FlexibleSearchQuery flexiQuery = new FlexibleSearchQuery(query);
        flexiQuery.addQueryParameters(params);

        final SearchResult<CustomerModel> result = getFlexibleSearchService().search(flexiQuery);

        return CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult() : ListUtils.EMPTY_LIST;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.dao.SslServiceDao#getVersionUpdateInfoModel(java.lang.String)
     */
    @Override
    public MobileVersionUpdateInfoModel getVersionUpdateInfoModel(final String mobileType) {

        final FlexibleSearchQuery flexibleSearchQuery = buildQuery(mobileType);
        final SearchResult<MobileVersionUpdateInfoModel> searchResult = getFlexibleSearchService().search(flexibleSearchQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult().get(0) : null;
    }

    /**
     * Method to build search query to fetch MobileVersionUpdateInfoModel
     *
     * @param mobileType
     * @return FlexibleSearchQuery
     */
    private FlexibleSearchQuery buildQuery(final String mobileType) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {");
        stringBuilder.append(MobileVersionUpdateInfoModel.PK);
        stringBuilder.append("} from {");
        stringBuilder.append(MobileVersionUpdateInfoModel._TYPECODE + " as m");
        stringBuilder.append(" join " + AppType._TYPECODE + " as a");
        stringBuilder.append(" on {m:" + MobileVersionUpdateInfoModel.MOBILETYPE + "}={a:pk}}");
        stringBuilder.append(" where {a:code}= ?mobileType");

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(stringBuilder.toString());
        flexibleSearchQuery.addQueryParameter("mobileType", mobileType);
        flexibleSearchQuery.setResultClassList(Collections.singletonList(MobileVersionUpdateInfoModel.class));
        return flexibleSearchQuery;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.dao.SslServiceDao#getAllCheckoutMessages()
     */
    @Override
    public List<SSLCheckoutMessagesModel> getAllCheckoutMessages() {
        final String query = "select {pk} from {SSLCheckoutMessages}";
        final List<SSLCheckoutMessagesModel> checkoutMessages = getFlexibleSearchService()
                .<SSLCheckoutMessagesModel> search(new FlexibleSearchQuery(query)).getResult();
        if (CollectionUtils.isNotEmpty(checkoutMessages)) {
            return checkoutMessages;
        }
        return null;
    }

    @Override
    public List<SSLCheckoutMessagesModel> getMessage(final String code) {
        final String query = "select {pk} from {SSLCheckoutMessages} where {messageCode} = ?messageCode";
        final Map<String, Object> params = new HashMap();
        params.put("messageCode", code);
        final List<SSLCheckoutMessagesModel> checkoutMessages = getFlexibleSearchService()
                .<SSLCheckoutMessagesModel> search(new FlexibleSearchQuery(query, params)).getResult();
        if (CollectionUtils.isNotEmpty(checkoutMessages)) {
            return checkoutMessages;
        }
        return null;
    }
    
    @Override
	public List<PaymentGatewayMappingModel> getAllCheckoutSubtitles() {
		final String query = "select {pk} from {PaymentGatewayMapping}";
		final List<PaymentGatewayMappingModel> checkoutMessages = getFlexibleSearchService()
				.<PaymentGatewayMappingModel>search(new FlexibleSearchQuery(query)).getResult();
		if (CollectionUtils.isNotEmpty(checkoutMessages)) {
			return checkoutMessages;
		}
		return null;
	}

	@Override
	public List<SSLCheckoutMessagesModel> getProductDetailLinkMessages(final List<String> codes) {
		final String query = "select {pk} from {SSLCheckoutMessages} where {messageCode} IN (?messageCodes)";
		final Map<String, Object> params = new HashMap();
		params.put("messageCodes", codes);
		final List<SSLCheckoutMessagesModel> productDetailLinkMessages = getFlexibleSearchService()
				.<SSLCheckoutMessagesModel>search(new FlexibleSearchQuery(query, params)).getResult();
		if (CollectionUtils.isNotEmpty(productDetailLinkMessages)) {
			return productDetailLinkMessages;
		}
		return null;
	}
}
