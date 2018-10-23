/**
 *
 */
package com.borngroup.ssl.core.services.returns.impl;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.commerceservices.stock.strategies.CommerceAvailabilityCalculationStrategy;
import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.returns.model.ReplacementEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsAgentGroupModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.enums.ReturnRequestType;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ExchangeStockLevelModel;
import com.borngroup.ssl.core.services.returns.SSLExchangeService;

/**
 * @author nikhilbarar
 *
 */
public class SSLExchangeServiceImpl extends SSLReturnServiceImpl implements SSLExchangeService {

    private static final Logger LOG = Logger.getLogger(SSLExchangeServiceImpl.class);

    @Autowired
    private BaseStoreService baseStoreService;

    @Autowired
    private StockService stockService;

    @Autowired
    private WarehouseSelectionStrategy warehouseSelectionStrategy;

    @Autowired
    private CommerceAvailabilityCalculationStrategy commerceStockLevelCalculationStrategy;

    @Resource(name = "stockConverter")
    private Converter<ProductModel, StockData> stockConverter;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private EnumerationService enumerationService;

    @Override
    public StockData getStock(final ProductModel productModel) {
        StockData stockData = null;
        if (productModel instanceof ApparelSizeVariantProductModel) {
            stockData = stockConverter.convert(productModel);
        }
        return stockData;
    }

    @Override
    public boolean isOutOfStock(final ProductModel productModel) {
        boolean isOutOfStock = true;

        StockData stockData = null;
        if (productModel instanceof ApparelSizeVariantProductModel) {
            stockData = stockConverter.convert(productModel);
            final long availablelAmount = stockData.getStockLevel().longValue();
            final StockLevelStatus stockLevelStatus = stockData.getStockLevelStatus();
            if (availablelAmount > 0
                    && (stockLevelStatus.equals(StockLevelStatus.INSTOCK) || stockLevelStatus.equals(StockLevelStatus.LOWSTOCK))
                    && (productModel.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))) {
                isOutOfStock = false;
            }
        }

        return isOutOfStock;
    }

    @Override
    public List<StockLevelModel> getStockLevels(final ProductModel product) {
        final BaseStoreModel baseStore = baseStoreService.getCurrentBaseStore();
        final Collection<StockLevelModel> stockLevels = stockService.getStockLevels(product,
                warehouseSelectionStrategy.getWarehousesForBaseStore(baseStore));

        final List<StockLevelModel> stockLevelsList = new ArrayList<StockLevelModel>(stockLevels);
        return stockLevelsList;
    }

    @Override
    public long getAvailability(final StockLevelModel stockLevel) {
        final Collection<StockLevelModel> stockLevels = new ArrayList<StockLevelModel>();
        stockLevels.add(stockLevel);
        return commerceStockLevelCalculationStrategy.calculateAvailability(stockLevels).longValue();
    }

    @Override
    public void sortStockByAvailableQuantity(final List<StockLevelModel> stockLevels) {
        Collections.sort(stockLevels, new Comparator<StockLevelModel>() {
            @Override
            public int compare(final StockLevelModel s1, final StockLevelModel s2) {
                final long availableS1 = getAvailability(s1);
                final long availableS2 = getAvailability(s2);
                return Long.compare(availableS2, availableS1);
            }
        });
    }

    @Override
    public ReturnRequestModel generateExchangeRequestPerODC(final List<ReplacementEntryModel> replacableEntities,
            final OrderModel orderModel) {
        ReturnRequestModel returnRequest = null;
        if (CollectionUtils.isNotEmpty(replacableEntities)) {
            returnRequest = createReturnRequest(replacableEntities, orderModel);
            final String rmaNumber = createRMA(returnRequest);
            returnRequest.setRMA(rmaNumber);
        }
        return returnRequest;
    }

    private ReturnRequestModel createReturnRequest(final List<ReplacementEntryModel> returnableEntities, final OrderModel orderModel) {
        final ReturnRequestModel returnRequest = createReturnRequest(orderModel);
        returnRequest.setType(ReturnRequestType.EXCHANGE);
        returnRequest.setStatus(ReturnStatus.NOT_PICKED);
        for (final ReplacementEntryModel returnableEntity : returnableEntities) {
            returnableEntity.setReturnRequest(returnRequest);
            getModelService().save(returnableEntity);
        }
        return returnRequest;
    }

    @Override
    public CsTicketModel createCSTicket(final ReturnRequestModel returnRequest, final AbstractOrderModel orderModel) {
        if (orderModel != null && returnRequest != null) {
            final CsTicketModel csticketmodel = getModelService().create(CsTicketModel.class);
            csticketmodel.setHeadline("Exchange: Order-" + returnRequest.getOrder().getCode() + " RMA-" + returnRequest.getRMA());
            csticketmodel.setCategory(CsTicketCategory.EXCHANGE);

            csticketmodel.setPriority(CsTicketPriority.HIGH);
            csticketmodel.setOrder(orderModel);
            csticketmodel.setCustomer(orderModel.getUser());
            csticketmodel.setState(CsTicketState.OPEN);
            if (getUserService().getCurrentUser() instanceof EmployeeModel) {
                csticketmodel.setAssignedAgent((EmployeeModel) getUserService().getCurrentUser());
            }
            csticketmodel.setAssignedGroup((CsAgentGroupModel) getUserService().getUserGroupForUID("csgroup"));

            final CsCustomerEventModel csCustomerEventModel = getModelService().create(CsCustomerEventModel.class);
            csCustomerEventModel.setInterventionType(CsInterventionType.CALL);
            csCustomerEventModel.setReason(CsEventReason.EXCHANGE);
            csCustomerEventModel.setSubject("Exchange Order Ticket");
            csCustomerEventModel.setText("Exchange Order");

            final CsTicketModel csTicket = getTicketBusinessService().createTicket(csticketmodel, csCustomerEventModel);

            String note = "";
            if (null != returnRequest.getPickup()) {
                note = "Pickup: " + returnRequest.getPickup().getCode();
                getTicketBusinessService().addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.EXCHANGE, note, null);
            }

            if (CollectionUtils.isNotEmpty(returnRequest.getReturnEntries())) {
                final StringBuilder products = new StringBuilder();
                for (final ReturnEntryModel returnEntry : returnRequest.getReturnEntries()) {
                    final ProductModel product = returnEntry.getOrderEntry().getProduct();
                    final ApparelSizeVariantProductModel sizeVariant = (ApparelSizeVariantProductModel) product;
                    final String size = (sizeVariant).getSize();
                    final String style = ((ApparelStyleVariantProductModel) (sizeVariant).getBaseProduct()).getStyle();
                    products.append("Product: " + getBaseProduct(product).getName() + "-" + style + "-" + size + "\tReturn Quantity: "
                            + returnEntry.getExpectedQuantity() + "\tReason: "
                            + enumerationService.getEnumerationName(((ReplacementEntryModel) returnEntry).getReason()) + "\r");

                    products.append("Exchanged Products:\r");

                    int i = 0;
                    final CatalogVersionModel activeCatalogVersion = ((CMSSiteModel) (returnRequest.getOrder().getSite()))
                            .getDefaultCatalog().getActiveCatalogVersion();
                    final ReplacementEntryModel replacementEntry = (ReplacementEntryModel) returnEntry;
                    final Set<ExchangeStockLevelModel> stockEntries = replacementEntry.getExchangeStockLevels();
                    for (final ExchangeStockLevelModel stockLevel : stockEntries) {
                        final String exchangeProductCode = stockLevel.getStockLevel().getProductCode();
                        ProductModel exchangeProduct = null;
                        try {
                            final ProductModel exampleProduct = new ProductModel();
                            exampleProduct.setCode(exchangeProductCode);
                            exampleProduct.setCatalogVersion(activeCatalogVersion);
                            exchangeProduct = flexibleSearchService.getModelByExample(exampleProduct);
                        } catch (ModelNotFoundException | AmbiguousIdentifierException e) {
                            LOG.error("Error in getting replacement product" + e);
                            LOG.error(ExceptionUtils.getStackTrace(e));
                        }
                        final ApparelSizeVariantProductModel exchangeProductSizeVariant = (ApparelSizeVariantProductModel) exchangeProduct;
                        final String exchangeProductSize = exchangeProductSizeVariant.getSize();
                        final String exchangeProductStyle = ((ApparelStyleVariantProductModel) (exchangeProductSizeVariant)
                                .getBaseProduct()).getStyle();
                        products.append("Product " + i + ": " + getBaseProduct(exchangeProduct).getName() + "-" + exchangeProductStyle
                                + "-" + exchangeProductSize + "\t\tQuantity: " + stockLevel.getQuantity() + "\r");
                        i++;
                    }

                }
                getTicketBusinessService().addNoteToTicket(csTicket, CsInterventionType.CALL, CsEventReason.EXCHANGE, products.toString(),
                        null);
            }

            getModelService().save(csTicket);

            return csTicket;
        }
        return null;
    }

    private ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }
}
