/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.DealComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.DealCollectionComponent DealCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDealCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>DealCollectionComponent.dealHeader</code> attribute **/
	public static final String DEALHEADER = "dealHeader";
	/** Qualifier of the <code>DealCollectionComponent.dealSubHeading</code> attribute **/
	public static final String DEALSUBHEADING = "dealSubHeading";
	/** Qualifier of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute **/
	public static final String NOOFPRODUCTSONCOMPONENTWEB = "noOfProductsOnComponentWeb";
	/** Qualifier of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute **/
	public static final String NOOFPRODUCTSONCOMPONENTMOBILE = "noOfProductsOnComponentMobile";
	/** Qualifier of the <code>DealCollectionComponent.dealCollection</code> attribute **/
	public static final String DEALCOLLECTION = "dealCollection";
	/** Relation ordering override parameter constants for DealCollection2DealComponentRelation from ((sslcore))*/
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED = "relation.DealCollection2DealComponentRelation.source.ordered";
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_TGT_ORDERED = "relation.DealCollection2DealComponentRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for DealCollection2DealComponentRelation from ((sslcore))*/
	protected static String DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED = "relation.DealCollection2DealComponentRelation.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DEALHEADER, AttributeMode.INITIAL);
		tmp.put(DEALSUBHEADING, AttributeMode.INITIAL);
		tmp.put(NOOFPRODUCTSONCOMPONENTWEB, AttributeMode.INITIAL);
		tmp.put(NOOFPRODUCTSONCOMPONENTMOBILE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealCollection</code> attribute.
	 * @return the dealCollection
	 */
	public List<DealComponent> getDealCollection(final SessionContext ctx)
	{
		final List<DealComponent> items = getLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealCollection</code> attribute.
	 * @return the dealCollection
	 */
	public List<DealComponent> getDealCollection()
	{
		return getDealCollection( getSession().getSessionContext() );
	}
	
	public long getDealCollectionCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null
		);
	}
	
	public long getDealCollectionCount()
	{
		return getDealCollectionCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealCollection</code> attribute. 
	 * @param value the dealCollection
	 */
	public void setDealCollection(final SessionContext ctx, final List<DealComponent> value)
	{
		setLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealCollection</code> attribute. 
	 * @param value the dealCollection
	 */
	public void setDealCollection(final List<DealComponent> value)
	{
		setDealCollection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to dealCollection. 
	 * @param value the item to add to dealCollection
	 */
	public void addToDealCollection(final SessionContext ctx, final DealComponent value)
	{
		addLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to dealCollection. 
	 * @param value the item to add to dealCollection
	 */
	public void addToDealCollection(final DealComponent value)
	{
		addToDealCollection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from dealCollection. 
	 * @param value the item to remove from dealCollection
	 */
	public void removeFromDealCollection(final SessionContext ctx, final DealComponent value)
	{
		removeLinkedItems( 
			ctx,
			true,
			SslCoreConstants.Relations.DEALCOLLECTION2DEALCOMPONENTRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(DEALCOLLECTION2DEALCOMPONENTRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from dealCollection. 
	 * @param value the item to remove from dealCollection
	 */
	public void removeFromDealCollection(final DealComponent value)
	{
		removeFromDealCollection( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealHeader</code> attribute.
	 * @return the dealHeader - Deal Header
	 */
	public String getDealHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEALHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealHeader</code> attribute.
	 * @return the dealHeader - Deal Header
	 */
	public String getDealHeader()
	{
		return getDealHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealHeader</code> attribute. 
	 * @param value the dealHeader - Deal Header
	 */
	public void setDealHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEALHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealHeader</code> attribute. 
	 * @param value the dealHeader - Deal Header
	 */
	public void setDealHeader(final String value)
	{
		setDealHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealSubHeading</code> attribute.
	 * @return the dealSubHeading - Deal Header
	 */
	public String getDealSubHeading(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEALSUBHEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.dealSubHeading</code> attribute.
	 * @return the dealSubHeading - Deal Header
	 */
	public String getDealSubHeading()
	{
		return getDealSubHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealSubHeading</code> attribute. 
	 * @param value the dealSubHeading - Deal Header
	 */
	public void setDealSubHeading(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEALSUBHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.dealSubHeading</code> attribute. 
	 * @param value the dealSubHeading - Deal Header
	 */
	public void setDealSubHeading(final String value)
	{
		setDealSubHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute.
	 * @return the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public Integer getNoOfProductsOnComponentMobile(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NOOFPRODUCTSONCOMPONENTMOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute.
	 * @return the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public Integer getNoOfProductsOnComponentMobile()
	{
		return getNoOfProductsOnComponentMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @return the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public int getNoOfProductsOnComponentMobileAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNoOfProductsOnComponentMobile( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @return the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public int getNoOfProductsOnComponentMobileAsPrimitive()
	{
		return getNoOfProductsOnComponentMobileAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @param value the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public void setNoOfProductsOnComponentMobile(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NOOFPRODUCTSONCOMPONENTMOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @param value the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public void setNoOfProductsOnComponentMobile(final Integer value)
	{
		setNoOfProductsOnComponentMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @param value the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public void setNoOfProductsOnComponentMobile(final SessionContext ctx, final int value)
	{
		setNoOfProductsOnComponentMobile( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentMobile</code> attribute. 
	 * @param value the noOfProductsOnComponentMobile - Number of products to be displayed on component on mobile.
	 */
	public void setNoOfProductsOnComponentMobile(final int value)
	{
		setNoOfProductsOnComponentMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute.
	 * @return the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public Integer getNoOfProductsOnComponentWeb(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NOOFPRODUCTSONCOMPONENTWEB);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute.
	 * @return the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public Integer getNoOfProductsOnComponentWeb()
	{
		return getNoOfProductsOnComponentWeb( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @return the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public int getNoOfProductsOnComponentWebAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNoOfProductsOnComponentWeb( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @return the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public int getNoOfProductsOnComponentWebAsPrimitive()
	{
		return getNoOfProductsOnComponentWebAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @param value the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public void setNoOfProductsOnComponentWeb(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NOOFPRODUCTSONCOMPONENTWEB,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @param value the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public void setNoOfProductsOnComponentWeb(final Integer value)
	{
		setNoOfProductsOnComponentWeb( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @param value the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public void setNoOfProductsOnComponentWeb(final SessionContext ctx, final int value)
	{
		setNoOfProductsOnComponentWeb( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DealCollectionComponent.noOfProductsOnComponentWeb</code> attribute. 
	 * @param value the noOfProductsOnComponentWeb - Number of products to be displayed on component on web.
	 */
	public void setNoOfProductsOnComponentWeb(final int value)
	{
		setNoOfProductsOnComponentWeb( getSession().getSessionContext(), value );
	}
	
}
