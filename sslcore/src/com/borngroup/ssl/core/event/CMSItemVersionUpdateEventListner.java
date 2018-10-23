/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.CMSItemVersionUpdateEvent;
import com.borngroup.ssl.core.model.SSLMobileContentPageModel;
import com.borngroup.ssl.core.model.SSLMobileWidgetComponentModel;

/**
 * @author Nagarro Dev
 *
 */
public class CMSItemVersionUpdateEventListner extends AbstractEventListener<CMSItemVersionUpdateEvent> {

	private static final Logger Log = Logger.getLogger(CMSItemVersionUpdateEventListner.class);

	@Resource(name = "cmsPageService")
	private CMSPageService cmsPageService;
	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;
	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;
	@Resource(name = "modelService")
	private ModelService modelService;
	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Override
	protected void onEvent(final CMSItemVersionUpdateEvent event) {
		if (null != event.getComponentModel()) {
			updateStagedComponentVersion(event);
			updateStagedPageVersion(event);
		}
	}

	/**
	 * @param event
	 */
	private void updateStagedComponentVersion(final CMSItemVersionUpdateEvent event) {
		if (event.isUpdateComponent()) {
			sessionService.executeInLocalView(new SessionExecutionBody() {
				@Override
				public void executeWithoutResult() {
					final JaloSession jaloSession = JaloSession.getCurrentSession();
					jaloSession.getSessionContext().setAttribute("disableRestrictions", Boolean.TRUE);
					try {
						AbstractCMSComponentModel component = null;
						component = cmsComponentService.getAbstractCMSComponent(event.getComponentModel().getUid(),
								Arrays.asList(catalogVersionService.getCatalogVersion(SslCoreConstants.CONTENTCATALOG,
										SslCoreConstants.CATALOG_VERSION)));
						if (null != component && component instanceof SSLMobileWidgetComponentModel) {
							final Long updatedComponentVersion = updateVersionValue(
									event.getComponentModel().getVersion());
							((SSLMobileWidgetComponentModel) component).setVersion(updatedComponentVersion);
							modelService.save(component);
						}
					} catch (final CMSItemNotFoundException e) {
						Log.error("Error in updating version of component : " + event.getComponentModel().getUid());
					}
				}
			});
		}
	}

	/**
	 * @param event
	 */
	private void updateStagedPageVersion(final CMSItemVersionUpdateEvent event) {
		if (event.isUpdatePage()) {
			sessionService.executeInLocalView(new SessionExecutionBody() {
				@Override
				public void executeWithoutResult() {
					final JaloSession jaloSession = JaloSession.getCurrentSession();
					jaloSession.getSessionContext().setAttribute("disableRestrictions", Boolean.TRUE);
					catalogVersionService.setSessionCatalogVersion(SslCoreConstants.CONTENTCATALOG,
							SslCoreConstants.CATALOG_VERSION);
					try {
						AbstractCMSComponentModel component = null;
						component = cmsComponentService.getAbstractCMSComponent(event.getComponentModel().getUid());
						if (null != component && component instanceof SSLMobileWidgetComponentModel) {
							final Collection<AbstractPageModel> pages = cmsPageService.getPagesForComponent(component);
							if (CollectionUtils.isNotEmpty(pages)) {
								pages.stream().filter(SSLMobileContentPageModel.class::isInstance)
										.map(SSLMobileContentPageModel.class::cast).forEach(page -> {
											page.setVersion(updateVersionValue(page.getVersion()));
											modelService.save(page);
										});
							}
						}
					} catch (final CMSItemNotFoundException e) {
						Log.error("Error in updating version of component : " + event.getComponentModel().getUid());
					}
				}
			});
		}
	}

	private Long updateVersionValue(Long version) {
		if (version != null) {
			if (version.longValue() > 0) {
				version = Long.valueOf(version.longValue() + 1);
			} else {
				version = Long.valueOf(1);
			}
		} else {
			version = Long.valueOf(1);
		}
		return version;
	}
}
