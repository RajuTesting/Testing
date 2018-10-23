/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.validation.services.ValidationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.events.OrderRefundAmountEvent;
import com.borngroup.ssl.core.events.ReturnRequestEvent;

/**
 *
 * @author phuc.dang
 *
 */
public class SSLOrderStatusValidateInterceptor implements
		ValidateInterceptor<OrderModel> {

	private static final Logger LOG = Logger
			.getLogger(SSLOrderStatusValidateInterceptor.class);

	@Autowired
	private ValidationService validationService;

	@Autowired
	private EventService eventService;

	@Override
	public void onValidate(final OrderModel orderModel,
			final InterceptorContext arg1) throws InterceptorException {
//RETURN_REJECT
		if (OrderStatus.RETURN_APPROVED.equals(orderModel.getStatus())) {
			LOG.info("----------Send mail------------");
			validationService.validate(orderModel);
			eventService.publishEvent(new OrderRefundAmountEvent(orderModel));
		}
		
		if(OrderStatus.RETURN_REJECT.equals(orderModel.getStatus()))
		{
		    eventService.publishEvent(new ReturnRequestEvent(orderModel.getOrderProcess().iterator().next()));
		}

	}
}
