/**
 *
 */
package com.ssl.core.mms.jaxb.api;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class MMSItemDTO implements Serializable {

    public MMSItemDTO() {

    }

    private static final String ORG_CODE = "SSL";

    private static final String UOM = "PIECES";

    public MMSItemDTO(final String quantity, final String warehouseCode, final String productCode, final String productClass,
            final String reason, final String orderNumber) {

        this.quantity = quantity;
        this.warehouseCode = warehouseCode;
        this.productCode = productCode;
        this.productClass = productClass;
        this.reasonCode = reason;
        this.orderNumber = orderNumber;
        this.orgCode = ORG_CODE;
        this.uom = UOM;
        final LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        this.date = now.format(formatter);
        formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        this.timestamp = now.format(formatter).replace(" ", "T");
    }

    @XmlAttribute(name = "ItemID")
    private String productCode;

    @XmlAttribute(name = "Quantity")
    private String quantity;

    @XmlAttribute(name = "ShipNode")
    private String warehouseCode;

    @XmlAttribute(name = "UnitOfMeasure")
    private String uom;

    @XmlAttribute(name = "ProductClass")
    private String productClass;

    @XmlAttribute(name = "ReasonCode")
    private String reasonCode;

    @XmlAttribute(name = "OrderNo")
    private String orderNumber;

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Time")
    private String timestamp;

    @XmlAttribute(name = "OrganizationCode")
    private String orgCode;

    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode
     *        the orgCode to set
     */
    public void setOrgCode(final String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *        the productCode to set
     */
    public void setProductCode(final String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *        the quantity to set
     */
    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * @param warehouseCode
     *        the warehouseCode to set
     */
    public void setWarehouseCode(final String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * @return the uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom
     *        the uom to set
     */
    public void setUom(final String uom) {
        this.uom = uom;
    }

    /**
     * @return the productClass
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * @param productClass
     *        the productClass to set
     */
    public void setProductClass(final String productClass) {
        this.productClass = productClass;
    }

    /**
     * @return the reasonCode
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * @param reasonCode
     *        the reasonCode to set
     */
    public void setReasonCode(final String reasonCode) {
        this.reasonCode = reasonCode;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber
     *        the orderNumber to set
     */
    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *        the date to set
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *        the timestamp to set
     */
    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}
