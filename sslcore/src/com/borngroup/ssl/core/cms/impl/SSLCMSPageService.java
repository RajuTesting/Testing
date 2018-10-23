package com.borngroup.ssl.core.cms.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSPageService;
import de.hybris.platform.core.model.type.ComposedTypeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.dao.SslCMSDao;
import com.borngroup.ssl.core.model.OfferPageModel;

/**
 * SSL specific implementation for {@link DefaultCMSPageService}.
 *
 * @author Monomoy.Ghosh
 */
public class SSLCMSPageService extends DefaultCMSPageService {

    /**
     * Constand Logger.
     */
    protected static final Logger LOG = Logger.getLogger(SSLCMSPageService.class.getName());
    /**
     * Brand Root Category Code.
     */
    protected static final String BRAND_ROOT_CATEGORY_CODE = "brands";

    /**
     * Ssl CMS Dao {@link SslCMSDao}.
     */
    @Resource(name = "sslCmsDao")
    private SslCMSDao sslCMSDao;

    /**
     * Method to find Category Page corresponding to a category.
     *
     * @param category
     *        {@link CategoryPageModel}
     *
     * @return {@link CategoryPageModel}
     */
    @Override
    public CategoryPageModel getPageForCategory(final CategoryModel category) throws CMSItemNotFoundException {
        final CategoryPageModel page = (CategoryPageModel) getSinglePage("CategoryPage");
        if (page != null) {
            LOG.debug("Only one CategoryPage for category [" + category.getCode() + "] found. Considering this as default.");
            return page;
        }
        final ComposedTypeModel type = typeService.getComposedTypeForCode("CategoryPage");
        final Set<CatalogVersionModel> versions = catalogService.getSessionCatalogVersions();
        final RestrictionData data = cmsDataFactory.createRestrictionData(category);
        final Collection<AbstractPageModel> pages = cmsPageDao.findAllPagesByTypeAndCatalogVersions(type, versions);
        final Collection<AbstractPageModel> result = cmsRestrictionService.evaluatePages(pages, data);
        return getPageForCategoryOrBrand(category, result);
    }

    /**
     * Method to find CategoryPageModel corresponding to Category or Brand.
     *
     * @param category
     *        {@link CategoryModel}
     * @param pages
     *        Collection of {@link AbstractPageModel}
     * @return {@link CategoryPageModel}
     * @throws CMSItemNotFoundException
     *         {@link CMSItemNotFoundException}
     */
    protected CategoryPageModel getPageForCategoryOrBrand(final CategoryModel category, final Collection<AbstractPageModel> pages)
            throws CMSItemNotFoundException {
        if (pages.isEmpty() || null == category) {
            throw new CMSItemNotFoundException("No page for category found.");
        }

        if (pages.size() == 1) {
            return ((CategoryPageModel) pages.iterator().next());
        }

        final boolean isBrand = isBrand(category);
        // If Category Model holds a categoryPage. get all components if they are not the default ones
        CategoryPageModel cPage = category.getCategoryPage();
        if(cPage!=null){
            if(!isBrand)
                return cPage;
        }
        final Collection<CategoryPageModel> result = new ArrayList<CategoryPageModel>();
        for (final AbstractPageModel page : pages) {
            if (page instanceof CategoryPageModel) {
                final CategoryPageModel categoryPage = (CategoryPageModel) page;
                if (categoryPage.isUsableForBrand() == isBrand) {
                    result.add(categoryPage);
                }
            }
        }

        if (result.isEmpty()) {
            throw new CMSItemNotFoundException("No page for category [" + category.getCode() + "] found.");
        }
        if (result.size() > 1) {
            LOG.warn("More than one page found for category [" + category.getCode() + "]. Returning default.");
        }
        return result.iterator().next();
    }

    /***
     * Method to check if a page is brand page.
     *
     * @param category
     *        {@link CategoryModel}
     * @return boolean return true if page is a brand page
     */
    protected boolean isBrand(final CategoryModel category) {
        if (category != null) {
            if (BRAND_ROOT_CATEGORY_CODE.equalsIgnoreCase(category.getCode())) {
                return true;
            }

            if (CollectionUtils.isNotEmpty(category.getSupercategories())) {
                for (final CategoryModel supercategory : category.getSupercategories()) {
                    if (isBrand(supercategory)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Method to get all Offer Pages.
     *
     * @return List of {@link OfferPageModel}
     */
    public List<OfferPageModel> findOfferpage() {
        final Set catalogVersions = this.catalogService.getSessionCatalogVersions();
        return this.sslCMSDao.findOfferpage(catalogVersions);
    }

    /**
     * Method to find offer Page for Label.
     *
     * @param offerCode
     *        {@link String}
     * @return {@link OfferPageModel}
     */
    public OfferPageModel findOfferPageForLabel(final String offerCode) {
        final Set catalogVersions = this.catalogService.getSessionCatalogVersions();
        final List<OfferPageModel> offerPageModels = this.sslCMSDao.findOfferPageForLabel(offerCode, catalogVersions);
        if (CollectionUtils.isNotEmpty(offerPageModels)) {
            return this.sslCMSDao.findOfferPageForLabel(offerCode, catalogVersions).get(0);
        }
        return null;
    }

    /**
     * Getter method for SSL CMS Dao.
     *
     * @return {@link SslCMSDao} the SslCMSDao
     */
    public SslCMSDao getSslCMSDao() {
        return sslCMSDao;
    }

    /**
     * Setter method for SSL CMS Dao.
     *
     * @param sslCMSDao
     *        {@link SslCMSDao} the sslCMSDao to set
     */
    public void setSslCMSDao(final SslCMSDao sslCMSDao) {
        this.sslCMSDao = sslCMSDao;
    }

}