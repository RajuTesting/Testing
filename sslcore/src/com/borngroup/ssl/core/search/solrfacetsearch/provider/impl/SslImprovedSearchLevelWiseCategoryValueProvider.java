package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
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
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * The Class SslImprovedSearchLevelWiseCategoryValueProvider. Used to provide values in facets l1category,l2category, l3catgeory.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SslImprovedSearchLevelWiseCategoryValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider,
        Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /** The category service. */
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    /** The category level. */
    private Integer categoryLevel;

    /** The Constant ALL_CATEGORIES. */
    private static final String ALL_CATEGORIES = "All";

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel product = (ApparelProductModel) model;
            return createFieldValues(product, indexedProperty);
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelProductModel product = (ApparelProductModel) ((ApparelStyleVariantProductModel) model).getBaseProduct();
            return createFieldValues(product, indexedProperty);
        }
        throw new FieldValueProviderException("Cannot evaluate style of non-product item");
    }

    /**
     * Creates the field values.
     *
     * @param product
     *        the product
     * @param indexedProperty
     *        the indexed property
     * @return the list of values indexed
     */
    protected List<FieldValue> createFieldValues(final ApparelProductModel product, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList();
        if (product != null) {
            LOG.info("SslImprovedSearchLevelWiseCategoryValueProvider Indexing Starts: Category Level [" + this.getCategoryLevel()
                    + "] Product [" + product.getCode() + "]");
            final Collection<CategoryModel> allCategories = product.getSupercategories();
            if (CollectionUtils.isNotEmpty(allCategories)) {

                CategoryModel parentCategoryForProduct = null;
                for (final CategoryModel cat : allCategories) {
                    if (cat != null && !(cat instanceof ClassificationClassModel)) {
                        parentCategoryForProduct = cat;
                        this.createFieldValueForCategory(parentCategoryForProduct, product, fieldValues, indexedProperty);
                    }
                }

            }
            LOG.info("SslImprovedSearchLevelWiseCategoryValueProvider Indexing Ends: Category Level [" + this.getCategoryLevel()
                    + "] Product [" + product.getCode() + "]");
        }
        return fieldValues;
    }

    /**
     * Creates the field value for each category in product except.
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
    private void createFieldValueForCategory(final CategoryModel parentCategoryForProduct, final ApparelProductModel product,
            final List<FieldValue> fieldValues, final IndexedProperty indexedProperty) {
        String value = null;
        if (parentCategoryForProduct != null) {
            final Collection<List<CategoryModel>> categoryPaths = this.categoryService.getPathsForCategory(parentCategoryForProduct);
            if (CollectionUtils.isNotEmpty(categoryPaths)) {
                final List<CategoryModel> pathForParentCategory = categoryPaths.iterator().next();
                if (null != pathForParentCategory && pathForParentCategory.size() > 3) {

                    if (this.getCategoryLevel() == 1 && pathForParentCategory.get(1).getIsIndexable() != null
                            && pathForParentCategory.get(1).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(2).getIsIndexable() != null
                            && pathForParentCategory.get(2).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(3).getIsIndexable() != null
                            && pathForParentCategory.get(3).getIsIndexable().booleanValue()) {
                        this.addFieldValues(fieldValues, indexedProperty, ALL_CATEGORIES);
                        value = pathForParentCategory.get(1).getName();
                    } else if (this.getCategoryLevel() == 2 && pathForParentCategory.get(1).getIsIndexable() != null
                            && pathForParentCategory.get(1).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(2).getIsIndexable() != null
                            && pathForParentCategory.get(2).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(3).getIsIndexable() != null
                            && pathForParentCategory.get(3).getIsIndexable().booleanValue()) {
                        value = pathForParentCategory.get(2).getName();
                    } else if (this.getCategoryLevel() == 3 && pathForParentCategory.get(1).getIsIndexable() != null
                            && pathForParentCategory.get(1).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(2).getIsIndexable() != null
                            && pathForParentCategory.get(2).getIsIndexable().booleanValue()
                            && pathForParentCategory.get(3).getIsIndexable() != null
                            && pathForParentCategory.get(3).getIsIndexable().booleanValue()) {
                        value = pathForParentCategory.get(3).getName().toLowerCase();
                    }
                }
                if (value != null) {
                    this.addFieldValues(fieldValues, indexedProperty, value);
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
     * Gets the field name provider.
     *
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * Sets the field name provider.
     *
     * @param fieldNameProvider
     *        the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

}
