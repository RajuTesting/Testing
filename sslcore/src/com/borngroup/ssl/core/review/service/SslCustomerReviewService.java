/**
 *
 */
package com.borngroup.ssl.core.review.service;

import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.CustomerReviewService;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

import java.util.List;

/**
 * @author Viji
 *
 */
public interface SslCustomerReviewService extends CustomerReviewService {

    public Double getAveragQuality(ProductModel productModel);

    public Double getAveragRating(ProductModel baseProduct);

    public SearchPageData<CustomerReviewModel> getPaginatedReviews(ProductModel productModel, String[] selectedRatings,
            PaginationData data);

    /**
     * @param user
     * @return
     */
    List<CustomerReviewModel> getReviewsForCurrentUser(UserModel user);

    List<CustomerReviewModel> getWeeklyProductReviews();

    /**
     * @param product
     * @param user
     * @return
     */
    List<CustomerReviewModel> getReviewsForProductAndUser(ProductModel product, UserModel user);

    /**
     * @param reviewId
     * @param reviewData
     */
    public void editProductReview(String reviewId, ReviewData reviewData);

    public int getTotalRecommendations(ProductModel sssproduct);

	public int getTotalReviews(ProductModel product);

}
