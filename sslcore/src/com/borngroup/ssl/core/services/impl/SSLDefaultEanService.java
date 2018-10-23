/**
 *
 */
package com.borngroup.ssl.core.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.EanDao;
import com.borngroup.ssl.core.model.EanModel;
import com.borngroup.ssl.core.services.EanService;

/**
 * @author tejsharma
 *
 */
public class SSLDefaultEanService implements EanService {
    private static final Logger LOG = Logger.getLogger(SSLDefaultEanService.class);

    @Resource(name = "eanDao")
    private EanDao eanDao;

    @Resource(name = "modelService")
    private ModelService modelService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EanModel> getEans() {
        return getEanDao().findEans();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EanModel getEanByNumber(final String eanNumber) throws UnknownIdentifierException, AmbiguousIdentifierException {
        final List<EanModel> eans = getEanDao().findEanByNumber(eanNumber);
        validateIfSingleResult(eans, String.format("Ean with code '%s' not found!", eanNumber),
                String.format("Ean code '%s' is not unique!", eanNumber));
        return eans.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EanModel getOrCreateEanByNumber(final String eanNumber) throws AmbiguousIdentifierException {
        EanModel ean = null;
        try {
            ean = getEanByNumber(eanNumber);
        } catch (final UnknownIdentifierException uie) {
            LOG.debug(uie.getMessage());
            ean = modelService.create(EanModel.class);
            ean.setEanNumber(eanNumber);
            getModelService().save(ean);
            getModelService().refresh(ean);
        }
        return ean;
    }

    /**
     * @return the eanDao
     */
    public EanDao getEanDao() {
        return eanDao;
    }

    /**
     * @param eanDao
     *        the eanDao to set
     */
    public void setEanDao(final EanDao eanDao) {
        this.eanDao = eanDao;
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService
     *        the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

}
