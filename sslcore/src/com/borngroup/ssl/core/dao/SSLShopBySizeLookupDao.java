/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.model.SslShopBySizeMappingModel;

/**
 * @author gurkiratmohain
 *
 */
public interface SSLShopBySizeLookupDao {
	public List<SslShopBySizeMappingModel> getShopBySizePriorityList();

}
