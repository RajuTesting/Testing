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
public class InstructionsDTO {

    @XmlElement(name = "Instruction")
    private List<InstructionDTO> orderInstruction;

    public List<InstructionDTO> getOrderInstruction() {
        if (orderInstruction == null) {
            orderInstruction = new ArrayList<>();
        }
        return orderInstruction;
    }

    public void setOrderInstruction(final List<InstructionDTO> orderInstruction) {
        this.orderInstruction = orderInstruction;
    }

}
