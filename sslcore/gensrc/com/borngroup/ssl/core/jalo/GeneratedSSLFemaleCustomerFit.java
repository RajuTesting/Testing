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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.SSLFemaleCustomerFit SSLFemaleCustomerFit}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLFemaleCustomerFit extends SSLCustomerFit
{
	/** Qualifier of the <code>SSLFemaleCustomerFit.bust</code> attribute **/
	public static final String BUST = "bust";
	/** Qualifier of the <code>SSLFemaleCustomerFit.waist</code> attribute **/
	public static final String WAIST = "waist";
	/** Qualifier of the <code>SSLFemaleCustomerFit.hips</code> attribute **/
	public static final String HIPS = "hips";
	/** Qualifier of the <code>SSLFemaleCustomerFit.sleeve</code> attribute **/
	public static final String SLEEVE = "sleeve";
	/** Qualifier of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute **/
	public static final String INSIDELEGLENGTH = "insideLegLength";
	/** Qualifier of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute **/
	public static final String OUTSIDELEGLENGTH = "outsideLegLength";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SSLCustomerFit.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BUST, AttributeMode.INITIAL);
		tmp.put(WAIST, AttributeMode.INITIAL);
		tmp.put(HIPS, AttributeMode.INITIAL);
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
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.bust</code> attribute.
	 * @return the bust
	 */
	public Double getBust(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, BUST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.bust</code> attribute.
	 * @return the bust
	 */
	public Double getBust()
	{
		return getBust( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @return the bust
	 */
	public double getBustAsPrimitive(final SessionContext ctx)
	{
		Double value = getBust( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @return the bust
	 */
	public double getBustAsPrimitive()
	{
		return getBustAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @param value the bust
	 */
	public void setBust(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, BUST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @param value the bust
	 */
	public void setBust(final Double value)
	{
		setBust( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @param value the bust
	 */
	public void setBust(final SessionContext ctx, final double value)
	{
		setBust( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.bust</code> attribute. 
	 * @param value the bust
	 */
	public void setBust(final double value)
	{
		setBust( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.hips</code> attribute.
	 * @return the hips
	 */
	public Double getHips(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, HIPS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.hips</code> attribute.
	 * @return the hips
	 */
	public Double getHips()
	{
		return getHips( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @return the hips
	 */
	public double getHipsAsPrimitive(final SessionContext ctx)
	{
		Double value = getHips( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @return the hips
	 */
	public double getHipsAsPrimitive()
	{
		return getHipsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @param value the hips
	 */
	public void setHips(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, HIPS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @param value the hips
	 */
	public void setHips(final Double value)
	{
		setHips( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @param value the hips
	 */
	public void setHips(final SessionContext ctx, final double value)
	{
		setHips( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.hips</code> attribute. 
	 * @param value the hips
	 */
	public void setHips(final double value)
	{
		setHips( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute.
	 * @return the insideLegLength
	 */
	public Double getInsideLegLength(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, INSIDELEGLENGTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute.
	 * @return the insideLegLength
	 */
	public Double getInsideLegLength()
	{
		return getInsideLegLength( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @return the insideLegLength
	 */
	public double getInsideLegLengthAsPrimitive(final SessionContext ctx)
	{
		Double value = getInsideLegLength( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @return the insideLegLength
	 */
	public double getInsideLegLengthAsPrimitive()
	{
		return getInsideLegLengthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, INSIDELEGLENGTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final Double value)
	{
		setInsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final SessionContext ctx, final double value)
	{
		setInsideLegLength( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.insideLegLength</code> attribute. 
	 * @param value the insideLegLength
	 */
	public void setInsideLegLength(final double value)
	{
		setInsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute.
	 * @return the outsideLegLength
	 */
	public Double getOutsideLegLength(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, OUTSIDELEGLENGTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute.
	 * @return the outsideLegLength
	 */
	public Double getOutsideLegLength()
	{
		return getOutsideLegLength( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @return the outsideLegLength
	 */
	public double getOutsideLegLengthAsPrimitive(final SessionContext ctx)
	{
		Double value = getOutsideLegLength( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @return the outsideLegLength
	 */
	public double getOutsideLegLengthAsPrimitive()
	{
		return getOutsideLegLengthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, OUTSIDELEGLENGTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final Double value)
	{
		setOutsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final SessionContext ctx, final double value)
	{
		setOutsideLegLength( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.outsideLegLength</code> attribute. 
	 * @param value the outsideLegLength
	 */
	public void setOutsideLegLength(final double value)
	{
		setOutsideLegLength( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute.
	 * @return the sleeve
	 */
	public Double getSleeve(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SLEEVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute.
	 * @return the sleeve
	 */
	public Double getSleeve()
	{
		return getSleeve( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @return the sleeve
	 */
	public double getSleeveAsPrimitive(final SessionContext ctx)
	{
		Double value = getSleeve( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @return the sleeve
	 */
	public double getSleeveAsPrimitive()
	{
		return getSleeveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SLEEVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final Double value)
	{
		setSleeve( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final SessionContext ctx, final double value)
	{
		setSleeve( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.sleeve</code> attribute. 
	 * @param value the sleeve
	 */
	public void setSleeve(final double value)
	{
		setSleeve( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.waist</code> attribute.
	 * @return the waist
	 */
	public Double getWaist(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WAIST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.waist</code> attribute.
	 * @return the waist
	 */
	public Double getWaist()
	{
		return getWaist( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @return the waist
	 */
	public double getWaistAsPrimitive(final SessionContext ctx)
	{
		Double value = getWaist( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @return the waist
	 */
	public double getWaistAsPrimitive()
	{
		return getWaistAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WAIST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final Double value)
	{
		setWaist( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final SessionContext ctx, final double value)
	{
		setWaist( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLFemaleCustomerFit.waist</code> attribute. 
	 * @param value the waist
	 */
	public void setWaist(final double value)
	{
		setWaist( getSession().getSessionContext(), value );
	}
	
}
