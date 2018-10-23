/**
 *
 */
package com.borngroup.ssl.core.services.url.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.url.impl.AbstractUrlResolver;
import de.hybris.platform.site.BaseSiteService;

import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.model.SSLCollectionsModel;

/**
 * The Class SSLCollectionsModelUrlResolver is used to resolve the collections URL.
 *
 * @author harleenchadha
 */
public class SSLCollectionsModelUrlResolver extends AbstractUrlResolver<SSLCollectionsModel> {

    /** The cache key. */
    private final String cacheKey = SSLCollectionsModelUrlResolver.class.getName();

    /** The base site service. */
    private BaseSiteService baseSiteService;

    /** The pattern. */
    private String pattern;

    @Override
    protected String resolveInternal(final SSLCollectionsModel sslCollectionsModel) {
        final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
        String url = getPattern();

        if ((currentBaseSite != null) && (url.contains("{baseSite-uid}"))) {
            url = url.replace("{baseSite-uid}", currentBaseSite.getUid());
        }
        if (url.contains("{collections-name}")) {
            url = url.replace("{collections-name}", urlSafe(sslCollectionsModel.getName()));
        }
        if (url.contains("{collections-code}")) {
            url = url.replace("{collections-code}", sslCollectionsModel.getCode());
        }
        return url;
    }

    @Override
    protected String urlSafe(final String text) {
        if ((text == null) || (text.isEmpty())) {
            return "";
        }
        String cleanedText = text.toLowerCase().replaceAll("[^\\w]", "-");
        cleanedText = cleanedText.replaceAll("-+", "-");
        return cleanedText;
    }

    /**
     * Gets the base site service.
     *
     * @return the baseSiteService
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * Sets the base site service.
     *
     * @param baseSiteService
     *        the baseSiteService to set
     */
    @Required
    public void setBaseSiteService(final BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /**
     * Gets the pattern.
     *
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the pattern.
     *
     * @param pattern
     *        the pattern to set
     */
    @Required
    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * Gets the cache key.
     *
     * @param source
     *        the source
     * @return the cACHE_KEY
     */
    @Override
    public String getKey(final SSLCollectionsModel source) {
        return this.cacheKey + "." + source.getPk().toString();
    }
}