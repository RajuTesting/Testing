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
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem SslTax}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSslTax extends GenericItem
{
	/** Qualifier of the <code>SslTax.SKUCode</code> attribute **/
	public static final String SKUCODE = "SKUCode";
	/** Qualifier of the <code>SslTax.StyleCode</code> attribute **/
	public static final String STYLECODE = "StyleCode";
	/** Qualifier of the <code>SslTax.TaxIdentifier</code> attribute **/
	public static final String TAXIDENTIFIER = "TaxIdentifier";
	/** Qualifier of the <code>SslTax.TaxType</code> attribute **/
	public static final String TAXTYPE = "TaxType";
	/** Qualifier of the <code>SslTax.classId</code> attribute **/
	public static final String CLASSID = "classId";
	/** Qualifier of the <code>SslTax.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>SslTax.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>SslTax.deptId</code> attribute **/
	public static final String DEPTID = "deptId";
	/** Qualifier of the <code>SslTax.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>SslTax.subClassId</code> attribute **/
	public static final String SUBCLASSID = "subClassId";
	/** Qualifier of the <code>SslTax.subDeptId</code> attribute **/
	public static final String SUBDEPTID = "subDeptId";
	/** Qualifier of the <code>SslTax.taxCalculationType</code> attribute **/
	public static final String TAXCALCULATIONTYPE = "taxCalculationType";
	/** Qualifier of the <code>SslTax.taxFlag</code> attribute **/
	public static final String TAXFLAG = "taxFlag";
	/** Qualifier of the <code>SslTax.validFromAmount</code> attribute **/
	public static final String VALIDFROMAMOUNT = "validFromAmount";
	/** Qualifier of the <code>SslTax.validFromDate</code> attribute **/
	public static final String VALIDFROMDATE = "validFromDate";
	/** Qualifier of the <code>SslTax.validToAmount</code> attribute **/
	public static final String VALIDTOAMOUNT = "validToAmount";
	/** Qualifier of the <code>SslTax.validToDate</code> attribute **/
	public static final String VALIDTODATE = "validToDate";
	/** Qualifier of the <code>SslTax.value</code> attribute **/
	public static final String VALUE = "value";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SKUCODE, AttributeMode.INITIAL);
		tmp.put(STYLECODE, AttributeMode.INITIAL);
		tmp.put(TAXIDENTIFIER, AttributeMode.INITIAL);
		tmp.put(TAXTYPE, AttributeMode.INITIAL);
		tmp.put(CLASSID, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(DEPTID, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(SUBCLASSID, AttributeMode.INITIAL);
		tmp.put(SUBDEPTID, AttributeMode.INITIAL);
		tmp.put(TAXCALCULATIONTYPE, AttributeMode.INITIAL);
		tmp.put(TAXFLAG, AttributeMode.INITIAL);
		tmp.put(VALIDFROMAMOUNT, AttributeMode.INITIAL);
		tmp.put(VALIDFROMDATE, AttributeMode.INITIAL);
		tmp.put(VALIDTOAMOUNT, AttributeMode.INITIAL);
		tmp.put(VALIDTODATE, AttributeMode.INITIAL);
		tmp.put(VALUE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.classId</code> attribute.
	 * @return the classId
	 */
	public String getClassId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLASSID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.classId</code> attribute.
	 * @return the classId
	 */
	public String getClassId()
	{
		return getClassId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.classId</code> attribute. 
	 * @param value the classId
	 */
	public void setClassId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLASSID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.classId</code> attribute. 
	 * @param value the classId
	 */
	public void setClassId(final String value)
	{
		setClassId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx)
	{
		return (Currency)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Currency value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Currency value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.deptId</code> attribute.
	 * @return the deptId
	 */
	public String getDeptId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEPTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.deptId</code> attribute.
	 * @return the deptId
	 */
	public String getDeptId()
	{
		return getDeptId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.deptId</code> attribute. 
	 * @param value the deptId
	 */
	public void setDeptId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEPTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.deptId</code> attribute. 
	 * @param value the deptId
	 */
	public void setDeptId(final String value)
	{
		setDeptId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.SKUCode</code> attribute.
	 * @return the SKUCode
	 */
	public String getSKUCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SKUCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.SKUCode</code> attribute.
	 * @return the SKUCode
	 */
	public String getSKUCode()
	{
		return getSKUCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.SKUCode</code> attribute. 
	 * @param value the SKUCode
	 */
	public void setSKUCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SKUCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.SKUCode</code> attribute. 
	 * @param value the SKUCode
	 */
	public void setSKUCode(final String value)
	{
		setSKUCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.StyleCode</code> attribute.
	 * @return the StyleCode
	 */
	public String getStyleCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STYLECODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.StyleCode</code> attribute.
	 * @return the StyleCode
	 */
	public String getStyleCode()
	{
		return getStyleCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.StyleCode</code> attribute. 
	 * @param value the StyleCode
	 */
	public void setStyleCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STYLECODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.StyleCode</code> attribute. 
	 * @param value the StyleCode
	 */
	public void setStyleCode(final String value)
	{
		setStyleCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.subClassId</code> attribute.
	 * @return the subClassId
	 */
	public String getSubClassId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBCLASSID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.subClassId</code> attribute.
	 * @return the subClassId
	 */
	public String getSubClassId()
	{
		return getSubClassId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.subClassId</code> attribute. 
	 * @param value the subClassId
	 */
	public void setSubClassId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBCLASSID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.subClassId</code> attribute. 
	 * @param value the subClassId
	 */
	public void setSubClassId(final String value)
	{
		setSubClassId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.subDeptId</code> attribute.
	 * @return the subDeptId
	 */
	public String getSubDeptId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SUBDEPTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.subDeptId</code> attribute.
	 * @return the subDeptId
	 */
	public String getSubDeptId()
	{
		return getSubDeptId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.subDeptId</code> attribute. 
	 * @param value the subDeptId
	 */
	public void setSubDeptId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SUBDEPTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.subDeptId</code> attribute. 
	 * @param value the subDeptId
	 */
	public void setSubDeptId(final String value)
	{
		setSubDeptId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.taxCalculationType</code> attribute.
	 * @return the taxCalculationType
	 */
	public String getTaxCalculationType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TAXCALCULATIONTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.taxCalculationType</code> attribute.
	 * @return the taxCalculationType
	 */
	public String getTaxCalculationType()
	{
		return getTaxCalculationType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.taxCalculationType</code> attribute. 
	 * @param value the taxCalculationType
	 */
	public void setTaxCalculationType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TAXCALCULATIONTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.taxCalculationType</code> attribute. 
	 * @param value the taxCalculationType
	 */
	public void setTaxCalculationType(final String value)
	{
		setTaxCalculationType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.taxFlag</code> attribute.
	 * @return the taxFlag
	 */
	public String getTaxFlag(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TAXFLAG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.taxFlag</code> attribute.
	 * @return the taxFlag
	 */
	public String getTaxFlag()
	{
		return getTaxFlag( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.taxFlag</code> attribute. 
	 * @param value the taxFlag
	 */
	public void setTaxFlag(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TAXFLAG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.taxFlag</code> attribute. 
	 * @param value the taxFlag
	 */
	public void setTaxFlag(final String value)
	{
		setTaxFlag( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.TaxIdentifier</code> attribute.
	 * @return the TaxIdentifier
	 */
	public String getTaxIdentifier(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TAXIDENTIFIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.TaxIdentifier</code> attribute.
	 * @return the TaxIdentifier
	 */
	public String getTaxIdentifier()
	{
		return getTaxIdentifier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.TaxIdentifier</code> attribute. 
	 * @param value the TaxIdentifier
	 */
	public void setTaxIdentifier(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TAXIDENTIFIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.TaxIdentifier</code> attribute. 
	 * @param value the TaxIdentifier
	 */
	public void setTaxIdentifier(final String value)
	{
		setTaxIdentifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.TaxType</code> attribute.
	 * @return the TaxType
	 */
	public EnumerationValue getTaxType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, TAXTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.TaxType</code> attribute.
	 * @return the TaxType
	 */
	public EnumerationValue getTaxType()
	{
		return getTaxType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.TaxType</code> attribute. 
	 * @param value the TaxType
	 */
	public void setTaxType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, TAXTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.TaxType</code> attribute. 
	 * @param value the TaxType
	 */
	public void setTaxType(final EnumerationValue value)
	{
		setTaxType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromAmount</code> attribute.
	 * @return the validFromAmount
	 */
	public Double getValidFromAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALIDFROMAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromAmount</code> attribute.
	 * @return the validFromAmount
	 */
	public Double getValidFromAmount()
	{
		return getValidFromAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @return the validFromAmount
	 */
	public double getValidFromAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getValidFromAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @return the validFromAmount
	 */
	public double getValidFromAmountAsPrimitive()
	{
		return getValidFromAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALIDFROMAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final Double value)
	{
		setValidFromAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final SessionContext ctx, final double value)
	{
		setValidFromAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromAmount</code> attribute. 
	 * @param value the validFromAmount
	 */
	public void setValidFromAmount(final double value)
	{
		setValidFromAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromDate</code> attribute.
	 * @return the validFromDate
	 */
	public Date getValidFromDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, VALIDFROMDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validFromDate</code> attribute.
	 * @return the validFromDate
	 */
	public Date getValidFromDate()
	{
		return getValidFromDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromDate</code> attribute. 
	 * @param value the validFromDate
	 */
	public void setValidFromDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, VALIDFROMDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validFromDate</code> attribute. 
	 * @param value the validFromDate
	 */
	public void setValidFromDate(final Date value)
	{
		setValidFromDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToAmount</code> attribute.
	 * @return the validToAmount
	 */
	public Double getValidToAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALIDTOAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToAmount</code> attribute.
	 * @return the validToAmount
	 */
	public Double getValidToAmount()
	{
		return getValidToAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToAmount</code> attribute. 
	 * @return the validToAmount
	 */
	public double getValidToAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getValidToAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToAmount</code> attribute. 
	 * @return the validToAmount
	 */
	public double getValidToAmountAsPrimitive()
	{
		return getValidToAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALIDTOAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final Double value)
	{
		setValidToAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final SessionContext ctx, final double value)
	{
		setValidToAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToAmount</code> attribute. 
	 * @param value the validToAmount
	 */
	public void setValidToAmount(final double value)
	{
		setValidToAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToDate</code> attribute.
	 * @return the validToDate
	 */
	public Date getValidToDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, VALIDTODATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.validToDate</code> attribute.
	 * @return the validToDate
	 */
	public Date getValidToDate()
	{
		return getValidToDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToDate</code> attribute. 
	 * @param value the validToDate
	 */
	public void setValidToDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, VALIDTODATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.validToDate</code> attribute. 
	 * @param value the validToDate
	 */
	public void setValidToDate(final Date value)
	{
		setValidToDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.value</code> attribute.
	 * @return the value
	 */
	public Double getValue(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.value</code> attribute.
	 * @return the value
	 */
	public Double getValue()
	{
		return getValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.value</code> attribute. 
	 * @return the value
	 */
	public double getValueAsPrimitive(final SessionContext ctx)
	{
		Double value = getValue( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SslTax.value</code> attribute. 
	 * @return the value
	 */
	public double getValueAsPrimitive()
	{
		return getValueAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final Double value)
	{
		setValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final SessionContext ctx, final double value)
	{
		setValue( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SslTax.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final double value)
	{
		setValue( getSession().getSessionContext(), value );
	}
	
}
