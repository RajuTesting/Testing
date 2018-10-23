/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLFAQComponent SSLFAQComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLFAQComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLFAQComponent.faqTitle</code> attribute **/
	public static final String FAQTITLE = "faqTitle";
	/** Qualifier of the <code>SSLFAQComponent.faqDescription</code> attribute **/
	public static final String FAQDESCRIPTION = "faqDescription";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FAQTITLE, AttributeMode.INITIAL);
		tmp.put(FAQDESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQComponent.faqDescription</code> attribute.
	 * @return the faqDescription
	 */
	public CMSParagraphComponent getFaqDescription(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, FAQDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQComponent.faqDescription</code> attribute.
	 * @return the faqDescription
	 */
	public CMSParagraphComponent getFaqDescription()
	{
		return getFaqDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQComponent.faqDescription</code> attribute. 
	 * @param value the faqDescription
	 */
	public void setFaqDescription(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, FAQDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQComponent.faqDescription</code> attribute. 
	 * @param value the faqDescription
	 */
	public void setFaqDescription(final CMSParagraphComponent value)
	{
		setFaqDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQComponent.faqTitle</code> attribute.
	 * @return the faqTitle
	 */
	public String getFaqTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FAQTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFAQComponent.faqTitle</code> attribute.
	 * @return the faqTitle
	 */
	public String getFaqTitle()
	{
		return getFaqTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQComponent.faqTitle</code> attribute. 
	 * @param value the faqTitle
	 */
	public void setFaqTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FAQTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFAQComponent.faqTitle</code> attribute. 
	 * @param value the faqTitle
	 */
	public void setFaqTitle(final String value)
	{
		setFaqTitle( getSession().getSessionContext(), value );
	}
	
}
