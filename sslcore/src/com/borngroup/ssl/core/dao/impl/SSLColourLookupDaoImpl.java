/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.List;

import com.borngroup.ssl.core.dao.SSLColourLookupDao;
import com.borngroup.ssl.core.model.ColorMappingModel;


/**
 * @author midhun.bose
 * 
 */
public class SSLColourLookupDaoImpl extends DefaultGenericDao<ColorMappingModel> implements SSLColourLookupDao
{

	/**
	 * 
	 */
	public SSLColourLookupDaoImpl()
	{
		super("ColorMapping");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Pass ColourCD to lookup and get Alt_colour_Desc of matching row from ColorMapping table if such a row exists.
	 * Else return empty string
	 */
	@Override
	public String getColourCodeForInputString(final String lookupString)
	{
		final List<ColorMappingModel> colours = find(Collections.singletonMap("colourCode", lookupString));
		return (colours.iterator().hasNext() ? colours.iterator().next().getAltColourDesc() : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Pass Alt_colour_Desc to lookup and get Alt_colour_Desc of matching row from ColorMapping table if such a row
	 * exists. Else return empty string
	 */
	@Override
	public String getColourCodeForActualCode(final String lookupString)
	{
		final List<ColorMappingModel> colours = find(Collections.singletonMap("altColourDesc", lookupString));
		return (colours.iterator().hasNext() ? colours.iterator().next().getAltColourDesc() : "");
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see com.borngroup.ssl.core.dao.SSLColourLookupDao#
	 * getColourFamilyForActualCode(java.lang.String)
	 */
	@Override
	public String getColourFamilyForActualCode(final String lookupString) {
		final List<ColorMappingModel> colours = find(Collections.singletonMap("colourCode", lookupString));
		return (colours.iterator().hasNext() ? colours.iterator().next().getColorFamily() : "");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.borngroup.ssl.core.dao.SSLColourLookupDao#getColourFamilyForAltDesc(
	 * java.lang.String)
	 */
	@Override
	public String getColourFamilyForAltDesc(final String lookupString) {
		final List<ColorMappingModel> colours = find(Collections.singletonMap("altColourDesc", lookupString));
		return (colours.iterator().hasNext() ? colours.iterator().next().getColorFamily() : "");
	}
}