/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.EanDao;
import com.borngroup.ssl.core.model.EanModel;

/**
 * @author tejsharma
 *
 */
public class SSLDefaultEanDao implements EanDao {
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EanModel> findEans() {
        final String query = "SELECT {p:" + EanModel.PK + "} FROM {" + EanModel._TYPECODE + " AS p}";
        final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
        return flexibleSearchService.<EanModel> search(flexQuery).getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EanModel> findEanByNumber(final String eanNumber) {
        final String query = "SELECT {p:" + EanModel.PK + "} FROM {" + EanModel._TYPECODE + " AS p}" + " WHERE {p:" + EanModel.EANNUMBER
                + "}=?eanNumber ";
        final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
        flexQuery.addQueryParameter("eanNumber", eanNumber);
        return flexibleSearchService.<EanModel> search(flexQuery).getResult();
    }

}
