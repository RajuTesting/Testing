/**
 *
 */
package com.borngroup.ssl.core.review.dao;

import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.dao.CustomerReviewDao;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

import java.util.List;

/**
 * @author Viji
 *
 */
public interface SslCustomerReviewDao extends CustomerReviewDao {

    public Double getAveragQuality(ProductModel productModel);

    public List<CustomerReviewModel> getReviews(ProductModel productModel, String[] selectedRatings);

    public Double getAveragRating(ProductModel productModel);

    public SearchPageData<CustomerReviewModel> getPaginatedReviews(ProductModel productModel, String[] selectedRatings,
            PaginationData paginationData);

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
    public List<CustomerReviewModel> getReviewsForProductAndUser(ProductModel product, UserModel user);

    public int getTotalRecommendations(ProductModel product);

	public int getTotalReviews(ProductModel product);

}
