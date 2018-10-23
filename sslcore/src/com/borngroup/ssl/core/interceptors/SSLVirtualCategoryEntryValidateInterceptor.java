/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.model.VirtualCategoryEntryModel;

/**
 * Interceptor : Validate VirtualCategoryEntryModel before saving.
 *
 * Created by harman.kaur@nagarro.com.
 *
 * @author SSL
 */
public class SSLVirtualCategoryEntryValidateInterceptor implements ValidateInterceptor<VirtualCategoryEntryModel> {

    /** The Constant NO_ATTRIBUTE_TYPE_VALIDATION_MSG. */
    private static final String NO_ATTRIBUTE_TYPE_VALIDATION_MSG = "No Category Associated with Classification Attribute type.Please enter valid Classification Attribute type";

    /** The Constant EMPTY_ATTRIBUTE_VALIDATION_MSG. */
    private static final String EMPTY_ATTRIBUTE_VALIDATION_MSG = "Classification Class corresponding to Classification Attribute type is empty.Please enter valid Classification Attribute type";

    /** The Constant CATEGORY_VIRTUAL_VALIDATION_MSG. */
    private static final String CATEGORY_VIRTUAL_VALIDATION_MSG = "Please enter category which is not of virtual type";

    /** The Constant CATEGORY_EMPTY_VALIDATION_MSG. */
    private static final String CATEGORY_EMPTY_VALIDATION_MSG = "Please add Category for Product Selection";

    /** The Constant CLASS_CLASSIFCATION_VALIDATION_MSG. */
    private static final String CLASS_CLASSIFCATION_VALIDATION_MSG = "Classification Attribute Value doesn't belong to Class Attribute Assignment.Please add valid Classification Attribute Value";

    /** The Constant CLASS_ATTRIBUTE_VALIDATION_MSG. */
    private static final String CLASS_ATTRIBUTE_VALIDATION_MSG = "Please add Classification Assignment corresponding to Classification Attribute Value";

    /** The Constant CLASSIFICATION_ATTR_VALIDATION_MSG. */
    private static final String CLASSIFICATION_ATTR_VALIDATION_MSG = "Please add Classification Attribute Value corresponding to Classification Assignment";

    /** The Constant EMPTY_FIELDS_VALIDATION_MSG. */
    private static final String EMPTY_FIELDS_VALIDATION_MSG = "Please enter valid Brand Code or Classification Attributes";

    /** The Constant INVALID_CATEGORY_VALIDATION_MSG. */
    private static final String INVALID_CATEGORY_VALIDATION_MSG = "Category given in Selection Criteria doesn't match with Classifying Categories";

    /**
     * . Method to validate VirtualCategoryEntryModel attributes
     *
     * @param virtualCategoryEntryModel
     *        {@link VirtualCategoryEntryModel}
     * @param interceptorContext
     *        {@link InterceptorContext}
     *
     */
    @Override
    public void onValidate(final VirtualCategoryEntryModel virtualCategoryEntryModel, final InterceptorContext interceptorContext)
            throws InterceptorException {
        if (virtualCategoryEntryModel != null) {
            final ClassAttributeAssignmentModel classAttributeAssignmentModel = virtualCategoryEntryModel.getClassAttributeAssignment();
            final ClassificationAttributeValueModel classificationAttributeValueModel = virtualCategoryEntryModel
                    .getClassificationAttributeValue();
            final CategoryModel categoryModel = virtualCategoryEntryModel.getCategory();
            final String brandCode = virtualCategoryEntryModel.getBrandCode();
            if ((categoryModel == null) && ((StringUtils.isEmpty(virtualCategoryEntryModel.getBrandCode()))
                    || classAttributeAssignmentModel != null || classificationAttributeValueModel != null)) {
                throw new InterceptorException(CATEGORY_EMPTY_VALIDATION_MSG);
            } else if (StringUtils.isEmpty(brandCode) && classAttributeAssignmentModel == null
                    && classificationAttributeValueModel == null) {
                throw new InterceptorException(EMPTY_FIELDS_VALIDATION_MSG);
            } else if (categoryModel == null) {
                throw new InterceptorException(CATEGORY_EMPTY_VALIDATION_MSG);
            } else {
                classificationAttributeValidation(classAttributeAssignmentModel, classificationAttributeValueModel, categoryModel);
            }
        }
    }

    /**
     * Method for Classification attribute validation.
     *
     * @param classAttributeAssignmentModel
     *        the class attribute assignment model {@link ClassAttributeAssignmentModel}
     * @param classificationAttributeValueModel
     *        the classification attribute value model {@link ClassificationAttributeValueModel}
     * @param categoryModel
     *        the category model {@link CategoryModel}
     * @throws InterceptorException
     *         the interceptor exception {@link InterceptorException}
     */
    private void classificationAttributeValidation(final ClassAttributeAssignmentModel classAttributeAssignmentModel,
            final ClassificationAttributeValueModel classificationAttributeValueModel, final CategoryModel categoryModel)
            throws InterceptorException {
        if (categoryModel.getIsVirtualCategory() != null && categoryModel.getIsVirtualCategory().equals(Boolean.TRUE)) {
            throw new InterceptorException(CATEGORY_VIRTUAL_VALIDATION_MSG);
        } else if (classAttributeAssignmentModel == null && classificationAttributeValueModel != null) {
            throw new InterceptorException(CLASS_ATTRIBUTE_VALIDATION_MSG);
        } else if (classAttributeAssignmentModel != null) {
            if (classificationAttributeValueModel == null) {
                throw new InterceptorException(CLASSIFICATION_ATTR_VALIDATION_MSG);
            }
            classificationClassRelationValidation(classAttributeAssignmentModel, classificationAttributeValueModel, categoryModel);
        }
    }

    /**
     * Method for Classification class relation validation.
     *
     * @param classAttributeAssignmentModel
     *        the class attribute assignment model {@link ClassAttributeAssignmentModel}
     * @param classificationAttributeValueModel
     *        the classification attribute value model {@link ClassificationAttributeValueModel}
     * @param category
     *        the category {@link CategoryModel}
     * @throws InterceptorException
     *         the interceptor exception {@link InterceptorException}
     */
    private void classificationClassRelationValidation(final ClassAttributeAssignmentModel classAttributeAssignmentModel,
            final ClassificationAttributeValueModel classificationAttributeValueModel, final CategoryModel category)
            throws InterceptorException {
        final ClassificationClassModel classificationClassModel = classAttributeAssignmentModel.getClassificationClass();
        if (classificationClassModel != null) {
            classificationClassValidation(category, classificationClassModel);
        } else {
            throw new InterceptorException(EMPTY_ATTRIBUTE_VALIDATION_MSG);
        }
        if (classificationAttributeValueModel != null) {
            final List<ClassificationAttributeValueModel> classificationAttributeValueModels = classAttributeAssignmentModel
                    .getAttributeValues();
            if (!classificationAttributeValueModels.contains(classificationAttributeValueModel)) {
                throw new InterceptorException(CLASS_CLASSIFCATION_VALIDATION_MSG);
            }
        }
    }

    /**
     * Method for Classification class validation.
     *
     * @param category
     *        the category {@link CategoryModel}
     * @param classificationClassModel
     *        the classification class model {@link ClassificationClassModel}
     * @throws InterceptorException
     *         the interceptor exception {@link InterceptorException}
     */
    private void classificationClassValidation(final CategoryModel category, final ClassificationClassModel classificationClassModel)
            throws InterceptorException {
        final List<CategoryModel> categoryModels = classificationClassModel.getCategories();

        if (CollectionUtils.isNotEmpty(categoryModels)) {
            int count = 0;
            for (final CategoryModel categoryModel2 : categoryModels) {
                if ((!((categoryModel2.getIsVirtualCategory() != null) && (Boolean.TRUE.equals(categoryModel2.getIsVirtualCategory()))))
                        && (categoryModel2.getCode().equals(category.getCode()))) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                throw new InterceptorException(INVALID_CATEGORY_VALIDATION_MSG);
            }
        } else {
            throw new InterceptorException(NO_ATTRIBUTE_TYPE_VALIDATION_MSG);
        }
    }

}
