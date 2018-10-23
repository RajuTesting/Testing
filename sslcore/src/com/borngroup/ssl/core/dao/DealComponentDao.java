/**
 *
 */
package com.borngroup.ssl.core.dao;

import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author tejsharma
 *
 */
public interface DealComponentDao {
    /**
     * Method returns deal component model for given code/id.
     * 
     * @param dealId
     * @return
     */
    DealComponentModel getDealById(String dealId);
}
