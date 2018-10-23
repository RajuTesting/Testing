/**
 * 
 */
package com.borngroup.ssl.core.services.sitemap.renderer;

import de.hybris.platform.acceleratorservices.enums.SiteMapChangeFrequencyEnum;
import de.hybris.platform.acceleratorservices.enums.SiteMapPageEnum;
import de.hybris.platform.acceleratorservices.model.SiteMapPageModel;
import de.hybris.platform.acceleratorservices.sitemap.renderer.SiteMapContext;
import de.hybris.platform.cms2.model.site.CMSSiteModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * @author tarun.ranka
 * 
 */
public class SSLSiteMapContext extends SiteMapContext {
	@Override
	public void init(final CMSSiteModel site,
			final SiteMapPageEnum siteMapPageEnum) {
		final String currentUrlEncodingPattern = getUrlEncoderService()
				.getCurrentUrlEncodingPattern();
		this.put(
				BASE_URL,
				getSiteBaseUrlResolutionService().getWebsiteUrlForSite(site,
						currentUrlEncodingPattern, true, ""));
		this.put(MEDIA_URL, getSiteBaseUrlResolutionService()
				.getMediaUrlForSite(site, true, ""));

		final Collection<SiteMapPageModel> siteMapPages = site
				.getSiteMapConfig().getSiteMapPages();
		final SiteMapPageModel siteMapPageModel = (SiteMapPageModel) CollectionUtils
				.find(siteMapPages, new Predicate() {
					@Override
					public boolean evaluate(final Object o) {
						return ((SiteMapPageModel) o).getCode().equals(
								siteMapPageEnum);
					}
				});

		if (siteMapPageModel != null) {
			this.put(CHANGE_FREQ, siteMapPageModel.getFrequency().getCode());
			this.put(PRIORITY, siteMapPageModel.getPriority());
		} else {
			this.put(CHANGE_FREQ, SiteMapChangeFrequencyEnum.DAILY.getCode());
			this.put(PRIORITY, Double.valueOf(0.5D));
		}
	}
}
