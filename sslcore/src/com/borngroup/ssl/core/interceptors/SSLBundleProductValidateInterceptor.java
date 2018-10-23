/**
 * 
 */



package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.commerceservices.promotion.CommercePromotionService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductBundlePromotionModel;
import de.hybris.platform.promotions.model.PromotionPriceRowModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.i18n.impl.DefaultCommonI18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SslBundleProductModel;


/**
 * @author sriharsha.karuturi
 * 
 */



public class SSLBundleProductValidateInterceptor implements ValidateInterceptor<SslBundleProductModel>
{

	private static final Logger LOG = Logger.getLogger(SSLBundleProductValidateInterceptor.class);

	@Resource
	ModelService modelService;

	@Resource
	DefaultCommonI18NService commonI18NService;

	@Resource(name = "promotionsService")
	PromotionsService promotionsService;

	@Resource(name = "commercePromotionService")
	CommercePromotionService commercePromotionService;

	@Override
	public void onValidate(final SslBundleProductModel bundleProductModel, final InterceptorContext arg1)
			throws InterceptorException
	{

		boolean productListOK = false;
		try
		{
			final ProductBundlePromotionModel promotionModel = (ProductBundlePromotionModel) commercePromotionService
					.getPromotion(bundleProductModel.getCode() + "_BP");

			if (!promotionModel.getCode().isEmpty())
			{
				modelService.remove(promotionModel);
			}
		}
		catch (final Exception exp)
		{
			LOG.warn("No Promotion exists for " + bundleProductModel.getCode());
		}

		if (!StringUtils.isEmpty(bundleProductModel.getCode()))
		{
			final List<ProductModel> productList = bundleProductModel.getBundleItems();

			if (!productList.isEmpty())
			{

				for (final ProductModel product : productList)
				{
					if (product instanceof SslBundleProductModel)
					{
						throw new InterceptorException("Please add individual products to bundle and not BundleProduct ");
					}
				}
				final ProductModel baseProduct = checkBaseProducts(productList);
				if(baseProduct !=null)
				{
					throw new InterceptorException("This product :"+baseProduct.getCode()+" is already added");
				}
				final Collection<ProductModel> plist = new ArrayList<ProductModel>();
				final ProductBundlePromotionModel bundlePromotion = modelService.create(ProductBundlePromotionModel.class);
				bundlePromotion.setCode(bundleProductModel.getCode() + "_BP");
				bundlePromotion.setDescription("Creating a Promotion with name " + bundlePromotion.getCode());
				bundlePromotion.setPromotionGroup(promotionsService.getPromotionGroup(SslCoreConstants.PROMOTION_GROUP_SSL));
				bundlePromotion.setEnabled(true);
				bundlePromotion.setStartDate(bundleProductModel.getOnlineDate());
				bundlePromotion.setEndDate(bundleProductModel.getOfflineDate());
				plist.addAll(bundleProductModel.getBundleItems());

				bundlePromotion.setProducts(plist);
				try
				{
					modelService.save(bundlePromotion);
					final Collection<PriceRowModel> priceRows = bundleProductModel.getEurope1Prices();
					if (!CollectionUtils.isEmpty(priceRows))
					{
						for (final PriceRowModel priceRow : priceRows)
						{
							if (SslCoreConstants.COUNTRY_CURRENCY_INDIA.equalsIgnoreCase(priceRow.getCurrency().getIsocode()))
							{
								final Collection<PromotionPriceRowModel> priceRowListforBundlePromotion = new ArrayList<PromotionPriceRowModel>();
								final PromotionPriceRowModel priceRowforPromotion = modelService.create(PromotionPriceRowModel.class);
								priceRowforPromotion.setPrice(priceRow.getPrice());
								priceRowforPromotion.setCurrency(commonI18NService.getCurrency(SslCoreConstants.COUNTRY_CURRENCY_INDIA));
								modelService.save(priceRowforPromotion);
								priceRowListforBundlePromotion.add(priceRowforPromotion);
								bundlePromotion.setBundlePrices(priceRowListforBundlePromotion);
								modelService.save(bundlePromotion);
								break;
							}
						}
					}
				}
				catch (final ModelSavingException exception)
				{
					LOG.error("The exception is" + exception.getMessage());
				}
				productListOK = true;
			}
		}

		if (!productListOK)
		{
			throw new InterceptorException("Please add products for the bundle created ");

		}

	}
	
	private ProductModel checkBaseProducts(final List<ProductModel> items)
	{
		final List<ProductModel> baseProducts=new ArrayList<ProductModel>();
		for (ProductModel productModel : items) {
			ProductModel baseProduct=getBaseProduct(productModel);
			if(baseProduct!=null)
			{
				baseProducts.add(baseProduct);
			}
			
		}
		//comparing each and every bundle item  with others
		for (ProductModel productModel1 : baseProducts) {
			int count=0;
			for (ProductModel productModel2 : baseProducts) {
				if(productModel1.getCode().equals(productModel2.getCode()))
				{
					count++;
				}
			}
			if(count>1)
			{
				return productModel1;
			}
		}
		
		return null;
		
	}
	private ProductModel getBaseProduct(ProductModel productModel)
	{
		
			if(productModel instanceof ApparelProductModel)
			{
				return productModel;
			}
			else if(productModel instanceof ApparelStyleVariantProductModel )
			{
				return ((ApparelStyleVariantProductModel) productModel).getBaseProduct();
			}
			else if(productModel instanceof ApparelSizeVariantProductModel)
			{
				return getBaseProduct(((ApparelSizeVariantProductModel) productModel).getBaseProduct());
			}
			return null;
	}
}
