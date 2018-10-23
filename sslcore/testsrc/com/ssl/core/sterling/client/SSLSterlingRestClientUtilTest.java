/**
 *
 */
package com.ssl.core.sterling.client;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.ssl.core.sterling.exception.SterlingException;
import com.ssl.core.sterling.orderDetails.dto.OrderDetailsRequestDTO;
import com.ssl.core.sterling.orderDetails.dto.OrderDetailsResponseDTO;
import com.ssl.core.sterling.orderList.dto.OrderDetailRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderInputRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderListApiDTO;
import com.ssl.core.sterling.orderList.dto.OrderListRequestDTO;
import com.ssl.core.sterling.orderList.dto.OrderListResponseDTO;

/**
 * @author pankajgandhi
 *
 */
@UnitTest
public class SSLSterlingRestClientUtilTest extends ServicelayerTransactionalTest {

	@Resource
	private SSLSterlingRestClientUtil sslSterlingRestClientUtil;

	@Test
	public void testGetOrderList() throws SterlingException {

		final OrderListRequestDTO requestDTO = new OrderListRequestDTO();
		final OrderListApiDTO api = new OrderListApiDTO();
		final OrderInputRequestDTO input = new OrderInputRequestDTO();
		final OrderDetailRequestDTO order = new OrderDetailRequestDTO();

		requestDTO.setStartRowNumber("1");
		requestDTO.setPageSize("10");
		requestDTO.setPaginationStrategy("GENERIC");

		api.setInput(input);
		api.setIsFlow("N");
		api.setName("getOrderList");

		order.setBillToId("");
		order.setEnterpriseCode("SSL");
		order.setIgnoreOrdering("Y");

		input.setOrder(order);
		api.setInput(input);
		requestDTO.setApiData(api);

		final OrderListResponseDTO response = sslSterlingRestClientUtil.getOrderList(requestDTO, 0);

		Assert.assertNotNull(response);

	}

	@Test
	public void testGetOrderDetails() throws SterlingException {

		final OrderDetailsRequestDTO requestDTO = new OrderDetailsRequestDTO();
		requestDTO.setOrderNo("Y100000322");
		requestDTO.setEnterpriseCode("SSL");

		final OrderDetailsResponseDTO response = sslSterlingRestClientUtil.getOrderDetails(requestDTO, 0);

		Assert.assertNotNull(response);

	}

}
