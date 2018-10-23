package com.borngroup.ssl.core.gst.services.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.storelocator.PointOfServiceDao;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.voucher.model.VoucherModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.solr.common.util.Pair;

import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.core.enums.RegionType;
import com.borngroup.ssl.core.gst.dao.GSTTaxCalculationDAO;
import com.borngroup.ssl.core.gst.dto.TaxComponentDTO;
import com.borngroup.ssl.core.gst.services.CoreGSTTaxCalculationService;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.GstTaxModel;
import com.borngroup.ssl.core.services.gst.SslGSTService;
import com.borngroup.ssl.core.util.FCCGiftUtil;
import com.borngroup.ssl.core.util.GSTUtil;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.enums.InternalConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryShippingModel;
import com.borngroup.ssl.fulfilmentprocess.model.ShippingExcludedProdModel;
import com.borngroup.tax.model.ShippingChargesModel;

/**
 * <p>
 * <p>
 * CoreGSTTaxCalculationServiceImpl.java : Service introduced to calculate GST tax.
 * <p>
 * Created By : anupam.srivastava@nagarro.com.  
 *
 * @author Ssl  
 */
public class CoreGSTTaxCalculationServiceImpl implements CoreGSTTaxCalculationService {

    /**
     *
     */
    private static final String ORDER = "Order:";

    private static final Logger LOG = Logger.getLogger(CoreGSTTaxCalculationServiceImpl.class);

    @Resource(name = "pointOfServiceDao")
    private PointOfServiceDao pointOfServiceDao;

    @Resource(name = "gstTaxCalculationDAO")
    private GSTTaxCalculationDAO gstTaxCalculationDAO;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "sslGSTService")
    private SslGSTService sslGSTService;

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Override
    public void calculateGSTTaxForCentralloc(final OrderModel orderModel) {
        if (orderModel.getDeliveryAddress() != null && orderModel.getDeliveryAddress().getRegion() != null) {
            final String fromLocationCode = sslGSTService.getCentralLocation().getAddress().getRegion().getIsocodeShort();
            final String toLocationCode = orderModel.getDeliveryAddress().getRegion().getIsocodeShort();
            final List<ItemModel> items = new ArrayList<>();
            final double totalAmountExcludedShippingSKU = getTotalAmountExcludedShippingSku(orderModel);
            LOG.info("from location :" + fromLocationCode + " and to location: " + toLocationCode);
            for (final AbstractOrderEntryModel entry : orderModel.getEntries()) {

                final double taxPercent = getAppliedTaxOnOrderEntry(fromLocationCode, toLocationCode, entry);
                entry.setApplicableTaxPercent(taxPercent); // Applied total tax according to GST on order
                                                           // entry level
                final double taxableItemValue = getTaxableItemValueOnOrderEntry(entry, taxPercent);
                entry.setTaxableItemVal(taxableItemValue);// taxable item value post promotion and discounts
                final double apportionableDelivCharges = getApportionableDeliveryChagres((OrderEntryModel) entry,
                        totalAmountExcludedShippingSKU);
                entry.setApportionedDeliveryCharges(apportionableDelivCharges); // Set apportionable
                                                                                // delivery charges on order
                                                                                // entry
                final double taxableShippingValue = getTaxableShippingValueOnOrderEntry(entry, taxPercent);
                entry.setTaxableShippingVal(taxableShippingValue); // Applied tax shipping value on order
                                                                   // entry level
                // entry.setTaxableShippingVal(new Double(taxableItemValWithShipping)); // Applied tax + shipping on
                // order entry
                items.add(entry);
            }
            modelService.saveAll(items);
        }
    }

    @Override
    public double getTotalAmountExcludedShippingSku(final OrderModel orderModel) {
        double orderTotalWithoutDiscount = 0.0;
        for (final AbstractOrderEntryModel entry : orderModel.getEntries()) {
            final List<String> skuIdList = new ArrayList<>(1);
            skuIdList.add(entry.getProduct().getCode());
            final List<String> dataList = getExcludedShippingSKU(skuIdList);
            if (CollectionUtils.isEmpty(dataList)) {
                // Entry Total
                orderTotalWithoutDiscount += entry.getTotalPrice().doubleValue();
            }
        }
        return orderTotalWithoutDiscount;
    }

    @Override
    public TaxComponentDTO getGSTTaxComponentByPOS(final PointOfServiceModel fromLocation, final PointOfServiceModel toLocation,
            final AbstractOrderEntryModel orderEntry) {
        final String fromLocationStateCode = pointOfServiceDao.getPosByName(fromLocation.getName()).getAddress().getRegion()
                .getIsocodeShort();
        final String toLocationStateCode = pointOfServiceDao.getPosByName(toLocation.getName()).getAddress().getRegion().getIsocodeShort();
        return getGSTTaxComponentByCode(fromLocationStateCode, toLocationStateCode, orderEntry.getProduct(), orderEntry);
    }

    @Override
    public TaxComponentDTO getGSTTaxComponentByCode(final String fromLocationStateCode, final String toLocationStateCode,
            final ProductModel productModel, final AbstractOrderEntryModel orderEntry) {

        LOG.info("Inside gst tax component by code for with from location: " + fromLocationStateCode + " and to location : "
                + toLocationStateCode);
        TaxComponentDTO taxComponentDTO = null;

        final Set<String> taxCategoryIdSet = new LinkedHashSet<>(3);

        populateValidTaxCategoryIds(productModel, taxCategoryIdSet);

        if (taxCategoryIdSet.isEmpty()) {
            return taxComponentDTO;
        }
        final double productPrice = getProductEffectivePrice(orderEntry);

        List<GstTaxModel> taxmodels = null;

        for (final String taxCategoryId : taxCategoryIdSet) {
            taxmodels = gstTaxCalculationDAO.findTaxByTaxCategoryId(taxCategoryId, Double.valueOf(productPrice));
            LOG.info("Calculating tax based on Category Id:" + taxCategoryId + " for Order: " + orderEntry.getOrder().getCode()
                    + " for Product: " + productModel.getCode());

            if (CollectionUtils.isNotEmpty(taxmodels)) {
                break;
            }
        }

        if (CollectionUtils.isNotEmpty(taxmodels)) {
            final Map<String, GstTaxModel> recentlyUpdatedTaxModelMap = new LinkedHashMap<>(taxmodels.size());
            for (final GstTaxModel taxModel : taxmodels) {
                if (null != taxModel) {
                    final String key = taxModel.getTaxType();
                    if (recentlyUpdatedTaxModelMap.get(key) != null) {
                        final GstTaxModel oldmodel = recentlyUpdatedTaxModelMap.get(key);
                        if (taxModel.getModifiedtime().compareTo(oldmodel.getModifiedtime()) > 0) {
                            LOG.info("oldmodel with identifer is " + oldmodel.getCode() + " and date is" + oldmodel.getModifiedtime()
                                    + " other tax row " + taxModel.getCode() + "--> and time is" + taxModel.getModifiedtime());
                            recentlyUpdatedTaxModelMap.put(key, taxModel);
                        }
                    } else {
                        recentlyUpdatedTaxModelMap.put(key, taxModel);
                    }
                }
            }

            taxComponentDTO = this.filterTaxRowsOnLocation(recentlyUpdatedTaxModelMap, fromLocationStateCode, toLocationStateCode);
        }
        return taxComponentDTO;
    }

    /**
     * @param productModel
     * @param taxCategoryIdSet
     */
    private void populateValidTaxCategoryIds(final ProductModel productModel, final Set<String> taxCategoryIdSet) {
        ProductModel temp = productModel;
        while (temp instanceof VariantProductModel) {
            final String taxCategoryCode = ((VariantProductModel) temp).getTaxCategoryCode();
            temp = ((VariantProductModel) temp).getBaseProduct();
            if (StringUtils.isNotBlank(taxCategoryCode)) {
                taxCategoryIdSet.add(taxCategoryCode.trim());
            }
        }
        if (temp instanceof ApparelProductModel) {
            final String taxCategoryCode = temp.getTaxCategoryCode();
            if (StringUtils.isNotBlank(taxCategoryCode)) {
                taxCategoryIdSet.add(taxCategoryCode.trim());
            }
        }
    }

    @Override
    public double getApportionableDeliveryChagres(final OrderEntryModel orderEntry, final double orderTotalWithoutItemDiscount) {
        double apportionedAmount = 0.0;
        final OrderModel orderMod = orderEntry.getOrder();

        final double orderEntryBasePrice = orderEntry.getBasePrice().doubleValue();
        final long orderEntryQuantity = orderEntry.getQuantity().longValue();
        final double deliveryCost = orderMod.getDeliveryCost().doubleValue();
        final double itemDiscount = ((orderEntryBasePrice * orderEntryQuantity) - orderEntry.getTotalPrice().doubleValue())
                / orderEntry.getQuantity().longValue();

        if (orderMod.getDeliveryCost() != null && deliveryCost > 0) {
            final List<String> entrySku = new ArrayList<>(1);
            entrySku.add(orderEntry.getProduct().getCode());
            final List<String> isItemExluded = getExcludedShippingSKU(entrySku);
            if (CollectionUtils.isEmpty(isItemExluded)) {
                apportionedAmount = ((orderEntryBasePrice - itemDiscount) * orderEntryQuantity * deliveryCost)
                        / (orderTotalWithoutItemDiscount);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Apportionable delivery charges for order " + orderMod.getCode()
                            + " and aportioned amount using [orderentrytotalprice [" + orderEntry.getTotalPrice().doubleValue()
                            + "] * deliverycost [" + deliveryCost + "] / allorderentrytotal [" + orderTotalWithoutItemDiscount + "]] "
                            + apportionedAmount);
                }
            } else {
                LOG.info("Order :" + orderMod.getCode() + ": Found excluded product " + orderEntry.getProduct().getCode());
            }
        }
        return new BigDecimal(String.valueOf(apportionedAmount)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double getAppliedTotalGSTPercent(final ConsignmentEntryModel entry) {
        LOG.info("Inside get applied GST tax percent");
        final Map<GstTaxTypes, String> appliedComponent = new LinkedHashMap<>();
        double taxableItemValue = 0.0;
        final String shipToRegion = getShippingToRegion(entry.getConsignment());
        final String shipFromRegion = getShippingFromRegion(entry.getConsignment());
        if (StringUtils.isEmpty(shipFromRegion) || StringUtils.isEmpty(shipToRegion)) {
            LOG.info("Unable to find ShipFromRegion or ShipToRegion for consignment : " + entry.getConsignment().getCode());
            LOG.info("considering consignment entry : " + entry.getPk() + ": as BOS item");
            entry.setTaxCode(StringUtils.EMPTY);
            entry.setTaxComponents(appliedComponent);
            return taxableItemValue;
        }
        final TaxComponentDTO taxComponentDTO = getGSTTaxComponentByCode(shipFromRegion, shipToRegion, entry.getOrderEntry().getProduct(),
                entry.getOrderEntry());
        final List<String> taxCodeList = new ArrayList<>(3);
        if (taxComponentDTO != null) {
            final List<Pair<GstTaxTypes, GstTaxModel>> taxComponents = taxComponentDTO.getTaxComponent();
            for (final Pair<GstTaxTypes, GstTaxModel> pair : taxComponents) {
                if (pair.getValue() != null) {
                    if (pair.getValue().getCode() != null) {
                        taxCodeList.add(pair.getValue().getTaxCode() + "_" + pair.getValue().getTaxValue());
                    }
                    taxableItemValue += pair.getValue().getTaxValue();
                    appliedComponent.put(pair.getKey(), String.valueOf(pair.getValue().getTaxValue()));
                }
            }
        }
        // Set indicator on order entry
        entry.setTaxCode(StringUtils.join(taxCodeList, ','));
        entry.setTaxComponents(appliedComponent);
        entry.setAppliedGSTTaxPercent(taxableItemValue);
        LOG.info("Total GST tax value is applied on " + entry.getOrderEntry().getOrder().getCode() + " value " + taxableItemValue);
        return taxableItemValue;
    }

    @Override
    public double getAppliedTaxOnOrderEntry(final String fromLocationCode, final String toLocationCode,
            final AbstractOrderEntryModel orderModel) {
        final Map<GstTaxTypes, String> appliedComponent = new LinkedHashMap<>();
        double taxableItemValue = 0.0;
        final List<String> taxCodeList = new ArrayList<>(3);
        final TaxComponentDTO taxComponentDTO = getGSTTaxComponentByCode(fromLocationCode, toLocationCode, orderModel.getProduct(),
                orderModel);

        if (taxComponentDTO != null) {
            final List<Pair<GstTaxTypes, GstTaxModel>> taxComponents = taxComponentDTO.getTaxComponent();
            for (final Pair<GstTaxTypes, GstTaxModel> pair : taxComponents) {
                if (pair.getValue().getCode() != null) {
                    taxCodeList.add(pair.getValue().getTaxCode() + "_" + pair.getValue().getTaxValue());
                }
                taxableItemValue += pair.getValue().getTaxValue();
                appliedComponent.put(pair.getKey(), String.valueOf(pair.getValue().getTaxValue()));
            }
        }
        orderModel.setTaxCode(StringUtils.join(taxCodeList, ","));
        orderModel.setTaxComponents(appliedComponent);
        return taxableItemValue;
    }

    @Override
    public double getTaxableItemValueOnOrderEntry(final AbstractOrderEntryModel entry, final double totalTaxPercent) {
        double taxableItemValue;
        double itemDiscount = 0.0;
        double pieceDiscount = 0.0;
        final long qty = entry.getQuantity().longValue();
        if (entry.getPieceDiscount() != null) {
            pieceDiscount = entry.getPieceDiscount().doubleValue();
        }
        if (entry.getItemDiscount() != null) {
            itemDiscount = entry.getItemDiscount().doubleValue();
            if (LOG.isDebugEnabled()) {
                LOG.debug(ORDER + entry.getOrder().getCode() + " Item level discount " + entry.getItemDiscount().doubleValue());
            }
        }
        final double promotionAnditemPromotionAmount = (pieceDiscount * qty) + (itemDiscount * qty);
        LOG.info("Total amount after item promotion and order promotion " + promotionAnditemPromotionAmount);
        final double totalPrice = entry.getBasePrice().doubleValue();

        final double consignmentPriceWithoutDiscount = BigDecimal.valueOf(totalPrice).multiply(BigDecimal.valueOf(qty)).doubleValue();
        double consignmentPriceAfterDiscount = consignmentPriceWithoutDiscount;
        if (promotionAnditemPromotionAmount > 0) {
            consignmentPriceAfterDiscount = BigDecimal.valueOf(consignmentPriceWithoutDiscount)
                    .subtract(BigDecimal.valueOf(promotionAnditemPromotionAmount)).doubleValue();
        }

        taxableItemValue = (consignmentPriceAfterDiscount * 100) / (100 + totalTaxPercent);
        if (LOG.isDebugEnabled()) {
            LOG.debug(ORDER + entry.getOrder().getCode() + "Taxable item value is " + taxableItemValue);
        }
        return new BigDecimal(String.valueOf(taxableItemValue)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double getTaxInclusiveItemValueOnConsignmentEntry(final ConsignmentEntryModel entry, final OrderModel orderModel,
            final Long quantities) {
        final long quantitiy = quantities.longValue();
        double pieceDiscount = 0.0;
        double itemDiscount = 0.0;
        if (entry.getOrderEntry().getPieceDiscount() != null) {
            pieceDiscount = entry.getOrderEntry().getPieceDiscount().doubleValue();
        }
        if (entry.getOrderEntry().getItemDiscount() != null) {
            itemDiscount = entry.getOrderEntry().getItemDiscount().doubleValue();
            if (LOG.isDebugEnabled()) {
                LOG.debug(ORDER + orderModel.getCode() + " Item level discount " + entry.getOrderEntry().getItemDiscount().doubleValue());
            }
        }
        final double itemAndPieceDiscount = (pieceDiscount * quantitiy) + (itemDiscount * quantitiy);
        LOG.info("Total amount after item promotion and order promotion " + itemAndPieceDiscount);
        final double totalPrice = entry.getOrderEntry().getBasePrice().doubleValue();
        final double itemPriceWithoutDiscount = BigDecimal.valueOf(totalPrice).multiply(BigDecimal.valueOf(quantitiy)).doubleValue();
        double consignmentPriceAfterDiscount = itemPriceWithoutDiscount;
        if (itemAndPieceDiscount > 0) {
            consignmentPriceAfterDiscount = BigDecimal.valueOf(itemPriceWithoutDiscount).subtract(BigDecimal.valueOf(itemAndPieceDiscount))
                    .doubleValue();
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug(ORDER + orderModel.getCode() + "TaxInclusive item value is " + consignmentPriceAfterDiscount);
        }
        return new BigDecimal(String.valueOf(consignmentPriceAfterDiscount)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double getTaxInclusiveShippingChargesOnConsEntry(final ConsignmentEntryModel entry, final OrderModel orderModel,
            final Long quantities) {
        if (orderModel.getDeliveryCost() != null && orderModel.getDeliveryCost().doubleValue() > 0) {
            final Double apportionedDeliveryCharges = entry.getOrderEntry().getApportionedDeliveryCharges();
            if (apportionedDeliveryCharges != null) {
                final double approtionedDelivCharges = apportionedDeliveryCharges.doubleValue()
                        / entry.getOrderEntry().getQuantity().doubleValue();
                final double consignmentEntryDelivCharges = approtionedDelivCharges * quantities.doubleValue();
                return new BigDecimal(String.valueOf(consignmentEntryDelivCharges)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return 0.0;
    }

    @Override
    public double getTaxableShippingValueOnOrderEntry(final AbstractOrderEntryModel orderEntryModel, final double taxPercent) {
        if (orderEntryModel.getOrder().getDeliveryCost() != null && orderEntryModel.getOrder().getDeliveryCost().doubleValue() > 0
                && orderEntryModel.getApportionedDeliveryCharges() != null) {
            final double consignmentEntryDelivCharges = orderEntryModel.getApportionedDeliveryCharges().doubleValue();
            final double delivAfterTax = (consignmentEntryDelivCharges * 100) / (100 + taxPercent);
            return new BigDecimal(String.valueOf(delivAfterTax)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return 0.0;
    }

    @Override
    public double getItemAndShippingTax(final ConsignmentEntryModel entry, final OrderModel orderModel, final Long quantities) {
        double itemWithTax;
        double shippingChargesWithTax;
        double totalExcludingTax = 0.0;

        if (entry.getTaxInclusiveItemValue() != null && entry.getTaxInclusiveItemValue().doubleValue() > 0) {
            itemWithTax = entry.getTaxInclusiveItemValue().doubleValue();
        } else {
            itemWithTax = getTaxInclusiveItemValueOnConsignmentEntry(entry, orderModel, quantities);
        }
        if (entry.getTaxInclusiveShippingValue() != null && entry.getTaxInclusiveShippingValue().doubleValue() > 0) {
            shippingChargesWithTax = entry.getTaxInclusiveShippingValue().doubleValue();
        } else {
            shippingChargesWithTax = getTaxInclusiveShippingChargesOnConsEntry(entry, orderModel, quantities);
        }
        double totalTaxPercent;
        if (entry.getAppliedGSTTaxPercent() != null && entry.getAppliedGSTTaxPercent().doubleValue() > 0) {
            totalTaxPercent = entry.getAppliedGSTTaxPercent().doubleValue();
        } else {
            totalTaxPercent = getAppliedTotalGSTPercent(entry);
        }
        itemWithTax += shippingChargesWithTax;
        if (totalTaxPercent > 0) {
            totalExcludingTax = (itemWithTax * 100) / (100 + totalTaxPercent);
        }

        return itemWithTax - totalExcludingTax;
    }

    @Override
    public double getOrderEntryTaxableValueWithShipping(final AbstractOrderEntryModel entry, final double totalTaxPercent) {
        double itemWithTax;
        double shippingChargesWithTax;
        double totalExcludingTax = 0.0;
        if (entry.getTaxableItemVal() != null && entry.getTaxableItemVal().doubleValue() > 0) {
            itemWithTax = entry.getTaxableItemVal().doubleValue();
        } else {
            itemWithTax = getTaxableItemValueOnOrderEntry(entry, totalTaxPercent);
        }
        if (entry.getTaxableShippingVal() != null && entry.getTaxableShippingVal().doubleValue() > 0) {
            shippingChargesWithTax = entry.getTaxableShippingVal().doubleValue();
        } else {
            shippingChargesWithTax = getTaxableShippingValueOnOrderEntry(entry, totalTaxPercent);
        }
        itemWithTax += shippingChargesWithTax;
        if (totalTaxPercent > 0) {
            totalExcludingTax = (itemWithTax * 100) / (100 + totalTaxPercent);
        }

        final double itemAndShippingTax = itemWithTax - totalExcludingTax;

        return new BigDecimal(String.valueOf(itemAndShippingTax)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String getShippingFromRegion(final ConsignmentModel consignment) {
        if (StringUtils.isNotEmpty(consignment.getWarehouse().getCode())) {
            final String regionCode = consignment.getWarehouse().getRegionCode();
            final RegionModel regionModel = sslGSTService.getRegionModel(regionCode);
            if (null != regionModel) {
                return regionModel.getIsocodeShort();
            }

        }
        return StringUtils.EMPTY;
    }

    private String getShippingToRegion(final ConsignmentModel consignment) {
        if (consignment.getDeliveryAddress() != null && consignment.getDeliveryAddress().getRegion() != null
                && StringUtils.isNotEmpty(consignment.getDeliveryAddress().getRegion().getIsocodeShort())) {
            return consignment.getDeliveryAddress().getRegion().getIsocodeShort();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void calculateGSTTaxOnConsignment(final ConsignmentModel consignment) {
        if (consignment == null || consignment.getConsignmentEntries().isEmpty()) {
            return;
        }
        final List<ItemModel> items = new ArrayList<>();
        for (final ConsignmentEntryModel consignmentEntry : consignment.getConsignmentEntries()) {
            if (consignmentEntry.getOrderEntry().getProduct() == null) {
                continue;
            }
            long quantity = consignmentEntry.getQuantity().longValue();
            if (consignment.getReturnRequest() != null) {
                final Set<ReturnEntryModel> returnEntry = consignment.getReturnRequest().getReturnEntries().stream()
                        .filter(re -> re.getOrderEntry().getProduct().equals(consignmentEntry.getOrderEntry().getProduct()))
                        .collect(Collectors.toSet());
                quantity = returnEntry.stream().findFirst().get().getFinalReturnQuantity();
            }
            final OrderModel orderModel = (OrderModel) consignmentEntry.getOrderEntry().getOrder();

            final double taxInclusiveShippingVal = getTaxInclusiveShippingChargesOnConsEntry(consignmentEntry, orderModel, quantity);
            consignmentEntry.setTaxInclusiveShippingValue(taxInclusiveShippingVal);

            final double taxInclusiveItemVal = getTaxInclusiveItemValueOnConsignmentEntry(consignmentEntry, orderModel, quantity);

            consignmentEntry.setTaxInclusiveItemValue(taxInclusiveItemVal);

            final double totalTax = getItemAndShippingTax(consignmentEntry, orderModel, quantity);

            consignmentEntry.setTotalTaxIncludedinPrice(totalTax);
            if (consignment.getReturnRequest() == null) {
                getAppliedTotalGSTPercent(consignmentEntry);
            }
            items.add(consignmentEntry);
        }
        modelService.saveAll(items);
    }

    @Override
    public void associateGSTTaxComponents(final List<ConsignmentModel> consignments) {
        LOG.info("Inside associate gst tax components");
        final List<ItemModel> items = new ArrayList<>();
        for (final ConsignmentModel consignment : consignments) {
            for (final ConsignmentEntryModel entry : consignment.getConsignmentEntries()) {
                getAppliedTotalGSTPercent(entry);
                items.add(entry);
            }
        }
        modelService.saveAll(items);
    }

    @Override
    public void setConsignmentDeliveryCost(final ConsignmentModel consignment) {

        double consignmentDeliveryCost = 0.0d;
        double bosDeliveryCost = 0.0d;
        for (final ConsignmentEntryModel cem : consignment.getConsignmentEntries()) {
            final long allocatedQuantity = GSTUtil.getAllocatedQuantityForConsignmentEntry(cem);
            if (GSTUtil.isZeroTax(cem.getTaxComponents()) > 0) {
                consignmentDeliveryCost += (cem.getTaxInclusiveShippingValue() / cem.getQuantity()) * allocatedQuantity;
            } else {
                bosDeliveryCost += (cem.getTaxInclusiveShippingValue() / cem.getQuantity()) * allocatedQuantity;
            }
        }
        consignment.setCnmtDeliveryCost(consignmentDeliveryCost);
        consignment.setBosDeliveryCost(bosDeliveryCost);
    }

    @Override
    public List<String> getExcludedShippingSKU(final List<String> cartItems) {
        final List<String> excludedList = new ArrayList<>();
        final List<ShippingExcludedProdModel> shippingModel = gstTaxCalculationDAO.getListofExcludedSku(cartItems);
        for (final ShippingExcludedProdModel shippingExcludedProdModel : shippingModel) {
            excludedList.add(shippingExcludedProdModel.getProductCode());
        }
        return excludedList;
    }

    @Override
    public String getShippingSku(final double taxPercent) {
        final ShippingChargesModel shippingModel = gstTaxCalculationDAO.getShippingSKUOnPercent(taxPercent);
        if (shippingModel != null) {
            return shippingModel.getSkuID();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void createSalesDataShippingOnConsignmentEntry(final SalesDataEntryModel salesEntryModel, final ConsignmentEntryModel consEntry) {
        if (salesEntryModel != null && consEntry.getAppliedGSTTaxPercent() != null && consEntry.getAppliedGSTTaxPercent().doubleValue() > 0) {
            LOG.info("Shipping charges calculation on consignment entry for " + consEntry.getOrderEntry().getOrder().getCode());
            final Map<GstTaxTypes, String> taxComponents = consEntry.getTaxComponents();
            final List<SalesDataEntryShippingModel> shippingList = new ArrayList<>();
            for (final Entry<GstTaxTypes, String> taxEntry : taxComponents.entrySet()) {
                final SalesDataEntryShippingModel salesDataEntryShippingModel = modelService.create(SalesDataEntryShippingModel.class);
                final double taxValue = Double.parseDouble(taxEntry.getValue());
                final String shippingSku = getShippingSku(taxValue);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Shipping sku found for tavalue " + taxValue + " and SKU id " + shippingSku + " and for order"
                            + consEntry.getOrderEntry().getOrder().getCode());
                }
                if (StringUtils.isNotEmpty(shippingSku)) {
                    salesDataEntryShippingModel.setProductCode(salesEntryModel.getProductCode());
                    salesDataEntryShippingModel.setShippingSKU(shippingSku != null ? shippingSku : StringUtils.EMPTY);

                    double shippingValue;
                    if (consEntry.getConsignment().getReturnRequest() == null) {
                        shippingValue = consEntry.getTaxInclusiveShippingValue() != null ? consEntry.getTaxInclusiveShippingValue()
                                .doubleValue() : 0.0;
                    } else {
                        // Return flow
                        shippingValue = salesEntryModel.getShippingCost();
                    }
                    salesDataEntryShippingModel.setShippingAmount(shippingValue);
                    salesDataEntryShippingModel.setTaxPercent(taxValue);
                    final double taxablevalue = (shippingValue * 100) / (100 + taxValue);
                    salesDataEntryShippingModel.setTaxableValue(taxablevalue);
                    salesDataEntryShippingModel.setTaxValue((taxablevalue * taxValue) / 100);
                    salesDataEntryShippingModel.setTaxType(taxEntry.getKey());
                    shippingList.add(salesDataEntryShippingModel);
                }
            }
            if (CollectionUtils.isNotEmpty(shippingList)) {
                salesEntryModel.setSalesDataEntryShipping(shippingList);
            }
        }
    }

    @Override
    public void createSalesDataShippingOnOrderEntry(final SalesDataEntryModel salesEntryModel, final OrderEntryModel orderEntry) {
        if (salesEntryModel != null && orderEntry.getApplicableTaxPercent() != null && salesEntryModel.getGstReceiptShippingAmount() > 0) {
            LOG.info("Shipping charges calculation on order entry for " + orderEntry.getOrder().getCode());
            final Map<GstTaxTypes, String> taxComponents = orderEntry.getTaxComponents();
            final List<SalesDataEntryShippingModel> shippingList = new ArrayList<>();
            for (final Entry<GstTaxTypes, String> taxEntry : taxComponents.entrySet()) {
                final SalesDataEntryShippingModel salesDataEntryShippingModel = modelService.create(SalesDataEntryShippingModel.class);
                final double taxValue = Double.parseDouble(taxEntry.getValue());
                final String shippingSku = getShippingSku(taxValue);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Shipping sku found for tavalue " + taxValue + " and SKU id " + shippingSku + " and for order"
                            + orderEntry.getOrder().getCode());
                }
                if (StringUtils.isNotEmpty(shippingSku)) {
                    salesDataEntryShippingModel.setProductCode(salesEntryModel.getProductCode());
                    salesDataEntryShippingModel.setShippingSKU(shippingSku);
                    final double shippingValue = salesEntryModel.getGstReceiptShippingAmount() * salesEntryModel.getQuantity();
                    salesDataEntryShippingModel.setShippingAmount(shippingValue);
                    final double taxablevalue = (shippingValue * 100) / (100 + taxValue);
                    salesDataEntryShippingModel.setTaxableValue(taxablevalue);
                    salesDataEntryShippingModel.setTaxValue((taxablevalue * taxValue) / 100);
                    salesDataEntryShippingModel.setTaxPercent(taxValue);
                    salesDataEntryShippingModel.setTaxType(taxEntry.getKey());
                    shippingList.add(salesDataEntryShippingModel);
                }
            }
            if (CollectionUtils.isNotEmpty(shippingList)) {
                salesEntryModel.setSalesDataEntryShipping(shippingList);
            }
        }
    }

    private double getProductEffectivePrice(final AbstractOrderEntryModel orderEntry) {
        final double itemDiscount = null != orderEntry.getItemDiscount() ? orderEntry.getItemDiscount().doubleValue() : 0.0;
        final double pieceDiscount = null != orderEntry.getPieceDiscount() ? orderEntry.getPieceDiscount().doubleValue() : 0.0;
        return Math.ceil(orderEntry.getBasePrice().doubleValue() - itemDiscount - pieceDiscount);
    }

    @Override
    public Set<ConsignmentEntryModel> getTaxableItemConsignmentEntries(final ConsignmentModel consignment) {
        final Set<ConsignmentEntryModel> taxableConsignmentEntriesList = new HashSet<>(consignment.getConsignmentEntries().size());
        for (final ConsignmentEntryModel cem : consignment.getConsignmentEntries()) {
            if (GSTUtil.isZeroTax(cem.getTaxComponents()) > 0
                    || FCCGiftUtil.isFCCProduct(cem.getOrderEntry().getProduct())) {
                taxableConsignmentEntriesList.add(cem);
            }
        }
        return taxableConsignmentEntriesList;
    }

    @Override
    public Set<ConsignmentEntryModel> getNonTaxableItemConsignmentEntries(final ConsignmentModel consignment) {
        final Set<ConsignmentEntryModel> nonTaxableConsignmentEntriesList = new HashSet<>(consignment.getConsignmentEntries().size());
        for (final ConsignmentEntryModel cem : consignment.getConsignmentEntries()) {
            if ((Double.compare(GSTUtil.isZeroTax(cem.getTaxComponents()), 0) == 0)
                    && !FCCGiftUtil.isFCCProduct(cem.getOrderEntry().getProduct())) {
                nonTaxableConsignmentEntriesList.add(cem);
            }
        }
        return nonTaxableConsignmentEntriesList;
    }

    @SuppressWarnings("boxing")
    @Override
    public de.hybris.platform.promotions.util.Pair<BigDecimal, BigDecimal> getInvoiceAndVoucherAmount(
            final Set<ConsignmentEntryModel> consignmentEntries) {
        BigDecimal invoiceAmount = BigDecimal.ZERO;
        BigDecimal apportionedDiscount = BigDecimal.ZERO;
        final ConsignmentModel consignment = consignmentEntries.iterator().next().getConsignment();
        double shippingFee = 0d;
        for (final ConsignmentEntryModel consignmentEntry : consignmentEntries) {
            final AbstractOrderEntryModel orderEntry = consignmentEntry.getOrderEntry();
            final double basePrice = orderEntry.getBasePrice() != null ? orderEntry.getBasePrice().doubleValue() : 0.0D;
            final double pieceDiscount = orderEntry.getPieceDiscount() != null ? orderEntry.getPieceDiscount().doubleValue() : 0.0D;
            final double itemDiscount = orderEntry.getItemDiscount() != null ? orderEntry.getItemDiscount().doubleValue() : 0.0D;
            final long quantity = calculateQuantity(consignmentEntry);
            final double entryValue = (basePrice - pieceDiscount - itemDiscount) * quantity;
            if (entryValue > 0) {
                invoiceAmount = invoiceAmount.add(BigDecimal.valueOf(entryValue));
            }

            if (GSTUtil.isGSTSwitchOn() && orderEntry.getApportionedDeliveryCharges() != null) {
                shippingFee += (orderEntry.getApportionedDeliveryCharges().doubleValue() / orderEntry.getQuantity()) * quantity;
            }

        }
        invoiceAmount = invoiceAmount.add(BigDecimal.valueOf(shippingFee));
        // Apportion voucher amounts and reduce invoice amount accordingly.
        double voucherDiscount = 0.0D;
        // Iterate all Global Discounts
        for (final DiscountValue val : consignment.getOrder().getGlobalDiscountValues()) {
            // Iterate in Discount Model to get the Voucher discounts
            for (final DiscountModel disc : consignment.getOrder().getDiscounts()) {
                if (disc.getCode().equals(val.getCode()) && disc instanceof VoucherModel) {
                    voucherDiscount += val.getValue();
                }
            }
        }
        if (voucherDiscount > 0) {
            // apportion this amount.
            double orderTotal = consignment.getOrder().getTotalPrice().doubleValue() + voucherDiscount
                    - consignment.getOrder().getDeliveryCost().doubleValue();

            if (GSTUtil.isGSTSwitchOn()) {
                orderTotal += consignment.getOrder().getDeliveryCost().doubleValue();
            }

            LOG.info("orderPrice : " + consignment.getOrder().getTotalPrice().doubleValue() + " VoucherDiscount : " + voucherDiscount
                    + "OrderTotalPrice : " + orderTotal);
            apportionedDiscount = BigDecimal.valueOf(invoiceAmount.doubleValue() * voucherDiscount / orderTotal).setScale(2,
                    RoundingMode.HALF_UP);
            invoiceAmount = invoiceAmount.subtract(apportionedDiscount).setScale(2, RoundingMode.HALF_UP);
            LOG.info("Apportioned Discount : " + apportionedDiscount);
            LOG.info("Invoice Amount : " + invoiceAmount);
        }
        return new de.hybris.platform.promotions.util.Pair<>(invoiceAmount, apportionedDiscount);
    }

    public long calculateQuantity(final ConsignmentEntryModel consignmentEntry) {
        if (ConsignmentEntryStatus.CANCELLED.equals(consignmentEntry.getStatus())) {
            return 0L;
        }
        long quantity = consignmentEntry.getAllocatedQuantity().longValue();
        if (!ConsignmentEntryStatus.COMPLETE.equals(consignmentEntry.getStatus())) {
            final Collection<InternalConsignmentEntryModel> internalEntries = consignmentEntry.getInternalEntries();
            for (final InternalConsignmentEntryModel internalEntry : internalEntries) {
                if (!InternalConsignmentEntryStatus.CANCELLED.equals(internalEntry.getStatus())
                        && !InternalConsignmentEntryStatus.COMPLETE.equals(internalEntry.getStatus())) {
                    quantity += internalEntry.getQuantity().longValue();
                }
            }
        }
        return quantity;
    }

    @Override
    public boolean isGSTValidConsignmentEntry(final ConsignmentEntryModel consignmentEntry) {

        final List<String> dataList = getExcludedShippingSKU(Arrays.asList(consignmentEntry.getOrderEntry().getProduct().getCode()));
        // Non Taxable Products
        if (!dataList.isEmpty()) {
            return NumberUtils.compare(GSTUtil.isZeroTax(consignmentEntry.getTaxComponents()), 0) == 0 ? true : false;
        }

        if (consignmentEntry.getConsignment().getDeliveryAddress() == null
                || consignmentEntry.getConsignment().getDeliveryAddress().getRegion() == null) {
            return false;
        }

        final String productTaxCategory = consignmentEntry.getOrderEntry().getProduct().getTaxCategoryCode();
        if (StringUtils.isBlank(productTaxCategory) || !StringUtils.isNumeric(productTaxCategory.trim())) {
            return false;
        }

        final ConsignmentModel consignment = consignmentEntry.getConsignment();
        final String posRegion = consignment.getWarehouse().getRegionCode();
        final String deliveryRegion = consignment.getDeliveryAddress().getRegion().getIsocode();

        LOG.info("Found " + consignmentEntry.getTaxComponents() + " tax components for Consignment Entry : " + consignmentEntry.getPk()
                + " For From location : " + posRegion + " , To Location: " + deliveryRegion);

        if (posRegion != null && deliveryRegion != null) {
            // 2 tax components should exist
            if (posRegion.equals(deliveryRegion)) {
                return consignmentEntry.getTaxComponents().size() == 2;
            } else {
                return consignmentEntry.getTaxComponents().size() == 1;
            }
        }
        return false;
    }

    private TaxComponentDTO filterTaxRowsOnLocation(final Map<String, GstTaxModel> taxComponents, final String billingLocation,
            final String shippingLocation) {

        final TaxComponentDTO taxComponentDTO = new TaxComponentDTO();
        if (billingLocation == null || shippingLocation == null) {
            return taxComponentDTO;
        }

        Map<GstTaxTypes, GstTaxModel> filteredComponents;
        if (billingLocation.equals(shippingLocation)) {

            RegionModel region = new RegionModel();
            region.setIsocodeShort(billingLocation);
            try {
                region = flexibleSearchService.getModelByExample(region);
            } catch (final ModelNotFoundException mn) {
                LOG.error("SEVERE : No region found for IsocodeShort: " + billingLocation);
                return taxComponentDTO;
            }
            if (RegionType.UT.equals(region.getRegionType())) {
                filteredComponents = taxComponents.entrySet().stream()
                        .filter(tax -> ("U").equalsIgnoreCase(tax.getKey()) || ("C").equalsIgnoreCase(tax.getKey()))
                        .collect(Collectors.toMap(tax -> tax.getValue().getTaxCode(), Map.Entry::getValue));
            } else {
                filteredComponents = taxComponents.entrySet().stream()
                        .filter(tax -> ("S").equalsIgnoreCase(tax.getKey()) || ("C").equalsIgnoreCase(tax.getKey()))
                        .collect(Collectors.toMap(tax -> tax.getValue().getTaxCode(), Map.Entry::getValue));
            }

        } else {
            filteredComponents = taxComponents.entrySet().stream().filter(tax -> ("I").equalsIgnoreCase(tax.getKey()))
                    .collect(Collectors.toMap(tax -> tax.getValue().getTaxCode(), Map.Entry::getValue));
        }
        LOG.info("Filtered tax Components for Billing Location: " + billingLocation + " , to Shipping Location: " + shippingLocation
                + " are: " + filteredComponents);

        final List<Pair<GstTaxTypes, GstTaxModel>> taxComponent = new ArrayList<>(3);
        for (final Entry<GstTaxTypes, GstTaxModel> taxModel : filteredComponents.entrySet()) {
            final Pair<GstTaxTypes, GstTaxModel> taxEntry = new Pair<>(taxModel.getKey(), taxModel.getValue());
            taxComponent.add(taxEntry);
            LOG.info("GST tax component found " + taxModel.getKey() + " - " + taxModel.getValue() + " from location " + billingLocation
                    + " and tolocation " + shippingLocation);
        }
        taxComponentDTO.setTaxComponent(taxComponent);
        return taxComponentDTO;
    }
}
