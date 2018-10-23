/**
 * 
 */
package com.borngroup.ssl.core.personalshoppers.services.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.impl.DefaultPagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.impl.DefaultFlexibleSearchService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.mockito.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.borngroup.ssl.core.model.PSBookingTypeModel;
import com.borngroup.ssl.core.model.PSStoreMasterModel;
import com.borngroup.ssl.core.personalshoppers.dao.PSStoreMasterDAO;
import com.borngroup.ssl.core.personalshoppers.dao.impl.PSStoreMasterDAOImpl;

/**
 * <p>
 * PSStoreMasterServiceImplTest.java : Test class to city store details
 * <p>
 * Created By : sana.ambreen@techouts.com
 * <p>
 * 
 * @author Techouts.
 */
@UnitTest
public class PSStoreMasterServiceImplTest {
	@InjectMocks
	PSStoreMasterServiceImpl pSStoreMasterServiceImpl;

	@InjectMocks
	PSStoreMasterDAOImpl psStoreMasterDAOImpl;
	
	@InjectMocks
	DefaultPagedFlexibleSearchService pagedFlexibleSearchService;

	@InjectMocks
	DefaultFlexibleSearchService defaultFlexibleSearchService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		pSStoreMasterServiceImpl = new PSStoreMasterServiceImpl();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getPagedPSStoreListTest(){
		  StoreFinderSearchPageData<PSStoreMasterModel> searchPageData = new StoreFinderSearchPageData<>();
		  PSStoreMasterModel pSStoreMasterModel =new PSStoreMasterModel();
		  pSStoreMasterModel.setStoreId("101");
		  pSStoreMasterModel.setStoreCity("Mumbai");
		  pSStoreMasterModel.setStoreName("Andheri");
		  pSStoreMasterModel.setStoreLat(19.115211);
		  pSStoreMasterModel.setStoreLong(72.842393);
		  
		List<PSStoreMasterModel> psStoreMasterList= new ArrayList<>();
		psStoreMasterList.add(pSStoreMasterModel);
		
		searchPageData.setResults(psStoreMasterList);
  
		  PSBookingTypeModel pSBookingTypeModel = new PSBookingTypeModel();
		  pSBookingTypeModel.setBookingTypeId("inStore");
		  
		  List<PSBookingTypeModel>  availableBookingTypesList = new ArrayList<>();	
		  availableBookingTypesList.add(pSBookingTypeModel);
		  pSStoreMasterModel.setAvailableBookingTypes(availableBookingTypesList);	  
		  
		  PageableData pageableData =new PageableData();
		  pageableData.setCurrentPage(0);
		  pageableData.setPageSize(10);
		  
		   Map<String, Object> queryParams = new HashMap<>();
		  queryParams.put("bookingType", pSBookingTypeModel.getBookingTypeId());
		  
		  FlexibleSearchQuery searchQuery = new FlexibleSearchQuery("");
		  when(defaultFlexibleSearchService.search(searchQuery)).thenReturn(Matchers.any(SearchResult.class));
		  SearchPageData<Object> storesData =new SearchPageData<>();
		  //storesData.setResults(psStoreMasterList);
		  when(pagedFlexibleSearchService.search(Matchers.anyString(), queryParams, pageableData)).thenReturn(storesData);
		  
		  when(psStoreMasterDAOImpl.getPagedPSStoreList(pageableData,Matchers.anyString())).thenReturn(searchPageData);
		  
		  StoreFinderSearchPageData<PSStoreMasterModel> searchPageDataResult=pSStoreMasterServiceImpl.getPagedPSStoreList(Matchers.any(PageableData.class),Matchers.anyString());
		  Assert.assertNotNull(searchPageDataResult);
		  Assert.assertTrue("Store list is not empty ", searchPageDataResult.getResults().size() > 0);
		  
	}
}
