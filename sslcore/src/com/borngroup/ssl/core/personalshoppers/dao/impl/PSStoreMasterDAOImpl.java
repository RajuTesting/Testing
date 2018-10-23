package com.borngroup.ssl.core.personalshoppers.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.PSBookingTypeModel;
import com.borngroup.ssl.core.model.PSPersonalShopperModel;
import com.borngroup.ssl.core.model.PSServicesModel;
import com.borngroup.ssl.core.model.PSStoreMasterModel;
import com.borngroup.ssl.core.personalshoppers.dao.PSStoreMasterDAO;

import net.sourceforge.pmd.util.StringUtil;

/**
 * <p>
 * PSStoreMasterDAOImpl.java : DAO to get the PS store details and PS city store
 * <p>
 * Created By : raju.p@techouts.com
 * <p>
 *
 * @author Techouts
 */
public class PSStoreMasterDAOImpl implements PSStoreMasterDAO{

	/**
	 * To get the unique PSStoreMasterModels
	 */
private static final String STORE_QUERY = "SELECT DISTINCT{store." + PSStoreMasterModel.PK + "} FROM {" + PSStoreMasterModel._TYPECODE
			+ " AS store JOIN " + PSBookingTypeModel._TYPECODE + " as btype ON {store."
			+ PSStoreMasterModel.AVAILABLEBOOKINGTYPES
			+ "} LIKE CONCAT('%',CONCAT({btype.pk},'%')) JOIN PSPersonalShopperStoreRelation as psStore ON {store."
			+ PSStoreMasterModel.PK + "}={psStore.target} JOIN " + PSPersonalShopperModel._TYPECODE + " as ps ON {ps."
			+ PSPersonalShopperModel.PK + "}={psStore.source} JOIN " + PSServicesModel._TYPECODE
			+ " as  psService ON {store." + PSStoreMasterModel.AVAILABLEBOOKINGSERVICES
			+ "} LIKE CONCAT('%',CONCAT({psService.pk},'%'))} WHERE {btype." + PSBookingTypeModel.BOOKINGTYPEID
			+ "}=?bookingType AND {btype." + PSBookingTypeModel.ISBOOKINGTYPEACTIVE + "}=1 AND {store."
			+ PSStoreMasterModel.ISSTOREACTIVE + "}=1 AND {ps." + PSPersonalShopperModel.ISPSACTIVE
			+ "}=1 AND {psService." + PSServicesModel.ACTIVE + "}=1";

	/** The Paged Flexible Search Service **/
	@Resource(name="pagedFlexibleSearchService")
	private PagedFlexibleSearchService pagedFlexibleSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSStoreList(final PageableData pageable, final String bookingType) {
		validateParameterNotNull(bookingType, "BookingType cannot be null");
		validateParameterNotNull(pageable, "PageableData must not be null");

		final StoreFinderSearchPageData<PSStoreMasterModel> searchPageData = new StoreFinderSearchPageData<>();
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("bookingType", bookingType.trim().toLowerCase());
		final SearchPageData<PSStoreMasterModel> storesData = pagedFlexibleSearchService.search(STORE_QUERY, queryParams, pageable);

		searchPageData.setResults(storesData.getResults());
		searchPageData.setPagination(storesData.getPagination());
		searchPageData.setSorts(storesData.getSorts());

		return searchPageData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoreFinderSearchPageData<PSStoreMasterModel> getPagedPSCityStoreList(final PageableData pageable, final String bookingType, final String cityLocation, final Double latitude, final Double longitude) {
		validateParameterNotNull(bookingType, "BookingType cannot be null");
		validateParameterNotNull(pageable, "PageableData must not be null");

		final StoreFinderSearchPageData<PSStoreMasterModel> searchPageData = new StoreFinderSearchPageData<>();
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("bookingType", bookingType.trim().toLowerCase());
		final StringBuilder queryBuilder = new StringBuilder(STORE_QUERY);
		if(StringUtil.isNotEmpty(cityLocation)){
			queryParams.put("cityLocation", cityLocation.trim().toLowerCase());
			queryBuilder.append(" AND lower({store."+PSStoreMasterModel.STORECITY+"}) = ?cityLocation");
		}
		if (null != latitude && null != longitude) {
			queryBuilder.append(" AND {store." + PSStoreMasterModel.STORELAT + "} = ?latitude AND {store."
					+ PSStoreMasterModel.STORELONG + "} = ?longitude");
			queryParams.put("latitude", latitude);
			queryParams.put("longitude", longitude);
		}
		final SearchPageData<PSStoreMasterModel> storesData = pagedFlexibleSearchService.search(queryBuilder.toString(), queryParams, pageable);

		searchPageData.setResults(storesData.getResults());
		searchPageData.setPagination(storesData.getPagination());
		searchPageData.setSorts(storesData.getSorts());

		return searchPageData;
	}

	/**
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService() {
		return pagedFlexibleSearchService;
	}

	/**
	 * @param pagedFlexibleSearchService the pagedFlexibleSearchService to set
	 */
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService) {
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}
}