/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.SSLMobileContentPageDao;
import com.borngroup.ssl.core.model.SSLMobileContentPageModel;
import com.borngroup.ssl.core.services.SSLMobileContentPageService;

/**
 * @author gokulpandey
 *
 */
public class SSLMobileContentPageServiceImpl implements SSLMobileContentPageService {

    @Resource(name = "ssLMobileContentPageDao")
    private SSLMobileContentPageDao ssLMobileContentPageDao;

    @Override
    public List<SSLMobileContentPageModel> getAllMobileContentPages() {
        return ssLMobileContentPageDao.getAllMobileContentPages();
    }

    /**
     * @param ssLMobileContentPageDao
     *        the ssLMobileContentPageDao to set
     */
    public void setSsLMobileContentPageDao(final SSLMobileContentPageDao ssLMobileContentPageDao) {
        this.ssLMobileContentPageDao = ssLMobileContentPageDao;
    }

}
