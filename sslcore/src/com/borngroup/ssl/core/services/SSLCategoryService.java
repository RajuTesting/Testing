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
package com.borngroup.ssl.core.services;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import com.borngroup.ssl.core.model.components.AbstractCMSCategoryComponentModel;


/**
 * @author Monomoy.Ghosh
 */
public interface SSLCategoryService extends CommerceCategoryService
{

	void setSessionCategory(String categoryCode);

	CategoryModel getSessionCategory();

	String buildComponentURL(CategoryModel category);

	<T extends AbstractCMSCategoryComponentModel> T buildComponent(CategoryModel category, Class<T> componentClass)
			throws ModelNotFoundException;

	String buildComponentHeader(CategoryModel category);

	String buildComponentText(CategoryModel category);

}
