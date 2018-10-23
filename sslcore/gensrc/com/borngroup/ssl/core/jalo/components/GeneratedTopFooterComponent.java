/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo.components;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.jalo.components.CMSImageLinkComponent;
import de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.cms2.jalo.navigation.CMSNavigationNode;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.borngroup.ssl.core.jalo.components.TopFooterComponent TopFooterComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTopFooterComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>TopFooterComponent.downloadAppList</code> attribute **/
	public static final String DOWNLOADAPPLIST = "downloadAppList";
	/** Qualifier of the <code>TopFooterComponent.newsletterSignUp</code> attribute **/
	public static final String NEWSLETTERSIGNUP = "newsletterSignUp";
	/** Qualifier of the <code>TopFooterComponent.followUs</code> attribute **/
	public static final String FOLLOWUS = "followUs";
	/** Qualifier of the <code>TopFooterComponent.verifiedByList</code> attribute **/
	public static final String VERIFIEDBYLIST = "verifiedByList";
	/** Qualifier of the <code>TopFooterComponent.paySecurilyList</code> attribute **/
	public static final String PAYSECURILYLIST = "paySecurilyList";
	/** Qualifier of the <code>TopFooterComponent.sslServicesList</code> attribute **/
	public static final String SSLSERVICESLIST = "sslServicesList";
	/** Qualifier of the <code>TopFooterComponent.questionsCall</code> attribute **/
	public static final String QUESTIONSCALL = "questionsCall";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(DOWNLOADAPPLIST, AttributeMode.INITIAL);
		tmp.put(NEWSLETTERSIGNUP, AttributeMode.INITIAL);
		tmp.put(FOLLOWUS, AttributeMode.INITIAL);
		tmp.put(VERIFIEDBYLIST, AttributeMode.INITIAL);
		tmp.put(PAYSECURILYLIST, AttributeMode.INITIAL);
		tmp.put(SSLSERVICESLIST, AttributeMode.INITIAL);
		tmp.put(QUESTIONSCALL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.downloadAppList</code> attribute.
	 * @return the downloadAppList
	 */
	public List<CMSImageLinkComponent> getDownloadAppList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, DOWNLOADAPPLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.downloadAppList</code> attribute.
	 * @return the downloadAppList
	 */
	public List<CMSImageLinkComponent> getDownloadAppList()
	{
		return getDownloadAppList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.downloadAppList</code> attribute. 
	 * @param value the downloadAppList
	 */
	public void setDownloadAppList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, DOWNLOADAPPLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.downloadAppList</code> attribute. 
	 * @param value the downloadAppList
	 */
	public void setDownloadAppList(final List<CMSImageLinkComponent> value)
	{
		setDownloadAppList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.followUs</code> attribute.
	 * @return the followUs - List of navigation nodes that are rendered in this footer component.
	 */
	public CMSNavigationNode getFollowUs(final SessionContext ctx)
	{
		return (CMSNavigationNode)getProperty( ctx, FOLLOWUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.followUs</code> attribute.
	 * @return the followUs - List of navigation nodes that are rendered in this footer component.
	 */
	public CMSNavigationNode getFollowUs()
	{
		return getFollowUs( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.followUs</code> attribute. 
	 * @param value the followUs - List of navigation nodes that are rendered in this footer component.
	 */
	public void setFollowUs(final SessionContext ctx, final CMSNavigationNode value)
	{
		setProperty(ctx, FOLLOWUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.followUs</code> attribute. 
	 * @param value the followUs - List of navigation nodes that are rendered in this footer component.
	 */
	public void setFollowUs(final CMSNavigationNode value)
	{
		setFollowUs( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.newsletterSignUp</code> attribute.
	 * @return the newsletterSignUp
	 */
	public JspIncludeComponent getNewsletterSignUp(final SessionContext ctx)
	{
		return (JspIncludeComponent)getProperty( ctx, NEWSLETTERSIGNUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.newsletterSignUp</code> attribute.
	 * @return the newsletterSignUp
	 */
	public JspIncludeComponent getNewsletterSignUp()
	{
		return getNewsletterSignUp( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.newsletterSignUp</code> attribute. 
	 * @param value the newsletterSignUp
	 */
	public void setNewsletterSignUp(final SessionContext ctx, final JspIncludeComponent value)
	{
		setProperty(ctx, NEWSLETTERSIGNUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.newsletterSignUp</code> attribute. 
	 * @param value the newsletterSignUp
	 */
	public void setNewsletterSignUp(final JspIncludeComponent value)
	{
		setNewsletterSignUp( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.paySecurilyList</code> attribute.
	 * @return the paySecurilyList
	 */
	public List<CMSImageLinkComponent> getPaySecurilyList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, PAYSECURILYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.paySecurilyList</code> attribute.
	 * @return the paySecurilyList
	 */
	public List<CMSImageLinkComponent> getPaySecurilyList()
	{
		return getPaySecurilyList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.paySecurilyList</code> attribute. 
	 * @param value the paySecurilyList
	 */
	public void setPaySecurilyList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, PAYSECURILYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.paySecurilyList</code> attribute. 
	 * @param value the paySecurilyList
	 */
	public void setPaySecurilyList(final List<CMSImageLinkComponent> value)
	{
		setPaySecurilyList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.questionsCall</code> attribute.
	 * @return the questionsCall
	 */
	public CMSParagraphComponent getQuestionsCall(final SessionContext ctx)
	{
		return (CMSParagraphComponent)getProperty( ctx, QUESTIONSCALL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.questionsCall</code> attribute.
	 * @return the questionsCall
	 */
	public CMSParagraphComponent getQuestionsCall()
	{
		return getQuestionsCall( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.questionsCall</code> attribute. 
	 * @param value the questionsCall
	 */
	public void setQuestionsCall(final SessionContext ctx, final CMSParagraphComponent value)
	{
		setProperty(ctx, QUESTIONSCALL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.questionsCall</code> attribute. 
	 * @param value the questionsCall
	 */
	public void setQuestionsCall(final CMSParagraphComponent value)
	{
		setQuestionsCall( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.sslServicesList</code> attribute.
	 * @return the sslServicesList
	 */
	public List<CMSImageLinkComponent> getSslServicesList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, SSLSERVICESLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.sslServicesList</code> attribute.
	 * @return the sslServicesList
	 */
	public List<CMSImageLinkComponent> getSslServicesList()
	{
		return getSslServicesList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.sslServicesList</code> attribute. 
	 * @param value the sslServicesList
	 */
	public void setSslServicesList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, SSLSERVICESLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.sslServicesList</code> attribute. 
	 * @param value the sslServicesList
	 */
	public void setSslServicesList(final List<CMSImageLinkComponent> value)
	{
		setSslServicesList( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.verifiedByList</code> attribute.
	 * @return the verifiedByList
	 */
	public List<CMSImageLinkComponent> getVerifiedByList(final SessionContext ctx)
	{
		List<CMSImageLinkComponent> coll = (List<CMSImageLinkComponent>)getProperty( ctx, VERIFIEDBYLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TopFooterComponent.verifiedByList</code> attribute.
	 * @return the verifiedByList
	 */
	public List<CMSImageLinkComponent> getVerifiedByList()
	{
		return getVerifiedByList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.verifiedByList</code> attribute. 
	 * @param value the verifiedByList
	 */
	public void setVerifiedByList(final SessionContext ctx, final List<CMSImageLinkComponent> value)
	{
		setProperty(ctx, VERIFIEDBYLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TopFooterComponent.verifiedByList</code> attribute. 
	 * @param value the verifiedByList
	 */
	public void setVerifiedByList(final List<CMSImageLinkComponent> value)
	{
		setVerifiedByList( getSession().getSessionContext(), value );
	}
	
}
