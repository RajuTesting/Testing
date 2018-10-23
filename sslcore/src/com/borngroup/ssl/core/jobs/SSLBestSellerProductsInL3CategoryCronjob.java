package com.borngroup.ssl.core.jobs;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.SSLBestSellerProductsInL3CategoryCronjobModel;
import com.borngroup.ssl.core.product.service.SslProductService;
import com.borngroup.ssl.core.services.SSLCategoryService;
import com.ssl.core.model.BestSellerComponentModel;

/**
 * Cron Job : Cron Job to attach similar products.
 * <p/>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public class SSLBestSellerProductsInL3CategoryCronjob extends AbstractJobPerformable<SSLBestSellerProductsInL3CategoryCronjobModel> {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SSLBestSellerProductsInL3CategoryCronjob.class);

    @Resource(name = "sslProductService")
    private SslProductService sslProductService;

    @Resource(name = "commerceCategoryService")
    protected SSLCategoryService sslCategoryService;

    /** Model Service {@link ModelService}. */
    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public PerformResult perform(final SSLBestSellerProductsInL3CategoryCronjobModel cronJobModel) {

        LOG.info("Product - Best Seller Products Cron Job");
        final Instant start = Instant.now();
        sslProductService.resetBestSellerProducts();
        final CategoryModel topCategory = sslCategoryService.getCategoryForCode("categories");
        final Collection<CategoryModel> allCategories = topCategory.getAllSubcategories();
        final List<BestSellerComponentModel> bestSellerComponentList = new ArrayList<BestSellerComponentModel>();
        for (final CategoryModel L1Category : allCategories) {
            for (final CategoryModel l2Category : L1Category.getAllSubcategories()) {
                for (final CategoryModel l3Category : l2Category.getAllSubcategories()) {
                    if (l3Category.getAllSubcategories().size() == 0) {
                        if (cronJobModel.getDateOfOrderCreated() == null) {
                            cronJobModel.setDateOfOrderCreated(1);
                        }

                        final List<ProductModel> styleproducts = sslProductService.getBestSellerProductsForCategory(l3Category,
                                cronJobModel.getDateOfOrderCreated());
                        final StringBuilder listOfProducts = new StringBuilder();
                        if (!styleproducts.isEmpty()) {
                            for (final ProductModel product : styleproducts) {
                                product.setBestSeller(Boolean.valueOf(true));
                                modelService.save(product);
                                listOfProducts.append(product.getCode() + ", ");
                            }

                            l3Category.setBestSellerProducts(styleproducts);
                            final BestSellerComponentModel bestSellerComponent = new BestSellerComponentModel();
                            bestSellerComponent.setCategory(l3Category.getCode());

                            bestSellerComponent.setBestSellerProducts(listOfProducts.toString());

                            final String pattern = "dd/MM/yyyy";
                            final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            final Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            final String output = sdf.format(calendar.getTime());
                            Date date = null;
                            try {
                                date = new SimpleDateFormat(pattern).parse(output);
                            } catch (final ParseException e) {
                                LOG.error("Error Occured:", e);
                            }

                            bestSellerComponent.setDate(date);
                            bestSellerComponentList.add(bestSellerComponent);
                            this.modelService.save(l3Category);
                        }
                    }
                }
            }
        }
        this.modelService.saveAll(bestSellerComponentList);
        final Instant end = Instant.now();
        LOG.info(String.format("Spent during this cron job call for best sellers : " + Duration.between(start, end)));
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
