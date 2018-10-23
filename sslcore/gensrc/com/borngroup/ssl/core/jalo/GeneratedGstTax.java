/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem GstTax}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGstTax extends GenericItem
{
	/** Qualifier of the <code>GstTax.taxCategoryId</code> attribute **/
	public static final String TAXCATEGORYID = "taxCategoryId";
	/** Qualifier of the <code>GstTax.taxCode</code> attribute **/
	public static final String TAXCODE = "taxCode";
	/** Qualifier of the <code>GstTax.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>GstTax.validFromDate</code> attribute **/
	public static final String VALIDFROMDATE = "validFromDate";
	/** Qualifier of the <code>GstTax.validToDate</code> attribute **/
	public static final String VALIDTODATE = "validToDate";
	/** Qualifier of the <code>GstTax.taxValue</code> attribute **/
	public static final String TAXVALUE = "taxValue";
	/** Qualifier of the <code>GstTax.validFromAmount</code> attribute **/
	public static final String VALIDFROMAMOUNT = "validFromAmount";
	/** Qualifier of the <code>GstTax.validToAmount</code> attribute **/
	public static final String VALIDTOAMOUNT = "validToAmount";
	/** Qualifier of the <code>GstTax.taxType</code> attribute **/
	public static final String TAXTYPE = "taxType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(TAXCATEGORYID, AttributeMode.INITIAL);
		tmp.put(TAXCODE, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(VALIDFROMDATE, AttributeMode.INITIAL);
		tmp.put(VALIDTODATE, AttributeMode.INITIAL);
		tmp.put(TAXVALUE, AttributeMode.INITIAL);
		tmp.put(VALIDFROMAMOUNT, AttributeMode.INITIAL);
		tmp.put(VALIDTOAMOUNT, AttributeMode.INITIAL);
		tmp.put(TAXTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxCategoryId</code> attribute.
	 * @return the taxCategoryId
	 */
	public String getTaxCategoryId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TAXCATEGORYID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxCategoryId</code> attribute.
	 * @return the taxCategoryId
	 */
	public String getTaxCategoryId()
	{
		return getTaxCategoryId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxCategoryId</code> attribute. 
	 * @param value the taxCategoryId
	 */
	public void setTaxCategoryId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TAXCATEGORYID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxCategoryId</code> attribute. 
	 * @param value the taxCategoryId
	 */
	public void setTaxCategoryId(final String value)
	{
		setTaxCategoryId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxCode</code> attribute.
	 * @return the taxCode
	 */
	public EnumerationValue getTaxCode(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, TAXCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxCode</code> attribute.
	 * @return the taxCode
	 */
	public EnumerationValue getTaxCode()
	{
		return getTaxCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxCode</code> attribute. 
	 * @param value the taxCode
	 */
	public void setTaxCode(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, TAXCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxCode</code> attribute. 
	 * @param value the taxCode
	 */
	public void setTaxCode(final EnumerationValue value)
	{
		setTaxCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxType</code> attribute.
	 * @return the taxType
	 */
	public String getTaxType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TAXTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxType</code> attribute.
	 * @return the taxType
	 */
	public String getTaxType()
	{
		return getTaxType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxType</code> attribute. 
	 * @param value the taxType
	 */
	public void setTaxType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TAXTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxType</code> attribute. 
	 * @param value the taxType
	 */
	public void setTaxType(final String value)
	{
		setTaxType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxValue</code> attribute.
	 * @return the taxValue
	 */
	public Double getTaxValue(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, TAXVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxValue</code> attribute.
	 * @return the taxValue
	 */
	public Double getTaxValue()
	{
		return getTaxValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxValue</code> attribute. 
	 * @return the taxValue
	 */
	public double getTaxValueAsPrimitive(final SessionContext ctx)
	{
		Double value = getTaxValue( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.taxValue</code> attribute. 
	 * @return the taxValue
	 */
	public double getTaxValueAsPrimitive()
	{
		return getTaxValueAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxValue</code> attribute. 
	 * @param value the taxValue
	 */
	public void setTaxValue(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, TAXVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxValue</code> attribute. 
	 * @param value the taxValue
	 */
	public void setTaxValue(final Double value)
	{
		setTaxValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxValue</code> attribute. 
	 * @param value the taxValue
	 */
	public void setTaxValue(final SessionContext ctx, final double value)
	{
		setTaxValue( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.taxValue</code> attribute. 
	 * @param value the taxValue
	 */
	public void setTaxValue(final double value)
	{
		setTaxValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromAmount</code> attribute.
	 * @return the validFromAmount
	 */
	public Double getValidFromAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALIDFROMAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromAmount</code> attribute.
	 * @return the validFromAmount
	 */
	public Double getValidFromAmount()
	{
		return getValidFromAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @return the validFromAmount
	 */
	public double getValidFromAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getValidFromAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @return the validFromAmount
	 */
	public double getValidFromAmountAsPrimitive()
	{
		return getValidFromAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALIDFROMAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final Double value)
	{
		setValidFromAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final SessionContext ctx, final double value)
	{
		setValidFromAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final double value)
	{
		setValidFromAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromDate</code> attribute.
	 * @return the validFromDate
	 */
	public Date getValidFromDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, VALIDFROMDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validFromDate</code> attribute.
	 * @return the validFromDate
	 */
	public Date getValidFromDate()
	{
		return getValidFromDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromDate</code> attribute. 
	 * @param value the validFromDate
	 */
	public void setValidFromDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, VALIDFROMDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validFromDate</code> attribute. 
	 * @param value the validFromDate
	 */
	public void setValidFromDate(final Date value)
	{
		setValidFromDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToAmount</code> attribute.
	 * @return the validToAmount
	 */
	public Double getValidToAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALIDTOAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToAmount</code> attribute.
	 * @return the validToAmount
	 */
	public Double getValidToAmount()
	{
		return getValidToAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToAmount</code> attribute. 
	 * @return the validToAmount
	 */
	public double getValidToAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getValidToAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToAmount</code> attribute. 
	 * @return the validToAmount
	 */
	public double getValidToAmountAsPrimitive()
	{
		return getValidToAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALIDTOAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final Double value)
	{
		setValidToAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final SessionContext ctx, final double value)
	{
		setValidToAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final double value)
	{
		setValidToAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToDate</code> attribute.
	 * @return the validToDate
	 */
	public Date getValidToDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, VALIDTODATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GstTax.validToDate</code> attribute.
	 * @return the validToDate
	 */
	public Date getValidToDate()
	{
		return getValidToDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToDate</code> attribute. 
	 * @param value the validToDate
	 */
	public void setValidToDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, VALIDTODATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GstTax.validToDate</code> attribute. 
	 * @param value the validToDate
	 */
	public void setValidToDate(final Date value)
	{
		setValidToDate( getSession().getSessionContext(), value );
	}
	
}
