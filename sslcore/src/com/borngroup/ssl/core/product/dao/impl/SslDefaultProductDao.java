/**
 *
 */
package com.borngroup.ssl.core.product.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.SearchRestrictionModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.CategorySizeGuideMappingModel;
import com.borngroup.ssl.core.model.SizeGuideMappingModel;
import com.borngroup.ssl.core.product.dao.SslProductDao;
import com.borngroup.ssl.core.util.FlexibleSearchQueryBuilder;

/**
 * @author Viji
 *
 */
public class SslDefaultProductDao implements SslProductDao {
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource
    private UserService userService;

    @Resource
    private TypeService typeService;

    private static final Logger LOG = Logger.getLogger(SslDefaultProductDao.class);
    private static final String TRUE = "1";

    /**
     * To get the size guide for a product based on the category and brand it is associated with. It returns String which is the html of the
     * size guide.
     */
    @Override
    public String getProductSizeGuide(final ProductModel productModel) {
        if (null != productModel.getSupercategories() && !productModel.getSupercategories().isEmpty()) {
            final StringBuilder query = new StringBuilder();

            query.append("SELECT {" + CategorySizeGuideMappingModel.PK + "} FROM {" + CategorySizeGuideMappingModel._TYPECODE + "} WHERE {"
                    + "category" + "} in ( " + "?categories" + " )");
            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
            flexibleSearchQuery.addQueryParameter("categories", productModel.getSupercategories());

            for (final CategoryModel categoryModel : productModel.getSupercategories()) {
                if (categoryModel.getIsBrand().booleanValue()) {
                    query.append(" AND {" + "brand" + "} = ?brand");
                    flexibleSearchQuery.addQueryParameter("brand", categoryModel);
                }
            }

            flexibleSearchQuery.setResultClassList(Collections.singletonList(CategorySizeGuideMappingModel.class));
            final SearchResult<CategorySizeGuideMappingModel> result = flexibleSearchService.search(flexibleSearchQuery);
            if (result.getResult() != null && result.getResult().size() > 0) {
                return result.getResult().get(0).getCategorySizeGuideHTML();
            } else {
                try {
                    /*
                     * FlexibleSearchQueryBuilder builder = new FlexibleSearchQueryBuilder().from(SizeGuideMappingModel._TYPECODE,
                     * "sizeguide") .select("sizeguide",SizeGuideMappingModel.PK).join(CategoryModel._TYPECODE, CategoryModel.PK, "c",
                     * SizeGuideMappingModel.CATEGORY, "sizeguide", JoinType.LEFT_OUTER) .join(BrandMappingModel._TYPECODE,
                     * BrandMappingModel.PK, "brand", SizeGuideMappingModel.BRAND,"sizeguide", JoinType.LEFT_OUTER)
                     * .join(ProductModel._TYPECODE, ProductModel.PK, "p",SizeGuideMappingModel.PRODUCT,"sizeguide", JoinType.LEFT_OUTER )
                     * .whereEquals("c", "code", getPrimaryCategoryForProduct(productModel).getCode()).whereEquals("brand", "altBrandCode",
                     * productModel.getAltBrandCode()) .whereEquals("p", "code", productModel.getCode());
                     *
                     * FlexibleSearchQueryBuilder builder1 = new FlexibleSearchQueryBuilder().from(SizeGuideMappingModel._TYPECODE,
                     * "sizeguide") .select("sizeguide",SizeGuideMappingModel.PK).join(CategoryModel._TYPECODE, CategoryModel.PK, "c",
                     * SizeGuideMappingModel.CATEGORY, "sizeguide", JoinType.LEFT_OUTER) .join(BrandMappingModel._TYPECODE,
                     * BrandMappingModel.PK, "brand", SizeGuideMappingModel.BRAND,"sizeguide", JoinType.LEFT_OUTER)
                     * .join(ProductModel._TYPECODE, ProductModel.PK, "p",SizeGuideMappingModel.PRODUCT,"sizeguide", JoinType.LEFT_OUTER )
                     * .whereEquals("c", "code", getPrimaryCategoryForProduct(productModel).getCode()).whereEquals("brand", "altBrandCode",
                     * productModel.getAltBrandCode()) .whereNull("p", "code");
                     */

                    // final StringBuilder query1 = new StringBuilder();
                    // final String part1 = "select {s.pk} from {sizeguidemapping as s left outer join category as c on {s.category}={c.pk}
                    // join brandmapping as b on {s.brand}={b.pk} ";
                    // final String part2 = " left outer join apparelproduct as p on {s.product}={p.pk} ";
                    // final String part3 = " } where {c.code}=?categoryCode and {b.altBrandDesc}=?altBrandDesc";
                    // final String part4 = " and {p.code}=?productCode";
                    // final String part5 = " and {s.product} is null";

                    final StringBuilder query1 = new StringBuilder();
                    final String part1 = "select {s.pk} from {sizeguidemapping as s left outer join category as c on {s.category}={c.pk} ";
                    final String part2 = " left outer join apparelproduct as p on {s.product}={p.pk} ";
                    final String part3 = " } where {c.code}=?categoryCode and {s.altBrandDesc}=?altBrandDesc";
                    final String part4 = " and {p.code}=?productCode";
                    final String part5 = " and {s.product} is null";

                    query1.append(part1);
                    query1.append(part2);
                    query1.append(part3);
                    query1.append(part4);

                    final FlexibleSearchQuery flexibleSearchQuery1 = new FlexibleSearchQuery(query1);
                    flexibleSearchQuery1.addQueryParameter("categoryCode", getPrimaryCategoryForProduct(productModel).getCode());
                    flexibleSearchQuery1.addQueryParameter("altBrandDesc", productModel.getBrandCode());
                    flexibleSearchQuery1.addQueryParameter("productCode", productModel.getCode());

                    SearchResult<SizeGuideMappingModel> result1 = flexibleSearchService.search(flexibleSearchQuery1);
                    if (result1.getResult() != null && result1.getResult().size() > 0) {
                        return modifyHTML(result1.getResult().get(0).getSizeGuideHTML());
                    } else {
                        final StringBuilder query2 = new StringBuilder();
                        query2.append(part1);
                        query2.append(part3);
                        query2.append(part5);

                        final FlexibleSearchQuery flexibleSearchQuery2 = new FlexibleSearchQuery(query2);
                        flexibleSearchQuery2.addQueryParameter("categoryCode", getPrimaryCategoryForProduct(productModel).getCode());
                        flexibleSearchQuery2.addQueryParameter("altBrandDesc", productModel.getBrandCode());

                        result1 = flexibleSearchService.search(flexibleSearchQuery2);

                        if (result1.getResult() != null && result1.getResult().size() > 0) {
                            return modifyHTML(result1.getResult().get(0).getSizeGuideHTML());
                        }
                    }
                } catch (final Exception e) {
                    // YTODO Auto-generated catch block
                }

            }
        }
        return null;
    }

    protected CategoryModel getPrimaryCategoryForProduct(final ProductModel product) {
        // Get the first super-category from the product that isn't a classification category
        for (final CategoryModel category : product.getSupercategories()) {
            if (!(category instanceof ClassificationClassModel)) {
                return category;
            }
        }
        return null;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.product.dao.SslProductDao#changeApprovalStatusInSearchRestriction(boolean)
     */
    @Override
    public void changeApprovalStatusInSearchRestriction(final boolean active) {
        try {

            SearchRestrictionModel searchRestriction = null;

            final String query = "SELECT {pk} FROM {" + SearchRestrictionModel._TYPECODE + "} WHERE {" + "code" + "} = ?code" + " and {"
                    + "principal" + "} = ?principal" + " and {" + "restrictedType" + "} = ?restrictedType";
            final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
            flexibleSearchQuery.addQueryParameter("code", "Frontend_ProductApprovalStatus");
            flexibleSearchQuery.addQueryParameter("principal", userService.getUserGroupForUID("customergroup"));
            flexibleSearchQuery.addQueryParameter("restrictedType", typeService.getComposedTypeForCode("Product"));
            final SearchResult<SearchRestrictionModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
            final List<SearchRestrictionModel> searchRestrictionList = result != null ? result.getResult() : Collections.emptyList();
            if (searchRestrictionList.isEmpty()) {
                LOG.info("No data found while making flexi search for changeApprovalStatusInSearchRestriction ");
            } else if (searchRestrictionList.size() > 1) {
                LOG.info("No unique data found while making flexi search for changeApprovalStatusInSearchRestriction ");
            } else {
                searchRestriction = searchRestrictionList.get(0);
            }
            if (searchRestriction != null) {
                searchRestriction.setActive(active);
                modelService.save(searchRestriction);
            }

        } catch (final ModelSavingException e) {
            LOG.info("Error while sanging approval status in search restrictions " + e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.product.dao.SslProductDao#getProducts(java.util.List,
     * de.hybris.platform.catalog.model.CatalogVersionModel)
     */
    @Override
    public List<ProductModel> getProducts(final List<String> codes, final CatalogVersionModel catalog, final boolean sort) {

        final StringBuilder query = new StringBuilder();

        query.append("SELECT {" + ProductModel.PK + "} FROM {" + ProductModel._TYPECODE + "} WHERE {" + ProductModel.CODE + "} in ( "
                + "?code" + " ) AND {" + ProductModel.CATALOGVERSION + "} =" + "?catalogVersion");
        if (sort && CollectionUtils.isNotEmpty(codes)) {
            query.append(" order by case");
            int priority = 0;
            for (final String pCode : codes) {
                query.append(" when {code} = " + pCode + " then " + priority);
                priority++;
            }
            query.append(" else null end");
        }
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("code", codes);
        flexibleSearchQuery.addQueryParameter("catalogVersion", catalog);
        final SearchResult<ProductModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
        LOG.debug("Searched products count: " + result.getCount());
        return result.getResult();

    }

    @Override
    public List<ApparelStyleVariantProductModel> getProductsForCategory(final String categoryCodes, final CatalogVersionModel catalog,
            final String seasonCode, final String approvalStatus, final Date productCreationDate) {
        final StringBuilder query = new StringBuilder();
        final StringBuilder select = new StringBuilder();
        final StringBuilder whereClause = new StringBuilder();
        select.append("SELECT  distinct {style.pk} FROM {" + ApparelStyleVariantProductModel._TYPECODE + " as style join "
                + ApparelProductModel._TYPECODE
                + " as base on {base.pk} = {style.baseproduct}  join ArticleApprovalStatus as status on {base.approvalstatus}={status.pk}");
        whereClause
                .append("} WHERE {" + ApparelProductModel.CATALOGVERSION + "} =" + "?catalogVersion AND {style.galleryImages} IS Not Null");

        if (!StringUtils.isEmpty(categoryCodes)) {
            select.append(" left join CategoryProductRelation AS rel ON {base:PK} = {rel:target} left join " + CategoryModel._TYPECODE
                    + " AS cat ON {cat.PK} = {rel.source}");
            whereClause.append(" AND {cat.code}  In (?categoryCodeList) ");
        }

        query.append(select);
        query.append(whereClause);

        if (!StringUtils.isEmpty(seasonCode)) {
            query.append(" AND {base.seasonCode} In (?seasonCodeList)");
        }

        if (!StringUtils.isEmpty(approvalStatus)) {
            query.append(" AND {status.code} In ( ?statusCodeList)");
        }

        if (productCreationDate != null) {
            query.append(" AND {style.creationtime} >= ( ?productCreationDate)");
        }

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("catalogVersion", catalog);
        if (!StringUtils.isEmpty(categoryCodes)) {
            final String[] categoryCode = categoryCodes.split(",");
            final List<String> categoryCodeList = new ArrayList<>(0);
            for (int i = 0; i < categoryCode.length; i++) {
                categoryCodeList.add(categoryCode[i].trim());
            }
            flexibleSearchQuery.addQueryParameter("categoryCodeList", categoryCodeList);
        }
        if (!StringUtils.isEmpty(approvalStatus)) {
            final String[] statusCode = approvalStatus.split(",");
            final List<String> statusCodeList = new ArrayList<>(0);
            for (int i = 0; i < statusCode.length; i++) {
                statusCodeList.add(statusCode[i].trim());
            }
            flexibleSearchQuery.addQueryParameter("statusCodeList", statusCodeList);
        }
        if (!StringUtils.isEmpty(seasonCode)) {
            final String[] seasonCodes = seasonCode.split(",");
            final List<String> seasonCodeList = new ArrayList<>(0);
            for (int i = 0; i < seasonCodes.length; i++) {
                seasonCodeList.add(seasonCodes[i].trim());
            }
            flexibleSearchQuery.addQueryParameter("seasonCodeList", seasonCodeList);
        }

        if (productCreationDate != null) {
            flexibleSearchQuery.addQueryParameter("productCreationDate", productCreationDate);
        }

        final SearchResult<ApparelStyleVariantProductModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
        LOG.debug("Searched Primary Images count: " + result.getCount());
        return result.getResult();
    }

    @Override
    public List<ApparelProductModel> getBaseProductsForCategory(final String category, final CatalogVersionModel catalog) {
        final StringBuilder query = new StringBuilder();
        query.append("SELECT  distinct {base.pk} FROM {" + ApparelProductModel._TYPECODE + " as base join "
                + " ArticleApprovalStatus as status on {base.approvalstatus}={status.pk}"
                + " left join CategoryProductRelation AS rel ON {base.PK} = {rel.target} left join " + CategoryModel._TYPECODE
                + " AS cat ON {cat.PK} = {rel.source}}" + " WHERE {" + ApparelProductModel.CATALOGVERSION + "} ="
                + "?catalogVersion  AND {status.code} =  'approved'" + " AND {cat.code}  = (?categoryCode) "
                + "and {base.pk} in  ({{  select {style.baseproduct} from {apparelstylevariantproduct! as style}  where {style.pk} in ({{ select {size.baseproduct} from {apparelsizevariantproduct! as size left outer join stocklevel as stock on {size.code}={stock.productcode} left outer join warehouse as w on {stock.warehouse}={w.pk}} where {w.enabledForOnline} = '1' and {stock.available} > 0   }})   }})");

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("catalogVersion", catalog);
        flexibleSearchQuery.addQueryParameter("categoryCode", category);
        final SearchResult<ApparelProductModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
        LOG.debug("Searched Product count: " + result.getCount());
        return result.getResult();
    }

    @Override
    public List<ProductModel> getBestSellerProductsForCategory(final CategoryModel category, final Integer date) {
        final StringBuilder query = new StringBuilder();
        query.append("select distinct {stp.pk},COUNT({stp.pk}) AS NUM from { " + OrderEntryModel._TYPECODE + " as oe join "
                + VariantProductModel._TYPECODE + " as svp on {svp.pk} = {oe.product} join " + VariantProductModel._TYPECODE
                + " as stp on {stp.pk} = {svp.baseproduct} join " + ProductModel._TYPECODE
                + " as bp on {bp.pk} = {stp.baseproduct} join CategoryProductRelation as rel on {rel.target} = {bp.pk} join "
                + CategoryModel._TYPECODE
                + " as c on {c.pk} = {rel.source}} where {c.code} = (?categoryCode) and {oe.creationtime} > sysdate- (?orderDate) group by {stp.pk} order by NUM desc");
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("categoryCode", category.getCode());
        flexibleSearchQuery.addQueryParameter("orderDate", date);
        final SearchResult<ProductModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
        LOG.debug("Searched Product count: " + result.getCount());
        return result.getResult();
    }

    @Override
    public String getProductFeatureValueByCode(final String code, final PK productPk) {

        final String queryString = "select {cv.code}  from { ClassificationAttributeValue as cv left join ProductFeature as pf  on to_char({pf.stringvalue}) = {cv.pk}} where {pf.product} = ?productPk and {pf.qualifier} = ?code";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        query.addQueryParameter("productPk", productPk);
        final List<Class> resultClassList = new ArrayList<Class>();
        resultClassList.add(String.class);
        query.setResultClassList(resultClassList);
        final SearchResult<String> searchResult = getFlexibleSearchService().search(query);
        if (searchResult.getResult() != null && searchResult.getResult().size() > 0) {
            return searchResult.getResult().get(0);
        }
        return null;
    }

    /*
     * Returns best seller products for each L3 category from previous execution of cronjob. (non-Javadoc)
     *
     * @see com.borngroup.ssl.core.product.dao.SslProductDao#resetBestSeller()
     */
    @Override
    public List<ProductModel> resetBestSeller() {
        final FlexibleSearchQueryBuilder queryBuilder = new FlexibleSearchQueryBuilder().from(ProductModel._TYPECODE, "p")
                .select("p", ItemModel.PK).whereEquals("p", ProductModel.BESTSELLER, TRUE);
        final FlexibleSearchQuery flexibleSearchQuery = queryBuilder.build();
        final SearchResult<ProductModel> searchResult = getFlexibleSearchService().search(flexibleSearchQuery);
        LOG.debug("Searched Product count whose Best Seller is TRUE : " + searchResult.getCount());
        return searchResult.getResult();
    }

    private String modifyHTML(final String html) {
        final List<String> strings = Arrays
                .asList(StringUtils.substringBetween(html, "<ul>", "</ul>").replaceAll("^.*?<li>", "").split("</li>.*?(<li>|$)"));
        final String htmlContent = "<ul><li>" + strings.get(2) + "</li><li>" + strings.get(1) + "</li><li>" + strings.get(0) + "</li></ul>";
        return html.split("<ul>")[0].concat(htmlContent).concat("</ul>");
    }
}
