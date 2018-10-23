/**
 *
 */
package com.borngroup.ssl.core.services;

import java.util.List;

import com.borngroup.ssl.core.enums.ConfigurablePageType;
import com.borngroup.ssl.core.model.ConfigurablePageIndexModel;

/**
 * @author gokulpandey
 *
 */
public interface ConfigurablePageIndexService {

    public List<ConfigurablePageIndexModel> getAllIndexModel();

    public ConfigurablePageIndexModel getIndexModel(final ConfigurablePageType pageType);

}
