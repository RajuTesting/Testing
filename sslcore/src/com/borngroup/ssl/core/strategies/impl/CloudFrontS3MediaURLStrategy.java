package com.borngroup.ssl.core.strategies.impl;

import de.hybris.platform.amazon.media.services.S3StorageServiceFactory;
import de.hybris.platform.amazon.media.url.S3MediaURLStrategy;
import de.hybris.platform.media.MediaSource;
import de.hybris.platform.media.storage.MediaStorageConfigService;
import de.hybris.platform.media.url.MediaURLStrategy;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.common.base.Preconditions;

public class CloudFrontS3MediaURLStrategy implements MediaURLStrategy {
    public static final Logger LOG = Logger.getLogger(S3MediaURLStrategy.class);
    public static final String SIGNED_KEY = "url.signed";
    public static final String SIGNED_VALID_FOR_KEY = "url.signed.validFor";
    private static final Integer DEFAULT_TIME_TO_LIVE = Integer.valueOf(10);
    private static final Boolean DEFAULT_USE_SIGNED = Boolean.TRUE;
    // private static final String CDN_URL_QUERY_PARAMS = "media.folder.%1$s.cdn.query.params";
    private final ConcurrentMap<String, List<String>> bucketCache = new ConcurrentHashMap();

    private final S3StorageServiceFactory s3StorageServiceFactory;

    public CloudFrontS3MediaURLStrategy(final S3StorageServiceFactory s3StorageServiceFactory) {
        this.s3StorageServiceFactory = s3StorageServiceFactory;
    }

    @Override
    public String getUrlForMedia(final MediaStorageConfigService.MediaFolderConfig config, final MediaSource media) {
        Preconditions.checkArgument(config != null, "Folder config is required to perform this operation");
        Preconditions.checkArgument(media != null, "MediaSource is required to perform this operation");

        String url = "";
        AmazonS3 s3Service = null;
        String bucket = null;
        try {
            s3Service = this.s3StorageServiceFactory.getS3ServiceForFolder(config);
            bucket = this.s3StorageServiceFactory.getS3BucketForFolder(config, s3Service);
            s3Service.setEndpoint(Config.getString("ssl.endpoint", config.getParameter("endpoint")));
            if (isConfiguredForSignedUrls(config)) {
                url = s3Service.generatePresignedUrl(bucket, media.getLocation(), getTimeToLiveForUrl(config)).toString();
            } else {
                url = ((AmazonS3Client) s3Service).getUrl(bucket, media.getLocation()).toString();
            }
            s3Service.setEndpoint(config.getParameter("endpoint"));
        } catch (final com.amazonaws.AmazonClientException e) {
            if (null != s3Service) {
                s3Service.setEndpoint(config.getParameter("endpoint"));
            }
            logDebug(media, e);
        }

        final String mime = String.valueOf(media.getMime());

        // apply cdn aliases
        url = getCloudFrontURL(config, mime, bucket, url);

        // apply params
        url = applyCDNQueryParams(config, mime, url);

        return url;
    }

    private String getCloudFrontURL(final MediaStorageConfigService.MediaFolderConfig config, final String mime, final String bucket, final String s3URL) {
        if (s3URL == null || mime == null || !mime.startsWith("image/")) {
            return s3URL;
        }

        final String folderQualifier = config.getFolderQualifier();

        final List<String> aliasList = getBucketAlias(folderQualifier);
        if (aliasList.isEmpty()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No Url translation for folder : " + folderQualifier);
            }
            return s3URL;
        }

        final Random random = new Random();
        final int randomNumber = random.nextInt(aliasList.size());

        final String alias = aliasList.get(randomNumber);
        final String URL = s3URL.replace("://" + bucket, "://" + alias);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Translated s3URL : " + s3URL + " to URL : " + URL);
        }

        return URL;
    }

    private String applyCDNQueryParams(final MediaStorageConfigService.MediaFolderConfig config, final String mime, final String s3URL) {
        if (s3URL == null || mime == null || !mime.startsWith("image/png")) {
            return s3URL;
        }

        final String folderQualifier = config.getFolderQualifier();
        final String queryParams = Config.getString(String.format("media.folder.%1$s.cdn.params", folderQualifier), null);
        if (queryParams == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No cdn query params for folder : " + folderQualifier);
            }
            return s3URL;
        }

        return s3URL + "?" + queryParams;
    }

    private List<String> getBucketAlias(final String folderQualifier) {
        List<String> aliasList = bucketCache.get(folderQualifier);
        if (aliasList == null) {
            aliasList = new LinkedList<String>();
            String alias = null;

            for (int i = 1; i <= 5; i++) {
                alias = Config.getString(String.format("media.folder.%1$s.cdn.alias." + i, folderQualifier), null);
                if (alias != null) {
                    aliasList.add(alias);
                }
            }

            bucketCache.put(folderQualifier, aliasList);
        }

        return aliasList;
    }

    private boolean isConfiguredForSignedUrls(final MediaStorageConfigService.MediaFolderConfig config) {
        return config.getParameter("url.signed", Boolean.class, DEFAULT_USE_SIGNED).booleanValue();
    }

    private Date getTimeToLiveForUrl(final MediaStorageConfigService.MediaFolderConfig config) {
        final Calendar cal = Calendar.getInstance();

        final Integer configuredTimeToLive = config.getParameter("url.signed.validFor", Integer.class, DEFAULT_TIME_TO_LIVE);
        cal.add(12, configuredTimeToLive.intValue());
        return cal.getTime();
    }

    private void logDebug(final MediaSource media, final Exception exc) {
        final String msg = "Cannot render public url for media location: " + media.getLocation() + " stored in Amazon S3 storage.";
        LOG.error(msg);
        if (LOG.isDebugEnabled()) {
            LOG.debug(msg, exc);
        }
    }
}
