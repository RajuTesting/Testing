/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author pankajgandhi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderTotalDTO {

    @XmlAttribute(name = "GrandCharges")
    private String grandCharges;

    @XmlAttribute(name = "GrandDiscount")
    private String grandDiscount;

    @XmlAttribute(name = "GrandTotal")
    private String grandTotal;

    /**
     * @return the grandCharges
     */
    public String getGrandCharges() {
        return grandCharges;
    }

    /**
     * @param grandCharges
     *        the grandCharges to set
     */
    public void setGrandCharges(final String grandCharges) {
        this.grandCharges = grandCharges;
    }

    /**
     * @return the grandDiscount
     */
    public String getGrandDiscount() {
        return grandDiscount;
    }

    /**
     * @param grandDiscount
     *        the grandDiscount to set
     */
    public void setGrandDiscount(final String grandDiscount) {
        this.grandDiscount = grandDiscount;
    }

    /**
     * @return the grandTotal
     */
    public String getGrandTotal() {
        return grandTotal;
    }

    /**
     * @param grandTotal
     *        the grandTotal to set
     */
    public void setGrandTotal(final String grandTotal) {
        this.grandTotal = grandTotal;
    }

}
