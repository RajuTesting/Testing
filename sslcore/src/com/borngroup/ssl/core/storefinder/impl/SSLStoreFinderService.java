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
package com.borngroup.ssl.core.storefinder.impl;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.store.data.GeoPoint;
import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.commerceservices.storefinder.impl.DefaultStoreFinderService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.GPS;
import de.hybris.platform.storelocator.exception.GeoLocatorException;
import de.hybris.platform.storelocator.exception.GeoServiceWrapperException;
import de.hybris.platform.storelocator.exception.LocationServiceException;
import de.hybris.platform.storelocator.impl.DefaultGPS;
import de.hybris.platform.storelocator.impl.GeometryUtils;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.borngroup.ssl.core.storefinder.dao.SSLStoreFinderDao;
import com.borngroup.ssl.core.util.SSLLocationsComparator;
import org.springframework.util.Assert;

/**
 * @author Monomoy.Ghosh
 */
public class SSLStoreFinderService extends DefaultStoreFinderService<PointOfServiceDistanceData> {

	private final Logger LOG = LoggerFactory.getLogger(SSLStoreFinderService.class);

	private BaseStoreService baseStoreService;

	@Resource(name = "sslStoreFinderDaoImpl")
	SSLStoreFinderDao sslStoreFinderDaoImpl;

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *            the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}

	/**
	 * Overrided the method to supply all the boundaries for the POS data
	 * retrieved.
	 *
	 */
	@Override
	public StoreFinderSearchPageData<PointOfServiceDistanceData> getAllPos(final BaseStoreModel baseStore,
			final PageableData pageableData) {
		final StoreFinderSearchPageData<PointOfServiceDistanceData> searchPageData = super.getAllPos(baseStore,
				pageableData);
		final GeoPoint centerPointIndia = new GeoPoint();
		/*
		 * //TODO: hardcoded for India centerPointIndia.setLatitude(27.89);
		 * centerPointIndia.setLongitude(78.07);
		 */
		final Boundary<GeoPoint> boundary = calculateBounds(searchPageData.getResults(), centerPointIndia);
		searchPageData.setBoundNorthLatitude(boundary.getMin().getLatitude());
		searchPageData.setBoundEastLongitude(boundary.getMin().getLongitude());
		searchPageData.setBoundSouthLatitude(boundary.getMax().getLatitude());
		searchPageData.setBoundWestLongitude(boundary.getMax().getLongitude());
		return searchPageData;
	}

	/**
	 * Overridden to find stores for a given city by looking up for the given
	 * city in address associated with the POS.
	 */
	@Override
	public StoreFinderSearchPageData<PointOfServiceDistanceData> locationSearch(final BaseStoreModel baseStore,
			final String locationText, final PageableData pageableData) {
		final List<PointOfServiceModel> posList = this.sslStoreFinderDaoImpl.getPOSForTown(locationText, baseStore);
		if (CollectionUtils.isNotEmpty(posList)) {
			final List<PointOfServiceDistanceData> posResults = new ArrayList<PointOfServiceDistanceData>();
			for (final PointOfServiceModel model : posList) {
				final PointOfServiceDistanceData data = new PointOfServiceDistanceData();
				data.setPointOfService(model);
				posResults.add(data);
			}

			final int resultRangeStart = pageableData.getCurrentPage() * pageableData.getPageSize();
			final int resultRangeEnd = (pageableData.getCurrentPage() + 1) * pageableData.getPageSize();
			final GeoPoint geoPoint = new GeoPoint();

			if (StringUtils.isNotEmpty(locationText)) {
				try {
					final GPS resolvedPoint = getGeoWebServiceWrapper()
							.geocodeAddress(generateGeoAddressForSearchQuery(baseStore, locationText));

					geoPoint.setLatitude(resolvedPoint.getDecimalLatitude());
					geoPoint.setLongitude(resolvedPoint.getDecimalLongitude());
				} catch (final GeoServiceWrapperException localGeoServiceWrapperException) {
					this.LOG.info("Failed to resolve location for [" + locationText + "]");
				}
			}
			final List orderedResults = calculateDistances(geoPoint, posList);
			final PaginationData paginationData = createPagination(pageableData, posResults.size());

			final List orderedResultsWindow = orderedResults.subList(Math.min(orderedResults.size(), resultRangeStart),
					Math.min(orderedResults.size(), resultRangeEnd));
			Collections.sort(orderedResultsWindow, new SSLLocationsComparator());
			return createSearchResult(locationText, geoPoint, orderedResultsWindow, paginationData);

		}
		return null;
	}

	/**
	 * Overridden the method to remove stores without latitude and longitude to
	 * be used in map and avoid the exception
	 *
	 */
	@Override
	protected double calculateDistance(final GeoPoint centerPoint, final PointOfServiceModel posModel)
			throws GeoLocatorException, LocationServiceException {
		if ((posModel.getLatitude() != null) && (posModel.getLongitude() != null)) {
			final GPS positionGPS = new DefaultGPS(posModel.getLatitude().doubleValue(),
					posModel.getLongitude().doubleValue());
			final GPS referenceGps = new DefaultGPS(centerPoint.getLatitude(), centerPoint.getLongitude());
			return GeometryUtils.getElipticalDistanceKM(referenceGps, positionGPS);
		} else {
			return 99999;
		}
	}

	@Override
	public PointOfServiceModel getPointOfServiceForName(final BaseStoreModel baseStore, final String name)
	{
		Assert.notNull(baseStore);
		Assert.notNull(name);

		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("baseStore", baseStore);
		paramMap.put("type", PointOfServiceTypeEnum.STORE);

		final List<PointOfServiceModel> posModels = getPointOfServiceGenericDao().find(paramMap);
		if (CollectionUtils.isNotEmpty(posModels))
		{
			return posModels.get(0);
		}

		throw new ModelNotFoundException("No PointOfService with name " + name + ", type STORE found in baseStore "
										 + baseStore.getUid());
	}

}
