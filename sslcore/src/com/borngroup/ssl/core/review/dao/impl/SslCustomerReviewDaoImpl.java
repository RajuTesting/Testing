/**
 *
 */
package com.borngroup.ssl.core.review.dao.impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.dao.impl.DefaultCustomerReviewDao;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.review.dao.SslCustomerReviewDao;

/**
 * @author Viji
 *
 */
public class SslCustomerReviewDaoImpl extends DefaultCustomerReviewDao implements SslCustomerReviewDao {
    private static final Logger LOG = Logger.getLogger(SslCustomerReviewDaoImpl.class);
    final String reviewsQuery = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "baseProduct"
            + "}=?product";

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "pagedFlexibleSearchService")
    private PagedFlexibleSearchService pagedFlexibleSearchService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "searchRestrictionService")
    private SearchRestrictionService searchRestrictionService;

    @Override
    public Double getAveragQuality(final ProductModel product) {
        final String query = "SELECT avg({quality}) FROM {" + CustomerReviewModel._TYPECODE + "} WHERE {" + "baseProduct" + "} = ?product";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("product", product);
        flexibleSearchQuery.setResultClassList(Collections.singletonList(Double.class));
        final SearchResult<Double> result = getFlexibleSearchService().search(flexibleSearchQuery);
        return result.getResult().get(0);
    }

    @Override
    public Double getAveragRating(final ProductModel product) {
        final String query = "SELECT avg({rating}) FROM {" + CustomerReviewModel._TYPECODE + "} WHERE {" + "baseProduct"
                + "} = ?product and {rating} > 0";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("product", product);
        flexibleSearchQuery.setResultClassList(Collections.singletonList(Double.class));
        final SearchResult<Double> result = getFlexibleSearchService().search(flexibleSearchQuery);
        return result.getResult().get(0);
    }

    @Override
    public List<CustomerReviewModel> getReviewsForProduct(final ProductModel product) {
        final String query = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "baseProduct"
                + "}=?product ORDER BY {" + "creationtime" + "} DESC";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("product", product);
        fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));

        final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
        return searchResult.getResult();
    }

    @Override
    public List<CustomerReviewModel> getReviewsForProductAndLanguage(final ProductModel product, final LanguageModel language) {
        final String query = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "baseProduct"
                + "}=?product AND {" + "language" + "}=?language AND {rating} > 0 ORDER BY {" + "creationtime" + "} DESC";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("product", product);
        fsQuery.addQueryParameter("language", language);
        fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));

        final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
        return searchResult.getResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.dao.SslCustomerReviewDao#getReviews(java.util.List)
     */
    @Override
    @SuppressWarnings("boxing")
    public List<CustomerReviewModel> getReviews(final ProductModel productModel, final String[] selectedRatings) {
        final List<Double> ratingsList = new ArrayList<Double>();
        for (final String rating : selectedRatings) {
            final double selectedRating = Integer.parseInt(rating);
            if (selectedRating != 1) {
                ratingsList.add(selectedRating);
                ratingsList.add((selectedRating - 0.5));
            } else {
                ratingsList.add(selectedRating);
            }
        }
        final String query = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "baseProduct"
                + "}=?product AND {rating} IN (?ratings) ORDER BY {" + "creationtime" + "} DESC";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("product", productModel);
        fsQuery.addQueryParameter("ratings", ratingsList);
        fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));

        final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
        return searchResult.getResult();
    }

    @Override
    @SuppressWarnings("boxing")
    public SearchPageData<CustomerReviewModel> getPaginatedReviews(final ProductModel productModel, final String[] selectedRatings,
            final PaginationData paginationData) {
        String query = null;
        final Map<String, Object> queryParams = new HashMap<String, Object>();
        final List<Double> ratingsList = new ArrayList<Double>();
        if (null != selectedRatings && selectedRatings.length > 0) {
            for (final String rating : selectedRatings) {
                final double selectedRating = Integer.parseInt(rating);
                if (selectedRating != 1) {
                    ratingsList.add(selectedRating);
                    ratingsList.add((selectedRating - 0.5));
                } else {
                    ratingsList.add(selectedRating);
                }
            }
            query = reviewsQuery + " AND {rating} IN (?ratings) ORDER BY {" + "creationtime" + "} DESC";
            queryParams.put("ratings", ratingsList);
        }

        else {
            query = reviewsQuery + " AND {rating} > 0 ORDER BY {" + "creationtime" + "} DESC";
        }

        queryParams.put("product", productModel);
        final PageableData pageableData = new PageableData();
        pageableData.setCurrentPage(paginationData.getCurrentPage());
        pageableData.setSort(paginationData.getSort());
        pageableData.setPageSize(paginationData.getPageSize());

        final SearchPageData<CustomerReviewModel> searchResult = pagedFlexibleSearchService.search(query, queryParams, pageableData);

        return searchResult;

    }

    @Override
    public List<CustomerReviewModel> getReviewsForCurrentUser(final UserModel user) {
        final String query = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + CustomerReviewModel._TYPECODE + "} WHERE {"
                + CustomerReviewModel.USER + "}=?user AND {rating} > 0 ORDER BY {" + "creationtime" + "} DESC";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("user", user);
        fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));
        return sessionService.executeInLocalView(new SessionExecutionBody() {
            @Override
            public List<CustomerReviewModel> execute() {
                searchRestrictionService.disableSearchRestrictions();
                final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
                return searchResult.getResult();
            }
        });
    }

    @Override
    public List<CustomerReviewModel> getWeeklyProductReviews() {

        try {

            final Calendar cal = Calendar.getInstance();
            final Date endDate = cal.getTime();
            cal.add(Calendar.DATE, -1 * 7);
            final Date startDate = cal.getTime();

            final FlexibleSearchQuery query = new FlexibleSearchQuery(
                    "SELECT {cr.pk} FROM { customerreview as cr JOIN product as p " + "ON {cr.product}={p.pk} JOIN user as u "
                            + "ON {u.pk}={cr.user} JOIN enumerationvalue as ev on {ev.pk}={cr.approvalstatus} } "
                            + "WHERE {ev.code}='pending' and {cr.creationtime} >= ?startDate and {cr.creationtime} <= ?endDate "
                            + "order by {cr.creationtime}");

            query.addQueryParameter("startDate", startDate);
            query.addQueryParameter("endDate", endDate);

            final SearchResult<CustomerReviewModel> searchResult = flexibleSearchService.search(query);

            return searchResult.getResult();

        } catch (final Exception ex) {
            LOG.error("Error Message: " + ex.getMessage() + " Error cause: " + ex.getCause());
        }

        return ListUtils.EMPTY_LIST;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.review.dao.SslCustomerReviewDao# getReviewsForProductAndUser(de.hybris.platform.core.model.product.
     * ProductModel, de.hybris.platform.core.model.user.UserModel)
     */
    @Override
    public List<CustomerReviewModel> getReviewsForProductAndUser(final ProductModel product, final UserModel user) {
        final String query = "SELECT {" + CustomerReviewModel.PK + "} FROM {" + CustomerReviewModel._TYPECODE + "} WHERE {"
                + CustomerReviewModel.USER + "}=?user AND {" + CustomerReviewModel.BASEPRODUCT + "} =?product AND {rating} > 0 ORDER BY {"
                + "creationtime" + "} DESC";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("user", user);
        fsQuery.addQueryParameter("product", product);
        fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));
        return sessionService.executeInLocalView(new SessionExecutionBody() {
            @Override
            public List<CustomerReviewModel> execute() {
                searchRestrictionService.disableSearchRestrictions();
                final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
                return searchResult.getResult();
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.borngroup.ssl.core.review.dao.SslCustomerReviewDao#getTotalRecommendations(de.hybris.platform.core.model.product.ProductModel)
     */
    @Override
    public int getTotalRecommendations(final ProductModel product) {
		final String query = "SELECT * FROM {CustomerReview} WHERE {ISRECOMMENDED}=1 AND {BASEPRODUCT}=?product";
        final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
        fsQuery.addQueryParameter("product", product);
        final SearchResult searchResult = getFlexibleSearchService().search(fsQuery);
        return searchResult.getCount();
    }

    @Override
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Override
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

	@Override
	public int getTotalReviews(final ProductModel product) {
		final String query = "SELECT count(*) FROM {CustomerReview} WHERE {rating} > 0 AND {baseProduct}=?product";
		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.addQueryParameter("product", product);
		fsQuery.setResultClassList(Collections.singletonList(Integer.class));
		final SearchResult<Integer> result = this.flexibleSearchService.search(fsQuery);

		if (result.getCount() == 1) {
			return result.getResult().get(0).intValue();
		}
		return 0;
	}

}
