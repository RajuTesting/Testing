package com.borngroup.ssl.core.util;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.util.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.common.util.Pair;
import org.joda.time.DateTime;

import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.fulfilmentprocess.enums.ConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.enums.InternalConsignmentEntryStatus;
import com.borngroup.ssl.fulfilmentprocess.model.InternalConsignmentEntryModel;

/**
 * <p>
 * <p>
 * GSTUtil.java : GST Utility. 
 * <p>
 * Created By : anupam.srivastava@nagarro.com  
 *
 * @author Ssl  
 */
public class GSTUtil {

    public static final String EXCLUDED_SKU_QUERY = "SELECT {c:pk}, {c:productcode} FROM {ShippingExcludedProd as c} where {c:active}='1' and {c:productcode} IN (?skuLists)";
    public static final String SHIPPING_SKU_QUERY = "SELECT {c:pk} FROM {ShippingCharges as c} where {c:taxPercent} = ?taxPercent";
    private static final Logger LOG = Logger.getLogger(GSTUtil.class);

    public static final String ORDER_START_DATE = "{order_start_date}";

    public static final String ORDER_END_DATE = "{order_end_date}";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String COD_TENDER = "37";

    public static final String OPEN_ORDER_QUERY = "Select * from ({{SELECT q1.PK FROM (({{Select {o:pk} as pk, SUM({oe:quantity} - {oe:cancelledQty}) as bookedQuantity "
            + " from {Order as o left join OrderEntry as oe on {oe:order} = {o:pk} left join OrderStatus as os on {os:pk} = {o:status}} "
            + " where {o:creationTime} >= TO_DATE('"
            + ORDER_START_DATE
            + "', 'YYYY-MM-DD HH24:MI:SS') and {o:creationTime} <= TO_DATE('"
            + ORDER_END_DATE
            + "', 'YYYY-MM-DD HH24:MI:SS') "
            + " and {o:versionId} is null and ({os:code} <> 'CANCELLED' or {os:code} is null) group by {o:pk}}}) q1 "
            + " left join ({{Select {o:pk} as pk, SUM(NVL({ce:shippedQuantity},0) + NVL({ice:quantity},0)) as shippedQuantity "
            + " from {Order as o left join Consignment as c on {o:pk} = {c:order} left join ConsignmentEntry as ce on {ce:consignment} = {c:pk} "
            + " left join InternalConsignmentEntry as ice on {ice:parentConsignmentEntry} = {ce:pk} left join ConsignmentStatus as cs on {cs:pk} = {c:status} "
            + " left join ConsignmentEntryStatus as ces on {ces:pk} = {ce:status} left join InternalConsignmentEntryStatus as ices on {ices:pk} = {ice:status}} "
            + " where {o:creationTime} >= TO_DATE('"
            + ORDER_START_DATE
            + " ', 'YYYY-MM-DD HH24:MI:SS') and "
            + " {o:creationTime} <= TO_DATE('"
            + ORDER_END_DATE
            + "', 'YYYY-MM-DD HH24:MI:SS') and {o:versionId} is null and {c:invoiceTime} is not null and {c:returnrequest} is null and "
            + " ( {cs:code} <> 'CANCELLED' AND {ces:code} <> 'CANCELLED' AND {ices:code} <> 'CANCELLED' OR ({cs:code} is null) OR ({ices:code} is null)) group by {o:pk}}}) q2 on q1.pk = q2.pk ) "
            + " where q1.BOOKEDQUANTITY <> q2.SHIPPEDQUANTITY OR q2.SHIPPEDQUANTITY IS NULL }} "
            + " UNION {{SELECT {O: pk} FROM {ORDER AS O LEFT JOIN ORDERENTRY AS OE ON {O: pk} = {OE: order} "
            + "LEFT JOIN ORDERENTRYSTATUS AS OES ON {OES: pk} = {OE: QUANTITYSTATUS}} "
            + "WHERE {O: creationTime} >= TO_DATE('"
            + ORDER_START_DATE
            + " ', 'YYYY-MM-DD HH24:MI:SS') AND {O: creationTime} <= TO_DATE('"
            + ORDER_END_DATE
            + "', 'YYYY-MM-DD HH24:MI:SS')"
            + "AND {O: versionId} IS NULL AND {OES: code} IN ('CANCELING', 'PARTIALLY_CANCELING')}})";

    /**
     * Method to check if GST switch is on or not
     *
     * @return true if on and false if off
     */
    public static boolean isGSTSwitchOn() {
        final String gstSwitch = Config.getParameter("gst.switch");
        if (StringUtils.isNotEmpty(gstSwitch) && StringUtils.endsWithIgnoreCase(gstSwitch, "YES")) {
            return true;
        }
        return false;
    }

    /**
     * Method to get the starting text for tax invoice number
     *
     */
    public static String getInvoiceText(final String type) {
        String invoiceText = null;
        switch (type) {
        case "INVOICE":
            invoiceText = Config.getParameter("gst.taxinvoice.name");
            break;
        case "BILL_OF_SUPPLY":
            invoiceText = Config.getParameter("gst.billofsupply.name");
            break;
        case "ADVANCE_RECEIPT":
            invoiceText = Config.getParameter("gst.advancereceipt.taxablename");
            break;
        case "ADVANCE_RECEIPT_BOS":
            invoiceText = Config.getParameter("gst.advancereceipt.exempted");
            break;
        case "CREDIT_NOTE":
            invoiceText = Config.getParameter("gst.creditnote.taxinvoice.name");
            break;
        case "CREDIT_NOTE_BOS":
            invoiceText = Config.getParameter("gst.creditnote.billofsupply.name");
            break;
        case "REFUND_VOUCHER":
            invoiceText = Config.getParameter("gst.refundvoucher.taxable.name");
            break;
        case "REFUND_VOUCHER_BOS":
            invoiceText = Config.getParameter("gst.refundvoucher.exempted.name");
            break;
        }

        return invoiceText;

    }

    public static long getAllocatedQuantityForConsignmentEntry(final ConsignmentEntryModel consignmentEntry) {
        long shippedQuantity = 0;
        if ((CollectionUtils.isEmpty(consignmentEntry.getInternalEntries()))
                && (!(consignmentEntry.getStatus().equals(ConsignmentEntryStatus.CANCELLED)))) {
            shippedQuantity = shippedQuantity + consignmentEntry.getAllocatedQuantity().longValue();
        } else {
            for (final InternalConsignmentEntryModel internalConsignmentEntryModel : consignmentEntry.getInternalEntries()) {
                if ((internalConsignmentEntryModel != null) && (internalConsignmentEntryModel.getQuantity() != null)
                        && (!(internalConsignmentEntryModel.getStatus().equals(InternalConsignmentEntryStatus.CANCELLED)))) {
                    shippedQuantity = shippedQuantity + internalConsignmentEntryModel.getQuantity().longValue();
                }
            }
        }
        return shippedQuantity;
    }

    public static Set<ConsignmentModel> getConsignmentAssociatedWithReturnEntry(
            final Map<ConsignmentEntryModel, Long> consignmentEntryQuantityMap) {
        if (consignmentEntryQuantityMap == null) {
            return new HashSet<>();
        }
        final Iterator<ConsignmentEntryModel> consignmentEntryIterator = consignmentEntryQuantityMap.keySet().iterator();
        final Set<ConsignmentModel> consignmentModelSet = new HashSet<>();
        while (consignmentEntryIterator.hasNext()) {
            consignmentModelSet.add(consignmentEntryIterator.next().getConsignment());
        }
        return consignmentModelSet;
    }

    public static boolean isReturnDisabled(final ConsignmentEntryModel source) {
        return (null == source || null == source.getConsignment().getInvoiceTime());
    }

    public static double isZeroTax(final Map<GstTaxTypes, String> taxComponent) {
        double taxVal = 0.0;
        if (MapUtils.isNotEmpty(taxComponent)) {
            for (final Entry<GstTaxTypes, String> taxCompos : taxComponent.entrySet()) {
                if (taxCompos.getValue() != null) {
                    try {
                        taxVal += Double.parseDouble(taxCompos.getValue());
                    } catch (final NumberFormatException e) {
                        LOG.error("NumberFormatException for tax component :" + taxCompos + " Message> " + e.getMessage());
                    }
                    if (taxVal > 0) {
                        break;
                    }
                }
            }
        }
        return taxVal;
    }

    public static Pair<Date, Date> getDateRange() {
        Date begining, end;
        final boolean previousMonth = getReceiptVoucherPreviousMonth();
        {
            final Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            final Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }
        if (previousMonth) {
            final DateTime startDate = new DateTime(begining).minusMonths(1);
            begining = new Date(startDate.getMillis());

            final DateTime endDate = new DateTime(end).minusMonths(1);
            end = new Date(endDate.getMillis());
        }
        return new Pair<>(begining, end);
    }

    public static Calendar getCalendarForNow() {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static void setTimeToBeginningOfDay(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setTimeToEndofDay(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static String getFormattedDate(final Date date) {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String result = df.format(date);
        return result;
    }

    public static List<Map<String, Integer>> generateSkuQuantityMapFromOrder(final OrderModel order) {
        final List<AbstractOrderEntryModel> orderEntries = order.getEntries();
        final Map<String, Integer> skusNonBOS = new HashMap<>();
        final Map<String, Integer> skusBOS = new HashMap<>();

        final List<Map<String, Integer>> skuMapList = new ArrayList<>();

        for (final AbstractOrderEntryModel orderEntry : orderEntries) {
            final String productCode = orderEntry.getProduct().getCode();
            if (orderEntry.getApplicableTaxPercent().doubleValue() > 0) {
                final Integer quantityPresent = null == skusNonBOS.get(productCode) ? Integer.valueOf(0) : skusNonBOS.get(productCode);
                skusNonBOS.put(productCode, Integer.valueOf((int) (quantityPresent.intValue() + orderEntry.getQuantity())));
            } else {
                final Integer quantityPresent = null == skusBOS.get(productCode) ? Integer.valueOf(0) : skusBOS.get(productCode);
                skusBOS.put(productCode, Integer.valueOf((int) (quantityPresent.intValue() + orderEntry.getQuantity())));
            }

        }

        skuMapList.add(skusNonBOS);
        skuMapList.add(skusBOS);

        return skuMapList;
    }

    public static List<Map<String, Integer>> generateSkuQuantityMapFromConsignment(final Set<ConsignmentEntryModel> consignmentEntries) {
        final Map<String, Integer> skusNonBOS = new HashMap<>();
        final Map<String, Integer> skusBOS = new HashMap<>();

        final List<Map<String, Integer>> skuMapList = new ArrayList<>();

        for (final ConsignmentEntryModel consignmentEntry : consignmentEntries) {
            final long quantity = GSTUtil.getAllocatedQuantityForConsignmentEntry(consignmentEntry);
            final String productCode = consignmentEntry.getOrderEntry().getProduct().getCode();
            if (consignmentEntry.getOrderEntry().getApplicableTaxPercent().doubleValue() > 0) {
                final Integer quantityPresent = null == skusNonBOS.get(productCode) ? Integer.valueOf(0) : skusNonBOS.get(productCode);
                skusNonBOS.put(productCode, Integer.valueOf((int) (quantityPresent.intValue() + quantity)));
            } else {
                final Integer quantityPresent = null == skusBOS.get(productCode) ? Integer.valueOf(0) : skusBOS.get(productCode);
                skusBOS.put(productCode, Integer.valueOf((int) (quantityPresent.intValue() + quantity)));
            }

        }

        skuMapList.add(skusNonBOS);
        skuMapList.add(skusBOS);

        return skuMapList;

    }

    public static boolean getReceiptVoucherPreviousMonth() {
        final String prevousMonth = Config.getParameter("receiptvoucher.previousmonth");
        if (prevousMonth != null && prevousMonth.equals("true")) {
            return true;
        }
        return false;
    }
}
