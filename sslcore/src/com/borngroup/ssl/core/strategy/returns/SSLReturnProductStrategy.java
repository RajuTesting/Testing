/**
 *
 */
package com.borngroup.ssl.core.strategy.returns;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.RefundReason;
import de.hybris.platform.basecommerce.enums.ReplacementReason;
import de.hybris.platform.basecommerce.enums.ReturnAction;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReplacementEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.data.returns.ReturnableEntity;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ExchangeStockLevelModel;
import com.borngroup.ssl.core.services.returns.SSLReturnService;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.enums.InternalConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;

/**
 * @author atul2455
 *
 */
public class SSLReturnProductStrategy {

    @Autowired
    private ModelService modelService;

    @Autowired
    private SSLReturnService returnService;

    private void sortConsignmentByMaxShippedQuantity(final List<Entry<ConsignmentEntryModel, Long>> consignments) {
        Collections.sort(consignments, new Comparator<Map.Entry<ConsignmentEntryModel, Long>>() {
            @Override
            public int compare(final Map.Entry<ConsignmentEntryModel, Long> c1, final Map.Entry<ConsignmentEntryModel, Long> c2) {
                long c1ShippedQuantity = c1.getKey().getShippedQuantity().longValue();
                if (null != c1.getKey().getInternalEntries() && null != c1.getKey().getInternalEntries()
                        && !c1.getKey().getInternalEntries().isEmpty()) {
                    for (final InternalConsignmentEntryModel internalEntry : c1.getKey().getInternalEntries()) {
                        if (null != internalEntry.getShippedQuantity()) {
                            c1ShippedQuantity += internalEntry.getShippedQuantity().longValue();
                        }
                    }
                }
                final Long availableC1 = Long.valueOf(c1ShippedQuantity - c1.getValue().longValue());

                long c2ShippedQuantity = c2.getKey().getShippedQuantity().longValue();
                if (null != c2.getKey().getInternalEntries() && null != c2.getKey().getInternalEntries()
                        && !c2.getKey().getInternalEntries().isEmpty()) {
                    for (final InternalConsignmentEntryModel internalEntry : c2.getKey().getInternalEntries()) {
                        if (null != internalEntry.getShippedQuantity()) {
                            c2ShippedQuantity += internalEntry.getShippedQuantity().longValue();
                        }
                    }
                }
                final Long availableC2 = Long.valueOf(c2ShippedQuantity - c2.getValue().longValue());

                return availableC2.compareTo(availableC1);
            }
        });

    }

    private ReturnableEntity createReturnableEntity(final ConsignmentEntryModel consignmentEntry, final Long quantity, final String notes,
            final RefundReason reason) {
        final ReturnableEntity entity = new ReturnableEntity();
        final RefundEntryModel refundEntryModel = modelService.create(RefundEntryModel.class);
        refundEntryModel.setOrderEntry(consignmentEntry.getOrderEntry());
        refundEntryModel.setExpectedQuantity(quantity);
        refundEntryModel.setStatus(ReturnStatus.NOT_PICKED);
        refundEntryModel.setAction(ReturnAction.IMMEDIATE);
        refundEntryModel.setReason(reason);
        refundEntryModel.setNotes(notes);
        modelService.save(refundEntryModel);
        entity.setRefundEntry(refundEntryModel);
        return entity;
    }

    private ReplacementEntryModel createReplacableEntity(final ConsignmentEntryModel consignmentEntry, final Long quantity,
            final String notes, final ReplacementReason reason, final Map<StockLevelModel, Long> stocksForReplacementEntry) {
        final ReplacementEntryModel replacementEntry = modelService.create(ReplacementEntryModel.class);
        replacementEntry.setOrderEntry(consignmentEntry.getOrderEntry());
        replacementEntry.setExpectedQuantity(quantity);
        replacementEntry.setStatus(ReturnStatus.NOT_PICKED);
        replacementEntry.setAction(ReturnAction.IMMEDIATE);
        replacementEntry.setReason(reason);
        replacementEntry.setNotes(notes);

        for (final Map.Entry<StockLevelModel, Long> stockForReplacement : stocksForReplacementEntry.entrySet()) {
            final ExchangeStockLevelModel exchangeStockLevel = modelService.create(ExchangeStockLevelModel.class);
            exchangeStockLevel.setStockLevel(stockForReplacement.getKey());
            exchangeStockLevel.setQuantity(stockForReplacement.getValue().longValue());
            exchangeStockLevel.setReplacementEntry(replacementEntry);
            modelService.save(exchangeStockLevel);
        }

        modelService.save(replacementEntry);
        return replacementEntry;
    }

    private Map<ConsignmentEntryModel, Long> findConsignmentWithMaxReturnableQuantity(
            final Map<ConsignmentEntryModel, Long> originalConsignmentEntriesReturnedQuantity, Long returnQuantity) {
        final Map<ConsignmentEntryModel, Long> consignmentWithFullfilledQuantity = new HashMap<>();
        final List<Map.Entry<ConsignmentEntryModel, Long>> consignments = new ArrayList(
                originalConsignmentEntriesReturnedQuantity.entrySet());
        if (consignments.size() >= 1) {
            this.sortConsignmentByMaxShippedQuantity(consignments);
            for (final Entry<ConsignmentEntryModel, Long> consignment : consignments) {
                if (returnQuantity.longValue() > 0) {

                    long consignmentShippedQuantity = consignment.getKey().getShippedQuantity().longValue();
                    if (null != consignment.getKey().getInternalEntries() && null != consignment.getKey().getInternalEntries()
                            && !consignment.getKey().getInternalEntries().isEmpty()) {
                        for (final InternalConsignmentEntryModel internalEntry : consignment.getKey().getInternalEntries()) {
                            if (null != internalEntry.getShippedQuantity()) {
                                consignmentShippedQuantity += internalEntry.getShippedQuantity().longValue();
                            }
                        }
                    }
                    final Long shippedQuantity = Long.valueOf(consignmentShippedQuantity - consignment.getValue().longValue());

                    if (shippedQuantity.longValue() >= returnQuantity.longValue()) {
                        consignmentWithFullfilledQuantity.put(consignment.getKey(), returnQuantity);
                        break;
                    } else {
                        consignmentWithFullfilledQuantity.put(consignment.getKey(), shippedQuantity);
                        returnQuantity = Long.valueOf(returnQuantity.longValue() - shippedQuantity.longValue());
                    }
                } else {
                    break;
                }
            }
        }

        return consignmentWithFullfilledQuantity;
    }

    public Map<String, ReplacementEntryModel> findReturnableEntities(final AbstractOrderEntryModel orderEntry, final Long returnQuantity,
            final String notes, final ReplacementReason reason, final Map<StockLevelModel, Long> bookedStocks) {
        final Map<String, ReplacementEntryModel> returnEntryPerODC = new HashMap<String, ReplacementEntryModel>();
        if (orderEntry != null && returnQuantity != null && returnQuantity.longValue() > 0l) {
            final Set<ConsignmentEntryModel> originalOrderEntryConsignmentEntries = new HashSet<ConsignmentEntryModel>();
            for (final ConsignmentEntryModel consignmentEntry : orderEntry.getConsignmentEntries()) {
                final ConsignmentModel consignment = consignmentEntry.getConsignment();
                if (null == consignment) {
                    continue;
                }
                if (null == consignment.getReturnRequest()
                        && !(consignment.getStatus().getCode().contains("EXCHANGE") || consignment.getStatus().getCode().contains("RETURN") || null == consignmentEntry
                                .getShippedQuantity()) && ConsignmentStatus.COMPLETE.equals(consignment.getStatus())) {
                    originalOrderEntryConsignmentEntries.add(consignmentEntry);
                }
            }

            final Set<String> ODCs = new HashSet<String>();
            for (final ConsignmentEntryModel originalConsignmentEntry : originalOrderEntryConsignmentEntries) {
                ODCs.add(originalConsignmentEntry.getConsignment().getWarehouse().getInvoiceStoreId());
            }

            final Map<String, Long> odcFulfilledQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                long quantityFulfilled = 0;
                for (final ConsignmentEntryModel originalConsignmentEntry : originalOrderEntryConsignmentEntries) {
                    final ConsignmentModel consignment = originalConsignmentEntry.getConsignment();
                    if (consignment.getWarehouse().getInvoiceStoreId().equals(odc)) {
                        long consignmentShippedQuantity = originalConsignmentEntry.getShippedQuantity();
                        if (null != originalConsignmentEntry.getInternalEntries()) {
                            if (!ConsignmentStatus.CANCELLED.equals(originalConsignmentEntry.getStatus())) {
                                for (final InternalConsignmentEntryModel internalEntry : originalConsignmentEntry.getInternalEntries()) {
                                    if (!InternalConsignmentEntryStatus.CANCELLED.equals(internalEntry.getStatus())) {
                                        consignmentShippedQuantity += internalEntry.getShippedQuantity();
                                    }
                                }
                            }
                        }
                        quantityFulfilled += consignmentShippedQuantity;
                    }
                }
                odcFulfilledQuantity.put(odc, quantityFulfilled);
            }

            final Map<String, Long> odcReturnedQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                long quantityAlreadyReturned = 0;
                for (final ConsignmentEntryModel consignmentEntry : orderEntry.getConsignmentEntries()) {
                    final ConsignmentModel consignment = consignmentEntry.getConsignment();
                    if (null != consignment.getReturnRequest()
                            && !ConsignmentStatus.CANCELLED.equals(consignment.getStatus())
                            && !(ReturnStatus.PICKUP_FAILED.equals(consignment.getReturnRequest().getStatus()) || ReturnStatus.CANCELED
                                    .equals(consignment.getReturnRequest().getStatus()))
                            && consignment.getWarehouse().getInvoiceStoreId().equals(odc)) {
                        quantityAlreadyReturned += consignmentEntry.getQuantity().longValue();
                    }
                }
                odcReturnedQuantity.put(odc, quantityAlreadyReturned);
            }

            final Map<String, Long> odcAvailableQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                odcAvailableQuantity.put(odc, odcFulfilledQuantity.get(odc) - odcReturnedQuantity.get(odc));
            }

            final Map<StockLevelModel, Long> bookedStocksLocal = new HashMap<StockLevelModel, Long>(bookedStocks);
            final Map<String, Long> odcWithFulfilledQuantityMap = findODCWithMaxReturnableQuantity(odcAvailableQuantity, returnQuantity);
            for (final Map.Entry<String, Long> entry : odcWithFulfilledQuantityMap.entrySet()) {
                final Map<StockLevelModel, Long> stocksForReplacementEntry = getProductsForReplacementEntry(bookedStocksLocal, entry
                        .getValue().longValue());
                final ReplacementEntryModel returnablEntity = this.createReplacableEntity(orderEntry, entry.getValue(), notes, reason,
                        stocksForReplacementEntry);
                final String ODCCode = entry.getKey();
                returnEntryPerODC.put(ODCCode, returnablEntity);
            }
        }

        return returnEntryPerODC;
    }

    private ReplacementEntryModel createReplacableEntity(final AbstractOrderEntryModel orderEntry, final Long quantity, final String notes,
            final ReplacementReason reason, final Map<StockLevelModel, Long> stocksForReplacementEntry) {
        final ReplacementEntryModel replacementEntry = modelService.create(ReplacementEntryModel.class);
        replacementEntry.setOrderEntry(orderEntry);
        replacementEntry.setExpectedQuantity(quantity);
        replacementEntry.setStatus(ReturnStatus.NOT_PICKED);
        replacementEntry.setAction(ReturnAction.IMMEDIATE);
        replacementEntry.setReason(reason);
        replacementEntry.setNotes(notes);

        for (final Map.Entry<StockLevelModel, Long> stockForReplacement : stocksForReplacementEntry.entrySet()) {
            final ExchangeStockLevelModel exchangeStockLevel = modelService.create(ExchangeStockLevelModel.class);
            exchangeStockLevel.setStockLevel(stockForReplacement.getKey());
            exchangeStockLevel.setQuantity(stockForReplacement.getValue().longValue());
            exchangeStockLevel.setReplacementEntry(replacementEntry);
            modelService.save(exchangeStockLevel);
        }

        modelService.save(replacementEntry);
        return replacementEntry;
    }

    private Map<StockLevelModel, Long> getProductsForReplacementEntry(final Map<StockLevelModel, Long> bookedStocks, final long quantity) {
        final List<Map.Entry<StockLevelModel, Long>> sortedBookedSlots = new LinkedList<Map.Entry<StockLevelModel, Long>>(
                bookedStocks.entrySet());
        sortedBookedSlots.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        final Map<StockLevelModel, Long> productsForReplacementEntry = new HashMap<>();
        long quantityForReplacement = quantity;
        for (final Map.Entry<StockLevelModel, Long> stockEntry : sortedBookedSlots) {
            final long stockQuantity = stockEntry.getValue().longValue();
            if (stockQuantity >= quantityForReplacement) {
                productsForReplacementEntry.put(stockEntry.getKey(), quantityForReplacement);
                bookedStocks.remove(stockEntry.getKey(), stockQuantity);
                if (stockQuantity - quantityForReplacement != 0) {
                    bookedStocks.put(stockEntry.getKey(), stockQuantity - quantityForReplacement);
                }
                break;
            } else {
                productsForReplacementEntry.put(stockEntry.getKey(), stockEntry.getValue());
                bookedStocks.remove(stockEntry.getKey(), stockQuantity);
                quantityForReplacement -= stockQuantity;
            }
        }

        return productsForReplacementEntry;
    }

    private static final Logger LOG = Logger.getLogger(SSLReturnProductStrategy.class);

    public Map<String, ReturnableEntity> findReturnableEntities(final AbstractOrderEntryModel orderEntry, final Long returnQuantity,
            final String notes, final RefundReason reason) {
        final Map<String, ReturnableEntity> returnEntryPerODC = new HashMap<String, ReturnableEntity>();
        if (orderEntry != null && returnQuantity != null && returnQuantity.longValue() > 0l) {
            final Set<ConsignmentEntryModel> originalOrderEntryConsignmentEntries = new HashSet<ConsignmentEntryModel>();
            for (final ConsignmentEntryModel consignmentEntry : orderEntry.getConsignmentEntries()) {
                final ConsignmentModel consignment = consignmentEntry.getConsignment();
                if (null == consignment) {
                    continue;
                }
                //ECD-2988 : Open RMA for Consignment status Delivered as well with Completed
				if (null == consignment.getReturnRequest()
						&& !(consignment.getStatus().getCode().contains("EXCHANGE")
								|| consignment.getStatus().getCode().contains("RETURN")
								|| null == consignmentEntry.getShippedQuantity())
						&& (ConsignmentStatus.COMPLETE.equals(consignment.getStatus())
								|| ConsignmentStatus.DELIVERED.equals(consignment.getStatus()))) {
                    originalOrderEntryConsignmentEntries.add(consignmentEntry);
                }
            }

            final Set<String> ODCs = new HashSet<String>();
            for (final ConsignmentEntryModel originalConsignmentEntry : originalOrderEntryConsignmentEntries) {
                ODCs.add(originalConsignmentEntry.getConsignment().getWarehouse().getInvoiceStoreId());
            }

            final Map<String, Long> odcFulfilledQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                long quantityFulfilled = 0;
                for (final ConsignmentEntryModel originalConsignmentEntry : originalOrderEntryConsignmentEntries) {
                    final ConsignmentModel consignment = originalConsignmentEntry.getConsignment();
                    if (consignment.getWarehouse().getInvoiceStoreId().equals(odc)) {
                        long consignmentShippedQuantity = originalConsignmentEntry.getShippedQuantity();
                        if (null != originalConsignmentEntry.getInternalEntries()) {
                            if (null != originalConsignmentEntry.getStatus()
                                    && !ConsignmentEntryStatus.CANCELLED.equals(originalConsignmentEntry.getStatus())) {

                                for (final InternalConsignmentEntryModel internalEntry : originalConsignmentEntry.getInternalEntries()) {

                                    if (null != originalConsignmentEntry.getStatus()
                                            && !InternalConsignmentEntryStatus.CANCELLED.equals(internalEntry.getStatus())) {
                                        consignmentShippedQuantity += internalEntry.getShippedQuantity();

                                    }
                                }
                            }
                        }
                        quantityFulfilled += consignmentShippedQuantity;
                    }
                }
                odcFulfilledQuantity.put(odc, quantityFulfilled);
            }

            final Map<String, Long> odcReturnedQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                long quantityAlreadyReturned = 0;
                for (final ConsignmentEntryModel consignmentEntry : orderEntry.getConsignmentEntries()) {
                    final ConsignmentModel consignment = consignmentEntry.getConsignment();
                    if (null != consignment.getReturnRequest()
                            && !ConsignmentStatus.CANCELLED.equals(consignment.getStatus())
                            && !(ReturnStatus.PICKUP_FAILED.equals(consignment.getReturnRequest().getStatus()) || ReturnStatus.CANCELED
                                    .equals(consignment.getReturnRequest().getStatus()))
                            && consignment.getWarehouse().getInvoiceStoreId().equals(odc)) {
                        quantityAlreadyReturned += consignmentEntry.getQuantity().longValue();
                    }
                }
                odcReturnedQuantity.put(odc, quantityAlreadyReturned);
            }

            final Map<String, Long> odcAvailableQuantity = new HashMap<String, Long>();
            for (final String odc : ODCs) {
                odcAvailableQuantity.put(odc, odcFulfilledQuantity.get(odc) - odcReturnedQuantity.get(odc));
            }

            final Map<String, Long> odcWithFulfilledQuantityMap = findODCWithMaxReturnableQuantity(odcAvailableQuantity, returnQuantity);
            for (final Map.Entry<String, Long> entry : odcWithFulfilledQuantityMap.entrySet()) {
                final ReturnableEntity returnablEntity = this.createReturnableEntity(orderEntry, entry.getValue(), notes, reason);
                final String ODCCode = entry.getKey();
                returnEntryPerODC.put(ODCCode, returnablEntity);
            }
        }

        return returnEntryPerODC;
    }

    private ReturnableEntity createReturnableEntity(final AbstractOrderEntryModel orderEntry, final Long quantity, final String notes,
            final RefundReason reason) {
        final ReturnableEntity entity = new ReturnableEntity();
        final RefundEntryModel refundEntryModel = modelService.create(RefundEntryModel.class);
        refundEntryModel.setOrderEntry(orderEntry);
        refundEntryModel.setExpectedQuantity(quantity);
        refundEntryModel.setStatus(ReturnStatus.NOT_PICKED);
        refundEntryModel.setAction(ReturnAction.IMMEDIATE);
        refundEntryModel.setReason(reason);
        refundEntryModel.setNotes(notes);
        modelService.save(refundEntryModel);
        entity.setRefundEntry(refundEntryModel);
        return entity;
    }

    private Map<String, Long> findODCWithMaxReturnableQuantity(final Map<String, Long> odcAvailableQuantity, Long returnQuantity) {
        final Map<String, Long> odcWithFullfilledQuantity = new HashMap<>();
        final List<Map.Entry<String, Long>> ODCs = new ArrayList(odcAvailableQuantity.entrySet());
        if (ODCs.size() >= 1) {
            Collections.sort(ODCs, (odc1, odc2) -> odc2.getValue().compareTo(odc1.getValue()));
            for (final Entry<String, Long> odc : ODCs) {
                if (returnQuantity.longValue() > 0) {
                    final Long availableQuantity = Long.valueOf(odc.getValue());
                    if (availableQuantity.longValue() >= returnQuantity.longValue()) {
                        odcWithFullfilledQuantity.put(odc.getKey(), returnQuantity);
                        break;
                    } else {
                        odcWithFullfilledQuantity.put(odc.getKey(), availableQuantity);
                        returnQuantity = Long.valueOf(returnQuantity.longValue() - availableQuantity.longValue());
                    }
                }
            }
        }

        return odcWithFullfilledQuantity;
    }

    public boolean isReturnEligible(final ConsignmentEntryModel consignmentEntry) {

        final OrderEntryModel orderEntry = (OrderEntryModel) consignmentEntry.getOrderEntry();
        final ProductModel product = orderEntry.getProduct();
        final Integer returnDays = returnService.getReturnDays(product);

        if (consignmentEntry.getConsignment().getStatus().equals(ConsignmentStatus.CANCELLED)
                && consignmentEntry.getConsignment().getReturnRequest() == null) {
            return false;
        } else if (consignmentEntry.getStatus().equals(ConsignmentEntryStatus.CANCELLED)
                && consignmentEntry.getConsignment().getReturnRequest() == null) {
            return false;
        }

        if (null == returnDays || returnDays.intValue() == 0) {
            return false;
        }

        final LocalDateTime rmaInitiationDateTime = new LocalDateTime();

        final Date deliveredDate = consignmentEntry.getConsignment().getDeliveredTime();
        final DateTime deliveredDateTime = new DateTime(deliveredDate);

        if (null == deliveredDate) {
            return false;
        }

        final int hours = Hours.hoursBetween(deliveredDateTime.toLocalDate(), rmaInitiationDateTime.toLocalDate()).getHours();

        if (hours <= returnDays.intValue() * 24) {
            return true;
        }

        return false;
    }

    public String returnIneligibleReason(final ConsignmentEntryModel consignmentEntry) {

        final OrderEntryModel orderEntry = (OrderEntryModel) consignmentEntry.getOrderEntry();
        final ProductModel product = orderEntry.getProduct();
        final Integer returnDays = returnService.getReturnDays(product);

        if (consignmentEntry.getConsignment().getStatus().equals(ConsignmentStatus.CANCELLED)
                && consignmentEntry.getConsignment().getReturnRequest() == null) {
            return "The Item has been cancelled. Hence Return or Exchange is deactivated.";
        } else if (consignmentEntry.getStatus().equals(ConsignmentEntryStatus.CANCELLED)
                && consignmentEntry.getConsignment().getReturnRequest() == null) {
            return "The Item has been cancelled. Hence Return or Exchange is deactivated.";
        }

        if (null == returnDays || returnDays.intValue() == 0) {
            return "We don't accept Returns for this item.";
        }

        final LocalDateTime rmaInitiationDateTime = new LocalDateTime();

        final Date deliveredDate = consignmentEntry.getConsignment().getDeliveredTime();
        final DateTime deliveredDateTime = new DateTime(deliveredDate);

        if (null == deliveredDate) {
            return null;
        }

        final int hours = Hours.hoursBetween(deliveredDateTime.toLocalDate(), rmaInitiationDateTime.toLocalDate()).getHours();

        if (hours >= returnDays.intValue() * 24) {
            return "The product cannot be returned since the " + returnDays + " days return period has been expired";
        }

        return null;
    }

    public boolean isReturnEligible(final AbstractOrderEntryModel orderEntry) {

        final ProductModel product = orderEntry.getProduct();
        final Integer returnDays = returnService.getReturnDays(product);
        if (null == returnDays) {
            return false;
        }

        final Set<ConsignmentEntryModel> consignmentEntries = orderEntry.getConsignmentEntries();

        final LocalDateTime rmaInitiationDateTime = new LocalDateTime();

        Date deliveredDate = null;
        DateTime deliveredDateTime = null;
      //ECD-2988 : Open RMA for Consignment status Delivered as well with Completed
        for (final ConsignmentEntryModel consignmentEntry : consignmentEntries) {
			if (!(orderEntry.equals(modelService.toModelLayer(consignmentEntry.getOrderEntry()))
					&& null != consignmentEntry.getConsignment()
					&& (ConsignmentStatus.COMPLETE.equals(consignmentEntry.getConsignment().getStatus())
							|| ConsignmentStatus.DELIVERED.equals(consignmentEntry.getConsignment().getStatus()))
					&& null == consignmentEntry.getConsignment().getReturnRequest())) {
                continue;
            }
            deliveredDate = consignmentEntry.getConsignment().getDeliveredTime();
            if (null == deliveredDateTime) {
                deliveredDateTime = new DateTime(deliveredDate);
            } else {
                final DateTime newDateTime = new DateTime(deliveredDate);
                if (newDateTime.isAfter(deliveredDateTime)) {
                    deliveredDateTime = newDateTime;
                }
            }
        }

        if (null == deliveredDate || null == deliveredDateTime) {
            return false;
        }

        final int hours = Hours.hoursBetween(deliveredDateTime.toLocalDate(), rmaInitiationDateTime.toLocalDate()).getHours();

        if (hours <= returnDays.intValue() * 24) {
            return true;
        }

        return false;
    }
}
