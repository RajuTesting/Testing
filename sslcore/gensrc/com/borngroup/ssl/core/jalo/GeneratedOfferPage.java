/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.pages.ContentPage;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.pages.ContentPage OfferPage}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOfferPage extends ContentPage
{
	/** Qualifier of the <code>OfferPage.sequenceID</code> attribute **/
	public static final String SEQUENCEID = "sequenceID";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ContentPage.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SEQUENCEID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferPage.sequenceID</code> attribute.
	 * @return the sequenceID - Sequence ID
	 */
	public Integer getSequenceID(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, SEQUENCEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferPage.sequenceID</code> attribute.
	 * @return the sequenceID - Sequence ID
	 */
	public Integer getSequenceID()
	{
		return getSequenceID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @return the sequenceID - Sequence ID
	 */
	public int getSequenceIDAsPrimitive(final SessionContext ctx)
	{
		Integer value = getSequenceID( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @return the sequenceID - Sequence ID
	 */
	public int getSequenceIDAsPrimitive()
	{
		return getSequenceIDAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @param value the sequenceID - Sequence ID
	 */
	public void setSequenceID(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, SEQUENCEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @param value the sequenceID - Sequence ID
	 */
	public void setSequenceID(final Integer value)
	{
		setSequenceID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @param value the sequenceID - Sequence ID
	 */
	public void setSequenceID(final SessionContext ctx, final int value)
	{
		setSequenceID( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OfferPage.sequenceID</code> attribute. 
	 * @param value the sequenceID - Sequence ID
	 */
	public void setSequenceID(final int value)
	{
		setSequenceID( getSession().getSessionContext(), value );
	}
	
}
