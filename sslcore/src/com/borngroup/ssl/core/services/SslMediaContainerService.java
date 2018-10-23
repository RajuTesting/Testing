/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.servicelayer.media.MediaContainerService;


/**
 * @author admin
 *
 */
public interface SslMediaContainerService extends MediaContainerService
{
	public MediaContainerModel getMediaContainerForQualifier(final CatalogVersionModel catalogVersion, String qualifier);
}
