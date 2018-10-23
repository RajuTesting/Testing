package com.borngroup.ssl.core.interceptors;

import com.borngroup.ssl.core.model.PreferredPaymentGroupModel;
import com.borngroup.ssl.core.model.PreferredPaymentModel;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interceptor: Checks that no duplicate entry for Preferred Payment
 * should be added in the PreferredPaymentGroupModel
 * @author nagarro_dev
 */
public class PreferredPaymentInterceptor implements PrepareInterceptor<PreferredPaymentGroupModel> {

    @Override
    public void onPrepare(PreferredPaymentGroupModel preferredPaymentGroupModel, InterceptorContext interceptorContext) throws InterceptorException {
        if(interceptorContext.isModified(preferredPaymentGroupModel, PreferredPaymentGroupModel.PREFERRED)){
            List<PreferredPaymentModel> preferredPaymentModelList = preferredPaymentGroupModel.getPreferred();
            Set<PreferredPaymentModel> preferredPaymentModelSet = new HashSet<>();

            for(PreferredPaymentModel preferredPayment: preferredPaymentModelList){
                if(!preferredPaymentModelSet.add(preferredPayment)){
                    throw new InterceptorException("Preferred payment already exists in the list");
                }
            }
        }
    }

}
