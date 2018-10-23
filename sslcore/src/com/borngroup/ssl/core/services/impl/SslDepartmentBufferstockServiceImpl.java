/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SslDepartmentBufferstockDao;
import com.borngroup.ssl.core.model.DepartmentBufferStockModel;
import com.borngroup.ssl.core.services.SslDepartmentBufferstockService;

/**
 * @author dikshabhatia
 *
 */
public class SslDepartmentBufferstockServiceImpl implements SslDepartmentBufferstockService {

    @Resource(name = "sslDepartmentBufferstockDao")
    SslDepartmentBufferstockDao sslDepartmentBufferstockDao;

    /**
     * To check if for a particular season code any corresponding value of Season Group code exists or not
     *
     * @param seasonCode, seasonGroupCode
     * @return boolean
     */
    @Override
    public List<DepartmentBufferStockModel> checkSeasonMappingWithSeasonGroup(final String seasonCode) {
        return sslDepartmentBufferstockDao.checkSeasonMappingWithSeasonGroup(seasonCode);
    }

}
