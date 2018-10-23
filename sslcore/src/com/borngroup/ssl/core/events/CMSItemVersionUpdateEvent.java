/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import com.borngroup.ssl.core.model.SSLMobileWidgetComponentModel;

/**
 * @author Nagarro Dev
 *
 */
public class CMSItemVersionUpdateEvent extends AbstractEvent implements ClusterAwareEvent {

	private final SSLMobileWidgetComponentModel componentModel;
	private final boolean updateComponent;
	private final boolean updatePage;

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId) {
		return (sourceNodeId == targetNodeId);
	}

	/**
	 * @param componentModel
	 */
	public CMSItemVersionUpdateEvent(final SSLMobileWidgetComponentModel componentModel, final boolean updateComponent,
			final boolean updatePage) {
		this.componentModel = componentModel;
		this.updateComponent = updateComponent;
		this.updatePage = updatePage;
	}

	/**
	 * @return the componentModel
	 */
	public SSLMobileWidgetComponentModel getComponentModel() {
		return componentModel;
	}

	/**
	 * @return the updateComponent
	 */
	public boolean isUpdateComponent() {
		return updateComponent;
	}

	/**
	 * @return the updatePage
	 */
	public boolean isUpdatePage() {
		return updatePage;
	}

}
