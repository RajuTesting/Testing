/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author manikmalhotra
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeDTO {

    @XmlAttribute(name = "Desc")
    private String desc;

    @XmlAttribute(name = "Name")
    private String name;

    public String getDesc() {
        return desc;
    }

    public void setDesc(final String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
