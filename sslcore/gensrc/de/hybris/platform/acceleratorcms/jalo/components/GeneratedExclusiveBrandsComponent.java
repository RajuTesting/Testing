/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package de.hybris.platform.acceleratorcms.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.acceleratorcms.jalo.components.ExclusiveBrandsComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.components.ExclusiveBrandsComponent ExclusiveBrandsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExclusiveBrandsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ExclusiveBrandsComponent.children</code> attribute **/
	public static final String CHILDREN = "children";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CHILDREN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExclusiveBrandsComponent.children</code> attribute.
	 * @return the children - Carousel Banner Components
	 */
	public List<ExclusiveBrandsComponent> getChildren(final SessionContext ctx)
	{
		List<ExclusiveBrandsComponent> coll = (List<ExclusiveBrandsComponent>)getProperty( ctx, CHILDREN);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExclusiveBrandsComponent.children</code> attribute.
	 * @return the children - Carousel Banner Components
	 */
	public List<ExclusiveBrandsComponent> getChildren()
	{
		return getChildren( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExclusiveBrandsComponent.children</code> attribute. 
	 * @param value the children - Carousel Banner Components
	 */
	public void setChildren(final SessionContext ctx, final List<ExclusiveBrandsComponent> value)
	{
		setProperty(ctx, CHILDREN,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExclusiveBrandsComponent.children</code> attribute. 
	 * @param value the children - Carousel Banner Components
	 */
	public void setChildren(final List<ExclusiveBrandsComponent> value)
	{
		setChildren( getSession().getSessionContext(), value );
	}
	
}
