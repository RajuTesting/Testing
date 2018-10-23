/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.services.SSLPriceService;

/**
 * Price service generates best price row from all price rows list with specific business logic.
 *
 * @author midhun.bose
 *
 */
public class SSLPriceServiceImpl implements SSLPriceService {

    /** Log4j logger. */
    private static final Logger LOG = Logger.getLogger(SSLPriceServiceImpl.class);

    /** i18n Service. */
    @Resource(name = "i18nService")
    private I18NService i18nService;

    /** Model Service. */
    @Resource(name = "modelService")
    private ModelService modelService;

    /** Price Service. */
    @Resource(name = "priceService")
    private PriceService priceService;

    /** Price Comparator {@link Comparator}. */
    private static final Comparator<PriceRowModel> PRICE_COMPARATOR = new Comparator<PriceRowModel>() {

        /** Compare price logic updated for sorting Price rows using Price type and Event ID */
        @Override
        public int compare(final PriceRowModel price1, final PriceRowModel price2) {
            if (null != price1.getPriceType() && null != price2.getPriceType()) {
                if (price1.getPriceType().intValue() < price2.getPriceType().intValue()) {
                    return -1;
                } else if (price1.getPriceType().intValue() > price2.getPriceType().intValue()) {
                    return 1;
                } else {
                    return sortPriceRows(price1,price2);
                }
            } else if ((null != price2.getPriceType() || null != price1.getPriceType())) {
                return sortPriceRows(price1,price2);
            }
            return price1.getPrice().compareTo(price2.getPrice());
        }
    };

    /**
     * @description this method sorts the price rows based on Preference ID or Event ID 
     * @param price1
     * @param price2
     * @return int
     */
    public static int sortPriceRows(final PriceRowModel price1,
            final PriceRowModel price2)
    {
        if (null != price1.getPreferenceId()
                && null != price2.getPreferenceId())
        {
            return (new BigInteger(price1.getPreferenceId())
                    .compareTo(new BigInteger(price2.getPreferenceId()))) == 1 ? -1
                    : 1;
        }
        else if (null == price1.getPreferenceId()
                && null != price2.getPreferenceId())
        {
            return 1;
        }
        else if (null != price1.getPreferenceId()
                && null == price2.getPreferenceId())
        {
            return -1;
        }
        else if (null != price1.getEventId() && null != price2.getEventId())
        {
            return (new BigInteger(price1.getEventId())
                    .compareTo(new BigInteger(price2.getEventId()))) == 1 ? -1
                    : 1;
        }
        return price1.getPrice().compareTo(price2.getPrice());
    }
    @Override
    public PriceRowModel getBestPrice(final ProductModel productModel) {
        final List<PriceRowModel> priceRows = new ArrayList<>();
        if (productModel instanceof ApparelSizeVariantProductModel && CollectionUtils.isNotEmpty(productModel.getEurope1Prices())) {
            for (final PriceRowModel priceRow : productModel.getEurope1Prices()) {
                if (priceRow.getCurrency() != null && priceRow.getCurrency().getIsocode().equals("INR")
                        && ((priceRow.getDateRange() != null && priceRow.getDateRange().encloses(new Date()))
                                || (priceRow.getDateRange() == null))
                        && priceRow.getPrice() != null) {
                    priceRows.add(priceRow);
                }
            }
            PriceRowModel rowModel = null;
            Collections.sort(priceRows, PRICE_COMPARATOR);
            if (CollectionUtils.isNotEmpty(priceRows)) {
                rowModel = priceRows.get(0);
            } else {
                LOG.debug("Zero price rows exist for product : " + productModel.getCode());
            }
            return rowModel;
        } else {
            return null;
        }
    }
}
