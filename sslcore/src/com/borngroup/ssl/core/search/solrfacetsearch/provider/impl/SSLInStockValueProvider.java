/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductInStockFlagValueProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collection;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

public class SSLInStockValueProvider extends ProductInStockFlagValueProvider {
    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductInStockFlagValueProvider#getProductStockLevelStatus
     * (de.hybris.platform.core.model.product.ProductModel, de.hybris.platform.store.BaseStoreModel)
     */
    @Override
    protected StockLevelStatus getProductStockLevelStatus(final ProductModel product, final BaseStoreModel baseStore) {
        boolean inStock = false;
        if (product instanceof ApparelStyleVariantProductModel) {
            final Collection<VariantProductModel> apparelSizeVariantProductModel = product.getVariants();
            for (final VariantProductModel apparelSizeVariant : apparelSizeVariantProductModel) {
                if (!StockLevelStatus.OUTOFSTOCK.equals(super.getProductStockLevelStatus(apparelSizeVariant, baseStore))) {
                    inStock = true;
                    break;
                }
            }
        } else if (product instanceof ApparelProductModel) {
            final Collection<VariantProductModel> variants = ((ApparelProductModel) product).getVariants();
            for (final VariantProductModel variantProductModel : variants) {
                final Collection<VariantProductModel> variantProductModels = variantProductModel.getVariants();
                for (final VariantProductModel variantProductModel2 : variantProductModels) {
                    if (!StockLevelStatus.OUTOFSTOCK.equals(super.getProductStockLevelStatus(variantProductModel2, baseStore))) {
                        inStock = true;
                        break;
                    }
                }
            }

        }
        if (inStock) {
            return StockLevelStatus.INSTOCK;
        } else {
            return null;
        }
    }
}
