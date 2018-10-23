/**
 *
 */
package com.borngroup.ssl.core.services;

import java.util.List;

import com.borngroup.ssl.core.model.DepartmentBufferStockModel;

/**
 * @author dikshabhatia
 *
 */
public interface SslDepartmentBufferstockService {
    public List<DepartmentBufferStockModel> checkSeasonMappingWithSeasonGroup(final String seasonCode);

}
