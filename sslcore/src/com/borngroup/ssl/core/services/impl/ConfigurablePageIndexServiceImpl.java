/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.List;

import com.borngroup.ssl.core.dao.ConfigurablePageIndexDao;
import com.borngroup.ssl.core.enums.ConfigurablePageType;
import com.borngroup.ssl.core.model.ConfigurablePageIndexModel;
import com.borngroup.ssl.core.services.ConfigurablePageIndexService;

/**
 * @author gokulpandey
 *
 */
public class ConfigurablePageIndexServiceImpl implements ConfigurablePageIndexService {

    private ConfigurablePageIndexDao configurablePageIndexDao;

    @Override
    public List<ConfigurablePageIndexModel> getAllIndexModel() {
        return configurablePageIndexDao.getAllIndexModel();

    }

    @Override
    public ConfigurablePageIndexModel getIndexModel(final ConfigurablePageType pageType) {
        return configurablePageIndexDao.getIndexModel(pageType);
    }

    /**
     * @param configurablePageIndexDao
     *        the configurablePageIndexDao to set
     */
    public void setConfigurablePageIndexDao(final ConfigurablePageIndexDao configurablePageIndexDao) {
        this.configurablePageIndexDao = configurablePageIndexDao;
    }

}
