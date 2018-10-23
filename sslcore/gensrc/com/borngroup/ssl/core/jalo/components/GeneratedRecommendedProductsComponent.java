/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.RecommendedProductsComponent RecommendedProductsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedRecommendedProductsComponent extends CMSImageComponent
{
	/** Qualifier of the <code>RecommendedProductsComponent.productCount</code> attribute **/
	public static final String PRODUCTCOUNT = "productCount";
	/** Qualifier of the <code>RecommendedProductsComponent.disableWeb</code> attribute **/
	public static final String DISABLEWEB = "disableWeb";
	/** Qualifier of the <code>RecommendedProductsComponent.disableMobile</code> attribute **/
	public static final String DISABLEMOBILE = "disableMobile";
	/** Qualifier of the <code>RecommendedProductsComponent.filterByStock</code> attribute **/
	public static final String FILTERBYSTOCK = "filterByStock";
	/** Qualifier of the <code>RecommendedProductsComponent.sortOrder</code> attribute **/
	public static final String SORTORDER = "sortOrder";
	/** Qualifier of the <code>RecommendedProductsComponent.recommendationType</code> attribute **/
	public static final String RECOMMENDATIONTYPE = "recommendationType";
	/** Qualifier of the <code>RecommendedProductsComponent.displayHeader</code> attribute **/
	public static final String DISPLAYHEADER = "displayHeader";
	/** Qualifier of the <code>RecommendedProductsComponent.countIA</code> attribute **/
	public static final String COUNTIA = "countIA";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSImageComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTCOUNT, AttributeMode.INITIAL);
		tmp.put(DISABLEWEB, AttributeMode.INITIAL);
		tmp.put(DISABLEMOBILE, AttributeMode.INITIAL);
		tmp.put(FILTERBYSTOCK, AttributeMode.INITIAL);
		tmp.put(SORTORDER, AttributeMode.INITIAL);
		tmp.put(RECOMMENDATIONTYPE, AttributeMode.INITIAL);
		tmp.put(DISPLAYHEADER, AttributeMode.INITIAL);
		tmp.put(COUNTIA, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.countIA</code> attribute.
	 * @return the countIA - Number Of Products to be asked from IA
	 */
	public Integer getCountIA(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, COUNTIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.countIA</code> attribute.
	 * @return the countIA - Number Of Products to be asked from IA
	 */
	public Integer getCountIA()
	{
		return getCountIA( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @return the countIA - Number Of Products to be asked from IA
	 */
	public int getCountIAAsPrimitive(final SessionContext ctx)
	{
		Integer value = getCountIA( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @return the countIA - Number Of Products to be asked from IA
	 */
	public int getCountIAAsPrimitive()
	{
		return getCountIAAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @param value the countIA - Number Of Products to be asked from IA
	 */
	public void setCountIA(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, COUNTIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @param value the countIA - Number Of Products to be asked from IA
	 */
	public void setCountIA(final Integer value)
	{
		setCountIA( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @param value the countIA - Number Of Products to be asked from IA
	 */
	public void setCountIA(final SessionContext ctx, final int value)
	{
		setCountIA( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.countIA</code> attribute. 
	 * @param value the countIA - Number Of Products to be asked from IA
	 */
	public void setCountIA(final int value)
	{
		setCountIA( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableMobile</code> attribute.
	 * @return the disableMobile - Boolean to disable the component on mobile
	 */
	public Boolean isDisableMobile(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DISABLEMOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableMobile</code> attribute.
	 * @return the disableMobile - Boolean to disable the component on mobile
	 */
	public Boolean isDisableMobile()
	{
		return isDisableMobile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @return the disableMobile - Boolean to disable the component on mobile
	 */
	public boolean isDisableMobileAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDisableMobile( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @return the disableMobile - Boolean to disable the component on mobile
	 */
	public boolean isDisableMobileAsPrimitive()
	{
		return isDisableMobileAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @param value the disableMobile - Boolean to disable the component on mobile
	 */
	public void setDisableMobile(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DISABLEMOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @param value the disableMobile - Boolean to disable the component on mobile
	 */
	public void setDisableMobile(final Boolean value)
	{
		setDisableMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @param value the disableMobile - Boolean to disable the component on mobile
	 */
	public void setDisableMobile(final SessionContext ctx, final boolean value)
	{
		setDisableMobile( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableMobile</code> attribute. 
	 * @param value the disableMobile - Boolean to disable the component on mobile
	 */
	public void setDisableMobile(final boolean value)
	{
		setDisableMobile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableWeb</code> attribute.
	 * @return the disableWeb - Boolean to disable the component
	 */
	public Boolean isDisableWeb(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DISABLEWEB);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableWeb</code> attribute.
	 * @return the disableWeb - Boolean to disable the component
	 */
	public Boolean isDisableWeb()
	{
		return isDisableWeb( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @return the disableWeb - Boolean to disable the component
	 */
	public boolean isDisableWebAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDisableWeb( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @return the disableWeb - Boolean to disable the component
	 */
	public boolean isDisableWebAsPrimitive()
	{
		return isDisableWebAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @param value the disableWeb - Boolean to disable the component
	 */
	public void setDisableWeb(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DISABLEWEB,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @param value the disableWeb - Boolean to disable the component
	 */
	public void setDisableWeb(final Boolean value)
	{
		setDisableWeb( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @param value the disableWeb - Boolean to disable the component
	 */
	public void setDisableWeb(final SessionContext ctx, final boolean value)
	{
		setDisableWeb( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.disableWeb</code> attribute. 
	 * @param value the disableWeb - Boolean to disable the component
	 */
	public void setDisableWeb(final boolean value)
	{
		setDisableWeb( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.displayHeader</code> attribute.
	 * @return the displayHeader - Type of Recommendation API
	 */
	public String getDisplayHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DISPLAYHEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.displayHeader</code> attribute.
	 * @return the displayHeader - Type of Recommendation API
	 */
	public String getDisplayHeader()
	{
		return getDisplayHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.displayHeader</code> attribute. 
	 * @param value the displayHeader - Type of Recommendation API
	 */
	public void setDisplayHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DISPLAYHEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.displayHeader</code> attribute. 
	 * @param value the displayHeader - Type of Recommendation API
	 */
	public void setDisplayHeader(final String value)
	{
		setDisplayHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.filterByStock</code> attribute.
	 * @return the filterByStock - Max Number Of Products to be shown
	 */
	public Boolean isFilterByStock(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, FILTERBYSTOCK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.filterByStock</code> attribute.
	 * @return the filterByStock - Max Number Of Products to be shown
	 */
	public Boolean isFilterByStock()
	{
		return isFilterByStock( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @return the filterByStock - Max Number Of Products to be shown
	 */
	public boolean isFilterByStockAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isFilterByStock( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @return the filterByStock - Max Number Of Products to be shown
	 */
	public boolean isFilterByStockAsPrimitive()
	{
		return isFilterByStockAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - Max Number Of Products to be shown
	 */
	public void setFilterByStock(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, FILTERBYSTOCK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - Max Number Of Products to be shown
	 */
	public void setFilterByStock(final Boolean value)
	{
		setFilterByStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - Max Number Of Products to be shown
	 */
	public void setFilterByStock(final SessionContext ctx, final boolean value)
	{
		setFilterByStock( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.filterByStock</code> attribute. 
	 * @param value the filterByStock - Max Number Of Products to be shown
	 */
	public void setFilterByStock(final boolean value)
	{
		setFilterByStock( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.productCount</code> attribute.
	 * @return the productCount - Max Number Of Products to be shown
	 */
	public Integer getProductCount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PRODUCTCOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.productCount</code> attribute.
	 * @return the productCount - Max Number Of Products to be shown
	 */
	public Integer getProductCount()
	{
		return getProductCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @return the productCount - Max Number Of Products to be shown
	 */
	public int getProductCountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getProductCount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @return the productCount - Max Number Of Products to be shown
	 */
	public int getProductCountAsPrimitive()
	{
		return getProductCountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @param value the productCount - Max Number Of Products to be shown
	 */
	public void setProductCount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PRODUCTCOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @param value the productCount - Max Number Of Products to be shown
	 */
	public void setProductCount(final Integer value)
	{
		setProductCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @param value the productCount - Max Number Of Products to be shown
	 */
	public void setProductCount(final SessionContext ctx, final int value)
	{
		setProductCount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.productCount</code> attribute. 
	 * @param value the productCount - Max Number Of Products to be shown
	 */
	public void setProductCount(final int value)
	{
		setProductCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.recommendationType</code> attribute.
	 * @return the recommendationType - Type of Recommendation API
	 */
	public String getRecommendationType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RECOMMENDATIONTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.recommendationType</code> attribute.
	 * @return the recommendationType - Type of Recommendation API
	 */
	public String getRecommendationType()
	{
		return getRecommendationType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.recommendationType</code> attribute. 
	 * @param value the recommendationType - Type of Recommendation API
	 */
	public void setRecommendationType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RECOMMENDATIONTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.recommendationType</code> attribute. 
	 * @param value the recommendationType - Type of Recommendation API
	 */
	public void setRecommendationType(final String value)
	{
		setRecommendationType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.sortOrder</code> attribute.
	 * @return the sortOrder - Sort order of Products to be shown
	 */
	public String getSortOrder(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SORTORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecommendedProductsComponent.sortOrder</code> attribute.
	 * @return the sortOrder - Sort order of Products to be shown
	 */
	public String getSortOrder()
	{
		return getSortOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.sortOrder</code> attribute. 
	 * @param value the sortOrder - Sort order of Products to be shown
	 */
	public void setSortOrder(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SORTORDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecommendedProductsComponent.sortOrder</code> attribute. 
	 * @param value the sortOrder - Sort order of Products to be shown
	 */
	public void setSortOrder(final String value)
	{
		setSortOrder( getSession().getSessionContext(), value );
	}
	
}
