/**
 *
 */
package com.borngroup.ssl.core.storepickup.service;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;
import java.util.Map;

import com.borngroup.ssl.core.enums.SterlingStatusType;
import com.borngroup.ssl.core.model.PincodePickupStoreMappingModel;

/**
 * @author Nagarro-Dev
 *
 */
public interface SslStorePickupService {

    /**
     * Gets the list of stores mapped to the pincode with their storeCode and address
     *
     * @param pincode
     * @return List of stores mapped to the pincode
     */
    public List<PincodePickupStoreMappingModel> getPickupStoresForPincode(final String pincode);

    /**
     * Gets the list of stock levels mapped to the pincode ,skuCode And warehouseCode
     *
     * @param pincode
     * @return List of StockLevels
     */
    public List<StockLevelModel> getStoreStockLevelsForPincodeAndSkus(final String pincode, String skuCode, String warehouseCode);

    /**
     * get the buffer stock mapped to the departmentCode ,subDepartmentCode,classCode,subClassCode and seasonCode Gets the SKU Inventory Map
     * of stock levels mapped to the pincode ,skuCode And warehouseCode
     *
     * @param pincode
     * @param skuCodes
     * @param warehouseCode
     * @return SKU Mapped Stocklevel's
     */
    public Map<String, List<StockLevelModel>> getStoreStockLevelsForPincodeAndSkus(final String pincode, List<String> skuCodes,
            String warehouseCode);

    /**
     * get the buffer stock mapped to the departmentCode ,subDepartmentCode,classCode,subClassCode and seasonCode
     *
     * @param departmentCode
     * @param subDepartmentCode
     * @param classCode
     * @param subClassCode
     * @param seasonCode
     * @return buffer stock
     */
    public long getStoreBufferStock(String departmentCode, String subDepartmentCode, String classCode, String subClassCode,
            String seasonCode);

    /**
     * Gets the list of stock levels mapped to the skuCode And warehouseCode
     *
     * @param skuCode
     * @param warehouseCode
     * @return List of StockLevels
     */
    public List<StockLevelModel> getStoreStockLevelsForSkus(String skuCode, String warehouseCode);

    /**
     * Gets the SKU Inventory Map mapped to the skuCode And warehouseCode
     *
     * @param skuCodes
     * @param warehouseCode
     * @return SKU Mapped Stocklevel's
     */
    public Map<String, List<StockLevelModel>> getStoreStockLevelsForSkus(List<String> skuCodes, String warehouseCode);

    /**
     * Get ODC code mapped to the storeCode And pincode
     *
     * @param storeCode
     * @param pincode
     * @return ODC code
     */
    public String getOdcCodeForStoreCodeAndPincode(final String storeCode, String pincode);

    /**
     * Get availability for pickup from store
     *
     * @param productCode
     * @return product's Eligibility for pickup from store
     */
    String getProductEligibilityForPickup(String productCode);

    /**
     * this method is used to know,Store is eligible for pickup or not
     *
     * @param warehouseCode
     * @return boolean
     */
    public boolean isWarehouseEligibleForPickup(String warehouseCode);

    /**
     * this method is used to get Hybris Status corresponding Sterling status code and Status type
     *
     * @param code
     * @param statusType
     * @return Hybris Status Description
     */
    String getStatusForCode(String code, SterlingStatusType statusType);

    List<DeliveryModeModel> getPickupDeliveryModes(CartModel cart);

    Map<String, String> getHybsirSterlingOrderStatusMappings();

}
