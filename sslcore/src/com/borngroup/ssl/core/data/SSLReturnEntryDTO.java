/**
 *
 */
package com.borngroup.ssl.core.data;

/**
 * @author bhavya2486
 *
 */
public class SSLReturnEntryDTO {
    private String entryName;
    private String entrySKU;
    private String quantity;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(final String entryName) {
        this.entryName = entryName;
    }

    public String getEntrySKU() {
        return entrySKU;
    }

    public void setEntrySKU(final String entrySKU) {
        this.entrySKU = entrySKU;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
