/**
 *
 */
package com.borngroup.ssl.core.dataimport.batch.image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Handler: To convert images to csv file.
 * <p/>
 * Created by: hemani@nagarro.com
 *
 * @author SSL
 *
 */
public class SslImageFileHandler {

    /** The image path. */
    private String imagePath;

    /** The csv file path. */
    private String csvPath;

    /** The destination image path. */
    private String destinationImagePath;

    /** The LOG variable for logs. */
    private static final Logger LOG = LoggerFactory.getLogger(SslImageFileHandler.class);

    /** jpg extension. */
    private static final String JPG_EXT = ".jpg";

    /** jpeg extension. */
    private static final String JPEG_EXT = ".jpeg";

    /** png extension. */
    private static final String PNG_EXT = ".png";

    /** gif extension. */
    private static final String GIF_EXT = ".gif";

    /** csv file separator. */
    private static final String SEPARATOR = ",";

    /** error path to append. */
    private static final String ERROR_PATH = "/error/";

    /** date format for csv file. */
    private static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** hiphen (-). */
    private static final String HIPHEN = "-";

    /** csv extension. */
    private static final String CSV_EXT = ".csv";

    /**
     * Creates the csv file from image.
     *
     * It creates csv in this format: banner_fileName,fileName.ext,image/ext,fileName,fileNameBannerComponent,fileName Banner Component.
     *
     * @param imageFile
     *        image file added in hot folder
     *
     * @return csvFile converted csv file
     */
    public File createCSV(final File imageFile) {
        try {
            LOG.debug("File name: " + imageFile);
            final String fileName = imageFile.getName();
            if (fileName.endsWith(JPG_EXT) || imageFile.getName().endsWith(JPEG_EXT) || fileName.endsWith(PNG_EXT)
                    || fileName.endsWith(GIF_EXT)) {
                final File csvFile = new File(getCsvPath());
                final FileWriter writer = new FileWriter(csvFile);
                final boolean errorFlag = writeCSVLine(writer, Arrays.asList("banner_" + stripExtension(fileName), fileName, "image/"
                        + getFileExtension(fileName), stripExtension(fileName), stripExtension(fileName) + "BannerComponent",
                        stripExtension(fileName) + " Banner Component"));
                moveImagesToNewLocation(imageFile, errorFlag);
                writer.flush();
                writer.close();
                LOG.debug("csvFile: " + csvFile);
                return csvFile;
            } else {
                LOG.error("Error occured while reading file " + fileName);
            }
        } catch (final IOException e) {
            LOG.error("Error reading Image file or Incorrect FileName.", e);
        }

        return null;

    }

    /**
     * Gets File extension.
     *
     * @param fileName
     *        full file name, E.g: banner.txt
     * @return fileExtension E.g: txt
     */
    private String getFileExtension(final String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Removes file extension from file name.
     *
     * @param fileName
     *        full file name, E.g: banner.txt
     * @return file name without extension E.g: banner
     */
    private String stripExtension(final String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            final int lastIndex = fileName.lastIndexOf('.');
            if (lastIndex == -1) {
                return fileName;
            }
            return fileName.substring(0, lastIndex);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Writes csv line to the csv file.
     *
     * @param writer
     *        file writer
     * @param values
     *        values to be written in csv file
     * @return errorFlag flag that determines whether error occured
     */
    private boolean writeCSVLine(final FileWriter writer, final List<String> values) {
        boolean first = true;
        boolean errorFlag = false;

        final StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(values)) {
            for (final String value : values) {
                if (!first) {
                    builder.append(SEPARATOR);
                }
                builder.append(followCSVFormat(value));
                first = false;
            }
        }
        LOG.debug("csv line: " + builder.toString());
        builder.append('\n');
        try {
            writer.append(builder.toString());
        } catch (final IOException e) {
            errorFlag = true;
            LOG.error("Error occured in writing csv line for values" + values);
        }

        return errorFlag;

    }

    /**
     * Follows csv format for all lines.
     *
     * @param value
     *        value to be entered in csv file
     * @return result Add " " with data according to CSV format
     */
    private String followCSVFormat(final String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    /**
     * Move images to new location, either error or destination path.
     *
     * @param file
     *        file to be moved to destinationImagePath or error path
     * @param isError
     *        to check whether error occurred
     * @return flag to determine if transfer of image is successful
     */
    private boolean moveImagesToNewLocation(final File file, final boolean isError) {
        final String filePath;
        if (isError) {
            filePath = getImagePath() + ERROR_PATH;
        } else {
            filePath = getDestinationImagePath();
        }
        LOG.debug("File moved to [" + filePath + "] location");
        final File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }

        final boolean flag = file.renameTo(new File(filePath + file.getName()));
        file.delete();

        return flag;
    }

    /**
     * Gets the image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the image path.
     *
     * @param imagePath
     *        the new image path
     */
    @Required
    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets the csv path.
     *
     * @return the csv path
     */
    public String getCsvPath() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

        return csvPath + HIPHEN + sdf.format(new Date()) + CSV_EXT;
    }

    /**
     * Sets the csv path.
     *
     * @param csvPath
     *        the new csv path
     */
    @Required
    public void setCsvPath(final String csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Gets the destination image path.
     *
     * @return the destination image path
     */
    public String getDestinationImagePath() {
        return destinationImagePath;
    }

    /**
     * Sets the destination image path.
     *
     * @param destinationImagePath
     *        the new destination image path
     */
    @Required
    public void setDestinationImagePath(final String destinationImagePath) {
        this.destinationImagePath = destinationImagePath;
    }

}
