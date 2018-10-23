package com.borngroup.ssl.core.event;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.events.OrderFlagsUpdateAsyncEvent;
import com.borngroup.ssl.core.services.SSLUserService;
import com.borngroup.ssl.loyalty.model.SslLoyaltyDetailModel;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

/**
 * Fires when order is placed, it updates 2 flags -
 *  isFirstOrder (for this user and guest)
 *  isLoyaltyOrder
 */
	public class OrderFlagsUpdateAsyncEventListener extends AbstractEventListener<OrderFlagsUpdateAsyncEvent> {

	private static final Logger LOG = Logger.getLogger(OrderFlagsUpdateAsyncEventListener.class);

	@Resource(name = "userService")
	private SSLUserService userService;
	@Resource(name = "modelService")
	private ModelService modelService;

	@Override
	protected void onEvent(final OrderFlagsUpdateAsyncEvent event) {
		OrderModel orderModel = event.getOrder();
		final Instant start = Instant.now();
		if (orderModel != null) {
			final UserModel userModel = orderModel.getUser();
			if (userModel instanceof CustomerModel) {
				final CustomerModel customerModel = (CustomerModel) userModel;
				final List<CustomerModel> customerModels = userService.getUserByEmail(customerModel);
				orderModel.setFirstOrderFlag(Boolean.TRUE);
				orderModel.setLoyaltyOrderFlag(Boolean.FALSE);
				if (CollectionUtils.isNotEmpty(customerModels)) {
					for (final CustomerModel custModel : customerModels) {
							setLoyaltyOrderFlag(orderModel, event.getFccNumber(), custModel);
							setFirstOrderFlag(orderModel, custModel);
					}
				}
			}
		}
		modelService.save(orderModel);
		final Instant end = Instant.now();
		LOG.info(String.format("Spent %s time to save order flags", Duration.between(start, end)));
	}
	/**
	 * Updates first order flag.
	 *
	 * @param orderModel
	 *        {@link OrderModel}
	 * @param custModel
	 *        {@link CustomerModel}
	 * @return boolean
	 */
	private void setFirstOrderFlag(final OrderModel orderModel, final CustomerModel custModel) {
		//save calls if we already know its not a first order.
		if(orderModel.getFirstOrderFlag()) {
			final Collection<OrderModel> orders = custModel.getOrders();
			if (CollectionUtils.isNotEmpty(orders)) {
				for (final OrderModel order : orders) {
					if ((!order.getCode().equals(orderModel.getCode())) && !OrderStatus.CANCELLED.equals(order.getStatus()) && null == order
							.getVersionID()) {
						orderModel.setFirstOrderFlag(Boolean.FALSE);
							LOG.debug(String.format("Setting current order flag to false because customer - %s exists with order %s",
									custModel.getOriginalUid(), order.getCode()));
						break;
					}
				}
			}
		}
	}

	/**
	 * Updates loyalty order flag.
	 *
	 * @param orderModel
	 *        {@link OrderModel}
	 * @param fccCardNumber
	 *        fcc card number
	 * @param custModel
	 *        {@link CustomerModel}
	 * @param isLoyalityCardLinked
	 *        is loyalty card linked
	 * @return boolean
	 */
	private void setLoyaltyOrderFlag(final OrderModel orderModel, final String fccCardNumber, final CustomerModel custModel) {
		//save calls if we already know its a loyalty order.
		if (!orderModel.getLoyaltyOrderFlag()) {
			boolean isLoyalityCardLinked = false;
			if (null != custModel.getSslLoyaltyDetail()) {
				final SslLoyaltyDetailModel sslLoyaltyDetailModel = custModel.getSslLoyaltyDetail();
				if (sslLoyaltyDetailModel != null && sslLoyaltyDetailModel.getIsLinked() != null) {
					isLoyalityCardLinked = sslLoyaltyDetailModel.getIsLinked().booleanValue();
				}
			}
			if (isLoyalityCardLinked || null != fccCardNumber) {
				orderModel.setLoyaltyOrderFlag(Boolean.TRUE);
			}
		}
	}
}
