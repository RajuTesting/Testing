/**
 *
 */
package com.borngroup.ssl.core.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;

import java.util.List;


/**
 * @author admin
 *
 */
public interface SslMediaContainerDao
{
	abstract List<MediaContainerModel> findMediaContextByQualifier(final CatalogVersionModel catalogVersion, String qualifier);
}
