
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

import javax.annotation.Resource;

/**
 * @author osheengulati
 *
 */
public class SSLColorPDPValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;
    @Resource(name = "i18nService")
    private I18NService i18nService;

    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ApparelProductModel) {
            final ApparelProductModel apparelModel = (ApparelProductModel) model;
            return getAllFieldValuesFromStyleProducts(apparelModel, indexedProperty);
        } else if (model instanceof ApparelStyleVariantProductModel) {
            return getFieldValuesFromStyleProduct((ApparelStyleVariantProductModel) model, indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * Get the field values list from variants
     *
     * @param apparelStyleModel
     * @param indexedProperty
     * @return
     */
    protected Collection<FieldValue> getFieldValuesFromStyleProduct(final ApparelStyleVariantProductModel apparelStyleModel,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<>();
        final String color = apparelStyleModel.getColourCode();
        final String style = apparelStyleModel.getStyle(i18nService.getCurrentLocale());
        if (StringUtils.isNotEmpty(style)) {
            fieldValues.addAll(createFieldValue(style, indexedProperty));
        }
        else if(color != null && !color.isEmpty()) {
            fieldValues.addAll(createFieldValue(color, indexedProperty));
        }
        if(CollectionUtils.isNotEmpty(fieldValues)){
            return fieldValues;
        }
        return Collections.emptyList();
    }

    /**
     * Create the field value out of color and indexed property
     *
     * @param color
     * @param indexedProperty
     * @return
     */
    protected List<FieldValue> createFieldValue(final String color, final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, color.toLowerCase()));
        }
        return fieldValues;
    }

    /**
     * Get all the color facets from the Base product
     *
     * @param model
     * @param indexedProperty
     * @return
     */
    protected Collection<FieldValue> getAllFieldValuesFromStyleProducts(final ApparelProductModel model,
            final IndexedProperty indexedProperty) {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        for (final VariantProductModel variantProductModel : model.getVariants()) {
            if (variantProductModel instanceof ApparelStyleVariantProductModel) {
                fieldValues.addAll(getFieldValuesFromStyleProduct((ApparelStyleVariantProductModel) variantProductModel, indexedProperty));
            }
            if (variantProductModel instanceof ApparelSizeVariantProductModel) {
                fieldValues.addAll(getFieldValuesFromStyleProduct(
                        (ApparelStyleVariantProductModel) ((ApparelSizeVariantProductModel) variantProductModel).getBaseProduct(),
                        indexedProperty));
            }
        }
        return fieldValues;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}