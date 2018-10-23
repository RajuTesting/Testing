/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import javax.annotation.Resource;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.ProductPopularityEvent;
import com.borngroup.ssl.core.product.service.SslProductService;

/**
 * @author TO-W7GL-06
 *
 */
public class ProductPopularityEventListner extends AbstractEventListener<ProductPopularityEvent> {

	@Resource(name = "sslProductService")
	private SslProductService sslProductService;
	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;
	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Override
	protected void onEvent(final ProductPopularityEvent productPopularityEvent) {
		catalogVersionService.setSessionCatalogVersion(SslCoreConstants.CATALOG_NAME,
				SslCoreConstants.ONLINE_CATALOG_VERSION);

		this.getSessionService().executeInLocalView(new SessionExecutionBody() {
			@Override
			public void executeWithoutResult() {
				final JaloSession jaloSession = JaloSession.getCurrentSession();
				jaloSession.getSessionContext().setAttribute("disableRestrictions", Boolean.TRUE);
				sslProductService.updatePopularity(productPopularityEvent.getProductModel(),
						productPopularityEvent.getPoularityValue());
			}
		});
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService() {
		return sessionService;
	}

}
