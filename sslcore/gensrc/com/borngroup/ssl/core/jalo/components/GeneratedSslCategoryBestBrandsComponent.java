/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.CMSBlogComponent;
import com.borngroup.ssl.core.jalo.components.CMSLinkImageTextFavoritesComponent;
import com.borngroup.ssl.core.jalo.components.OurFavoritesBrandsComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslCategoryBestBrandsComponent SslCategoryBestBrandsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCategoryBestBrandsComponent extends CMSImageComponent
{
	/** Qualifier of the <code>SslCategoryBestBrandsComponent.bestBrandsLogos</code> attribute **/
	public static final String BESTBRANDSLOGOS = "bestBrandsLogos";
	/** Qualifier of the <code>SslCategoryBestBrandsComponent.trends</code> attribute **/
	public static final String TRENDS = "trends";
	/** Qualifier of the <code>SslCategoryBestBrandsComponent.blog</code> attribute **/
	public static final String BLOG = "blog";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BESTBRANDSLOGOS, AttributeMode.INITIAL);
		tmp.put(TRENDS, AttributeMode.INITIAL);
		tmp.put(BLOG, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.bestBrandsLogos</code> attribute.
	 * @return the bestBrandsLogos - List Of Brands
	 */
	public OurFavoritesBrandsComponent getBestBrandsLogos(final SessionContext ctx)
	{
		return (OurFavoritesBrandsComponent)getProperty( ctx, BESTBRANDSLOGOS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.bestBrandsLogos</code> attribute.
	 * @return the bestBrandsLogos - List Of Brands
	 */
	public OurFavoritesBrandsComponent getBestBrandsLogos()
	{
		return getBestBrandsLogos( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.bestBrandsLogos</code> attribute. 
	 * @param value the bestBrandsLogos - List Of Brands
	 */
	public void setBestBrandsLogos(final SessionContext ctx, final OurFavoritesBrandsComponent value)
	{
		setProperty(ctx, BESTBRANDSLOGOS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.bestBrandsLogos</code> attribute. 
	 * @param value the bestBrandsLogos - List Of Brands
	 */
	public void setBestBrandsLogos(final OurFavoritesBrandsComponent value)
	{
		setBestBrandsLogos( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.blog</code> attribute.
	 * @return the blog - Blog Section
	 */
	public CMSBlogComponent getBlog(final SessionContext ctx)
	{
		return (CMSBlogComponent)getProperty( ctx, BLOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.blog</code> attribute.
	 * @return the blog - Blog Section
	 */
	public CMSBlogComponent getBlog()
	{
		return getBlog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.blog</code> attribute. 
	 * @param value the blog - Blog Section
	 */
	public void setBlog(final SessionContext ctx, final CMSBlogComponent value)
	{
		setProperty(ctx, BLOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.blog</code> attribute. 
	 * @param value the blog - Blog Section
	 */
	public void setBlog(final CMSBlogComponent value)
	{
		setBlog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.trends</code> attribute.
	 * @return the trends - Trend Section
	 */
	public CMSLinkImageTextFavoritesComponent getTrends(final SessionContext ctx)
	{
		return (CMSLinkImageTextFavoritesComponent)getProperty( ctx, TRENDS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryBestBrandsComponent.trends</code> attribute.
	 * @return the trends - Trend Section
	 */
	public CMSLinkImageTextFavoritesComponent getTrends()
	{
		return getTrends( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.trends</code> attribute. 
	 * @param value the trends - Trend Section
	 */
	public void setTrends(final SessionContext ctx, final CMSLinkImageTextFavoritesComponent value)
	{
		setProperty(ctx, TRENDS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryBestBrandsComponent.trends</code> attribute. 
	 * @param value the trends - Trend Section
	 */
	public void setTrends(final CMSLinkImageTextFavoritesComponent value)
	{
		setTrends( getSession().getSessionContext(), value );
	}
	
}
