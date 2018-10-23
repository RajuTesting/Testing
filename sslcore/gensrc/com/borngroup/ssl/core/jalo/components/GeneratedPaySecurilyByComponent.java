/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSImageLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.PaySecurilyByComponent PaySecurilyByComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPaySecurilyByComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>PaySecurilyByComponent.paySecurilyList</code> attribute **/
	public static final String PAYSECURILYLIST = "paySecurilyList";
	/** Qualifier of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute **/
	public static final String ISCHECKOUTPAGE = "isCheckoutPage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PAYSECURILYLIST, AttributeMode.INITIAL);
		tmp.put(ISCHECKOUTPAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute.
	 * @return the isCheckoutPage
	 */
	public Boolean isIsCheckoutPage(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISCHECKOUTPAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute.
	 * @return the isCheckoutPage
	 */
	public Boolean isIsCheckoutPage()
	{
		return isIsCheckoutPage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @return the isCheckoutPage
	 */
	public boolean isIsCheckoutPageAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsCheckoutPage( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @return the isCheckoutPage
	 */
	public boolean isIsCheckoutPageAsPrimitive()
	{
		return isIsCheckoutPageAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @param value the isCheckoutPage
	 */
	public void setIsCheckoutPage(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISCHECKOUTPAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @param value the isCheckoutPage
	 */
	public void setIsCheckoutPage(final Boolean value)
	{
		setIsCheckoutPage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @param value the isCheckoutPage
	 */
	public void setIsCheckoutPage(final SessionContext ctx, final boolean value)
	{
		setIsCheckoutPage( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.isCheckoutPage</code> attribute. 
	 * @param value the isCheckoutPage
	 */
	public void setIsCheckoutPage(final boolean value)
	{
		setIsCheckoutPage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.paySecurilyList</code> attribute.
	 * @return the paySecurilyList
	 */
	public List<CMSImageLinkComponent> getPaySecurilyList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, PAYSECURILYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaySecurilyByComponent.paySecurilyList</code> attribute.
	 * @return the paySecurilyList
	 */
	public List<CMSImageLinkComponent> getPaySecurilyList()
	{
		return getPaySecurilyList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.paySecurilyList</code> attribute. 
	 * @param value the paySecurilyList
	 */
	public void setPaySecurilyList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, PAYSECURILYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaySecurilyByComponent.paySecurilyList</code> attribute. 
	 * @param value the paySecurilyList
	 */
	public void setPaySecurilyList(final List<CMSImageLinkComponent> value)
	{
		setPaySecurilyList( getSession().getSessionContext(), value );
	}
	
}
