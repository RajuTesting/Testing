/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.model.VirtualCategoryEntryModel;

/**
 * Interceptor : Validate CategoryModel before saving.
 *
 * Created by harman.kaur@nagarro.com.
 *
 * @author SSL
 */
public class SSLCategoryValidateInterceptor implements ValidateInterceptor<CategoryModel> {

    /** The Constant INVALID_SUPERCATEGORY_MSG. */
    private static final String INVALID_SUPERCATEGORY_MSG = "Super Category cannot be of type Virtual Category. Please enter valid Super Category";

    /** The Constant INVALID_PRODUCT_SELECTION_CRITERIA. */
    private static final String INVALID_PRODUCT_SELECTION_CRITERIA = "No Product Selection Criteria should be associated with Normal Category";

    /**
     * . Method to validate CategoryModel attributes
     *
     * @param categoryModel
     *        {@link CategoryModel}
     * @param interceptorContext
     *        {@link InterceptorContext}
     *
     */
    @Override
    public void onValidate(final CategoryModel categoryModel, final InterceptorContext interceptorContext) throws InterceptorException {
        if (categoryModel != null) {
            virtualCategoryValidations(categoryModel);
        }
    }

    /**
     * . Method for Virtual category validations.
     *
     * @param categoryModel
     *        the category model {@link CategoryModel}
     * @throws InterceptorException
     *         the interceptor exception {@link InterceptorException}
     */
    private void virtualCategoryValidations(final CategoryModel categoryModel) throws InterceptorException {
        if (!(categoryModel.getIsVirtualCategory() != null && Boolean.TRUE.equals(categoryModel.getIsVirtualCategory()))) {
            final List<VirtualCategoryEntryModel> virtualCategoryEntries = categoryModel.getVirtualCategoryEntries();
            if (CollectionUtils.isNotEmpty(virtualCategoryEntries)) {
                throw new InterceptorException(INVALID_PRODUCT_SELECTION_CRITERIA);
            }
        }
        final Collection<CategoryModel> categoryModels = categoryModel.getAllSupercategories();
        if (CollectionUtils.isNotEmpty(categoryModels)) {
            for (final CategoryModel categoryModel2 : categoryModels) {
                if (categoryModel2.getIsVirtualCategory() != null && Boolean.TRUE.equals(categoryModel2.getIsVirtualCategory())) {
                    throw new InterceptorException(INVALID_SUPERCATEGORY_MSG);
                }
            }
        }
    }

}
