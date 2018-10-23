/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.WalletCreditsBulkUploadEvent;
import com.borngroup.ssl.core.services.SslWalletCreditsBulkUploadReportService;

/**
 * Service class implementation to provide methods to send errors reports.
 *
 * @author shilpaverma
 *
 */
public class SslWalletCreditsBulkUploadReportServiceImpl implements SslWalletCreditsBulkUploadReportService {

    /** Log4j logger. */
    private static final Logger LOG = Logger.getLogger(SslWalletCreditsBulkUploadReportServiceImpl.class);

    /** Base Site Service. */
    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    /** Event Service. */
    @Resource(name = "eventService")
    private EventService eventService;

    /** Catalog Version Service. */
    @Resource
    private CatalogVersionService catalogVersionService;

    /** Email Service. */
    @Resource(name = "defaultEmailService")
    private DefaultEmailService emailService;

    /** Search restriction service. **/
    @Resource
    private SearchRestrictionService searchRestrictionService;

    /**
     * method to send email containing error log file.
     *
     * @param file
     *        the file
     * @param uploadStatus
     *        the uploadStatus
     */
    @Override
    public void sendErrorReport(final File file, final String uploadStatus) throws IOException {
        setContentCatalogVersion();
        final InputStream inStream = new FileInputStream(file);

        final EmailAttachmentModel emailAttachment = emailService.createEmailAttachment(new DataInputStream(inStream), file.getName(),
                "text/csv");
        final WalletCreditsBulkUploadEvent walletCreditsBulkUploadEvent = this.initializeEvent(uploadStatus, emailAttachment.getCode());
        if (walletCreditsBulkUploadEvent != null && walletCreditsBulkUploadEvent.getSite() != null) {
            this.getEventService().publishEvent(walletCreditsBulkUploadEvent);
        } else {
            LOG.info("No base site available : Cannot publish mail send event.");
        }
        resetCatalogVersion();
    }

    /**
     * Event init method.
     *
     * @param uploadStatus
     *        the uploadStatus
     * @param reportAttachmentNo
     *        the reportAttachmentNo
     * @return mail event {@link WalletCreditsBulkUploadEvent}
     */
    private WalletCreditsBulkUploadEvent initializeEvent(final String uploadStatus, final String reportAttachmentNo) {
        final WalletCreditsBulkUploadEvent<BaseSiteModel> walletCreditsBulkUploadEvent = new WalletCreditsBulkUploadEvent();
        walletCreditsBulkUploadEvent.setUploadStatus(uploadStatus);
        walletCreditsBulkUploadEvent.setReportAttachmentCode(reportAttachmentNo);
        final Collection<BaseSiteModel> sites = this.getBaseSiteService().getAllBaseSites();
        if (CollectionUtils.isNotEmpty(sites)) {
            for (final BaseSiteModel bs : sites) {
                LOG.info("Setting site : " + bs.getName() + " to Wallet Credits Bulk Upload Event.");
                walletCreditsBulkUploadEvent.setSite(bs);
            }
        } else {
            LOG.info("No site availbale to set in Wallet Credits Bulk Upload Event.");
            return null;
        }

        return walletCreditsBulkUploadEvent;
    }

    /**
     * Getter for BaseSiteService.
     *
     * @return the baseSiteService
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * Getter for EventService.
     *
     * @return the eventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * Sets content Catalog Version in session.
     */
    private void setContentCatalogVersion() {
        searchRestrictionService.disableSearchRestrictions();
        catalogVersionService.setSessionCatalogVersion("sslContentCatalog", SslCoreConstants.CATALOG_VERSION);
        searchRestrictionService.enableSearchRestrictions();
    }

    /**
     * Sets Product Catalog Version in session.
     */
    private void resetCatalogVersion() {
        searchRestrictionService.disableSearchRestrictions();
        catalogVersionService.setSessionCatalogVersion(SslCoreConstants.CATALOG_NAME, "Online");
        searchRestrictionService.enableSearchRestrictions();
    }
}
