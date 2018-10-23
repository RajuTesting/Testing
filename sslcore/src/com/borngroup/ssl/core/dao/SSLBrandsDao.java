/**
 * 
 */
package com.borngroup.ssl.core.dao;

import java.util.Set;

import com.borngroup.ssl.core.model.BrandMappingModel;

/**
 * @author midhun.bose
 * 
 */
public interface SSLBrandsDao {
    /**
     * Gets all the brand mappings models.
     * 
     * @return Set of all the brand mappings as model.
     */
    public Set<BrandMappingModel> getAllBrands();

    /**
     * Gets the first brand mapping model with the given alternate description.
     * 
     * @param brandDesc
     * @return BrandMappingModel
     */
    public BrandMappingModel getBrandMappingByAltDesc(final String brandDesc);
}
