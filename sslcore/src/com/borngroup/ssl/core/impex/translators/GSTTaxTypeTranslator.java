/**
 *
 */
package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.enums.GstTaxTypes;

/**
 * Translator for converting
 *
 * @author SSL
 *
 */
public class GSTTaxTypeTranslator extends AbstractValueTranslator {

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private ModelService modelService;

    private final Map<String, GstTaxTypes> gstTaxTypeMap = new HashMap<>();

    @Override
    public Object importValue(final String taxType, final Item item) {
        GstTaxTypes pK = null;
        if (MapUtils.isEmpty(gstTaxTypeMap)) {
            if (StringUtils.isNotEmpty(taxType) || null != taxType) {
                final String query = "{SELECT {tax.PK} FROM {" + GstTaxTypes._TYPECODE + " as tax}";

                final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
                // searchQuery.addQueryParameter("taxType", taxType);
                flexibleSearchService = (FlexibleSearchService) Registry.getApplicationContext().getBean("flexibleSearchService");
                modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
                final SearchResult<EnumerationValueModel> searchResult = flexibleSearchService.search(searchQuery);
                for (final Iterator<EnumerationValueModel> iter = searchResult.getResult().iterator(); iter.hasNext();) {
                    final EnumerationValueModel model = iter.next();
                    gstTaxTypeMap.put(model.getCode().toString(), modelService.get(model.getPk()));

                }

            }
            pK = gstTaxTypeMap.get(taxType);
        } else {
            pK = gstTaxTypeMap.get(taxType);
        }

        return pK;
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
