package com.borngroup.ssl.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import de.hybris.platform.promotions.util.Helper;
import org.apache.log4j.Logger;

public class SslProductPromotionRestriction extends GeneratedSslProductPromotionRestriction {
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslProductPromotionRestriction.class.getName());

    @Override
    protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
            throws JaloBusinessException {
        // business code placed here will be executed before the item is created
        // then create the item
        final Item item = super.createItem(ctx, type, allAttributes);
        // business code placed here will be executed after the item was created
        // and return the item
        return item;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.promotions.jalo.AbstractPromotionRestriction#evaluate(de.hybris.platform.jalo.SessionContext,
     * java.util.Collection, java.util.Date, de.hybris.platform.jalo.order.AbstractOrder)
     */
    @Override
    public RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> products, final Date date,
            final AbstractOrder order) {
        // YTODO Auto-generated method stub
        final boolean include = isIncludeAsPrimitive(ctx);
        final Collection<Product> productSKUList = getProductCodeList(ctx);
        final ArrayList restrictedProducts = new ArrayList();
        for (final Product product : products) {
            if (!this.isApplicableProduct(product, include, productSKUList)) {
                restrictedProducts.add(product);
            }
        }
        if (!(restrictedProducts.isEmpty())) {
            products.removeAll(restrictedProducts);
            return AbstractPromotionRestriction.RestrictionResult.ADJUSTED_PRODUCTS;
        }
        return AbstractPromotionRestriction.RestrictionResult.ALLOW;
    }

    private boolean isApplicableProduct(final Product product, final boolean include, final Collection<Product> productSKUList) {
        boolean result = false;
        result =  productSKUList.contains(product);
        if (include) {
            if (!result) {
                List<Product> baseProducts = Helper.getBaseProducts(this.getSession().getSessionContext(), product);
                for(Product baseProduct : baseProducts){
                    if (productSKUList.contains(baseProduct)) {
                        result = true;
                        break;
                    }
                }
            }
        } else {
            //exclude if present in list.
            if(result){
                result = false;
            }
        }
        return result;
    }
}
