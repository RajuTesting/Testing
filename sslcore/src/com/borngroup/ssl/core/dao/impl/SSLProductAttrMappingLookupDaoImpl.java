/**
 * Modification History:
 * <p>
 * Version						Author					Task_ID					Description
 * ================================================================================================
 * 0.1							Midhun Bose 			SSL-19 					Base Version
 */
package com.borngroup.ssl.core.dao.impl;

import com.borngroup.ssl.core.dao.SSLProductAttrMappingLookupDao;
import com.borngroup.ssl.core.model.SSLProductAttrMappingModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;
import java.util.Map;

/**
 * @author midhun.bose
 *
 */
public class SSLProductAttrMappingLookupDaoImpl extends DefaultGenericDao<SSLProductAttrMappingModel> implements SSLProductAttrMappingLookupDao {

	/**
	 *
	 */
	public SSLProductAttrMappingLookupDaoImpl() {
		super("SSLProductAttrMapping");
	}

	@Override
	public List<SSLProductAttrMappingModel> getProductAttrMappingList() {
		return find();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.dao.SSLProductAttrMappingLookupDao#
	 * getProductAttrMappingListByType(java.util.Map)
	 */
	@Override
	public List<SSLProductAttrMappingModel> getProductAttrMappingListByType(final Map<String, ? extends Object> params) {
		return find(params);
	}

}
