/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.media.MediaModel;
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

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author kanagaraj.k
 *
 */
public class ProductVariantsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private FieldNameProvider fieldNameProvider;

    @Override
    public final Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
            final Object model) throws FieldValueProviderException {
        if (model instanceof ApparelProductModel && null != ((ApparelProductModel) model).getVariants()) {
            final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
            for (final VariantProductModel variantProduct : ((ApparelProductModel) model).getVariants()) {
                if (variantProduct instanceof ApparelStyleVariantProductModel) {

                    fieldValues.addAll(createFieldValue(variantProduct.getCode(),
                            ((ApparelStyleVariantProductModel) variantProduct).getColourCode(), variantProduct.getPicture(),
                            indexedProperty));
                }
            }
            return fieldValues;
        } else if (model instanceof ApparelStyleVariantProductModel) {
            return createFieldValue(((ApparelStyleVariantProductModel) model).getCode(),
                    ((ApparelStyleVariantProductModel) model).getColourCode(), ((ApparelStyleVariantProductModel) model).getPicture(),
                    indexedProperty);
        }
        return Collections.emptyList();
    }

    /**
     * The variant field value will be created with the combination of variantCode, Color and Image Url seperated by :::
     *
     * This value will be splitted in the populator to get the variant values to showcase in the listing
     *
     * @param productCode
     * @param color
     * @param value
     * @param indexedProperty
     * @return
     */
    protected List<FieldValue> createFieldValue(final String productCode, final String color, final MediaModel value,
            final IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            if (value != null) {
                fieldValues.add(new FieldValue(fieldName, productCode + ":::" + color + ":::" + value.getURL()));
            } else {
                fieldValues.add(new FieldValue(fieldName, productCode + ":::" + color + ":::"));
            }
        }
        return fieldValues;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}
