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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLTestimonialComponent SSLTestimonialComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLTestimonialComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLTestimonialComponent.testimonialTitle</code> attribute **/
	public static final String TESTIMONIALTITLE = "testimonialTitle";
	/** Qualifier of the <code>SSLTestimonialComponent.testimonialDescription</code> attribute **/
	public static final String TESTIMONIALDESCRIPTION = "testimonialDescription";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TESTIMONIALTITLE, AttributeMode.INITIAL);
		tmp.put(TESTIMONIALDESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialComponent.testimonialDescription</code> attribute.
	 * @return the testimonialDescription
	 */
	public CMSParagraphComponent getTestimonialDescription(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, TESTIMONIALDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialComponent.testimonialDescription</code> attribute.
	 * @return the testimonialDescription
	 */
	public CMSParagraphComponent getTestimonialDescription()
	{
		return getTestimonialDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialComponent.testimonialDescription</code> attribute. 
	 * @param value the testimonialDescription
	 */
	public void setTestimonialDescription(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, TESTIMONIALDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialComponent.testimonialDescription</code> attribute. 
	 * @param value the testimonialDescription
	 */
	public void setTestimonialDescription(final CMSParagraphComponent value)
	{
		setTestimonialDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialComponent.testimonialTitle</code> attribute.
	 * @return the testimonialTitle
	 */
	public String getTestimonialTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TESTIMONIALTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLTestimonialComponent.testimonialTitle</code> attribute.
	 * @return the testimonialTitle
	 */
	public String getTestimonialTitle()
	{
		return getTestimonialTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialComponent.testimonialTitle</code> attribute. 
	 * @param value the testimonialTitle
	 */
	public void setTestimonialTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TESTIMONIALTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLTestimonialComponent.testimonialTitle</code> attribute. 
	 * @param value the testimonialTitle
	 */
	public void setTestimonialTitle(final String value)
	{
		setTestimonialTitle( getSession().getSessionContext(), value );
	}
	
}
