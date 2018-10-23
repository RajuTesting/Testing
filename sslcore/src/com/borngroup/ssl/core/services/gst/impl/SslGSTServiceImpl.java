/**
 *
 */
package com.borngroup.ssl.core.services.gst.impl;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.services.gst.SslGSTService;
import com.borngroup.ssl.core.util.GSTUtil;

/**
 * @author manikmalhotra
 *
 */
public class SslGSTServiceImpl implements SslGSTService {
    private static final Logger LOG = Logger.getLogger(SslGSTServiceImpl.class);

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public PointOfServiceModel getCentralLocation() {
        final PointOfServiceModel centralLocation = new PointOfServiceModel();
        centralLocation.setType(PointOfServiceTypeEnum.CENTRAL);
        return flexibleSearchService.getModelByExample(centralLocation);
    }

    @Override
    public RegionModel getRegionModel(final String regionISOcode) {
        final RegionModel regionModel = new RegionModel();
        RegionModel warehouseRegion = null;
        regionModel.setIsocode(regionISOcode);
        try {
            warehouseRegion = flexibleSearchService.getModelByExample(regionModel);
        } catch (final Exception e) {
            LOG.error("FATAL ERROR: Invalid region code for the warehouse. " + regionISOcode);
        }
        return warehouseRegion;

    }

    @Override
    public boolean hasBOSEntries(final ConsignmentModel consignment) {
        return CollectionUtils.isNotEmpty(consignment.getConsignmentEntries().stream()
                .filter(ce -> NumberUtils.compare(GSTUtil.isZeroTax(ce.getTaxComponents()), 0) == 0).collect(Collectors.toSet()));
    }
}
