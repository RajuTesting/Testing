/**
 *
 */
package com.borngroup.ssl.core.data;

import de.hybris.platform.core.model.product.ProductModel;

import java.io.Serializable;

/**
 * @author harmankaur
 *
 */
public class ProductLocalViewDTO implements Serializable {

    private ProductModel productModel;

    private boolean isBundleProduct;

    private boolean googleRedirect;

    private ProductModel styleVariantProductModel;

    /**
     *
     */
    public ProductLocalViewDTO() {
        super();
        // YTODO Auto-generated constructor stub
        this.isBundleProduct = Boolean.FALSE;
        this.googleRedirect = Boolean.FALSE;
    }

    /**
     * @return the productModel
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    /**
     * @param productModel
     *        the productModel to set
     */
    public void setProductModel(final ProductModel productModel) {
        this.productModel = productModel;
    }

    /**
     * @return the isBundleProduct
     */
    public boolean isBundleProduct() {
        return isBundleProduct;
    }

    /**
     * @param isBundleProduct
     *        the isBundleProduct to set
     */
    public void setBundleProduct(final boolean isBundleProduct) {
        this.isBundleProduct = isBundleProduct;
    }

    /**
     * @return the googleRedirect
     */
    public boolean isGoogleRedirect() {
        return googleRedirect;
    }

    /**
     * @param googleRedirect
     *        the googleRedirect to set
     */
    public void setGoogleRedirect(final boolean googleRedirect) {
        this.googleRedirect = googleRedirect;
    }

    /**
     * @return the styleVariantProductModel
     */
    public ProductModel getStyleVariantProductModel() {
        return styleVariantProductModel;
    }

    /**
     * @param styleVariantProductModel
     *        the styleVariantProductModel to set
     */
    public void setStyleVariantProductModel(final ProductModel styleVariantProductModel) {
        this.styleVariantProductModel = styleVariantProductModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductLocalViewDTO [productModel=" + productModel + ", isBundleProduct=" + isBundleProduct + ", googleRedirect="
                + googleRedirect + ", styleVariantProductModel=" + styleVariantProductModel + "]";
    }

}
