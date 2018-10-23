/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.ProductUploadAutomationDao;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * Product accessing DAO implementation used in product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class SslProductUploadAutomationDao implements ProductUploadAutomationDao {

    /** FlexibleSearch Service */
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<ApparelProductModel> getProductsWithStatus(final CatalogVersionModel catalogVersion,
            final ArticleApprovalStatus productStatus) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("productStatus", productStatus.toString().toLowerCase());
        params.put("productCatalogVersion", catalogVersion.getPk());

        final StringBuilder builder = new StringBuilder();
        builder.append("SELECT {").append(ProductModel.PK).append("} FROM {").append(ApparelProductModel._TYPECODE).append(" AS PRO JOIN ");
        builder.append(ArticleApprovalStatus._TYPECODE)
                .append(" AS APPSTTS ON {APPSTTS.pk}={PRO.approvalStatus}} WHERE {APPSTTS.code}= \'");
        builder.append(productStatus.toString().toLowerCase()).append("\' and {PRO.catalogVersion} = " + catalogVersion.getPk());

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(builder.toString());
        flexibleSearchQuery.setResultClassList(Collections.singletonList(ApparelProductModel.class));
        final SearchResult result = this.getFlexibleSearchService().search(flexibleSearchQuery);
        return result.getResult();
    }
    

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.dao.ProductUploadAutomationDao#getStyleVariantsWithSizeVariantStatus(de.hybris.platform.catalog.model.CatalogVersionModel, de.hybris.platform.catalog.enums.ArticleApprovalStatus)
     */
    @Override
    public List<ApparelStyleVariantProductModel> getStyleVariantsWithSizeVariantStatus(
            CatalogVersionModel catalogVersion,
            ArticleApprovalStatus approvalStatus)
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("SELECT DISTINCT {").append(VariantProductModel.BASEPRODUCT).append("} FROM {").append(ApparelSizeVariantProductModel._TYPECODE).append(" AS ASP JOIN ");
        builder.append(ArticleApprovalStatus._TYPECODE)
                .append(" AS APPSTTS ON {APPSTTS.pk}={ASP.approvalStatus}} WHERE {APPSTTS.code}= \'");
        builder.append(approvalStatus.toString().toLowerCase()).append("\' and {ASP.catalogVersion} = " + catalogVersion.getPk());

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(builder.toString());
        flexibleSearchQuery.setResultClassList(Collections.singletonList(ApparelStyleVariantProductModel.class));
        final SearchResult result = this.getFlexibleSearchService().search(flexibleSearchQuery);
        return CollectionUtils.isNotEmpty(result.getResult())?result.getResult():Collections.emptyList();
    }

    /**
     * flexibleSearchService getter method
     *
     * @return flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * flexibleSearchService setter method
     *
     * @param flexibleSearchService
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
