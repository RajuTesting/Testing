/**
 *
 */
package com.borngroup.ssl.core.product.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.sslfacades.services.data.ProductPincodeData;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ProductSkuListModel;
import com.borngroup.ssl.core.model.components.RecommendedProductsComponentModel;
import com.borngroup.ssl.storepickup.store.data.PickupStoreData;
import com.borngroup.ssl.storepickup.store.data.StoreWisePickupStoreData;

/**
 * @author Viji
 *
 */
public interface SslProductService extends ProductService {

    public String getProductSizeGuide(ProductModel productModel);

    /**
     * This will checks for product status and redirect to new page if any one of below condition meets. A) If base product is Unapproved.
     * B) If base is approved and all style varients are unapproved. C) If base is approved and style varients are approved then check for
     * all size varients. If all size varients are unapporved.
     *
     * @param productModel
     * @return boolean
     */

    boolean checkGoogleRedirectionForProduct(final ProductModel productModel);

    /**
     * This method changes the approval status to true/false in search restrictions.
     *
     * @param active
     */

    void changeApprovalStatusInSearchRestriction(boolean active);

    /**
     * @param code
     * @return
     */
    ProductModel getProductForCodeUsingAdminAccess(String code);

    /**
     *
     * @param components
     * @param params
     * @return
     */
    List<ProductSkuListModel> getProductRecommendations(List<RecommendedProductsComponentModel> components, Map<String, String> params);

    /**
     * @param code
     * @param catalog
     * @param sort
     * @return products
     */
    List<ProductModel> getProducts(List<String> code, CatalogVersionModel catalog, boolean sort);

    List<ApparelStyleVariantProductModel> getProductsForCategory(final String categoryCodes, final String seasonCode,
            final String approvalStatus, Date productCreationDate);

    /**
     * @param product
     * @param value
     */
    void updatePopularity(ProductModel product, int value);

    /**
     * @param categoryCode
     * @return
     */
    List<ApparelProductModel> getBaseProductsForCategory(String categoryCode);

    /**
     * @param components
     * @param params
     * @return
     */
    List<ProductSkuListModel> getSimilarProducts(List<RecommendedProductsComponentModel> components, Map<String, String> params);

    /**
     * @param code
     * @param productPk
     * @return
     */
    String getProductFeatureValueByCode(String code, PK productPk);

    /**
     * @param categoryCode
     * @param date
     * @return
     */
    List<ProductModel> getBestSellerProductsForCategory(CategoryModel categoryCode, Integer date);

    /**
     * @param product
     * @return
     */
    ProductModel getBaseProduct(ProductModel product);

    /**
     * @param sessionCart
     * @param pincodeData
     * @param productPickupStoresInfo
     * @param storeData
     * @param model
     * @return
     */
    boolean checkSameStorePickup(CartData sessionCart, List<ProductPincodeData> pincodeData,
            List<StoreWisePickupStoreData> productPickupStoresInfo, PickupStoreData storeData, Model model);

    /**
     * @param sessionCart
     * @param pincodeData
     * @param storeWiseData
     * @param storeData
     * @param pincode
     * @param model
     * @return
     */
    boolean checkAllProductsInStore(CartData sessionCart, List<ProductPincodeData> pincodeData,
            List<StoreWisePickupStoreData> storeWiseData, PickupStoreData storeData, String pincode, Model model);

    /**
     * Sets bestseller attribute to false for all products when cron job was executed previously. (non-Javadoc)
     */
    void resetBestSellerProducts();
}
