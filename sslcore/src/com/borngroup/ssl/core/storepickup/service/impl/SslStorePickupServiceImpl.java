/**
 *
 */
package com.borngroup.ssl.core.storepickup.service.impl;

import de.hybris.platform.commerceservices.delivery.dao.PickupDeliveryModeDao;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.enums.SterlingStatusType;
import com.borngroup.ssl.core.model.PickFromStoreBufferModel;
import com.borngroup.ssl.core.model.PincodePickupStoreMappingModel;
import com.borngroup.ssl.core.model.SterlingHybrisStatusMappingModel;
import com.borngroup.ssl.core.storepickup.dao.SslStorePickupDao;
import com.borngroup.ssl.core.storepickup.service.SslStorePickupService;

/**
 * @author Nagarro-Dev
 *
 */
public class SslStorePickupServiceImpl implements SslStorePickupService {

    @Resource(name = "sslStorePickupDao")
    private SslStorePickupDao sslStorePickupDao;

    @Resource(name = "pickupDeliveryModeDao")
    private PickupDeliveryModeDao pickupDeliveryModeDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.service.SslStorePickupService#getStoresForPincode(java.lang.String)
     */
    @Override
    public List<PincodePickupStoreMappingModel> getPickupStoresForPincode(final String pincode) {

        return (StringUtils.isEmpty(pincode)) ? ListUtils.EMPTY_LIST : sslStorePickupDao.getPickupStoresForPincode(pincode);
    }

    @Override
    public List<StockLevelModel> getStoreStockLevelsForPincodeAndSkus(final String pincode, final String skuCode, final String warehouseCode) {
        return sslStorePickupDao.findStoreStockLevelsForPincodeAndSkuCode(pincode, skuCode, warehouseCode);
    }

    @Override
    public List<StockLevelModel> getStoreStockLevelsForSkus(final String skuCode, final String warehouseCode) {
        return sslStorePickupDao.findStoreStockLevelsForSkuCode(skuCode, warehouseCode);
    }

    public Map<String, List<StockLevelModel>> getStoreStockLevelsForPincodeAndSkus(final String pincode, final List<String> skuCodes,
            final String warehouseCode) {
        final List<StockLevelModel> stockLevles = new ArrayList<>(new LinkedHashSet<>(
                sslStorePickupDao.findStoreStockLevelsForPincodeAndSkuCodes(pincode, skuCodes, warehouseCode)));

        return this.getSkuInventoryMap(stockLevles, skuCodes);

    }

    @Override
    public long getStoreBufferStock(final String departmentCode, final String subDepartmentCode, final String classCode,
            final String subClassCode, final String seasonCode) {

        final PickFromStoreBufferModel storeBuffer = sslStorePickupDao.findStoreBufferStock(departmentCode, subDepartmentCode, classCode,
                subClassCode, seasonCode);
        if (storeBuffer != null) {
            return storeBuffer.getBufferStock().longValue();
        } else {
            return 0;
        }
    }

    @Override
    public Map<String, List<StockLevelModel>> getStoreStockLevelsForSkus(final List<String> skuCodes, final String warehouseCode) {

        final List<StockLevelModel> stockLevles = new ArrayList<>(new LinkedHashSet<>(
                sslStorePickupDao.findStoreStockLevelsForPincodeAndSkuCodes(null, skuCodes, warehouseCode)));
        return this.getSkuInventoryMap(stockLevles, skuCodes);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.service.SslStorePickupService# getOdcCodeForStoreCodeAndPincode(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String getOdcCodeForStoreCodeAndPincode(final String storeCode, final String pincode) {
        final List<PincodePickupStoreMappingModel> pincodePickupStoreMapping = sslStorePickupDao.findPickupStoresForStoreCodeAndPincode(
                storeCode, pincode);
        if (CollectionUtils.isNotEmpty(pincodePickupStoreMapping)) {
            return pincodePickupStoreMapping.get(0).getOdc();
        }
        return StringUtils.EMPTY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.service.SslStorePickupService# isWarehouseEligibleForPickup(java.lang.String)
     */
    @Override
    public boolean isWarehouseEligibleForPickup(final String warehouseCode) {

        return CollectionUtils.isNotEmpty(sslStorePickupDao.findPickupStoresForStoreCodeAndPincode(warehouseCode, null));
    }

    /**
     * @description this method returns the SKU mapped Stocklevel's
     * @param stockLevels
     * @return SKU Mapped Stocklevel's
     */
    private Map<String, List<StockLevelModel>> getSkuInventoryMap(final List<StockLevelModel> stockLevels, final List<String> skuCodes) {
        final Map<String, List<StockLevelModel>> skuInventoryMap = new HashMap<>();

        if (CollectionUtils.isNotEmpty(skuCodes) && skuCodes.size() > 1) {
            for (final String sku : skuCodes) {
                skuInventoryMap.put(sku, new ArrayList());
            }
            for (final StockLevelModel stock : stockLevels) {
                skuInventoryMap.get(stock.getProductCode()).add(stock);
            }
        } else {
            skuInventoryMap.put(skuCodes.get(0), stockLevels);
        }
        return skuInventoryMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.service.SslStorePickupService#getProductEligibilityForPickup(java.lang.String)
     */
    @Override
    public String getProductEligibilityForPickup(final String productCode) {
        return sslStorePickupDao.getProductEligibilityForPickup(productCode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.borngroup.ssl.core.storepickup.service.SslStorePickupService# getStatusForCode(java.lang.String,
     * com.borngroup.ssl.core.enums.SterlingStatusType)
     */
    @Override
    public String getStatusForCode(final String code, final SterlingStatusType statusType) {

        final List<SterlingHybrisStatusMappingModel> statusMapping = sslStorePickupDao.findStatusForCode(code, statusType);
        if (CollectionUtils.isNotEmpty(statusMapping) && null != statusMapping.get(0).getHybrisStatus()) {
            return statusMapping.get(0).getHybrisStatus().getCode();
        } else {
            return StringUtils.EMPTY;
        }

    }

    private Map<String, String> getOrderStatusMapping(final SterlingStatusType statusType) {

        final List<SterlingHybrisStatusMappingModel> statusMapping = sslStorePickupDao.findStatusForCode(null, statusType);
        if (CollectionUtils.isNotEmpty(statusMapping) && null != statusMapping.get(0).getHybrisStatus()) {
            return statusMapping.stream().collect(
                    Collectors.toMap(mapping -> mapping.getStatusCode(), mapping -> mapping.getHybrisStatus().getCode()));
        } else {
            return Collections.emptyMap();
        }

    }

    /**
     * @return the sslStorePickupDao
     */
    public SslStorePickupDao getSslStorePickupDao() {
        return sslStorePickupDao;
    }

    /**
     * @param sslStorePickupDao
     *        the sslStorePickupDao to set
     */
    public void setSslStorePickupDao(final SslStorePickupDao sslStorePickupDao) {
        this.sslStorePickupDao = sslStorePickupDao;
    }

    @Override
    public List<DeliveryModeModel> getPickupDeliveryModes(final CartModel cart) {
        return pickupDeliveryModeDao.findPickupDeliveryModesForAbstractOrder(cart);
    }

    @Override
    public Map<String, String> getHybsirSterlingOrderStatusMappings() {
        return getOrderStatusMapping(SterlingStatusType.ORDER);
    }

}
