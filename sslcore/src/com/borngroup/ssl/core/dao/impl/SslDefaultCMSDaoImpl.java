/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.enums.CmsApprovalStatus;
import de.hybris.platform.cms2.servicelayer.daos.impl.DefaultCMSPageDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.FlexibleSearchUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.borngroup.ssl.core.dao.SslCMSDao;
import com.borngroup.ssl.core.model.OfferPageModel;

/**
 * Dao Class : Dao class for CMS components
 *
 * Created by harman.kaur@nagarro.com.
 *
 * @author SSL
 */
public class SslDefaultCMSDaoImpl extends DefaultCMSPageDao implements SslCMSDao {

    /**
     * Method to return offer pages .
     *
     * @param catalogVersions
     *        set of {@link CatalogVersionModel}
     * @return List of {@link OfferPageModel}
     */
    @Override
    public List<OfferPageModel> findOfferpage(final Set<CatalogVersionModel> catalogVersions) {
        final HashMap queryParameters = new HashMap();
        final StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT {pk} FROM {OfferPage} WHERE {approvalStatus} = ?approvalStatus AND ");
        queryBuilder.append(FlexibleSearchUtils.buildOracleCompatibleCollectionStatement("{catalogVersion} in (?catalogVersions)",
                "catalogVersions", "OR", catalogVersions, queryParameters));
        queryParameters.put("approvalStatus", CmsApprovalStatus.APPROVED);
        final SearchResult result = this.search(queryBuilder.toString(), queryParameters);
        return result.getResult();
    }

    /**
     * Method to find offer detail page corresponding to a label .
     *
     * @param offerCode
     * @param catalogVersions
     *        set of {@link CatalogVersionModel}
     * @return List of {@link OfferPageModel}
     */
    @Override
    public List<OfferPageModel> findOfferPageForLabel(final String offerCode, final Set<CatalogVersionModel> catalogVersions) {
        final StringBuilder queryBuilder = new StringBuilder();
        final HashMap queryParameters = new HashMap();
        queryBuilder.append("SELECT {pk}  FROM {ContentPage}  WHERE {label} = ?label AND {approvalStatus} = ?approvalStatus AND ");
        queryBuilder.append(FlexibleSearchUtils.buildOracleCompatibleCollectionStatement("{catalogVersion} in (?catalogVersions)",
                "catalogVersions", "OR", catalogVersions, queryParameters));
        queryParameters.put("label", offerCode);
        queryParameters.put("approvalStatus", CmsApprovalStatus.APPROVED);
        final SearchResult result = this.search(queryBuilder.toString(), queryParameters);
        return result.getResult();
    }

}
