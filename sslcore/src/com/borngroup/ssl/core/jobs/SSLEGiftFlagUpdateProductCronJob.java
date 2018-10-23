/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * CronJob to change egift flag for department mapped products.
 *
 * @author techouts
 *
 */
public class SSLEGiftFlagUpdateProductCronJob extends AbstractJobPerformable<CronJobModel> {

	/** Log4j Logger. **/
	private static final Logger LOG = Logger.getLogger(SSLEGiftFlagUpdateProductCronJob.class);

	private static final String DEPARTMENT_LIST = "ssl.department.code.list";

	private static final String PRODUCTLIST_FOR_CATEGORY = "select {pk} from {product} where {departmentCode} IN (?depCodes) and {isGiftProduct} = ?giftProduct ";

	@Override
	public PerformResult perform(final CronJobModel arg0) {
		try {
			final List<String> departments = getDepartmentList();
			if (departments.isEmpty()) {
				LOG.info("No department configured :: please configure the department");
				return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
			}
			departments.replaceAll(String::trim);
			final List<ProductModel> products = getProductsForDepartment(departments);
			if (CollectionUtils.isEmpty(products)) {
				LOG.info("No products found with department" + departments);
				return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
			} else {
				final List failureDepList = checkFailureDepartments(departments, products);
				LOG.info("No products found with department" + failureDepList);
				for (final ProductModel productModel : products) {
					productModel.setIsGiftProduct(Boolean.TRUE);
				}
				modelService.saveAll(products);
			}
		} catch (final Exception exception) {
			LOG.error(exception.getMessage());
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param departments
	 * @param products
	 *            return Not Found Product DepList
	 */
	private List checkFailureDepartments(final List<String> departments, final List<ProductModel> products) {
		final List list = new ArrayList();
		for (final String department : departments) {
			int count = 0;
			for (final ProductModel productModel : products) {
				if (productModel.getDepartmentCode().equals(department)) {
					LOG.info("Product found with department" + department);
					break;
				} else {
					count++;
				}
			}
			if (count == products.size()) {
				list.add(department);
			}
		}
		return list;
	}

	/**
	 * @param departments
	 * @return product list for departments
	 */
	private List<ProductModel> getProductsForDepartment(final List<String> departments) {
		final FlexibleSearchQuery query = new FlexibleSearchQuery(PRODUCTLIST_FOR_CATEGORY);
		query.addQueryParameter("depCodes", departments);
		query.addQueryParameter("giftProduct", Boolean.FALSE);
		final SearchResult<ProductModel> searchResult = flexibleSearchService.search(query);
		return searchResult.getResult();
	}

	/**
	 * @return departmentList
	 */
	private List<String> getDepartmentList() {
		if (null != Config.getParameter(DEPARTMENT_LIST)) {
			return Lists.newArrayList(Splitter.on(",").split(Config.getParameter(DEPARTMENT_LIST)));
		}
		return Collections.emptyList();
	}
}
