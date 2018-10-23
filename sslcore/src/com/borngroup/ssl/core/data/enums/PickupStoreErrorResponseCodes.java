/**
 *
 */
package com.borngroup.ssl.core.data.enums;

/**
 * @author Techouts
 *
 */
public enum PickupStoreErrorResponseCodes {

    /** WAREHOUSE_NOT_FOUND. */
    WAREHOUSE_NOT_FOUND("460", "No warehouse found mapped to the given pincode"),
    EMPTY_SKU_CODES("461", "skuCodes missing in the request"),
    SKU_NOT_AVAILABLE("462", "sku is not availble "),
    SKU_NOT_ELIGIBLE("463", "sku is not eligible for pickup"),
    INVALID_PINCODE("464", "invalid pincode"),
	NO_INVENTORY_FOUND("465", "No inventroy available for the given sku code"),
    EMPTY_PINCODE_WAREHOUSE("466","Need to provide either pincode or warehouseCode parameter"),
	UNMAPPED_PINCODE("467", "No Store Mapped to given pincode"),
	STORE_NOT_ELIGIBLE("468","Store is not eligible for pickup");

    /** Response Code. */
    private String responseCode;

    /** Description. */
    private String description;

    /**
     * @param responseCode
     * @param description
     */
    private PickupStoreErrorResponseCodes(final String responseCode, final String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    public static PickupStoreErrorResponseCodes getReponseEnum(final String responseCode) {
        PickupStoreErrorResponseCodes respEnum = null;
        for (final PickupStoreErrorResponseCodes code : PickupStoreErrorResponseCodes.values()) {
            if (code.getResponseCode().equals(responseCode)) {
                respEnum = code;
                break;
            }
        }
        return respEnum;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
