/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * Product accessing DAO interface used in product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public interface ProductUploadAutomationDao {
    /**
     * method to retrieve products with specified status
     * 
     * @param catalogVersion
     * @param productStatus
     * @return list of product from staged catalog with provided status
     */
    List<ApparelProductModel> getProductsWithStatus(CatalogVersionModel catalogVersion, ArticleApprovalStatus productStatus);
    
    
    /**
     * method to retrieve style variant products with specified size variant approval status
     * 
     * @param catalogVersion
     * @param approvalStatus
     * @return list of Style Variant product from staged catalog with provided status
     */
    List<ApparelStyleVariantProductModel> getStyleVariantsWithSizeVariantStatus(CatalogVersionModel catalogVersion, ArticleApprovalStatus approvalStatus);

}
