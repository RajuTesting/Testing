/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LineChargesDTO {

    @XmlElement(name = "LineCharge")
    private List<LineChargeDTO> lineCharge;

    public List<LineChargeDTO> getLineCharge() {
        if (lineCharge == null) {
            lineCharge = new ArrayList<>();
        }
        return lineCharge;
    }

    public void setLineCharge(final List<LineChargeDTO> lineCharge) {
        this.lineCharge = lineCharge;
    }

}
