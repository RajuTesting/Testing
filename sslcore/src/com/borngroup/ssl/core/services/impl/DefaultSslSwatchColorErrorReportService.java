/**
 * 
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.VelocityContext;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.context.SwatchColorErrorEmailContext;
import com.borngroup.ssl.core.email.services.HtmlEmailDTO;
import com.borngroup.ssl.core.email.services.HtmlMailSender;
import com.borngroup.ssl.core.services.SslSwatchColorErrorReportService;
import com.borngroup.ssl.facades.data.SwatchColorErrorData;

/**
 * @author Techouts
 *
 */
public class DefaultSslSwatchColorErrorReportService implements SslSwatchColorErrorReportService {
    private static final Logger LOG = Logger.getLogger(DefaultSslSwatchColorErrorReportService.class);
	@Resource(name = "htmlMailSender")
	private HtmlMailSender htmlMailSender;
	@Override
	public synchronized void generateSendErorsXlsReport(SwatchColorErrorData data, boolean isJobTriggered)
			throws Exception
	{
		if (isJobTriggered)
		{
			this.sendXlsReport();
		}
		else
		{
			this.generateXlsReport(data);
		}
	}

	/**
	 * @Description this method used to send the swatch color errors  Report
	 * @throws Exception
	 */
	private void sendXlsReport() throws Exception
	{
		try
		{
			final String folderpath = SslCoreConstants.TEMP_DIR + File.separatorChar + SslCoreConstants.LOG_DIR_NAME;
			final String reportPath = folderpath + File.separatorChar + SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT
					+ SslCoreConstants.XLSX;
			MutableBoolean isMailSent = new MutableBoolean(false);
			final File report = new File(reportPath);
			if (report.exists() && !report.isDirectory())
			{

				File file = renameXls(report, new File(folderpath, SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT + "_"
						+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + SslCoreConstants.XLSX));
				this.sendMail(file, isMailSent);
				if (isMailSent.booleanValue())
				{
					file = new File(file.getAbsolutePath());
					if (!file.delete())
					{
						LOG.error("Unable to delete the file due to file locked by another resource");
					}
				}
				else
				{
					renameXls(new File(folderpath, SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT + "_"
							+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + SslCoreConstants.XLSX), report);
				}
			}
			else
			{
				LOG.error("No file found to send the report");
			}
		}
		catch (Exception error)
		{
			LOG.error("Exception occurred while sending mail" + error.getCause());
			throw error;
		}

	}

	/**
	 * @Description this method used to rename the fromFile to toFile
	 * @param fromFile
	 * @param toFile
	 * @return File
	 */
	private File renameXls(File fromFile, File toFile)
	{
		if (!fromFile.renameTo(toFile))
		{
			LOG.error("Unable to rename the file " + fromFile.getName() + "to " + toFile.getName());
		}
		return toFile;

	}

	/**
	 * @Description this method used to send the swatch color errors  Report to configured mail id's
	 * @param file
	 * @param isMailSent
	 * @return
	 * @throws Exception
	 */
	private void sendMail(File file, MutableBoolean isMailSent) throws Exception
	{
		int maxRowCount = Config.getInt("ssl.mail.mdm.productupload.maxrowcount", 20);
		List<SwatchColorErrorData> data = new ArrayList<>();
		Workbook workbook = null;
		HtmlEmailDTO emailBody;
		try (FileInputStream inputStream = new FileInputStream(file))
		{
			workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			int rowCount = firstSheet.getPhysicalNumberOfRows() - 1;
			LOG.info("Execution started for mail triggering");
			if (rowCount <= maxRowCount)
			{
				this.prepareData(firstSheet, data);
			}
			final VelocityContext successMailGeneration = new SwatchColorErrorEmailContext(data, rowCount, maxRowCount);
			emailBody = htmlMailSender.createHtmlEmail(
					successMailGeneration,
					SslCoreConstants.SWATCHCOLOR_ERROR_EMAIL_BODY,
					SslCoreConstants.SWATCHCOLOR_ERROR_EMAIL_SUB + "_"
							+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()),
							Config.getParameter("ssl.mail.swatchcolor.error.to"), StringUtils.EMPTY,
							Config.getParameter("ssl.mail.swatchcolor.error.cc"), StringUtils.EMPTY,
							Config.getParameter("ssl.mail.swatchcolor.error.from"));
			if (rowCount > maxRowCount)
			{
				DataSource dataSource = new FileDataSource(file);
				emailBody.getHtmlEmail().attach(dataSource, file.getName(), file.getName(), "attachment");
			}
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Mail triggering process is about to invoke in next step");
			}
			emailBody.getHtmlEmail().send();
			isMailSent.setValue(true);
		}
		catch (Exception ex)
		{
			LOG.error("Exception occurred while sending mail" + ex.getCause());
			throw ex;
		}
	}

	/**
	 * @Description this method used to prepare the Data object from the Sheet
	 * @param firstSheet
	 * @param data
	 */
	private void prepareData(Sheet firstSheet, List<SwatchColorErrorData> data)
	{
		Iterator<Row> iterator = firstSheet.iterator();
		if (iterator.hasNext())
		{
			iterator.next();
		}
		while (iterator.hasNext())
		{
			SwatchColorErrorData dataEntry = new SwatchColorErrorData();
			Row nextRow = iterator.next();
			dataEntry.setSkuCode(nextRow.getCell(0).getStringCellValue());
			dataEntry.setColorCode(nextRow.getCell(1).getStringCellValue());
			dataEntry.setColorFamily(nextRow.getCell(2).getStringCellValue());
			dataEntry.setErrorMessage(nextRow.getCell(3).getStringCellValue());
			dataEntry.setEventDate(nextRow.getCell(4).getStringCellValue());
			data.add(dataEntry);
		}
	}

	/**
	 * @Description this method used to generate the swatch color errors  Report
	 * @param records
	 * @throws Exception
	 */
	public void generateXlsReport(SwatchColorErrorData records) throws Exception
	{
		File file = getErrorReport(SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT);
		Workbook workbook = null;
		try (FileInputStream input = new FileInputStream(file))
		{

			workbook = new XSSFWorkbook(input);
			final Sheet sheet = workbook.getSheet(SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT);
			this.addErrorRecordsToXlsFile(file, records, workbook, sheet);
		}
		catch (Exception e)
		{
			LOG.error("Exception occured while generating Mdm product validation Failed report" + e.getCause());
			throw e;
		}
	}

	/**
	 * @Description this method used to create file with given reportName
	 * @param reportName
	 * @return File
	 */
	public File getErrorReport(String reportName)
	{
		File fileObj = null;
		File folderPath = null;
		FileOutputStream out = null;
		try
		{
			final String logFileParentDir = SslCoreConstants.TEMP_DIR + File.separatorChar + SslCoreConstants.LOG_DIR_NAME;
			final String xlsFileName = logFileParentDir + File.separatorChar + reportName + SslCoreConstants.XLSX;
			fileObj = new File(xlsFileName);
			folderPath = new File(logFileParentDir);
			if (!folderPath.exists())
			{
				folderPath.mkdir();
			}
			if (!fileObj.exists())
			{
				final Workbook workbook = new XSSFWorkbook();
				if (!fileObj.createNewFile())
				{
					LOG.error("Unable to create the file " + fileObj);
				}
				out = new FileOutputStream(fileObj);
				final Sheet sheet = workbook.createSheet(SslCoreConstants.SWATCHCOLOR_ERROR_VARIANTS_REPORT);
				this.createHeader(sheet, workbook);
				workbook.write(out);

			}
		}
		catch (final Exception e)
		{
			LOG.error("Exception occured while creating excel file" + fileObj + " Reason: " + e.getCause());
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
			}
			catch (IOException e)
			{
				LOG.error("Exception occured while closing excel file Reason: " + e.getCause());
			}
		}
		return fileObj;
	}

	/**
	 * @Description this method used to create the Header to generated report
	 * @param sheet
	 * @param workbook
	 */
	private void createHeader(final Sheet sheet, Workbook workbook)
	{
		CellStyle style = workbook.createCellStyle();//Create style
		Font font = workbook.createFont();//Create font
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
		style.setFont(font);//set it to bold
		Row row = null;
		Cell cell = null;

		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(SslCoreConstants.STYLE_VARAINT_CODE);
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue(SslCoreConstants.COLOR_CODE);
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue(SslCoreConstants.COLOR_FAMILY);
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue(SslCoreConstants.ERROR_MSG);
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue(SslCoreConstants.DATE_TIME);
		cell.setCellStyle(style);
	}

	/**
	 * @Description this method used to add the swatch color errors  
	 * @param file
	 * @param records
	 * @param workbook
	 * @param sheet
	 */
	public void addErrorRecordsToXlsFile(File file, final SwatchColorErrorData records, Workbook workbook, Sheet sheet)
	{
		Row row = null;
		int rowCount = sheet.getPhysicalNumberOfRows();
		try (FileOutputStream outputStream = new FileOutputStream(file))
		{
			int startRowCount = rowCount;
				row = sheet.createRow(startRowCount);
				row.createCell(0).setCellValue(records.getSkuCode());
				row.createCell(1).setCellValue(records.getColorCode());
				row.createCell(2).setCellValue(records.getColorFamily());
				row.createCell(3).setCellValue(records.getErrorMessage());
				row.createCell(4).setCellValue(records.getEventDate());
			sheet.autoSizeColumn((short) 1);
			workbook.write(outputStream);
		}
		catch (Exception e)
		{
			LOG.error("Exception occured while writing the data into excel file Reason: " + e.getCause());

		}

	}
}