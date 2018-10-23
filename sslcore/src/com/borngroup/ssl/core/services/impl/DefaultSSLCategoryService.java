/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.category.impl.DefaultCommerceCategoryService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.model.components.AbstractCMSCategoryComponentModel;
import com.borngroup.ssl.core.services.SSLCategoryService;


/**
 * @author Monomoy.Ghosh
 */
public class DefaultSSLCategoryService extends DefaultCommerceCategoryService implements SSLCategoryService
{

	private static final String SESSION_CATEGORY_KEY = "SESSION_CATEGORY_KEY";

	private SessionService sessionService;
	private FlexibleSearchService flexibleSearchService;
	private UrlResolver<CategoryModel> categoryModelUrlResolver;

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the categoryModelUrlResolver
	 */
	public UrlResolver<CategoryModel> getCategoryModelUrlResolver()
	{
		return categoryModelUrlResolver;
	}

	/**
	 * @param categoryModelUrlResolver
	 *           the categoryModelUrlResolver to set
	 */
	public void setCategoryModelUrlResolver(final UrlResolver<CategoryModel> categoryModelUrlResolver)
	{
		this.categoryModelUrlResolver = categoryModelUrlResolver;
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#setSessionCategory(String)
	 */
	@Override
	public void setSessionCategory(final String categoryCode)
	{
		getSessionService().setAttribute(SESSION_CATEGORY_KEY, categoryCode);
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#getSessionCategory()
	 */
	@Override
	public CategoryModel getSessionCategory()
	{
		final String categoryCode = getSessionService().getAttribute(SESSION_CATEGORY_KEY);
		if (StringUtils.isNotBlank(categoryCode))
		{
			return getCategoryForCode(categoryCode);
		}

		throw new UnknownIdentifierException("No Category set in current Session with code '" + SESSION_CATEGORY_KEY + "'!");
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#buildComponentURL(de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public String buildComponentURL(final CategoryModel category)
	{
		return categoryModelUrlResolver.resolve(category);
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#buildComponent(CategoryModel, Class)
	 */
	@Override
	public <T extends AbstractCMSCategoryComponentModel> T buildComponent(final CategoryModel category,
			final Class<T> componentClass) throws ModelNotFoundException
	{
		T categorySampleComponent = null;
		try
		{
			categorySampleComponent = componentClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new ModelNotFoundException("Unable to create Model for class : [" + componentClass.getSimpleName() + "]");
		}
		categorySampleComponent.setCategory(category);
		final List<T> categoriesComponent = flexibleSearchService.getModelsByExample(categorySampleComponent);
		final T categoryComponent = CollectionUtils.isNotEmpty(categoriesComponent) ? categoriesComponent.get(0) : null;
		if (categoryComponent == null)
		{
			throw new ModelNotFoundException("No " + componentClass.getSimpleName() + " Model found for category : ["
					+ category.getCode() + "]");
		}

		if (CollectionUtils.isNotEmpty(category.getMedias()))
		{
			categoryComponent.setMedia(category.getMedias().get(0));
		}
		return categoryComponent;
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#buildComponentHeader(de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public String buildComponentHeader(final CategoryModel category)
	{
		return category.getName();
	}

	/**
	 * @see com.borngroup.ssl.core.services.SSLCategoryService#buildComponentText(de.hybris.platform.category.model.CategoryModel)
	 */
	@Override
	public String buildComponentText(final CategoryModel category)
	{
		return category.getDescription();
	}

}
