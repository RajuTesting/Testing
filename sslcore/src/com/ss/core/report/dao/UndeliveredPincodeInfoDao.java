/**
 *
 */
package com.ss.core.report.dao;

import java.util.List;

import com.borngroup.ssl.core.model.SSLUndeliverPincodeInfoModel;

public interface UndeliveredPincodeInfoDao {

	List<SSLUndeliverPincodeInfoModel> getAllUndeliveredPincodeInfos();

	SSLUndeliverPincodeInfoModel getUndeliveredPincodeInfoByPincode(String pincode);
}
