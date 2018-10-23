/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22 Oct, 2018 11:46:56 AM                    ---
 * ----------------------------------------------------------------
 */
package com.borngroup.ssl.core.jalo;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob SSLProductLinkSimilarProductsCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSLProductLinkSimilarProductsCronJob extends CronJob
{
	/** Qualifier of the <code>SSLProductLinkSimilarProductsCronJob.categories</code> attribute **/
	public static final String CATEGORIES = "categories";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CATEGORIES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductLinkSimilarProductsCronJob.categories</code> attribute.
	 * @return the categories - L3 categories to be used.
	 */
	public List<Category> getCategories(final SessionContext ctx)
	{
		List<Category> coll = (List<Category>)getProperty( ctx, CATEGORIES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSLProductLinkSimilarProductsCronJob.categories</code> attribute.
	 * @return the categories - L3 categories to be used.
	 */
	public List<Category> getCategories()
	{
		return getCategories( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductLinkSimilarProductsCronJob.categories</code> attribute. 
	 * @param value the categories - L3 categories to be used.
	 */
	public void setCategories(final SessionContext ctx, final List<Category> value)
	{
		setProperty(ctx, CATEGORIES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSLProductLinkSimilarProductsCronJob.categories</code> attribute. 
	 * @param value the categories - L3 categories to be used.
	 */
	public void setCategories(final List<Category> value)
	{
		setCategories( getSession().getSessionContext(), value );
	}
	
}
