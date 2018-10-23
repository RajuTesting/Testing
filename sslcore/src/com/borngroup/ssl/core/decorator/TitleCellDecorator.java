/**
 *
 */
package com.borngroup.ssl.core.decorator;

import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;


/**
 * @author Suresh
 *
 */
public class TitleCellDecorator implements CSVCellDecorator
{

    @Override
    public String decorate(final int position, final Map<Integer, String> srcLine)
    {
        String parsedValue = srcLine.get(position);
        if (null != parsedValue)
        {
            parsedValue = parsedValue.toLowerCase();
        }
        return parsedValue;
    }
}
