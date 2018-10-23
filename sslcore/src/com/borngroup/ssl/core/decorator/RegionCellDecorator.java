/**
 *
 */
package com.borngroup.ssl.core.decorator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Suresh
 *
 */
public class RegionCellDecorator implements CSVCellDecorator
{
    private static final Logger LOG = Logger.getLogger(SizeCellDecorator.class);

    @Autowired
    private ModelService          modelService;
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public String decorate(final int position, final Map<Integer, String> srcLine)
    {
        final String parsedValue = srcLine.get(position);
        try
        {
            RegionModel regionModel = getModelService().create(RegionModel.class);
            regionModel.setName(parsedValue);
            regionModel = getFlexibleSearchService().getModelByExample(regionModel);
            return regionModel.getIsocode();
        }
        catch (final Exception $e)
        {
            return "";
        }

    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService()
    {
        return (FlexibleSearchService) Registry.getApplicationContext().getBean("flexibleSearchService");
    }

    /**
     * @param flexibleSearchService
     *            the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService()
    {
        return (ModelService) Registry.getApplicationContext().getBean("modelService");
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
