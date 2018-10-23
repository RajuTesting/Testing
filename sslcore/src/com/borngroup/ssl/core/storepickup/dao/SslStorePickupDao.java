/**
 *
 */
package com.borngroup.ssl.core.storepickup.dao;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.borngroup.ssl.core.enums.SterlingStatusType;
import com.borngroup.ssl.core.model.PickFromStoreBufferModel;
import com.borngroup.ssl.core.model.PincodePickupStoreMappingModel;
import com.borngroup.ssl.core.model.SterlingHybrisStatusMappingModel;

/**
 * @author Nagarro-Dev
 *
 */
public interface SslStorePickupDao {

    /**
     * Gets the list of stores mapped to the pincode with their storeCode and address
     *
     * @param pincode
     * @return List of stores mapped to the pincode
     */
    List<PincodePickupStoreMappingModel> getPickupStoresForPincode(String pincode);

    /**
     * find the list of Stocklevel's mapped to the pincode and skuCode
     *
     * @param pincode
     * @param skuCode
     * @return List of Stocklevel's mapped to the pickupStores
     */
    public List<StockLevelModel> findStoreStockLevelsForPincodeAndSkuCode(final String pincode, String skuCode, String warehouseCode);

    /**
     * find the buffer stock mapped to the departmentCode ,subDepartmentCode,classCode,subClassCode and seasonCode find the list of
     * Stocklevel's mapped to the pincode and skuCodes
     *
     * @param pincode
     * @param skuCodes
     * @return List of Stocklevel's mapped to the pickupStores
     */
    public List<StockLevelModel> findStoreStockLevelsForPincodeAndSkuCodes(final String pincode, final List<String> skuCodes,
            final String warehouseCode);

    /**
     * find the buffer stock mapped to the departmentCode ,subDepartmentCode,classCode,subClassCode and seasonCode
     *
     * @param departmentCode
     * @param subDepartmentCode
     * @param classCode
     * @param subClassCode
     * @param seasonCode
     * @return buffer stock
     */
    public PickFromStoreBufferModel findStoreBufferStock(String departmentCode, String subDepartmentCode, String classCode,
            String subClassCode, String seasonCode);

    /**
     * find the list of Stocklevel's mapped to the pincode warehouseCode and skuCode
     *
     * @param skuCode
     * @param warehouseCode
     * @return list of Stocklevel's
     */
    List<StockLevelModel> findStoreStockLevelsForSkuCode(String skuCode, String warehouseCode);

    /**
     * find the list of Stocklevel's mapped to the pincode warehouseCode and skuCodes
     *
     * @param skuCode
     * @param warehouseCode
     * @return list of Stocklevel's
     */
    List<StockLevelModel> findStoreStockLevelsForSkuCodes(List<String> skuCodes, String warehouseCode);

    /**
     * find the list of stores mapped to the pincode and storeCode
     *
     * @param storeCode
     * @param pincode
     * @return List of stores mapped to the pincode and storeCode
     */
    public List<PincodePickupStoreMappingModel> findPickupStoresForStoreCodeAndPincode(final String storeCode, String pincode);

    /**
     *
     * @param productCode
     * @return product Eligibility for pickup from store
     */
    String getProductEligibilityForPickup(final String productCode);

	/**
	 * find the status description for the given status code and status type
	 *
	 * @param code
	 * @param statusType
	 * @return status description
	 */
	List<SterlingHybrisStatusMappingModel> findStatusForCode(String code, SterlingStatusType statusType);

	/**
	 * @param pincode
	 * @param eligibleProducts
	 * @return
	 */
	Map<String, String> getWareHouseProductsMap(String pincode, Set<String> eligibleProducts);


	/**
	 * @param storeCode
	 * @param productsList
	 * @param pincode
	 * @return
	 */
	Set<String> getAvilableProductForStore(String storeCode, Set<String> productsList, String pincode);
}
