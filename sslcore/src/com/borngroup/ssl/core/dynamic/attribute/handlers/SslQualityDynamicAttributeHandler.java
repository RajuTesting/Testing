/**
 * 
 */
package com.borngroup.ssl.core.dynamic.attribute.handlers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.variants.model.VariantProductModel;

import javax.annotation.Resource;

import com.borngroup.ssl.core.model.SslBundleProductModel;
import com.borngroup.ssl.core.review.service.SslCustomerReviewService;


/**
 * @author Viji
 * 
 */
public class SslQualityDynamicAttributeHandler implements DynamicAttributeHandler<Double, ProductModel>
{
    @Resource(name = "sslCustomerReviewService")
    private SslCustomerReviewService sslCustomerReviewService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model
     * .AbstractItemModel)
     */
    @Override
    public Double get(final ProductModel productModel)
    {
        return getSslCustomerReviewService().getAveragQuality(getBaseProduct(productModel));
    }

    protected ProductModel getBaseProduct(final ProductModel product)
    {
        if (product instanceof VariantProductModel)
        {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        else if (product instanceof SslBundleProductModel)
        {
            return product;
        }
        return product;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model
     * .AbstractItemModel, java.lang.Object)
     */
    @Override
    public void set(final ProductModel arg0, final Double arg1)
    {
        //throw new UnsupportedOperationException();

    }

    public SslCustomerReviewService getSslCustomerReviewService()
    {
        return sslCustomerReviewService;
    }

    public void setSslCustomerReviewService(final SslCustomerReviewService sslCustomerReviewService)
    {
        this.sslCustomerReviewService = sslCustomerReviewService;
    }
}
