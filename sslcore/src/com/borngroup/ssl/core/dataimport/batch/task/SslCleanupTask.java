package com.borngroup.ssl.core.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupTask;
import de.hybris.platform.util.Config;

import java.io.File;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * The Class SslCleanupTask to clean all the csv files and image files from hot folder after successful task completion.
 *
 * Modified by: hemani@nagarro.com
 *
 * @author SSL
 */
public class SslCleanupTask extends CleanupTask {

    /** The cleanup helper. */
    private CleanupHelper cleanupHelper;

    /** The Constant LOG variable for logs. */
    private static final Logger LOG = Logger.getLogger(AbstractImpexRunnerTask.class);

    /** The Constant DATA_DIR to determine hybris data directory. */
    private static final String DATA_DIR = Config.getParameter("HYBRIS_DATA_DIR");

    /** The file path. */
    private String filePath;

    /** jpg extension. */
    private static final String JPG_EXT = ".jpg";

    /** jpeg extension. */
    private static final String JPEG_EXT = ".jpeg";

    /** png extension. */
    private static final String PNG_EXT = ".png";

    /** gif extension. */
    private static final String GIF_EXT = ".gif";

    @Override
    public BatchHeader execute(final BatchHeader header) {
        cleanupHelper.cleanup(header, false);

        if (header != null) {
            deleteImageFiles();
        }
        // Call function to delete dummy file
        deleteDummyFile();

        return null;
    }

    /**
     * Deletes image files present in processing folder.
     */
    private void deleteImageFiles() {

        final File directory = new File(getFilePath());

        final File[] files = directory.listFiles();
        if (ArrayUtils.isNotEmpty(files)) {
            for (final File file : files) {
                final String fileName = file.getName();
                if (fileName.endsWith(JPG_EXT) || fileName.endsWith(JPEG_EXT) || fileName.endsWith(PNG_EXT) || fileName.endsWith(GIF_EXT)) {
                    file.delete();
                    LOG.info("delete successful for file: " + file);
                }
            }
        }

    }

    /**
     * Deletes dummy file to indicate that hot folder is complete.
     */
    private void deleteDummyFile() {
        final File dummyFile = new File(DATA_DIR + SslCoreConstants.HOT_FOLDER_DUMMY_FILE);
        if (dummyFile.exists()) {
            try {
                dummyFile.delete();
            } catch (final Exception ex) {
                LOG.error("Exception while deleting hot folder dummy file");
            }
        } else {
            LOG.error("No dummy file found");
        }

    }

    /**
     * Sets the cleanup helper.
     *
     * @param cleanupHelper
     *        the cleanupHelper to set
     */

    @Override
    public void setCleanupHelper(final CleanupHelper cleanupHelper) {
        this.cleanupHelper = cleanupHelper;
    }

    /**
     * Gets the cleanup helper.
     *
     * @return the cleanupHelper
     */
    @Override
    protected CleanupHelper getCleanupHelper() {
        return cleanupHelper;
    }

    /**
     * Gets the file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path.
     *
     * @param filePath
     *        the new file path
     */
    @Required
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

}
