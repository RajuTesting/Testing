/**
 *
 */
package com.borngroup.ssl.core.review.service.impl;

import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.enums.CustomerReviewApprovalType;
import de.hybris.platform.customerreview.impl.DefaultCustomerReviewService;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.review.dao.SslCustomerReviewDao;
import com.borngroup.ssl.core.review.service.SslCustomerReviewService;

/**
 * @author Viji
 *
 */
public class SslCustomerReviewServiceImpl extends DefaultCustomerReviewService implements SslCustomerReviewService {
    @Resource(name = "sslCustomerReviewDao")
    private SslCustomerReviewDao sslCustomerReviewDao;

    @Resource(name = "modelService")
    private ModelService modelService;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.service.SslCustomerReviewService#getAveragQuality(de.hybris.platform.core.model.
     * product.ProductModel)
     */
    @Override
    public Double getAveragQuality(final ProductModel productModel) {
        return getSslCustomerReviewDao().getAveragQuality(productModel);
    }

    @Override
    public SearchPageData<CustomerReviewModel> getPaginatedReviews(final ProductModel productModel, final String[] selectedRatings,
            final PaginationData data) {
        return getSslCustomerReviewDao().getPaginatedReviews(productModel, selectedRatings, data);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.service.SslCustomerReviewService#getAveragRating(de.hybris.platform.core.model.product
     * .ProductModel)
     */
    @Override
    public Double getAveragRating(final ProductModel productModel) {
        return getSslCustomerReviewDao().getAveragRating(productModel);
    }

    @Override
    public List<CustomerReviewModel> getReviewsForProductAndLanguage(final ProductModel product, final LanguageModel language) {
        return getSslCustomerReviewDao().getReviewsForProductAndLanguage(product, language);
    }

    @Override
    public List<CustomerReviewModel> getReviewsForCurrentUser(final UserModel user) {
        return getSslCustomerReviewDao().getReviewsForCurrentUser(user);
    }

    @Override
    public List<CustomerReviewModel> getWeeklyProductReviews() {
        return getSslCustomerReviewDao().getWeeklyProductReviews();
    }

    @Override
    public List<CustomerReviewModel> getReviewsForProductAndUser(final ProductModel product, final UserModel user) {
        return getSslCustomerReviewDao().getReviewsForProductAndUser(product, user);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.service.impl.SslCustomerReviewService# editProductReview(java.lang.String,
     * de.hybris.platform.commercefacades.product.data.ReviewData)
     */
    @Override
    public void editProductReview(final String reviewId, final ReviewData reviewData) {
        final ItemModel item = modelService.get(PK.parse(reviewId));
        if (item instanceof CustomerReviewModel) {
            final CustomerReviewModel customerReview = (CustomerReviewModel) item;
            customerReview.setRating(reviewData.getRating());
            customerReview.setComment(reviewData.getComment());
            customerReview.setHeadline(reviewData.getHeadline());
            customerReview.setApprovalStatus(CustomerReviewApprovalType.PENDING);
            modelService.save(customerReview);
        } else {
            throw new ModelNotFoundException("Invalid Review Id");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.service.SslCustomerReviewService#getTotalRecommendations(de.hybris.platform.core.model.product.
     * ProductModel)
     */
    @Override
    public int getTotalRecommendations(final ProductModel product) {
        return sslCustomerReviewDao.getTotalRecommendations(product);
    }

    public SslCustomerReviewDao getSslCustomerReviewDao() {
        return sslCustomerReviewDao;
    }

    public void setSslCustomerReviewDao(final SslCustomerReviewDao sslCustomerReviewDao) {
        this.sslCustomerReviewDao = sslCustomerReviewDao;
    }

	@Override
	public int getTotalReviews(final ProductModel product) {
		return this.sslCustomerReviewDao.getTotalReviews(product);
	}
}
