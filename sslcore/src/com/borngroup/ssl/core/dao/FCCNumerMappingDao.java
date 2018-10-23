/**
 * 
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.FccNumberMappingModel;

/**
 * <p>
 * FCCNumerMappingDao.java : interface to access FCCNumberMappingModel
 * details.
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public interface FCCNumerMappingDao {
	/**
	 * Method for finding all FCCNumberMappings 
	 * @return {@link List of FccNumberMappingModel}
	 */
	List<FccNumberMappingModel> findAllFCCNumberMappings();
    /**
     * Method for checking FCCNumberMapping by customer PK
     * @param customerPk
     * @return {@link List of FccNumberMappingModel}
     */
	List<FccNumberMappingModel> findFCCNumberMapping(String customerPk);
}
