/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.core.model.media.MediaModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility : Created for utility methods for components </p> Created by sonam.kaushik@nagarro.com
 *
 * @author SSL
 *
 */
public class SSLComponentUtils {

    /**
     * Constant Logger
     */
    public static Logger LOG = LoggerFactory.getLogger(SSLComponentUtils.class);

    /**
     * Method to tell whether a component/entity is active or not
     *
     * @param from
     *        - date starts from
     * @param until
     *        - date ends on
     * @return whether a component/entity is active or not
     */
    public static boolean isComponentActive(final Date from, final Date until) {
        final Date now = new Date();
        if (LOG.isDebugEnabled()) {
            final DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            LOG.debug("Current time: " + dateformat.format(now));
            LOG.debug("Valid from: " + ((from != null) ? dateformat.format(from) : "null"));
            LOG.debug("Valid until: " + ((until != null) ? dateformat.format(until) : "null"));
        }
        boolean after = false;
        if (from != null) {
            after = now.after(from);
        }
        boolean before = false;
        if (until != null) {
            before = now.before(until);
        }
        return ((after) && (before));
    }

    /**
     * Method to get active media from a list of media in a component </p> <b>Business rules</b> for deciding active media: </p> - In case
     * of overlapping time periods, the one with the earlier start date would be displayed. </p> - In case start date (with time) for two or
     * more non-carousel and carousel banners is same, then the one that is most recently created in the system would be displayed. </p>
     *
     * @param images
     *        - list of media
     * @return {@link MediaModel} - active media
     */
    public static MediaModel getActiveMedia(final List<MediaModel> images) {
        if (CollectionUtils.isNotEmpty(images)) {
            final List<MediaModel> activeImages = new ArrayList<MediaModel>();
            for (final MediaModel media : images) {
                final boolean isActive = isComponentActive(media.getActiveFrom(), media.getActiveUntil());
                if (isActive) {
                    activeImages.add(media);
                }
            }
            if (CollectionUtils.isNotEmpty(activeImages)) {
                if (activeImages.size() > 1) {
                    Collections.sort(activeImages, new ActiveFromUntilDateComparator());
                }
                return activeImages.get(0);
            }
        }
        return null;
    }
}
