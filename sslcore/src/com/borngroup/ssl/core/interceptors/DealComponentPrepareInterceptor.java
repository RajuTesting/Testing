/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.CacheRegionProvider;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import java.util.Date;
import java.util.List;

import com.borngroup.ssl.core.model.DealCollectionComponentModel;
import com.borngroup.ssl.core.model.DealComponentModel;

/**
 * @author shilpiverma
 *
 */
public class DealComponentPrepareInterceptor implements PrepareInterceptor<DealComponentModel> {

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
    public void onPrepare(final DealComponentModel model, final InterceptorContext ctx) throws InterceptorException {
        // YTODO Auto-generated method stub
        if (null != model) {
            final DealComponentModel dealComponentModel = model;
            final Date currentDate = new Date();
            final Date startDate = dealComponentModel.getStartTime();
            final Date endDate = dealComponentModel.getEndTime();
            if (currentDate.after(startDate)) {
                throw new InterceptorException("Start Date has to be a future date.");
            }
            if (currentDate.after(endDate)) {
                throw new InterceptorException("End Date has to be a future date.");
            }
            if (startDate.after(endDate)) {
                throw new InterceptorException("Start date cannot be after End date");
            } else if (startDate.equals(endDate)) {
                throw new InterceptorException("Start date cannot be equals to End date");
            }

            if (null != model.getCollectionComponent()) {
                final List<DealCollectionComponentModel> productModel = (List<DealCollectionComponentModel>) model.getCollectionComponent();
                for (final DealCollectionComponentModel collectionComponentModel : productModel) {
                    final List<DealComponentModel> dealList = collectionComponentModel.getDealCollection();
                    if (null != dealList) {
                        final int dealListCount = dealList.size();
                        if (dealListCount > 1) {
                            for (int i = 0; i < dealListCount; i++) {
                                final DealComponentModel dealComponentModel1 = dealList.get(i);
                                final Date startTime1 = dealComponentModel1.getStartTime();
                                final Date endTime1 = dealComponentModel1.getEndTime();
                                if (startTime1.compareTo(startDate) >= 0 && startTime1.compareTo(endDate) <= 0) {
                                    throw new InterceptorException("Deal times clashes!!!");
                                } else if (startTime1.compareTo(startDate) <= 0 && startTime1.compareTo(endDate) >= 0) {
                                    throw new InterceptorException("Deal times clashes!!!");
                                }
                                if (endTime1.compareTo(startDate) >= 0 && endTime1.compareTo(endDate) <= 0) {
                                    throw new InterceptorException("Deal times clashes!!!");
                                } else if (endTime1.compareTo(startDate) <= 0 && endTime1.compareTo(endDate) >= 0) {
                                    throw new InterceptorException("Deal times clashes!!!");
                                }
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
