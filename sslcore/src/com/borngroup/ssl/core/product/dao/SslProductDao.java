/**
 *
 */
package com.borngroup.ssl.core.product.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author Viji
 *
 */
public interface SslProductDao {

    public String getProductSizeGuide(ProductModel productModel);

    /**
     * This method changes the approval status to true/false in search restrictions.
     *
     * @param active
     */

    void changeApprovalStatusInSearchRestriction(boolean active);

    /**
     * @param code
     * @param catalog
     * @return the products
     */
    List<ProductModel> getProducts(final List<String> code, final CatalogVersionModel catalog, boolean sort);

    List<ApparelStyleVariantProductModel> getProductsForCategory(final String categoryCodes, final CatalogVersionModel catalog,
            final String seasonCode, final String approvalStatus, final Date productCreationDate);

    /**
     * @param category
     * @param catalog
     * @return
     */
    List<ApparelProductModel> getBaseProductsForCategory(String category, CatalogVersionModel catalog);

    /**
     * @param code
     * @param productPk
     * @return
     */
    String getProductFeatureValueByCode(String code, PK productPk);

    /**
     * @param category
     * @param date
     * @return
     */
    List<ProductModel> getBestSellerProductsForCategory(CategoryModel category, Integer date);

    /**
     * @return
     */
    public List<ProductModel> resetBestSeller();
}
