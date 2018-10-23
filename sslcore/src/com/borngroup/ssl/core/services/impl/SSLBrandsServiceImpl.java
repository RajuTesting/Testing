package com.borngroup.ssl.core.services.impl;

import java.util.Set;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLBrandsDao;
import com.borngroup.ssl.core.model.BrandMappingModel;
import com.borngroup.ssl.core.services.SSLBrandsService;

/**
 * @author midhun.bose
 * 
 */
public class SSLBrandsServiceImpl implements SSLBrandsService {
    @Resource(name = "sslBrandsDao")
    SSLBrandsDao sslBrandsDao;


    @Override
    public Set<BrandMappingModel> getAllBrands() {
        return getSslBrandsDao().getAllBrands();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.services.SSLBrandsService#getBrandMappingByAltDesc(java.lang.String)
     */
    @Override
    public BrandMappingModel getBrandMappingByAltDesc(final String brandDesc) {
        return getSslBrandsDao().getBrandMappingByAltDesc(brandDesc);
    }

    /**
     * @return the sslBrandsDao
     */
    public SSLBrandsDao getSslBrandsDao() {
        return sslBrandsDao;
    }

    /**
     * @param sslBrandsDao
     *        the sslBrandsDao to set
     */
    public void setSslBrandsDao(final SSLBrandsDao sslBrandsDao) {
        this.sslBrandsDao = sslBrandsDao;
    }

}
