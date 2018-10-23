/**
 *
 */
package com.borngroup.ssl.core.storefinder.dao.impl;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.storefinder.dao.SSLStoreFinderDao;

/**
 * DAO: Default implementation of {@link SSLStoreFinderDao}
 * <p/>
 * Created by sonam.kaushik@nagarro.com
 *
 * @author SSL
 */
public class SSLStoreFinderDaoImpl implements SSLStoreFinderDao {

    @Resource(name = "flexibleSearchService")
    FlexibleSearchService flexibleSearchService;

    @Override
    public List<PointOfServiceModel> getPOSForTown(final String town, final BaseStoreModel baseStore) {
        final String queryString = "SELECT {pos:" + PointOfServiceModel.PK + "} FROM {" + PointOfServiceModel._TYPECODE + " AS pos JOIN "
                + EnumerationValueModel._TYPECODE + " as evm ON {pos:" + PointOfServiceModel.TYPE + "}={evm:" + EnumerationValueModel.PK
                + "} and {evm:" + EnumerationValueModel.CODE + "}= 'STORE'" + " JOIN " + AddressModel._TYPECODE + " AS addr ON {pos:"
                + PointOfServiceModel.ADDRESS + "}={addr:" + AddressModel.PK + "} } WHERE LOWER({addr:" + AddressModel.TOWN
                + "}) LIKE LOWER('%" + town.toLowerCase().trim() + "') AND {pos:" + PointOfServiceModel.LATITUDE
                + "} IS NOT NULL AND {pos:" + PointOfServiceModel.LONGITUDE + "} IS NOT NULL AND {pos:" + PointOfServiceModel.BASESTORE
                + "}=?basestore";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("basestore", baseStore);
        final SearchResult<PointOfServiceModel> searchResult = this.flexibleSearchService.search(query);
        if (searchResult != null && searchResult.getResult() != null) {
            return searchResult.getResult();
        }
        return null;
    }

}
