/**
 *
 */
package com.borngroup.ssl.core.data;

/**
 * @author bhavya2486
 *
 */
public class SSLWarehouseAddressDTO {
    private String name;
    private String line1;
    private String line2;
    private String district;
    private String city;
    private String pinCode;
    private String state;
    private String contact;
    private String tollFree;

    public String getName() {
        return name;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getState() {
        return state;
    }

    public String getContact() {
        return contact;
    }

    public String getTollFree() {
        return tollFree;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setLine1(final String line1) {
        this.line1 = line1;
    }

    public void setLine2(final String line2) {
        this.line2 = line2;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    public void setTollFree(final String tollFree) {
        this.tollFree = tollFree;
    }
}
