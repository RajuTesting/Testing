/**
 *
 */
package com.ssl.core.sterling.orderpush.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author manikmalhotra
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InstructionDTO {

    @XmlAttribute(name = "SequenceNo")
    private String sequenceNo;

    @XmlAttribute(name = "InstructionText")
    private String instructionText;

    @XmlAttribute(name = "InstructionType")
    private String instructionType;

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(final String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getInstructionText() {
        return instructionText;
    }

    public void setInstructionText(final String instructionText) {
        this.instructionText = instructionText;
    }

    public String getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(final String instructionType) {
        this.instructionType = instructionType;
    }

}
