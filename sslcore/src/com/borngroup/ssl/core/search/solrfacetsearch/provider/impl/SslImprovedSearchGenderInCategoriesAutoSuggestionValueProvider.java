package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * SslImprovedSearchGenderInCategoriesAutoSuggestionValueProvider. Index gender values along with the L3 category values.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SslImprovedSearchGenderInCategoriesAutoSuggestionValueProvider extends AbstractPropertyFieldValueProvider implements
        FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /** The category level. */
    private Integer categoryLevel;

    /** The category service. */
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {

        final List<FieldValue> fieldValues = new ArrayList();

        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelProductModel = (ApparelProductModel) model;
            final Collection<CategoryModel> allCategories = apparelProductModel.getSupercategories();

            if (CollectionUtils.isNotEmpty(allCategories)) {

                CategoryModel parentCategoryForProduct = null;
                for (final CategoryModel cat : allCategories) {
                    if (cat != null && !(cat instanceof ClassificationClassModel)) {
                        parentCategoryForProduct = cat;
                        this.createFieldValueForCategoryAndGender(parentCategoryForProduct, apparelProductModel, fieldValues,
                                indexedProperty);
                    }
                }

            }
            return fieldValues;
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelProductModel apparelProductModel = (ApparelProductModel) ((ApparelStyleVariantProductModel) model)
                    .getBaseProduct();
            final Collection<CategoryModel> allCategories = apparelProductModel.getSupercategories();

            if (CollectionUtils.isNotEmpty(allCategories)) {

                CategoryModel parentCategoryForProduct = null;
                for (final CategoryModel cat : allCategories) {
                    if (cat != null && !(cat instanceof ClassificationClassModel)) {
                        parentCategoryForProduct = cat;
                        this.createFieldValueForCategoryAndGender(parentCategoryForProduct, apparelProductModel, fieldValues,
                                indexedProperty);
                    }
                }

            }
            return fieldValues;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Creates the field value for Gender and L3 category which is used in auto suggestion.
     *
     * @param parentCategoryForProduct
     *        the parent category
     * @param product
     *        the product
     * @param fieldValues
     *        the field values
     * @param indexedProperty
     *        the indexed property
     *
     */
    private void createFieldValueForCategoryAndGender(final CategoryModel parentCategoryForProduct, final ApparelProductModel product,
            final List<FieldValue> fieldValues, final IndexedProperty indexedProperty) {
        if (parentCategoryForProduct != null) {
            final Collection<List<CategoryModel>> categoryPaths = this.categoryService.getPathsForCategory(parentCategoryForProduct);
            if (CollectionUtils.isNotEmpty(categoryPaths)) {
                final List<CategoryModel> pathForParentCategory = categoryPaths.iterator().next();
                if (null != pathForParentCategory && pathForParentCategory.size() > 3) {

                    if (this.getCategoryLevel() == 3 && pathForParentCategory.get(1).getIsIndexable() != null
                            && pathForParentCategory.get(1).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(2).getIsIndexable() != null
                            && pathForParentCategory.get(2).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(3).getIsIndexable() != null
                            && pathForParentCategory.get(3).getIsIndexable().booleanValue()) {
                        for (final Gender gender : product.getGenders()) {
                            this.addFieldValues(fieldValues, indexedProperty, pathForParentCategory.get(3).getName().toLowerCase() + "_"
                                    + pathForParentCategory.get(2).getName().toLowerCase() + "_" + gender);
                        }
                    }
                }
            }
        }

    }

    /**
     * Adds the field values.
     *
     * @param fieldValues
     *        the field values
     * @param indexedProperty
     *        the indexed property
     * @param value
     *        the value
     */
    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty, final String value) {
        final Collection<String> fieldNames = this.getFieldNameProvider().getFieldNames(indexedProperty, null);
        if (CollectionUtils.isNotEmpty(fieldNames)) {
            for (final String fieldName : fieldNames) {
                fieldValues.add(new FieldValue(fieldName, value));
            }
        }
    }

    /**
     * Gets the category level.
     *
     * @return the categoryLevel
     */
    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * Sets the category level.
     *
     * @param categoryLevel
     *        the categoryLevel to set
     */
    public void setCategoryLevel(final Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * Sets the fieldNameProvider.
     *
     * @param fieldNameProvider
     *        Field Name Provider.
     */
    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    /**
     * Gets FieldNameProvider.
     *
     * @return the FieldNameProvider
     */
    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }
}
