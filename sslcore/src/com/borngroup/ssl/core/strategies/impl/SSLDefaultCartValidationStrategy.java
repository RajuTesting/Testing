/**
 *
 */
package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.commerceservices.strategies.impl.DefaultCartValidationStrategy;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.product.service.SslProductService;

/**
 * SSLDefaultCartValidationStrategy: This class overrides the OOTB functionality of validateCartEntry method. This basically removes the
 * product from cart if it is unapproved or it's base product(style variant) is unapproved or it's base product(apparel product) is
 * unapproved.
 *
 * Created by harleen.chadha@nagarro.com
 *
 * @author SSL
 */
public class SSLDefaultCartValidationStrategy extends DefaultCartValidationStrategy {
	
	@Resource(name = "sslProductService")
    private SslProductService sslProductService;
	
	@Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Override
    protected CommerceCartModification validateCartEntry(final CartModel cartModel, final CartEntryModel cartEntryModel) {
        // First verify that the product exists
        try {
        	List<String> productCodes=null;
            //getProductService().getProductForCode(cartEntryModel.getProduct().getCode());
            if (cartEntryModel.getProduct() instanceof ApparelSizeVariantProductModel) {
                final ProductModel styleVariantProduct = ((ApparelSizeVariantProductModel) (cartEntryModel.getProduct())).getBaseProduct();
                // check if base product i.e. Style Variant exists
                productCodes=new ArrayList<>();
                productCodes.add(styleVariantProduct.getCode());
                // getProductService().getProductForCode(styleVariantProduct.getCode());
                // check if base product i.e. Apparel Product exists
                final ProductModel product = ((ApparelStyleVariantProductModel) styleVariantProduct).getBaseProduct();
                productCodes.add(product.getCode());
                //getProductService().getProductForCode(product.getCode());
            } else if (cartEntryModel.getProduct() instanceof ApparelStyleVariantProductModel) {
                // check if base product i.e. Apparel Product exists
                final ProductModel product = ((ApparelStyleVariantProductModel) cartEntryModel.getProduct()).getBaseProduct();
                getProductService().getProductForCode(product.getCode());
            }
			if (!CollectionUtils.isEmpty(productCodes)) {
				final List<ProductModel> products = sslProductService.getProducts(productCodes, catalogVersionService
.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.ONLINE_CATALOG_VERSION),
                        false);
				if (CollectionUtils.isEmpty(products) || products.size() < productCodes.size()) {
					return setCartModificationData(cartModel, cartEntryModel);
				}
			}
            }
            catch (final UnknownIdentifierException e) {
            	return setCartModificationData(cartModel,cartEntryModel);
        }
        return super.validateCartEntry(cartModel, cartEntryModel);
    }

	private CommerceCartModification setCartModificationData(final CartModel cartModel, final CartEntryModel cartEntryModel) {
		final CommerceCartModification modification = new CommerceCartModification();
		modification.setStatusCode(CommerceCartModificationStatus.UNAVAILABLE);
		modification.setQuantityAdded(0);
		modification.setQuantity(0);

		final CartEntryModel entry = new CartEntryModel();
		entry.setProduct(cartEntryModel.getProduct());

		modification.setEntry(entry);

		getModelService().remove(cartEntryModel);
		getModelService().refresh(cartModel);

		return modification;
	}
}