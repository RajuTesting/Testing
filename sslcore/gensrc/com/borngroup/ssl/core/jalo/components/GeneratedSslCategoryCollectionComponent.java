/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CategoryListingComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.SslCategoryCollectionComponent SslCategoryCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslCategoryCollectionComponent extends CMSImageComponent
{
	/** Qualifier of the <code>SslCategoryCollectionComponent.SslTabsList</code> attribute **/
	public static final String SSLTABSLIST = "SslTabsList";
	/** Qualifier of the <code>SslCategoryCollectionComponent.SslCategoryList</code> attribute **/
	public static final String SSLCATEGORYLIST = "SslCategoryList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SSLTABSLIST, AttributeMode.INITIAL);
		tmp.put(SSLCATEGORYLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryCollectionComponent.SslCategoryList</code> attribute.
	 * @return the SslCategoryList - List Of Components
	 */
	public List<CategoryListingComponent> getSslCategoryList(final SessionContext ctx)
	{
		List<CategoryListingComponent> coll = (List<CategoryListingComponent>)getProperty( ctx, SSLCATEGORYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryCollectionComponent.SslCategoryList</code> attribute.
	 * @return the SslCategoryList - List Of Components
	 */
	public List<CategoryListingComponent> getSslCategoryList()
	{
		return getSslCategoryList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryCollectionComponent.SslCategoryList</code> attribute. 
	 * @param value the SslCategoryList - List Of Components
	 */
	public void setSslCategoryList(final SessionContext ctx, final List<CategoryListingComponent> value)
	{
		setProperty(ctx, SSLCATEGORYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryCollectionComponent.SslCategoryList</code> attribute. 
	 * @param value the SslCategoryList - List Of Components
	 */
	public void setSslCategoryList(final List<CategoryListingComponent> value)
	{
		setSslCategoryList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryCollectionComponent.SslTabsList</code> attribute.
	 * @return the SslTabsList - List Of Links
	 */
	public List<CMSLinkComponent> getSslTabsList(final SessionContext ctx)
	{
		List<CMSLinkComponent> coll = (List<CMSLinkComponent>)getProperty( ctx, SSLTABSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslCategoryCollectionComponent.SslTabsList</code> attribute.
	 * @return the SslTabsList - List Of Links
	 */
	public List<CMSLinkComponent> getSslTabsList()
	{
		return getSslTabsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryCollectionComponent.SslTabsList</code> attribute. 
	 * @param value the SslTabsList - List Of Links
	 */
	public void setSslTabsList(final SessionContext ctx, final List<CMSLinkComponent> value)
	{
		setProperty(ctx, SSLTABSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslCategoryCollectionComponent.SslTabsList</code> attribute. 
	 * @param value the SslTabsList - List Of Links
	 */
	public void setSslTabsList(final List<CMSLinkComponent> value)
	{
		setSslTabsList( getSession().getSessionContext(), value );
	}
	
}
