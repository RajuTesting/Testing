package com.borngroup.ssl.core.product.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.sslfacades.services.data.ProductPincodeData;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.data.response.IADataResponseDTO;
import com.borngroup.ssl.core.data.response.IARecommendationDTO;
import com.borngroup.ssl.core.data.response.IAResponseDTO;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.ProductSkuListModel;
import com.borngroup.ssl.core.model.components.RecommendedProductsComponentModel;
import com.borngroup.ssl.core.product.dao.SslProductDao;
import com.borngroup.ssl.core.product.service.SslProductService;
import com.borngroup.ssl.storepickup.store.data.PickupStoreData;
import com.borngroup.ssl.storepickup.store.data.StoreWisePickupStoreData;
import com.borngroup.ssl.storepickup.store.data.StoreWiseProductData;

/**
 * @author Viji
 */
public class SslDefaultProductService extends DefaultProductService implements SslProductService {

    private static final String SSL_PRODUCT_CATALOG = "sslProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    @Resource(name = "sslProductDao")
    private SslProductDao sslProductDao;

    @Resource
    private UserService userService;

    @Resource
    private CatalogVersionService catalogVersionService;

    /**
     * The session service.
     */
    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "modelService")
    private ModelService modelService;

    /**
     * resttemplate {@link RestOperations}.
     */
    @Resource(name = "defaultSolrRestTemplate")
    private RestOperations restTemplate;

    /**
     * Http Protocol.
     */
    private static final String HTTP_PROTOCOL = "http://";
    private static final String POPULARITY_RANK_DAYS = "solr.sort.popularity.days";
    private static final Logger LOG = Logger.getLogger(SslDefaultProductService.class);

    /**
     * To get the size guide for a product based on the category and brand it is associated with. It returns String which is the html of the
     * size guide.
     */
    @Override
    public String getProductSizeGuide(final ProductModel productModel) {
        return getSslProductDao().getProductSizeGuide(productModel);
    }

    private SslProductDao getSslProductDao() {
        return sslProductDao;
    }

    /**
     * This method checks for product status and redirect to new page if any one of below condition meets. A) If base product is Unapproved.
     * B) If base is approved and all style variants are unapproved. C) If base is approved and * style variants are approved then check for
     * all size variants. If all size variants are unapproved.
     *
     * @param productModel
     * @return boolean
     */

    @Override
    public boolean checkGoogleRedirectionForProduct(final ProductModel productModel) {
        boolean googleRedirect = false;
        try {
            if (productModel != null && productModel instanceof ApparelProductModel) {
                if (!(productModel.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))) {
                    googleRedirect = true;
                } else {
                    final Collection<VariantProductModel> variants = productModel.getVariants();
                    if (CollectionUtils.isNotEmpty(variants)) {
                        for (final VariantProductModel variantProductModel : variants) {
                            if (variantProductModel.getApprovalStatus() != null
                                    && StringUtils.isNotEmpty(variantProductModel.getApprovalStatus().getCode())) {
                                if(ArticleApprovalStatus.APPROVED.getCode().equals(variantProductModel.getApprovalStatus().getCode())){
                                	googleRedirect = false;
                                	break;
                                }else{
                                	googleRedirect = true;
                                }
                            }
                        }
                        if (!googleRedirect) {
                            for (final VariantProductModel variantProductModel : variants) {
                                final Collection<VariantProductModel> styleVariantProductModels = variantProductModel.getVariants();
                                for (final VariantProductModel sizeVariantProductModel : styleVariantProductModels) {
                                    if (sizeVariantProductModel.getApprovalStatus() != null
                                            && StringUtils.isNotEmpty(sizeVariantProductModel.getApprovalStatus().getCode())) {
                                        if(ArticleApprovalStatus.APPROVED.getCode().equals(sizeVariantProductModel.getApprovalStatus().getCode())){
                                        	googleRedirect = false;
                                        	break;
                                        }else{
                                        	googleRedirect = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (final Exception e) {
            LOG.error("Error during checkGoogleRedirectionForProduct with exception" + ExceptionUtils.getFullStackTrace(e));
        }

        return googleRedirect;
    }

    @Override
    public void changeApprovalStatusInSearchRestriction(final boolean active) {
        getSslProductDao().changeApprovalStatusInSearchRestriction(active);
    }

    @Override
    public ProductModel getProductForCodeUsingAdminAccess(final String code) {
        final UserModel adminUser = userService.getAdminUser();
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SSL_PRODUCT_CATALOG, CATALOG_VERSION_NAME);
        validateParameterNotNull(code, "Parameter code must not be null");
        final List<ProductModel> products = getSessionService().executeInLocalView(new SessionExecutionBody() {
            @Override
            public Object execute() {

                return getProductDao().findProductsByCode(catalogVersion, code);
            }
        }, adminUser);

        validateIfSingleResult(products, format("Product with code '%s' not found!", code),
                format("Product code '%s' is not unique, %d products found!", code, products.size()));

        return products.get(0);
    }

    private String buildRecommendationUri(final Map<String, String> params) {
        final String uri = HTTP_PROTOCOL + SslCoreConstants.RecommendationConstants.InfiniteAnalyticsUrl;
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
        if (params != null) {
            for (final Map.Entry<String, String> param : params.entrySet()) {
                builder.queryParam(param.getKey(), param.getValue());
            }
        }
        return builder.toUriString();
    }

    /**
     * @param components
     * @param params
     * @return List<ProductSkuListModel>
     */
    @Override
    public List<ProductSkuListModel> getProductRecommendations(final List<RecommendedProductsComponentModel> components,
            final Map<String, String> params) {

        final String uri = buildRecommendationUri(params);
        final Instant start = Instant.now();
        LOG.debug(String.format("Hitting IA recommendations & url= %s", uri));
        final HttpHeaders httpHeaders = this.getHttpHeader();
        final HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        IAResponseDTO recommendationResponse = null;
        try {
            final ResponseEntity<IAResponseDTO> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, IAResponseDTO.class);
            recommendationResponse = response.getBody();
        } catch (final RestClientException e) {
            LOG.error(String.format("[RecommendedProducts] Error in connecting due to exception in RestClient: %s", e.getMessage()));
        } catch (final Exception e) {
            LOG.error(String.format("Exception Caught : %s \nWhile trying to hit url: %s", uri, e.getMessage()));
        }
        final Instant end = Instant.now();
        LOG.debug(String.format("Spent %s during this IA call", Duration.between(start, end)));

        final List<ProductSkuListModel> requestRecommendations = new ArrayList<>();
        if (recommendationResponse != null && recommendationResponse.getData() != null) {
            final List<IADataResponseDTO> dataList = recommendationResponse.getData();
            final Map<String, List<IARecommendationDTO>> dataMap = new HashMap<>();
            // query 0 position -> recommendations, 1st position ->
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (final IADataResponseDTO data : dataList) {
                    dataMap.put(data.getMessage(), data.getRecommendations());
                }
            }
            for (final RecommendedProductsComponentModel component : components) {

                final ProductSkuListModel productSkuList = new ProductSkuListModel();
                final List<String> list = new ArrayList<>();
                if (dataMap.containsKey(component.getRecommendationType())) {
                    final List<IARecommendationDTO> recommendations = dataMap.get(component.getRecommendationType());
                    final String requestId = recommendationResponse.getRequestId();
                    final String label = component.getDisplayHeader();
                    if (recommendations != null) {
                        list.addAll(recommendations.stream().map(IARecommendationDTO::getSiteProductId)
                                .filter(s -> StringUtils.contains(s, "p-")).map(s -> s.substring(2)).collect(Collectors.toList()));
                        productSkuList.setSkuList(list);
                        productSkuList.setRequestId(requestId);
                        productSkuList.setLabel(label);
                        productSkuList.setFilterbyStock(component.getFilterByStock());
                        productSkuList.setCountFilter(component.getProductCount());
                    }
                    requestRecommendations.add(productSkuList);
                }

            }
            return requestRecommendations;
        }
        return requestRecommendations;
    }

    private HttpHeaders getHttpHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @Override
    public List<ProductModel> getProducts(@NotNull
    final List<String> code, @NotNull
    final CatalogVersionModel catalog, final boolean sort) {
        return getSslProductDao().getProducts(code, catalog, sort);
    }

    @Override
    public List<ApparelStyleVariantProductModel> getProductsForCategory(final String categoryCodes, final String seasonCode,
            final String approvalStatus, final Date productCreationDate) {
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SSL_PRODUCT_CATALOG, CATALOG_VERSION_NAME);
        return sslProductDao.getProductsForCategory(categoryCodes, catalogVersion, seasonCode, approvalStatus, productCreationDate);
    }

    @Override
    public void updatePopularity(final ProductModel product, final int value) {
        try {
            final int bucketSize = Config.getInt(POPULARITY_RANK_DAYS, 30);

            final Date currentDate = new Date();
            final String date = new SimpleDateFormat("YYMMdd").format(currentDate);

            final Map<String, Long> ranks = new HashMap<>(product.getPopularityRank());
            if (ranks.size() == bucketSize) {
                if (ranks.containsKey(date)) {
                    Long currentRank = ranks.get(date);
                    currentRank += value;
                    ranks.put(date, currentRank);
                } else {
                    final NavigableSet<String> sortedDates = new TreeSet<>(Collections.reverseOrder());
                    sortedDates.addAll(ranks.keySet());
                    ranks.remove(sortedDates.last());
                    ranks.put(date, (long) value);
                }
            } else if (ranks.size() < bucketSize) {
                if (ranks.containsKey(date)) {
                    Long currentDayRank = ranks.get(date);
                    currentDayRank = currentDayRank + value;
                    ranks.put(date, currentDayRank);
                } else {
                    ranks.put(date, (long) value);
                }
            } else {
                if (ranks.containsKey(date)) {
                    Long currentRank = ranks.get(date);
                    currentRank += value;
                    ranks.put(date, currentRank);
                } else {
                    ranks.put(date, (long) value);
                }
                final Set<String> sortedDates = new TreeSet<>(Collections.reverseOrder());
                sortedDates.addAll(ranks.keySet());
                final Iterator<String> iterator = sortedDates.iterator();
                while (ranks.size() != bucketSize) {
                    ranks.remove(iterator.next());
                }
            }

            product.setPopularityRank(ranks);
            getModelService().save(product);

            final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SslCoreConstants.CATALOG_NAME,
                    SslCoreConstants.CATALOG_VERSION);
            final ProductModel stagedProduct = getProductForCode(catalogVersion, product.getCode());
            stagedProduct.setPopularityRank(ranks);
            getModelService().save(stagedProduct);
        } catch (ModelNotFoundException | UnknownIdentifierException | AmbiguousIdentifierException | ModelSavingException
                | NumberFormatException e) {
            LOG.error("Exception in updating popularity");
            LOG.debug(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public List<ApparelProductModel> getBaseProductsForCategory(final String categoryCode) {
        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SSL_PRODUCT_CATALOG, CATALOG_VERSION_NAME);
        return sslProductDao.getBaseProductsForCategory(categoryCode, catalogVersion);
    }

    @Override
    public List<ProductModel> getBestSellerProductsForCategory(final CategoryModel categoryCode, final Integer date) {
        List<ProductModel> productsList = sslProductDao.getBestSellerProductsForCategory(categoryCode, date);
        if (productsList.size() > 20) {
            productsList = productsList.subList(0, 20);
        }
        return productsList;
    }

    @Override
    public String getProductFeatureValueByCode(final String code, final PK productPk) {
        return sslProductDao.getProductFeatureValueByCode(code, productPk);
    }

    @Override
    public List<ProductSkuListModel> getSimilarProducts(final List<RecommendedProductsComponentModel> components,
            final Map<String, String> params) {

        String productCode = params.get("site_product_id");
        productCode = productCode.substring(2);
        final ProductModel product = getProductForCodeUsingAdminAccess(productCode);
        final ProductModel baseProduct = getBaseProduct(product);
        final List<ProductSkuListModel> requestRecommendations = new ArrayList<>();
        for (final RecommendedProductsComponentModel component : components) {

            final ProductSkuListModel productSkuList = new ProductSkuListModel();
            final List<String> list = new ArrayList<>();
            if (baseProduct instanceof ApparelProductModel) {
                final ApparelProductModel apparelProduct = (ApparelProductModel) baseProduct;
                final String label = component.getDisplayHeader();
                if (CollectionUtils.isEmpty(apparelProduct.getSimilarProducts())) {
                    return Collections.emptyList();
                }
                list.addAll(apparelProduct.getSimilarProducts());
                productSkuList.setSkuList(list);
                productSkuList.setLabel(label);
                productSkuList.setFilterbyStock(component.getFilterByStock());
                productSkuList.setCountFilter(component.getProductCount());
                requestRecommendations.add(productSkuList);
            }

        }
        return requestRecommendations;

    }

    @Override
    public ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    private void copyStoreData(final PickupStoreData storeDestination, final PickupStoreData storeSource) {
        storeDestination.setOdcCode(storeSource.getOdcCode());
        storeDestination.setStorePickupTimings(storeSource.getStorePickupTimings());
        storeDestination.setStockQuantity(storeSource.getStockQuantity());
        storeDestination.setStoreName(storeSource.getStoreName());
        storeDestination.setStoreAddressData(storeSource.getStoreAddressData());
        storeDestination.setStoreCode(storeSource.getStoreCode());
        storeDestination.setLatitude(storeSource.getLatitude());
        storeDestination.setLongitude(storeSource.getLongitude());
    }

    @Override
    public boolean checkSameStorePickup(final CartData sessionCart, final List<ProductPincodeData> pinCodeData,
            final List<StoreWisePickupStoreData> productPickupStoresInfo, final PickupStoreData storeData, final Model model) {
        boolean sameStore = false;
        if (!productPickupStoresInfo.isEmpty()) {
            if (productPickupStoresInfo.size() == 1) {
                sameStore = true;

                for (final StoreWisePickupStoreData productPickupStockAvailabilityData : productPickupStoresInfo) {
                    productPickupStockAvailabilityData.getProductList();
                    for (final StoreWiseProductData productData : productPickupStockAvailabilityData.getProductList()) {
                        for (final ProductPincodeData pinCodeDataEntry : pinCodeData) {
                            if (productData.getProduct().getCode().equals(pinCodeDataEntry.getProductSizeVariant())
                                    && productData.getStockQuantity() != 0) {
                                copyStoreData(storeData, productPickupStockAvailabilityData.getStoreData());
                                model.addAttribute("itemsUnavailable",
                                        sessionCart.getEntries().size() - productPickupStockAvailabilityData.getAvailableProductCount());
                                pinCodeDataEntry.setAvailableAtPickupStore(productPickupStockAvailabilityData.getStoreData());
                            }
                        }
                    }
                }
            }

        }

        if (sameStore) {
            model.addAttribute("OnePickupStore", storeData);
            sessionService.setAttribute("pickupStore", storeData);
        }

        return sameStore;
    }

    @Override
    public boolean checkAllProductsInStore(final CartData sessionCart, final List<ProductPincodeData> pinCodeData,
            final List<StoreWisePickupStoreData> storeWiseData, final PickupStoreData storeData, final String pinCode, final Model model) {
        boolean allProductsInStore = false;
        for (final StoreWisePickupStoreData data : storeWiseData) {
            final List<StoreWiseProductData> list = data.getProductList();
            int count = 0;
            for (final StoreWiseProductData product : list) {
                if (product.getStockQuantity() != 0) {
                    count++;
                }
            }
            if (data.getStoreData() != null && data.getStoreData().getStoreAddressData() != null
                    && data.getStoreData().getStoreAddressData().getPostalCode().equals((pinCode))
                    && count == sessionCart.getEntries().size()) {
                model.addAttribute("allProductsInStore", data.getStoreData());
                sessionService.setAttribute("pickupStore", data.getStoreData());
                model.addAttribute("itemsUnavailable", sessionCart.getEntries().size() - data.getAvailableProductCount());
                allProductsInStore = true;
                for (final StoreWiseProductData productData : data.getProductList()) {
                    for (final ProductPincodeData pinCodeDataEntry : pinCodeData) {
                        if (productData.getProduct().getCode().equals(pinCodeDataEntry.getProductSizeVariant())
                                && productData.getStockQuantity() != 0) {
                            copyStoreData(storeData, data.getStoreData());
                            pinCodeDataEntry.setAvailableAtPickupStore(storeData);
                        }
                    }
                }
            } else {
                for (final StoreWiseProductData productData : data.getProductList()) {
                    for (final ProductPincodeData pinCodeDataEntry : pinCodeData) {
                        if (productData.getProduct().getCode().equals(pinCodeDataEntry.getProductSizeVariant())
                                && productData.getStockQuantity() != 0) {
                            pinCodeDataEntry.setAvailableAtPickupStore(storeData);
                        }
                    }
                }
            }
        }
        return allProductsInStore;
    }

    /*
     *
     *
     * @see com.borngroup.sssl.core.product.service.SslProductService#resetBestSellerProducts()
     */
    @Override
    public void resetBestSellerProducts() {
        sslProductDao.resetBestSeller().stream().filter(Objects::nonNull).forEach((p) -> {
            p.setBestSeller(false);
            modelService.save(p);
        });
    }
}
