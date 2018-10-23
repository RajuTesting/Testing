package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.PropertyFieldValueProviderTestBase;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.impl.DefaultSolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.search.solrfacetsearch.provider.impl.SSLImprovedSearchStockAtWarehouseValueProvider;

/**
 * @author osheengulati
 *
 */
@UnitTest
public class SSLImprovedSearchStockAtWarehouseValueProviderTest extends PropertyFieldValueProviderTestBase {

    Logger LOG = Logger.getLogger(SSLImprovedSearchStockAtWarehouseValueProviderTest.class);

    private static final String TEST_STOCK_AT_WAREHOUSE_PROP = "boostInventoryWithDCImprovedsearch_boolean";
    @Mock
    private StockService stockService;
    @Mock
    private DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy;

    @Mock
    private IndexedProperty indexedProperty;

    WarehouseModel warehouseModel1;

    WarehouseModel warehouseModel2;

    ApparelProductModel baseProduct1;

    ApparelProductModel baseProduct2;

    ApparelStyleVariantProductModel styleVariant1;

    ApparelStyleVariantProductModel styleVariant2;

    ApparelSizeVariantProductModel sizeVariant1;

    ApparelSizeVariantProductModel sizeVariant2;

    List<StockLevelModel> stockLevelList1 = new ArrayList<StockLevelModel>();
    StockLevelModel stockLevelModel1;

    StockLevelModel stockLevelModel2;

    @Before
    public void setUp() throws Exception {
        configure();
    }

    @Override
    protected String getPropertyName() {
        return TEST_STOCK_AT_WAREHOUSE_PROP;
    }

    @Override
    protected void configure() {
        try {
            configureInternal();
        } catch (final NoValidSolrConfigException e) {
            LOG.info(e.toString());
        }
    }

    @SuppressWarnings("boxing")
    protected void configureInternal() throws NoValidSolrConfigException {
        setPropertyFieldValueProvider(new SSLImprovedSearchStockAtWarehouseValueProvider());
        ((SSLImprovedSearchStockAtWarehouseValueProvider) getPropertyFieldValueProvider())
                .setFieldNameProvider(fieldNameProvider);
        configureBase();
        final List<VariantProductModel> styleVariants1 = new ArrayList<>();
        final List<VariantProductModel> styleVariants2 = new ArrayList<>();
        final List<VariantProductModel> sizeVariants1 = new ArrayList<>();
        final List<VariantProductModel> sizeVariants2 = new ArrayList<>();
        baseProduct1 = new ApparelProductModel();
        baseProduct2 = new ApparelProductModel();
        styleVariant1 = new ApparelStyleVariantProductModel();
        styleVariant2 = new ApparelStyleVariantProductModel();
        sizeVariant1 = new ApparelSizeVariantProductModel();
        sizeVariant2 = new ApparelSizeVariantProductModel();
        styleVariants1.add(styleVariant1);
        styleVariants2.add(styleVariant2);
        sizeVariants1.add(sizeVariant1);
        sizeVariants2.add(sizeVariant2);
        baseProduct1.setVariants(styleVariants1);
        baseProduct2.setVariants(styleVariants2);
        styleVariant1.setVariants(sizeVariants1);
        styleVariant2.setVariants(sizeVariants2);
        warehouseModel1 = new WarehouseModel();
        warehouseModel2 = new WarehouseModel();
        final List<WarehouseModel> boostedWarehouses = new ArrayList<>();
        boostedWarehouses.add(warehouseModel1);
        final SolrFacetSearchConfigModel solrFacetSearchConfiguration = new SolrFacetSearchConfigModel();
        solrFacetSearchConfiguration.setWarehouses(boostedWarehouses);
        ((SSLImprovedSearchStockAtWarehouseValueProvider) getPropertyFieldValueProvider())
                .setStockService(stockService);
        ((SSLImprovedSearchStockAtWarehouseValueProvider) getPropertyFieldValueProvider())
                .setDefaultSolrFacetSearchConfigSelectionStrategy(defaultSolrFacetSearchConfigSelectionStrategy);
        ((SSLImprovedSearchStockAtWarehouseValueProvider) getPropertyFieldValueProvider())
                .setFieldNameProvider(fieldNameProvider);
        Assert.assertTrue(getPropertyFieldValueProvider() instanceof FieldValueProvider);
        Mockito.when(fieldNameProvider.getFieldNames(Mockito.eq(indexedProperty), Mockito.anyString()))
                .thenReturn(singletonList(TEST_STOCK_AT_WAREHOUSE_PROP));
        given(stockService.getStockLevelAmount(sizeVariant1, warehouseModel1)).willReturn(0);
        given(stockService.getStockLevelAmount(sizeVariant1, warehouseModel2)).willReturn(50);
        given(stockService.getStockLevelAmount(sizeVariant2, warehouseModel1)).willReturn(100);
        given(stockService.getStockLevelAmount(sizeVariant2, warehouseModel2)).willReturn(0);
        given(defaultSolrFacetSearchConfigSelectionStrategy.getCurrentSolrFacetSearchConfig())
                .willReturn(solrFacetSearchConfiguration);
    }

    @Test
    public void testGetStockLevelsFromSizeProductsNegative()
            throws FieldValueProviderException, NoValidSolrConfigException {
        @SuppressWarnings("deprecation")
        final Collection<FieldValue> result = ((FieldValueProvider) getPropertyFieldValueProvider())
                .getFieldValues(indexConfig, indexedProperty, baseProduct1);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(result.iterator().next().getValue(), Boolean.FALSE);
    }

    @Test
    public void testGetStockLevelsFromSizeProductsPositive()
            throws FieldValueProviderException, NoValidSolrConfigException {
        @SuppressWarnings("deprecation")
        final Collection<FieldValue> result = ((FieldValueProvider) getPropertyFieldValueProvider())
                .getFieldValues(indexConfig, indexedProperty, baseProduct2);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(result.iterator().next().getValue(), Boolean.TRUE);
    }
}
