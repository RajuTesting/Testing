/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.FCCCardComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.ResponsiveShopByCategoryComponent ResponsiveShopByCategoryComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedResponsiveShopByCategoryComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ResponsiveShopByCategoryComponent.shopByCategoryList</code> attribute **/
	public static final String SHOPBYCATEGORYLIST = "shopByCategoryList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SHOPBYCATEGORYLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveShopByCategoryComponent.shopByCategoryList</code> attribute.
	 * @return the shopByCategoryList
	 */
	public List<FCCCardComponent> getShopByCategoryList(final SessionContext ctx)
	{
		List<FCCCardComponent> coll = (List<FCCCardComponent>)getProperty( ctx, SHOPBYCATEGORYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ResponsiveShopByCategoryComponent.shopByCategoryList</code> attribute.
	 * @return the shopByCategoryList
	 */
	public List<FCCCardComponent> getShopByCategoryList()
	{
		return getShopByCategoryList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveShopByCategoryComponent.shopByCategoryList</code> attribute. 
	 * @param value the shopByCategoryList
	 */
	public void setShopByCategoryList(final SessionContext ctx, final List<FCCCardComponent> value)
	{
		setProperty(ctx, SHOPBYCATEGORYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ResponsiveShopByCategoryComponent.shopByCategoryList</code> attribute. 
	 * @param value the shopByCategoryList
	 */
	public void setShopByCategoryList(final List<FCCCardComponent> value)
	{
		setShopByCategoryList( getSession().getSessionContext(), value );
	}
	
}
