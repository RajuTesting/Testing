package com.borngroup.ssl.core.data;

import java.util.Date;
import java.util.List;

/**
 * POJO class to store error data during product upload automation cronjobs
 *
 * @author anupamsrivastava
 *
 */
public class ProductUploadValidationDTO {

    private String baseSKU;
    private String productSKU;
    private Date productSKUDateCreated;
    private String baseSKUStatus;
    private String prodSKUStatus;
    private String errorType;
    private List<String> missingFields;

    /**
     * @return the baseSKU
     */
    public String getBaseSKU() {
        return baseSKU;
    }

    /**
     * @param baseSKU the baseSKU to set
     */
    public void setBaseSKU(final String baseSKU) {
        this.baseSKU = baseSKU;
    }

    /**
     * @return the productSKU
     */
    public String getProductSKU() {
        return productSKU;
    }

    /**
     * @param productSKU the productSKU to set
     */
    public void setProductSKU(final String productSKU) {
        this.productSKU = productSKU;
    }

    /**
     * @return the productSKUDateCreated
     */
    public Date getProductSKUDateCreated() {
        return productSKUDateCreated;
    }

    /**
     * @param productSKUDateCreated the productSKUDateCreated to set
     */
    public void setProductSKUDateCreated(final Date productSKUDateCreated) {
        this.productSKUDateCreated = productSKUDateCreated;
    }

    /**
     * @return the baseSKUStatus
     */
    public String getBaseSKUStatus() {
        return baseSKUStatus;
    }

    /**
     * @param baseSKUStatus the baseSKUStatus to set
     */
    public void setBaseSKUStatus(final String baseSKUStatus) {
        this.baseSKUStatus = baseSKUStatus;
    }

    /**
     * @return the prodSKUStatus
     */
    public String getProdSKUStatus() {
        return prodSKUStatus;
    }

    /**
     * @param prodSKUStatus the prodSKUStatus to set
     */
    public void setProdSKUStatus(final String prodSKUStatus) {
        this.prodSKUStatus = prodSKUStatus;
    }

    /**
     * @return the errorType
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * @param errorType the errorType to set
     */
    public void setErrorType(final String errorType) {
        this.errorType = errorType;
    }

    /**
     * @return the missingFields
     */
    public List<String> getMissingFields() {
        return missingFields;
    }

    /**
     * @param missingFields the missingFields to set
     */
    public void setMissingFields(final List<String> missingFields) {
        this.missingFields = missingFields;
    }

}
