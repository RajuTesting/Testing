/**
 *
 */
package com.borngroup.ssl.core.services;

import com.borngroup.ssl.core.model.SslCCAvenueCustomerCardDetailModel;

/**
 * Service Implementation for SslCCAvenueCustomerCardDetailModel
 * <p/>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public interface SslCustomerCCAvenueCardService {

    /**
     * Gets SslCCAvenueCustomerCardDetailModel for the passed code.
     *
     * @param code
     * @return SslCCAvenueCustomerCardDetailModel
     */
    SslCCAvenueCustomerCardDetailModel getCCAvenueCustomerCardDetailForCode(String code);

    /**
     * Saves card details for the customer.
     *
     * @param sslCCAvenueCustomerCardDetailModel
     *        {@link SslCCAvenueCustomerCardDetailModel}.
     */
    void saveAndLinkCardDetails(SslCCAvenueCustomerCardDetailModel sslCCAvenueCustomerCardDetailModel);

    /**
     * Sets Stored card for passed Id as default for Logged In customer.
     *
     * @param cardId
     */
    void setStoredCardForIdAsDefault(String cardId);

    /**
     * Deletes stored card for passed cardId.
     *
     * @param cardId
     */
    void deleteStoredCardForId(String cardId);

    /**
     * updates stored card label for passed cardId.
     *
     * @param cardId
     * @param cardLabel
     */
    void updateStoredCardForId(Integer cardId, String cardLabel, Boolean isDefault);
}
