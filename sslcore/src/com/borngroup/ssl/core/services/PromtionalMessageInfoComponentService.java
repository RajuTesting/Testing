package com.borngroup.ssl.core.services;

import com.borngroup.ssl.core.model.PromotionalMessageInfoComponentModel;

/**
 * @author guneetsingh
 *
 */
public interface PromtionalMessageInfoComponentService {

    /**
     * Method to get all active messages
     *
     * @return : List of active messages
     */
    PromotionalMessageInfoComponentModel getActiveMessage();

}
