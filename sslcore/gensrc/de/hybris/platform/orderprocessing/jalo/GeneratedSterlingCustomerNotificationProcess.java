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
package de.hybris.platform.orderprocessing.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.processengine.jalo.BusinessProcess;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.orderprocessing.jalo.SterlingCustomerNotificationProcess SterlingCustomerNotificationProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSterlingCustomerNotificationProcess extends BusinessProcess
{
	/** Qualifier of the <code>SterlingCustomerNotificationProcess.CustomerNotificationsMessageDTO</code> attribute **/
	public static final String CUSTOMERNOTIFICATIONSMESSAGEDTO = "CustomerNotificationsMessageDTO";
	/** Qualifier of the <code>SterlingCustomerNotificationProcess.order</code> attribute **/
	public static final String ORDER = "order";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n ORDER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedSterlingCustomerNotificationProcess> ORDERHANDLER = new BidirectionalOneToManyHandler<GeneratedSterlingCustomerNotificationProcess>(
	SslCoreConstants.TC.STERLINGCUSTOMERNOTIFICATIONPROCESS,
	false,
	"order",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(BusinessProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CUSTOMERNOTIFICATIONSMESSAGEDTO, AttributeMode.INITIAL);
		tmp.put(ORDER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		ORDERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingCustomerNotificationProcess.CustomerNotificationsMessageDTO</code> attribute.
	 * @return the CustomerNotificationsMessageDTO
	 */
	public Object getCustomerNotificationsMessageDTO(final SessionContext ctx)
	{
		return getProperty( ctx, CUSTOMERNOTIFICATIONSMESSAGEDTO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingCustomerNotificationProcess.CustomerNotificationsMessageDTO</code> attribute.
	 * @return the CustomerNotificationsMessageDTO
	 */
	public Object getCustomerNotificationsMessageDTO()
	{
		return getCustomerNotificationsMessageDTO( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingCustomerNotificationProcess.CustomerNotificationsMessageDTO</code> attribute. 
	 * @param value the CustomerNotificationsMessageDTO
	 */
	public void setCustomerNotificationsMessageDTO(final SessionContext ctx, final Object value)
	{
		setProperty(ctx, CUSTOMERNOTIFICATIONSMESSAGEDTO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingCustomerNotificationProcess.CustomerNotificationsMessageDTO</code> attribute. 
	 * @param value the CustomerNotificationsMessageDTO
	 */
	public void setCustomerNotificationsMessageDTO(final Object value)
	{
		setCustomerNotificationsMessageDTO( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingCustomerNotificationProcess.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder(final SessionContext ctx)
	{
		return (Order)getProperty( ctx, ORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SterlingCustomerNotificationProcess.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder()
	{
		return getOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingCustomerNotificationProcess.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final SessionContext ctx, final Order value)
	{
		ORDERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SterlingCustomerNotificationProcess.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final Order value)
	{
		setOrder( getSession().getSessionContext(), value );
	}
	
}
