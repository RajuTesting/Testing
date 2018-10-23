/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author manikmalhotra
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderByDTO {
    @XmlElement(name = "Attribute")
    private AttributeDTO attribute;

    public AttributeDTO getAttribute() {
        if (attribute == null) {
            attribute = new AttributeDTO();
        }
        return attribute;
    }

    public void setAttribute(final AttributeDTO attribute) {
        this.attribute = attribute;
    }

}
