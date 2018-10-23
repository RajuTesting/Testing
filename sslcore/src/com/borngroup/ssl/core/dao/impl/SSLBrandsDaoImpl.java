/**
 * 
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLBrandsDao;
import com.borngroup.ssl.core.model.BrandMappingModel;

/**
 * @author midhun.bose
 * 
 */
public class SSLBrandsDaoImpl implements SSLBrandsDao {
    @Resource(name = "flexibleSearchService")
    FlexibleSearchService flexibleSearchService;

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.dao.SSLBrandsDao#getAllBrands()
     */
    @Override
    public Set<BrandMappingModel> getAllBrands() {
        final StringBuilder query = new StringBuilder();
        query.append("SELECT {").append(BrandMappingModel.PK).append("} FROM {").append(BrandMappingModel._TYPECODE).append("}");
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.setResultClassList(Collections.singletonList(BrandMappingModel.class));
        final SearchResult<BrandMappingModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
        if (result != null && result.getResult() != null && 0 != result.getResult().size()) {
            return new HashSet<BrandMappingModel>(result.getResult());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.dao.SSLBrandsDao#getBrandMappingByAltDesc(java.lang.String)
     */
    @Override
    public BrandMappingModel getBrandMappingByAltDesc(final String brandDesc) {
        // This method has created as the business was using alt brand desc attribute to maintain data and same behaviour was to be
        // followed. This is the reason code of the model has not been used.
        final StringBuilder query = new StringBuilder();
        final Map<String, Object> params = new HashMap<String, Object>();
        query.append("SELECT {").append(BrandMappingModel.PK).append("} FROM {").append(BrandMappingModel._TYPECODE)
                .append("} where UPPER({").append(BrandMappingModel.ALTBRANDDESC).append("})=?brandDesc");
        params.put("brandDesc", brandDesc.toUpperCase());
        final SearchResult<BrandMappingModel> result = getFlexibleSearchService().search(query.toString(), params);
        if (result != null && result.getResult() != null && 0 != result.getResult().size()) {
            return result.getResult().iterator().next();
        }

        return null;
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

}
