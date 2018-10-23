/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem BluedartResponseMapping}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBluedartResponseMapping extends GenericItem
{
	/** Qualifier of the <code>BluedartResponseMapping.trackAPIResponse</code> attribute **/
	public static final String TRACKAPIRESPONSE = "trackAPIResponse";
	/** Qualifier of the <code>BluedartResponseMapping.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>BluedartResponseMapping.returnStatus</code> attribute **/
	public static final String RETURNSTATUS = "returnStatus";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(TRACKAPIRESPONSE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(RETURNSTATUS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBluedartResponseMapping.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedBluedartResponseMapping.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.returnStatus</code> attribute.
	 * @return the returnStatus
	 */
	public EnumerationValue getReturnStatus(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, RETURNSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.returnStatus</code> attribute.
	 * @return the returnStatus
	 */
	public EnumerationValue getReturnStatus()
	{
		return getReturnStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.returnStatus</code> attribute. 
	 * @param value the returnStatus
	 */
	public void setReturnStatus(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, RETURNSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.returnStatus</code> attribute. 
	 * @param value the returnStatus
	 */
	public void setReturnStatus(final EnumerationValue value)
	{
		setReturnStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.trackAPIResponse</code> attribute.
	 * @return the trackAPIResponse
	 */
	public String getTrackAPIResponse(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TRACKAPIRESPONSE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BluedartResponseMapping.trackAPIResponse</code> attribute.
	 * @return the trackAPIResponse
	 */
	public String getTrackAPIResponse()
	{
		return getTrackAPIResponse( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.trackAPIResponse</code> attribute. 
	 * @param value the trackAPIResponse
	 */
	public void setTrackAPIResponse(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TRACKAPIRESPONSE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BluedartResponseMapping.trackAPIResponse</code> attribute. 
	 * @param value the trackAPIResponse
	 */
	public void setTrackAPIResponse(final String value)
	{
		setTrackAPIResponse( getSession().getSessionContext(), value );
	}
	
}
