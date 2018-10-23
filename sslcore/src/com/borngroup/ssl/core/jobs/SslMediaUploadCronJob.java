/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFolderModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.mediaconversion.MediaConversionService;
import de.hybris.platform.mediaconversion.model.ConversionGroupModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;
import de.hybris.platform.variants.model.VariantProductModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SSLCollectionsModel;
import com.borngroup.ssl.core.services.SslMediaContainerService;

/**
 * @author Sriharsha
 *
 */
public class SslMediaUploadCronJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(SslMediaUploadCronJob.class);

    @Resource
    private ModelService modelService;

    @Resource
    private MediaService mediaService;

    @Resource(name = "mediaContainerService")
    SslMediaContainerService mediaContainerService;

    @Resource
    CatalogVersionService catalogVersionService;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private MediaConversionService mediaConversionService;

    @Resource
    private ProductService productService;

    private static String DATA_DIR = Config.getParameter("HYBRIS_DATA_DIR");

    /*
     * (non-Javadoc)
     *
     * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform (de.hybris.platform.cronjob.model.CronJobModel )
     */
    @Override
    public PerformResult perform(final CronJobModel cronjob) {
        LOG.info("Media Upload Cronjob Started.");

        final String alternativePath = cronjob.getAlternativeDataSourceID();
        String absolutePath = null;
        final Set<String> successFileNames = new HashSet<String>();
        final Set<String> errorFileNames = new HashSet<String>();

        List<File> imageList = new ArrayList();

        try {
            final File mediaFileFolder = loadPath(alternativePath);
            imageList = loadFiles(mediaFileFolder);
            absolutePath = mediaFileFolder.getAbsolutePath();
            LOG.info(absolutePath);
            for (final File file : imageList) {
                try {
                    final String extension = file.getName().substring(file.getName().lastIndexOf('.') + 1, file.getName().length());
                    final ProductModel product = isProperFile(file, extension);
                    if (product == null) {
                        errorFileNames.add(file.getName());
                        // moveFileToError(file.getName(), alternativePath, absolutePath);
                        continue;
                    } else {
                        if (product instanceof SSLCollectionsModel) {
                            processFileForCollection(file, extension, (SSLCollectionsModel) product);
                        } else {
                            processFile(file, extension, (ApparelStyleVariantProductModel) product);
                        }
                        successFileNames.add(file.getName());
                        moveFileToDone(file.getName(), alternativePath, absolutePath);
                    }
                } catch (final Exception e) {

                    LOG.info("Please check the file whether it matches agreed nomenclature " + file.getName() + " - Exception : " + e);
                    // moveFileToError(file.getName(), alternativePath, absolutePath);
                    errorFileNames.add(file.getName());
                }
            }

        } catch (final Exception exception) {
            LOG.info(exception.getMessage());
        }

        /*
         * for (final String successFileName : successFileNames) { moveFileToDone(successFileName, alternativePath, absolutePath); }
         *
         * for (final String errorFileName : errorFileNames) { moveFileToError(errorFileName, alternativePath, absolutePath); }
         */

        sendMail(successFileNames, errorFileNames);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    /**
     * @param file
     * @throws IOException
     */
    private void processFile(final File file, final String extension, final ApparelStyleVariantProductModel product) throws IOException {
        if (isImageFile(extension)) {
            final MediaModel mediaModel = createMediaForUpload(file, SslCoreConstants.IMAGE_STORE_LOCATION, SslCoreConstants.IMAGE_FORMAT,
                    SslCoreConstants.MIME_TYPE);
            processImage(file.getName(), product, mediaModel);
        } else {
            final MediaModel mediaModel = createMediaForUpload(file, SslCoreConstants.VIDEO_STORE_LOCATION, SslCoreConstants.VIDEO_FORMAT,
                    SslCoreConstants.VIDEO_MIME_TYPE);
            processVideo(product, mediaModel);
        }
    }

    /**
     * @param file
     * @throws IOException
     */
    private void processFileForCollection(final File file, final String extension, final SSLCollectionsModel product) throws IOException {
        if (isImageFile(extension)) {
            final MediaModel mediaModel = createMediaForUpload(file, SslCoreConstants.IMAGE_STORE_LOCATION, SslCoreConstants.IMAGE_FORMAT,
                    SslCoreConstants.MIME_TYPE);
            processImageForCollection(file.getName(), product, mediaModel);
        } else {
            final MediaModel mediaModel = createMediaForUpload(file, SslCoreConstants.VIDEO_STORE_LOCATION, SslCoreConstants.VIDEO_FORMAT,
                    SslCoreConstants.VIDEO_MIME_TYPE);
            processVideoForCollection(product, mediaModel);
        }
    }

    /**
     * @throws IOException
     *
     */
    private void processImage(final String fileName, final ApparelStyleVariantProductModel product, final MediaModel mediaModel)
            throws IOException {
        if (!fileName.contains(SslCoreConstants.SEPARATOR_ALT) && !fileName.contains(SslCoreConstants.SEPARATOR_SWATCH)) {
            processMainImage(fileName, product, mediaModel);
        } else if (fileName.contains(SslCoreConstants.SEPARATOR_ALT)) {
            processAltImage(fileName, product, mediaModel);
        } else {
            // swatch file
            product.setThumbnail(mediaModel);
            modelService.save(product);
        }
    }

    /**
     * @throws IOException
     *
     */
    private void processImageForCollection(final String fileName, final SSLCollectionsModel product, final MediaModel mediaModel)
            throws IOException {
        if (!fileName.contains(SslCoreConstants.SEPARATOR_ALT) && !fileName.contains(SslCoreConstants.SEPARATOR_SWATCH)) {
            processMainImageForCollection(fileName, product, mediaModel);
        } else if (fileName.contains(SslCoreConstants.SEPARATOR_ALT)) {
            processAltImageForCollection(fileName, product, mediaModel);
        } else {
            // swatch file
            product.setThumbnail(mediaModel);
            modelService.save(product);
        }
    }

    /**
     *
     */
    private void processAltImage(final String fileName, final ApparelStyleVariantProductModel product, final MediaModel mediaModel) {
        final MediaContainerModel mediaContainer = createMediaContainerforProduct(fileName, getCatalogVersionModel(), mediaModel);
        // alt images
        final List<MediaContainerModel> mediaContainerList = getMediaContainerList(product);
        mediaContainerList.add(mediaContainer);
        product.setGalleryImages(mediaContainerList);
        getBaseProduct(product).setGalleryImages(mediaContainerList);
        modelService.save(product);

    }

    /**
    *
    */
    private void processAltImageForCollection(final String fileName, final SSLCollectionsModel product, final MediaModel mediaModel) {
        final MediaContainerModel mediaContainer = createMediaContainerforProduct(fileName, getCatalogVersionModel(), mediaModel);
        // alt images
        final List<MediaContainerModel> mediaContainerList = getMediaContainerList(product);
        mediaContainerList.add(mediaContainer);
        product.setGalleryImages(mediaContainerList);
        getBaseProduct(product).setGalleryImages(mediaContainerList);
        modelService.save(product);

    }

    private List<MediaContainerModel> getMediaContainerList(final ProductModel product) {
        final List<MediaContainerModel> galleryImages = new ArrayList<MediaContainerModel>();
        if (product.getGalleryImages() != null & !product.getGalleryImages().isEmpty()) {
            galleryImages.addAll(product.getGalleryImages());
        }
        return galleryImages;
    }

    /**
     *
     */
    private void processMainImage(final String fileName, final ApparelStyleVariantProductModel product, final MediaModel mediaModel) {

        final MediaContainerModel mediaContainer = createMediaContainerforProduct(fileName, getCatalogVersionModel(), mediaModel);

        LOG.info("Product available with product code " + product.getCode());
        MediaModel picture = null;
        MediaModel thumbnailImage = null;
        final List<MediaModel> otherImages = new ArrayList<MediaModel>();
        final List<MediaModel> thumbnails = new ArrayList<MediaModel>();
        final List<MediaModel> normals = new ArrayList<MediaModel>();
        final List<MediaContainerModel> galleryImages = getMediaContainerList(product);
        galleryImages.add(mediaContainer);

        for (final MediaModel media : mediaContainer.getMedias()) {
            final String mediaCode = media.getCode();
            if (mediaCode.contains(SslCoreConstants.FORMAT_NORMAL)) {
                picture = media;
                normals.add(media);
                otherImages.add(media);
            } else if (mediaCode.contains(SslCoreConstants.FORMAT_SMALL)) {
                thumbnailImage = media;
                thumbnails.add(media);
                otherImages.add(media);
            } else {
                otherImages.add(media);
            }

            /*
             * if (mediaCode.contains(SslCoreConstants.FORMAT_LARGE) || mediaCode.contains(SslCoreConstants.FORMAT_VERYSMALL) ||
             * mediaCode.contains(SslCoreConstants.FORMAT_TINY))
             */
        }
        product.setPicture(picture);
        product.setThumbnail(thumbnailImage);
        product.setOthers(otherImages);
        product.setNormal(normals);
        product.setThumbnails(thumbnails);
        product.setGalleryImages(galleryImages);

        getBaseProduct(product).setPicture(picture);
        getBaseProduct(product).setThumbnail(thumbnailImage);
        getBaseProduct(product).setOthers(otherImages);
        getBaseProduct(product).setNormal(normals);
        getBaseProduct(product).setThumbnails(thumbnails);
        getBaseProduct(product).setGalleryImages(galleryImages);
        getBaseProduct(product).setApprovalStatus(ArticleApprovalStatus.CHECK);

        modelService.save(product);
        final ProductModel baseProduct = product.getBaseProduct();
        if (baseProduct.getPicture() == null) {
            baseProduct.setPicture(product.getPicture());
            modelService.save(baseProduct);
        }

    }

    /**
    *
    */
    private void processMainImageForCollection(final String fileName, final SSLCollectionsModel product, final MediaModel mediaModel) {

        final MediaContainerModel mediaContainer = createMediaContainerforProduct(fileName, getCatalogVersionModel(), mediaModel);

        LOG.info("Product available with product code " + product.getCode());
        MediaModel picture = null;
        MediaModel thumbnailImage = null;
        final List<MediaModel> otherImages = new ArrayList<MediaModel>();
        final List<MediaModel> thumbnails = new ArrayList<MediaModel>();
        final List<MediaModel> normals = new ArrayList<MediaModel>();
        final List<MediaContainerModel> galleryImages = getMediaContainerList(product);
        galleryImages.add(mediaContainer);

        for (final MediaModel media : mediaContainer.getMedias()) {
            final String mediaCode = media.getCode();
            if (mediaCode.contains(SslCoreConstants.FORMAT_NORMAL)) {
                picture = media;
                normals.add(media);
                otherImages.add(media);
            } else if (mediaCode.contains(SslCoreConstants.FORMAT_SMALL)) {
                thumbnailImage = media;
                thumbnails.add(media);
                otherImages.add(media);
            } else {
                otherImages.add(media);
            }

            /*
             * if (mediaCode.contains(SslCoreConstants.FORMAT_LARGE) || mediaCode.contains(SslCoreConstants.FORMAT_VERYSMALL) ||
             * mediaCode.contains(SslCoreConstants.FORMAT_TINY))
             */
        }
        product.setPicture(picture);
        product.setThumbnail(thumbnailImage);
        product.setOthers(otherImages);
        product.setNormal(normals);
        product.setThumbnails(thumbnails);
        product.setGalleryImages(galleryImages);

        getBaseProduct(product).setPicture(picture);
        getBaseProduct(product).setThumbnail(thumbnailImage);
        getBaseProduct(product).setOthers(otherImages);
        getBaseProduct(product).setNormal(normals);
        getBaseProduct(product).setThumbnails(thumbnails);
        getBaseProduct(product).setGalleryImages(galleryImages);
        getBaseProduct(product).setApprovalStatus(ArticleApprovalStatus.CHECK);

        modelService.save(product);

    }

    protected ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    /**
     * @return
     */
    private CatalogVersionModel getCatalogVersionModel() {
        return catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION);

    }

    /**
     * @param file
     * @throws IOException
     */
    private MediaModel createMediaForUpload(final File file, final String location, final String fileFormat, final String mimeType)
            throws IOException {
        final InputStream inStream = new FileInputStream(file);
        final MediaModel mediaModel = modelService.create(MediaModel.class);
        final MediaFolderModel configFolderModel = mediaService.getFolder(location);
        mediaModel.setCode(file.getName());
        final MediaFormatModel format = mediaService.getFormat(fileFormat);
        mediaModel.setMediaFormat(format);
        mediaModel.setFolder(configFolderModel);
        mediaModel.setCatalogVersion(getCatalogVersionModel());
        mediaModel.setMime(mimeType);
        try {
            final MediaModel oldMediaModel = mediaService.getMedia(getCatalogVersionModel(), file.getName());
            modelService.remove(oldMediaModel.getPk());
        } catch (final Exception e) {
            LOG.info(file.getName() + " media not exist. Creating new Media" + e);
        }
        modelService.save(mediaModel);
        mediaService.setStreamForMedia(mediaModel, inStream);
        inStream.close();

        return mediaModel;

    }

    /**
     *
     */
    private void processVideo(final ApparelStyleVariantProductModel product, final MediaModel mediaModel) {
        final List<MediaModel> videoList = new ArrayList<MediaModel>(product.getVideos());
        videoList.add(mediaModel);
        product.setVideos(videoList);
        modelService.save(product);

    }

    /**
    *
    */
    private void processVideoForCollection(final SSLCollectionsModel product, final MediaModel mediaModel) {
        final List<MediaModel> videoList = new ArrayList<MediaModel>(product.getVideos());
        videoList.add(mediaModel);
        product.setVideos(videoList);
        modelService.save(product);

    }

    /**
     * @return
     */
    private ProductModel isProperFile(final File file, final String extension) {

        if (!isImageFile(extension) && !isVideoFile(extension)) {
            LOG.info(file.getName() + " is not an Image(jpg/png) or video(mp4) file");
        }
        // check the resolution of image file
        else if (!isImageResolutionValid(file)) {
            LOG.info(file.getName() + " Image file resolution not proper");
        } else {
            try {
                final String fileName = getImageFileName(file);
                return productService.getProductForCode(
                        catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME, SslCoreConstants.CATALOG_VERSION),
                        getStyleVariantProductCode(fileName));
            } catch (final Exception e) {
                LOG.info("No product found with product code. " + file.getName(), e);
                LOG.info("File cannot be proccessed " + file.getName() + "as product with filename not exist.");
            }
        }
        return null;
    }

    private void moveFileToDone(final String fileName, final String alternateivePath, final String absolutePath) {
        try {
            // final Path sourcePath1 = getSourcePath(fileName,
            // alternateivePath);
            final Path sourcePath1 = Paths.get(absolutePath + SslCoreConstants.SEPARATOR + fileName);

            final Path targetPath = Paths
                    .get(Config.getParameter(SslCoreConstants.MEDIA_DONE_PATH) + SslCoreConstants.SEPARATOR + fileName);
            Files.move(sourcePath1, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (final Exception e1) {
            LOG.info(e1.getMessage());
        }

    }

    private void moveFileToError(final String fileName, final String alternativePath, final String absolutePath) {
        try {
            // final Path sourcePath1 = getSourcePath(fileName,
            // alternativePath);
            final Path sourcePath1 = Paths.get(absolutePath + SslCoreConstants.SEPARATOR + fileName);
            final Path targetPath = Paths.get(Config.getParameter(SslCoreConstants.MEDIA_ERROR_PATH) + SslCoreConstants.SEPARATOR
                    + fileName);
            Files.move(sourcePath1, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (final Exception exp) {
            LOG.info(exp.getMessage());
        }

    }

    private ConversionGroupModel getConversionModel() {
        ConversionGroupModel conversionGroupModel = modelService.create(ConversionGroupModel.class);
        conversionGroupModel.setCode(SslCoreConstants.CONVERSION_GROUP_CODE);
        conversionGroupModel = flexibleSearchService.getModelByExample(conversionGroupModel);
        return conversionGroupModel;
    }

    private String getStyleVariantProductCode(final String fileName) {
        String productCode = fileName;
        if (fileName.contains(SslCoreConstants.SEPARATOR_ALT) || fileName.contains(SslCoreConstants.SEPARATOR_SWATCH)
                || fileName.contains(SslCoreConstants.SEPARATOR_VIDEO)) {
            productCode = fileName.substring(0,
                    fileName.indexOf(SslCoreConstants.SEPARATOR_UNDERSCORE, fileName.indexOf(SslCoreConstants.SEPARATOR_UNDERSCORE) + 1));
        } else {
            productCode = fileName;
        }
        return productCode;
    }

    private String getImageFileName(final File file) {
        final String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
        return fileName;
    }

    private String getImageNameWithoutExtension(final String fileName) {
        final String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        return nameWithoutExtension;
    }

    private MediaContainerModel createMediaContainerforProduct(final String fileName, final CatalogVersionModel catalogModelVersionModel,
            final MediaModel mediaModel)

    {
        MediaContainerModel mediaContainerForJpg = null;
        try {
            mediaContainerForJpg = mediaContainerService.getMediaContainerForQualifier(catalogModelVersionModel,
                    getImageNameWithoutExtension(fileName).concat(".jpg"));
        } catch (final Exception e) {
            LOG.info(getImageNameWithoutExtension(fileName) + "jpg mediaContainer not exist");
        }
        if (mediaContainerForJpg != null) {
            for (final MediaModel media : mediaContainerForJpg.getMedias()) {
                modelService.remove(media.getPk());
            }
            modelService.remove(mediaContainerForJpg);
        }

        MediaContainerModel mediaContainerForPng = null;
        try {
            mediaContainerForPng = mediaContainerService.getMediaContainerForQualifier(catalogModelVersionModel,
                    getImageNameWithoutExtension(fileName).concat(".png"));
        } catch (final Exception e1) {
            LOG.info(getImageNameWithoutExtension(fileName) + " png mediaContainer not exist");
        }
        if (mediaContainerForPng != null) {
            for (final MediaModel media : mediaContainerForPng.getMedias()) {
                modelService.remove(media.getPk());
            }
            modelService.remove(mediaContainerForPng);
        }

        MediaContainerModel mediaContainer = null;
        mediaContainer = modelService.create(MediaContainerModel.class);
        mediaContainer.setQualifier(fileName);
        mediaContainer.setCatalogVersion(catalogModelVersionModel);
        mediaContainer.setConversionGroup(getConversionModel());
        mediaContainer.setMedias(Collections.singletonList(mediaModel));
        modelService.save(mediaContainer);

        if (SslCoreConstants.UNCONVERTED.equalsIgnoreCase(mediaContainer.getConversionStatus().getCode())) {
            try {
                mediaConversionService.convertMedias(mediaContainer);
            } catch (final Exception exception) {
                LOG.info(exception.getMessage());
            }
        }
        return mediaContainer;
    }

    private List<File> loadFiles(final File mediaFileFolder) {
        List<File> mediaList = new ArrayList<File>();

        if (mediaFileFolder.isDirectory()) {
            mediaList = new ArrayList<File>();
            final File[] files = mediaFileFolder.listFiles();

            for (int i = 0; i < files.length; i++) {
                final File file = files[i];
                mediaList.add(file);
            }
        }
        return mediaList;
    }

    private boolean isImageFile(final String extension) {
        boolean isImageFile = false;

        if (SslCoreConstants.MEDIA_PATTERN.equalsIgnoreCase(extension) || SslCoreConstants.MEDIA_PATTERN_PNG.equalsIgnoreCase(extension)) {
            isImageFile = true;
        }
        return isImageFile;
    }

    private boolean isVideoFile(final String extension) {
        boolean isVideoFile = false;

        if (SslCoreConstants.VIDEO_PATTERN.equalsIgnoreCase(extension)) {
            isVideoFile = true;
        }

        return isVideoFile;
    }

    private boolean isImageResolutionValid(final File file) {
        boolean isValidResolution = false;
        int imagewidth;
        int imageheight;
        try {
            final InputStream stream = new FileInputStream(file);
            final BufferedImage bimg = ImageIO.read(stream);
            imagewidth = bimg.getWidth();
            imageheight = bimg.getHeight();
            stream.close();
        } catch (final IOException e1) {
            return false;
        }

        if (file.getName().contains(SslCoreConstants.SEPARATOR_ALT)) {
            // Alt image resolution check

            final int altImageWidth = Integer.parseInt(Config.getParameter(SslCoreConstants.ALT_MEDIA_WIDTH));
            final int altImageHeigth = Integer.parseInt(Config.getParameter(SslCoreConstants.ALT_MEDIA_HEIGTH));

            if (imagewidth >= altImageWidth && imageheight >= altImageHeigth) {
                isValidResolution = true;
            }

        } else if (file.getName().contains(SslCoreConstants.SEPARATOR_SWATCH)) {
            // Swatch image resolution check

            final int swatchImageWidth = Integer.parseInt(Config.getParameter(SslCoreConstants.SWATCH_MEDIA_WIDTH));
            final int swatchImageHeigth = Integer.parseInt(Config.getParameter(SslCoreConstants.SWATCH_MEDIA_HEIGTH));

            if (imagewidth >= swatchImageWidth && imageheight >= swatchImageHeigth) {
                isValidResolution = true;
            }
        } else {
            // Main image resolution check

            final int mainImageWidth = Integer.parseInt(Config.getParameter(SslCoreConstants.MAIN_MEDIA_WIDTH));
            final int mainImageHeigth = Integer.parseInt(Config.getParameter(SslCoreConstants.MAIN_MEDIA_HEIGTH));

            if (imagewidth >= mainImageWidth && imageheight >= mainImageHeigth) {
                isValidResolution = true;
            }
        }
        return isValidResolution;

    }

    protected void sendMail(final Set<String> successFileNames, final Set<String> errorFileNames) {
        boolean isSuccessFile = false;
        boolean isErrorFile = false;

        final StringBuffer emailMessage = new StringBuffer();
        emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_FIRST_LINE
                + SslCoreConstants.PARAGRAPH_TAG_END);

        final String PRODUCT_IMPORT_FROM_ADDRESS = Config.getParameter(SslCoreConstants.IMAGE_IMPORT_FROM_ADDRESS) != null
                && !Config.getParameter(SslCoreConstants.IMAGE_IMPORT_FROM_ADDRESS).isEmpty() ? Config
                .getParameter(SslCoreConstants.IMAGE_IMPORT_FROM_ADDRESS) : SslCoreConstants.IMAGE_IMPORT_FROM_ADDRESS_FALLBACK;

        final String PRODUCT_IMPORT_TO_ADDRESS = Config.getParameter(SslCoreConstants.IMAGE_IMPORT_TO_ADDRESS) != null
                && !Config.getParameter(SslCoreConstants.IMAGE_IMPORT_TO_ADDRESS).isEmpty() ? Config
                .getParameter(SslCoreConstants.IMAGE_IMPORT_TO_ADDRESS) : SslCoreConstants.IMAGE_IMPORT_TO_ADDRESS_FALLBACK;

        List<String> ToAddresses;
        final List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();
		try (FileWriter writer = new FileWriter(
				DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH + SslCoreConstants.UPLOADED_IMAGE_FILE_NAME);) {


            if (successFileNames != null && successFileNames.size() > 0) {
                isSuccessFile = true;

                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_IMAGE_SECOND_LINE
                        + SslCoreConstants.PARAGRAPH_TAG_END);

                for (final String fileName : successFileNames) {
                    writer.append(fileName);

                    writer.append(SslCoreConstants.NEW_LINE_CHARACTER);

                }
            } else {
                emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_IMAGE_ALT_SECOND_LINE
                        + SslCoreConstants.PARAGRAPH_TAG_END);
            }

            if (errorFileNames != null && errorFileNames.size() > 0) {
                isErrorFile = true;

                try (FileWriter failedProdWriter = new FileWriter(DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH
                        + SslCoreConstants.UPLOADED_FAILED_IMAGE_FILE_NAME)) {

                    emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_IMAGE_ALT2_SECOND_LINE
                            + SslCoreConstants.PARAGRAPH_TAG_END);

                    for (final String errorFileName : errorFileNames) {
                        failedProdWriter.append(errorFileName);

                        failedProdWriter.append(SslCoreConstants.NEW_LINE_CHARACTER);

                    }
                } catch (final Exception e) {

                    LOG.error("File Writer error while attaching success image attachment");
                }

            }

            emailMessage.append(SslCoreConstants.PARAGRAPH_TAG_START + SslCoreConstants.EMAIL_BODY_THANKS
                    + SslCoreConstants.PARAGRAPH_TAG_END);
        } catch (final IOException exception) {
            LOG.error(exception.getMessage());
            LOG.error("File Writer error while creating updated products attachment");
        }
        try {
            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail(); // creates a mail instance with
                                                                                       // set mail properties read from
                                                                                       // project.properties
                                                                                       // Added fix for multiple to
                                                                                       // addresses
            if (PRODUCT_IMPORT_TO_ADDRESS.contains(",")) {
                ToAddresses = Arrays.asList(PRODUCT_IMPORT_TO_ADDRESS.split(","));
                final Iterator<String> it = ToAddresses.iterator();

                while (it.hasNext()) {
                    final InternetAddress emailAddr = new InternetAddress(it.next());
                    internetAddressList.add(emailAddr);
                }
                htmlEmail.setTo(internetAddressList);
                // Added fix for multiple to addresses
            } else {
                htmlEmail.addTo(PRODUCT_IMPORT_TO_ADDRESS);
            }
            htmlEmail.setSubject(CollectionUtils.size(successFileNames) + SslCoreConstants.EMAIL_SUBJECT_IMAGE);

            if (isSuccessFile) {

                final String filename = DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH + SslCoreConstants.UPLOADED_IMAGE_FILE_NAME;
                final DataSource source = new FileDataSource(filename);
                htmlEmail.attach(source, SslCoreConstants.UPLOADED_IMAGE_FILE_NAME, SslCoreConstants.UPLOADED_IMAGE_FILE_NAME);
            }
            if (isErrorFile) {
                final String filename = DATA_DIR + SslCoreConstants.UNRESOLVED_FILE_PATH + SslCoreConstants.UPLOADED_FAILED_IMAGE_FILE_NAME;
                final DataSource source = new FileDataSource(filename);
                htmlEmail
                        .attach(source, SslCoreConstants.UPLOADED_FAILED_IMAGE_FILE_NAME, SslCoreConstants.UPLOADED_FAILED_IMAGE_FILE_NAME);
            }

            htmlEmail.setHtmlMsg(emailMessage.toString());
            htmlEmail.setFrom(PRODUCT_IMPORT_FROM_ADDRESS);

            htmlEmail.send();
        } catch (final EmailException ex) {

            LOG.error("Email Exception");
            LOG.error(ex.getMessage());
        } catch (final AddressException ex) {
            LOG.error("Address Exception");
            LOG.error(ex.getMessage());
        }

    }

    Path getSourcePath(final String fileName, final String alternativePath) {
        Path sourcePath = null;
        if (!StringUtils.isEmpty(alternativePath)) {
            try {
                sourcePath = !StringUtils.isEmpty(alternativePath) ? Paths.get(Config.getParameter(alternativePath
                        + SslCoreConstants.SEPARATOR + fileName)) : Paths.get(Config.getParameter(SslCoreConstants.MEDIA_FEED_PATH)
                        + SslCoreConstants.SEPARATOR + fileName);
            } catch (final Exception e1) {
                LOG.info(e1.getMessage());
            }
        }
        return sourcePath;
    }

    File loadPath(final String alternativePath) {
        File mediaFileFolder = null;

        if (!StringUtils.isEmpty(alternativePath)) {

            mediaFileFolder = new File(alternativePath);
            LOG.info("Secondary Media Path for Media Upload " + mediaFileFolder.getAbsolutePath());
        } else {
            mediaFileFolder = new File(Config.getParameter(SslCoreConstants.MEDIA_FEED_PATH));
        }
        return mediaFileFolder;
    }

}
