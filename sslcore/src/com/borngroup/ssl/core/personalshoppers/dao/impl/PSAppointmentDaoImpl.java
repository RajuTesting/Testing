/**
 *
 */
package com.borngroup.ssl.core.personalshoppers.dao.impl;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.borngroup.ssl.core.enums.PSAppointmentStatus;
import com.borngroup.ssl.core.model.PSAppointmentFeedbackModel;
import com.borngroup.ssl.core.model.PSAppointmentModel;
import com.borngroup.ssl.core.model.PSServicesModel;
import com.borngroup.ssl.core.personalshoppers.dao.PSAppointmentDao;

/**
 * @author S53299
 *
 */
public class PSAppointmentDaoImpl implements PSAppointmentDao {

    Logger log = Logger.getLogger(PSAppointmentDaoImpl.class);

    private FlexibleSearchService flexibleSearchService;

    private PagedFlexibleSearchService pagedFlexibleSearchService;

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public PagedFlexibleSearchService getPagedFlexibleSearchService() {
        return pagedFlexibleSearchService;
    }

    public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService) {
        this.pagedFlexibleSearchService = pagedFlexibleSearchService;
    }

    @Override
    public SearchPageData<PSAppointmentModel> searchAppointmentsPagewise(final PSAppointmentModel appointmentModel,
            final List<PSAppointmentStatus> appointmentStatusList, final boolean onlyRescheduledAppointments, final Long modelPk,
            final PageableData pageableData) {

        log.info("-----------------PSAppointmentDaoImpl/searchAppointmentsPagewise-------------------");

        final StringBuilder searchAppointmentquery = new StringBuilder();

        final Map<String, Object> queryParams = prepareSearchAppointmentQuery(searchAppointmentquery, appointmentModel,
                appointmentStatusList, onlyRescheduledAppointments, modelPk);

        final SearchPageData<PSAppointmentModel> searchResult = pagedFlexibleSearchService.search(searchAppointmentquery.toString(),
                queryParams, pageableData);

        return searchResult;
    }

    public Map<String, Object> prepareSearchAppointmentQuery(final StringBuilder searchAppointmentquery,
            final PSAppointmentModel appointmentModel, final List<PSAppointmentStatus> appointmentStatusList,
            final boolean onlyRescheduledAppointments, final Long modelPk) {

        final Map<String, Object> queryParams = new HashMap<String, Object>();

        boolean isWhereClause = false;

        searchAppointmentquery.append(" SELECT {" + PSAppointmentModel.PK + "} FROM {" + PSAppointmentModel._TYPECODE + "}");

        if (null != modelPk) {
            if (isWhereClause) {
                searchAppointmentquery.append(" and {" + PSAppointmentModel.PK + "} = ?appointmentPk ");
            } else {
                searchAppointmentquery.append(" where {" + PSAppointmentModel.PK + "} = ?appointmentPk ");

                isWhereClause = true;
            }

            queryParams.put("appointmentPk", modelPk);
        }

        if (null != appointmentModel) {

            if (!StringUtils.isEmpty(appointmentModel.getCode())) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CODE + "} = ?code ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CODE + "} = ?code ");

                    isWhereClause = true;
                }

                queryParams.put("code", appointmentModel.getCode());
            }

            if (null != appointmentModel.getAppoinmentStatus()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.APPOINMENTSTATUS + "} = ?appointmentStatus ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.APPOINMENTSTATUS + "} = ?appointmentStatus ");

                    isWhereClause = true;
                }
                queryParams.put("appointmentStatus", appointmentModel.getAppoinmentStatus());
            }

            if (null != appointmentModel.getStore()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.STORE + "} = ?store ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.STORE + "} = ?store ");

                    isWhereClause = true;
                }
                queryParams.put("store", appointmentModel.getStore());
            }

            if (null != appointmentModel.getServiceType()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.SERVICETYPE + "} = ?serviceType ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.SERVICETYPE + "} = ?serviceType ");

                    isWhereClause = true;
                }
                queryParams.put("serviceType", appointmentModel.getServiceType());
            }

            if (null != appointmentModel.getAppointmnetDate()) {

                if (isWhereClause) {
                    searchAppointmentquery
                            .append(" and to_char({" + PSAppointmentModel.APPOINTMNETDATE + "},'dd/mm/yyyy') = ?appointmentDate ");
                } else {
                    searchAppointmentquery
                            .append(" where to_char({" + PSAppointmentModel.APPOINTMNETDATE + "},'dd/mm/yyyy') = ?appointmentDate ");

                    isWhereClause = true;
                }
                queryParams.put("appointmentDate", new SimpleDateFormat("dd/MM/yyyy").format(appointmentModel.getAppointmnetDate()));
            }

            if (null != appointmentModel.getCustomerAge()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMERAGE + "} = ?customerAge ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMERAGE + "} = ?customerAge ");

                    isWhereClause = true;
                }
                queryParams.put("customerAge", appointmentModel.getCustomerAge());
            }

            if (!StringUtils.isEmpty(appointmentModel.getCustomerName())) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMERNAME + "} = ?customerName ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMERNAME + "} = ?customerName ");

                    isWhereClause = true;
                }
                queryParams.put("customerName", appointmentModel.getCustomerName());
            }

            if (!StringUtils.isEmpty(appointmentModel.getCustomerEmail())) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMEREMAIL + "} = ?customerEmail ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMEREMAIL + "} = ?customerEmail ");

                    isWhereClause = true;
                }
                queryParams.put("customerEmail", appointmentModel.getCustomerEmail());
            }

            if (!StringUtils.isEmpty(appointmentModel.getCustomerMobileNo())) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMERMOBILENO + "} = ?customerMobno ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMERMOBILENO + "} = ?customerMobno ");

                    isWhereClause = true;
                }
                queryParams.put("customerMobno", appointmentModel.getCustomerMobileNo());
            }

            if (null != appointmentModel.getCustomerGender()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMERGENDER + "} = ?customerGender ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMERGENDER + "} = ?customerGender ");

                    isWhereClause = true;
                }
                queryParams.put("customerGender", appointmentModel.getCustomerGender());
            }

            if (null != appointmentModel.getPersonalShopper()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.PERSONALSHOPPER + "} = ?personalShopper ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.PERSONALSHOPPER + "} = ?personalShopper ");

                    isWhereClause = true;
                }
                queryParams.put("personalShopper", appointmentModel.getPersonalShopper());
            }

            if (null != appointmentModel.getParentAppointment()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.PARENTAPPOINTMENT + "} = ?parentAppointment ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.PARENTAPPOINTMENT + "} = ?parentAppointment ");

                    isWhereClause = true;
                }
                queryParams.put("parentAppointment", appointmentModel.getParentAppointment());
            } else {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.PARENTAPPOINTMENT + "} IS  NULL ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.PARENTAPPOINTMENT + "} IS  NULL ");

                    isWhereClause = true;
                }

            }

            if (!StringUtils.isEmpty(appointmentModel.getDescription())) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.DESCRIPTION + "} = ?description ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.DESCRIPTION + "} = ?description ");

                    isWhereClause = true;
                }
                queryParams.put("description", appointmentModel.getDescription());
            }

            if (null != appointmentModel.getCustomerFeedback()) {

                if (isWhereClause) {
                    searchAppointmentquery.append(" and {" + PSAppointmentModel.CUSTOMERFEEDBACK + "} = ?customerFeedback ");
                } else {
                    searchAppointmentquery.append(" where {" + PSAppointmentModel.CUSTOMERFEEDBACK + "} = ?customerFeedback ");

                    isWhereClause = true;
                }
                queryParams.put("customerFeedback", appointmentModel.getCustomerFeedback());
            }
        }

        if (!CollectionUtils.isEmpty(appointmentStatusList)) {
            if (isWhereClause) {
                searchAppointmentquery.append(" and {" + PSAppointmentModel.APPOINMENTSTATUS + "} IN  (?appointmentStatuses) ");
            } else {
                searchAppointmentquery.append(" where {" + PSAppointmentModel.APPOINMENTSTATUS + "} IN (?appointmentStatuses) ");

                isWhereClause = true;
            }

            queryParams.put("appointmentStatuses", appointmentStatusList);
        }

        if (onlyRescheduledAppointments) {

            if (isWhereClause) {
                searchAppointmentquery.append(" and {" + PSAppointmentModel.PARENTAPPOINTMENT + "} IS NOT NULL");
            } else {
                searchAppointmentquery.append(" where {" + PSAppointmentModel.PARENTAPPOINTMENT + "}  IS NOT NULL ");

                isWhereClause = true;
            }

        }

        searchAppointmentquery.append(" order by {" + PSAppointmentModel.MODIFIEDTIME + "}  DESC ");

        log.info("searchAppointmentquery  : " + searchAppointmentquery);
        log.info("searchAppointmentquery queryParams : " + queryParams);

        return queryParams;
    }

    public String getQueryClause(final boolean isWhereClauseUsed) {
        if (isWhereClauseUsed) {
            return " AND ";
        } else {
            return " WHERE ";
        }
    }

    @Override
    public SearchPageData<PSServicesModel> searchPersonalShopperServicesPagewise(final PSServicesModel servicesModel, final Long modelPk,
            final PageableData pageableData) {
        log.info("-----------------PSAppointmentDaoImpl/searchPersonalShopperServicesPagewise-------------------");

        final StringBuilder searchServicesquery = new StringBuilder();

        final Map<String, Object> queryParams = preparePersonalShopperServicesQuery(searchServicesquery, servicesModel, modelPk);

        final SearchPageData<PSServicesModel> searchResult = pagedFlexibleSearchService.search(searchServicesquery.toString(), queryParams,
                pageableData);

        return searchResult;

    }

    private Map<String, Object> preparePersonalShopperServicesQuery(final StringBuilder searchServicesquery,
            final PSServicesModel servicesModel, final Long modelPk) {

        log.info("--------------preparePersonalShopperServicesQuery------------------");

        final Map<String, Object> queryParams = new HashMap<String, Object>();

        // boolean isWhereClause = false;

        searchServicesquery.append(" SELECT {" + PSServicesModel.PK + "} FROM {" + PSServicesModel._TYPECODE + "}");

        if (null != modelPk) {
            searchServicesquery.append(" where {" + PSServicesModel.PK + "} = ?servicePk ");
            queryParams.put("servicePk", modelPk);
        }

        if (null != servicesModel) {
            // need to write

        }

        return queryParams;
    }

    @Override
    public SearchPageData<PSAppointmentFeedbackModel> searchAppointmentFeedbackByPage(final PageableData pageableData) {
        log.info("-----------------PSAppointmentDaoImpl/searchAppointmentFeedbackByPage-------------------");
        final StringBuilder queryForFeedbacks = new StringBuilder();
        queryForFeedbacks.append(" SELECT {" + PSAppointmentFeedbackModel.PK + "} FROM {" + PSAppointmentFeedbackModel._TYPECODE + "} ");
        queryForFeedbacks.append(" order by {" + PSAppointmentFeedbackModel.MODIFIEDTIME + "} desc ");
        final Map<String, Object> queryParams = new HashMap<>();

        final SearchPageData<PSAppointmentFeedbackModel> searchResult = pagedFlexibleSearchService.search(queryForFeedbacks.toString(),
                queryParams, pageableData);

        return searchResult;
    }

    @Override
    public List<Object> getAppointmentsCount() {
        // log.info("-----------------PSAppointmentDaoImpl/getAppointmentsCount-------------------");

        final StringBuilder queryForAppointmentsCount = new StringBuilder();

        queryForAppointmentsCount.append(" select  ");
        queryForAppointmentsCount.append(" sum( CASE WHEN {e.code} IN ('PENDING','CONFIRMED')  THEN 1 ELSE 0 END )AS PEDING, ");
        queryForAppointmentsCount.append(" sum( CASE  WHEN {e.code} = 'COMPLETED' THEN 1 ELSE 0 END )AS COMPLETED, ");
        queryForAppointmentsCount
                .append(" sum( CASE  WHEN {e.code} = 'PENDING' AND {p.parentappointment} IS NOT NULL THEN 1 ELSE 0 END )AS RESCHEDULED  ");
        queryForAppointmentsCount.append(" FROM {PSAppointment  as p join Enumerationvalue as e on {p:appoinmentStatus}={e:pk} }");

        // log.info("queryForAppointmentsCount query " +
        // queryForAppointmentsCount);

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryForAppointmentsCount);

        query.setResultClassList(Arrays.asList(String.class, String.class, String.class));

        final SearchResult<List<Object>> searchResult = flexibleSearchService.search(query);

        List<Object> result = new ArrayList<>();

        if (!CollectionUtils.isEmpty(searchResult.getResult())) {
            result = searchResult.getResult().get(0);
        }

        return result;
    }

}
