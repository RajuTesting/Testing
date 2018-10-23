/**
 *
 */
package com.ssl.core.mms.jaxb.api;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Items")
@XmlAccessorType(XmlAccessType.FIELD)
public class MMSMessagePublishDTO implements Serializable {

    public MMSMessagePublishDTO() {

    }

    public MMSMessagePublishDTO(final List<MMSItemDTO> items) {
        this.items = items;
    }

    @XmlElement(name = "Item")
    private List<MMSItemDTO> items;

    private String date;

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
     * @return the items
     */
    public List<MMSItemDTO> getItems() {
        return items;
    }

    /**
     * @param items
     *        the item to set
     */
    public void setItems(final List<MMSItemDTO> items) {
        this.items = items;
    }
}
