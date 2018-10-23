/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.PSAppointmentModel;

/**
 * @author S53299
 *
 */
public class PSAppointmentInterceptor implements PrepareInterceptor<PSAppointmentModel> {

	Logger log = Logger.getLogger(PSAppointmentInterceptor.class);

	private KeyGenerator psAppointmentCodeGenerator;

	public KeyGenerator getPsAppointmentCodeGenerator() {
		return psAppointmentCodeGenerator;
	}

	public void setPsAppointmentCodeGenerator(final KeyGenerator psAppointmentCodeGenerator) {
		this.psAppointmentCodeGenerator = psAppointmentCodeGenerator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(
	 * java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final PSAppointmentModel appointmentModel, final InterceptorContext paramInterceptorContext)
			throws InterceptorException {

		log.info("------------inside PSAppointmentInterceptor--------");

		if (null == appointmentModel.getPk()) {
			appointmentModel.setCode(psAppointmentCodeGenerator.generate().toString());
		}

	}

}
