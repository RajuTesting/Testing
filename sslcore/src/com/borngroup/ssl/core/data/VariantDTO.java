/**
 *
 */
package com.borngroup.ssl.core.data;

import java.util.List;

/**
 * @author Viji
 *
 */
public class VariantDTO {
    private String code;
    //Not commented for backward compatibility
    private String smallImageUrl;
    //Not commented for backward compatibility
    private String mediumImageUrl;
    //Not commented for backward compatibility
    private String largeImageUrl;
    private String swatchUrl;
    private String color;
    private String size;
    private long stock;
    private double price;
    private List<VariantDTO> sizes;
    private String sizeDesc;
    /**
     * Hold style variant images for product. 
     */
    private List<VariantStyleImagesDTO> styleImages;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(final long stock) {
        this.stock = stock;
    }

    /**
     * @return the sizes
     */
    public List<VariantDTO> getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(final List<VariantDTO> sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the swatchUrl
     */
    public String getSwatchUrl() {
        return swatchUrl;
    }

    /**
     * @param swatchUrl the swatchUrl to set
     */
    public void setSwatchUrl(final String swatchUrl) {
        this.swatchUrl = swatchUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * @return the sizeDesc
     */
    public String getSizeDesc() {
        return sizeDesc;
    }

    /**
     * @param sizeDesc the sizeDesc to set
     */
    public void setSizeDesc(final String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    

    /**
     * @return the smallImageUrl
     */
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * @param smallImageUrl the smallImageUrl to set
     */
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    /**
     * @return the mediumImageUrl
     */
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    /**
     * @param mediumImageUrl the mediumImageUrl to set
     */
    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    /**
     * @return the largeImageUrl
     */
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    /**
     * @param largeImageUrl the largeImageUrl to set
     */
    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    /**
     * Get List of VariantStyleImagesDTO.
     * @return the styleImages
     */
    public List<VariantStyleImagesDTO> getStyleImages() {
        return styleImages;
    }

    /**
     *  Set styleImages - Instance of List<VariantStyleImagesDTO>.
     * @param styleImages the styleImages to set
     */
    public void setStyleImages(List<VariantStyleImagesDTO> styleImages) {
        this.styleImages = styleImages;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VariantDTO [code=" + code + ", smallImageUrl=" + smallImageUrl + ", mediumImageUrl=" + mediumImageUrl + ", largeImageUrl="
                + largeImageUrl + ", swatchUrl=" + swatchUrl + ", color=" + color + ", size=" + size + ", stock=" + stock + ", price="
                + price + ", sizes=" + sizes + ", sizeDesc=" + sizeDesc + ", styleImages=" + styleImages + "]";
    }

}
