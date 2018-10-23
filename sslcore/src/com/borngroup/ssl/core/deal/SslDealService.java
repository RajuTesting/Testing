/**
 *
 */
package com.borngroup.ssl.core.deal;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author jasleensaini
 *
 */
public interface SslDealService {
    public List<ProductModel> getProductsFromDealComponent(DealComponentModel component);
}
