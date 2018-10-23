/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.PSAppointment;
import com.borngroup.ssl.core.jalo.PSAppointmentFeedback;
import com.borngroup.ssl.core.jalo.PSBookingType;
import com.borngroup.ssl.core.jalo.PSPersonalShopper;
import com.borngroup.ssl.core.jalo.PSServices;
import com.borngroup.ssl.core.jalo.PSStoreMaster;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.storelocator.jalo.PointOfService;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PSAppointment}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPSAppointment extends GenericItem
{
	/** Qualifier of the <code>PSAppointment.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>PSAppointment.store</code> attribute **/
	public static final String STORE = "store";
	/** Qualifier of the <code>PSAppointment.serviceType</code> attribute **/
	public static final String SERVICETYPE = "serviceType";
	/** Qualifier of the <code>PSAppointment.appointmnetDate</code> attribute **/
	public static final String APPOINTMNETDATE = "appointmnetDate";
	/** Qualifier of the <code>PSAppointment.customerName</code> attribute **/
	public static final String CUSTOMERNAME = "customerName";
	/** Qualifier of the <code>PSAppointment.customerEmail</code> attribute **/
	public static final String CUSTOMEREMAIL = "customerEmail";
	/** Qualifier of the <code>PSAppointment.customerMobileNo</code> attribute **/
	public static final String CUSTOMERMOBILENO = "customerMobileNo";
	/** Qualifier of the <code>PSAppointment.customerAge</code> attribute **/
	public static final String CUSTOMERAGE = "customerAge";
	/** Qualifier of the <code>PSAppointment.customerGender</code> attribute **/
	public static final String CUSTOMERGENDER = "customerGender";
	/** Qualifier of the <code>PSAppointment.personalShopper</code> attribute **/
	public static final String PERSONALSHOPPER = "personalShopper";
	/** Qualifier of the <code>PSAppointment.appoinmentStatus</code> attribute **/
	public static final String APPOINMENTSTATUS = "appoinmentStatus";
	/** Qualifier of the <code>PSAppointment.parentAppointment</code> attribute **/
	public static final String PARENTAPPOINTMENT = "parentAppointment";
	/** Qualifier of the <code>PSAppointment.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>PSAppointment.customerFeedback</code> attribute **/
	public static final String CUSTOMERFEEDBACK = "customerFeedback";
	/** Qualifier of the <code>PSAppointment.bookingType</code> attribute **/
	public static final String BOOKINGTYPE = "bookingType";
	/** Qualifier of the <code>PSAppointment.psStoreMaster</code> attribute **/
	public static final String PSSTOREMASTER = "psStoreMaster";
	/** Qualifier of the <code>PSAppointment.appointmentCreationDate</code> attribute **/
	public static final String APPOINTMENTCREATIONDATE = "appointmentCreationDate";
	/** Qualifier of the <code>PSAppointment.crmPersonalShopper</code> attribute **/
	public static final String CRMPERSONALSHOPPER = "crmPersonalShopper";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(STORE, AttributeMode.INITIAL);
		tmp.put(SERVICETYPE, AttributeMode.INITIAL);
		tmp.put(APPOINTMNETDATE, AttributeMode.INITIAL);
		tmp.put(CUSTOMERNAME, AttributeMode.INITIAL);
		tmp.put(CUSTOMEREMAIL, AttributeMode.INITIAL);
		tmp.put(CUSTOMERMOBILENO, AttributeMode.INITIAL);
		tmp.put(CUSTOMERAGE, AttributeMode.INITIAL);
		tmp.put(CUSTOMERGENDER, AttributeMode.INITIAL);
		tmp.put(PERSONALSHOPPER, AttributeMode.INITIAL);
		tmp.put(APPOINMENTSTATUS, AttributeMode.INITIAL);
		tmp.put(PARENTAPPOINTMENT, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(CUSTOMERFEEDBACK, AttributeMode.INITIAL);
		tmp.put(BOOKINGTYPE, AttributeMode.INITIAL);
		tmp.put(PSSTOREMASTER, AttributeMode.INITIAL);
		tmp.put(APPOINTMENTCREATIONDATE, AttributeMode.INITIAL);
		tmp.put(CRMPERSONALSHOPPER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appoinmentStatus</code> attribute.
	 * @return the appoinmentStatus
	 */
	public EnumerationValue getAppoinmentStatus(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, APPOINMENTSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appoinmentStatus</code> attribute.
	 * @return the appoinmentStatus
	 */
	public EnumerationValue getAppoinmentStatus()
	{
		return getAppoinmentStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appoinmentStatus</code> attribute. 
	 * @param value the appoinmentStatus
	 */
	public void setAppoinmentStatus(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, APPOINMENTSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appoinmentStatus</code> attribute. 
	 * @param value the appoinmentStatus
	 */
	public void setAppoinmentStatus(final EnumerationValue value)
	{
		setAppoinmentStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appointmentCreationDate</code> attribute.
	 * @return the appointmentCreationDate
	 */
	public Date getAppointmentCreationDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, APPOINTMENTCREATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appointmentCreationDate</code> attribute.
	 * @return the appointmentCreationDate
	 */
	public Date getAppointmentCreationDate()
	{
		return getAppointmentCreationDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appointmentCreationDate</code> attribute. 
	 * @param value the appointmentCreationDate
	 */
	public void setAppointmentCreationDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, APPOINTMENTCREATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appointmentCreationDate</code> attribute. 
	 * @param value the appointmentCreationDate
	 */
	public void setAppointmentCreationDate(final Date value)
	{
		setAppointmentCreationDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appointmnetDate</code> attribute.
	 * @return the appointmnetDate
	 */
	public Date getAppointmnetDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, APPOINTMNETDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.appointmnetDate</code> attribute.
	 * @return the appointmnetDate
	 */
	public Date getAppointmnetDate()
	{
		return getAppointmnetDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appointmnetDate</code> attribute. 
	 * @param value the appointmnetDate
	 */
	public void setAppointmnetDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, APPOINTMNETDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.appointmnetDate</code> attribute. 
	 * @param value the appointmnetDate
	 */
	public void setAppointmnetDate(final Date value)
	{
		setAppointmnetDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.bookingType</code> attribute.
	 * @return the bookingType
	 */
	public PSBookingType getBookingType(final SessionContext ctx)
	{
		return (PSBookingType)getProperty( ctx, BOOKINGTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.bookingType</code> attribute.
	 * @return the bookingType
	 */
	public PSBookingType getBookingType()
	{
		return getBookingType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.bookingType</code> attribute. 
	 * @param value the bookingType
	 */
	public void setBookingType(final SessionContext ctx, final PSBookingType value)
	{
		setProperty(ctx, BOOKINGTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.bookingType</code> attribute. 
	 * @param value the bookingType
	 */
	public void setBookingType(final PSBookingType value)
	{
		setBookingType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.crmPersonalShopper</code> attribute.
	 * @return the crmPersonalShopper
	 */
	public PSPersonalShopper getCrmPersonalShopper(final SessionContext ctx)
	{
		return (PSPersonalShopper)getProperty( ctx, CRMPERSONALSHOPPER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.crmPersonalShopper</code> attribute.
	 * @return the crmPersonalShopper
	 */
	public PSPersonalShopper getCrmPersonalShopper()
	{
		return getCrmPersonalShopper( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.crmPersonalShopper</code> attribute. 
	 * @param value the crmPersonalShopper
	 */
	public void setCrmPersonalShopper(final SessionContext ctx, final PSPersonalShopper value)
	{
		setProperty(ctx, CRMPERSONALSHOPPER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.crmPersonalShopper</code> attribute. 
	 * @param value the crmPersonalShopper
	 */
	public void setCrmPersonalShopper(final PSPersonalShopper value)
	{
		setCrmPersonalShopper( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerAge</code> attribute.
	 * @return the customerAge
	 */
	public Integer getCustomerAge(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, CUSTOMERAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerAge</code> attribute.
	 * @return the customerAge
	 */
	public Integer getCustomerAge()
	{
		return getCustomerAge( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @return the customerAge
	 */
	public int getCustomerAgeAsPrimitive(final SessionContext ctx)
	{
		Integer value = getCustomerAge( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @return the customerAge
	 */
	public int getCustomerAgeAsPrimitive()
	{
		return getCustomerAgeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @param value the customerAge
	 */
	public void setCustomerAge(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, CUSTOMERAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @param value the customerAge
	 */
	public void setCustomerAge(final Integer value)
	{
		setCustomerAge( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @param value the customerAge
	 */
	public void setCustomerAge(final SessionContext ctx, final int value)
	{
		setCustomerAge( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerAge</code> attribute. 
	 * @param value the customerAge
	 */
	public void setCustomerAge(final int value)
	{
		setCustomerAge( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerEmail</code> attribute.
	 * @return the customerEmail
	 */
	public String getCustomerEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMEREMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerEmail</code> attribute.
	 * @return the customerEmail
	 */
	public String getCustomerEmail()
	{
		return getCustomerEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerEmail</code> attribute. 
	 * @param value the customerEmail
	 */
	public void setCustomerEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMEREMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerEmail</code> attribute. 
	 * @param value the customerEmail
	 */
	public void setCustomerEmail(final String value)
	{
		setCustomerEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerFeedback</code> attribute.
	 * @return the customerFeedback
	 */
	public PSAppointmentFeedback getCustomerFeedback(final SessionContext ctx)
	{
		return (PSAppointmentFeedback)getProperty( ctx, CUSTOMERFEEDBACK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerFeedback</code> attribute.
	 * @return the customerFeedback
	 */
	public PSAppointmentFeedback getCustomerFeedback()
	{
		return getCustomerFeedback( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerFeedback</code> attribute. 
	 * @param value the customerFeedback
	 */
	public void setCustomerFeedback(final SessionContext ctx, final PSAppointmentFeedback value)
	{
		setProperty(ctx, CUSTOMERFEEDBACK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerFeedback</code> attribute. 
	 * @param value the customerFeedback
	 */
	public void setCustomerFeedback(final PSAppointmentFeedback value)
	{
		setCustomerFeedback( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerGender</code> attribute.
	 * @return the customerGender
	 */
	public EnumerationValue getCustomerGender(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CUSTOMERGENDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerGender</code> attribute.
	 * @return the customerGender
	 */
	public EnumerationValue getCustomerGender()
	{
		return getCustomerGender( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerGender</code> attribute. 
	 * @param value the customerGender
	 */
	public void setCustomerGender(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CUSTOMERGENDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerGender</code> attribute. 
	 * @param value the customerGender
	 */
	public void setCustomerGender(final EnumerationValue value)
	{
		setCustomerGender( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerMobileNo</code> attribute.
	 * @return the customerMobileNo
	 */
	public String getCustomerMobileNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERMOBILENO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerMobileNo</code> attribute.
	 * @return the customerMobileNo
	 */
	public String getCustomerMobileNo()
	{
		return getCustomerMobileNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerMobileNo</code> attribute. 
	 * @param value the customerMobileNo
	 */
	public void setCustomerMobileNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERMOBILENO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerMobileNo</code> attribute. 
	 * @param value the customerMobileNo
	 */
	public void setCustomerMobileNo(final String value)
	{
		setCustomerMobileNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerName</code> attribute.
	 * @return the customerName
	 */
	public String getCustomerName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CUSTOMERNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.customerName</code> attribute.
	 * @return the customerName
	 */
	public String getCustomerName()
	{
		return getCustomerName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerName</code> attribute. 
	 * @param value the customerName
	 */
	public void setCustomerName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CUSTOMERNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.customerName</code> attribute. 
	 * @param value the customerName
	 */
	public void setCustomerName(final String value)
	{
		setCustomerName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.parentAppointment</code> attribute.
	 * @return the parentAppointment
	 */
	public PSAppointment getParentAppointment(final SessionContext ctx)
	{
		return (PSAppointment)getProperty( ctx, PARENTAPPOINTMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.parentAppointment</code> attribute.
	 * @return the parentAppointment
	 */
	public PSAppointment getParentAppointment()
	{
		return getParentAppointment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.parentAppointment</code> attribute. 
	 * @param value the parentAppointment
	 */
	public void setParentAppointment(final SessionContext ctx, final PSAppointment value)
	{
		setProperty(ctx, PARENTAPPOINTMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.parentAppointment</code> attribute. 
	 * @param value the parentAppointment
	 */
	public void setParentAppointment(final PSAppointment value)
	{
		setParentAppointment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.personalShopper</code> attribute.
	 * @return the personalShopper
	 */
	public User getPersonalShopper(final SessionContext ctx)
	{
		return (User)getProperty( ctx, PERSONALSHOPPER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.personalShopper</code> attribute.
	 * @return the personalShopper
	 */
	public User getPersonalShopper()
	{
		return getPersonalShopper( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.personalShopper</code> attribute. 
	 * @param value the personalShopper
	 */
	public void setPersonalShopper(final SessionContext ctx, final User value)
	{
		setProperty(ctx, PERSONALSHOPPER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.personalShopper</code> attribute. 
	 * @param value the personalShopper
	 */
	public void setPersonalShopper(final User value)
	{
		setPersonalShopper( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.psStoreMaster</code> attribute.
	 * @return the psStoreMaster
	 */
	public PSStoreMaster getPsStoreMaster(final SessionContext ctx)
	{
		return (PSStoreMaster)getProperty( ctx, PSSTOREMASTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.psStoreMaster</code> attribute.
	 * @return the psStoreMaster
	 */
	public PSStoreMaster getPsStoreMaster()
	{
		return getPsStoreMaster( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.psStoreMaster</code> attribute. 
	 * @param value the psStoreMaster
	 */
	public void setPsStoreMaster(final SessionContext ctx, final PSStoreMaster value)
	{
		setProperty(ctx, PSSTOREMASTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.psStoreMaster</code> attribute. 
	 * @param value the psStoreMaster
	 */
	public void setPsStoreMaster(final PSStoreMaster value)
	{
		setPsStoreMaster( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.serviceType</code> attribute.
	 * @return the serviceType
	 */
	public PSServices getServiceType(final SessionContext ctx)
	{
		return (PSServices)getProperty( ctx, SERVICETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.serviceType</code> attribute.
	 * @return the serviceType
	 */
	public PSServices getServiceType()
	{
		return getServiceType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.serviceType</code> attribute. 
	 * @param value the serviceType
	 */
	public void setServiceType(final SessionContext ctx, final PSServices value)
	{
		setProperty(ctx, SERVICETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.serviceType</code> attribute. 
	 * @param value the serviceType
	 */
	public void setServiceType(final PSServices value)
	{
		setServiceType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.store</code> attribute.
	 * @return the store
	 */
	public PointOfService getStore(final SessionContext ctx)
	{
		return (PointOfService)getProperty( ctx, STORE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PSAppointment.store</code> attribute.
	 * @return the store
	 */
	public PointOfService getStore()
	{
		return getStore( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.store</code> attribute. 
	 * @param value the store
	 */
	public void setStore(final SessionContext ctx, final PointOfService value)
	{
		setProperty(ctx, STORE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PSAppointment.store</code> attribute. 
	 * @param value the store
	 */
	public void setStore(final PointOfService value)
	{
		setStore( getSession().getSessionContext(), value );
	}
	
}
