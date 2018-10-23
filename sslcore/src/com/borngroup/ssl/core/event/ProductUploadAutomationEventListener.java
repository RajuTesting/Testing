/**
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.model.process.ProductUploadAutoApprovalStoreFrontProcessModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.events.ProductUploadAutomationEvent;

/*
 * Event listener for mail send event in product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public class ProductUploadAutomationEventListener extends AbstractSiteEventListener<ProductUploadAutomationEvent> {

    /** Log4j logger */
    private static final Logger LOG = Logger.getLogger(ProductUploadAutomationEventListener.class);

    /** JOB EMAIL PROCESS NAME */
    private static final String JOB_EMAIL_PROCESS_NAME = "productUploadAutomationCronjobResultEmailProcess";

    /** Model Service */
    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    @Resource
  	private MediaService mediaService;   
  	@Resource(name = "catalogVersionService")
  	private CatalogVersionService catalogVersionService;  
	@Resource(name = "flexibleSearchService") 
  	private FlexibleSearchService flexibleSearchService;

    @Override
    protected void onSiteEvent(final ProductUploadAutomationEvent productUploadAutomationEvent) {
        if (null != productUploadAutomationEvent) {
            LOG.info("################### ProductUploadAutomationEventListener onSiteEvent  start ##########");
            Map<String, Object> emailAttachment=new HashMap<>();
            if(CollectionUtils.isNotEmpty(productUploadAutomationEvent.getUrlForReport())){
            emailAttachment = getEmailAttachment(productUploadAutomationEvent.getUrlForReport());
            }
            final ProductUploadAutoApprovalStoreFrontProcessModel productUploadAutoProcessModel = this.getBusinessProcessService()
                    .createProcess(JOB_EMAIL_PROCESS_NAME + System.currentTimeMillis(), "productUploadAutomationEmailProcess",emailAttachment);
            productUploadAutoProcessModel.setSite(productUploadAutomationEvent.getSite());
            productUploadAutoProcessModel.setCronJobCode(productUploadAutomationEvent.getCronJobCode());
            productUploadAutoProcessModel.setStartTime(productUploadAutomationEvent.getStartTime().toString());
            productUploadAutoProcessModel.setCronJobResult(productUploadAutomationEvent.getCronJobResult().toString().toUpperCase());
            productUploadAutoProcessModel.setCronJobStatus(productUploadAutomationEvent.getCronJobStatus().toString().toUpperCase());
            //productUploadAutoProcessModel.setUrlForReport(productUploadAutomationEvent.getUrlForReport());
            
            if(CollectionUtils.isNotEmpty(productUploadAutomationEvent.getUrlForReport())){
            for (int i = 0; i < productUploadAutomationEvent.getUrlForReport().size(); i++) {	
            	switch (i) {
				case 0:
		           	 productUploadAutoProcessModel.setUrlForReport((String) productUploadAutomationEvent.getUrlForReport().get(i));
					break;
				case 1:
		           	 productUploadAutoProcessModel.setUrlForReport2((String)productUploadAutomationEvent.getUrlForReport().get(i));
					break;
				case 2:
		           	 productUploadAutoProcessModel.setUrlForReport3((String)productUploadAutomationEvent.getUrlForReport().get(i));
					break;
				default:
					break;
				}
			}
            }
            getModelService().save(productUploadAutoProcessModel);
            getBusinessProcessService().startProcess(productUploadAutoProcessModel);
            LOG.info("################### ProductUploadAutomationEventListener onSiteEvent end  ##########");
        } else {
            LOG.info("################### ProductUploadAutomationEventListener cannot start onSiteEvent : NULL #########");
        }
    }
    
    /**
	 * @param productUploadAutomationEvent
	 * @return
     * @throws IOException 
	 */
	private Map<String, Object> getEmailAttachment(List<String> productUploadAutomationEvent)  {
		final Map<String, Object> emailAttachmentsMap = new HashMap<>();
		final List<EmailAttachmentModel> emailAttachment=new ArrayList();
		for (String event : productUploadAutomationEvent) {
		Path filePath=Paths.get(event);
		//String emailAttachmentName="ProductAutoApproval"+ SslCoreConstants.PDF;
		String emailAttachmentName=filePath.getFileName().toString();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.setContentDispositionFormData(emailAttachmentName, emailAttachmentName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ByteArrayInputStream documentStream=null;
		try {
			documentStream = new ByteArrayInputStream(Files.readAllBytes(filePath));
		} catch (IOException e) {
			LOG.error("########## Error occurred while reading stream ########"+e.getMessage());
		}
		final DataInputStream documentInputStream = new DataInputStream(documentStream);
		final CatalogVersionModel activeCatalogVersion = catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME,
				SslCoreConstants.ONLINE_CATALOG_VERSION);
		emailAttachment.add(createEmailAttachment(documentInputStream,emailAttachmentName, SslCoreConstants.APPLICATION_XLSX, activeCatalogVersion,new Date()));
	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.error("##########"+e.getMessage());
		}
		}
		emailAttachmentsMap.put("productAutoApproval", emailAttachment);
		return emailAttachmentsMap;
	}

	/**
	 * @param documentInputStream
	 * @param emailAttachmentName
	 * @param applicationPdf
	 * @param activeCatalogVersion
	 * @param string
	 * @return
	 */
	private EmailAttachmentModel createEmailAttachment(
			DataInputStream documentInputStream, String emailAttachmentName,
			String mimeType, CatalogVersionModel activeCatalogVersion,
			Date emailAttachmentCode) {
		try {
			final EmailAttachmentModel exampleAttachment = modelService.create(EmailAttachmentModel.class);
			exampleAttachment.setCode(String.valueOf(emailAttachmentCode));
			exampleAttachment.setCatalogVersion(activeCatalogVersion);
			final EmailAttachmentModel attachmentSaved = flexibleSearchService.getModelByExample(exampleAttachment);
			if (null != attachmentSaved) {
				mediaService.setStreamForMedia(attachmentSaved, documentInputStream, emailAttachmentName, mimeType, getEmailAttachmentsMediaFolder());
				return attachmentSaved;
			}
		} catch (ModelNotFoundException | AmbiguousIdentifierException e) {
			// Attachment Not Found
			LOG.error( e.getMessage(),e);
		}
		try {
			final EmailAttachmentModel attachment = modelService.create(EmailAttachmentModel.class);
			attachment.setCode(String.valueOf(emailAttachmentCode));
			attachment.setMime(mimeType);
			attachment.setRealFileName(emailAttachmentName);
			attachment.setCatalogVersion(activeCatalogVersion);

			modelService.save(attachment);

			mediaService.setStreamForMedia(attachment, documentInputStream, emailAttachmentName, mimeType, getEmailAttachmentsMediaFolder());
			return attachment;
		} catch (final ModelSavingException e) {
			LOG.error("Error saving attachment model", e);
		}

		return null;
	}

	private MediaFolderModel getEmailAttachmentsMediaFolder() {
		return mediaService.getFolder("email-attachments");
	}
	
    @Override
    protected boolean shouldHandleEvent(final ProductUploadAutomationEvent productUploadAutomationEvent) {
        LOG.info("################### ProductUploadAutomationEventListener shouldHandleEvent  ##########");
        LOG.info("BaseSite from event : " + productUploadAutomationEvent.getSite());
        final BaseSiteModel site = productUploadAutomationEvent.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return SiteChannel.B2C.equals(site.getChannel());
    }

    /**
     * getter for ModelService
     *
     * @return the modelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * setter for ModelService
     *
     * @param modelService the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * getter for BusinessProcessService
     *
     * @return the businessProcessService
     */
    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    /**
     * setter for BusinessProcessService
     * 
     * @param businessProcessService the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }
  	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService() {
		return mediaService;
	}

	/**
	 * @param mediaService the mediaService to set
	 */
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService() {
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService the catalogVersionService to set
	 */
	public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
