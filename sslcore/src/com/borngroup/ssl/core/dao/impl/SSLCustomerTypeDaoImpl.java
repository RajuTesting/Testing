package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

import com.borngroup.ssl.core.dao.SSLCustomerTypeDao;
import com.borngroup.ssl.core.model.SSLCustomerTypeModel;


/**
 * @author trinadh.nimmagadda
 *
 */
public class SSLCustomerTypeDaoImpl extends DefaultGenericDao<SSLCustomerTypeModel>implements SSLCustomerTypeDao
{

    public SSLCustomerTypeDaoImpl()
    {
        super(SSLCustomerTypeModel._TYPECODE);
    }

    @Override
    public SSLCustomerTypeModel getSSLCustomerType()
    {
        final List<SSLCustomerTypeModel> result = find();

        if (result.size() > 0)
        {
            return result.get(0);
        }
        return null;
    }


}
