/**
 *
 */
package com.borngroup.ssl.core.dao;

import java.util.List;

import com.borngroup.ssl.core.enums.ConfigurablePageType;
import com.borngroup.ssl.core.model.ConfigurablePageIndexModel;

/**
 * @author gokulpandey
 *
 */
public interface ConfigurablePageIndexDao {

    public ConfigurablePageIndexModel getIndexModel(ConfigurablePageType pageType);

    public List<ConfigurablePageIndexModel> getAllIndexModel();

}
