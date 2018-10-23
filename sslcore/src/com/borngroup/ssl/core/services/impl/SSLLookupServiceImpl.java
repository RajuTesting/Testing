/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.borngroup.ssl.core.dao.SSLBrandLookupDao;
import com.borngroup.ssl.core.dao.SSLColourLookupDao;
import com.borngroup.ssl.core.dao.SSLShopBySizeLookupDao;
import com.borngroup.ssl.core.dao.SSLSizeLookupDao;
import com.borngroup.ssl.core.model.BrandMappingModel;
import com.borngroup.ssl.core.model.SizeMappingModel;
import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author midhun.bose
 *
 */
@Service(value = "sslLookupService")
public class SSLLookupServiceImpl implements SSLLookupService {

	@Resource(name = "sslBrandLookupDao")
	SSLBrandLookupDao sslBrandLookupDao;

	@Resource(name = "sslColourLookupDao")
	SSLColourLookupDao sslColourLookupDao;

	@Resource(name = "sslSizeLookupDao")
	SSLSizeLookupDao sslSizeLookupDao;

	@Resource(name = "sslShopBySizeLookupDao")
	SSLShopBySizeLookupDao sslShopBySizeLookupDao;

	private Map<String, Integer> sizePriorityMap = null;

	private static final Logger LOG = Logger.getLogger(SSLLookupServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLBrandCodeLookupService#
	 * getBrandCodeForInputString(java.lang.String)
	 */
	@Override
	public String getBrandCodeForInputString(final String lookupString) {
		return sslBrandLookupDao.getBrandCodeForInputString(lookupString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLBrandLookupService#
	 * getBrandCodeForActualCode(java.lang.String)
	 */
	@Override
	public String getBrandCodeForActualCode(final String lookupString) {
		return sslBrandLookupDao.getBrandCodeForActualCode(lookupString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getColourCodeForInputString(java.lang.String)
	 */
	@Override
	public String getColourCodeForInputString(final String lookupString) {
		return sslColourLookupDao.getColourCodeForInputString(lookupString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getColourCodeForActualCode(java.lang.String)
	 */
	@Override
	public String getColourCodeForActualCode(final String lookupString) {
		return sslColourLookupDao.getColourCodeForActualCode(lookupString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getSizeCodeForInputString(java.lang.String)
	 */
	@Override
	public SizeMappingModel getSizeCodeForInputStrings(final String lookupString, final String deptCode,
			final String brandCode, final String subDeptCode)

	{
		return sslSizeLookupDao.getSizeCodeForInputStrings(lookupString, deptCode, brandCode, subDeptCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.borngroup.ssl.core.services.SSLLookupService#getSizeCodeForActualCode
	 * (java.lang.String)
	 */
	@Override
	public SizeMappingModel getSizeCodeForActualCode(final String lookupString, final String deptCode,
			final String brandCode, final String subDeptCode) {
		return sslSizeLookupDao.getSizeCodeForActualCode(lookupString, deptCode, brandCode, subDeptCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getBrandModelForInputString(java.lang.String)
	 */
	@Override
	public BrandMappingModel getBrandModelForInputString(final String lookupString) {
		return sslBrandLookupDao.getBrandModelForInputString(lookupString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getBrandModelForActualCode(java.lang.String)
	 */
	@Override
	public BrandMappingModel getBrandModelForActualCode(final String lookupString) {
		return sslBrandLookupDao.getBrandModelForActualCode(lookupString);
	}

	/**
	 * @return the sizePriorityMap Size-priority map gets populated here on 1st
	 *         call and the consequent calls just return the populated map.
	 */
	@Override
	public Map<String, Integer> getSizePriorityMap() {
		if (null == sizePriorityMap) {

			sizePriorityMap = new HashMap<>();

			final List<SslShopBySizeMappingModel> priorityList = getShopBySizePriorityList();
			LOG.info("Shop by size priority list size : " + priorityList.size());

			for (final SslShopBySizeMappingModel sizePriorityPair : priorityList) {
				if (!sizePriorityMap.containsKey(sizePriorityPair.getSize())) {
					sizePriorityMap.put(sizePriorityPair.getSize(), sizePriorityPair.getPriority());
				}
			}
		}
		return sizePriorityMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getShopBySizePriorityList() Obtains the size-priority list from Dao.
	 */
	@Override
	public List<SslShopBySizeMappingModel> getShopBySizePriorityList() {

		LOG.info("Shop by size map size : " + sslShopBySizeLookupDao.getShopBySizePriorityList().size());

		return sslShopBySizeLookupDao.getShopBySizePriorityList();
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getColourFamilyForActualCode(java.lang.String)
	 */
	@Override
	public String getColourFamilyForActualCode(final String lookupString) {
		return sslColourLookupDao.getColourFamilyForActualCode(lookupString);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.services.SSLLookupService#
	 * getColourFamilyForAltDesc(java.lang.String)
	 */
	@Override
	public String getColourFamilyForAltDesc(final String lookupString) {
		return sslColourLookupDao.getColourFamilyForAltDesc(lookupString);
	}
}