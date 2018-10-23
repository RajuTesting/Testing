/**
 * 
 */
package com.borngroup.ssl.core.order.price;

import de.hybris.platform.catalog.jalo.CatalogAwareEurope1PriceFactory;
import de.hybris.platform.europe1.channel.strategies.RetrieveChannelStrategy;
import de.hybris.platform.europe1.constants.Europe1Tools;
import de.hybris.platform.europe1.enums.PriceRowChannel;
import de.hybris.platform.europe1.jalo.Europe1PriceFactory;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Yamuna.V
 *
 */
public class ExtendedEurope1PriceFactory extends CatalogAwareEurope1PriceFactory {
	
	
	private RetrieveChannelStrategy retrieveChannelStrategy;
	
	 private ModelService        modelService;
	
	/**
	 * @return the modelService
	 */
	public ModelService getModelService() {
		return modelService;
	}


	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}


	/**
	 * @return the retrieveChannelStrategy
	 */
	public RetrieveChannelStrategy getRetrieveChannelStrategy() {
		return retrieveChannelStrategy;
	}


	/**
	 * @param retrieveChannelStrategy the retrieveChannelStrategy to set
	 */
	public void setRetrieveChannelStrategy(RetrieveChannelStrategy retrieveChannelStrategy) {
		this.retrieveChannelStrategy = retrieveChannelStrategy;
	}


	/* (non-Javadoc)
	 * @see de.hybris.platform.europe1.jalo.Europe1PriceFactory#getBasePrice(de.hybris.platform.jalo.order.AbstractOrderEntry)
	 */
	@Override
	public PriceValue getBasePrice(AbstractOrderEntry entry) throws JaloPriceFactoryException {
		// YTODO Auto-generated method stub
		SessionContext ctx = getSession().getSessionContext();
		AbstractOrder order = entry.getOrder(ctx);
		Currency currency = null;
		EnumerationValue productGroup = null;
		User user = null;
		EnumerationValue userGroup = null;
		Unit unit = null;
		long quantity = 0L;
		boolean net = false;
		Date date = null;
		Product product = entry.getProduct();
		boolean giveAwayMode = entry.isGiveAway(ctx).booleanValue();
		PriceRow row;
		if ((giveAwayMode) && (entry.isRejected(ctx).booleanValue())) {
			row = null;
		} else {
			productGroup = getPPG(ctx, product);
			user = order.getUser();
			userGroup = getUPG(ctx, user);
			quantity = entry.getQuantity(ctx).longValue();
			unit = entry.getUnit(ctx);
			currency = order.getCurrency(ctx);
			date = order.getDate(ctx);

			row = matchPriceRowForPrice1(ctx, product, productGroup, user, userGroup, quantity, unit, currency, date,
					net = order.isNet().booleanValue(), giveAwayMode);
		}

		if (row != null) {
			Currency rowCurr = row.getCurrency();
			return new PriceValue(currency.getIsoCode(),
					(currency.equals(rowCurr)) ? row.getPriceAsPrimitive() / row.getUnitFactorAsPrimitive()
							: rowCurr.convert(currency, row.getPriceAsPrimitive() / row.getUnitFactorAsPrimitive()),
					row.isNetAsPrimitive());
		}

		if (giveAwayMode) {
			return new PriceValue(order.getCurrency(ctx).getIsoCode(), 0.0D, order.isNet().booleanValue());
		}

		String msg = Localization.getLocalizedString(
				"exception.europe1pricefactory.getbaseprice.jalopricefactoryexception1",
				new Object[] { product, productGroup, user, userGroup, Long.toString(quantity), unit, currency, date,
						Boolean.toString(net) });
		throw new JaloPriceFactoryException(msg, 0);
	}

	@Override
	protected List getPriceInformations(SessionContext ctx, Product product, EnumerationValue productGroup, User user,
			EnumerationValue userGroup, Currency curr, boolean net, Date date, Collection taxValues)
					throws JaloPriceFactoryException {
		Collection<PriceRow> priceRows = filterPriceRows(
				matchPriceRowsForInfo(ctx, product, productGroup, user, userGroup, curr, date, net));
		List priceInfos = new ArrayList(priceRows.size());
		Collection theTaxValues = taxValues;

		List defaultPriceInfos = new ArrayList(priceRows.size());
		PriceRowChannel channel = retrieveChannelStrategy.getChannel(ctx);

		for (PriceRow row : priceRows) {
			PriceInformation pInfo = Europe1Tools.createPriceInformation(row, curr);

			if (pInfo.getPriceValue().isNet() != net) {
				if (theTaxValues == null) {
					theTaxValues = Europe1Tools.getTaxValues(
							getTaxInformations(product, getPTG(ctx, product), user, getUTG(ctx, user), date));
				}

				pInfo = new PriceInformation(pInfo.getQualifiers(), pInfo.getPriceValue().getOtherPrice(theTaxValues));
			}

			if (row.getChannel() == null) {
				defaultPriceInfos.add(pInfo);
			}

			if ((channel == null) && (row.getChannel() == null)) {
				priceInfos.add(pInfo);
			} else {
				if ((channel == null) || (row.getChannel() == null)
						|| (!(row.getChannel().getCode().equalsIgnoreCase(channel.getCode()))))
					continue;
				priceInfos.add(pInfo);
			}
		}

		if (priceInfos.size() == 0) {
			return defaultPriceInfos;
		}

		return priceInfos;
	}

	
	@Override
	public List<PriceRow> matchPriceRowsForInfo(SessionContext ctx, Product product, EnumerationValue productGroup,
			User user, EnumerationValue userGroup, Currency currency, Date date, boolean net)
					throws JaloPriceFactoryException {
		if ((product == null) && (productGroup == null)) {
			throw new JaloPriceFactoryException(
					"cannot match price info without product and product group - at least one must be present", 0);
		}
		if ((user == null) && (userGroup == null)) {
			throw new JaloPriceFactoryException(
					"cannot match price info without user and user group - at least one must be present", 0);
		}
		if (currency == null) {
			throw new JaloPriceFactoryException("cannot match price info without currency", 0);
		}
		if (date == null) {
			throw new JaloPriceFactoryException("cannot match price info without date", 0);
		}
		Collection rows = queryPriceRows4Price(ctx, product, productGroup, user, userGroup);
		if (!(rows.isEmpty())) {
			PriceRowChannel channel = this.retrieveChannelStrategy.getChannel(ctx);
			List ret = new ArrayList<>(rows);
			if (ret.size() > 1) {
				Collections.sort(ret, new PriceRowInfoComparator(currency, net));
			}
			return filterPriceRows4Info(ret, currency, date, channel);
		}

		return Collections.EMPTY_LIST;
	}
	public PriceRow matchPriceRowForPrice1(SessionContext ctx, Product product, EnumerationValue productGroup, User user,
			EnumerationValue userGroup, long qtd, Unit unit, Currency currency, Date date, boolean net,
			boolean giveAwayMode) throws JaloPriceFactoryException {
		if ((product == null) && (productGroup == null)) {
			throw new JaloPriceFactoryException(
					"cannot match price without product and product group - at least one must be present", 0);
		}
		if ((user == null) && (userGroup == null)) {
			throw new JaloPriceFactoryException(
					"cannot match price without user and user group - at least one must be present", 0);
		}
		if (currency == null) {
			throw new JaloPriceFactoryException("cannot match price without currency", 0);
		}
		if (date == null) {
			throw new JaloPriceFactoryException("cannot match price without date", 0);
		}
		if (unit == null) {
			throw new JaloPriceFactoryException("cannot match price without unit", 0);
		}

		Collection rows = queryPriceRows4Price(ctx, product, productGroup, user, userGroup);
		if (!(rows.isEmpty())) {
			PriceRowChannel channel = retrieveChannelStrategy.getChannel(ctx);
			List list = filterPriceRows4Price(rows, qtd, unit, currency, date, giveAwayMode, channel);

			if (list.isEmpty()) {
				return null;
			}
			if (list.size() == 1) {
				return ((PriceRow) list.get(0));
			}

			Collections.sort(list, new PriceRowMatchComparator(currency, net, unit));
			Collections.sort(list, new Comparator<PriceRow>()
	        {
	            @Override
	            public int compare(final PriceRow arg0, final PriceRow arg1)
	            {
	            	PriceRowModel price1=modelService.get(arg0);
	            	PriceRowModel price2=modelService.get(arg1);
	            	
	                if (price1 == null || price1.getPrice() == null)
	                {
	                    return -1;
	                }
	                if (price2 == null || price2.getPrice()== null)
	                {
	                    return 1;
	                }
	                return Double.compare(price1.getPrice().doubleValue(), (price2.getPrice().doubleValue()));
	            }
	        });
			return ((PriceRow) list.get(0));
		}

		return null;
	}

	
	protected class PriceRowMatchComparator implements Comparator<PriceRow> {
		private final Currency curr;
		private final boolean net;
		private final Unit unit;

		protected PriceRowMatchComparator(Currency paramCurrency, boolean paramBoolean, Unit paramUnit) {
			this.curr = paramCurrency;
			this.net = paramBoolean;
			this.unit = paramUnit;
		}

		public int compare(PriceRow row1, PriceRow row2) {
			int matchValue1 = row1.getMatchValueAsPrimitive();
			int matchValue2 = row2.getMatchValueAsPrimitive();
			if (matchValue1 != matchValue2) {
				return (matchValue2 - matchValue1);
			}

			boolean c1Match = this.curr.equals(row1.getCurrency());
			boolean c2Match = this.curr.equals(row2.getCurrency());
			if (c1Match != c2Match) {
				return ((c1Match) ? -1 : 1);
			}

			boolean n1Match = this.net == row1.isNetAsPrimitive();
			boolean n2Match = this.net == row2.isNetAsPrimitive();
			if (n1Match != n2Match) {
				return ((n1Match) ? -1 : 1);
			}

			boolean u1Match = this.unit.equals(row1.getUnit());
			boolean u2Match = this.unit.equals(row2.getUnit());
			if (u1Match != u2Match) {
				return ((u1Match) ? -1 : 1);
			}

			long min1 = row1.getMinqtdAsPrimitive();
			long min2 = row2.getMinqtdAsPrimitive();
			if (min1 != min2) {
				return ((min1 > min2) ? -1 : 1);
			}

			boolean row1Range = row1.getStartTime() != null;
			boolean row2Range = row2.getStartTime() != null;

			if (row1Range != row2Range) {
				return ((row1Range) ? -1 : 1);
			}

//			Europe1PriceFactory.LOG
//					.warn("found ambigous price rows " + row1 + " and " + row2 + " - using PK to distinguish");

			return row1.getPK().compareTo(row2.getPK());
		}
	}
	
	protected class PriceRowInfoComparator implements Comparator<PriceRow> {
		private final Currency curr;
		private final boolean net;

		protected PriceRowInfoComparator(Currency paramCurrency, boolean paramBoolean) {
			this.curr = paramCurrency;
			this.net = paramBoolean;
		}

		public int compare(PriceRow row1, PriceRow row2) {
			long u1Match = row1.getUnit().getPK().getLongValue();
			long u2Match = row2.getUnit().getPK().getLongValue();
			if (u1Match != u2Match) {
				return ((u1Match < u2Match) ? -1 : 1);
			}

			long min1 = row1.getMinqtdAsPrimitive();
			long min2 = row2.getMinqtdAsPrimitive();
			if (min1 != min2) {
				return ((min1 > min2) ? -1 : 1);
			}

			int matchValue1 = row1.getMatchValueAsPrimitive();
			int matchValue2 = row2.getMatchValueAsPrimitive();
			if (matchValue1 != matchValue2) {
				return (matchValue2 - matchValue1);
			}

			boolean c1Match = this.curr.equals(row1.getCurrency());
			boolean c2Match = this.curr.equals(row2.getCurrency());
			if (c1Match != c2Match) {
				return ((c1Match) ? -1 : 1);
			}

			boolean n1Match = this.net == row1.isNetAsPrimitive();
			boolean n2Match = this.net == row2.isNetAsPrimitive();
			if (n1Match != n2Match) {
				return ((n1Match) ? -1 : 1);
			}

			boolean row1Range = row1.getStartTime() != null;
			boolean row2Range = row2.getStartTime() != null;

			if (row1Range != row2Range) {
				return ((row1Range) ? -1 : 1);
			}
			return row1.getPK().compareTo(row2.getPK());
		}
	}
}
