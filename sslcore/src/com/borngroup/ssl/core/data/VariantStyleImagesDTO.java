package com.borngroup.ssl.core.data;

/**
 * VariantStyleImagesDTO instance.
 * <p/>
 * Created by ravi.yadav@nagarro.com
 *
 * @author SSL
 */
public class VariantStyleImagesDTO {
    /**
     * Hold smallImageUrl for style variant.
     */
    private String smallImageUrl;
    /**
     * Hold mediumImageUrl for style variant.
     */
    private String mediumImageUrl;
    /**
     * Hold largeImageUrl for style variant.
     */
    private String largeImageUrl;

    /**
     * Get smallImageUrl - Instance of String.
     * @return the smallImageUrl
     */
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * Set smallImageUrl for style variant.
     * @param smallImageUrl1 the smallImageUrl to set
     */
    public void setSmallImageUrl(final String smallImageUrl1) {
        this.smallImageUrl = smallImageUrl1;
    }

    /**
     * Get mediumImageUrl - Instance of String. 
     * @return the mediumImageUrl
     */
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    /**
     * Set mediumImageUrl for style variant.
     * @param mediumImageUrl1 the mediumImageUrl to set
     */
    public void setMediumImageUrl(final String mediumImageUrl1) {
        this.mediumImageUrl = mediumImageUrl1;
    }

    /**
     * Get largeImageUrl - Instance of String.
     * @return the largeImageUrl
     */
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    /**
     * Set largeImageUrl for style variant.
     * @param largeImageUrl1 the largeImageUrl to set
     */
    public void setLargeImageUrl(final String largeImageUrl1) {
        this.largeImageUrl = largeImageUrl1;
    }

    /**
     * To string method print data on system print.
     */
    @Override
    public String toString() {
        return "VariantStyleImagesDTO [smallImageUrl=" + smallImageUrl + ", mediumImageUrl=" + mediumImageUrl + ", largeImageUrl="
                + largeImageUrl + "]";
    }

}
