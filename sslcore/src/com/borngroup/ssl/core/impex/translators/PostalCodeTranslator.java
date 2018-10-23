/**
 *
 */
package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Translator for converting
 *
 * @author SSL
 *
 */
public class PostalCodeTranslator extends AbstractValueTranslator {

    private static final String MODEL_SERVICE = "modelService";
    private static final String FLEXIBLE_SEARCH_SERVICE = "flexibleSearchService";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private ModelService modelService;

    private final Map<String, RegionModel> regionModelMap = new HashMap<>();

    @Override
    public Object importValue(final String state, final Item item) {
        RegionModel pK = null;
        if (MapUtils.isEmpty(regionModelMap)) {

            final String query = "{SELECT {region.PK} FROM {" + RegionModel._TYPECODE + " as region}";

            final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
            flexibleSearchService = (FlexibleSearchService) Registry.getApplicationContext().getBean(FLEXIBLE_SEARCH_SERVICE);
            modelService = (ModelService) Registry.getApplicationContext().getBean(MODEL_SERVICE);
            final SearchResult<RegionModel> searchResults = flexibleSearchService.search(searchQuery);
            final List<RegionModel> regionModels = searchResults.getResult();
            if (CollectionUtils.isNotEmpty(regionModels)) {
                regionModels.forEach(region -> {
                    regionModelMap.put(region.getName().toUpperCase(), region);
                });
            }
            pK = regionModelMap.get(state.toUpperCase());
        } else {
            pK = regionModelMap.get(state.toUpperCase());
        }
        if (null != pK) {
            return pK.getIsocode();
        } else {
            return "NA";
        }
    }

    @Override
    public String exportValue(final Object value) throws JaloInvalidParameterException {
        return value == null ? "" : value.toString();
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

}
