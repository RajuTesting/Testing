/**
 *
 */
package com.borngroup.ssl.core.sslsocialconnectaddon.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.sslfacades.customer.data.CustomerSocialAccountData;
import de.hybris.platform.util.Config;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.FacebookTokenResponse;
import com.borngroup.ssl.core.data.GoogleTokenResponse;
import com.borngroup.ssl.core.data.enums.APIMethodEnum;
import com.borngroup.ssl.core.data.enums.ContentTypeEnum;
import com.borngroup.ssl.core.model.CustomerSocialAccountModel;
import com.borngroup.ssl.core.sslsocialconnectaddon.CustomerSocialAccountService;
import com.borngroup.ssl.core.sslsocialconnectaddon.daos.CustomerSocialAccountDAO;
import com.borngroup.ssl.core.util.CommonHelper;

/**
 * @author admin
 *
 */
public class CustomerSocialAccountServiceImpl implements CustomerSocialAccountService
{
    private static final String FB_OAUTH_URL     = Config.getParameter("facebook.oauth.url"); // https://graph.facebook.com/me?access_token=
    private static final String GOOGLE_OAUTH_URL = Config.getParameter("google.oauth.url");   // https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=
    private static final String GOOGLE_PROFILE_URL ="google.profile.url";//https://www.googleapis.com/oauth2/v3/userinfo
    private static final String FACEBOOK         = "facebook";
    private static final String GOOGLE           = "google";

    private static final Logger LOG          = Logger.getLogger(CustomerSocialAccountServiceImpl.class);
    private final CommonHelper  commonHelper = CommonHelper.getInstance();

    private CustomerSocialAccountDAO customerSocialAccountDAO;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Resource(name = "cmsSiteService")
    private CMSSiteService cmsSiteService;

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.sslsocialconnectaddon.CustomerSocialAccountService# getCustomerBySocialAccountId(java.lang.String )
     */
    @Override
    public CustomerSocialAccountModel getCustomerBySocialAccountId(final String code) throws AmbiguousIdentifierException
    {
        final List<CustomerSocialAccountModel> result = customerSocialAccountDAO.findCustomerBySocialAccountId(code);
        if (result.isEmpty())
        {
            return null;
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException(
                    "Social account code '" + code + "' is not unique, " + result.size() + " Customer found!");
        }
        return result.get(0);
    }

    @Required
    public void setCustomerSocialAccountDAO(final CustomerSocialAccountDAO customerSocialAccountDAO)
    {
        this.customerSocialAccountDAO = customerSocialAccountDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.sslsocialconnectaddon.CustomerSocialAccountService#validateAuthToken(java.lang.String, java.lang.String)
     */
    @Override
    public CustomerSocialAccountData getUserIdFromAuthToken(final String auth_token, final String socialIDP) throws Exception
    {

        CustomerSocialAccountData socialAccountData = null;

        if (socialIDP.toLowerCase().contains(FACEBOOK))
        {
            // Call API
            final FacebookTokenResponse apiResponse = (FacebookTokenResponse) commonHelper
                    .callAPIJSONResponse(commonHelper.getAPIInputObject(FB_OAUTH_URL, "fields=id,email,first_name,last_name&access_token=" + auth_token, "",
                            ContentTypeEnum.JSON, APIMethodEnum.GET, false), FacebookTokenResponse.class);

            if (apiResponse != null)
            {
                socialAccountData = new CustomerSocialAccountData();
                socialAccountData.setCurrentIDP(FACEBOOK);
                socialAccountData.setSocialAccountEmailId(apiResponse.getEmail());
                socialAccountData.setSocialAccountUid(apiResponse.getId());
                socialAccountData.setFirstName(apiResponse.getFirst_name());
                socialAccountData.setLastName(apiResponse.getLast_name());

                return socialAccountData;
            }
            throw new AuthenticationException("Invalid auth token");
        }
        else if (socialIDP.toLowerCase().contains(GOOGLE))
        {
            final GoogleTokenResponse apiResponse = (GoogleTokenResponse) commonHelper.callAPIJSONResponse(commonHelper.getAPIInputObject(
            		Config.getParameter(GOOGLE_PROFILE_URL), "access_token=" + auth_token, "", ContentTypeEnum.JSON, APIMethodEnum.GET, false),
                    GoogleTokenResponse.class);
            if (apiResponse != null)
            {
                socialAccountData = new CustomerSocialAccountData();
                socialAccountData.setCurrentIDP(GOOGLE);
                socialAccountData.setSocialAccountEmailId(apiResponse.getEmail());
                socialAccountData.setSocialAccountUid(apiResponse.getSub());
                socialAccountData.setFirstName(apiResponse.getFirstName());
                socialAccountData.setLastName(apiResponse.getLastName());

                return socialAccountData;
            }
            throw new AuthenticationException("Invalid auth token");
        }

        throw new AuthenticationException("Invalid IDP");
    }
    @Override
    public void updateProfilePicture(final String socialId, final String pictureUrl, final CustomerModel userModel) {

        final Instant start = Instant.now();
        if (userModel != null && StringUtils.isNotEmpty(pictureUrl) && (BooleanUtils.isNotFalse(userModel.getIsSocialPicUploaded())
                || userModel.getProfilePicture() == null)) {
            try {
                MediaModel media = userModel.getProfilePicture();
                if (media == null) {
                    final Random randomNum = new Random();
                    media = modelService.create(MediaModel.class);
                    media.setCode(new StringBuilder().append(socialId).append(randomNum.nextInt()).append("." + SslCoreConstants.MEDIA_PATTERN).toString());
                    media.setMime(SslCoreConstants.IMAGE_MIME_TYPE);
                    media.setRealFileName(socialId.concat("." + SslCoreConstants.MEDIA_PATTERN));
                    media.setCatalogVersion(catalogVersionService.getCatalogVersion("sslContentCatalog", "Staged"));
                }
                media.setURL(pictureUrl);
                modelService.save(media);
                userModel.setIsSocialPicUploaded(Boolean.TRUE);
                userModel.setProfilePicture(media);
                modelService.save(userModel);
            } catch (final Exception e) {
                LOG.error("Exception occurred while creating profile picture media for social user " + e.getCause());
            }
        }
        final Instant end = Instant.now();
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Spent %s to update profile picture.", Duration.between(start, end)));
        }
    }

}
