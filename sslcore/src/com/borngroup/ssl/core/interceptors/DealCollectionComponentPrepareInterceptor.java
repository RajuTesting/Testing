/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.CacheRegionProvider;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.util.Config;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.DealCollectionComponentModel;
import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author shilpiverma
 *
 */
public class DealCollectionComponentPrepareInterceptor implements PrepareInterceptor<DealCollectionComponentModel> {
    private static final Logger LOG = Logger.getLogger(DealCollectionComponentPrepareInterceptor.class);

    /** Constant for DEAL_COMPONENT_REGION_NAME. */
    private static final String DEAL_COMPONENT_REGION_NAME = "dealComponentCacheRegion";

    /** Object for CacheRegion. */
    private CacheRegionProvider cacheRegionProvider;

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
     * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
     */
    @Override
    public void onPrepare(final DealCollectionComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        // YTODO Auto-generated method stub
        if (model != null) {
            final DealCollectionComponentModel dealCollectionComponentModel = model;
            if (dealCollectionComponentModel.getNoOfProductsOnComponentWeb() < Integer
                    .parseInt(Config.getParameter("no.of.deal.product.on.desktop"))) {
                throw new InterceptorException("Number of products shown on web cannot be less than "
                        + Integer.parseInt(Config.getParameter("no.of.deal.product.on.desktop")) + ".");
            }
            if (dealCollectionComponentModel.getNoOfProductsOnComponentMobile() < Integer
                    .parseInt(Config.getParameter("no.of.deal.product.on.mobile"))) {
                throw new InterceptorException("Number of products shown on mobile cannot be less than "
                        + Integer.parseInt(Config.getParameter("no.of.deal.product.on.mobile")) + ".");
            }
            final DealCollectionComponentModel productModel = model;
            final List<DealComponentModel> dealList = productModel.getDealCollection();
            if (null != dealList) {
                final int dealListCount = dealList.size();
                if (dealListCount > 1) {
                    for (int i = 0; i < dealListCount; i++) {
                        final DealComponentModel dealComponentModel1 = dealList.get(i);
                        final Date startTime1 = dealComponentModel1.getStartTime();
                        final Date endTime1 = dealComponentModel1.getEndTime();
                        for (int j = i + 1; j < dealListCount; j++) {
                            final DealComponentModel dealComponentModel2 = dealList.get(j);
                            final Date startTime2 = dealComponentModel2.getStartTime();
                            final Date endTime2 = dealComponentModel2.getEndTime();
                            if (startTime1.compareTo(startTime2) >= 0 && startTime1.compareTo(endTime2) <= 0) {
                                throw new InterceptorException("Deal times clashes!!!");
                            } else if (startTime1.compareTo(startTime2) <= 0 && startTime1.compareTo(endTime2) >= 0) {
                                throw new InterceptorException("Deal times clashes!!!");
                            }
                            if (endTime1.compareTo(startTime2) >= 0 && endTime1.compareTo(endTime2) <= 0) {
                                throw new InterceptorException("Deal times clashes!!!");
                            } else if (endTime1.compareTo(startTime2) <= 0 && endTime1.compareTo(endTime2) >= 0) {
                                throw new InterceptorException("Deal times clashes!!!");
                            }
                        }
                    }
                }
            }
            // Logic to clear cache if the above validation has passed.
            final CacheRegion cacheRegion = this.cacheRegionProvider.getManualRegion(DEAL_COMPONENT_REGION_NAME);
            cacheRegion.clearCache();

        }
    }

    /** Getter for CacheRegionProvider. */
    public CacheRegionProvider getCacheRegionProvider() {
        return cacheRegionProvider;
    }

    /** Setter for CacheRegionProvider. */
    public void setCacheRegionProvider(final CacheRegionProvider cacheRegionProvider) {
        this.cacheRegionProvider = cacheRegionProvider;
    }
}
