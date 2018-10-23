/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.PropertyFieldValueProviderTestBase;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

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

import com.borngroup.ssl.core.model.SizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

/**
 * @author S52543(Koteswara Rao)
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SslSizeFacetValueProviderTest extends PropertyFieldValueProviderTestBase {
	@Mock
	private IndexedProperty indexedProperty;
	private static final String SOLR_PROPERTY = "shopBySize";
	private static final String INDEXED_PROPERTY = SOLR_PROPERTY + "_String";
	@Mock
	private SSLLookupService sslLookupService;
	@Mock
	private SslSizeFacetValueProvider SslSizeFacetValueProvider;
	@Mock
	private SizeMappingModel lookedUpSizeCode;

	@Before
	public void setUp() throws Exception {
		SslSizeFacetValueProvider = new SslSizeFacetValueProvider();
		lookedUpSizeCode = new SizeMappingModel();
		configure();
	}

	@Override
	protected String getPropertyName() {
		return SOLR_PROPERTY;
	}

	@Override
	protected void configure() {
		setPropertyFieldValueProvider(SslSizeFacetValueProvider);
		configureBase();
		((SslSizeFacetValueProvider) getPropertyFieldValueProvider()).setFieldNameProvider(fieldNameProvider);
		Assert.assertTrue(getPropertyFieldValueProvider() instanceof FieldValueProvider);
	}

	@Test
	public void testFieldIsIndexed() throws FieldValueProviderException {
		lookedUpSizeCode = sslLookupService.getSizeCodeForActualCode("L", "150", "TOMYHR", "115");
		final List fieldNames = Arrays.asList(new String[] { INDEXED_PROPERTY });
		Mockito.when(fieldNameProvider.getFieldNames(Mockito.eq(indexedProperty), Mockito.anyString()))
				.thenReturn(fieldNames);
		final Collection<FieldValue> result = ((FieldValueProvider) getPropertyFieldValueProvider())
				.getFieldValues(indexConfig, indexedProperty, lookedUpSizeCode);
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

}
