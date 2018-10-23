/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.EanModel;

/**
 * @author tejsharma
 *
 */
public interface EanDao {
    /**
     * Method to get all Eans.
     * 
     * @return EanModel
     */
    List<EanModel> findEans();

    /**
     * Method to get Ean by number.
     * 
     * @param eanNumber
     * @return EanModel
     */
    List<EanModel> findEanByNumber(String eanNumber);
}
