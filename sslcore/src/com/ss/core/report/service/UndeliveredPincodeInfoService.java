/**
 *
 */
package com.ss.core.report.service;

import java.util.List;

import com.borngroup.ssl.core.model.SSLUndeliverPincodeInfoModel;

public interface UndeliveredPincodeInfoService {

	List<SSLUndeliverPincodeInfoModel> getAllUndeliveredPincodeInfos();

	SSLUndeliverPincodeInfoModel getUndeliveredPincodeInfoByPincode(String pincode);

	void setUndeliveredPincodeInfo(String pincode, boolean cod);
}
