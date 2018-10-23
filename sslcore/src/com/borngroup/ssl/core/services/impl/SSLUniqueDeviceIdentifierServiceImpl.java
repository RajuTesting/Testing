/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.SSLUniqueDeviceIdentifierDao;
import com.borngroup.ssl.core.enums.AppType;
import com.borngroup.ssl.core.model.SSLUniqueDeviceIdentifierModel;
import com.borngroup.ssl.core.services.SSLUniqueDeviceIdentifierService;
import com.borngroup.ssl.facades.data.SSLUniqueDeviceIdentifierData;

/**
 * @author gokulpandey
 *
 */
public class SSLUniqueDeviceIdentifierServiceImpl implements SSLUniqueDeviceIdentifierService {

    /**
     *
     */
    private static final int RETRY_COUNT = 3;

    private SSLUniqueDeviceIdentifierDao uniqueDeviceIdentifierDao;

    private ModelService modelService;

    @Override
    public List<SSLUniqueDeviceIdentifierModel> getDeviceIdentifierByCode(final String code) {
        return uniqueDeviceIdentifierDao.getDeviceIdentifierModelByCode(code);

    }

    @Override
    public SSLUniqueDeviceIdentifierModel getDeviceIdentifierByCodeAndEmail(final String code, final String email) {
        return uniqueDeviceIdentifierDao.getDeviceIdentifierModelByCodeAndEmail(code, email);
    }

    @Override
    public void updateEmail(final String code, final String email) {
        final SSLUniqueDeviceIdentifierModel model = getDeviceIdentifierByCodeAndEmail(code, null);
        model.setEmail(email);
        modelService.save(model);
    }

    @Override
    public String save(final SSLUniqueDeviceIdentifierData deviceIdentifierData) {
        final SSLUniqueDeviceIdentifierModel model = modelService.create(SSLUniqueDeviceIdentifierModel.class);
        final String uid = generateUniqueId();
        if (uid == null) {
            return null;
        }
        model.setUniqueId(uid);
        model.setDeviceBrand(deviceIdentifierData.getDeviceBrand());
        model.setDeviceModel(deviceIdentifierData.getDeviceModel());
		if (AppType.ANDROID.equals(AppType.valueOf(deviceIdentifierData.getOsType()))) {
            model.setOsName(AppType.ANDROID);
        } else {
            model.setOsName(AppType.IOS);
        }
        model.setOsVersion(deviceIdentifierData.getOsVersion());
        model.setScreenSize(deviceIdentifierData.getScreenSize());
        model.setAppVersion(deviceIdentifierData.getAppVersion());
        modelService.save(model);
        modelService.refresh(model);
        return model.getUniqueId();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.services.SSLUniqueDeviceIdentifierService#getDeviceIdentifierByCode(java.lang.String, java.lang.String)
     */
    @Override
    public void updateEmailTouniqueIdentifier(final String uniqueId, final String email) {
        final List<SSLUniqueDeviceIdentifierModel> modelList = getDeviceIdentifierByCode(uniqueId);
        if (CollectionUtils.isNotEmpty(modelList)) {
            final Optional model = modelList.stream().filter(item -> null == item.getEmail()).findAny();
            if (model.isPresent()) {
                final SSLUniqueDeviceIdentifierModel itemModel = (SSLUniqueDeviceIdentifierModel) model.get();
                if (null != itemModel) {
                    itemModel.setEmail(email);
                    modelService.save(itemModel);
                }
            }
        }
    }

    /**
     *
     */
    private String generateUniqueId() {
        String uniqueId = getUid();
        int count;
        for (count = 0; count < RETRY_COUNT && CollectionUtils.isNotEmpty(getDeviceIdentifierByCode(uniqueId)); count++) {
            uniqueId = getUid();
        }
        if (count == RETRY_COUNT) {
            return null;
        }
        return uniqueId;
    }

    /**
     * @return string
     */
    private String getUid() {
        return UUID.randomUUID().toString();
    }

    /**
     * @param uniqueDeviceIdentifierDao
     *        the uniqueDeviceIdentifierDao to set
     */
    public void setUniqueDeviceIdentifierDao(final SSLUniqueDeviceIdentifierDao uniqueDeviceIdentifierDao) {
        this.uniqueDeviceIdentifierDao = uniqueDeviceIdentifierDao;
    }

    /**
     * @param modelService
     *        the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

}
