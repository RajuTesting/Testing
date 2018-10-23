package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.DateRestrictionModel;
import de.hybris.platform.voucher.model.ProductRestrictionModel;
import de.hybris.platform.voucher.model.RestrictionModel;
import de.hybris.platform.voucher.model.VoucherModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

public class PromotionCodeValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {
    private FieldNameProvider fieldNameProvider;
    private PromotionsService promotionService;
    private VoucherService voucherService;
    private boolean productRestriction = false;
    /** DATE_RESTRICTION {@link DateRestrictionModel} Constant. */
    public static final String DATE_RESTRICTION = "DateRestriction";

    protected FieldNameProvider getFieldNameProvider() {
        return this.fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected PromotionsService getPromotionsService() {
        return this.promotionService;
    }

    @Required
    public void setPromotionsService(final PromotionsService promotionService) {
        this.promotionService = promotionService;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model)
            throws FieldValueProviderException {
        if (model instanceof ProductModel) {
            ProductModel product = (ProductModel) model;
            if (product instanceof ApparelStyleVariantProductModel) {
                product = ((ApparelStyleVariantProductModel) product).getBaseProduct();
            }

            final Collection fieldValues = new ArrayList();

            if (indexedProperty.isMultiValue()) {
                fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty));
            } else {
                fieldValues.addAll(createFieldValue(product, indexConfig, indexedProperty));
            }
            return fieldValues;

        }

        throw new FieldValueProviderException("Cannot get promotion codes of non-product item");
    }

    protected List<FieldValue> createFieldValue(final ProductModel product, final IndexConfig indexConfig,
            final IndexedProperty indexedProperty) {
        final List fieldValues = new ArrayList();
        final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
        if ((baseSiteModel != null) && (baseSiteModel.getDefaultPromotionGroup() != null)) {
            final Iterator localIterator = getPromotionsService().getProductPromotions(
                    Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product).iterator();
            if (localIterator.hasNext()) {
                final ProductPromotionModel promotion = (ProductPromotionModel) localIterator.next();

                addFieldValues(fieldValues, indexedProperty, null, promotion.getCode());
            }
        }

        return fieldValues;
    }

    protected List<FieldValue> createFieldValues(final ProductModel product, final IndexConfig indexConfig,
            final IndexedProperty indexedProperty) {
        final List fieldValues = new ArrayList();
        final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
        if ((baseSiteModel != null) && (baseSiteModel.getDefaultPromotionGroup() != null)) {
            for (final ProductPromotionModel promotion : getPromotionsService().getProductPromotions(
                    Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product)) {
                if (null != promotion.getShowOnNav() && promotion.getShowOnNav()) {
                    addFieldValues(fieldValues, indexedProperty, null, promotion.getCode());
                }
            }
        }

        // SSLM-251 Starts
        // SSLM-1542 Changes for Universal Voucher Starts
        if ((baseSiteModel != null) && (getVoucherService().getAllVouchers() != null)) {
            for (final VoucherModel voucher : getVoucherService().getAllVouchers()) {
                boolean dateRestriction = true;
                if (Boolean.TRUE.equals(voucher.getShowOnNav())) {
                    if (!voucher.getRestrictions().isEmpty()) {
                        String typeCode;
                        for (final RestrictionModel restriction : voucher.getRestrictions()) {
                            typeCode = restriction.getItemtype();
                            if (StringUtils.equals(DATE_RESTRICTION, typeCode)
                                    && this.validateForDateRestriction((DateRestrictionModel) restriction)) {
                                dateRestriction = false;
                                break;
                            }
                            if (restriction instanceof ProductRestrictionModel) {
                                productRestriction = true;
                                break;
                            }
                        }

                        if (productRestriction && dateRestriction) {
                            for (final RestrictionModel restriction : voucher.getRestrictions()) {
                                if (restriction instanceof ProductRestrictionModel
                                        && (!((ProductRestrictionModel) restriction).getProducts().isEmpty())) {
                                    if (Boolean.TRUE.equals(restriction.getPositive())
                                            && ((ProductRestrictionModel) restriction).getProducts().contains(product)) {
                                        addFieldValues(fieldValues, indexedProperty, null, voucher.getCode());
                                    } else if (Boolean.FALSE.equals(restriction.getPositive())
                                            && !((ProductRestrictionModel) restriction).getProducts().contains(product)) {
                                        addFieldValues(fieldValues, indexedProperty, null, voucher.getCode());
                                    }
                                }

                            }
                        } else if (dateRestriction) {
                            addFieldValues(fieldValues, indexedProperty, null, voucher.getCode());
                        }
                    } else {
                        addFieldValues(fieldValues, indexedProperty, null, voucher.getCode());

                    }
                }

            }
        }

        // SSLM-251 Ends
        productRestriction = false;
        // SSLM-1542 Changes for Universal Voucher
        return fieldValues;
    }

    /**
     * Validate the voucher is expired or not.
     *
     * @return if it is expired then return true otherwise false.
     */
    private boolean validateForDateRestriction(final DateRestrictionModel dateRestriction) {
        boolean isExpired = false;
        final Date startDate = dateRestriction.getStartDate();
        final Date endDate = dateRestriction.getEndDate();
        final Date currentDate = new Date();
        if (!(currentDate.after(startDate) && currentDate.before(endDate))) {
            isExpired = true;
        }
        return isExpired;
    }

    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty, final LanguageModel language,
            final Object value) {
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
                (language == null) ? null : language.getIsocode());
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

    // SSLM-251 Starts

    /**
     * @return the voucherService
     */
    public VoucherService getVoucherService() {
        return voucherService;
    }

    /**
     * @param voucherService
     *        the voucherService to set
     */
    public void setVoucherService(final VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    // SSLM-251 Ends
}
