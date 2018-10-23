/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "API")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListApiDTO {

    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "IsFlow")
    private String isFlow;

    @XmlElement(name = "Input")
    private OrderInputRequestDTO input;

    @XmlElement(name = "Template")
    private String template;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the isFlow
     */
    public String getIsFlow() {
        return isFlow;
    }

    /**
     * @param isFlow the isFlow to set
     */
    public void setIsFlow(final String isFlow) {
        this.isFlow = isFlow;
    }

    /**
     * @return the input
     */
    public OrderInputRequestDTO getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(final OrderInputRequestDTO input) {
        this.input = input;
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(final String template) {
        this.template = template;
    }

}
