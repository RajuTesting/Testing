/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

import com.borngroup.ssl.core.model.DepartmentBufferStockModel;

/**
 * @author Nagarro-Dev
 *
 */
public interface SslDepartmentBufferstockDao extends GenericDao<DepartmentBufferStockModel> {

    public Long getBufferStock(ProductModel product);

    public List<DepartmentBufferStockModel> checkSeasonMappingWithSeasonGroup(final String seasonCode);
}
