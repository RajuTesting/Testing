/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.borngroup.ssl.core.product.service.impl.SslDefaultProductService;
import com.google.common.collect.Iterables;

/**
 * @author techouts
 *
 */
public class SSLMediaDownloadCronJob extends AbstractJobPerformable<CronJobModel> {

	/** Log4j Logger. **/
	private static final Logger LOG = Logger.getLogger(SSLMediaDownloadCronJob.class);

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "sslProductService")
	private SslDefaultProductService sslProductService;

	private static final String MEDIA_DOWNLOAD_CSV_FILEPATH = "media.download.csv.filepath";
	private static final String MEDIA_DOWNLOAD_FORMAT = "media.format";
	private static final String MEDIA_DOWNLOAD_RECORDS_LIMIT = "media.download.csv.records.limit";
	private static final String MEDIA_DOWNLOAD_PATH = "media.download.path";
	private static final String MEDIA_DOWNLOAD_CSV_FILE_HEADER = "Style Number";
	private static final String MEDIA_DOWNLOAD_CSV_FILE_EXTENSION = ".csv";
	private static final String MEDIA_DOWNLOAD_CSV_ARCHIVE_FOLDERNAME = "Archive";
	private static final String MEDIA_DOWNLOAD_REPORTS_EXTENSION = ".xlsx";

	@Override
	public PerformResult perform(final CronJobModel arg0) {
		LOG.info("#######SSLMediaDownloadCronJob Started########");
		try {
			final File[] listOfFiles = getSpecificExtensionFilesFromSpecifiedFolder(
					Config.getParameter(MEDIA_DOWNLOAD_CSV_FILEPATH), MEDIA_DOWNLOAD_CSV_FILE_EXTENSION);

			if (ArrayUtils.isEmpty(listOfFiles)) {
				LOG.warn("CSV File Not Found in the specified path");
				return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
			}
			for (final File file : listOfFiles) {
				final File imageFolder = createFolder(Config.getParameter(MEDIA_DOWNLOAD_PATH),
						file.getName().split("\\.")[0]);
				final List<String> styleNumberList = getStyleNumbersFromCsvFile(file, MEDIA_DOWNLOAD_CSV_FILE_HEADER);

				if (null != imageFolder && imageFolder.isDirectory() && CollectionUtils.isNotEmpty(styleNumberList)) {
					LOG.info("style code count---->" + styleNumberList.size());
					final List<ProductModel> styleVariantProductList = new ArrayList();
					final int partitionSize = Config.getInt(MEDIA_DOWNLOAD_RECORDS_LIMIT, 1000);
					for (final List<String> partitionList : Iterables.partition(styleNumberList, partitionSize)) {
						getProducts(partitionList, styleVariantProductList);
					}
					processMediaDownload(styleVariantProductList, imageFolder.getAbsolutePath());

					// archiving and moving csv file to a specific location
					if (imageFolder.listFiles().length > 0) {
						final File csvArhiveFolder = createFolder(Config.getParameter(MEDIA_DOWNLOAD_PATH),
								MEDIA_DOWNLOAD_CSV_ARCHIVE_FOLDERNAME);
						if (null != csvArhiveFolder && csvArhiveFolder.exists()) {
							makeFileArchiveInSpecificLocation(file, csvArhiveFolder.getAbsolutePath());
						}
					}
				} else {
					LOG.warn("Either image folder creation failed or styleNumbers are empty..");
				}
			}
		} catch (final Exception exception) {
			LOG.error(exception);
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	@Override
	public boolean isAbortable() {
		return true;
	}
	/**
	 * @description method for retrieving specific extension files from
	 *              specified path
	 * @param folderPath
	 * @param fileExtension
	 * @return listOfFiles
	 */
	private static File[] getSpecificExtensionFilesFromSpecifiedFolder(final String folderPath, final String fileExtension) {
		File[] listOfFiles = null;
		if (StringUtils.isNotBlank(folderPath) && StringUtils.isNotBlank(fileExtension)) {
			try {
				String tempFolderPath = folderPath;
				if (!folderPath.endsWith("\\")) {
					tempFolderPath = folderPath + File.separator;
				}
				final File folder = new File(tempFolderPath);
				listOfFiles = folder.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(final File dir, final String name) {
						return name.toLowerCase().endsWith(fileExtension);
					}
				});
			} catch (final Exception exception) {
				LOG.error(exception);
			}
		} else {
			LOG.warn("Retrieving files from the specified path failed,check specified path and fileextension");
		}
		return listOfFiles;
	}

	/**
	 * @description method for reading styleNumbers from csv file
	 * @param csvFile
	 * @param csvFileHeader
	 * @return styleNumberList
	 */
	private static List<String> getStyleNumbersFromCsvFile(final File csvFile, final String csvFileHeader) {
		List<String> styleNumberList = null;
		String styleNumber = null;
		if (null != csvFile && StringUtils.isNotBlank(csvFileHeader)) {
			styleNumberList = new ArrayList();
			try (BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))) {
				while ((styleNumber = bufferReader.readLine()) != null) {

					if (styleNumber.trim().equalsIgnoreCase(csvFileHeader)
							|| styleNumber.toLowerCase().trim().contains(csvFileHeader.split("\\s")[0].toLowerCase())) {
						LOG.info("headers Found for file " + csvFile.getName());
					} else if (styleNumber.trim().length() == 0) {
						LOG.info("Empty Line Found for file" + csvFile.getName());
					} else {
						styleNumberList.add(styleNumber.trim());
					}
				}

			} catch (final Exception exception) {
				LOG.error(exception);
			}
		} else {
			LOG.warn("Unable to read Style Numbers from csv file,check csv file and  its headers");
		}
		return styleNumberList;

	}

	/**
	 * @description method for creating folder in specific location
	 * @param directoryPath
	 * @param folderName
	 * @return folder
	 */
	private static File createFolder(final String directoryPath, final String folderName) {
		File folder = null;
		if (null != directoryPath && new File(directoryPath).exists() && null != folderName) {
			if (directoryPath.endsWith("\\")) {
				folder = new File(directoryPath + folderName);
			} else {
				folder = new File(directoryPath + File.separator + folderName);
			}
			if (!folder.exists()) {
				try {
					folder.mkdir();
				} catch (final Exception exception) {
					LOG.error("Folder Generation failed with root cause  " + exception);
				}
			}
			setFilePermissions(folder);
		} else {
			LOG.warn("Unable to create Folder::given directory path not found for folder " + folderName);
		}
		return folder;
	}

	/**
	 * @description method for retrieving products to specific styleNumbers
	 * @param styleNumberList
	 * @param styleVariantProductList
	 */
	private void getProducts(final List<String> styleNumberList, final List<ProductModel> styleVariantProductList) {
		if (CollectionUtils.isNotEmpty(styleNumberList)) {
			final String productsRetrieveQuery = "select {p.pk} from {ApparelStyleVariantProduct ! as p} where {p.catalogversion} =?catalogversion and  {styleCode} IN (?stylecodes) ";
			final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("sslProductCatalog",
					"Online");
			final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(productsRetrieveQuery);
			flexibleSearchQuery.addQueryParameter("catalogversion", catalogVersion.getPk());
			flexibleSearchQuery.addQueryParameter("stylecodes", styleNumberList);

			final SearchResult<ProductModel> result = flexibleSearchService.search(flexibleSearchQuery);

			if (CollectionUtils.isNotEmpty(result.getResult())) {
				styleVariantProductList.addAll(result.getResult());
			} else {
				LOG.warn("products not found for the given style Numbers");
			}
		} else {
			LOG.warn("Found Empty StyleNumbers in products retrieving process");
		}
	}

	/**
	 * @description method for downloading media
	 * @param productList
	 * @param directoryPath
	 */
	private static void processMediaDownload(final List<ProductModel> productList, final String directoryPath) {

		if (CollectionUtils.isEmpty(productList)) {
			LOG.warn("Found Empty products ");
			return;
		}
		final Set<String> downloadFailedStyleCodes = new HashSet();
		final Map<String, Set<String>> downloadSucceedMediaMap = new HashMap();
		final List<String> formats = Arrays.asList(Config.getString(MEDIA_DOWNLOAD_FORMAT, "desktop").split(","));
		formats.replaceAll(String::trim);

		for (final ProductModel product : productList) {

			final String styleCode = product.getStyleCode().trim();
			if (CollectionUtils.isEmpty(product.getGalleryImages())) {
				LOG.info("Gallery Images Not Found for styleCode [ " + product.getStyleCode() + "] and productCode["
						+ product.getCode()+"]");
				downloadFailedStyleCodes.add(styleCode);
				continue;
			}

			final Map<MediaContainerModel, Set<String>> galleryMediaUrlMap = new HashMap<>();
			for (final MediaContainerModel gallery : product.getGalleryImages()) {
				if (CollectionUtils.isEmpty(gallery.getMedias())) {
					LOG.info("Media  Not Found for styleCode [ " + product.getStyleCode() + "] and productCode["
							+ product.getCode()+"] and Mediacontainer ["+gallery.getQualifier()+"]");
					continue;
				}
				final Set<String> mediaUrls = new HashSet();
				getMediaUrl(mediaUrls, gallery, formats);
				if (CollectionUtils.isNotEmpty(mediaUrls)) {
					galleryMediaUrlMap.put(gallery, mediaUrls);
				}
			}
			
			
			int downloadSucceedImgsCount = 0;
			final HashSet<String> downloadedMediaSet = new HashSet();
			for (final Map.Entry<MediaContainerModel, Set<String>> entry : galleryMediaUrlMap.entrySet()) {
				for (final String url : entry.getValue()) {

					if (StringUtils.isEmpty(url)) {
						continue;
					}
					try {
						final String imgFileName = changeFileNameIfAlreadyExists(
								new File(directoryPath + File.separator + entry.getKey().getQualifier()));
						final File imageFile = new File(directoryPath + File.separator + imgFileName);
						Files.copy(new URL(url).openStream(), Paths.get(imageFile.getAbsolutePath()));

						setFilePermissions(imageFile);
						downloadedMediaSet.add(imgFileName);
						downloadSucceedImgsCount = downloadSucceedImgsCount+1;
					} catch (final Exception exception) {
						LOG.warn(exception);
					}
				}
			}
			if (StringUtils.isNotBlank(styleCode) && CollectionUtils.isNotEmpty(downloadedMediaSet)) {
				if (downloadSucceedMediaMap.containsKey(styleCode)) {
					downloadSucceedMediaMap.get(styleCode).addAll(downloadedMediaSet);
				} else {
					downloadSucceedMediaMap.put(styleCode, downloadedMediaSet);
				}
			}
			if (downloadSucceedImgsCount != formats.size() * product.getGalleryImages().size()) {
				downloadFailedStyleCodes.add(styleCode);
			}
		}
		
		// Report For Successfully downloaded media linked stylecode.
		final String successReportFileName = "Download_success_report";
		final String[] successReportHeaders = { "S.NO", "StyleCode", "MediaFileName" };
		generateExcelFile(successReportFileName, successReportHeaders, downloadSucceedMediaMap, directoryPath, true);
		// Report For download Failed media linked stylecode.
		final String[] failedReportHeaders = { "S.NO", "StyleCode" };
		final String failedReportFileName = "Download_failed_report";
		generateExcelFile(failedReportFileName, failedReportHeaders, downloadFailedStyleCodes, directoryPath, false);
	}
	/**
	 * @description method for file archiving in a specific location
	 * @param file
	 * @param directoryPath
	 */
	private  static  void makeFileArchiveInSpecificLocation(final File file, final String directoryPath) {
		boolean fileArchive = false;
		if (null != directoryPath && null != file && file.exists()) {
			ZipOutputStream zos = null;
			FileInputStream inputStream = null;
			FileOutputStream outputStream = null;
			final byte[] buffer = new byte[1024];
			try {
				final File zipFile = new File(directoryPath + File.separator + file.getName() + ".zip");
				outputStream = new FileOutputStream(zipFile);
				zos = new ZipOutputStream(outputStream);
				final ZipEntry ze = new ZipEntry(file.getName());
				zos.putNextEntry(ze);
				inputStream = new FileInputStream(file);

				int len;
				while ((len = inputStream.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				fileArchive = true;

			} catch (final Exception exception) {
				LOG.info(exception);
			} finally {
				if (null != inputStream) {
					try {
						inputStream.close();
					} catch (final Exception exception) {
						LOG.info(exception);
					}
				}
				if (null != zos) {
					try {
						zos.closeEntry();
						zos.close();
					} catch (final Exception exception) {
						LOG.info(exception);
					}
				}
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (final Exception exception) {
						LOG.error(exception);
					}
				}
			}
		} else {
			LOG.warn("Failed to archive the file in specified location");
		}
		if (fileArchive) {
			try {
				Files.delete(Paths.get(file.getAbsolutePath()));
			} catch (final IOException e) {
				LOG.error(e);

			}
			LOG.info(file.getName() + " Archived and moved succefully to specified path");
		}
	}

	/**
	 * it generates Excel File in the specified path
	 *
	 * @param excelHeaders
	 * @param excelData
	 * @param directoryPath
	 * @return excelFileGenerationStatus
	 */
	private static boolean generateExcelFile(final String fileName, final String[] excelHeaders, final Object excelData,
			final String directoryPath, final boolean successReport) {

		final String reportType = successReport ? "success" : "Failure";
		LOG.info("Generating " + reportType + " Report.");

		boolean excelFileGeneration = true;
		final XSSFWorkbook workbook = new XSSFWorkbook();
		setExcelSheetHeaders(workbook, fileName, excelHeaders);
		if (setExcelSheetData(workbook, fileName, excelData, successReport)) {

			FileOutputStream outputStream = null;
			try {
				if (StringUtils.isNotBlank(fileName) && StringUtils.isNotBlank(directoryPath)) {
					outputStream = new FileOutputStream(new File(fileName));
					workbook.write(outputStream);
					final StringBuilder reportsPath = new StringBuilder(directoryPath).append(File.separator)
							.append(fileName).append(MEDIA_DOWNLOAD_REPORTS_EXTENSION);
					final File file = new File(reportsPath.toString());
					final CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
							StandardCopyOption.COPY_ATTRIBUTES };
					Files.copy(Paths.get(fileName), Paths.get(file.getAbsolutePath()), options);
					setFilePermissions(file);
				} else {
					excelFileGeneration = false;
					LOG.warn(reportType + " report Generation Failed ,check with fileName & directoryPath");
				}

			} catch (final Exception exception) {
				LOG.error("Generating " + reportType + " report  Failed with root cause :" + exception);
				excelFileGeneration = false;
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (final IOException e) {
						LOG.error("File closing exception " + e);
					}
				}

			}
		} else {
			if (successReport) {
				LOG.warn("ALL styleCodes related media Failed to download.hence success report not generated");
			} else {
				LOG.warn("ALL styleCodes related media  downloaded.hence failure report not generated");
			}
		}
		return excelFileGeneration;
	}

	/**
	 * it sets headers to Excel sheet
	 *
	 * @param workbook
	 * @param sheetName
	 * @param headerArray
	 */
	private static void setExcelSheetHeaders(final XSSFWorkbook workbook, final String sheetName, final String[] headerArray) {

		LOG.info("Setting Excel headers.");
		if (null != workbook && StringUtils.isNotBlank(sheetName) && ArrayUtils.isNotEmpty(headerArray)) {
			final XSSFSheet excelSheet = workbook.createSheet(sheetName);

			final XSSFRow excelHeader = excelSheet.createRow(0);
			for (int i = 0; i < headerArray.length; i++) {
				if (StringUtils.isNotBlank(headerArray[i])) {
					excelHeader.createCell(i).setCellValue(headerArray[i]);
				} else {
					LOG.warn("Headers Not Found,check with given headers.");
				}

			}
		} else {
			LOG.warn("Either of the  given params to setExcelSheetHeaders may be null.");
		}

	}

	/**
	 * it sets Excel Data
	 *
	 * @param workbook
	 * @param sheetName
	 * @param excelData
	 * @param successReport
	 */
	private static boolean setExcelSheetData(final XSSFWorkbook workbook, final String sheetName, final Object excelData,
			final boolean successReport) {

		boolean setExcelData = true;
		if (null == workbook || null == excelData || StringUtils.isBlank(sheetName)) {
			LOG.warn("Getting null for  either workbook || sheetName || SheetData ");
			return false;
		}
		if (successReport && excelData instanceof Map && MapUtils.isNotEmpty((Map) excelData)) {
			final XSSFSheet excelSheet = workbook.getSheet(sheetName);
			int row = 1;
			int styleCode = 1;
			for (final Map.Entry<String, Set<String>> entry : (Set<Map.Entry<String, Set<String>>>) ((Map) excelData)
					.entrySet()) {
				int mediaCount = 1;
				for (final String mediaFileName : entry.getValue()) {
					final XSSFRow excelRow = excelSheet.createRow(row);
					if (mediaCount == 1) {
						excelRow.createCell(0).setCellValue(styleCode);
						excelRow.createCell(1).setCellValue(entry.getKey());
					} else {
						excelRow.createCell(0);
						excelRow.createCell(1);
					}
					excelRow.createCell(2).setCellValue(mediaFileName);
					row += 1;
					mediaCount += 1;
				}
				styleCode += 1;
			}
		} else if (!successReport && excelData instanceof Collection
				&& CollectionUtils.isNotEmpty((Collection) excelData)) {
			int row = 1;
			final XSSFSheet excelSheet = workbook.getSheet(sheetName);
			for (final String downloadFailedStyleCode : (Set<String>) excelData) {
				final XSSFRow excelRow = excelSheet.createRow(row);
				excelRow.createCell(0).setCellValue(row);
				excelRow.createCell(1).setCellValue(downloadFailedStyleCode);
				row += 1;
			}

		} else {
			setExcelData = false;
			LOG.warn("Reports data may be empty  in setExcelSheetData method");
		}
		return setExcelData;
	}

	/**
	 * It modifies the fileName if file already exists same name
	 *
	 * @param file
	 * @return string
	 */
	private static String changeFileNameIfAlreadyExists(File file) {
		File tempFile=file;
		if (tempFile.exists()) {
			final String filePath = tempFile.getAbsolutePath();
			while (tempFile.exists()) {
				final String fileName = tempFile.getName();
				tempFile = new File(filePath.substring(0, filePath.lastIndexOf(File.separator) + 1)
						+ changeFileName(fileName, fileName.substring(fileName.lastIndexOf(".") + 1)));
			}
		}
		return tempFile.getName();
	}

	/**
	 * it changes the given fileName
	 *
	 * @param fileName
	 * @param fileExtension
	 * @return string
	 */
	private static String changeFileName(String fileName, String fileExtension) {
		String tempFileName=fileName;
		String tempFileExtension=fileExtension;
		try {
			
			if (StringUtils.isNotBlank(tempFileExtension) && StringUtils.isNotBlank(tempFileName)) {
				if (tempFileExtension.charAt(0) != '.') {
					tempFileExtension = "." + tempFileExtension;
				}
				final Pattern pattern = Pattern.compile("(?:.*)(\\(+\\d+\\)+" + tempFileExtension + ")$");
				final Matcher matcher = pattern.matcher(tempFileName);
				if (matcher.matches()) {
					final StringBuilder fileNameTrailPart = new StringBuilder(matcher.group(1));
					int startIndex = 0;
					int endIndex = 0;
					final StringBuilder fileNumber = new StringBuilder();
					for (int i = 0; i < fileNameTrailPart.length(); i++) {
						if (Character.isDigit(fileNameTrailPart.charAt(i))) {
							if (startIndex == 0) {
								startIndex = i;
							}
							endIndex = i;
							fileNumber.append(fileNameTrailPart.charAt(i));
						}
					}
					if (startIndex > 0 && StringUtils.isNotBlank(fileNumber.toString())) {
						fileNameTrailPart.replace(startIndex, endIndex + 1,
								String.valueOf(Integer.parseInt(fileNumber.toString()) + 1));
					}
					tempFileName = tempFileName.replace(matcher.group(1), fileNameTrailPart.toString());
				} else {
					tempFileName = tempFileName.replace(tempFileExtension, "(1)" + tempFileExtension);
				}
			} else {
				LOG.warn("check provided params for chngeFileName method may be null");
			}
		} catch (final Exception e) {
			LOG.error("changing file name  failed with root cause :" + e);
		}
		return tempFileName;
	}
	/**
	 * retrieves images url's
	 * @param mediaUrlSet
	 * @param mediaContainer
	 * @param mediaFormats
	 */
	private static void getMediaUrl(final Set<String> mediaUrlSet, final MediaContainerModel mediaContainer,
			final List<String> mediaFormats) {
		if (CollectionUtils.isEmpty(mediaContainer.getMedias())) {
			return;
		}
		for (final MediaModel media : mediaContainer.getMedias()) {
			if (media != null && media.getMediaFormat() != null) {

				if (StringUtils.isNotEmpty(media.getMediaFormat().getQualifier())
						&& mediaFormats.stream().anyMatch(media.getMediaFormat().getQualifier()::equalsIgnoreCase)
						&& StringUtils.isNotEmpty(media.getURL())) {
					mediaUrlSet.add(media.getURL());
				}
			}
		}

	}
	/**
	 * issues file permissions
	 * @param file
	 * @return permission status
	 */
	private static boolean setFilePermissions(final File file) {
		return file.setExecutable(true, false) && file.setReadable(true, false) && file.setWritable(true, false);
	}

}
