/**
 *
 */
package com.ssl.core.validator;

import de.hybris.platform.core.model.user.CustomerModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Techouts
 *
 */
public class CustomerModelValidator implements Validator {
	@Override
	public boolean supports(final Class<?> paramClass) {
		return CustomerModel.class.equals(paramClass);
	}

	@Override
	public void validate(final Object paramObject, final Errors errors) {
		final CustomerModel customer = (CustomerModel) paramObject;

	    if (StringUtils.isEmpty(customer.getContactEmail())) {
			errors.rejectValue("email", "Customer Emailid should not be empty");
		} else if (!isEmptyMobileNumber(customer)) {
			errors.rejectValue("mobile", "Customer Mobile Number  Should not be empty");
		} else if (customer.getPk() == null) {
			errors.rejectValue("pk", "Customer PK should not be empty");
		} else if (StringUtils.isEmpty(customer.getUid())) {
			errors.rejectValue("uid", "Customer UID should not be empty ");
		}
	}
	
	private boolean isEmptyMobileNumber(CustomerModel customer) {
		if (StringUtils.isEmpty(customer.getMobile())) {
			if (customer.getDefaultShipmentAddress() == null
					|| StringUtils.isEmpty(customer.getDefaultShipmentAddress()
							.getCellphone())) {
				if (customer.getDefaultPaymentAddress() == null
						|| StringUtils.isEmpty(customer
								.getDefaultPaymentAddress().getCellphone())) {
					return false;
				}
			}
		}
		return true;
	}
	
}