/**
 *
 */
package com.borngroup.ssl.core.decorator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.EanModel;
import com.borngroup.ssl.core.services.EanService;

/**
 * @author raju.p@techouts.com
 *
 */
public class EanCellDecorator implements CSVCellDecorator
{
    private static final Logger LOGGER = Logger
            .getLogger(EanCellDecorator.class);
    private static final String SEPERATOR = ",";

    @Override
    public String decorate(final int position,
            final Map<Integer, String> srcLine)
    {
        try
        {
            final String parsedValue = srcLine.get(position);
            if (StringUtils.isEmpty(parsedValue))
            {
                return StringUtils.EMPTY;
            }
            final String eans[] = parsedValue.split(SEPERATOR);
            final StringBuilder eanList = new StringBuilder();
            for (final String ean : eans)
            {
                if(StringUtils.isBlank(ean) || ean.matches("[.:]"))
                {
                    continue;
                }
                try
                {
                    final EanModel eanModel = ((EanService) Registry
                            .getApplicationContext().getBean("eanService"))
                            .getOrCreateEanByNumber(ean);

                    if (eanModel == null)
                    {
                        continue;
                    }
                    eanList.append(eanModel.getEanNumber() + SEPERATOR);
                }
                catch (final Exception exception)
                {
                    LOGGER.warn("Exception while ean create/update "
                            + exception);
                }
            }
            if (StringUtils.isEmpty(eanList.toString()))
            {
                return StringUtils.EMPTY;
            }
            return eanList.substring(0, eanList.length() - 1);
        }
        catch (final Exception exception)
        {
            LOGGER.warn(exception);
        }
        return StringUtils.EMPTY;
    }
}