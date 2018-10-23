
package com.borngroup.ssl.core.search.solrfacetsearch.data;

/**
 * @author osheengulati
 *
 */
public class SimilarProductsData {

    String attribute;
    int boostValue;
    String action;

    public SimilarProductsData(final String attribute, final int boostValue, final String action) {
        this.attribute = attribute;
        this.boostValue = boostValue;
        this.action = action;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute
     *        the attribute to set
     */
    public void setAttribute(final String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return the boostValue
     */
    public int getBoostValue() {
        return boostValue;
    }

    /**
     * @param boostValue
     *        the boostValue to set
     */
    public void setBoostValue(final int boostValue) {
        this.boostValue = boostValue;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *        the action to set
     */
    public void setAction(final String action) {
        this.action = action;
    }
}
