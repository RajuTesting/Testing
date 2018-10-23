package com.borngroup.ssl.core.dao.impl;

import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import com.borngroup.ssl.core.dao.PromotionalMessageInfoComponentdao;
import com.borngroup.ssl.core.model.PromotionalMessageInfoComponentModel;

public class DefaultPromtionalMessageInfoComponentdao implements PromotionalMessageInfoComponentdao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    public List<PromotionalMessageInfoComponentModel> getActivePromotionalMessage() {
        {
            final String queryString = //
            "SELECT {p:" + PromotionalMessageInfoComponentModel.PK
                    + "}" //
                    + "FROM {" + PromotionalMessageInfoComponentModel._TYPECODE + " AS p} " + "WHERE " + "{p:"
                    + PromotionalMessageInfoComponentModel.ISACTIVE + "}=?isActive ";//

            final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
            query.addQueryParameter("isActive", Boolean.TRUE);
            return flexibleSearchService.<PromotionalMessageInfoComponentModel> search(query).getResult();
        }
    }
}
