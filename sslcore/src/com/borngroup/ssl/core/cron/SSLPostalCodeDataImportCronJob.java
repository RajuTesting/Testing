/**
 *
 */
package com.borngroup.ssl.core.cron;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author gokulpandey
 *
 */
public class SSLPostalCodeDataImportCronJob extends AbstractJobPerformable<CronJobModel> {

    /**
     * 
     */
    private static final String EXCEPTION = "Exception: ";

    private static final Logger LOG = Logger.getLogger(SSLPostalCodeDataImportCronJob.class);

    private String directory;

    private String url;

    @Override
    public PerformResult perform(final CronJobModel arg0) {
        LOG.info("      DownLoading Postal Code Data From Indian Govt Website and the download url :" + url);
        if (StringUtils.isBlank(url)) {
            LOG.info("There is no url specified to download file from :" + url);
            return null;
        } else if (StringUtils.isBlank(directory)) {
            LOG.info("There is no valid directory specified to download file on:" + directory);
            return null;
        } else {
            ReadableByteChannel rbc = null;
            try {
                final String fileName = url.substring(url.lastIndexOf('/'));
                final URL website = new URL(url);
                final InputStream is = website.openStream();
                rbc = Channels.newChannel(is);
                final File yourFile = new File(directory + File.separator + fileName);
                yourFile.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(yourFile)) {
                    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    fos.close();
                    rbc.close();
                } catch (final Exception e) {
                    LOG.error("Exception while transferring data" + e.getMessage());
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(EXCEPTION + e);
                    }
                }
            } catch (final IOException e) {
                LOG.error("Some error occured while downloading the postal code file from :" + url + " to the directory :" + directory
                        + "with error :" + e);
            } finally {
                try {
                    if (rbc != null) {
                        rbc.close();
                    }
                } catch (final IOException e) {
                    LOG.error("Exception while importing Postal Code Data" + e.getMessage());
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(EXCEPTION + e);
                    }
                }
            }

        }
        return null;
    }

    /**
     * @param directory
     *        the directory to set
     */
    public void setDirectory(final String directory) {
        this.directory = directory;
    }

    /**
     * @param url
     *        the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }//

}
