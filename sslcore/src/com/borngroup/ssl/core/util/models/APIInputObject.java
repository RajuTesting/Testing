/**
 *
 */
package com.borngroup.ssl.core.util.models;

import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;


/**
 * @author t.balagopalan
 *
 */
public class APIInputObject
{
    private String queryString;

    private String serializedPostData;

    private APIMethodEnum method;

    private ContentTypeEnum contentType;

    private String url;

    /**
     * This may be needed only for ContentTypeEnum.JSON. This indicates if the deserialized response object class has an
     * attribute decoration as JsonRootName. This means that the properties will be enclosed as an object and assigned
     * to an attribute, which is the rootname.
     *
     * For example: { "ResponseRootName": { "Property1": "value1", "Property2": "value2" }}
     */
    private boolean responseRootElementForJSON;

    /**
     * for Basic Authorization
     */
    private String basicAuthorization;
    
    public String getQueryString()
    {
        return queryString;
    }

    public void setQueryString(final String queryString)
    {
        this.queryString = queryString;
    }

    /**
     * @return the serializedPostData
     */
    public String getSerializedPostData()
    {
        return serializedPostData;
    }

    /**
     * @param serializedPostData
     *            the serializedPostData to set
     */
    public void setSerializedPostData(final String serializedPostData)
    {
        this.serializedPostData = serializedPostData;
    }

    public APIMethodEnum getMethod()
    {
        return method;
    }

    public void setMethod(final APIMethodEnum method)
    {
        this.method = method;
    }

    public ContentTypeEnum getContentType()
    {
        return contentType;
    }

    public void setContentType(final ContentTypeEnum contentType)
    {
        this.contentType = contentType;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    /**
     * @return the responseRootElementForJSON
     */
    public boolean isResponseRootElementForJSON()
    {
        return responseRootElementForJSON;
    }

    /**
     * @param responseRootElementForJSON
     *            the responseRootElementForJSON to set
     */
    public void setResponseRootElementForJSON(final boolean responseRootElementForJSON)
    {
        this.responseRootElementForJSON = responseRootElementForJSON;
    }

    /**
	 * @return the basicAuthorization
	 */
	public String getBasicAuthorization() {
		return basicAuthorization;
	}

	/**
	 * @param basicAuthorization
	 *            the basicAuthorization to set
	 */
	public void setBasicAuthorization(final String basicAuthorization) {
		this.basicAuthorization = basicAuthorization;
	}
	
    @Override
    public String toString()
    {
        return "ClassPojo [queryString = " + queryString + ", serializedPostData = " + serializedPostData + ", method = " + method
                + ", contentType = " + contentType + ", url = " + url + ", responseRootElementForJSON = "
                + responseRootElementForJSON + ", basicAuthorization="+basicAuthorization+"]";
    }
}
