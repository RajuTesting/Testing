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
public class SslCmsComponentLinkQueryAttributeHandler implements DynamicAttributeHandler<String, CMSLinkComponentModel> {

    private static final Logger LOG = Logger.getLogger(SslCmsComponentLinkQueryAttributeHandler.class);

    @Override
    public String get(final CMSLinkComponentModel model) {
        if (null != model) {
            return getFormattedQuery(model);
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

    private String getFormattedQuery(final CMSLinkComponentModel model) {
        if (null != model && null != model.getQuery()) {
            final String url = model.getQuery().trim();
            StringBuilder formattedQuery = null;
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
                formattedQuery = new StringBuilder();
                String query;
                query = attributes.get("q");
                if (null == query) {
                    query = attributes.get("query");
                }
                if (null != query) {
                    query.replace("::", ":");
                    if (query.charAt(0) != ':') {
                        formattedQuery.append(":");
                    }
                    formattedQuery.append(query);
                }
            }
            return formattedQuery != null ? formattedQuery.toString(): StringUtils.EMPTY;
        }
        return null;
    }

}
