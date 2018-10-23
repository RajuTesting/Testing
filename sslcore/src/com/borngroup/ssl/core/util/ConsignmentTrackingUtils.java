/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.HistoricalAwbNumberModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Techouts
 *
 *
 */
public class ConsignmentTrackingUtils {


    private ConsignmentTrackingUtils() {
        super();
    }

    /**
     * @description this method used to save the awbNumber in History
     *
     * @param awbNumber
     * @param consignment
     */
    public static void saveInAwbNumberHistory(final String awbNumber, final ConsignmentModel consignment) {
        final List<HistoricalAwbNumberModel> historicalAwbnumbers = new ArrayList<>(
                consignment.getHistoricalAwbNumbers());
        final HistoricalAwbNumberModel historicalAwbnumber = getModelService().create(HistoricalAwbNumberModel.class);
        historicalAwbnumber.setHistoricalAwbNumber(awbNumber);
        historicalAwbnumbers.add(historicalAwbnumber);
        consignment.setHistoricalAwbNumbers(historicalAwbnumbers);
    }

    private static ModelService getModelService() {
        return Registry.getApplicationContext().getBean("modelService", ModelService.class);
    }
}
