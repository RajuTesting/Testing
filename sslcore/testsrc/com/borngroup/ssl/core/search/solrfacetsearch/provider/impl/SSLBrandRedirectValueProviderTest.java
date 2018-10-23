package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import static java.util.Collections.singletonList;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.PropertyFieldValueProviderTestBase;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.search.solrfacetsearch.provider.impl.SSLBrandRedirectValueProvider;

/**
 * @author osheengulati
 *
 */
@UnitTest
public class SSLBrandRedirectValueProviderTest extends PropertyFieldValueProviderTestBase {

    Logger LOG = Logger.getLogger(SSLBrandRedirectValueProviderTest.class);

    private static final String TEST_BRANDS_REFINE_PROP = "brandAutosuggestRefine_string";
    @Mock
    private IndexedProperty indexedProperty;

    ApparelProductModel baseProduct;

    @Before
    public void setUp() throws Exception {
        configure();
    }

    @Override
    protected String getPropertyName() {
        return TEST_BRANDS_REFINE_PROP;
    }

    @Override
    protected void configure() {
        setPropertyFieldValueProvider(new SSLBrandRedirectValueProvider());
        ((SSLBrandRedirectValueProvider) getPropertyFieldValueProvider()).setFieldNameProvider(fieldNameProvider);
        configureBase();
        baseProduct = new ApparelProductModel();
        baseProduct.setBrandCode("Tommy Hilfiger");
        ((SSLBrandRedirectValueProvider) getPropertyFieldValueProvider()).setFieldNameProvider(fieldNameProvider);
        Assert.assertTrue(getPropertyFieldValueProvider() instanceof FieldValueProvider);
        Mockito.when(fieldNameProvider.getFieldNames(Mockito.eq(indexedProperty), Mockito.anyString()))
                .thenReturn(singletonList(TEST_BRANDS_REFINE_PROP));
    }

    @Test
    public void testFormattedBrandTest() throws FieldValueProviderException, NoValidSolrConfigException {
        @SuppressWarnings("deprecation")
        final Collection<FieldValue> result = ((FieldValueProvider) getPropertyFieldValueProvider())
                .getFieldValues(indexConfig, indexedProperty, baseProduct);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(result.iterator().next().getValue(), "tommyhilfiger");
    }

}
