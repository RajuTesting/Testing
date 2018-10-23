/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.PropertyFieldValueProviderTestBase;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.jalo.ProductPromotion;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.search.solrfacetsearch.provider.impl.PromotionApplicableProductValueProvider;

/**
 * @author S52543(Koteswara Rao)
 * 
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class PromotionApplicableProductValueProviderTest extends PropertyFieldValueProviderTestBase {
	@Mock
	private IndexedProperty indexedProperty;
	private static final String SOLR_PROPERTY = "isPromotionApplicable";
	private static final String INDEXED_PROPERTY = SOLR_PROPERTY + "_boolean";
	ApparelProductModel baseProduct;
	private PromotionApplicableProductValueProvider promotionApplicableProductValueProvider;

	@Before
	public void setUp() throws Exception {
		promotionApplicableProductValueProvider = new PromotionApplicableProductValueProvider();
		configure();
	}

	@Override
	protected String getPropertyName() {
		return SOLR_PROPERTY;
	}

	@Override
	protected void configure() {
		setPropertyFieldValueProvider(promotionApplicableProductValueProvider);
		configureBase();
		baseProduct = new ApparelProductModel();
		ProductPromotionModel promotionModel=new ProductPromotionModel();
		promotionModel.setCode("BYE1GET1FREE");
		List<ProductPromotionModel> list =new ArrayList<ProductPromotionModel>();
		list.add(promotionModel);
		baseProduct.setPromotions(list);
		((PromotionApplicableProductValueProvider) getPropertyFieldValueProvider())
				.setFieldNameProvider(fieldNameProvider);
		Assert.assertTrue(getPropertyFieldValueProvider() instanceof FieldValueProvider);
	}

	@Test
	public void testFieldIsIndexed() throws FieldValueProviderException {
		
		final List fieldNames = Arrays.asList(new String[] { INDEXED_PROPERTY });
		Mockito.when(fieldNameProvider.getFieldNames(Mockito.eq(indexedProperty), Mockito.anyString()))
				.thenReturn(fieldNames);
		final Collection<FieldValue> result = ((FieldValueProvider) getPropertyFieldValueProvider())
				.getFieldValues(indexConfig, indexedProperty, baseProduct);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

}
