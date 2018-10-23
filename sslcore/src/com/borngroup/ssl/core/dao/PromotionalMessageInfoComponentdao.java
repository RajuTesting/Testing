/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.PromotionalMessageInfoComponentModel;

/**
 * @author guneetsingh
 *
 */
public interface PromotionalMessageInfoComponentdao {
    /**
     * Method to get active promotions
     * 
     * @return list of active promotions
     */
    List<PromotionalMessageInfoComponentModel> getActivePromotionalMessage();

}
