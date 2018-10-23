/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.borngroup.ssl.core.model.EanModel;

/**
 * @author tejsharma
 *
 */
public interface EanService {
    /**
     * Method to get all Ean objects.
     *
     * @return
     */
    List<EanModel> getEans();

    /**
     * Method to get Ean for particular number. Either returns Ean if found or throws error.
     *
     * @param eanNumber
     * @return EanModel
     * @throws UnknownIdentifierException : Ean with given number not found.
     * @throws AmbiguousIdentifierException : Duplicate Ean for given number were found.
     */
    EanModel getEanByNumber(String eanNumber) throws UnknownIdentifierException, AmbiguousIdentifierException;

    /**
     * Method to get Ean for a particular number, in case it does not exists create one and return that Ean object. Throws error in case of
     * duplicates.
     *
     * @param eanNumber
     * @return EanModel
     * @throws AmbiguousIdentifierException : In case duplicate Ean were found, this exception is thrown.
     */
    EanModel getOrCreateEanByNumber(String eanNumber) throws AmbiguousIdentifierException;
}
