/**
 * 
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.promotions.model.ProductBundlePromotionModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;


/**
 * @author Viji
 * 
 */
public class SSLProductBundlePromotionValidateInterceptor implements ValidateInterceptor<ProductBundlePromotionModel>
{

    /* (non-Javadoc)
     * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object, de.hybris.platform.servicelayer.interceptor.InterceptorContext)
     */
    @Override
    public void onValidate(final ProductBundlePromotionModel promotionModel, final InterceptorContext arg1)
            throws InterceptorException
    {
        boolean isInvalidDate = false;

        if ((promotionModel.getStartDate().compareTo(promotionModel.getEndDate())) > 0)
        {
            isInvalidDate = true;
        }

        if (isInvalidDate)
        {
            throw new InterceptorException("End date should be greater than start date. ");

        }

    }

}
