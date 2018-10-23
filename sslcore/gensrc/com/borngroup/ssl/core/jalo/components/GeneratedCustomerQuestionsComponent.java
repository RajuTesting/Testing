/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CustomerFeedbackTypesComponent;
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
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.CustomerQuestionsComponent CustomerQuestionsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerQuestionsComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute **/
	public static final String CUSTOMERQUESTION = "customerQuestion";
	/** Qualifier of the <code>CustomerQuestionsComponent.customerFeedbackTypes</code> attribute **/
	public static final String CUSTOMERFEEDBACKTYPES = "customerFeedbackTypes";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CUSTOMERQUESTION, AttributeMode.INITIAL);
		tmp.put(CUSTOMERFEEDBACKTYPES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerFeedbackTypes</code> attribute.
	 * @return the customerFeedbackTypes - Customer Feedback Types Component
	 */
	public CustomerFeedbackTypesComponent getCustomerFeedbackTypes(final SessionContext ctx)
	{
		return (CustomerFeedbackTypesComponent)getProperty( ctx, CUSTOMERFEEDBACKTYPES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerFeedbackTypes</code> attribute.
	 * @return the customerFeedbackTypes - Customer Feedback Types Component
	 */
	public CustomerFeedbackTypesComponent getCustomerFeedbackTypes()
	{
		return getCustomerFeedbackTypes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerFeedbackTypes</code> attribute. 
	 * @param value the customerFeedbackTypes - Customer Feedback Types Component
	 */
	public void setCustomerFeedbackTypes(final SessionContext ctx, final CustomerFeedbackTypesComponent value)
	{
		setProperty(ctx, CUSTOMERFEEDBACKTYPES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerFeedbackTypes</code> attribute. 
	 * @param value the customerFeedbackTypes - Customer Feedback Types Component
	 */
	public void setCustomerFeedbackTypes(final CustomerFeedbackTypesComponent value)
	{
		setCustomerFeedbackTypes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute.
	 * @return the customerQuestion
	 */
	public String getCustomerQuestion(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerQuestionsComponent.getCustomerQuestion requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, CUSTOMERQUESTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute.
	 * @return the customerQuestion
	 */
	public String getCustomerQuestion()
	{
		return getCustomerQuestion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @return the localized customerQuestion
	 */
	public Map<Language,String> getAllCustomerQuestion(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,CUSTOMERQUESTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @return the localized customerQuestion
	 */
	public Map<Language,String> getAllCustomerQuestion()
	{
		return getAllCustomerQuestion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @param value the customerQuestion
	 */
	public void setCustomerQuestion(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedCustomerQuestionsComponent.setCustomerQuestion requires a session language", 0 );
		}
		setLocalizedProperty(ctx, CUSTOMERQUESTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @param value the customerQuestion
	 */
	public void setCustomerQuestion(final String value)
	{
		setCustomerQuestion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @param value the customerQuestion
	 */
	public void setAllCustomerQuestion(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,CUSTOMERQUESTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerQuestionsComponent.customerQuestion</code> attribute. 
	 * @param value the customerQuestion
	 */
	public void setAllCustomerQuestion(final Map<Language,String> value)
	{
		setAllCustomerQuestion( getSession().getSessionContext(), value );
	}
	
}
