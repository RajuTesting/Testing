/**
 * 
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.validation.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Viji
 * 
 */
public class SSLProductValidateInterceptor implements ValidateInterceptor<ProductModel>
{

    @Autowired
    ValidationService validationService;

    @Override
    public void onValidate(final ProductModel productModel, final InterceptorContext arg1) throws InterceptorException
    {
        /*if (productModel.getOnlineDate() != null && productModel.getOfflineDate() != null
        		&& productModel.getOnlineDate().compareTo(productModel.getOfflineDate()) > 0)
        {
        	throw new DefaultInterceptorException("Offline date must be greater the online date.");
        }*/
        //validationService.validate(productModel);
    }


}
