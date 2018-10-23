/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLStyleHubComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLStyleHubCollectionComponent SSLStyleHubCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLStyleHubCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSLStyleHubCollectionComponent.styleHubCollectionTitle</code> attribute **/
	public static final String STYLEHUBCOLLECTIONTITLE = "styleHubCollectionTitle";
	/** Qualifier of the <code>SSLStyleHubCollectionComponent.styleHubCollectionSubTitle</code> attribute **/
	public static final String STYLEHUBCOLLECTIONSUBTITLE = "styleHubCollectionSubTitle";
	/** Qualifier of the <code>SSLStyleHubCollectionComponent.styleHubCollectionComponent</code> attribute **/
	public static final String STYLEHUBCOLLECTIONCOMPONENT = "styleHubCollectionComponent";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(STYLEHUBCOLLECTIONTITLE, AttributeMode.INITIAL);
		tmp.put(STYLEHUBCOLLECTIONSUBTITLE, AttributeMode.INITIAL);
		tmp.put(STYLEHUBCOLLECTIONCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionComponent</code> attribute.
	 * @return the styleHubCollectionComponent
	 */
	public List<SSLStyleHubComponent> getStyleHubCollectionComponent(final SessionContext ctx)
	{
		List<SSLStyleHubComponent> coll = (List<SSLStyleHubComponent>)getProperty( ctx, STYLEHUBCOLLECTIONCOMPONENT);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionComponent</code> attribute.
	 * @return the styleHubCollectionComponent
	 */
	public List<SSLStyleHubComponent> getStyleHubCollectionComponent()
	{
		return getStyleHubCollectionComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionComponent</code> attribute. 
	 * @param value the styleHubCollectionComponent
	 */
	public void setStyleHubCollectionComponent(final SessionContext ctx, final List<SSLStyleHubComponent> value)
	{
		setProperty(ctx, STYLEHUBCOLLECTIONCOMPONENT,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionComponent</code> attribute. 
	 * @param value the styleHubCollectionComponent
	 */
	public void setStyleHubCollectionComponent(final List<SSLStyleHubComponent> value)
	{
		setStyleHubCollectionComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionSubTitle</code> attribute.
	 * @return the styleHubCollectionSubTitle
	 */
	public String getStyleHubCollectionSubTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLEHUBCOLLECTIONSUBTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionSubTitle</code> attribute.
	 * @return the styleHubCollectionSubTitle
	 */
	public String getStyleHubCollectionSubTitle()
	{
		return getStyleHubCollectionSubTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionSubTitle</code> attribute. 
	 * @param value the styleHubCollectionSubTitle
	 */
	public void setStyleHubCollectionSubTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLEHUBCOLLECTIONSUBTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionSubTitle</code> attribute. 
	 * @param value the styleHubCollectionSubTitle
	 */
	public void setStyleHubCollectionSubTitle(final String value)
	{
		setStyleHubCollectionSubTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionTitle</code> attribute.
	 * @return the styleHubCollectionTitle
	 */
	public String getStyleHubCollectionTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLEHUBCOLLECTIONTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionTitle</code> attribute.
	 * @return the styleHubCollectionTitle
	 */
	public String getStyleHubCollectionTitle()
	{
		return getStyleHubCollectionTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionTitle</code> attribute. 
	 * @param value the styleHubCollectionTitle
	 */
	public void setStyleHubCollectionTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLEHUBCOLLECTIONTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLStyleHubCollectionComponent.styleHubCollectionTitle</code> attribute. 
	 * @param value the styleHubCollectionTitle
	 */
	public void setStyleHubCollectionTitle(final String value)
	{
		setStyleHubCollectionTitle( getSession().getSessionContext(), value );
	}
	
}
