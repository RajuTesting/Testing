/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.SSLCustomerFit;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLMaleCustomerFit SSLMaleCustomerFit}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLMaleCustomerFit extends SSLCustomerFit
{
	/** Qualifier of the <code>SSLMaleCustomerFit.shoulder</code> attribute **/
	public static final String SHOULDER = "shoulder";
	/** Qualifier of the <code>SSLMaleCustomerFit.chest</code> attribute **/
	public static final String CHEST = "chest";
	/** Qualifier of the <code>SSLMaleCustomerFit.waist</code> attribute **/
	public static final String WAIST = "waist";
	/** Qualifier of the <code>SSLMaleCustomerFit.collar</code> attribute **/
	public static final String COLLAR = "collar";
	/** Qualifier of the <code>SSLMaleCustomerFit.sleeve</code> attribute **/
	public static final String SLEEVE = "sleeve";
	/** Qualifier of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute **/
	public static final String INSIDELEGLENGTH = "insideLegLength";
	/** Qualifier of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute **/
	public static final String OUTSIDELEGLENGTH = "outsideLegLength";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLCustomerFit.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SHOULDER, AttributeMode.INITIAL);
		tmp.put(CHEST, AttributeMode.INITIAL);
		tmp.put(WAIST, AttributeMode.INITIAL);
		tmp.put(COLLAR, AttributeMode.INITIAL);
		tmp.put(SLEEVE, AttributeMode.INITIAL);
		tmp.put(INSIDELEGLENGTH, AttributeMode.INITIAL);
		tmp.put(OUTSIDELEGLENGTH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.chest</code> attribute.
	 * @return the chest
	 */
	public Double getChest(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, CHEST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.chest</code> attribute.
	 * @return the chest
	 */
	public Double getChest()
	{
		return getChest( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @return the chest
	 */
	public double getChestAsPrimitive(final SessionContext ctx)
	{
		Double value = getChest( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @return the chest
	 */
	public double getChestAsPrimitive()
	{
		return getChestAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @param value the chest
	 */
	public void setChest(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, CHEST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @param value the chest
	 */
	public void setChest(final Double value)
	{
		setChest( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @param value the chest
	 */
	public void setChest(final SessionContext ctx, final double value)
	{
		setChest( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.chest</code> attribute. 
	 * @param value the chest
	 */
	public void setChest(final double value)
	{
		setChest( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.collar</code> attribute.
	 * @return the collar
	 */
	public Double getCollar(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, COLLAR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.collar</code> attribute.
	 * @return the collar
	 */
	public Double getCollar()
	{
		return getCollar( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @return the collar
	 */
	public double getCollarAsPrimitive(final SessionContext ctx)
	{
		Double value = getCollar( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @return the collar
	 */
	public double getCollarAsPrimitive()
	{
		return getCollarAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @param value the collar
	 */
	public void setCollar(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, COLLAR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @param value the collar
	 */
	public void setCollar(final Double value)
	{
		setCollar( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @param value the collar
	 */
	public void setCollar(final SessionContext ctx, final double value)
	{
		setCollar( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.collar</code> attribute. 
	 * @param value the collar
	 */
	public void setCollar(final double value)
	{
		setCollar( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute.
	 * @return the insideLegLength
	 */
	public Double getInsideLegLength(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, INSIDELEGLENGTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute.
	 * @return the insideLegLength
	 */
	public Double getInsideLegLength()
	{
		return getInsideLegLength( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @return the insideLegLength
	 */
	public double getInsideLegLengthAsPrimitive(final SessionContext ctx)
	{
		Double value = getInsideLegLength( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @return the insideLegLength
	 */
	public double getInsideLegLengthAsPrimitive()
	{
		return getInsideLegLengthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, INSIDELEGLENGTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final Double value)
	{
		setInsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final SessionContext ctx, final double value)
	{
		setInsideLegLength( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final double value)
	{
		setInsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute.
	 * @return the outsideLegLength
	 */
	public Double getOutsideLegLength(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, OUTSIDELEGLENGTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute.
	 * @return the outsideLegLength
	 */
	public Double getOutsideLegLength()
	{
		return getOutsideLegLength( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @return the outsideLegLength
	 */
	public double getOutsideLegLengthAsPrimitive(final SessionContext ctx)
	{
		Double value = getOutsideLegLength( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @return the outsideLegLength
	 */
	public double getOutsideLegLengthAsPrimitive()
	{
		return getOutsideLegLengthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, OUTSIDELEGLENGTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final Double value)
	{
		setOutsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final SessionContext ctx, final double value)
	{
		setOutsideLegLength( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final double value)
	{
		setOutsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.shoulder</code> attribute.
	 * @return the shoulder
	 */
	public Double getShoulder(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SHOULDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.shoulder</code> attribute.
	 * @return the shoulder
	 */
	public Double getShoulder()
	{
		return getShoulder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @return the shoulder
	 */
	public double getShoulderAsPrimitive(final SessionContext ctx)
	{
		Double value = getShoulder( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @return the shoulder
	 */
	public double getShoulderAsPrimitive()
	{
		return getShoulderAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @param value the shoulder
	 */
	public void setShoulder(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SHOULDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @param value the shoulder
	 */
	public void setShoulder(final Double value)
	{
		setShoulder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @param value the shoulder
	 */
	public void setShoulder(final SessionContext ctx, final double value)
	{
		setShoulder( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.shoulder</code> attribute. 
	 * @param value the shoulder
	 */
	public void setShoulder(final double value)
	{
		setShoulder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.sleeve</code> attribute.
	 * @return the sleeve
	 */
	public Double getSleeve(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SLEEVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.sleeve</code> attribute.
	 * @return the sleeve
	 */
	public Double getSleeve()
	{
		return getSleeve( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @return the sleeve
	 */
	public double getSleeveAsPrimitive(final SessionContext ctx)
	{
		Double value = getSleeve( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @return the sleeve
	 */
	public double getSleeveAsPrimitive()
	{
		return getSleeveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SLEEVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final Double value)
	{
		setSleeve( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final SessionContext ctx, final double value)
	{
		setSleeve( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final double value)
	{
		setSleeve( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.waist</code> attribute.
	 * @return the waist
	 */
	public Double getWaist(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WAIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.waist</code> attribute.
	 * @return the waist
	 */
	public Double getWaist()
	{
		return getWaist( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @return the waist
	 */
	public double getWaistAsPrimitive(final SessionContext ctx)
	{
		Double value = getWaist( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @return the waist
	 */
	public double getWaistAsPrimitive()
	{
		return getWaistAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WAIST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final Double value)
	{
		setWaist( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final SessionContext ctx, final double value)
	{
		setWaist( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLMaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final double value)
	{
		setWaist( getSession().getSessionContext(), value );
	}
	
}
