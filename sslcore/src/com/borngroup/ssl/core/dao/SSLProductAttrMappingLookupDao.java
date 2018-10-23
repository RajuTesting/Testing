/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.model.SSLProductAttrMappingModel;

/**
 * @author midhun.bose
 *
 */
public interface SSLProductAttrMappingLookupDao {
	public List<SSLProductAttrMappingModel> getProductAttrMappingList();

	List<SSLProductAttrMappingModel> getProductAttrMappingListByType(Map<String, ? extends Object> params);

}
