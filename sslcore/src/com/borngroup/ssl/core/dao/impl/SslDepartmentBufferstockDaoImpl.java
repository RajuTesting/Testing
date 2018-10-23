/**
 *
 */
package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.dao.SslDepartmentBufferstockDao;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.DepartmentBufferStockModel;

/**
 * @author Nagarro-Dev
 *
 */
public class SslDepartmentBufferstockDaoImpl implements SslDepartmentBufferstockDao {

    FlexibleSearchService flexibleSearchService;

    private final static String BUFFER_QUERY = "SELECT {dbs." + DepartmentBufferStockModel.PK + "} FROM { "
            + DepartmentBufferStockModel._TYPECODE + " AS dbs } WHERE { dbs." + DepartmentBufferStockModel.DEPARTMENTCODE
            + " } = ?departmentCode " + "AND {dbs." + DepartmentBufferStockModel.SUBDEPARTMENTCODE + "} = ?subDepartmentCode ";

    private final static String SEASON_CODE_CHECK_QUERY = "SELECT {dbs." + DepartmentBufferStockModel.PK + "} FROM { "
            + DepartmentBufferStockModel._TYPECODE + " AS dbs } WHERE { dbs." + DepartmentBufferStockModel.SEASONCODE + " } = ?seasonCode";

    /**
     * Method to get buffer stock for a product.
     */
    @Override
    public Long getBufferStock(final ProductModel product) {
        Long bufferstock = Long.valueOf(0L);
        if (product.getDepartmentCode() != null && product.getSubDepartmentCode() != null) {
            bufferstock = getBufferStockWithSeason(product);
            if (bufferstock.longValue() == -1) {
                final StringBuilder queryBuilder = new StringBuilder(BUFFER_QUERY);
                queryBuilder.append(" AND {dbs." + DepartmentBufferStockModel.SEASONCODE + "} is NULL");
                final String query = queryBuilder.toString();

                final Map<String, Object> params = new HashMap();
                params.put("departmentCode", product.getDepartmentCode());
                params.put("subDepartmentCode", product.getSubDepartmentCode());

                final List<DepartmentBufferStockModel> departmentBufferedStock = getFlexibleSearchService()
                        .<DepartmentBufferStockModel> search(new FlexibleSearchQuery(query, params)).getResult();
                if (CollectionUtils.isNotEmpty(departmentBufferedStock)) {
                    bufferstock = departmentBufferedStock.get(0).getBufferStock();
                } else {
                    bufferstock = Long.valueOf(0L);
                }
            }
        }
        return bufferstock;
    }

    /**
     * Method to get buffer stock for product correspoding to season of product.
     *
     * @param product
     * @return
     */
    private Long getBufferStockWithSeason(final ProductModel product) {
        Long bufferstock = Long.valueOf(-1L);
        final String seasonCode = getSeasonCodeFromProduct(product);
        final Map<String, Object> params = new HashMap();
        if (StringUtils.isNotEmpty(seasonCode)) {

            final StringBuilder queryBuilder = new StringBuilder(BUFFER_QUERY);

            queryBuilder.append(" AND {dbs." + DepartmentBufferStockModel.SEASONCODE + "} = ?seasonCode ");

            final String query = queryBuilder.toString();
            params.put("seasonCode", seasonCode);
            params.put("departmentCode", product.getDepartmentCode());
            params.put("subDepartmentCode", product.getSubDepartmentCode());

            final List<DepartmentBufferStockModel> departmentBufferedStock = getFlexibleSearchService()
                    .<DepartmentBufferStockModel> search(new FlexibleSearchQuery(query, params)).getResult();
            if (CollectionUtils.isNotEmpty(departmentBufferedStock) && departmentBufferedStock.get(0) != null) {
                bufferstock = departmentBufferedStock.get(0).getBufferStock();
            }
        }
        return bufferstock;
    }

    /**
     * Method to get SeasonCode of a product. If not present we search in hierarchy for season Code availability.
     *
     * @param product
     * @return
     */
    private String getSeasonCodeFromProduct(final ProductModel product) {
        String seasonCode = product.getSeasonCode();
        if (StringUtils.isEmpty(seasonCode) && product instanceof ApparelSizeVariantProductModel) {
            final ApparelSizeVariantProductModel sizeVariant = (ApparelSizeVariantProductModel) product;
            if (sizeVariant.getBaseProduct() != null) {
                final ApparelStyleVariantProductModel styleVariant = (ApparelStyleVariantProductModel) sizeVariant.getBaseProduct();
                seasonCode = !StringUtils.isEmpty(styleVariant.getSeasonCode()) ? styleVariant.getSeasonCode()
                        : (styleVariant.getBaseProduct() != null ? styleVariant.getBaseProduct().getSeasonCode() : null);
            }
        }
        return seasonCode;
    }

    /**
     * To check if for a particular season code any corresponding value of Season Group code exists or not
     *
     * @param seasonCode, seasonGroupCode
     * @return boolean
     */
    @Override
    public List<DepartmentBufferStockModel> checkSeasonMappingWithSeasonGroup(final String seasonCode) {
        final Map<String, Object> params = new HashMap();

        final StringBuilder queryBuilder = new StringBuilder(SEASON_CODE_CHECK_QUERY);

        final String query = queryBuilder.toString();
        params.put("seasonCode", seasonCode);
        final List<DepartmentBufferStockModel> departmentBufferedStock = getFlexibleSearchService()
                .<DepartmentBufferStockModel> search(new FlexibleSearchQuery(query, params)).getResult();
        return departmentBufferedStock;
    }

    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<DepartmentBufferStockModel> find() {
        // YTODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentBufferStockModel> find(final Map<String, ? extends Object> paramMap) {
        // YTODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentBufferStockModel> find(final SortParameters paramSortParameters) {
        // YTODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentBufferStockModel> find(final Map<String, ? extends Object> paramMap, final SortParameters paramSortParameters) {
        // YTODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DepartmentBufferStockModel> find(final Map<String, ? extends Object> paramMap, final SortParameters paramSortParameters,
            final int paramInt) {
        // YTODO Auto-generated method stub
        return null;
    }

}
