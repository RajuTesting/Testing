/**
 *
 */
package com.borngroup.ssl.core.services.evaluator.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.impl.CMSCategoryRestrictionEvaluator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Class CMSSslGenderRestrictionEvaluator.
 *
 */
public class SSLCMSCategoryRestrictionEvaluator extends CMSCategoryRestrictionEvaluator {

    private static final Logger LOG = Logger.getLogger(SSLCMSCategoryRestrictionEvaluator.class);

    @Override
    public boolean evaluate(final CMSCategoryRestrictionModel categoryRestrictionModel, final RestrictionData context) {

        if (context == null) {
            return true;
        } else {
            final ArrayList categories = new ArrayList();
            Iterator arg5;
            if (context.hasProduct()) {
                getCategoryCodesForProduct(categoryRestrictionModel, context, categories);
            } else {
                if (!context.hasCategory()) {
                    LOG.warn("Could not evaluate CMSCategoryRestriction. RestrictionData contains neither a category or a product. Returning false.");
                    return false;
                }

                getSuperCategoryCodes(categoryRestrictionModel, context, categories);
            }

            final List codes2 = this.getCategoryCodes(categories);
            arg5 = categoryRestrictionModel.getCategoryCodes().iterator();

            while (arg5.hasNext()) {
                final String code1 = (String) arg5.next();
                if (codes2.contains(code1)) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * @param categoryRestrictionModel
     * @param context
     * @param categories
     */
    private void getSuperCategoryCodes(final CMSCategoryRestrictionModel categoryRestrictionModel, final RestrictionData context,
            final ArrayList categories) {
        final CategoryModel codes1 = context.getCategory();
        categories.add(codes1);
        if (categoryRestrictionModel.isRecursive()) {
            categories.addAll(codes1.getAllSupercategories());
        }
    }

    /**
     * @param categoryRestrictionModel
     * @param context
     * @param categories
     * @return
     */
    private ArrayList getCategoryCodesForProduct(final CMSCategoryRestrictionModel categoryRestrictionModel, final RestrictionData context,
            final ArrayList categories) {
        Iterator arg5;
        final Collection codes = this.getBaseProduct(context.getProduct()).getSupercategories();
        categories.addAll(codes);
        if (categoryRestrictionModel.isRecursive()) {
            arg5 = codes.iterator();

            while (arg5.hasNext()) {
                final CategoryModel code = (CategoryModel) arg5.next();
                categories.addAll(code.getAllSupercategories());
            }
        }
        return categories;
    }

    /**
     * @param product
     */
    private ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }
}