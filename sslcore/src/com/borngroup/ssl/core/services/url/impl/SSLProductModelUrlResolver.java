/**
 *
 */
package com.borngroup.ssl.core.services.url.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.util.Config;

import java.util.List;

import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * @author kanagaraj.k
 */
public class SSLProductModelUrlResolver extends DefaultProductModelUrlResolver {

    @Override
    protected String buildPathString(final List<CategoryModel> path) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i != 0) {
                result.append('-');
            }
            result.append(urlSafe(path.get(i).getName()));
        }
        String urlStr = result.toString();
        if (urlStr.endsWith("-")) {
            urlStr = urlStr.substring(0, urlStr.length() - 1);
        }
        return urlStr;
    }

    @Override
    protected String urlSafe(final String text) {
        if ((text == null) || (text.isEmpty())) {
            return "";
        }
        String cleanedText = text.toLowerCase().replaceAll("[^\\w]", "-");
        cleanedText = cleanedText.replaceAll("-+", "-");
        return cleanedText;
    }

    @Override
    protected String resolveInternal(final ProductModel source) {
        ProductModel baseProduct = getBaseProduct(source);

        if (getBaseProduct(baseProduct) != null) {
            baseProduct = getBaseProduct(baseProduct);
        }

        final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();

        String url = getPattern();
        if ((currentBaseSite != null) && (url.contains("{baseSite-uid}"))) {
            url = url.replace("{baseSite-uid}", currentBaseSite.getUid());
        }
        if (url.contains("{category-path}")) {
            url = url.replace("{category-path}", buildPathString(getCategoryPath(baseProduct)));
        }
        if (url.contains("{product-name}") && !source.getClass().equals(ApparelStyleVariantProductModel.class)) {
			if (!source.getClass().equals(ApparelSizeVariantProductModel.class)
					|| source.getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code"))) {
				url = url.replace("{product-name}", urlSafe(baseProduct.getBrandCode() + "-" + baseProduct.getName()));
			}
        }
        if (url.contains("{product-code}")) {
            if (source.getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code"))) {
                if (url.contains("/{product-name}")) {
                    url = url.replace("{product-name}", urlSafe(baseProduct.getBrandCode() + "-" + baseProduct.getName()));
                }
                url = url.replace("{product-code}", source.getCode());
            } else if (source instanceof ApparelSizeVariantProductModel
                    && ((ApparelSizeVariantProductModel) source).getBaseProduct() instanceof ApparelStyleVariantProductModel) {
                url = url.replace("/{product-name}", "");
                url = url.replace("{product-code}", ((ApparelSizeVariantProductModel) source).getBaseProduct().getCode())
                        + "/colorChange?colorCode=" + ((ApparelSizeVariantProductModel) source).getBaseProduct().getCode();
            } else if (source instanceof ApparelStyleVariantProductModel) {
                url = url.replace("/{product-name}", "");
                url = url.replace("{product-code}", source.getCode()) + "/colorChange?colorCode=" + source.getCode();
            } else {
                url = url.replace("{product-code}", baseProduct.getCode());
            }
        }
        return url;
    }
}
