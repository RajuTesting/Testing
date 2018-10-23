package com.borngroup.ssl.core.event;

import java.time.Duration;
import java.time.Instant;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.PreOrderQuantityUpdateEvent;
import com.borngroup.ssl.core.product.service.impl.SslDefaultProductService;
import com.borngroup.ssl.core.services.SslCommerceStockService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import javax.annotation.Resource;

/**
 * Fires when order is placed, it updates  -
 *  Pre order quantity of product.
 */
	public class PreOrderQuantityUpdateEventListener extends AbstractEventListener<PreOrderQuantityUpdateEvent> {

	private static final Logger LOG = Logger.getLogger(PreOrderQuantityUpdateEventListener.class);

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;
	@Resource(name = "sslStockService")
	private SslCommerceStockService sslStockService;
	@Resource(name = "modelService")
	private ModelService modelService;
	@Resource(name = "searchRestrictionService")
	private SearchRestrictionService searchRestrictionService;
	@Resource(name = "sslProductService")
	private SslDefaultProductService sslProductService;

	@Override
	protected void onEvent(final PreOrderQuantityUpdateEvent event) {
		OrderModel orderModel = event.getOrder();
		final Instant start = Instant.now();
		searchRestrictionService.disableSearchRestrictions();
		if (null == orderModel.getEntries()) {
			return;
		}
		for (final AbstractOrderEntryModel orderEntry : orderModel.getEntries()) {
			final Long quantity = orderEntry.getQuantity();
			final Long stockLevel = sslStockService.getStockLevelForProductAndBaseStore(orderEntry.getProduct(),
					orderModel.getStore());
			if (null != stockLevel && null != orderEntry.getProduct().getPreOrder() && orderEntry.getProduct().getPreOrder().booleanValue()
					&& null != orderEntry.getProduct().getPreOrderQty()) {
				// If stock was 5, preorder was 5 and order placed for 7, then
				// here it will be (10-5-7=-2) and 2 is
				// taken from preorder, 10 because stockservice is giving
				// quantity after adding preorder quantity.
				final long stockValuePreorderAdditionalValue = stockLevel.longValue() - orderEntry.getProduct().getPreOrderQty().longValue()
						- quantity.longValue();
				if ((stockValuePreorderAdditionalValue) < 0) {
					final long preOrderQty = orderEntry.getProduct().getPreOrderQty().longValue()
							- Math.abs(stockValuePreorderAdditionalValue);

					modelService.enableTransactions();
					final ProductModel stagedProduct = sslProductService.getProductForCode(
							catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION),
							orderEntry.getProduct().getCode());
					if (null != stagedProduct) {
						stagedProduct.setPreOrderQty(Long.valueOf(preOrderQty));
						modelService.save(stagedProduct);
					}
					final ProductModel onlineProduct = sslProductService.getProductForCode(catalogVersionService
									.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.ONLINE_CATALOG_VERSION),
							orderEntry.getProduct().getCode());
					if (null != onlineProduct) {
						onlineProduct.setPreOrderQty(Long.valueOf(preOrderQty));
						modelService.save(onlineProduct);
					}
					modelService.disableTransactions();
				}
			}
		}
		searchRestrictionService.enableSearchRestrictions();
		final Instant end = Instant.now();
		LOG.info(String.format("Spent %s time to save preorderQuantity for order= %s", Duration.between(start, end), orderModel.getCode()));
	}
}
