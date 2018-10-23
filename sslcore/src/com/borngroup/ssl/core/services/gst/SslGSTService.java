/**
 *
 */
package com.borngroup.ssl.core.services.gst;

import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

/**
 * @author manikmalhotra
 *
 */
public interface SslGSTService {

    /**
     * @return PointOfServiceModel
     */
    PointOfServiceModel getCentralLocation();

    RegionModel getRegionModel(String regionISOCode);

    /**
     * @param consignment
     * @return
     */
    boolean hasBOSEntries(ConsignmentModel consignment);

}
