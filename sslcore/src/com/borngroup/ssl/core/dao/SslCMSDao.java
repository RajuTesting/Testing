
package com.borngroup.ssl.core.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.servicelayer.daos.CMSPageDao;

import java.util.List;
import java.util.Set;

import com.borngroup.ssl.core.model.OfferPageModel;
import com.borngroup.ssl.core.model.components.OfferDetailsComponentModel;

/**
 * Dao Interface : Dao class for CMS components
 *
 * Created by harman.kaur@nagarro.com.
 *
 * @author SSL
 */
public interface SslCMSDao extends CMSPageDao {

    /**
     * Method to return offer pages .
     *
     * @param catalogVersions
     *        Set of {@link CatalogVersionModel}
     * @return List of {@link OfferPageModel}
     */
    List<OfferPageModel> findOfferpage(Set<CatalogVersionModel> catalogVersions);

    /**
     * Method to find offer detail page corresponding to a label .
     *
     * @param offerCode
     *        {@link String}
     * @param catalogVersions
     *        Set of {@link CatalogVersionModel}
     * @return {@link OfferDetailsComponentModel}
     */
    List<OfferPageModel> findOfferPageForLabel(String offerCode, final Set<CatalogVersionModel> catalogVersions);
}
