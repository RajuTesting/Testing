package com.borngroup.ssl.core.personalshoppers.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;

import com.borngroup.ssl.core.model.PSStoreMasterModel;

/**
 * <p>
 * PSStoreMasterService.java : Service to get the PS store details and PS city
 * store details
 *
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public interface PSStoreMasterService {

	/**
	 * To get the PS store details based on the booking type
	 *
	 * @param pageable
	 *            : PageableData
	 * @param bookingType
	 *            :Booking type
	 *
	 * @return @link{ StoreFinderSearchPageData<PSStoreMasterModel>} : Store
	 *         Finder search page data of PSStoreMasterModel
	 */
	StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSStoreList(PageableData pageable, String bookingType);

	/**
	 * To get the PS City store details based on the booking type, city
	 * location, longitude and latitude
	 *
	 * @param pageable
	 *            : PageableData
	 * @param bookingType
	 *            : Booking type
	 * @param cityLocation
	 *            : City Location
	 * @param latitude
	 *            :Latitude of the store
	 * @param longitude
	 *            :Longitude of the store
	 *
	 * @return @link{StoreFinderSearchPageData<PSStoreMasterModel>} :Store
	 *         Finder search page data of PSStoreMasterModel
	 */
	StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSCityStoreList(PageableData pageable, String bookingType,
			String cityLocation, Double latitude, Double longitude);
}
