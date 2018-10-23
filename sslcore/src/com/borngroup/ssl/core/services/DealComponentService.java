/**
 *
 */
package com.borngroup.ssl.core.services;

import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author tejsharma
 *
 */
public interface DealComponentService {
    /**
     * Method returns deal component model for given code/id.
     *
     * @param dealId
     * @return
     */
    DealComponentModel getDealById(String dealId);

    /**
     * Method to get url of a deal.
     * 
     * @param deal
     * @return
     */
    String getDealPageUrl(final DealComponentModel deal);
}
