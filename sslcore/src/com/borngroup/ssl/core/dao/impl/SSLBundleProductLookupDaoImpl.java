/**
 * 
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLBundleProductLookupDao;
import com.borngroup.ssl.core.model.SslBundleProductModel;


/**
 * @author sriharsha.karuturi
 * 
 */
public class SSLBundleProductLookupDaoImpl extends DefaultGenericDao<SslBundleProductModel> implements SSLBundleProductLookupDao
{

	public SSLBundleProductLookupDaoImpl()
	{
		super("SslBundleProduct");
	}


	/**
	 * @param typecode
	 */
	public SSLBundleProductLookupDaoImpl(final String typecode)
	{
		super("SslBundleProduct");
	}
	
	 @Resource(name = "flexibleSearchService")
	 FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.borngroup.ssl.core.dao.SSLBundleProductLookupDao#getBundleProductByCode(java.lang.String)
	 */
	@Override
	public SslBundleProductModel getBundleProductByCode(final String code)
	{
		final List<SslBundleProductModel> sslBundleProduct = find(Collections.singletonMap("code", code));
		return sslBundleProduct.size() > 0 ? sslBundleProduct.get(0) : null;
	}

	 @Override
     public Collection<PriceRowModel> getAllPriceRows(String productId)
	    {
	    	final String query = "SELECT {" + PriceRowModel.PK + "} FROM {" + PriceRowModel._TYPECODE + "} WHERE {"
					+ PriceRowModel.PRODUCTID + "} = ?productId";
	    	
	    	 final Map<String, Object> map = new HashMap<>();
	         map.put("productId", productId);
	        final SearchResult<PriceRowModel> result = flexibleSearchService.search(query,map);
	        return result.getResult();
	    }

}
