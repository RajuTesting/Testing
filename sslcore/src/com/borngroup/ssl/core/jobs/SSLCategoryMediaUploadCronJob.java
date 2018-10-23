/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.promotions.util.Pair;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.product.service.impl.SslDefaultProductService;
import com.ssl.core.model.CategoryMediaUploadCronJobModel;

/**
 * @author techouts
 *
 */
public class SSLCategoryMediaUploadCronJob extends AbstractJobPerformable<CategoryMediaUploadCronJobModel> {

	/** Log4j Logger. **/
	private static final Logger LOG = Logger.getLogger(SSLCategoryMediaUploadCronJob.class);

	@Resource(name = "catalogVersionService")
	private CatalogVersionService catalogVersionService;

	@Resource(name = "sslProductService")
	private SslDefaultProductService sslProductService;

	private static final String CATEGORIES_MEDIA_DOWNLOAD_FORMAT = Config.getString("categories.media.upload.format",
			"2000Wx3000H");
	private static final String ACCESSKEY = Config.getParameter("categories.upload.amazons3.accesskey").trim();
	private static final String SECRET_ACCESSKEY = Config.getParameter("categories.upload.amazons3.secret.accesskey")
			.trim();
	private static final String ENDPOINT = Config.getParameter("categories.upload.amazons3.endpoint").trim();
	private static final String BUCKETID = Config.getParameter("categories.upload.amazons3.bucketid").trim();
	private static final String IMAGESKEYPATH = Config.getParameter("categories.upload.amazons3.images.keypath");
	private static final String CATEGORIES_SEPERATOR_REGEX = ",|;";
	private static final String CATEGORIES_FOLEDR_PREFIX = "poshaq_";
	private static final String CATEGORIES_MEDIA_ALTIMAGES_FILENAME = "alt";
	private static final String AMAZON_KEYS_SEPERATOR = "/";
	private static final String CATEGORY_PRODUCTS_RETRIEVING_QUERY = "SELECT  distinct {style.pk} FROM {ApparelStyleVariantProduct ! as style join ApparelProduct as base on {base.pk} = {style.baseProduct} "
			+ "join articleApprovalStatus as status on {status.pk}={style.approvalStatus} AND {status.code} IN (?approvalstatus) join CategoryProductRelation AS rel ON {base:PK} = {rel:target} "
			+ "join category AS cat ON {cat.PK} = {rel.source} AND {cat.code}  In (?categories)} where {style.catalogVersion} = ?catalogVersion  AND {style.galleryImages} IS Not Null";

	@Override
	public PerformResult perform(final CategoryMediaUploadCronJobModel arg0) {

		LOG.info("#########SSLCategoryMediaDownloadCronJob Started###########");
		final Map<String, Set<ApparelStyleVariantProductModel>> categoryProductMap = getStyleVariantProductsForCategories(arg0.getCategory(),
						arg0.getIncludeUnapprovedProducts().booleanValue(),
						arg0.getIncludezeroInventoryProducts().booleanValue());
		if (MapUtils.isNotEmpty(categoryProductMap)) {
			for (final Map.Entry<String, Set<ApparelStyleVariantProductModel>> categoryProductEntry : categoryProductMap
					.entrySet()) {
				if (CollectionUtils.isNotEmpty(categoryProductEntry.getValue())) {

					final Map<String, List<Pair<String, Collection<MediaModel>>>> productMediaMap = getMediaFromProducts(
							categoryProductEntry.getKey(), categoryProductEntry.getValue(), arg0.getExtractAltImages());

					final Map<String, Set<Pair<String, Set<String>>>> productMediaUrlMap = getMediaUrl(
							categoryProductEntry.getKey(), productMediaMap);

					uploadMediaToAmazonServer(categoryProductEntry.getKey(), productMediaUrlMap);
				} else {
					LOG.warn("Found Empty Products For category [" + categoryProductEntry.getKey() + "]");
				}
			}
		} else {
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
		}
		LOG.info("########SSLCategoryMediaDownloadCronJob End#########");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	@Override
	public boolean isAbortable() {
		return true;
	}
	/**
	 * @description method for retrieving ApparelStyleVariantProducts based on
	 *              category code.
	 * @param category
	 * @param includeUnapprovedProducts
	 * @return categoryProductMap
	 */
	private Map<String, Set<ApparelStyleVariantProductModel>> getStyleVariantProductsForCategories(
			final String category, final boolean includeUnapprovedProducts,
			final boolean includeZeroInventoryProducts) {
		
		LOG.info("categorystring =" + category + " includeUnapprovedProducts =" + includeUnapprovedProducts);
		LOG.info("includeUnapprovedProducts="+includeUnapprovedProducts);
		
		final String inventoryQuery = " AND  {style.pk} in ({{select distinct {size.baseProduct} as code from "
				+ "{ApparelSizeVariantProduct as size join articleApprovalStatus as sizeVariantStatus on {sizeVariantStatus.pk}={size.approvalStatus} "
				+ "AND {sizeVariantStatus.code} IN (?sizeVariantApprovalstatus) join stocklevel as sl ON {size.code}={sl.productCode}} "
				+ "where {size.catalogVersion} =?sizeVariantCatalogVersion group by {size.baseProduct} having sum({sl.available})>=1}})";
		final Map<String, Set<ApparelStyleVariantProductModel>> categoryProductMap = new HashMap<>();
		final Set<String> categoriesSet = this.getCategoriesFromString(category, CATEGORIES_SEPERATOR_REGEX);
		String finalQuery = CATEGORY_PRODUCTS_RETRIEVING_QUERY;
		if (includeZeroInventoryProducts) {
			finalQuery = CATEGORY_PRODUCTS_RETRIEVING_QUERY.concat(inventoryQuery);
		}
		
		if (CollectionUtils.isNotEmpty(categoriesSet)) {

			final Set<String> approvalStatus = new HashSet<>();
			approvalStatus.add(String.valueOf(ArticleApprovalStatus.APPROVED).toLowerCase().trim());
			if (includeUnapprovedProducts) {
				approvalStatus.add(String.valueOf(ArticleApprovalStatus.UNAPPROVED).toLowerCase().trim());
			}
			final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("sslProductCatalog",
					"Online");
			final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(finalQuery);
			flexibleSearchQuery.addQueryParameter("catalogversion", catalogVersion.getPk());
			flexibleSearchQuery.addQueryParameter("approvalstatus", approvalStatus);
			if (includeZeroInventoryProducts) {
				flexibleSearchQuery.addQueryParameter("sizeVariantCatalogVersion", catalogVersion.getPk());
				flexibleSearchQuery.addQueryParameter("sizeVariantApprovalstatus", approvalStatus);
			}
			
			for (final String tempCategory : categoriesSet) {
				flexibleSearchQuery.addQueryParameter("categories", tempCategory.trim());
				final SearchResult<ApparelStyleVariantProductModel> result = flexibleSearchService
						.search(flexibleSearchQuery);
				if (CollectionUtils.isNotEmpty(result.getResult())) {
					categoryProductMap.put(tempCategory, new HashSet<>(result.getResult()));
				} else {
					LOG.warn("products not found for the given category =" + tempCategory.trim());
					categoryProductMap.put(tempCategory, new HashSet<ApparelStyleVariantProductModel>());
				}

			}

		}
		return categoryProductMap;
	}

	/**
	 * @description method for splitting category string to category set.
	 * @param categoriesInString
	 * @param categorySeperatorRegex
	 * @return categoriesSet
	 */
	private Set<String> getCategoriesFromString(final String categoriesInString, final String categorySeperatorRegex) {

		Set<String> categoriesSet = null;
		if (StringUtils.isNotBlank(categoriesInString)) {
			categoriesSet = new HashSet<>();
			final String[] categories = Pattern.compile(categorySeperatorRegex).split(categoriesInString);
			for (final String category : categories) {
				categoriesSet.add(category.trim());
			}
			LOG.info("Extracted categories from category string.." + categoriesSet);
		} else {
			LOG.warn("Found Empty categoryString .......");
		}
		return categoriesSet;
	}

	/**
	 * @description retrieves media from given products.
	 * @param category
	 * @param productSet
	 * @param extractAltImages
	 * @return productMediaMap
	 */
	private Map<String, List<Pair<String, Collection<MediaModel>>>> getMediaFromProducts(final String category,
			final Set<ApparelStyleVariantProductModel> productSet, final Boolean extractAltImages) {

		LOG.info("Extracting  GalleryImages  for " + productSet.size() + " products contains Category[" + category
				+ "] and ExtractAltImages =" + extractAltImages);
		final Map<String, List<MediaContainerModel>> productGalleryImagesMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(productSet)) {
			for (final ProductModel product : productSet) {
				if (CollectionUtils.isEmpty(product.getGalleryImages())) {
					LOG.warn("GalleryImages Not Found for product[  " + product.getCode() + "] with category ["
							+ category + "]");
					continue;
				}
				productGalleryImagesMap.put(product.getCode(), product.getGalleryImages());
			}
		} else {
			LOG.warn("Found Empty Products  while Extracting GalleryImages for categoryCode =" + category);
			return Collections.emptyMap();
		}

		final Map<String, List<Pair<String, Collection<MediaModel>>>> productMediaMap = new HashMap<>();
		for (final Map.Entry<String, List<MediaContainerModel>> entry : productGalleryImagesMap.entrySet()) {
			final List<Pair<String, Collection<MediaModel>>> galleryMediaPairList = new ArrayList<>();
			for (final MediaContainerModel imageGallery : entry.getValue()) {
				if (CollectionUtils.isEmpty(imageGallery.getMedias())) {
					LOG.warn("Media Not Found for product[" + entry.getKey() + "] , category[" + category
							+ " ] and  GalleryImageName[" + imageGallery.getQualifier() + "]");
				}
				if (CollectionUtils.isEmpty(imageGallery.getMedias())
						|| (!extractAltImages.booleanValue() && StringUtils.containsIgnoreCase(
								imageGallery.getQualifier(), CATEGORIES_MEDIA_ALTIMAGES_FILENAME))) {
					continue;
				}
				final Pair<String, Collection<MediaModel>> galleryMediaPair = new Pair<String, Collection<MediaModel>>(
						imageGallery.getQualifier(), imageGallery.getMedias());
				galleryMediaPairList.add(galleryMediaPair);
				productMediaMap.put(entry.getKey(), galleryMediaPairList);
			}
		}

		return productMediaMap;
	}

	/**
	 * @description retrieves image url's for given media
	 * @param category
	 * @param productMediaMap
	 * @return productMediaUrlMap
	 */
	private Map<String, Set<Pair<String, Set<String>>>> getMediaUrl(final String category,
			final Map<String, List<Pair<String, Collection<MediaModel>>>> productMediaMap) {

		LOG.info("Specified MediaFormat =" + CATEGORIES_MEDIA_DOWNLOAD_FORMAT.split(","));
		final Map<String, Set<Pair<String, Set<String>>>> productMediaUrlMap = new HashMap<>();
		for (final Map.Entry<String, List<Pair<String, Collection<MediaModel>>>> entry : productMediaMap.entrySet()) {
			Set<Pair<String, Set<String>>> mediaUrlSet = new HashSet<>();
			for (final Pair<String, Collection<MediaModel>> galleryMediaPair : entry.getValue()) {

				final Pair<String, Set<String>> galleryMediaUrlPair = new Pair<String, Set<String>>(
						galleryMediaPair.getKey(), findMediaUrlForSpecificFormat(
								CATEGORIES_MEDIA_DOWNLOAD_FORMAT.split(","), new HashSet(galleryMediaPair.getValue())));
				mediaUrlSet.add(galleryMediaUrlPair);
			}
			productMediaUrlMap.put(entry.getKey(), mediaUrlSet);
		}
		return productMediaUrlMap;
	}

	/**
	 * @description retrieves image url's for specific format
	 * @param mediaFormatArray
	 * @param mediaSet
	 * @return mediaUrlSet
	 */
	private Set<String> findMediaUrlForSpecificFormat(final String[] mediaFormatArray, final Set<MediaModel> mediaSet) {
		final HashSet<String> mediaUrlSet = new HashSet<String>();
		for (final MediaModel media : mediaSet) {
			for (final String mediaFormat : mediaFormatArray) {
				if (media != null && media.getMediaFormat() != null
						&& StringUtils.isNotEmpty(media.getMediaFormat().getQualifier())
						&& media.getMediaFormat().getQualifier().equalsIgnoreCase(mediaFormat)
						&& StringUtils.isNotEmpty(media.getURL())) {
					mediaUrlSet.add(media.getURL());
				}
			}
		}
		return mediaUrlSet;
	}

	/**
	 * @description uploads medias to amazon server
	 * @param category
	 * @param productMediaUrlMap
	 */
	private void uploadMediaToAmazonServer(final String category,
			final Map<String, Set<Pair<String, Set<String>>>> productMediaUrlMap) {
		LOG.info("uploding media to Amazon for category [" + category + "] and products = "
				+ productMediaUrlMap.keySet() + " started ...");
		final AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, SECRET_ACCESSKEY);
		final AmazonS3 s3client = new AmazonS3Client(credentials);
		s3client.setEndpoint(ENDPOINT);

		final StringBuilder pathBuilder = new StringBuilder("");

		if (StringUtils.isNotBlank(IMAGESKEYPATH)) {
			String path = IMAGESKEYPATH.trim().replaceAll("\\\\", AMAZON_KEYS_SEPERATOR);
			if (path.startsWith(AMAZON_KEYS_SEPERATOR)) {
				path = path.substring(1);
			}
			if (!path.endsWith(AMAZON_KEYS_SEPERATOR)) {
				path = path + AMAZON_KEYS_SEPERATOR;
			}
			pathBuilder.append(path);
		}
		pathBuilder.append(CATEGORIES_FOLEDR_PREFIX).append(category).append(AMAZON_KEYS_SEPERATOR);

		for (final Map.Entry<String, Set<Pair<String, Set<String>>>> entry : productMediaUrlMap.entrySet()) {
			for (Pair<String, Set<String>> mediaUrlpair : entry.getValue()) {
				String finalPath=pathBuilder.toString();
				for (final String mediaUrl : mediaUrlpair.getValue()) {
					finalPath=finalPath+mediaUrlpair.getKey();
					try{

						ObjectMetadata metadata = new ObjectMetadata();
						metadata.setContentLength(
								Long.valueOf(IOUtils.toByteArray(new URL(mediaUrl).openStream()).length).longValue());

						final PutObjectResult result = s3client.putObject(
								new PutObjectRequest(BUCKETID, finalPath, new URL(mediaUrl).openStream(), metadata));
						LOG.info("Etag:" + result.getETag() + "-->" + result);
					} catch (final Exception e) {
						LOG.info(e);
					}
				}
			}
		}

		LOG.info("completed  Media Uploding For Category [" + category + "] ");
	}

}
