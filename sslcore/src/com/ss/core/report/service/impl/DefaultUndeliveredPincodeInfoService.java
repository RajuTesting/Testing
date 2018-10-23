/**
 *
 */
package com.ss.core.report.service.impl;

import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.SSLUndeliverPincodeInfoModel;
import com.ss.core.report.dao.UndeliveredPincodeInfoDao;
import com.ss.core.report.service.UndeliveredPincodeInfoService;

/**
 * @author Shivraj
 *
 */
public class DefaultUndeliveredPincodeInfoService implements UndeliveredPincodeInfoService {

	private UndeliveredPincodeInfoDao undeliveredPincodeInfoDao;

	private ModelService modelService;

	private static final Logger LOG = Logger.getLogger(DefaultUndeliveredPincodeInfoService.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ss.core.report.service.UndeliveredPincodeInfoService#
	 * getAllUndeliveredPincodeInfos()
	 */
	@Override
	public List<SSLUndeliverPincodeInfoModel> getAllUndeliveredPincodeInfos() {
		return undeliveredPincodeInfoDao.getAllUndeliveredPincodeInfos();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ss.core.report.service.UndeliveredPincodeInfoService#
	 * getUndeliveredPincodeInfoByPincode(java.lang.String)
	 */
	@Override
	public SSLUndeliverPincodeInfoModel getUndeliveredPincodeInfoByPincode(final String pincode) {
		return undeliveredPincodeInfoDao.getUndeliveredPincodeInfoByPincode(pincode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ss.core.report.service.UndeliveredPincodeInfoService#
	 * setUndeliveredPincodeInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void setUndeliveredPincodeInfo(final String pincode, final boolean cod) {

		try {
			final SSLUndeliverPincodeInfoModel pincodeInfoModel = this.getUndeliveredPincodeInfoByPincode(pincode);
			if (pincodeInfoModel != null) {
				if (cod) {
					pincodeInfoModel.setTotalCODFailureCount(pincodeInfoModel.getTotalCODFailureCount() != null
							? Integer.valueOf(pincodeInfoModel.getTotalCODFailureCount().intValue() + 1)
							: Integer.valueOf(1));
				} else {
					pincodeInfoModel.setTotalfailureCount(pincodeInfoModel.getTotalfailureCount() != null
							? Integer.valueOf(pincodeInfoModel.getTotalfailureCount().intValue() + 1)
							: Integer.valueOf(1));
				}
				modelService.save(pincodeInfoModel);
			} else {
				final SSLUndeliverPincodeInfoModel newPincodeInfoModel = modelService
						.create(SSLUndeliverPincodeInfoModel.class);
				newPincodeInfoModel.setPincode(pincode);
				if (cod) {
					newPincodeInfoModel.setTotalCODFailureCount(Integer.valueOf(1));
				} else {
					newPincodeInfoModel.setTotalfailureCount(Integer.valueOf(1));
				}
				 modelService.save(newPincodeInfoModel);
			}
		} catch (final ModelSavingException e) {
			LOG.error("ModelSavingException while setting status " + e.getMessage());
		} catch (final NullPointerException nullPointerException) {
			LOG.error("Exception occure while setting totalRequest" + nullPointerException.getMessage());
		}
	}

	/**
	 * @param undeliveredPincodeInfoDao
	 *            the undeliveredPincodeInfoDao to set
	 */
	public void setUndeliveredPincodeInfoDao(final UndeliveredPincodeInfoDao undeliveredPincodeInfoDao) {
		this.undeliveredPincodeInfoDao = undeliveredPincodeInfoDao;
	}

	/**
	 * @param modelService
	 *            the modelService to set
	 */
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}
}
