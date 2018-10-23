package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.impl.DefaultSolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * SSLImprovedSearchStockAtWarehouseValueProvider. Index true value if the stock exists in the warehouses mentioned for boost inventory else
 * index false.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SSLImprovedSearchStockAtWarehouseValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider,
        Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /**
     * Stock Service {@link StockService}.
     */
    @Autowired
    private StockService stockService;

    /** The category service. */
    @Autowired
    private DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy;

    /** The constant LOG. */
    private static final Logger LOG = Logger.getLogger(SSLImprovedSearchStockAtWarehouseValueProvider.class);

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            return getStockLevelsFromSizeProducts(apparelModel, indexedProperty);
        } else if (model instanceof ApparelStyleVariantProductModel) {
            return getStockLevelsFromSizeProducts((ApparelStyleVariantProductModel) model, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * @param apparelModel
     *        Apparel Product Model.
     * @param indexedProperty
     *        Indexed Property.
     * @return fieldValues List of values to be indexed.
     */
    private Collection<FieldValue> getStockLevelsFromSizeProducts(final ApparelProductModel apparelModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        SolrFacetSearchConfigModel searchConfig;
        try {
            searchConfig = getDefaultSolrFacetSearchConfigSelectionStrategy().getCurrentSolrFacetSearchConfig();
            if (null != searchConfig) {
                final List<WarehouseModel> wareHouses = searchConfig.getWarehouses();
                if (CollectionUtils.isNotEmpty(wareHouses)) {
                    for (final VariantProductModel variantStyleProductModel : apparelModel.getVariants()) {
                        for (final VariantProductModel variantSizeProductModel : variantStyleProductModel.getVariants()) {
                            if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                                try {
                                    for (final WarehouseModel wareHouse : wareHouses) {
                                        final int stock = getStockService().getStockLevelAmount(variantSizeProductModel, wareHouse);
                                        if (stock > 0) {
                                            fieldValues.addAll(createFieldValue(Boolean.TRUE, indexedProperty));
                                            return fieldValues;
                                        }
                                    }
                                } catch (final StockLevelNotFoundException slnfe) {
                                    if (LOG.isDebugEnabled()) {
                                        LOG.debug("No stock");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (final NoValidSolrConfigException e) {
            LOG.error("No valid solr config found!!");
        }

        fieldValues.addAll(createFieldValue(Boolean.FALSE, indexedProperty));
        return fieldValues;
    }

    /**
     * @param apparelStyleVariantModel
     *        Apparel Product Model.
     * @param indexedProperty
     *        Indexed Property.
     * @return fieldValues List of values to be indexed.
     */
    private Collection<FieldValue> getStockLevelsFromSizeProducts(final ApparelStyleVariantProductModel apparelStyleVariantModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        SolrFacetSearchConfigModel searchConfig;
        try {
            searchConfig = getDefaultSolrFacetSearchConfigSelectionStrategy().getCurrentSolrFacetSearchConfig();
            if (null != searchConfig) {
                final List<WarehouseModel> wareHouses = searchConfig.getWarehouses();
                if (CollectionUtils.isNotEmpty(wareHouses)) {
                    for (final VariantProductModel variantSizeProductModel : apparelStyleVariantModel.getVariants()) {
                        if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                            try {
                                for (final WarehouseModel wareHouse : wareHouses) {
                                    final int stock = getStockService().getStockLevelAmount(variantSizeProductModel, wareHouse);
                                    if (stock > 0) {
                                        fieldValues.addAll(createFieldValue(Boolean.TRUE, indexedProperty));
                                        return fieldValues;
                                    }
                                }
                            } catch (final StockLevelNotFoundException slnfe) {
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("No stock");
                                }
                            }
                        }
                    }
                }
            }
        } catch (final NoValidSolrConfigException e) {
            LOG.error("No valid solr config found!!");
        }

        fieldValues.addAll(createFieldValue(Boolean.FALSE, indexedProperty));
        return fieldValues;
    }

    /**
     * @param value
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final Boolean value, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, value));
        }
        return fieldValues;
    }

    /**
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * @param fieldNameProvider
     *        the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    /**
     * @return the stockService
     */
    public StockService getStockService() {
        return stockService;
    }

    /**
     * @param stockService
     *        the stockService to set
     */
    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * @return the defaultSolrFacetSearchConfigSelectionStrategy
     */
    public DefaultSolrFacetSearchConfigSelectionStrategy getDefaultSolrFacetSearchConfigSelectionStrategy() {
        return defaultSolrFacetSearchConfigSelectionStrategy;
    }

    /**
     * @param defaultSolrFacetSearchConfigSelectionStrategy
     *        the defaultSolrFacetSearchConfigSelectionStrategy to set
     */
    public void setDefaultSolrFacetSearchConfigSelectionStrategy(
            final DefaultSolrFacetSearchConfigSelectionStrategy defaultSolrFacetSearchConfigSelectionStrategy) {
        this.defaultSolrFacetSearchConfigSelectionStrategy = defaultSolrFacetSearchConfigSelectionStrategy;
    }

}
