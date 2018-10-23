package com.borngroup.ssl.core.interceptors;

import de.hybris.platform.commerceservices.promotion.CommercePromotionService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.promotions.model.ProductBundlePromotionModel;
import de.hybris.platform.promotions.model.PromotionPriceRowModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.StandardDateRange;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;
import com.borngroup.ssl.core.model.SslBundleProductModel;
import com.borngroup.ssl.core.services.SSLBrandsService;
import com.borngroup.ssl.core.services.SSLBundleLookupService;

public class SSLBundleProductLoadInterceptor implements LoadInterceptor {

	private static final Logger LOG = Logger.getLogger(SSLBundleProductLoadInterceptor.class);

	@Resource(name = "i18nService")
	I18NService i18nService;

	@Resource(name = "productService")
	ProductService productService;

	@Resource(name = "modelService")
	ModelService modelService;
	

	@Resource(name = "sslBundleLookupService")
	SSLBundleLookupService sslBundleLookupService;
	
    @Resource(name = "commercePromotionService")
    CommercePromotionService    commercePromotionService;


	public SSLBundleProductLoadInterceptor() {
	}

	@Override
    public void onLoad(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        if (model instanceof SslBundleProductModel)
        {
            final SslBundleProductModel bundleProductModel = (SslBundleProductModel)model;
            final Collection<PriceRowModel> priceRows =sslBundleLookupService.getAllPriceRows(bundleProductModel.getCode());
            PriceRowModel currentPriceRow=null;
            boolean isChecked=false;

            for (PriceRowModel priceRow : priceRows) {
            	if (priceRow.getIsSumofProductsPriceRow().booleanValue() &&(null == priceRow.getDateRange() || priceRow.getDateRange().encloses(new Date())))
                {
            	isChecked=true;
            	currentPriceRow=priceRow;
                }
			}
            if (CollectionUtils.isEmpty(priceRows) || isChecked)
            {
                final CurrencyModel currency = i18nService.getCurrency("INR");
                final List<ProductModel> bundleItems = bundleProductModel.getBundleItems();
                double totalPrice = 0.0;
                Collection<PriceRowModel> basePriceRows=null;
                for(final ProductModel bundleItem : bundleItems){
                	
                	if(bundleItem instanceof ApparelProductModel)
                	{
                		if((bundleItem.getVariants() != null) && !CollectionUtils.isEmpty(bundleItem.getVariants())){
                    	
                    	 final VariantProductModel variantModel = bundleItem.getVariants().iterator().next();
                         	if(variantModel.getVariants()!= null  && !CollectionUtils.isEmpty(variantModel.getVariants()))
                         	{
                         		basePriceRows=variantModel.getVariants().iterator().next().getEurope1Prices();
                         	}
                       if(CollectionUtils.isEmpty(basePriceRows)){
                    	   basePriceRows= bundleItem.getVariants().iterator().next().getEurope1Prices();
                        	}
                       
                		}
                   
                	}
                	else if(bundleItem instanceof ApparelSizeVariantProductModel)
                	{
                	    basePriceRows=bundleItem.getEurope1Prices();
                    }
                	else if(bundleItem instanceof ApparelStyleVariantProductModel)
                	{
                		if((bundleItem.getVariants() != null) && !CollectionUtils.isEmpty(bundleItem.getVariants())){
                			basePriceRows = bundleItem.getVariants().iterator().next().getEurope1Prices();
                		}
                		if(CollectionUtils.isEmpty(basePriceRows)){
                    	 basePriceRows=bundleItem.getEurope1Prices();
                		}
                   
                   }
                	
                	if(CollectionUtils.isNotEmpty(basePriceRows))
                	{
                	for(final PriceRowModel basePriceRow : basePriceRows){
                     	if(basePriceRow.getCurrency().equals(currency))
                     		{
                     		totalPrice=totalPrice+basePriceRow.getPrice().doubleValue();
                     		break;
                     		}
                      }
                	}
                }

                final Collection<PriceRowModel> priceRowListforBundleProduct ;
                final PriceRowModel priceRowforBundleProduct;
                if(isChecked)
                {
                	priceRowforBundleProduct=currentPriceRow;
                     priceRowforBundleProduct.setPrice(Double.valueOf(totalPrice));
                     final StandardDateRange value = new StandardDateRange(bundleProductModel.getOnlineDate(),
                             bundleProductModel.getOfflineDate());
                     priceRowforBundleProduct.setDateRange(value);
                     modelService.save(priceRowforBundleProduct);
                }
                else
                {
                priceRowListforBundleProduct = new ArrayList<PriceRowModel>();
                priceRowforBundleProduct = modelService.create(PriceRowModel.class);
                priceRowforBundleProduct.setUnit(productService.getUnit("pieces"));
                priceRowforBundleProduct.setCurrency(currency);
                priceRowforBundleProduct.setMinqtd(Long.valueOf(1));
                priceRowforBundleProduct.setPrice(Double.valueOf(totalPrice));
                priceRowforBundleProduct.setProductId(bundleProductModel.getCode());
                priceRowforBundleProduct.setIsSumofProductsPriceRow(Boolean.TRUE);
                final StandardDateRange value = new StandardDateRange(bundleProductModel.getOnlineDate(),
                        bundleProductModel.getOfflineDate());
                priceRowforBundleProduct.setDateRange(value);
                priceRowListforBundleProduct.add(priceRowforBundleProduct);
                modelService.save(priceRowforBundleProduct);
                bundleProductModel.setEurope1Prices(priceRowListforBundleProduct);
                }
                
                //changed price validator code to bundle interceptor
                ProductBundlePromotionModel promotionModel = null;
                try
                {
                    promotionModel = (ProductBundlePromotionModel) commercePromotionService.getPromotion(bundleProductModel.getCode()
                            + "_BP");

                    if (!promotionModel.getCode().isEmpty())
                    {
                        LOG.info("Promotion exists for bundle product " + bundleProductModel.getCode());
                    }
                }
                catch (final Exception exp)
                {
                    LOG.warn("No Promotion exists for " + bundleProductModel.getCode());
                }
                if (SslCoreConstants.COUNTRY_CURRENCY_INDIA.equalsIgnoreCase(priceRowforBundleProduct.getCurrency().getIsocode())
                        && promotionModel != null)
                {
                    final Collection<PromotionPriceRowModel> priceRowListforBundlePromotion = new ArrayList<PromotionPriceRowModel>();
                    final PromotionPriceRowModel priceRowforPromotion = modelService.create(PromotionPriceRowModel.class);
                    priceRowforPromotion.setPrice(priceRowforBundleProduct.getPrice());
                    priceRowforPromotion.setCurrency(i18nService.getCurrency(SslCoreConstants.COUNTRY_CURRENCY_INDIA));
                    modelService.save(priceRowforPromotion);
                    priceRowListforBundlePromotion.add(priceRowforPromotion);
                    promotionModel.setBundlePrices(priceRowListforBundlePromotion);
                    modelService.save(promotionModel);
                }
            }
        
        }
    }
}
