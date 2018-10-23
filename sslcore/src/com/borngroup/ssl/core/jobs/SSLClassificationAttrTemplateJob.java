package com.borngroup.ssl.core.jobs;

import com.borngroup.ssl.core.util.SSLClassificationUtil;
import com.pcm.perf.model.SSLClassificationAttrTemplateCronJobModel;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.catalog.jalo.classification.ClassificationAttribute;
import de.hybris.platform.catalog.jalo.classification.ClassificationAttributeValue;
import de.hybris.platform.catalog.jalo.classification.ClassificationClass;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.ProductFeatureModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeModel;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SSLClassificationAttrTemplateJob extends AbstractJobPerformable<SSLClassificationAttrTemplateCronJobModel> {

	/**
	 *
	 */
	private static final String XLSX = ".xlsx";

	private static final Logger LOG = Logger.getLogger(SSLClassificationAttrTemplateJob.class);

	private static final String EXCEPTION_MEDIA_MSG = "Exception during media saving";
	private static final String GET_MEDIA_INFO_MSG = "getMainMedia() : file does not exist hence creating new media..";
	private static final String CODE_UNIQUE_TRUE = "code[unique=true]";
	private static final String UPDATE_APPAREL_PRODUCT = "UPDATE ApparelProduct";
	private static final String VAR_CL_ATTR_MODIFIERS = "$clAttrModifiers=system='sslClassification',version='1.0',translator=de.hybris.platform.catalog.jalo.classification.impex.ClassificationAttributeTranslator,lang=en";
	private static final String VAR_CATALOG_VERSION = "$catalogVersion=catalogversion(catalog(id[default=$productCatalog]),version[default='Staged'])[unique=true,default=$productCatalog:Staged]";
	private static final String VAR_PRODUCT_CATALOG_NAME = "$productCatalogName=SSL Product Catalog";
	private static final String VAR_PRODUCT_CATALOG = "$productCatalog=sslProductCatalog";
	private static final String L3_CATEGORY = "L3 Category";
	private static final String L2_CATEGORY = "L2 Category";
	private static final String L1_CATEGORY = "L1 Category";
	private static final String PRETTY_PROD_NAME = "Pretty_Prod_Name";
	private static final String MASTER_SKU = "Master_SKU";
	private static final String ATTRIBUE_NAME = "##Attribue Name ->";
	private static final String DOT = ".";
	private static final String FORWARD_SLASH = "/";
	private static final String SYSTEM_VERSION = "/1.0/";
	private static final String CUSTOM_INITIAL = "@";
	private static final String CL_ATTR_MODIFIERS_CLASS = "[$clAttrModifiers,class=";
	private static final String EMPTY_STRING = "";
	private static final String STAGED = "Staged";
	private static final String ALT_BRAND_DESC = "ALT_BRAND_DESC";
	private static final String APPROVAL = "approvalstatus(code)[allownull=true]";
	private static final String STYLE = "Style #";
	private static final String UNDERSCORE = "_";
	private static final String TABLE_HEADER = "tableHeader";
	private static final String CLASSIFICATION_SET = "ClassificationSet";
	private static final String FILE_SEPERATOR = "//";
	private final String QUERY = "select distinct {c:pk} from {ApparelProduct as p join CategoryProductRelation as rel on {rel:target}={p:pk} join category as c on {c:pk}={rel:source} join articleapprovalStatus as app on {app:pk}={p:approvalStatus} } ";

	@Autowired(required = true)
	CatalogService catalogService;
	@Autowired(required = true)
	CategoryService categoryService;
	@Autowired
	private MediaService mediaService;

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public PerformResult perform(final SSLClassificationAttrTemplateCronJobModel cronJob) {
		PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		String query = QUERY;

		final CatalogVersionModel catalogVersion = SSLClassificationUtil.getCatalogVersion(cronJob.getCatalogId(), modelService, catalogService);
		final String catalogVersionPk = catalogVersion.getPk().getLongValueAsString();
		final Boolean limittedExport = cronJob.getLimitedExport();
		final String statusIds = cronJob.getApprovalStatus();
		final MediaModel template = cronJob.getTemplate();
		List<CategoryModel> l3Categories = new ArrayList<CategoryModel>();
		final List<MediaModel> targetSheets = new ArrayList<MediaModel>();
		int productListSize = 0;
		String articleStatus = "check";
		if (BooleanUtils.isTrue(limittedExport)) {
			final String categoryIds = cronJob.getCategoryIds();
			if (StringUtils.isNotEmpty(categoryIds)) {
				query =
					query + " where {c:code} in " + categoryIds + " and {p:catalogversion}=" + catalogVersionPk + " and {c:catalogversion}=" + catalogVersionPk;

				if (StringUtils.isNotEmpty(statusIds)) {
					articleStatus = statusIds;
					query = query + " and {app:code} in " + statusIds;
				}
			} else {
				query = EMPTY_STRING;
			}
		} else {
			query = query + " where {p:catalogversion}=" + catalogVersionPk + " and {c:catalogversion}=" + catalogVersionPk;
			if (StringUtils.isNotEmpty(statusIds)) {
				articleStatus = statusIds;
				query = query + " and {app:code} in " + statusIds;
			} else {
				query = query + " and {app:code}='check'";
			}

		}
		if (StringUtils.isNotEmpty(query)) {
			final SearchResult<CategoryModel> searchResult = flexibleSearchService.search(query);
			l3Categories = searchResult.getResult();
		}
		if (CollectionUtils.isNotEmpty(l3Categories)) {
			try {
				final int count = l3Categories.size();
				Map<String, Integer> featuresMap = null;
				for (int i = 1; i <= count; i++) {
					featuresMap = new HashMap<String, Integer>();

					final CategoryModel category = l3Categories.get(i - 1);
					productListSize = CollectionUtils.isNotEmpty(category.getProducts()) ? category.getProducts().size() : 0;
					final Set<String> seasonCodes = cronJob.getSeasonCodes() == null ? null : new HashSet<String>(Arrays.asList(cronJob.getSeasonCodes()
																																	   .toUpperCase()
																																	   .split("\\s*,\\sx*")));

					final Workbook workbook = new XSSFWorkbook();
					final Sheet sheet = workbook.createSheet(category.getCode());
					createImpexHeader(sheet, workbook);
					createTableHeader(category, sheet, workbook, productListSize, featuresMap);
					createSheetData(category, sheet, featuresMap, articleStatus, seasonCodes);
					sheet.autoSizeColumn((short) 1);
					final MediaModel targetmedia = writeToMediaSheet(catalogVersion, template, category, workbook, targetSheets);
					if (null != targetmedia) {
						targetSheets.add(targetmedia);
						LOG.info(LOG.getName() + " Successfully created target file with media id: " + targetmedia.getCode());
					} else {
						LOG.info(LOG.getName() + " Target Media is null for category : " + category.getCode());
					}
				}

				if (CollectionUtils.isNotEmpty(targetSheets)) {
					cronJob.setTargetSheets(targetSheets);
					modelService.save(cronJob);
				}

			} catch (final Exception exception) {
				LOG.error("Creation of xlsx file template is failed with error: ", exception);
				result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
			}

		}

		return result;
	}

	/**
	 * @param category
	 * @param sheet
	 * @param workbook
	 * @param productListSize
	 * @param featuresMap
	 */
	private void createTableHeader(final CategoryModel category, final Sheet sheet, final Workbook workbook, final int productListSize,
								   final Map<String, Integer> featuresMap) {
		// TableHeader style
		final CellStyle cellStyle = getStyle(workbook, TABLE_HEADER);

		Row row = null;
		Cell cell = null;

		row = sheet.createRow(5);

		cell = row.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(ATTRIBUE_NAME);

		cell = row.createCell(1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(MASTER_SKU);

		cell = row.createCell(2);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(STYLE);

		cell = row.createCell(3);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(PRETTY_PROD_NAME);

		cell = row.createCell(4);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(L1_CATEGORY);

		cell = row.createCell(5);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(L2_CATEGORY);

		cell = row.createCell(6);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(L3_CATEGORY);

		cell = row.createCell(7);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(ALT_BRAND_DESC);

		cell = row.createCell(8);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(APPROVAL);

		final List<CategoryModel> supcat = category.getSupercategories();
		int classAttrStartCount = 9;
		String qualifier = null;
		for (final CategoryModel cat : supcat) {
			ClassificationClassModel sslClass;
			if (cat instanceof ClassificationClassModel) {
				sslClass = (ClassificationClassModel) cat;
				final List<ClassificationAttributeModel> attributes = sslClass.getClassificationAttributes();
				for (final ClassificationAttributeModel attribute : attributes) {
					qualifier = sslClass.getCatalogVersion().getCatalog().getId() + SYSTEM_VERSION + sslClass.getCode() + DOT + attribute.getCode();
					featuresMap.put(qualifier.toLowerCase(), Integer.valueOf(classAttrStartCount));
					cell = row.createCell(classAttrStartCount);
					cell.setCellValue(sslClass.getName() + FORWARD_SLASH + attribute.getName());
					cell.setCellStyle(cellStyle);

					setRestrictedData(attribute, 5, classAttrStartCount, sheet, productListSize, sslClass);

					sheet.getRow(4).createCell(classAttrStartCount).setCellValue(
						CUSTOM_INITIAL + attribute.getCode() + CL_ATTR_MODIFIERS_CLASS + sslClass.getCode() + "]");
					classAttrStartCount++;
				}

			}
		}
		cell = sheet.getRow(4).createCell(classAttrStartCount);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("$catalogVersion");

		cell = sheet.getRow(4).createCell(classAttrStartCount + 1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("attributionFlag");
	}

	/**
	 * @param attribute
	 * @param currentRow
	 * @param productListSize
	 * @param currentColumn
	 * @param sheet
	 * @param sslClass
	 */
	private void setRestrictedData(final ClassificationAttributeModel attribute, final int currentRow, final int currentColumn, final Sheet sheet,
								   final int productListSize, final ClassificationClassModel sslClass) {

		final ClassificationClass classificationClass = modelService.getSource(sslClass);
		final ClassAttributeAssignment classAttributeAssignment = classificationClass.getAttributeAssignment((ClassificationAttribute) modelService.getSource(
			attribute));
		final List<ClassificationAttributeValue> clssAttValues = classAttributeAssignment.getAttributeValues();

		if (CollectionUtils.isNotEmpty(clssAttValues)) {
			final String[] selectiveValues = new String[clssAttValues.size()];
			int i = 0;

			for (final ClassificationAttributeValue attVal : clssAttValues) {
				selectiveValues[i] = attVal.getCode();
				i++;
			}
			DataValidationConstraint constraint = null;
			DataValidationHelper validationHelper = null;
			DataValidation dataValidation = null;

			validationHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
			final CellRangeAddressList addressList = new CellRangeAddressList(currentRow + 1, currentRow + productListSize, currentColumn, currentColumn);
			constraint = validationHelper.createExplicitListConstraint(selectiveValues);
			dataValidation = validationHelper.createValidation(constraint, addressList);
			dataValidation.setSuppressDropDownArrow(true);
			sheet.addValidationData(dataValidation);
		}

	}

	/**
	 * @param sheet
	 * @param workbook
	 */
	private void createImpexHeader(final Sheet sheet, final Workbook workbook) {
		// ImpexHeader style
		final CellStyle impexHeaderStyle = getStyle(workbook, null);
		Row row = null;
		Cell cell = null;

		// Macro
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(VAR_PRODUCT_CATALOG);

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(VAR_PRODUCT_CATALOG_NAME);

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(VAR_CATALOG_VERSION);

		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(VAR_CL_ATTR_MODIFIERS);

		// Impex Update
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(UPDATE_APPAREL_PRODUCT);

		cell = row.createCell(1);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(CODE_UNIQUE_TRUE);

		cell = row.createCell(2);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(3);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(4);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(5);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(6);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(7);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(EMPTY_STRING);

		cell = row.createCell(8);
		cell.setCellStyle(impexHeaderStyle);
		cell.setCellValue(APPROVAL);
	}

	/**
	 * @param catalogVersion
	 * @param template
	 * @param category
	 * @param workbook
	 * @param targetSheets
	 * @return MediaModel
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private MediaModel writeToMediaSheet(final CatalogVersionModel catalogVersion, final MediaModel template, final CategoryModel category,
										 final Workbook workbook, final List<MediaModel> targetSheets) throws FileNotFoundException, IOException {
		final String tempPath = Config.getParameter("hybris.temp.path");
		final String mediaCode = category.getCode() + UNDERSCORE + CLASSIFICATION_SET;

		final File file = new File(tempPath + FILE_SEPERATOR + mediaCode + XLSX);
		file.createNewFile();
		final FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		return SSLClassificationUtil.getTargetMedia(catalogVersion, mediaCode, file, targetSheets, modelService, flexibleSearchService);

	}

	/**
	 * @param category
	 * @param sheet
	 * @param featuresMap
	 * @param articleStatus
	 * @param seasonCodes
	 */
	private void createSheetData(final CategoryModel category, final Sheet sheet, final Map<String, Integer> featuresMap, final String articleStatus,
								 final Set<String> seasonCodes) {
		final List<ProductModel> products = category.getProducts();
		int startRowCount = 6;

		Row row = null;
		for (final ProductModel product : products) {
			boolean isSeasonCodeMatched = false;

			if (CollectionUtils.isNotEmpty(seasonCodes) && StringUtils.isNotEmpty(product.getSeasonCode())) {
				for (final String seasonCode : seasonCodes) {
					if (seasonCode.contains(product.getSeasonCode().toUpperCase())) {
						isSeasonCodeMatched = true;
						break;
					}
				}
			}
			if (CollectionUtils.isEmpty(seasonCodes) || isSeasonCodeMatched) {
				final ArticleApprovalStatus articleApprovalStatus = product.getApprovalStatus();
				if (null != articleApprovalStatus) {
					final String statusCode = articleApprovalStatus.getCode();
					if (articleStatus.contains(statusCode)) {
						final List<CategoryModel> superCateg = category.getSupercategories();
						CategoryModel l2Category = null;
						if (CollectionUtils.isNotEmpty(superCateg)) {
							for (final CategoryModel cat : superCateg) {
								if (!(cat instanceof ClassificationClassModel)) {
									l2Category = cat;
									break;
								}
							}
						}
						row = sheet.createRow(startRowCount);
						row.createCell(1).setCellValue(product.getCode());
						// TODO: Style# mapping
						row.createCell(2).setCellValue(product.getStyleCode());
						row.createCell(3).setCellValue(product.getName(Locale.ENGLISH));
						if (null != l2Category) {
							final List<CategoryModel> l2SuperCateg = l2Category.getSupercategories();

							if (CollectionUtils.isNotEmpty(l2SuperCateg)) {
								for (final CategoryModel cat : l2SuperCateg) {
									if (!(cat instanceof ClassificationClassModel)) {

										row.createCell(4).setCellValue(cat.getCode());
										break;

									}
								}
							}
							row.createCell(5).setCellValue(l2Category.getCode());
						}

						row.createCell(6).setCellValue(category.getCode());
						// TODO: Brand Desc mapping
						row.createCell(7).setCellValue(product.getBrandCode());

						if (product.getApprovalStatus() != null) {
							row.createCell(8).setCellValue(product.getApprovalStatus().toString().toLowerCase());
						} else {
							row.createCell(8).setCellValue("");
						}

						final List<ProductFeatureModel> features = product.getFeatures();
						if (CollectionUtils.isNotEmpty(features)) {
							for (final ProductFeatureModel feature : features) {
								final Integer cellPos = featuresMap.get(feature.getQualifier().toLowerCase());
								final Object obj = feature.getValue();
								if (obj instanceof ClassificationAttributeValueModel) {
									if (null != cellPos) {
										row.createCell(cellPos.intValue()).setCellValue(((ClassificationAttributeValueModel) obj).getCode());
									}
								}

							}

						}
						startRowCount++;
					}
				}
			}
		}
	}

	/**
	 * @param workbook
	 * @return CellStyle
	 */
	private CellStyle getStyle(final Workbook workbook, final String section) {
		final CellStyle cellStyle = workbook.createCellStyle();
		final Font font = workbook.createFont();

		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 11);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		if (TABLE_HEADER.equals(section)) {

			font.setColor(IndexedColors.WHITE.getIndex());
			font.setFontHeightInPoints((short) 10);
			cellStyle.setFont(font);
			cellStyle.setWrapText(true);

			cellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
			cellStyle.setFillPattern(CellStyle.ALIGN_FILL);

		} else {
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setFontHeightInPoints((short) 10);
			cellStyle.setFont(font);
			cellStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			cellStyle.setFillPattern(CellStyle.ALIGN_FILL);

		}

		return cellStyle;
	}
}