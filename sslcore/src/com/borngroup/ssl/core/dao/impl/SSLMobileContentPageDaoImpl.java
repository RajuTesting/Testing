/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLMobileContentPageDao;
import com.borngroup.ssl.core.model.SSLMobileContentPageModel;

/**
 * @author gokulpandey
 *
 */
public class SSLMobileContentPageDaoImpl implements SSLMobileContentPageDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "catalogService")
    private CatalogService catalogService;

    @Override
    public List<SSLMobileContentPageModel> getAllMobileContentPages() {
        final String catalogVersion = "sslContentCatalog";
        final String query = "SELECT {" + SSLMobileContentPageModel.PK + "} FROM {" + SSLMobileContentPageModel._TYPECODE + "} WHERE {"
                + SSLMobileContentPageModel.CATALOGVERSION + "}=?catalogVersion";
        final Map<String, Object> params = new HashMap<String, Object>();
        final CatalogModel catalog = catalogService.getCatalogForId(catalogVersion);
        final CatalogVersionModel cvm = catalog.getCatalogVersions().stream().filter(cata -> cata.getVersion().equals("Online")).findAny()
                .get();
        params.put("catalogVersion", cvm);
        final SearchResult<SSLMobileContentPageModel> searchResult = getFlexibleSearchService().search(query, params);
        return searchResult.getResult();
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService
     *        the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    /**
     * @param catalogService
     *        the catalogService to set
     */
    public void setCatalogService(final CatalogService catalogService) {
        this.catalogService = catalogService;
    }

}
