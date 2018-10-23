/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pankajgandhi
 *
 */
@XmlRootElement(name = "Page")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListRequestDTO {

    @XmlAttribute(name = "StartRowNumber")
    private String startRowNumber;

    @XmlAttribute(name = "PaginationStrategy")
    private String paginationStrategy;

    @XmlAttribute(name = "PageSize")
    private String pageSize;

    @XmlElement(name = "API")
    private OrderListApiDTO apiData;

    /**
     * @return the startRowNumber
     */
    public String getStartRowNumber() {
        return startRowNumber;
    }

    /**
     * @param startRowNumber the startRowNumber to set
     */
    public void setStartRowNumber(final String startRowNumber) {
        this.startRowNumber = startRowNumber;
    }

    /**
     * @return the paginationStrategy
     */
    public String getPaginationStrategy() {
        return paginationStrategy;
    }

    /**
     * @param paginationStrategy the paginationStrategy to set
     */
    public void setPaginationStrategy(final String paginationStrategy) {
        this.paginationStrategy = paginationStrategy;
    }

    /**
     * @return the pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(final String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the apiData
     */
    public OrderListApiDTO getApiData() {
        return apiData;
    }

    /**
     * @param apiData the apiData to set
     */
    public void setApiData(final OrderListApiDTO apiData) {
        this.apiData = apiData;
    }

}
