/**
 * 
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.acceleratorservices.search.solrfacetsearch.provider.impl.PromotionFacetDisplayNameProvider;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.order.DiscountService;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shashank2484
 * 
 */
public class SSLPromotionFacetDisplayNameProvider extends PromotionFacetDisplayNameProvider {
    @Autowired
    private DiscountService discountService;

    @Override
    public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue) {
        final AbstractPromotionModel promotion = getPromotionsDao().getPromotionForCode(facetValue);
        if (promotion != null) {
            return promotion.getName();
        } else {
            try {
                final DiscountModel discount = getDiscountService().getDiscountForCode(facetValue);
                return discount.getName();
            } catch (final UnknownIdentifierException e) {
                LOG.error("Discount with code [" + facetValue + "] not found!");
                if (LOG.isDebugEnabled()) {
                    LOG.debug(ExceptionUtils.getFullStackTrace(e));
                }
                return facetValue;
            }
        }
    }

    /**
     * @return the discountService
     */
    public DiscountService getDiscountService() {
        return discountService;
    }

    /**
     * @param discountService
     *        the discountService to set
     */
    public void setDiscountService(final DiscountService discountService) {
        this.discountService = discountService;
    }
}
