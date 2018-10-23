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

import org.apache.log4j.Logger;


public class SslAltBrandCodeRestriction extends GeneratedSslAltBrandCodeRestriction
{
    @SuppressWarnings("unused")
    private final static Logger LOG = Logger.getLogger(SslAltBrandCodeRestriction.class.getName());

    @Override
    protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
            throws JaloBusinessException
    {
        // business code placed here will be executed before the item is created
        // then create the item
        final Item item = super.createItem(ctx, type, allAttributes);
        // business code placed here will be executed after the item was created
        // and return the item
        return item;
    }

    /* (non-Javadoc)
     * @see de.hybris.platform.promotions.jalo.AbstractPromotionRestriction#evaluate(de.hybris.platform.jalo.SessionContext, java.util.Collection, java.util.Date, de.hybris.platform.jalo.order.AbstractOrder)
     */
    @Override
    public RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> products, final Date date,
            final AbstractOrder order)
    {
        final boolean include = isIncludeAsPrimitive(ctx);
        final Collection<String> altBrandDescriptionList = getAltBrandCodeList(ctx);
        final ArrayList productsToRemove = new ArrayList();
        for (final Product product : products)
        {
            if (!isApplicableProduct(product, include, altBrandDescriptionList))
            {
                productsToRemove.add(product);
            }
        }

        if (!(productsToRemove.isEmpty()))
        {
            products.removeAll(productsToRemove);
            return AbstractPromotionRestriction.RestrictionResult.ADJUSTED_PRODUCTS;
        }

        return AbstractPromotionRestriction.RestrictionResult.ALLOW;
    }

    /**
     * @param product
     * @param include
     * @param altBrandDescription
     * @return
     */
    private boolean isApplicableProduct(final Product product, final boolean include,
            final Collection<String> altBrandDescriptionList)
    {
        try
        {
        	if (include) {
				return altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
			} else {
				return !altBrandDescriptionList.contains(product.getAttribute("altBrandCode"));
			}
        }
        catch (final JaloInvalidParameterException e)
        {
            // YTODO Auto-generated catch block
        }
        catch (final JaloSecurityException e)
        {
            // YTODO Auto-generated catch block
        }
        return false;
    }

}
