/**
 *
 */
package com.borngroup.ssl.core.dynamic.attribute.handlers;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

/**
 *
 * @author gokulpandey
 *
 */
public class SslCmsComponentLinkSortAttributeHandler implements DynamicAttributeHandler<String, CMSLinkComponentModel> {

    private static final Logger LOG = Logger.getLogger(SslCmsComponentLinkSortAttributeHandler.class);

    @Override
    public String get(final CMSLinkComponentModel model) {
        if (null != model) {
            return getSort(model);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.AbstractItemModel,
     * java.lang.Object)
     */
    @Override
    public void set(final CMSLinkComponentModel arg0, final String arg1) {
        // YTODO Auto-generated method stub

    }

    private String getSort(final CMSLinkComponentModel model) {
        if (null != model && null != model.getQuery()) {
            final String url = model.getQuery().trim();
            Map<String, String> attributes = null;
            try {
                attributes = new HashMap<>();
                final URIBuilder urlObj = new URIBuilder(url);
                final List<NameValuePair> queryParams = urlObj.getQueryParams();
                for (final NameValuePair pair : queryParams) {
                    attributes.put(pair.getName(), pair.getValue());
                }
            } catch (final Exception e) {
                LOG.error("Unable to parse the URL : " + url + ", error" + e);
            }
            if (null != attributes) {
                final String sort = attributes.get("sort");
                if (StringUtils.isNotEmpty(sort)) {
                    return sort;
                }
            }
        }
        return null;
    }

}
