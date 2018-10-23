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
public class NameCellDecorator implements CSVCellDecorator {

	@Override
	public String decorate(final int position, final Map<Integer, String> srcLine) {
		final String firstName = srcLine.get(position);
		final String midddleName = srcLine.get(position + 1);
		final String lastName = srcLine.get(position + 2);

		String returnValue = firstName;
		if (midddleName.length() > 0) {
			returnValue = returnValue + " " + midddleName;
		}
		if (lastName.length() > 0) {
			returnValue = returnValue + " " + lastName;
		}

		return returnValue;
	}
}
