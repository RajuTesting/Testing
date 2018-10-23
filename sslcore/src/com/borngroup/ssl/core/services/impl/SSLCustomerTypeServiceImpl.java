/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.dao.SSLCustomerTypeDao;
import com.borngroup.ssl.core.model.SSLCustomerTypeModel;
import com.borngroup.ssl.core.services.SSLCustomerTypeService;


/**
 * @author trinadh.nimmagadda
 *
 */
public class SSLCustomerTypeServiceImpl implements SSLCustomerTypeService
{

    private SSLCustomerTypeDao sslCustomerTypeDao;

    /**
     * @return the sslCustomerTypeDao
     */
    public SSLCustomerTypeDao getSslCustomerTypeDao()
    {
        return sslCustomerTypeDao;
    }

    /**
     * @param sslCustomerTypeDao
     *            the sslCustomerTypeDao to set
     */
    @Required
    public void setSslCustomerTypeDao(final SSLCustomerTypeDao sslCustomerTypeDao)
    {
        this.sslCustomerTypeDao = sslCustomerTypeDao;
    }

    @Override
    public SSLCustomerTypeModel getSSLCustomerType()
    {
        return sslCustomerTypeDao.getSSLCustomerType();
    }

}
