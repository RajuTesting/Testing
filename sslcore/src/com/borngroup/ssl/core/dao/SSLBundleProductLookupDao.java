/**
 * 
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.europe1.model.PriceRowModel;

import java.util.Collection;

import com.borngroup.ssl.core.model.SslBundleProductModel;


/**
 * @author sriharsha.karuturi
 * 
 */
public interface SSLBundleProductLookupDao
{

	public SslBundleProductModel getBundleProductByCode(String code);

	/**
	 * @param productId
	 * @return
	 */
	Collection<PriceRowModel> getAllPriceRows(String productId);


}
