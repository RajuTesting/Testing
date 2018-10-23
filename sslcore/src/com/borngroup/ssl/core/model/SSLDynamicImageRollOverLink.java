/**
 *
 */
package com.borngroup.ssl.core.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.model.components.SSLImageLinkComponentModel;
import com.borngroup.ssl.core.model.components.SSLImageRollOverLinkComponentModel;
import com.borngroup.ssl.core.util.ActiveFromUntilDateComparator;
import com.borngroup.ssl.core.util.SSLComponentUtils;

/**
 * Created by harleen.chadha@nagarro.com
 *
 * @author SSL
 */
public class SSLDynamicImageRollOverLink implements DynamicAttributeHandler<SSLImageLinkComponentModel, SSLImageRollOverLinkComponentModel> {

    @Override
    public SSLImageLinkComponentModel get(final SSLImageRollOverLinkComponentModel component) {
        SSLImageLinkComponentModel sslImageLinkComponent = component.getDefaultSSLImageLink();
        if (CollectionUtils.isNotEmpty(component.getSslImageLinkList())) {
            final List<SSLImageLinkComponentModel> activeComponents = new ArrayList<>();
            for (final SSLImageLinkComponentModel imageLinkComponent : component.getSslImageLinkList()) {
                final boolean isActive = SSLComponentUtils.isComponentActive(imageLinkComponent.getActiveFrom(),
                        imageLinkComponent.getActiveUntil());
                if (isActive) {
                    activeComponents.add(imageLinkComponent);
                }
            }
            if (CollectionUtils.isNotEmpty(activeComponents)) {
                if (activeComponents.size() > 1) {
                    Collections.sort(activeComponents, new ActiveFromUntilDateComparator());
                }
                sslImageLinkComponent = activeComponents.get(0);
            }
        }
        return sslImageLinkComponent;
    }

    @Override
    public void set(final SSLImageRollOverLinkComponentModel component, final SSLImageLinkComponentModel value) {
        throw new UnsupportedOperationException();
    }

}
