/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import javax.annotation.Resource;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.dao.DealComponentDao;
import com.borngroup.ssl.core.enums.SortByEnum;
import com.borngroup.ssl.core.model.DealComponentModel;
import com.borngroup.ssl.core.services.DealComponentService;

/**
 * @author tejsharma
 *
 */
public class DealComponentServiceImpl implements DealComponentService {
    @Resource(name = "dealComponentDao")
    DealComponentDao dealComponentDao;

    /**
     * @return the dealComponentDao
     */
    public DealComponentDao getDealComponentDao() {
        return dealComponentDao;
    }

    @Override
    public DealComponentModel getDealById(final String dealId) {
        return getDealComponentDao().getDealById(dealId);
    }

    @Override
    public String getDealPageUrl(final DealComponentModel deal) {
        final SortByEnum sortBy = deal.getSortProductsBy();
        String sortByStr = null;
        if (sortBy.equals(SortByEnum.POPULARITY)) {
            sortByStr = SslCoreConstants.SORT_BY_TOPRATED;
        } else {
            sortByStr = SslCoreConstants.SORT_BY_DISCOUNT_DESC;
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("./search/?deal=");
        builder.append(deal.getUid());
        builder.append("&q=%3A");
        builder.append(sortByStr);
        builder.append("%3AinstockFlag%3Atrue");
        return builder.toString();
    }
}
