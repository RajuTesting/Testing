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
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants.NormalizedLookup;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SizeMappingModel;
import com.borngroup.ssl.core.services.SSLLookupService;

public class SslSizeFacetValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;

    @Resource
    private transient CommerceStockService commerceStockService;

    /** The base store service. */
    @Resource
    private BaseStoreService baseStoreService;

    protected static final Logger LOG = Logger.getLogger(SslSizeFacetValueProvider.class);

    @Resource(name = "sslLookupService")
    private SSLLookupService sslLookupService;

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            return getAllFieldValuesFromSizeProducts(apparelModel, indexedProperty);
        } else if (model instanceof ApparelStyleVariantProductModel) {
            final ApparelStyleVariantProductModel apparelModel = (ApparelStyleVariantProductModel) model;
            return getAllFieldValuesFromSizeProducts(apparelModel, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * Get the field values list from variants
     *
     * @param apparelSizeModel
     * @param indexedProperty
     * @return fieldValues or emptyList
     */
    protected Collection<FieldValue> getFieldValuesFromSizeProduct(final ApparelSizeVariantProductModel apparelSizeModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<>();
        try {
            final BaseStoreModel baseStore = baseStoreService.getCurrentBaseStore();
            final long stock = commerceStockService.getStockLevelForProductAndBaseStore(apparelSizeModel, baseStore);
            if (stock > 0L) {
				populateFieldValues(apparelSizeModel, indexedProperty, fieldValues);
            }
            return fieldValues;
        } catch (final StockLevelNotFoundException ex) {
            LOG.debug(ex.getMessage());
            return Collections.emptyList();
        }
    }

	private void populateFieldValues(ApparelSizeVariantProductModel apparelSizeModel, IndexedProperty indexedProperty,
			Collection<FieldValue> fieldValues) {
		final String deptCode = apparelSizeModel.getBaseProduct().getDepartmentCode();
		final String subDeptCode = apparelSizeModel.getBaseProduct().getSubDepartmentCode();
		final String brandCode = apparelSizeModel.getBaseProduct().getBrandCode();
		String altBrandCode = apparelSizeModel.getBaseProduct().getAltBrandCode();
		altBrandCode = altBrandCode != null && !altBrandCode.isEmpty() ? altBrandCode : "";

		apparelSizeModel.setBrandCode(brandCode);
		apparelSizeModel.setAltBrandCode(altBrandCode);
		apparelSizeModel.setDepartmentCode(deptCode);
		apparelSizeModel.setSubDepartmentCode(subDeptCode);
		if (!StringUtils.isEmpty(apparelSizeModel.getSize())) {
			SizeMappingModel lookedUpSizeCode = sslLookupService.getSizeCodeForInputStrings(apparelSizeModel.getSize(), deptCode,
					altBrandCode, subDeptCode);
			if (lookedUpSizeCode == null) {
				lookedUpSizeCode = sslLookupService.getSizeCodeForActualCode(apparelSizeModel.getSize(), deptCode, altBrandCode,
						subDeptCode);
			}
			if (lookedUpSizeCode != null) {
				final SizeMappingModel actualSizeCode = sslLookupService.getSizeCodeForActualCode(
						lookedUpSizeCode.getAltSizeCode(), deptCode, altBrandCode, subDeptCode);
				if (!StringUtils.isEmpty(actualSizeCode)) {
					final String size = actualSizeCode.getAltSizeCode();
					final String newsize = actualSizeCode.getNewSize();
					if (!StringUtils.isEmpty(newsize) && !NormalizedLookup.SizeNotMapped.equals(newsize)) {
						fieldValues.addAll(createFieldValue(newsize, indexedProperty));

					} else {
						if (!StringUtils.isEmpty(size) && !NormalizedLookup.SizeNotMapped.equals(size)) {
							fieldValues.addAll(createFieldValue(size, indexedProperty));
						}
					}

				}
			}
		}
	}

	/**
     * Create the field value out of size and indexed property
     *
     * @param size
     * @param indexedProperty
     * @return fieldValues
     */
    protected List<FieldValue> createFieldValue(final String size, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, size));
        }
        return fieldValues;
    }

    /**
     * Get all the size facets from the Base product
     *
     * @param model
     * @param indexedProperty
     * @return fieldValues
     */
    protected Collection<FieldValue> getAllFieldValuesFromSizeProducts(final ApparelProductModel model,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<>();
        for (final VariantProductModel variantStyleProductModel : model.getVariants()) {
            for (final VariantProductModel variantSizeProductModel : variantStyleProductModel.getVariants()) {
                if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                    fieldValues.addAll(getFieldValuesFromSizeProduct((ApparelSizeVariantProductModel) variantSizeProductModel,
                            indexedProperty));
                }
            }
        }
        return fieldValues;
    }

    protected Collection<FieldValue> getAllFieldValuesFromSizeProducts(final ApparelStyleVariantProductModel model,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<>();
        for (final VariantProductModel variantSizeProductModel : model.getVariants()) {
            if (variantSizeProductModel instanceof ApparelSizeVariantProductModel) {
                fieldValues
                        .addAll(getFieldValuesFromSizeProduct((ApparelSizeVariantProductModel) variantSizeProductModel, indexedProperty));
            }
        }
        return fieldValues;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

}
