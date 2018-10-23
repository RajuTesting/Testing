/**
 *
 */
package com.borngroup.ssl.core.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IAResponseDTO {

    @JsonProperty("status")
    private int status;


    @JsonProperty("request_id")
    private String requestId;


    @JsonProperty("site_product_id")
    private String siteProductId;


    @JsonProperty("data")
    private List<IADataResponseDTO> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String statusTxt) {
        this.requestId = statusTxt;
    }

    public String getSiteProductId() {
        return siteProductId;
    }

    public void setSiteProductId(String siteProductId) {
        this.siteProductId = siteProductId;
    }

    public List<IADataResponseDTO> getData() {
        return data;
    }

    public void setData(List<IADataResponseDTO> data) {
        this.data = data;
    }

    @Override public String toString() {
        return "IAResponseDTO{" + "status=" + status + ", requestId='" + requestId + '\'' + ", siteProductId='" + siteProductId + '\''
                + ", data=" + data + '}';
    }
}
