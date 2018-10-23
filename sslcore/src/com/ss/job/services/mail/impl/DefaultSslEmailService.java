/**
 *
 */
package com.ss.job.services.mail.impl;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.FccCrmTicketProcessModel;
import com.ss.job.services.mail.SslEmailService;

public class DefaultSslEmailService implements SslEmailService {

    Logger LOG = Logger.getLogger(DefaultSslEmailService.class);
    private BusinessProcessService businessProcessService;
    private ModelService modelService;
    private BaseSiteService baseSiteService;
    private CommonI18NService commonI18NService;

    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    public void setCommonI18NService(final CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    public void setBaseSiteService(final BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    @Override
    public void sendAbandonedNotificationEmail(final Set<CustomerModel> customers) {

        // final List<CustomerModel> filteredCustomerList = customers.;
        final Iterator<CustomerModel> iterator = customers.iterator();
        if (!customers.isEmpty()) {
            while (iterator.hasNext()) {
                final CustomerModel customerModel = iterator.next();

                final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel = (StoreFrontCustomerProcessModel) getBusinessProcessService()
                        .createProcess("sendAbondenedEmailProcess" + System.currentTimeMillis(), "sendAbondenedEmailProcess");
                storeFrontCustomerProcessModel.setCustomer(customerModel);
                storeFrontCustomerProcessModel.setSite(baseSiteService.getBaseSiteForUID("ssl"));
                // final storeFrontCustomerProcessModel.setCurrency(INR);
                // final BaseSiteService siteService = null;

                storeFrontCustomerProcessModel.setLanguage(commonI18NService.getLanguage("en"));
                modelService.save(storeFrontCustomerProcessModel);
                modelService.refresh(storeFrontCustomerProcessModel);
                getBusinessProcessService().startProcess(storeFrontCustomerProcessModel);

                LOG.info("SENDING EMAIL TO :" + customerModel.getName() + " EMAIL ID:" + customerModel.getContactEmail());
            }
        }
    }

    @Override
    public void sendAbandonedEmailToBusiness(final Set<CustomerModel> customers) {

        final StoreFrontCustomerProcessModel abandonedCartBusinessEmailProcess = (StoreFrontCustomerProcessModel) getBusinessProcessService()
                .createProcess("sendAbandonedBusinessEmailProcess" + System.currentTimeMillis(), "sendAbandonedBusinessEmailProcess");
        abandonedCartBusinessEmailProcess.setSite(baseSiteService.getBaseSiteForUID("ssl"));
        abandonedCartBusinessEmailProcess.setLanguage(commonI18NService.getLanguage("en"));

        modelService.save(abandonedCartBusinessEmailProcess);
        modelService.refresh(abandonedCartBusinessEmailProcess);
        getBusinessProcessService().startProcess(abandonedCartBusinessEmailProcess);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ss.job.services.mail.SslEmailService#sendFccCrmTicketEmail(de.hybris. platform.core.model.user.CustomerModel)
     */
    @Override
    public void sendFccCrmTicketEmail(final CustomerData customer, final String dirPath) {

        final FccCrmTicketProcessModel fccCrmTicketProcessModel = (FccCrmTicketProcessModel) getBusinessProcessService().createProcess(
                "fccCrmTicketEmailProcess" + System.currentTimeMillis(), "fccCrmTicketEmailProcess");

        fccCrmTicketProcessModel.setSite(baseSiteService.getBaseSiteForUID("ssl"));
        fccCrmTicketProcessModel.setLanguage(commonI18NService.getLanguage("en"));
        fccCrmTicketProcessModel.setFirstName(customer.getFirstName());
        fccCrmTicketProcessModel.setLastName(customer.getLastName());
        fccCrmTicketProcessModel.setEmail(customer.getUid());
        fccCrmTicketProcessModel.setMobileNo(customer.getContactMobile());
        fccCrmTicketProcessModel.setLoyaltyAddress(customer.getSslLoyaltyAddressData().getLine1());
        fccCrmTicketProcessModel.setLoyaltyDob(customer.getDob());
        fccCrmTicketProcessModel.setFilePath(dirPath);
        modelService.save(fccCrmTicketProcessModel);
        modelService.refresh(fccCrmTicketProcessModel);
        getBusinessProcessService().startProcess(fccCrmTicketProcessModel);

    }
}
