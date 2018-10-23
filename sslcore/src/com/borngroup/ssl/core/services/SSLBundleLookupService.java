/**
 * 
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.europe1.model.PriceRowModel;

import java.util.Collection;
import java.util.List;

import com.borngroup.ssl.core.model.SslBundleProductModel;



/**
 * @author sriharsha.karuturi
 * 
 */
public interface SSLBundleLookupService
{
	public SslBundleProductModel getBundleProductByCode(String code);

	public List<String> getBundleProductListByCode(String code);

	/**
	 * @param productId
	 * @return
	 */
	Collection<PriceRowModel> getAllPriceRows(String productId);

}
