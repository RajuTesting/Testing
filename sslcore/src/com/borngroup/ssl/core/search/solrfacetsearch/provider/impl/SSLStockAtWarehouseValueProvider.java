/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author ashish2483
 *
 */
public class SSLStockAtWarehouseValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private FieldNameProvider fieldNameProvider;
    private String warehouseCode;
    @Autowired
    private StockService stockService;
    @Autowired
    private WarehouseService warehouseService;

    private WarehouseModel stockLocation = null;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues
     * (de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel && getStockLocation() != null) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            return getStockLevelsFromSizeProducts(apparelModel, indexedProperty);
        } else if (model instanceof ApparelStyleVariantProductModel && getStockLocation() != null) {
            final ApparelStyleVariantProductModel apparelModel = (ApparelStyleVariantProductModel) model;
            return getStockLevelsFromSizeProducts(apparelModel, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * @param apparelModel
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<FieldValue> getStockLevelsFromSizeProducts(final ApparelProductModel apparelModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        int stock = 0;
        for (final VariantProductModel variantStyleProductModel : apparelModel.getVariants()) {
            for (final VariantProductModel variantSizeProductModel : variantStyleProductModel.getVariants()) {
                if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                    try {
                        stock = stock + stockService.getStockLevelAmount(variantSizeProductModel, stockLocation);
                    } catch (final Exception e) {
                        LOG.info(String.format("Stock level not found for Product with code :%s in warehouse : %s ",
                                variantSizeProductModel.getCode(), warehouseCode));
                    }

                }
            }
        }
        if (stock > 0) {
            fieldValues.addAll(createFieldValue(Integer.valueOf(stock), indexedProperty));
        }
        return fieldValues;
    }

    private Collection<FieldValue> getStockLevelsFromSizeProducts(final ApparelStyleVariantProductModel apparelStyleVariantProductModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        int stock = 0;
        for (final VariantProductModel variantSizeProductModel : apparelStyleVariantProductModel.getVariants()) {
            if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                try {
                    stock = stock + stockService.getStockLevelAmount(variantSizeProductModel, stockLocation);
                } catch (final Exception e) {
                    LOG.info(String.format("Stock level not found for Product with code :%s in warehouse : %s ",
                            variantSizeProductModel.getCode(), warehouseCode));
                }
            }
        }
        if (stock > 0) {
            fieldValues.addAll(createFieldValue(Integer.valueOf(stock), indexedProperty));
        }
        return fieldValues;
    }

    /**
     * @param stockValue
     * @param indexedProperty
     * @return fieldValues
     */
    private Collection<? extends FieldValue> createFieldValue(final Integer stockValue, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, stockValue));
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
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * @param warehouseCode
     *        the warehouseCode to set
     */
    public void setWarehouseCode(final String warehouseCode) {
        this.warehouseCode = warehouseCode;
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
     * @return the warehouseService
     */
    public WarehouseService getWarehouseService() {
        return warehouseService;
    }

    /**
     * @param warehouseService
     *        the warehouseService to set
     */
    public void setWarehouseService(final WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * @return the stockLocation
     */
    public WarehouseModel getStockLocation() {
        if (this.stockLocation == null) {
            try {
                stockLocation = warehouseService.getWarehouseForCode(warehouseCode);
            } catch (final UnknownIdentifierException e) {
                LOG.error("Warehouse not found with code :" + warehouseCode);
            }
        }
        return this.stockLocation;
    }

}
