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
public class CountryCellDecorator implements CSVCellDecorator {

	@Override
	public String decorate(final int position, final Map<Integer, String> srcLine) {
		String parsedValue = srcLine.get(position);
		parsedValue = parsedValue.toUpperCase();

		return parsedValue;
	}
}
