/**
 *
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

/**
 * @author satyanarayana.naidu
 *
 */
public class FeedBackEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

	private String visitOptions;
	private String easeOfFindingInfo;
	private String productAvailability;
	private String varietyOfProducts;
	private String others;
	private String comments;

	/**
	 * @return the visitOptions
	 */
	public String getVisitOptions() {
		return visitOptions;
	}

	/**
	 * @param visitOptions
	 *            the visitOptions to set
	 */
	public void setVisitOptions(final String visitOptions) {
		this.visitOptions = visitOptions;
	}

	/**
	 * @return the easeOfFindingInfo
	 */
	public String getEaseOfFindingInfo() {
		return easeOfFindingInfo;
	}

	/**
	 * @param easeOfFindingInfo
	 *            the easeOfFindingInfo to set
	 */
	public void setEaseOfFindingInfo(final String easeOfFindingInfo) {
		this.easeOfFindingInfo = easeOfFindingInfo;
	}

	/**
	 * @return the productAvailability
	 */
	public String getProductAvailability() {
		return productAvailability;
	}

	/**
	 * @param productAvailability
	 *            the productAvailability to set
	 */
	public void setProductAvailability(final String productAvailability) {
		this.productAvailability = productAvailability;
	}

	/**
	 * @return the varietyOfProducts
	 */
	public String getVarietyOfProducts() {
		return varietyOfProducts;
	}

	/**
	 * @param varietyOfProducts
	 *            the varietyOfProducts to set
	 */
	public void setVarietyOfProducts(final String varietyOfProducts) {
		this.varietyOfProducts = varietyOfProducts;
	}

	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}

	/**
	 * @param others
	 *            the others to set
	 */
	public void setOthers(final String others) {
		this.others = others;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

}
