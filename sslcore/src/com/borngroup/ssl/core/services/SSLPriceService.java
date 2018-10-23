/**
 * 
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;


/**
 * @author midhun.bose
 * 
 */
public interface SSLPriceService
{
    public PriceRowModel getBestPrice(ProductModel product);
}
