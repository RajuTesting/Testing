/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.servicelayer.media.impl.DefaultMediaContainerService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SslMediaContainerDao;
import com.borngroup.ssl.core.services.SslMediaContainerService;


/**
 * @author admin
 *
 */
public class SslMediaContainerServiceImpl extends DefaultMediaContainerService implements SslMediaContainerService
{

	@Resource(name = "sslMediaContainerDao")
	SslMediaContainerDao sslMediaContainerDao;

	@Override
	public MediaContainerModel getMediaContainerForQualifier(final CatalogVersionModel catalogVersion, final String qualifier)
	{

		ServicesUtil.validateParameterNotNull(qualifier, "Media context qualifier cannot be null");

		final List result = this.sslMediaContainerDao.findMediaContextByQualifier(catalogVersion, qualifier);
		ServicesUtil.validateIfSingleResult(result, "No media context with qualifier " + qualifier + " can be found.",
				"More than one media context with qualifier " + qualifier + " found.");

		return ((MediaContainerModel) result.iterator().next());
	}
}
