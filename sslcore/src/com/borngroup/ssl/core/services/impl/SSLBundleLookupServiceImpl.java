/**
 * 
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.SSLBundleProductLookupDao;
import com.borngroup.ssl.core.model.SslBundleProductModel;
import com.borngroup.ssl.core.services.SSLBundleLookupService;


/**
 * @author sriharsha.karuturi
 * 
 */
public class SSLBundleLookupServiceImpl implements SSLBundleLookupService
{

	@Resource(name = "sslBundleLookupDao")
	SSLBundleProductLookupDao sslBundleLookupDao;

	@Override
	public List<String> getBundleProductListByCode(final String code)
	{
		final SslBundleProductModel bundleProductModel = sslBundleLookupDao.getBundleProductByCode(code);
		List<ProductModel> productList = null;
		final List<String> productCodeList = new ArrayList<String>();
		if (bundleProductModel != null)
		{
			productList = new ArrayList<ProductModel>();
			productList = bundleProductModel.getBundleItems();

			if (!CollectionUtils.isEmpty(productList))
			{
				for (final ProductModel product : productList)
				{
					productCodeList.add(product.getCode());
				}
			}

		}
		return productCodeList;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.services.SSLBundleLookupService#getBundleProductByCode(java.lang.String)
	 */
	@Override
	public SslBundleProductModel getBundleProductByCode(final String code)
	{
		final SslBundleProductModel bundleProductModel = sslBundleLookupDao.getBundleProductByCode(code);
		return bundleProductModel != null ? bundleProductModel : null;
	}
	
	@Override
    public Collection<PriceRowModel> getAllPriceRows(String productId) {
    	return sslBundleLookupDao.getAllPriceRows(productId);
    }
}
