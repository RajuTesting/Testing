/**
 *
 */
package com.borngroup.ssl.core.services;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.borngroup.ssl.core.data.ProductUploadValidationDTO;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/*
 * Service class interface to provide methods to product upload automation cronjobs
 *
 * mailId : ashish.sabal@nagarro.com
 *
 * @author ashishsabal
 *
 */
public interface ProductUploadAutomationService {

    /**
     * @param catalogVersionModel
     * @param check
     * @return List of Apparel Products with provided status
     */
    List<ApparelProductModel> getProductsWithStatus(CatalogVersionModel catalogVersionModel, ArticleApprovalStatus check);

    /**
     * @param paramT
     * @param jobType
     * @param workbook
     * @return fileName
     */
    String sendErrorReport(CronJobModel paramT, String jobType, Workbook workbook,String reportType);

    /**
     * @param recordsList
     * @param paramT
     * @param jobType
     * @param reportType
     */
    String generateSendXlsReport(List<ProductUploadValidationDTO> recordsList, CronJobModel paramT, String jobType,String reportType);
    

    /**
     * @param catalogVersionModel
     * @param approvalStatus
     * @return List of Apparel Style Variant Products with provided Size variant status
     */
    List<ApparelStyleVariantProductModel> getStyleVariantsWithSizeVariantStatus(CatalogVersionModel catalogVersionModel, ArticleApprovalStatus approvalStatus);

	/**
	 * @param code
	 * @param startTime
	 * @param success
	 * @param finished
	 * @param fileNames
	 */
	void sendErrorReport(String code, Date startTime, CronJobResult success, CronJobStatus finished, List<String> fileNames);
    
    
}
