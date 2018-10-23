/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Midhun Bose 			SSL-19 					Base Version
 *
 */
package com.borngroup.ssl.core.dao;

/**
 * @author midhun.bose
 * 
 */
public interface SSLColourLookupDao
{
	public String getColourCodeForInputString(String lookupString);

	public String getColourCodeForActualCode(String lookupString);
	
	public String getColourFamilyForActualCode(String lookupString);

	public String getColourFamilyForAltDesc(String lookupString);
}
