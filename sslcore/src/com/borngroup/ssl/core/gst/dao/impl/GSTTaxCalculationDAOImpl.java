package com.borngroup.ssl.core.gst.dao.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.gst.dao.GSTTaxCalculationDAO;
import com.borngroup.ssl.core.model.GstTaxModel;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.borngroup.ssl.core.util.GSTUtil;
import com.borngroup.ssl.fulfilmentprocess.model.ShippingExcludedProdModel;
import com.borngroup.tax.model.ShippingChargesModel;

/**
 * <p>
 * <p>
 * GSTTaxCalculationDAOImpl.java : Service introduced to calculate GST tax. �*�
 * <p>
 * Created By : anupam.srivastava@nagarro.com. �
 *
 * @author�Ssl �
 */
public class GSTTaxCalculationDAOImpl implements GSTTaxCalculationDAO {

    private static final Logger LOG = Logger.getLogger(GSTTaxCalculationDAOImpl.class);

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<GstTaxModel> findTaxByTaxCategoryId(final String taxCategoryId, final Double productPrice) {

        final long currentTime = new Date().getTime();
        final FlexibleSearchQuery query = new FlexibleSearchQueryBuilder().from(GstTaxModel._TYPECODE, "t").select("t", ItemModel.PK)
                .select("t", GstTaxModel.CODE).select("t", GstTaxModel.TAXVALUE).select("t", GstTaxModel.TAXTYPE)
                .whereNotNull("t", GstTaxModel.TAXCODE).whereNotNull("t", GstTaxModel.VALIDFROMDATE)
                .whereEquals("t", GstTaxModel.TAXCATEGORYID, taxCategoryId)
                .whereLessThanEqualsDate("t", GstTaxModel.VALIDFROMDATE, currentTime)
                .whereMoreThanEqualsDate("t", GstTaxModel.VALIDTODATE, currentTime)
                .whereLessThanEquals("t", GstTaxModel.VALIDFROMAMOUNT, productPrice)
                .whereMoreThanEquals("t", GstTaxModel.VALIDTOAMOUNT, productPrice).build();

        final SearchResult<GstTaxModel> taxMappings = flexibleSearchService.search(query);
        return taxMappings.getResult();
    }

    @Override
    public List<ShippingExcludedProdModel> getListofExcludedSku(final List<String> excludedSku) {
        final FlexibleSearchQuery excludedSkuQuery = new FlexibleSearchQuery(GSTUtil.EXCLUDED_SKU_QUERY);
        excludedSkuQuery.addQueryParameter("skuLists", excludedSku);
        final SearchResult<ShippingExcludedProdModel> exludedSKU = flexibleSearchService.search(excludedSkuQuery);
        final List<ShippingExcludedProdModel> allocatedQtyList = exludedSKU.getResult();
        return allocatedQtyList;
    }

    @Override
    public ShippingChargesModel getShippingSKUOnPercent(final double taxPercent) {
        ShippingChargesModel shipingModel = null;
        final FlexibleSearchQuery excludedSkuQuery = new FlexibleSearchQuery(GSTUtil.SHIPPING_SKU_QUERY);
        excludedSkuQuery.addQueryParameter("taxPercent", taxPercent);
        final SearchResult<ShippingChargesModel> shippingSku = flexibleSearchService.search(excludedSkuQuery);
        final List<ShippingChargesModel> shippingSkuList = shippingSku.getResult();
        if (CollectionUtils.isNotEmpty(shippingSkuList)) {
            shipingModel = shippingSkuList.get(0);
        }
        return shipingModel;
    }

}
