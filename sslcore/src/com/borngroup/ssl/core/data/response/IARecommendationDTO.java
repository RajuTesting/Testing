/**
 *
 */
package com.borngroup.ssl.core.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class IARecommendationDTO {


    @JsonProperty("name")
    private String name;

    @JsonProperty("sku")
    private String sku;


    @JsonProperty("site_product_id")
    private String siteProductId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteProductId() {
        return siteProductId;
    }

    public void setSiteProductId(String siteProductId) {
        this.siteProductId = siteProductId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override public String toString() {
        return "IARecommendationDTO{" + "name='" + name + '\'' + ", sku='" + sku + '\'' + ", siteProductId='" + siteProductId + '\'' + '}';
    }
}
