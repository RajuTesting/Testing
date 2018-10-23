/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.stock.impl.DefaultStockLevelDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SSLStockLevelDao;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder.JoinType;

/**
 * @author kaushik.ganguly
 *
 */
public class DefaultSSLStockLevelDao extends DefaultStockLevelDao implements SSLStockLevelDao {

    private static final Logger LOG = Logger.getLogger(DefaultSSLStockLevelDao.class);
    private static final String STOCKLEVEL = "stockLevel";
    private static final String WAREHOUSE = "ware";

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.dao.SSLStockLevelDao#getStockLevels(int, int)
     */
    @Override
    public List<StockLevelModel> getStockLevels(final int pageNo, final int pageLimit) {

        final FlexibleSearchQuery query = getFlexibleSearchQueryForStockLevel().build();
        int offset = 0;
        if (pageNo <= 1) {
            offset = 0;
        } else {
            offset = (pageNo - 1) * (pageLimit);
        }

        final int range = offset - 1 + pageLimit;

        query.setStart(offset);
        query.setCount(range);

        final SearchResult<StockLevelModel> stockLevels = getFlexibleSearchService().search(query);
        final List<StockLevelModel> stock = stockLevels.getResult();

        return stock;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.stock.impl.DefaultStockLevelDao#findAllStockLevels(java.lang.String)
     */
    @Override
    public Collection<StockLevelModel> findAllStockLevels(final String productCode) {
        checkProductCode(productCode);

        final FlexibleSearchQuery fQuery = getFlexibleSearchQueryForStockLevel().whereEquals(STOCKLEVEL, StockLevelModel.PRODUCTCODE,
                productCode).build();
        final SearchResult result = getFlexibleSearchService().search(fQuery);
        return result.getResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.stock.impl.DefaultStockLevelDao#findStockLevel(java.lang.String,
     * de.hybris.platform.ordersplitting.model.WarehouseModel)
     */
    @Override
    public StockLevelModel findStockLevel(final String productCode, final WarehouseModel warehouse) {
        checkProductCode(productCode);
        checkWarehouse(warehouse);

        final FlexibleSearchQuery fQuery = new FlexibleSearchQueryBuilder().from(StockLevelModel._TYPECODE, STOCKLEVEL)
                .join(WarehouseModel._TYPECODE, WAREHOUSE, StockLevelModel.WAREHOUSE, JoinType.LEFT_OUTER).select(STOCKLEVEL, "PK")
                .whereEquals(STOCKLEVEL, StockLevelModel.PRODUCTCODE, productCode)
                .whereEquals(STOCKLEVEL, StockLevelModel.WAREHOUSE, warehouse.getPk()).build();
        final SearchResult result = getFlexibleSearchService().search(fQuery);
        final List stockLevels = result.getResult();

        if (stockLevels.isEmpty()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("no stock level for product [" + productCode + "] in warehouse [" + warehouse.getName() + "] found.");
            }
            return null;
        }
        if (stockLevels.size() == 1) {
            return ((StockLevelModel) stockLevels.get(0));
        }

        LOG.error("more than one stock level with product code [" + productCode + "] and warehouse [" + warehouse.getName()
                + "] found, and the first one is returned.");
        return ((StockLevelModel) stockLevels.get(0));
    }

    @Override
    public Collection<StockLevelModel> findStockLevels(final String productCode, final Collection<WarehouseModel> warehouses) {
        return findStockLevelsImpl(productCode, warehouses, null);
    }

    @Override
    public Collection<StockLevelModel> findStockLevelsWithoutOnline(final String productCode, final Collection<WarehouseModel> warehouses) {
        return findStockLevelsImplWithoutEnabled(productCode, warehouses, null);
    }

    @Override
    public Collection<StockLevelModel> findStockLevels(final String productCode, final Collection<WarehouseModel> warehouses,
            final int preOrderQuantity) {
        return findStockLevelsImpl(productCode, warehouses, Integer.valueOf(preOrderQuantity));
    }
    
	@Override
	public Collection<StockLevelModel> findStockLevelWithBuffer(final String productCode,
			final Collection<WarehouseModel> warehouses, final Boolean bufferIncluded) {
		return findStockLevelsForBuffer(productCode, warehouses, bufferIncluded);
	}

	private Collection<StockLevelModel> findStockLevelsForBuffer(String productCode,
			Collection<WarehouseModel> warehouses, Boolean bufferIncluded) {
		checkProductCode(productCode);
		final List filteredWarehouses = filterWarehouses(warehouses);
		if (filteredWarehouses.isEmpty()) {
			return CollectionUtils.EMPTY_COLLECTION;
		}

		final StringBuilder query = new StringBuilder();

		query.append("SELECT {pk} FROM {StockLevel as stock left outer join Warehouse as ware on {stock.warehouse} = {ware.pk} } WHERE {stock.productCode} = ?productCode");
		query.append(" AND {ware.enabledForOnline} = '1' AND {stock.warehouse} IN (?WAREHOUSES_PARAM)");

		query.append(" AND {ware.bufferIncluded} = ?bufferIncluded");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		fQuery.addQueryParameter("productCode", productCode);

		fQuery.addQueryParameter("WAREHOUSES_PARAM", filteredWarehouses);

		fQuery.addQueryParameter("bufferIncluded", bufferIncluded);

		final SearchResult result = getFlexibleSearchService().search(fQuery);
		return result.getResult();
	}

	private Collection<StockLevelModel> findStockLevelsImpl(final String productCode, final Collection<WarehouseModel> warehouses,
            final Integer preOrderQuantity) {
        checkProductCode(productCode);
        final List _warehouses = filterWarehouses(warehouses);
        if (_warehouses.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        final StringBuilder query = new StringBuilder();

        query.append("SELECT {pk} FROM {StockLevel as stock left outer join Warehouse as ware on {stock.warehouse} = {ware.pk} } WHERE {stock.productCode} = ?productCode");

        if (preOrderQuantity != null) {
            query.append(" AND {stock.maxPreOrder} >= ?maxPreOrder");
        }

        query.append(" AND {ware.enabledForOnline} = '1' AND {stock.warehouse} IN (?WAREHOUSES_PARAM)");

        final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
        fQuery.addQueryParameter("productCode", productCode);
        if (preOrderQuantity != null) {
            fQuery.addQueryParameter("maxPreOrder", preOrderQuantity);
        }
        fQuery.addQueryParameter("WAREHOUSES_PARAM", _warehouses);
        final SearchResult result = getFlexibleSearchService().search(fQuery);
        return result.getResult();
    }

    private Collection<StockLevelModel> findStockLevelsImplWithoutEnabled(final String productCode,
            final Collection<WarehouseModel> warehouses, final Integer preOrderQuantity) {
        checkProductCode(productCode);
        final List _warehouses = filterWarehouses(warehouses);
        if (_warehouses.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        final StringBuilder query = new StringBuilder();

        query.append("SELECT {pk} FROM {StockLevel as stock left outer join Warehouse as ware on {stock.warehouse} = {ware.pk} } WHERE {stock.productCode} = ?productCode");

        if (preOrderQuantity != null) {
            query.append(" AND {stock.maxPreOrder} >= ?maxPreOrder");
        }

        query.append(" AND {stock.warehouse} IN (?WAREHOUSES_PARAM)");

        final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
        fQuery.addQueryParameter("productCode", productCode);
        if (preOrderQuantity != null) {
            fQuery.addQueryParameter("maxPreOrder", preOrderQuantity);
        }
        fQuery.addQueryParameter("WAREHOUSES_PARAM", _warehouses);
        final SearchResult result = getFlexibleSearchService().search(fQuery);
        return result.getResult();
    }

    private void checkProductCode(final String productCode) {
        if (productCode != null) {
            return;
        }
        throw new IllegalArgumentException("product code cannot be null.");
    }

    /**
     * @return
     */
    private FlexibleSearchQueryBuilder getFlexibleSearchQueryForStockLevel() {
        return new FlexibleSearchQueryBuilder().from(StockLevelModel._TYPECODE, STOCKLEVEL)
                .join(WarehouseModel._TYPECODE, WAREHOUSE, StockLevelModel.WAREHOUSE, JoinType.LEFT_OUTER).select(STOCKLEVEL, "PK")
                .whereEquals(WAREHOUSE, WarehouseModel.ENABLEDFORONLINE, Boolean.TRUE);
    }

    private void checkWarehouse(final WarehouseModel warehouse) {
        if (warehouse != null) {
            return;
        }
        throw new IllegalArgumentException("warehouse cannot be null.");
    }

    private List<WarehouseModel> filterWarehouses(final Collection<WarehouseModel> warehouses) {
        if (warehouses == null) {
            throw new IllegalArgumentException("warehouses cannot be null.");
        }
        final Set result = new HashSet();
        for (final WarehouseModel house : warehouses) {
            if (house == null) {
                continue;
            }
            result.add(house);
        }

        return new ArrayList(result);
    }
}
