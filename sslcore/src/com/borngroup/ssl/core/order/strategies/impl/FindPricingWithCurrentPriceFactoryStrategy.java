/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.borngroup.ssl.core.order.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.europe1.constants.Europe1Tools;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.OrderManager;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.order.price.PriceFactory;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.FindDiscountValuesStrategy;
import de.hybris.platform.order.strategies.calculation.FindPriceStrategy;
import de.hybris.platform.order.strategies.calculation.FindTaxValuesStrategy;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.util.DateRange;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.PriceValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;


/**
 * Default implementation of price, taxes and discounts resolver strategies ({@link FindPriceStrategy},
 * {@link FindDiscountValuesStrategy}, {@link FindTaxValuesStrategy}) that resolves values for calculation from current
 * session's price factory. If no session price factory is set it uses {@link OrderManager#getPriceFactory()} which will
 * retrieve the default one according to system settings.
 */
public class FindPricingWithCurrentPriceFactoryStrategy extends AbstractBusinessService implements FindPriceStrategy, FindDiscountValuesStrategy, FindTaxValuesStrategy
{

    private PriceService priceService;

    /**
     * @return the priceService
     */
    public PriceService getPriceService()
    {
        return priceService;
    }

    /**
     * @param priceService
     *            the priceService to set
     */
    public void setPriceService(final PriceService priceService)
    {
        this.priceService = priceService;
    }

    @Override
    public Collection findTaxValues(final AbstractOrderEntryModel entry) throws CalculationException
    {
        final AbstractOrderEntry entryItem = getModelService().getSource(entry);
        try
        {
            return getCurrentPriceFactory().getTaxValues(entryItem);
        }
        catch (final JaloPriceFactoryException e)
        {
            throw new CalculationException(e);
        }
    }

    @Override
    public PriceValue findBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
    {
        /**
         * We need to remove this code and move to our ssl price factory after the release this quick and dirty fix is
         * for time being
         **/
        try
        {
            final Collection<PriceRowModel> priceRows = entry.getProduct().getEurope1Prices();
            final AbstractOrderEntry entryItem = getModelService().getSource(entry);
            final List<PriceRowModel> priceRowsFiltered = filterPriceRows4DateRange(priceRows, new Date());
            PriceRowModel bestPriceRow = null;
            for (final PriceRowModel pi : priceRowsFiltered)
            {
                if (bestPriceRow == null || bestPriceRow.getPrice() > pi.getPrice())
                {
                    bestPriceRow = pi;
                }
            }
            if (bestPriceRow != null && JaloSession.getCurrentSession() != null && JaloSession.getCurrentSession().getSessionContext() != null)
            {
                final PriceRow pricerow = getModelService().getSource(bestPriceRow);
                final PriceInformation bestPrice = Europe1Tools.createPriceInformation(pricerow, JaloSession.getCurrentSession().getSessionContext().getCurrency());
                return bestPrice.getPriceValue();
            }
            else
            {
                return getCurrentPriceFactory().getBasePrice(entryItem);
            }
        }
        catch (final JaloPriceFactoryException e)
        {
            throw new CalculationException(e);
        }
    }

    protected List<PriceRowModel> filterPriceRows4DateRange(final Collection<PriceRowModel> rows, final Date date)
    {
        if (rows.isEmpty())
        {
            return Collections.EMPTY_LIST;
        }

        final List<PriceRowModel> ret = new ArrayList(rows);
        for (final ListIterator it = ret.listIterator(); it.hasNext();)
        {
            final PriceRowModel priceRow = (PriceRowModel) it.next();
            final DateRange dateRange = priceRow.getDateRange();
            if ((dateRange != null) && (!(dateRange.encloses(date))))
            {
                it.remove();
            }
        }

        return ret;
    }

    @Override
    public List<DiscountValue> findDiscountValues(final AbstractOrderEntryModel entry) throws CalculationException
    {
        final AbstractOrderEntry entryItem = getModelService().getSource(entry);
        try
        {
            return getCurrentPriceFactory().getDiscountValues(entryItem);
        }
        catch (final JaloPriceFactoryException e)
        {
            throw new CalculationException(e);
        }
    }

    @Override
    public List<DiscountValue> findDiscountValues(final AbstractOrderModel order) throws CalculationException
    {
        final AbstractOrder orderItem = getModelService().getSource(order);
        try
        {
            return getCurrentPriceFactory().getDiscountValues(orderItem);
        }
        catch (final JaloPriceFactoryException e)
        {
            throw new CalculationException(e);
        }
    }

    public PriceFactory getCurrentPriceFactory()
    {
        // Actually OrderManager.getPriceFactory() implements default / session specific price
        // factory fetching. So no need to do it twice.
        return OrderManager.getInstance().getPriceFactory();
    }
}
