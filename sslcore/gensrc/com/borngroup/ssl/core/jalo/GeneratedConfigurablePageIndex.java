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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ConfigurablePageIndex}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedConfigurablePageIndex extends GenericItem
{
	/** Qualifier of the <code>ConfigurablePageIndex.pageType</code> attribute **/
	public static final String PAGETYPE = "pageType";
	/** Qualifier of the <code>ConfigurablePageIndex.sections</code> attribute **/
	public static final String SECTIONS = "sections";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PAGETYPE, AttributeMode.INITIAL);
		tmp.put(SECTIONS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConfigurablePageIndex.pageType</code> attribute.
	 * @return the pageType
	 */
	public EnumerationValue getPageType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PAGETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConfigurablePageIndex.pageType</code> attribute.
	 * @return the pageType
	 */
	public EnumerationValue getPageType()
	{
		return getPageType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConfigurablePageIndex.pageType</code> attribute. 
	 * @param value the pageType
	 */
	public void setPageType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PAGETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConfigurablePageIndex.pageType</code> attribute. 
	 * @param value the pageType
	 */
	public void setPageType(final EnumerationValue value)
	{
		setPageType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConfigurablePageIndex.sections</code> attribute.
	 * @return the sections
	 */
	public List<String> getSections(final SessionContext ctx)
	{
		List<String> coll = (List<String>)getProperty( ctx, SECTIONS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ConfigurablePageIndex.sections</code> attribute.
	 * @return the sections
	 */
	public List<String> getSections()
	{
		return getSections( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConfigurablePageIndex.sections</code> attribute. 
	 * @param value the sections
	 */
	public void setSections(final SessionContext ctx, final List<String> value)
	{
		setProperty(ctx, SECTIONS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ConfigurablePageIndex.sections</code> attribute. 
	 * @param value the sections
	 */
	public void setSections(final List<String> value)
	{
		setSections( getSession().getSessionContext(), value );
	}
	
}
