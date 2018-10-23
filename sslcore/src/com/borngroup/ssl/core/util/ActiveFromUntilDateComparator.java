/**
 *
 */
package com.borngroup.ssl.core.util;

import com.borngroup.ssl.core.model.components.SslImageMapComponentModel;
import de.hybris.platform.core.model.media.MediaModel;

import java.util.Comparator;

/**
 * Comparator : To compare two objects of type MediaModel for WCMS scheduling</p> Created by sonam.kaushik@nagarro.com
 *
 * @author SSL
 *
 */
public class ActiveFromUntilDateComparator implements Comparator {

    @Override
    public int compare(final Object o1, final Object o2) {
        if ((o1 instanceof MediaModel && o2 instanceof MediaModel)) {
            final int result1 = ((MediaModel) o1).getActiveFrom().compareTo(((MediaModel) o2).getActiveFrom());
            if (result1 == 0) {
                final int result2 = ((MediaModel) o1).getCreationtime().compareTo(((MediaModel) o2).getCreationtime());
                // reversing the natural order for having most recent creation date/time first
                if (result2 > 0) {
                    return -1;
                } else if (result2 < 0) {
                    return 1;
                }
                return result2;
            }
            return result1;
        }
        else if ((o1 instanceof SslImageMapComponentModel && o2 instanceof SslImageMapComponentModel)) {
            final int result1 = ((SslImageMapComponentModel) o1).getActiveFrom().compareTo(((SslImageMapComponentModel) o2).getActiveFrom());
            if (result1 == 0) {
                final int result2 = ((SslImageMapComponentModel) o1).getCreationtime().compareTo(((SslImageMapComponentModel) o2).getCreationtime());
                // reversing the natural order for having most recent creation date/time first
                if (result2 > 0) {
                    return -1;
                } else if (result2 < 0) {
                    return 1;
                }
                return result2;
            }
            return result1;
        }
        return 0;
    }

}
