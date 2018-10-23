/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SslCollectionsBannerComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SslCollectionsStyleComponent SslCollectionsStyleComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCollectionsStyleComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SslCollectionsStyleComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SslCollectionsStyleComponent.bigImageBanner</code> attribute **/
	public static final String BIGIMAGEBANNER = "bigImageBanner";
	/** Qualifier of the <code>SslCollectionsStyleComponent.smallImageBanner1</code> attribute **/
	public static final String SMALLIMAGEBANNER1 = "smallImageBanner1";
	/** Qualifier of the <code>SslCollectionsStyleComponent.smallImageBanner2</code> attribute **/
	public static final String SMALLIMAGEBANNER2 = "smallImageBanner2";
	/** Qualifier of the <code>SslCollectionsStyleComponent.styleType</code> attribute **/
	public static final String STYLETYPE = "styleType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(BIGIMAGEBANNER, AttributeMode.INITIAL);
		tmp.put(SMALLIMAGEBANNER1, AttributeMode.INITIAL);
		tmp.put(SMALLIMAGEBANNER2, AttributeMode.INITIAL);
		tmp.put(STYLETYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.bigImageBanner</code> attribute.
	 * @return the bigImageBanner - Big Image Banner for collection style component
	 */
	public SslCollectionsBannerComponent getBigImageBanner(final SessionContext ctx)
	{
		return (SslCollectionsBannerComponent)getProperty( ctx, BIGIMAGEBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.bigImageBanner</code> attribute.
	 * @return the bigImageBanner - Big Image Banner for collection style component
	 */
	public SslCollectionsBannerComponent getBigImageBanner()
	{
		return getBigImageBanner( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.bigImageBanner</code> attribute. 
	 * @param value the bigImageBanner - Big Image Banner for collection style component
	 */
	public void setBigImageBanner(final SessionContext ctx, final SslCollectionsBannerComponent value)
	{
		setProperty(ctx, BIGIMAGEBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.bigImageBanner</code> attribute. 
	 * @param value the bigImageBanner - Big Image Banner for collection style component
	 */
	public void setBigImageBanner(final SslCollectionsBannerComponent value)
	{
		setBigImageBanner( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.smallImageBanner1</code> attribute.
	 * @return the smallImageBanner1 - Small Image Banner1 for collection style component
	 */
	public SslCollectionsBannerComponent getSmallImageBanner1(final SessionContext ctx)
	{
		return (SslCollectionsBannerComponent)getProperty( ctx, SMALLIMAGEBANNER1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.smallImageBanner1</code> attribute.
	 * @return the smallImageBanner1 - Small Image Banner1 for collection style component
	 */
	public SslCollectionsBannerComponent getSmallImageBanner1()
	{
		return getSmallImageBanner1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.smallImageBanner1</code> attribute. 
	 * @param value the smallImageBanner1 - Small Image Banner1 for collection style component
	 */
	public void setSmallImageBanner1(final SessionContext ctx, final SslCollectionsBannerComponent value)
	{
		setProperty(ctx, SMALLIMAGEBANNER1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.smallImageBanner1</code> attribute. 
	 * @param value the smallImageBanner1 - Small Image Banner1 for collection style component
	 */
	public void setSmallImageBanner1(final SslCollectionsBannerComponent value)
	{
		setSmallImageBanner1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.smallImageBanner2</code> attribute.
	 * @return the smallImageBanner2 - Small Image Banner2 for collection style component
	 */
	public SslCollectionsBannerComponent getSmallImageBanner2(final SessionContext ctx)
	{
		return (SslCollectionsBannerComponent)getProperty( ctx, SMALLIMAGEBANNER2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.smallImageBanner2</code> attribute.
	 * @return the smallImageBanner2 - Small Image Banner2 for collection style component
	 */
	public SslCollectionsBannerComponent getSmallImageBanner2()
	{
		return getSmallImageBanner2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.smallImageBanner2</code> attribute. 
	 * @param value the smallImageBanner2 - Small Image Banner2 for collection style component
	 */
	public void setSmallImageBanner2(final SessionContext ctx, final SslCollectionsBannerComponent value)
	{
		setProperty(ctx, SMALLIMAGEBANNER2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.smallImageBanner2</code> attribute. 
	 * @param value the smallImageBanner2 - Small Image Banner2 for collection style component
	 */
	public void setSmallImageBanner2(final SslCollectionsBannerComponent value)
	{
		setSmallImageBanner2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.styleType</code> attribute.
	 * @return the styleType - display style for collection style component
	 */
	public EnumerationValue getStyleType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, STYLETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.styleType</code> attribute.
	 * @return the styleType - display style for collection style component
	 */
	public EnumerationValue getStyleType()
	{
		return getStyleType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.styleType</code> attribute. 
	 * @param value the styleType - display style for collection style component
	 */
	public void setStyleType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, STYLETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.styleType</code> attribute. 
	 * @param value the styleType - display style for collection style component
	 */
	public void setStyleType(final EnumerationValue value)
	{
		setStyleType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.title</code> attribute.
	 * @return the title - Title text for collection style component
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCollectionsStyleComponent.title</code> attribute.
	 * @return the title - Title text for collection style component
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.title</code> attribute. 
	 * @param value the title - Title text for collection style component
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCollectionsStyleComponent.title</code> attribute. 
	 * @param value the title - Title text for collection style component
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
}
