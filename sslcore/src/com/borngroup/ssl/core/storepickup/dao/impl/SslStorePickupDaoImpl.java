/**
 *
 */
package com.borngroup.ssl.core.storepickup.dao.impl;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.enums.SterlingStatusType;
import com.borngroup.ssl.core.model.PickFromStoreBufferModel;
import com.borngroup.ssl.core.model.PincodePickupStoreMappingModel;
import com.borngroup.ssl.core.model.SterlingHybrisStatusMappingModel;
import com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.google.common.collect.ImmutableList;

/**
 * @author Nagarro-Dev
 *
 */
public class SslStorePickupDaoImpl implements SslStorePickupDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    private static final Logger LOG = Logger.getLogger(SslStorePickupDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao#getStoresForPincode(java.lang.String)
     */
    @Override
    public List<PincodePickupStoreMappingModel> getPickupStoresForPincode(final String pincode) {

        try {

            // Will return the list of only those warehouses mapped to the given pincode
            // which have pick-up from store facility available
            final FlexibleSearchQueryBuilder builder = new FlexibleSearchQueryBuilder()
                    .from(PincodePickupStoreMappingModel._TYPECODE, "ppsm")
                    .join(WarehouseModel._TYPECODE, "wh", PincodePickupStoreMappingModel.PICKUPSTORELOCATION)
                    .select("ppsm", PincodePickupStoreMappingModel.PK).whereEquals("ppsm", PincodePickupStoreMappingModel.PINCODE, pincode)
                    .whereEquals("wh", WarehouseModel.ISSTOREPICKUPAVAILABLE, true)
                    .order("ppsm", PincodePickupStoreMappingModel.PICKUPSTOREPRIORITY, true);

            final FlexibleSearchQuery flexibleSearchQuery = builder.build();
            final List<PincodePickupStoreMappingModel> listOfPickupStores = flexibleSearchService.<PincodePickupStoreMappingModel> search(
                    flexibleSearchQuery).getResult();
            if (CollectionUtils.isNotEmpty(listOfPickupStores)) {
                return listOfPickupStores;
            }

        } catch (final Exception ex) {
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
        }
        return ListUtils.EMPTY_LIST;

    }

    @Override
    public List<StockLevelModel> findStoreStockLevelsForPincodeAndSkuCode(final String pincode, final String skuCode,
            final String warehouseCode) {

        try {
            // Will return the list of StockLevels mapped to the Pickup stores
            final StringBuilder query = new StringBuilder("SELECT  {sl:").append("pk").append("}");
            query.append(" FROM { ").append("StockLevel").append(" AS sl");
            query.append(" JOIN ").append("PincodePickupStoreMapping").append(" AS ppsm");
            query.append(" ON {sl:").append("warehouse").append("}={wh:").append("pickupStoreLocation").append('}');
            query.append(" JOIN ").append("Warehouse").append(" AS wh");
            query.append(" ON {ppsm:").append("pickupStoreLocation").append("}={wh:").append("PK").append('}');
            query.append(" } WHERE {ppsm:").append("pinCode").append("}=?pincode");
            query.append(" AND {wh:").append("isStorePickupAvailable").append("}=?isStorePickupAvailable");
            query.append(" AND {wh:").append("code").append("}=?warehouseCode");
            query.append(" AND {sl:").append("productCode").append("}=?skuCode");
            final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
            fpQuery.addQueryParameter("pincode", pincode);
            fpQuery.addQueryParameter("skuCode", skuCode);
            fpQuery.addQueryParameter("isStorePickupAvailable", 1);
            fpQuery.addQueryParameter("warehouseCode", warehouseCode);
            final SearchResult<StockLevelModel> searchResult = flexibleSearchService.search(fpQuery);
            return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult() : Collections.EMPTY_LIST;

        } catch (final Exception ex) {
            LOG.error("Exception occured while fectching the stocklevels from the pickupstores: Reason" + ex.getMessage()
                    + " Error cause: " + ex.getCause());
            return Collections.EMPTY_LIST;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao# findStoreStockLevelsForSkuCode(java.lang.String, java.lang.String)
     */
    @Override
    public List<StockLevelModel> findStoreStockLevelsForSkuCode(final String skuCode, final String warehouseCode) {
        final StringBuilder query = new StringBuilder("Select {pk}");
        query.append("FROM {").append("StockLevel").append("}");
        query.append("WHERE {").append("warehouse").append("} = ?warehouseCode");
        query.append("AND {").append("productCode").append("} = ?skuCode");
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
        fpQuery.setResultClassList(ImmutableList.of(Long.class));
        fpQuery.addQueryParameter("skuCode", skuCode);
        fpQuery.addQueryParameter("warehouseCode", warehouseCode);

        final SearchResult<StockLevelModel> searchResult = flexibleSearchService.search(fpQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult() : Collections.EMPTY_LIST;

    }

    @Override
    public List<StockLevelModel> findStoreStockLevelsForPincodeAndSkuCodes(final String pincode, final List<String> skuCodes,
            final String warehouseCode) {

        try {
            // Will return the list of StockLevels mapped to the Pickup stores
            final StringBuilder query = new StringBuilder("SELECT {sl:").append("pk").append("}");
            query.append(" FROM { ").append("StockLevel").append(" AS sl");
            query.append(" JOIN ").append("PincodePickupStoreMapping").append(" AS ppsm");
            query.append(" ON {sl:").append("warehouse").append("}={ppsm:").append("pickupStoreLocation").append('}');
            query.append(" JOIN ").append("Warehouse").append(" AS wh");
            query.append(" ON {ppsm:").append("pickupStoreLocation").append("}={wh:").append("PK").append('}');
            query.append("} WHERE {sl:").append("productCode").append("} IN (?skuCodes)");
            if (StringUtils.isNotBlank(pincode)) {
                query.append(" AND {ppsm:").append("pinCode").append("}=?pincode");
            }
            query.append(" AND {wh:").append("isStorePickupAvailable").append("}=?isStorePickupAvailable");
            if (StringUtils.isNotBlank(warehouseCode)) {
                query.append(" AND {wh:").append("code").append("}=?warehouseCode");
            }
            query.append(" AND {sl:").append("productCode").append("} IN (?skuCodes)");
            query.append(" ORDER BY {ppsm:").append("pickupStorePriority").append("} ASC ");
            final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
            if (StringUtils.isNotBlank(pincode)) {
                fpQuery.addQueryParameter("pincode", pincode);
            }
            fpQuery.addQueryParameter("skuCodes", skuCodes);
            fpQuery.addQueryParameter("isStorePickupAvailable", 1);
            if (StringUtils.isNotBlank(warehouseCode)) {
                fpQuery.addQueryParameter("warehouseCode", warehouseCode);
            }

            final SearchResult<StockLevelModel> searchResult = flexibleSearchService.search(fpQuery);
            return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult() : Collections.EMPTY_LIST;

        } catch (final Exception ex) {
            LOG.error("Exception occured while fectching the stocklevels from the pickupstores: Reason" + ex.getMessage()
                    + " Error cause: " + ex.getCause());
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<PincodePickupStoreMappingModel> findPickupStoresForStoreCodeAndPincode(final String storeCode, final String pincode) {

        try {

            // Will return the list of only those warehouses mapped to the given
            // storeCode
            final StringBuilder query = new StringBuilder("SELECT  {ppsm:").append("pk").append("}");
            query.append(" FROM { ").append("PincodePickupStoreMapping").append(" AS ppsm");
            query.append(" JOIN ").append("Warehouse").append(" AS wh");
            query.append(" ON {ppsm:").append("pickupStoreLocation").append("}={wh:").append("PK").append('}');
            query.append(" } WHERE {wh:").append("code").append("}=?warehouseCode");
            query.append(" AND {wh:").append("isStorePickupAvailable").append("}=?isStorePickupAvailable");
            if (StringUtils.isNotBlank(pincode)) {
                query.append(" AND {ppsm:").append("pinCode").append("}=?pincode");
            }
            final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
            fpQuery.addQueryParameter("warehouseCode", storeCode);
            fpQuery.addQueryParameter("isStorePickupAvailable", 1);
            if (StringUtils.isNotBlank(pincode)) {
                fpQuery.addQueryParameter("pinCode", pincode);
            }
            final List<PincodePickupStoreMappingModel> listOfPickupStores = flexibleSearchService.<PincodePickupStoreMappingModel> search(
                    fpQuery).getResult();
            if (CollectionUtils.isNotEmpty(listOfPickupStores)) {
                return listOfPickupStores;
            }

        } catch (final Exception ex) {
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
        }
        return ListUtils.EMPTY_LIST;

    }

    @Override
    public PickFromStoreBufferModel findStoreBufferStock(final String departmentCode, final String subDepartmentCode,
            final String classCode, final String subClassCode, final String seasonCode) {

        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("departmentCode", departmentCode);
        queryParams.put("subDepartmentCode", subDepartmentCode);
        final StringBuilder query = new StringBuilder("Select {pk}");
        query.append(" FROM {").append("PickFromStoreBuffer").append("}");
        query.append(" WHERE {").append("departmentCode").append("} = ?departmentCode");
        query.append(" AND (({").append("subDepartmentCode").append("} = ?subDepartmentCode");
        query.append(" OR {").append("subDepartmentCode").append("} IS NULL)");
        if (StringUtils.isNotBlank(classCode)) {
            query.append(" AND ({").append("classCode").append("} = ?classCode");
            query.append(" OR {").append("classCode").append("} IS NULL)");
            queryParams.put("classCode", classCode);
        }
        if (StringUtils.isNotBlank(subClassCode)) {
            query.append(" AND ({").append("subClassCode").append("} = ?subClassCode");
            query.append(" OR {").append("subClassCode").append("} IS NULL)");
            queryParams.put("subClassCode", subClassCode);
        }
        if (StringUtils.isNotBlank(seasonCode)) {
            query.append(" AND ({").append("seasonCode").append("} = ?seasonCode");
            query.append(" OR {").append("seasonCode").append("} IS NULL)");
            queryParams.put("seasonCode", seasonCode);
        }
        query.append(") ORDER BY {").append("subDepartmentCode").append("} DESC NULLS LAST");
        if (StringUtils.isNotBlank(classCode)) {
            query.append(", {").append("classCode").append("} DESC NULLS LAST");
        }
        if (StringUtils.isNotBlank(subClassCode)) {
            query.append(", {").append("subClassCode").append("} DESC NULLS LAST");
        }
        if (StringUtils.isNotBlank(seasonCode)) {
            query.append(", {").append("seasonCode").append("} DESC NULLS LAST");
        }
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
        fpQuery.addQueryParameters(queryParams);
        final SearchResult<PickFromStoreBufferModel> searchResult = flexibleSearchService.search(fpQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult().get(0) : null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao# findStoreStockLevelsForSkuCode(java.lang.String, java.lang.String)
     */
    @Override
    public List<StockLevelModel> findStoreStockLevelsForSkuCodes(final List<String> skuCodes, final String warehouseCode) {
        final StringBuilder query = new StringBuilder("SELECT  {sl:").append("pk").append("}");
        query.append(" FROM { ").append("StockLevel").append(" AS sl");
        query.append(" JOIN ").append("warehouse").append(" AS wh");
        query.append(" ON {sl:").append("warehouse").append("}={wh:").append("PK").append('}');
        query.append(" } WHERE {wh:").append("code").append("}=?warehouseCode");
        query.append(" AND {sl:").append("productCode").append("} IN (?skuCodes)");
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
        fpQuery.setResultClassList(ImmutableList.of(StockLevelModel.class));
        fpQuery.addQueryParameter("skuCodes", skuCodes);
        fpQuery.addQueryParameter("warehouseCode", warehouseCode);

        final SearchResult<StockLevelModel> searchResult = flexibleSearchService.search(fpQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult() : Collections.EMPTY_LIST;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao#getProductEligibilityForPickup(java.lang.String)
     */
    @Override
    public String getProductEligibilityForPickup(final String productCode) {

        // Will return the eligibility of the product for pickup from store
        final StringBuilder query = new StringBuilder("SELECT {p:pk").append("}");
        query.append(" FROM { ").append(ProductModel._TYPECODE).append(" AS p ");
        query.append("JOIN ").append(CatalogVersionModel._TYPECODE).append(" AS cv ");
        query.append("ON {p:catalogVersion}").append("=").append("{cv:pk} ");
        query.append("JOIN ").append(CatalogModel._TYPECODE).append(" AS c ");
        query.append("ON {cv:catalog}").append("=").append("{c:pk}");
        query.append("} WHERE {cv:version} =?catalogVersion").append(" AND ");
        query.append("{c:id} =?catalog").append(" AND ");
        query.append("{p:code} =?productCode");

        final FlexibleSearchQuery fsquery = new FlexibleSearchQuery(query.toString());
        fsquery.setResultClassList(ImmutableList.of(StockLevelModel.class));
        fsquery.addQueryParameter("productCode", productCode);
        fsquery.addQueryParameter("catalogVersion", SslCoreConstants.ONLINE_CATALOG_VERSION);
        fsquery.addQueryParameter("catalog", SslCoreConstants.CATALOG_NAME);

        final SearchResult<ProductModel> searchResult = flexibleSearchService.search(fsquery);
        if (CollectionUtils.isNotEmpty(searchResult.getResult()) && searchResult.getResult().get(0).getIsAvailable() != null) {
            return searchResult.getResult().get(0).getIsAvailable().toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao#getStatusForCode (java.lang.String,
     * com.borngroup.ssl.core.enums.SterlingStatusType)
     */
    @Override
    public List<SterlingHybrisStatusMappingModel> findStatusForCode(final String statusCode, final SterlingStatusType statusType) {
        final boolean isValidStatusCode = StringUtils.isNotEmpty(statusCode);
        final StringBuilder query = new StringBuilder("select {shs:pk}");
        query.append(" FROM {").append("SterlingHybrisStatusMapping as shs").append("} ,");
        query.append(" {").append("SterlingStatusType as st").append("}");
        query.append(" WHERE {").append("shs:statusType").append("} = {st:pk}");
        query.append(" AND {").append("st:code").append("} = ?statusType");
        if (isValidStatusCode) {
            query.append(" AND {").append("shs:statusCode").append("} = ?statusCode");
        }
        final FlexibleSearchQuery fpQuery = new FlexibleSearchQuery(query.toString());
        if (isValidStatusCode) {
            fpQuery.addQueryParameter("statusCode", statusCode);
        }
        fpQuery.addQueryParameter("statusType", statusType.getCode());

        final SearchResult<SterlingHybrisStatusMappingModel> searchResult = flexibleSearchService.search(fpQuery);
        return CollectionUtils.isNotEmpty(searchResult.getResult()) ? searchResult.getResult() : Collections.EMPTY_LIST;

    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService
     *        the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    /**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String,String> getWareHouseProductsMap(String pincode, Set<String> eligibleProducts) {
		 Map<String,String> wareHouseProducts=new HashMap<>();
		 StringBuilder builder=new StringBuilder();
		 builder.append("SELECT whc,count(*) FROM ({{ ");
		 builder.append(" SELECT {wh.code} as whc,case when {bs.bufferStock} is null then ({sl.available}) else ({sl.available}-{bs.bufferStock}) end as bst");
		 builder.append(" FROM {StockLevel AS sl JOIN PincodePickupStoreMapping AS ppsm ON {sl:warehouse}={ppsm:pickupStoreLocation}");
		 builder.append(" JOIN Warehouse AS wh ON {ppsm:pickupStoreLocation}={wh:PK} JOIN VariantProduct AS p ON {sl.productCode}={p.code}");
		 builder.append(" join VariantProduct as sp on {p.baseproduct}={sp.pk} join  Product as bp on {sp.baseproduct}={bp.pk}");
		 builder.append(" left join PickFromStoreBuffer as bs on {bs.departmentCode}={bp.departmentCode} and {bs.subDepartmentCode}={bp.subDepartmentCode} and {bs.classCode}={bp.classCode}");
         builder.append(" and {bs.subClassCode}={bp.subClassCode} and {bs.seasonCode}={bp.seasonCode}} ");
		 builder.append(" WHERE {sl:productCode} IN (?skuCodes) ");
		 if (StringUtils.isNotBlank(pincode)) {
		 builder.append(" AND {ppsm:pinCode}=?pincode ");
		 }
		 builder.append(" AND {wh:isStorePickupAvailable}='1' ");
		 builder.append("}}) Where  bst>0 group by whc ");
		 FlexibleSearchQuery flexibleSearchQuery=new FlexibleSearchQuery(builder.toString());
		 flexibleSearchQuery.addQueryParameter("skuCodes", eligibleProducts);
		 if (StringUtils.isNotBlank(pincode)) {
		 flexibleSearchQuery.addQueryParameter("pincode", pincode);
		 }
		 flexibleSearchQuery.setResultClassList(Arrays.asList(String.class, String.class));
		 final SearchResult<List<String>> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		 final List<List<String>> list = searchResult.getResult();
		 for (List<String> pair : list) {
			wareHouseProducts.put(pair.get(0), pair.get(1));
		}
		return wareHouseProducts;
	}

	/**
     * {@inheritDoc}
	 */
	@Override
	public Set<String> getAvilableProductForStore(String storeCode, Set<String> productsList,String pincode) {
		if(CollectionUtils.isEmpty(productsList)){
			 return new HashSet<>();
		}
		 StringBuilder builder=new StringBuilder();
		 builder.append("SELECT distinct(productCode) FROM ({{ ");
		 builder.append(" SELECT {p.code} as productCode,case when {bs.bufferStock} is null then ({sl.available}) else ({sl.available}-{bs.bufferStock}) end as stock");
		 builder.append(" FROM {StockLevel AS sl JOIN PincodePickupStoreMapping AS ppsm ON {sl:warehouse}={ppsm:pickupStoreLocation}");
		 builder.append(" JOIN Warehouse AS wh ON {ppsm:pickupStoreLocation}={wh:PK} JOIN VariantProduct AS p ON {sl.productCode}={p.code}");
		 builder.append(" join VariantProduct as sp on {p.baseproduct}={sp.pk} join  Product as bp on {sp.baseproduct}={bp.pk}");
		 builder.append(" left join PickFromStoreBuffer as bs on {bs.departmentCode}={bp.departmentCode} and {bs.subDepartmentCode}={bp.subDepartmentCode} and {bs.classCode}={bp.classCode}");
         builder.append(" and {bs.subClassCode}={bp.subClassCode} and {bs.seasonCode}={bp.seasonCode}} ");
		 builder.append(" WHERE {sl:productCode} IN (?skuCodes) ");
		 if (StringUtils.isNotBlank(pincode)) {
		 builder.append(" AND {ppsm:pinCode}=?pincode ");
		 }
		 if (StringUtils.isNotBlank(storeCode)) {
			 builder.append(" AND {wh:code} =?storeCode ");
	     }
		 builder.append(" AND {wh:isStorePickupAvailable}='1' AND {bp.isAvailable}='1' ");
		 builder.append("}}) Where  stock>0 ");
		 FlexibleSearchQuery flexibleSearchQuery=new FlexibleSearchQuery(builder.toString());
		 flexibleSearchQuery.addQueryParameter("skuCodes", productsList);
		 if (StringUtils.isNotBlank(pincode)) {
		 flexibleSearchQuery.addQueryParameter("pincode", pincode);
		 }
		 if (StringUtils.isNotBlank(storeCode)) {
		 flexibleSearchQuery.addQueryParameter("storeCode", storeCode);
		 }
		 flexibleSearchQuery.setResultClassList(Collections.singletonList(String.class));
		 final SearchResult<String> searchResult=flexibleSearchService.search(flexibleSearchQuery);
		 List<String> products=searchResult.getResult();
		 return new HashSet<>(products);
	}

	
}
