/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.daos.UserGroupDao;
import de.hybris.platform.servicelayer.user.daos.impl.DefaultUserDao;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.services.SSLUserService;
import com.borngroup.ssl.core.services.dao.SslServiceDao;

/**
 * @author Deepak
 *
 */
public class SSLUserServiceImpl extends DefaultUserService implements SSLUserService {
    private static final Logger LOG = Logger.getLogger(SSLUserServiceImpl.class);

    @Autowired
    private UserGroupDao userGroupDao;

    @Resource(name = "userDao")
    private DefaultUserDao userDao;

    /**
     * {@link:sslServiceDao}
     */
    private SslServiceDao sslServiceDao;

    /**
     * @return the sslServiceDao
     */
    public SslServiceDao getSslServiceDao() {
        return sslServiceDao;
    }

    /**
     * @param sslServiceDao
     *        the sslServiceDao to set
     */
    public void setSslServiceDao(final SslServiceDao sslServiceDao) {
        this.sslServiceDao = sslServiceDao;
    }

    /*
     * This method returns the Collection of PrincipalModel
     *
     * @param groupUid GroupId
     *
     * @return list of {@link PrincipalModel}
     */
    @Override
    public Collection<PrincipalModel> getUsersByUserGroup(final String groupUid) {
        LOG.debug("calling method from custom user service..........");
        final UserGroupModel userGroupModel = userGroupDao.findUserGroupByUid(groupUid);
        return userGroupModel.getMembers();
    }

    /*
     * This method returns the List of CustomerModel
     *
     * @param customerModel customerModel
     *
     * @return list of {@link CustomerModel}
     */
    @Override
    public List<CustomerModel> getUserByEmail(final CustomerModel customerModel) {
        LOG.debug("-Getting List of Customers by email id-");
        if (customerModel != null) {
            return sslServiceDao.getUserByEmail(customerModel);
        } else {
            return ListUtils.EMPTY_LIST;
        }

    }

    @Override
    public CustomerModel getUserByUid(final String uid) {
        final UserModel user = userDao.findUserByUID(uid);
        if (user instanceof CustomerModel) {
            return (CustomerModel) user;
        }

        return null;
    }

    /* enable read only access to user SSLM-5043 */
    public boolean isReadOnlyUser(final UserModel userModel, final boolean existingValue) {

        if (userModel != null && userModel instanceof EmployeeModel) {
            for (final PrincipalGroupModel groupModel : userModel.getGroups()) {
                try {
                    if (groupModel != null && "readonlyusergroup".equalsIgnoreCase(groupModel.getUid())) {
                        return true;
                    }
                } catch (final Exception e) {
                    LOG.error("Group model empty");
                }
            }
        }
        return existingValue;
    }

    /**
     * Returns true if the logged in user is a CS Manager
     *
     * @return boolean
     */
    @Override
    public boolean isValidStoreAgent(final UserModel userModel) {

        if (userModel != null && userModel instanceof EmployeeModel) {
            for (final PrincipalGroupModel groupModel : userModel.getGroups()) {
                if ("csstoreagentgroup".equalsIgnoreCase(groupModel.getUid()) && ((EmployeeModel) (userModel)).getStoreLocation() != null) {
                    return true;
                }
            }
        }

        return false;
    }

}
