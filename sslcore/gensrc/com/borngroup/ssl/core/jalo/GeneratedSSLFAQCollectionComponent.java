/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLFAQComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLFAQCollectionComponent SSLFAQCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLFAQCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLFAQCollectionComponent.faqCollectionTitle</code> attribute **/
	public static final String FAQCOLLECTIONTITLE = "faqCollectionTitle";
	/** Qualifier of the <code>SSLFAQCollectionComponent.faqCollectionComponent</code> attribute **/
	public static final String FAQCOLLECTIONCOMPONENT = "faqCollectionComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FAQCOLLECTIONTITLE, AttributeMode.INITIAL);
		tmp.put(FAQCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQCollectionComponent.faqCollectionComponent</code> attribute.
	 * @return the faqCollectionComponent
	 */
	public List<SSLFAQComponent> getFaqCollectionComponent(final SessionContext ctx)
	{
		List<SSLFAQComponent> coll = (List<SSLFAQComponent>)getProperty( ctx, FAQCOLLECTIONCOMPONENT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQCollectionComponent.faqCollectionComponent</code> attribute.
	 * @return the faqCollectionComponent
	 */
	public List<SSLFAQComponent> getFaqCollectionComponent()
	{
		return getFaqCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQCollectionComponent.faqCollectionComponent</code> attribute. 
	 * @param value the faqCollectionComponent
	 */
	public void setFaqCollectionComponent(final SessionContext ctx, final List<SSLFAQComponent> value)
	{
		setProperty(ctx, FAQCOLLECTIONCOMPONENT,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQCollectionComponent.faqCollectionComponent</code> attribute. 
	 * @param value the faqCollectionComponent
	 */
	public void setFaqCollectionComponent(final List<SSLFAQComponent> value)
	{
		setFaqCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQCollectionComponent.faqCollectionTitle</code> attribute.
	 * @return the faqCollectionTitle
	 */
	public String getFaqCollectionTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FAQCOLLECTIONTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQCollectionComponent.faqCollectionTitle</code> attribute.
	 * @return the faqCollectionTitle
	 */
	public String getFaqCollectionTitle()
	{
		return getFaqCollectionTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQCollectionComponent.faqCollectionTitle</code> attribute. 
	 * @param value the faqCollectionTitle
	 */
	public void setFaqCollectionTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FAQCOLLECTIONTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQCollectionComponent.faqCollectionTitle</code> attribute. 
	 * @param value the faqCollectionTitle
	 */
	public void setFaqCollectionTitle(final String value)
	{
		setFaqCollectionTitle( getSession().getSessionContext(), value );
	}
	
}
