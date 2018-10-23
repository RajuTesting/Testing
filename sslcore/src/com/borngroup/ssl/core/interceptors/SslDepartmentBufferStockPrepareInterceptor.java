/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.DepartmentBufferStockModel;
import com.borngroup.ssl.core.services.SslDepartmentBufferstockService;

/**
 * @author dikshabhatia
 *
 *         Prepare Interceptor: This interceptor will check the various model level validations applicable on DepartmentBufferStockModel
 *
 */
public class SslDepartmentBufferStockPrepareInterceptor implements PrepareInterceptor<DepartmentBufferStockModel> {

    private static final Logger LOG = Logger.getLogger(SslDepartmentBufferStockPrepareInterceptor.class);

    private SslDepartmentBufferstockService sslDepartmentBufferstockService;

    @Override
    public void onPrepare(final DepartmentBufferStockModel departmentBufferStock, final InterceptorContext context)
            throws InterceptorException {

        LOG.info("inside SslDepartmentBufferStockPrepareInterceptor");
        // if a new DepartmentBufferStock Row is inserted
        if (context.isNew(departmentBufferStock)) {
            validateInsertedBufferStockEntry(departmentBufferStock);
        } else {
            // If an existing row is updated
            validateUpdatedBufferStockEntry(departmentBufferStock, context);

        }

    }

    private void validateInsertedBufferStockEntry(final DepartmentBufferStockModel departmentBufferStock) throws InterceptorException {
        final DepartmentBufferStockModel existingDepartmentBufferStockModel = getExistingDepartmentBufferStockModel(departmentBufferStock);

        if (null != existingDepartmentBufferStockModel) {
            // This implies we already have a row with the given season code
            if (null != departmentBufferStock.getSeasonCode() && null != existingDepartmentBufferStockModel.getSeasonGroupCode()
                    && !existingDepartmentBufferStockModel.getSeasonGroupCode()
                            .equalsIgnoreCase(departmentBufferStock.getSeasonGroupCode())) {
                // To check for season code and season group mapping: a season code can exist for only one season group
                throw new InterceptorException("This season code already exists for some other season group");
            } else if (null == existingDepartmentBufferStockModel.getSeasonGroupCode()
                    && null != departmentBufferStock.getSeasonGroupCode()) {
                // if no season group is mapped for this season code
                throw new InterceptorException("No season group exists corresponding to this season code");
            }
        } else {
            // This implies we don't have a row with the given season code
            // To check if the season group is added with a season code only not without the season code
            if (null != departmentBufferStock.getSeasonGroupCode() && null == departmentBufferStock.getSeasonCode()) {
                throw new InterceptorException("A Season group code cannot be added without Season code");
            }
        }

    }

    private void validateUpdatedBufferStockEntry(final DepartmentBufferStockModel departmentBufferStock, final InterceptorContext context)
            throws InterceptorException {
        final DepartmentBufferStockModel existingDepartmentBufferStockModel = getExistingDepartmentBufferStockModel(departmentBufferStock);

        if (null != existingDepartmentBufferStockModel && null != departmentBufferStock.getSeasonCode()
                && null != existingDepartmentBufferStockModel.getSeasonGroupCode()
                && !existingDepartmentBufferStockModel.getSeasonGroupCode().equalsIgnoreCase(departmentBufferStock.getSeasonGroupCode())) {
            // This implies we already have a row with the given season code
            // To check for season code and season group mapping: a season code can exist for only one season group
            throw new InterceptorException("This season code already exists for some other season group");
        }
        /*
         * else if (null == existingDepartmentBufferStockModel.getSeasonGroupCode() && null != departmentBufferStock.getSeasonGroupCode()) {
         * LOG.info("no season group is mapped to this season code"); }
         */

    }

    private DepartmentBufferStockModel getExistingDepartmentBufferStockModel(final DepartmentBufferStockModel departmentBufferStock) {
        DepartmentBufferStockModel existingDepartmentBufferStockModel = null;
        if (null != departmentBufferStock.getSeasonCode()) {
            final List<DepartmentBufferStockModel> departmentBufferStockModel = getSslDepartmentBufferstockService()
                    .checkSeasonMappingWithSeasonGroup(departmentBufferStock.getSeasonCode());

            if (CollectionUtils.isNotEmpty(departmentBufferStockModel)) {
                existingDepartmentBufferStockModel = departmentBufferStockModel.iterator().next();
            }
        }
        return existingDepartmentBufferStockModel;

    }

    public SslDepartmentBufferstockService getSslDepartmentBufferstockService() {
        return sslDepartmentBufferstockService;
    }

    public void setSslDepartmentBufferstockService(final SslDepartmentBufferstockService sslDepartmentBufferstockService) {
        this.sslDepartmentBufferstockService = sslDepartmentBufferstockService;
    }

}
