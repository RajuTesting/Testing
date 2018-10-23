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
public class GenderCellDecorator implements CSVCellDecorator
{
    @Override
    public String decorate(final int position, final Map<Integer, String> srcLine)
    {
        final String parsedValue = srcLine.get(position);
        if (null != parsedValue)
        {
            if (parsedValue.equalsIgnoreCase("m"))
            {
                return "MALE";
            }
            else if (parsedValue.equalsIgnoreCase("f"))
            {
                return "FEMALE";
            }
            else
            {
                return parsedValue;
            }
        }
        return parsedValue;

    }
}
