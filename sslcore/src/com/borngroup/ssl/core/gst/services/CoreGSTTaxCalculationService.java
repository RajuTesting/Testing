package com.borngroup.ssl.core.gst.services;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.borngroup.ssl.core.gst.dto.TaxComponentDTO;
import com.borngroup.ssl.fulfilmentprocess.model.SalesDataEntryModel;

/**
 * <p>
 * <p>
 * GSTTaxCalculationService.java : Service introduced to calculate GST tax.
 * <p>
 * Created By : anupam.srivastava@nagarro.com.  
 *
 * @author Ssl  
 */
public interface CoreGSTTaxCalculationService {
    /**
     * Method to calculate GST tax on order entries on the basis of Central location
     *
     * @param orderModel
     */
    void calculateGSTTaxForCentralloc(final OrderModel orderModel);

    /**
     * @param consignmentModel
     */
    void calculateGSTTaxOnConsignment(ConsignmentModel consignmentModel);

    /**
     * Method to get tax components on the basis of priority sequence
     *
     * @param fromLocation
     * @param toLocation
     * @param orderEntry
     *
     * @return TaxComponentDTO
     */
    TaxComponentDTO getGSTTaxComponentByPOS(PointOfServiceModel fromLocation, PointOfServiceModel toLocation,
            AbstractOrderEntryModel orderEntry);

    /**
     * Method to get apportioned delivery charges
     *
     * @param orderEntry
     * @param orderTotalWithoutDiscount
     * @return delivery charges
     */
    double getApportionableDeliveryChagres(OrderEntryModel orderEntry, double orderTotalWithoutDiscount);

    /**
     * Method to get GST tax component on item level
     *
     * @param fromLocationStateCode
     * @param toLocationStateCode
     * @param productModel
     * @param orderEntry
     * @return TaxComponentDTO
     */
    TaxComponentDTO getGSTTaxComponentByCode(String fromLocationStateCode, String toLocationStateCode, ProductModel productModel,
            AbstractOrderEntryModel orderEntry);

    /**
     * Method to Get taxable item value
     *
     * @param entry
     * @param orderModel
     * @param quantities
     * @return double
     */
    double getTaxInclusiveItemValueOnConsignmentEntry(ConsignmentEntryModel entry, OrderModel orderModel, Long quantities);

    /**
     * Method to get shipping value on consignment entry level
     *
     * @param entry
     * @param orderModel
     * @param quantities
     * @return double
     */
    double getTaxInclusiveShippingChargesOnConsEntry(ConsignmentEntryModel entry, OrderModel orderModel, Long quantities);

    /**
     * Method to get product price including shipping charges
     *
     * @param entry
     * @param orderModel
     * @param quantities
     * @return double
     */
    double getItemAndShippingTax(final ConsignmentEntryModel entry, final OrderModel orderModel, final Long quantities);

    /**
     * Method to get tax percent applied according to GST
     *
     * @param entry
     * @return value
     */
    double getAppliedTotalGSTPercent(final ConsignmentEntryModel entry);

    /**
     * @param entry
     * @param totalTaxPercent
     * @return double
     */
    double getTaxableItemValueOnOrderEntry(AbstractOrderEntryModel entry, double totalTaxPercent);

    /**
     * @param fromLocationCode
     * @param toLocationCode
     * @param orderModel
     * @return double
     */
    double getAppliedTaxOnOrderEntry(String fromLocationCode, String toLocationCode, AbstractOrderEntryModel orderModel);

    /**
     * @param orderEntryModel
     * @param taxPercent
     * @return double
     */
    double getTaxableShippingValueOnOrderEntry(AbstractOrderEntryModel orderEntryModel, double taxPercent);

    /**
     * @param entry
     * @param totalTaxPercent
     * @return entry taxable value
     */
    double getOrderEntryTaxableValueWithShipping(AbstractOrderEntryModel entry, double totalTaxPercent);

    /**
     * @param consignment
     * @return double
     */
    void setConsignmentDeliveryCost(ConsignmentModel consignment);

    /**
     * @param cartItems
     * @return list of excluded shipping sku
     */
    List<String> getExcludedShippingSKU(List<String> cartItems);

    /**
     * @param orderModel
     * @return double
     */
    double getTotalAmountExcludedShippingSku(OrderModel orderModel);

    /**
     * @param consignments
     */
    void associateGSTTaxComponents(List<ConsignmentModel> consignments);

    /**
     * @param taxPercent
     * @return SKUID
     */
    String getShippingSku(double taxPercent);

    /**
     * @param salesEntryModel
     * @param consEntry
     */
    void createSalesDataShippingOnConsignmentEntry(SalesDataEntryModel salesEntryModel, ConsignmentEntryModel consEntry);

    /**
     * @param salesEntryModel
     * @param orderEntry
     */
    void createSalesDataShippingOnOrderEntry(SalesDataEntryModel salesEntryModel, OrderEntryModel orderEntry);

    /**
     *
     * @param consignment
     * @return
     */
    Set<ConsignmentEntryModel> getTaxableItemConsignmentEntries(ConsignmentModel consignment);

    /**
     *
     * @param consignment
     * @return
     */
    Set<ConsignmentEntryModel> getNonTaxableItemConsignmentEntries(final ConsignmentModel consignment);

    /**
     *
     * @param consignmentEntries
     * @return
     */
    Pair<BigDecimal, BigDecimal> getInvoiceAndVoucherAmount(final Set<ConsignmentEntryModel> consignmentEntries);

    /**
     * Calculates the final quantity for this entry. The total quantity for this entry is all the allocated quantity of the consignment
     * entry itself and all the quantity of the internal consignment entries.
     *
     * @param consignmentEntry
     * @return the calculated quantity for this consignment entry and its internal consignment entries.
     */
    long calculateQuantity(final ConsignmentEntryModel consignmentEntry);
    /**
     * 
     * @param consignmentEntry
     * @return boolean
     */
    boolean isGSTValidConsignmentEntry(final ConsignmentEntryModel consignmentEntry);

}
