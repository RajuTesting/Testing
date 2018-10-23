/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.catalog.jalo.classification.util.Feature;
import de.hybris.platform.catalog.jalo.classification.util.FeatureContainer;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.CommerceClassificationPropertyValueProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collection;
import java.util.Collections;

/**
 * @author jasleensaini
 *
 */
public class SslCommerceClassificationPropertyValueProvider extends CommerceClassificationPropertyValueProvider {
    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.CommerceClassificationPropertyValueProvider#getFieldValues(
     * de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
     */
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            final ClassAttributeAssignmentModel classAttributeAssignmentModel = indexedProperty.getClassAttributeAssignment();
            if(classAttributeAssignmentModel != null) {
                final ClassAttributeAssignment classAttributeAssignment = modelService.getSource(classAttributeAssignmentModel);
                Product product = null;
                if (model instanceof VariantProductModel) {
                    product = (Product) modelService.getSource(((VariantProductModel) model).getBaseProduct());
                } else {
                    product = (Product) modelService.getSource(model);
                }

                final FeatureContainer cont = FeatureContainer.loadTyped(product, classAttributeAssignment);
                if (cont.hasFeature(classAttributeAssignment)) {
                    final Feature feature = cont.getFeature(classAttributeAssignment);
                    if (feature == null || feature.isEmpty()) {
                        return Collections.emptyList();
                    } else {
                        return getFeaturesValues(indexConfig, feature, indexedProperty);
                    }
                } else {
                    return Collections.emptyList();
                }
            } else {
                return Collections.emptyList();
            }
        } else {
            throw new FieldValueProviderException("Cannot provide classification property of non-product item");
        }
    }
}
