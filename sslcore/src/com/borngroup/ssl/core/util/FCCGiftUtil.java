/**
 * 
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * FCCGiftUtil.java for FCC Utility. 
 * @author raju.p@techouts.com
 *
 */
public class FCCGiftUtil {
	
	private FCCGiftUtil() {
         //
	}

    /**
     * Method for checking consignment having only FCC product
     * @param consignment
     * @return return true if consignment having  only FCC otherwise false
     */
    public  static boolean isFCCConsignment(ConsignmentModel consignment) {
   		for(ConsignmentEntryModel entry:consignment.getConsignmentEntries()){
   			if(!isFCCProduct(entry.getOrderEntry().getProduct()))
   			{
   				return false;
   			}
   		}
   		return true;
   	}
    
    /**
     * Method for checking consignment having  FCC product or not
     * @param consignment
     * @return return true if consignment having   FCC otherwise false
     */
    public static boolean hasFCCProduct(ConsignmentModel consignment){
    	for(ConsignmentEntryModel entry:consignment.getConsignmentEntries()){
   			if(isFCCProduct(entry.getOrderEntry().getProduct()))
   			{
   				return true;
   			}
   		}
   		return false;
    }
    
    /**
     * Method for checking consignment has Gift product
     * @param consignment
     * @return true if consignment have Gift product otherwise false
     */
	public static boolean hasGiftProduct(ConsignmentModel consignment) {
		for (ConsignmentEntryModel entry : consignment.getConsignmentEntries()) {
			if (isGiftProduct(entry.getOrderEntry().getProduct())) {
				return true;
			}
		}
		return false;
	}
    
    /**
     * Checks if is  gift product.
     *
     * @param product the product
     * @return true if this SKU is an e-gv or its base product is an e-gv.
     */
    public static boolean isGiftProduct(final ProductModel product) {
		return Boolean.TRUE.equals(product.getIsGiftProduct())
				|| Boolean.TRUE.equals(getBaseProduct(product).getEGift());
    
    }
    
	/**
	 * Gets the base product.
	 *
	 * @param product
	 *            the product
	 * @return the base product model
	 */
	private static ProductModel getBaseProduct(final ProductModel product) {
		if (product instanceof VariantProductModel) {
			return getBaseProduct(((VariantProductModel) product)
					.getBaseProduct());
		}
		return product;
	}
	
	/**
	 * Checking product is FCC or not
	 * @param product
	 * @return true if FCC product otherwise false
	 */
	public static boolean isFCCProduct(ProductModel product) {
		return product.getCode().equals(
				Config.getString(SslCoreConstants.FCC_PRODUCT_CODE, "999"));
	}
}
