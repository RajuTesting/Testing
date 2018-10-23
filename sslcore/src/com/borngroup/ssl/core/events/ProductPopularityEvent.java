/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author TO-W7GL-06
 *
 */
public class ProductPopularityEvent extends AbstractEvent implements ClusterAwareEvent {

	private final ProductModel productModel;
	private final int poularityValue;

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId) {
		return (sourceNodeId == targetNodeId);
	}

	/**
	 * @return the productModel
	 */
	public ProductModel getProductModel() {
		return productModel;
	}

	/**
	 * @return the poularityValue
	 */
	public int getPoularityValue() {
		return poularityValue;
	}

	/**
	 * @param productModel
	 * @param poularityValue
	 */
	public ProductPopularityEvent(final ProductModel productModel, final int poularityValue) {
		this.productModel = productModel;
		this.poularityValue = poularityValue;
	}

}
