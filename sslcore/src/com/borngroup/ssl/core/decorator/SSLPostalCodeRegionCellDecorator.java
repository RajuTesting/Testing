package com.borngroup.ssl.core.decorator;

import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.i18n.daos.RegionDao;
import de.hybris.platform.util.CSVCellDecorator;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gokulpandey
 *
 */
public class SSLPostalCodeRegionCellDecorator implements CSVCellDecorator {

    @Autowired
    private RegionDao regionDao;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.util.CSVCellDecorator#decorate(int, java.util.Map)
     */
    @Override
    public String decorate(final int position, final Map<Integer, String> srcLine) {
        final String state = srcLine.get(Integer.valueOf(position));
        final List<RegionModel> regionModel = regionDao.findRegions();
        if (CollectionUtils.isNotEmpty(regionModel)) {
            return regionModel.stream().filter(region -> region.getName().equalsIgnoreCase(state)).findAny().get().getIsocode();
        }
        return null;
    }
}
