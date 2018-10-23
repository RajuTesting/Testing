/**
 *
 */
package com.borngroup.ssl.core.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class IADataResponseDTO {

    @JsonProperty("person_id")
    private String person_id;


    @JsonProperty("ecompany")
    private String ecompany;


    @JsonProperty("site_product_id")
    private String siteProductId;

    @JsonProperty("message")
    private String message;


    @JsonProperty("items")
    private List<IARecommendationDTO> recommendations;

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getEcompany() {
        return ecompany;
    }

    public void setEcompany(String ecompany) {
        this.ecompany = ecompany;
    }

    public String getSiteProductId() {
        return siteProductId;
    }

    public void setSiteProductId(String siteProductId) {
        this.siteProductId = siteProductId;
    }

    public List<IARecommendationDTO> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<IARecommendationDTO> recommendations) {
        this.recommendations = recommendations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override public String toString() {
        return "IADataResponseDTO{" + "person_id='" + person_id + '\'' + ", ecompany='" + ecompany + '\'' + ", siteProductId='"
                + siteProductId + '\'' + ", message='" + message + '\'' + ", recommendations=" + recommendations + '}';
    }
}
