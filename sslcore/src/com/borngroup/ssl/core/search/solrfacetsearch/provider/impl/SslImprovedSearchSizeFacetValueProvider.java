package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
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

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants.NormalizedLookup;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * The SslImprovedSearchSizeFacetValueProvider. Used to index size value in the product.
 *
 * Created by: osheen.gulati@nagarro.com
 *
 * @author SSL
 */
public class SslImprovedSearchSizeFacetValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /**
     * Stock Service {@link StockService}.
     */
    @Resource(name = "stockService")
    private StockService stockService;

    /** The Constant LOG. */
    protected static final Logger LOG = Logger.getLogger(SslImprovedSearchSizeFacetValueProvider.class);

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            final ProductModel apparelModel = (ProductModel) model;
            return getAllFieldValuesFromSizeProducts(apparelModel, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param apparelSizeModel
     * @param indexedProperty
     * @return fieldValues or emptyList
     */
    protected Collection<FieldValue> getFieldValuesFromSizeProduct(final ApparelSizeVariantProductModel apparelSizeModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        try {
            final int stock = stockService.getTotalStockLevelAmount(apparelSizeModel);
            if (stock > 0) {
                final String size = apparelSizeModel.getSize();
                final String sizeDescription = apparelSizeModel.getAlt_size_desc();
                if (size != null && !NormalizedLookup.SizeNotMapped.equals(size) && sizeDescription != null) {
                    fieldValues.addAll(createFieldValue(sizeDescription, indexedProperty));
                }
            }
            return fieldValues;
        } catch (final StockLevelNotFoundException ex) {
            LOG.debug(ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     *
     * @param sizeDescription
     * @param indexedProperty
     * @return fieldValues
     */
    protected List<FieldValue> createFieldValue(final String sizeDescription, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, sizeDescription));
        }
        return fieldValues;
    }

    /**
     * Get all the size variants from the base product.
     *
     * @param model
     * @param indexedProperty
     * @return fieldValues
     */
    protected Collection<FieldValue> getAllFieldValuesFromSizeProducts(final ProductModel model, final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        if (model instanceof ApparelStyleVariantProductModel) {
            for (final VariantProductModel variantSizeProductModel : model.getVariants()) {
                if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                    fieldValues.addAll(getFieldValuesFromSizeProduct((ApparelSizeVariantProductModel) variantSizeProductModel,
                            indexedProperty));
                }
            }
        } else {
            for (final VariantProductModel variantStyleProductModel : model.getVariants()) {
                for (final VariantProductModel variantSizeProductModel : variantStyleProductModel.getVariants()) {
                    if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                        fieldValues.addAll(getFieldValuesFromSizeProduct((ApparelSizeVariantProductModel) variantSizeProductModel,
                                indexedProperty));
                    }
                }
            }
        }
        return fieldValues;
    }

    /**
     * Sets the fieldNameProvider.
     *
     * @param fieldNameProvider
     *        Field Name Provider.
     */
    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

}
