/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.validation.services.ValidationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.SSLCollectionsModel;

/**
 * @author harmanKaur
 *
 */
public class SSLCollectionsValidateInterceptor implements ValidateInterceptor<SSLCollectionsModel> {

    @Autowired
    ValidationService validationService;

    @Override
    public void onValidate(final SSLCollectionsModel productModel, final InterceptorContext arg1) throws InterceptorException {

        if (productModel != null) {
            final List<ProductModel> productModels = productModel.getProducts();
            if (productModels != null && productModels.size() > 0) {
                for (final ProductModel productInCollection : productModels) {
                    if (productInCollection instanceof ApparelSizeVariantProductModel) {
                        throw new InterceptorException(
                                "This product Model is of type ApparelSizeVariant .Please add valid Product into collections");
                    }
                }
            }
        }
    }

}
