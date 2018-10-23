/**
 *
 */
package com.borngroup.ssl.core.login.magento.service.impl;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;
import com.borngroup.ssl.core.login.magento.service.SslMagentoLoginService;
import com.borngroup.ssl.core.util.CommonHelper;


/**
 * @author t.balagopalan
 *
 *         Date: Thirumalai - 17 Aug 2015 Reference:
 *         https://docs.google.com/document/d/1kOGtio7ui4oaV1X_F4x_RADzSR6uZYGNi5Ua1oeJNik/edit
 */
public final class SslMagentoLoginServiceImpl implements SslMagentoLoginService
{

    private static final Logger LOG = Logger.getLogger(SslMagentoLoginServiceImpl.class);

    private final CommonHelper commonHelper = CommonHelper.getInstance();

    /* (non-Javadoc)
     * @see com.borngroup.ssl.core.login.magento.service.SslMagentoLoginService#checkMagentoLogin(java.lang.String, java.lang.String)
     */
    @Override
    public Boolean validateExistingMagentoUserLogin(final String username, final String password)
    {
        Boolean existingUserValidated = Boolean.FALSE;

        try
        {
            // Encode username and password
            final String encodedFormPayload = String.format(SslCoreConstants.MAGENTOLOGINAPIFORMENCODEDFORMAT,
                    java.net.URLEncoder.encode(username, "UTF-8"), java.net.URLEncoder.encode(password, "UTF-8"));

            // Call the Magento API
            // Return response is not required.  If the responseCode is 200 then Success.
            // If it is 204, then it is a failure and the commonHelper will throw an exception.
            commonHelper.callAPIStringResponse(commonHelper.getAPIInputObject(SslCoreConstants.MAGENTOLOGINAPIURL, "",
                    encodedFormPayload, ContentTypeEnum.FORMURLENCODED, APIMethodEnum.POST, false));
            LOG.info("Returned successfully....");

            // Set the validation to be true
            existingUserValidated = Boolean.TRUE;
        }
        catch (final Exception ex)
        {
            LOG.error(String.format("%s - %s: %s", ex.getMessage(), "Incorrect Username/password for existing Magento user",
                    username));
        }

        return existingUserValidated;
    }

}
