/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CustomerFeedbackTypesComponent CustomerFeedbackTypesComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerFeedbackTypesComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>CustomerFeedbackTypesComponent.type1</code> attribute **/
	public static final String TYPE1 = "type1";
	/** Qualifier of the <code>CustomerFeedbackTypesComponent.type2</code> attribute **/
	public static final String TYPE2 = "type2";
	/** Qualifier of the <code>CustomerFeedbackTypesComponent.type3</code> attribute **/
	public static final String TYPE3 = "type3";
	/** Qualifier of the <code>CustomerFeedbackTypesComponent.type4</code> attribute **/
	public static final String TYPE4 = "type4";
	/** Qualifier of the <code>CustomerFeedbackTypesComponent.type5</code> attribute **/
	public static final String TYPE5 = "type5";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TYPE1, AttributeMode.INITIAL);
		tmp.put(TYPE2, AttributeMode.INITIAL);
		tmp.put(TYPE3, AttributeMode.INITIAL);
		tmp.put(TYPE4, AttributeMode.INITIAL);
		tmp.put(TYPE5, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute.
	 * @return the type1
	 */
	public String getType1(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.getType1 requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TYPE1);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute.
	 * @return the type1
	 */
	public String getType1()
	{
		return getType1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @return the localized type1
	 */
	public Map<Language,String> getAllType1(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TYPE1,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @return the localized type1
	 */
	public Map<Language,String> getAllType1()
	{
		return getAllType1( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @param value the type1
	 */
	public void setType1(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.setType1 requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TYPE1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @param value the type1
	 */
	public void setType1(final String value)
	{
		setType1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @param value the type1
	 */
	public void setAllType1(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TYPE1,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type1</code> attribute. 
	 * @param value the type1
	 */
	public void setAllType1(final Map<Language,String> value)
	{
		setAllType1( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute.
	 * @return the type2
	 */
	public String getType2(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.getType2 requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TYPE2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute.
	 * @return the type2
	 */
	public String getType2()
	{
		return getType2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @return the localized type2
	 */
	public Map<Language,String> getAllType2(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TYPE2,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @return the localized type2
	 */
	public Map<Language,String> getAllType2()
	{
		return getAllType2( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @param value the type2
	 */
	public void setType2(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.setType2 requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TYPE2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @param value the type2
	 */
	public void setType2(final String value)
	{
		setType2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @param value the type2
	 */
	public void setAllType2(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TYPE2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type2</code> attribute. 
	 * @param value the type2
	 */
	public void setAllType2(final Map<Language,String> value)
	{
		setAllType2( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute.
	 * @return the type3
	 */
	public String getType3(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.getType3 requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TYPE3);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute.
	 * @return the type3
	 */
	public String getType3()
	{
		return getType3( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @return the localized type3
	 */
	public Map<Language,String> getAllType3(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TYPE3,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @return the localized type3
	 */
	public Map<Language,String> getAllType3()
	{
		return getAllType3( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @param value the type3
	 */
	public void setType3(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.setType3 requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TYPE3,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @param value the type3
	 */
	public void setType3(final String value)
	{
		setType3( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @param value the type3
	 */
	public void setAllType3(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TYPE3,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type3</code> attribute. 
	 * @param value the type3
	 */
	public void setAllType3(final Map<Language,String> value)
	{
		setAllType3( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute.
	 * @return the type4
	 */
	public String getType4(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.getType4 requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TYPE4);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute.
	 * @return the type4
	 */
	public String getType4()
	{
		return getType4( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @return the localized type4
	 */
	public Map<Language,String> getAllType4(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TYPE4,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @return the localized type4
	 */
	public Map<Language,String> getAllType4()
	{
		return getAllType4( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @param value the type4
	 */
	public void setType4(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.setType4 requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TYPE4,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @param value the type4
	 */
	public void setType4(final String value)
	{
		setType4( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @param value the type4
	 */
	public void setAllType4(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TYPE4,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type4</code> attribute. 
	 * @param value the type4
	 */
	public void setAllType4(final Map<Language,String> value)
	{
		setAllType4( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute.
	 * @return the type5
	 */
	public String getType5(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.getType5 requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TYPE5);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute.
	 * @return the type5
	 */
	public String getType5()
	{
		return getType5( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @return the localized type5
	 */
	public Map<Language,String> getAllType5(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TYPE5,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @return the localized type5
	 */
	public Map<Language,String> getAllType5()
	{
		return getAllType5( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @param value the type5
	 */
	public void setType5(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerFeedbackTypesComponent.setType5 requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TYPE5,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @param value the type5
	 */
	public void setType5(final String value)
	{
		setType5( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @param value the type5
	 */
	public void setAllType5(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TYPE5,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerFeedbackTypesComponent.type5</code> attribute. 
	 * @param value the type5
	 */
	public void setAllType5(final Map<Language,String> value)
	{
		setAllType5( getSession().getSessionContext(), value );
	}
	
}
