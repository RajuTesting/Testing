package com.borngroup.ssl.core.interceptors;

import com.borngroup.ssl.core.model.PromotionPercentageDiscountRowModel;
import com.borngroup.ssl.core.model.SslProductBOGODiscountPromotionModel;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * @author Nagarro Dev
 *
 */
public class SslProductBOGODiscountPromotionValidateInterceptor implements ValidateInterceptor<SslProductBOGODiscountPromotionModel> {

    @Override public void onValidate(SslProductBOGODiscountPromotionModel sslProductBOGODiscountPromotionModel,
            InterceptorContext interceptorContext) throws InterceptorException {
        if (null != sslProductBOGODiscountPromotionModel) {
            final Collection<PromotionPercentageDiscountRowModel> discounts = sslProductBOGODiscountPromotionModel.getDiscounts();
            if (CollectionUtils.isNotEmpty(discounts) && discounts.size() > 1) {
                throw new InterceptorException("Discounts size should be one");
            } else if (CollectionUtils.isNotEmpty(discounts)){
                for(PromotionPercentageDiscountRowModel discount : discounts){
                    if(Double.valueOf("100").compareTo(discount.getPercentageDiscount()) == 0){
                        throw new InterceptorException("Discount percentage can not be equal to 100.");
                    }
                }
            }
        }
    }
}
