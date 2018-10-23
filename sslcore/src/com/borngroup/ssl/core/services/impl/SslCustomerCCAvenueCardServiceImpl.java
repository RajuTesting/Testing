package com.borngroup.ssl.core.services.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.borngroup.ssl.core.dao.SslCustomerCCAvenueCardDao;
import com.borngroup.ssl.core.model.SslCCAvenueCustomerCardDetailModel;
import com.borngroup.ssl.core.services.SslCustomerCCAvenueCardService;

/**
 * Service Implementation for SslCCAvenueCustomerCardDetailModel
 * <p/>
 * Created by shilpa.verma@nagarro.com.
 *
 * @author SSL
 */
public class SslCustomerCCAvenueCardServiceImpl implements SslCustomerCCAvenueCardService {

    /**
     * SslCustomerCCAvenueCardDao
     */
    private SslCustomerCCAvenueCardDao sslCustomerCCAvenueCardDao;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * Getter: Gets sslCustomerCCAvenueCardDao
     *
     * @return the sslCustomerCCAvenueCardDao
     */
    public SslCustomerCCAvenueCardDao getSslCustomerCCAvenueCardDao() {
        return sslCustomerCCAvenueCardDao;
    }

    /**
     * Setter: Sets sslCustomerCCAvenueCardDao
     *
     * @param sslCustomerCCAvenueCardDao
     *        the sslCustomerCCAvenueCardDao to set
     */
    public void setSslCustomerCCAvenueCardDao(final SslCustomerCCAvenueCardDao sslCustomerCCAvenueCardDao) {
        this.sslCustomerCCAvenueCardDao = sslCustomerCCAvenueCardDao;
    }

    /**
     * Gets SslCCAvenueCustomerCardDetailModel for passed code.
     */
    @Override
    public SslCCAvenueCustomerCardDetailModel getCCAvenueCustomerCardDetailForCode(final String code) {
        return getSslCustomerCCAvenueCardDao().getCustomerCardDetailForCode(code);
    }

    @Override
    public void saveAndLinkCardDetails(final SslCCAvenueCustomerCardDetailModel sslCCAvenueCustomerCardDetailModel) {
        modelService.save(sslCCAvenueCustomerCardDetailModel);
        final UserModel userModel = userService.getCurrentUser();
        if (userModel instanceof CustomerModel) {
            final CustomerModel customerModel = (CustomerModel) userModel;
            final List<SslCCAvenueCustomerCardDetailModel> storedCards = new ArrayList<>(0);
            if (CollectionUtils.isNotEmpty(customerModel.getSavedCards())) {
                storedCards.addAll(customerModel.getSavedCards());
            }
            storedCards.add(sslCCAvenueCustomerCardDetailModel);
            customerModel.setSavedCards(storedCards);
            modelService.save(customerModel);
        }
    }

    @Override
    public void setStoredCardForIdAsDefault(final String cardId) {
        final UserModel userModel = userService.getCurrentUser();
        if (userModel instanceof CustomerModel) {
            final CustomerModel customerModel = (CustomerModel) userModel;
            for (final SslCCAvenueCustomerCardDetailModel eachModel : customerModel.getSavedCards()) {
                if (eachModel.getCustomerCardId().toString().equals(cardId)) {
                    eachModel.setIsDefault(Boolean.TRUE);
                } else {
                    eachModel.setIsDefault(Boolean.FALSE);
                }
                modelService.save(eachModel);
            }
        }
    }

    @Override
    public void deleteStoredCardForId(final String cardId) {
        final SslCCAvenueCustomerCardDetailModel cardModel = getCCAvenueCustomerCardDetailForCode(cardId);
        modelService.remove(cardModel);

    }

    @Override
    public void updateStoredCardForId(final Integer cardId, final String cardLabel, final Boolean isDefault) {
        final SslCCAvenueCustomerCardDetailModel cardModel = getCCAvenueCustomerCardDetailForCode(cardId.toString());
        cardModel.setCustomerCardLabel(cardLabel);
        if (isDefault != null) {
            if (isDefault.booleanValue()) {
                this.setStoredCardForIdAsDefault(cardId.toString());
            } else {
                cardModel.setIsDefault(isDefault);
            }
        }
        modelService.save(cardModel);

    }

}
