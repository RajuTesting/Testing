/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLProductDetailBlogsComponent SSLProductDetailBlogsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductDetailBlogsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLProductDetailBlogsComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute **/
	public static final String NUMBEROFBLOGS = "numberOfBlogs";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(NUMBEROFBLOGS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute.
	 * @return the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public Integer getNumberOfBlogs(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NUMBEROFBLOGS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute.
	 * @return the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public Integer getNumberOfBlogs()
	{
		return getNumberOfBlogs( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @return the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public int getNumberOfBlogsAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNumberOfBlogs( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @return the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public int getNumberOfBlogsAsPrimitive()
	{
		return getNumberOfBlogsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @param value the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public void setNumberOfBlogs(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NUMBEROFBLOGS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @param value the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public void setNumberOfBlogs(final Integer value)
	{
		setNumberOfBlogs( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @param value the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public void setNumberOfBlogs(final SessionContext ctx, final int value)
	{
		setNumberOfBlogs( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.numberOfBlogs</code> attribute. 
	 * @param value the numberOfBlogs - Number of blogs to be shown on PDP
	 */
	public void setNumberOfBlogs(final int value)
	{
		setNumberOfBlogs( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.title</code> attribute.
	 * @return the title - Title of PDP WordPress Blogs Component
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductDetailBlogsComponent.title</code> attribute.
	 * @return the title - Title of PDP WordPress Blogs Component
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.title</code> attribute. 
	 * @param value the title - Title of PDP WordPress Blogs Component
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductDetailBlogsComponent.title</code> attribute. 
	 * @param value the title - Title of PDP WordPress Blogs Component
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
