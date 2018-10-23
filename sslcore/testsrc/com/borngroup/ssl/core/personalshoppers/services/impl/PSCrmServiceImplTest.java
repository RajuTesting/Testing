/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import javax.validation.constraints.AssertTrue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.util.CommonHelper;
import com.borngroup.ssl.core.util.models.APIInputObject;
import com.ssl.core.ps.crm.dto.PSAppointmentRequest;
import com.ssl.core.ps.crm.dto.PSBookingAppointmentRequest;
import com.ssl.core.ps.crm.dto.PSBookingAppointmentResponse;

/**
 * <p>
 * PSCrmServiceImplTest.java : Test class to get book PS appointment with all the details
 * city store details
 * <p>
 * Created By : sana.ambreen@techouts.com
 * <p>
 * @author Techouts. 
 *
 */
@UnitTest
public class PSCrmServiceImplTest {

	@InjectMocks
	PSCrmServiceImpl pSCrmServiceImpl;

	@InjectMocks
	CommonHelper commonHelper = CommonHelper.getInstance();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		pSCrmServiceImpl = new PSCrmServiceImpl();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void createBookingAppointmentTest(){
		try {
		PSBookingAppointmentResponse pSBookingAppointmentResponse = new PSBookingAppointmentResponse();
		pSBookingAppointmentResponse.setResponseCode("200");
		
		PSAppointmentModel appointment= new PSAppointmentModel();
		appointment.setCode("00046000");
		
		CustomerData customerData= new CustomerData();
		PSBookingAppointmentRequest psAppointmentRequest = new  PSBookingAppointmentRequest();	
		
		APIInputObject apiInputObject=new APIInputObject();
		Assert.assertNotNull(psAppointmentRequest);
		
		when(commonHelper.convertToJSONString(Matchers.any(PSBookingAppointmentRequest.class))).thenReturn(Matchers.anyString());
		when
		(commonHelper.getAPIInputObject(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),Matchers.any(ContentTypeEnum.class),
				Matchers.any(APIMethodEnum.class), Matchers.anyBoolean())).thenReturn(apiInputObject);
		when(commonHelper.callAPIJSONResponse(apiInputObject,PSBookingAppointmentResponse.class)).thenReturn(pSBookingAppointmentResponse);
	
		PSBookingAppointmentResponse finalResponse=pSCrmServiceImpl.createBookingAppointment(appointment,customerData);
		Assert.assertEquals("200", finalResponse.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
