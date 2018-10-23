/**
 *
 */
package com.ssl.core.sterling.services;

import de.hybris.platform.core.model.order.OrderModel;

import com.ssl.core.sterling.orderpush.dto.SSLSterlingCreateSalesOrderRequestDTO;

/**
 * @author manikmalhotra
 *
 */
public interface SSLSterlingCreateSalesOrderService {

    boolean pushSalesOrderToSterling(OrderModel order);

    SSLSterlingCreateSalesOrderRequestDTO getPushOrderRequestDTO(OrderModel order);

}
