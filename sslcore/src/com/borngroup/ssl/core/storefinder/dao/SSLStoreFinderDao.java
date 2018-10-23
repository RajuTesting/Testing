/**
 *
 */
package com.borngroup.ssl.core.storefinder.dao;

import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

/**
 * Interface: To get store/point of service related data
 * <p/>
 * Created by sonam.kaushik@nagarro.com
 *
 * @author SSL
 */
public interface SSLStoreFinderDao {
    /**
     * Method to get all POS for a given town/city
     *
     * @param town
     *        - city for which POS is required
     * @param baseStore
     *        - base store for which POS is required
     * @return A list of POS for a given town/city
     */
    List<PointOfServiceModel> getPOSForTown(String town, BaseStoreModel baseStore);
}
