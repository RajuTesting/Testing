package com.borngroup.ssl.core.personalshoppers.services.impl;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.store.data.GeoPoint;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.commerceservices.storefinder.impl.DefaultStoreFinderService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.PSStoreMasterModel;
import com.borngroup.ssl.core.personalshoppers.dao.PSStoreMasterDAO;
import com.borngroup.ssl.core.personalshoppers.services.PSStoreMasterService;
/**
 * <p>
 * PSStoreMasterServiceImpl.java : Service to get the PS store details and PS
 * city store details
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSStoreMasterServiceImpl implements PSStoreMasterService{

    private static final Logger LOG = Logger.getLogger(PSStoreMasterServiceImpl.class);
    /** The PS Store Master DAO **/
    @Resource (name="psStoreMasterDAO")
    private PSStoreMasterDAO psStoreMasterDAO;
    /**
	 * {@inheritDoc}}
	 */
    @Override
    public StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSStoreList(PageableData pageable, String bookingType) {

        final StoreFinderSearchPageData<PSStoreMasterModel> searchPageData = psStoreMasterDAO.getPagedPSStoreList(pageable, bookingType);
        final GeoPoint centerPointIndia = new GeoPoint();
        centerPointIndia.setLatitude(27.89);
        centerPointIndia.setLongitude(78.07);
        /*
         * //hardcoded for India centerPointIndia.setLatitude(27.89)
         * centerPointIndia.setLongitude(78.07)
         */
        final DefaultStoreFinderService.Boundary<GeoPoint> boundary = calculateBounds(searchPageData.getResults(), centerPointIndia);
        searchPageData.setBoundNorthLatitude(boundary.getMin().getLatitude());
        searchPageData.setBoundEastLongitude(boundary.getMin().getLongitude());
        searchPageData.setBoundSouthLatitude(boundary.getMax().getLatitude());
        searchPageData.setBoundWestLongitude(boundary.getMax().getLongitude());

        return searchPageData;
    }
    /**
	 * {@inheritDoc}}
	 */
    @Override
    public StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSCityStoreList(PageableData pageable, String bookingType, String cityLocation, Double latitude, Double longitude) {

        final StoreFinderSearchPageData<PSStoreMasterModel> searchPageData = psStoreMasterDAO.getPagedPSCityStoreList(pageable, bookingType, cityLocation, latitude, longitude);
        final GeoPoint centerPointIndia = new GeoPoint();
        centerPointIndia.setLatitude(27.89);
        centerPointIndia.setLongitude(78.07);
        /*
         * //hardcoded for India centerPointIndia.setLatitude(27.89)
         * centerPointIndia.setLongitude(78.07)
         */
        final DefaultStoreFinderService.Boundary<GeoPoint> boundary = calculateBounds(searchPageData.getResults(), centerPointIndia);
        searchPageData.setBoundNorthLatitude(boundary.getMin().getLatitude());
        searchPageData.setBoundEastLongitude(boundary.getMin().getLongitude());
        searchPageData.setBoundSouthLatitude(boundary.getMax().getLatitude());
        searchPageData.setBoundWestLongitude(boundary.getMax().getLongitude());
        return searchPageData;
    }
	/**
	 * @Description : To calculate the geo point boundaries
	 * @param results
	 *            : List of PS Store Master models
	 * @param centerPoint
	 *            : GeoPoint
	 * @return @{link DefaultStoreFinderService.Boundary<GeoPoint>}
	 */
    protected DefaultStoreFinderService.Boundary<GeoPoint> calculateBounds(final List<PSStoreMasterModel> results, final GeoPoint centerPoint)
    {
        DefaultStoreFinderService.Boundary<GeoPoint> calculatedBoundary = new DefaultStoreFinderService.Boundary<GeoPoint>(new GeoPoint(), new GeoPoint());
        if (CollectionUtils.isNotEmpty(results) && centerPoint != null)
        {
            GeoPoint northEast = null;
            GeoPoint southWest = null;
            boolean foundPoint = false;

            for (final PSStoreMasterModel result : results)
            {
                final Double latitude =  result.getStoreLat();
                final Double longitude = result.getStoreLong();

                if (latitude != null && longitude != null)
                {
                    foundPoint = true;
                    northEast= getMaxGeoPoint(northEast,latitude,longitude);
                    southWest= getMaxGeoPoint(southWest,latitude,longitude);
                    if (LOG.isDebugEnabled())
                    {
                        LOG.debug(" bounds coords NE(" + northEast + "), SW(" + southWest + ") after a point (" + latitude + ","
                                + longitude + ")");
                    }
                }
            }

            if (foundPoint)
            {
                final DefaultStoreFinderService.Boundary<Double> spanLat = recalculateSpanAgainstCenter(southWest.getLatitude(), northEast.getLatitude(),
                        centerPoint.getLatitude());
                final DefaultStoreFinderService.Boundary<Double> spanLong = recalculateSpanAgainstCenter(southWest.getLongitude(), northEast.getLongitude(),
                        centerPoint.getLongitude());
                if (LOG.isDebugEnabled())
                {
                    LOG.debug(" recalculated coords NE(" + northEast + "), SW(" + southWest + ") for a center point " + centerPoint);
                }
                northEast = new GeoPoint();
                northEast.setLatitude(spanLat.getMax().doubleValue());
                northEast.setLongitude(spanLong.getMax().doubleValue());
                southWest = new GeoPoint();
                southWest.setLatitude(spanLat.getMin().doubleValue());
                southWest.setLongitude(spanLong.getMin().doubleValue());
                calculatedBoundary = new DefaultStoreFinderService.Boundary<GeoPoint>(northEast, southWest);
            }
        }

        return calculatedBoundary;
    }
    
    /**
     * 
     * @param geoPoint : Geo point
     * @param latitude : Latitude
     * @param longitude : Longitude
     * @return @{link GeoPoint}:Longitude and Latitude for the geo point (This method used to set values for both north east and south west points)
     */
    private static GeoPoint getMaxGeoPoint(final GeoPoint  geoPoint,final Double latitude,final Double longitude)
   {
    	 if (geoPoint == null)
         {
    		final  GeoPoint  geoPoint1 = new GeoPoint();
    		geoPoint1.setLatitude(latitude.doubleValue());
    		geoPoint1.setLongitude(longitude.doubleValue());
    		 return geoPoint1;
         }
         else
         {
        	 geoPoint.setLatitude(Math.max(geoPoint.getLatitude(), latitude.doubleValue()));
        	 geoPoint.setLongitude(Math.max(geoPoint.getLongitude(), longitude.doubleValue()));
             return geoPoint;
         }
    }
    /**
	 * @Description : To recalculate store span the against the center
	 * @param leftBorder
	 *            : Left border
	 * @param rightBorder
	 *            : Right border
	 * @param centerPosition
	 *            : Center position
	 * @return @{link DefaultStoreFinderService.Boundary<Double>}
	 *
	 */
    protected DefaultStoreFinderService.Boundary<Double> recalculateSpanAgainstCenter(final double leftBorder, final double rightBorder,
                                                                                      final double centerPosition)
    {
        final double leftSpan = leftBorder - centerPosition;
        final double rightSpan = rightBorder - centerPosition;
        final double max, min;

        if (Math.abs(leftSpan) > Math.abs(rightSpan))
        {
            max = centerPosition + Math.abs(leftSpan);
            min = centerPosition - Math.abs(leftSpan);
        }
        else
        {
            min = centerPosition - Math.abs(rightSpan);
            max = centerPosition + Math.abs(rightSpan);
        }

        return new DefaultStoreFinderService.Boundary<Double>(Double.valueOf(min), Double.valueOf(max));
    }

	/**
	 * @return the psStoreMasterDAO
	 */
	public PSStoreMasterDAO getPsStoreMasterDAO() {
		return psStoreMasterDAO;
	}

	/**
	 * @param psStoreMasterDAO the psStoreMasterDAO to set
	 */
	public void setPsStoreMasterDAO(PSStoreMasterDAO psStoreMasterDAO) {
		this.psStoreMasterDAO = psStoreMasterDAO;
	}

}