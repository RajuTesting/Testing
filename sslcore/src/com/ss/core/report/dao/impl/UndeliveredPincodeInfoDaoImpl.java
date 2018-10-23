/**
 *
 */
package com.ss.core.report.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.HashMap;
import java.util.List;

import com.borngroup.ssl.core.model.SSLUndeliverPincodeInfoModel;
import com.ss.core.report.dao.UndeliveredPincodeInfoDao;

/**
 * @author Shivraj
 *
 */
public class UndeliveredPincodeInfoDaoImpl extends DefaultGenericDao<SSLUndeliverPincodeInfoModel>
		implements UndeliveredPincodeInfoDao {

	public UndeliveredPincodeInfoDaoImpl() {
		super("SSLUndeliverPincodeInfo");
		// YTODO Auto-generated constructor stub
	}

	/**
	 * @param typecode
	 */
	public UndeliveredPincodeInfoDaoImpl(final String typecode) {
		super(typecode);
		// YTODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ss.core.report.dao.UndeliveredPincodeInfoDao#
	 * getAllUndeliveredPincodeInfos()
	 */
	@Override
	public List<SSLUndeliverPincodeInfoModel> getAllUndeliveredPincodeInfos() {
		return this.find();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ss.core.report.dao.UndeliveredPincodeInfoDao#
	 * getUndeliveredPincodeInfoByPincode(java.lang.String)
	 */
	@Override
	public SSLUndeliverPincodeInfoModel getUndeliveredPincodeInfoByPincode(final String pincode) {
		final HashMap params = new HashMap();
		params.put("pincode", pincode);
		final List<SSLUndeliverPincodeInfoModel> infoModels = this.find(params);
		if (!infoModels.isEmpty() && infoModels.size() > 0) {
			return infoModels.get(0);
		}
		return null;

	}

}
