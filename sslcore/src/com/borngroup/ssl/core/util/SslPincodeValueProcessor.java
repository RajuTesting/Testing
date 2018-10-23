/**
 *
 */
package com.borngroup.ssl.core.util;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.sslfacades.services.data.CartPincodeCODAvailabilityData;
import de.hybris.platform.sslfacades.services.data.ProductPincodeData;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.fest.util.Collections;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.PincodesListModel;

/**
 * The Class SslPincodeValueProcessor.
 *
 * @author admin
 */
/**
 * @author admin
 *
 */
public class SslPincodeValueProcessor {

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(SslPincodeValueProcessor.class);

    /** The product service. */
    @Resource(name = "productService")
    private ProductService productService;

    /** The cart service. */
    @Resource(name = "cartService")
    private CartService cartService;

    /** The cart converter. */
    @Resource(name = "cartConverter")
    private Converter<CartModel, CartData> cartConverter;

    /**
     * Checks the availability of the product for the given pincode.
     *
     * @param pincode
     *        : Integer
     * @param productModel
     *        : ProductData
     * @return ProductPincodeData
     */
    @SuppressWarnings("boxing")
    public ProductPincodeData checkPincodeForProduct(final Integer pincode, final ProductModel productModel,final ProductModel baseProductModel) {
        final ProductPincodeData productPincodeData = new ProductPincodeData();

        //final ProductModel baseProductModel = getBaseProduct(productModel);
        try {
            productPincodeData.setProductCode(baseProductModel.getCode());
            if (baseProductModel.getIsAvailableForCoD()) {
                if (baseProductModel.getCodPinCodes() == null || baseProductModel.getCodPinCodes().isEmpty()
                        && baseProductModel.getDeliveryPinCodes() == null || baseProductModel.getDeliveryPinCodes().isEmpty()) {

                    productPincodeData.setAvailableForEnteredPincode(true);
                    productPincodeData.setAvailableForCoD(true);
                } else {
                    productPincodeData.setAvailableForEnteredPincode(checkDeliveryPinCodeList(baseProductModel, pincode));
                    productPincodeData.setAvailableForCoD(checkCodPinCodeList(baseProductModel, pincode)
                            && !productModel.getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code"))
                            && !productModel.getEGift());
                }
            } else {
                productPincodeData.setAvailableForEnteredPincode(checkDeliveryPinCodeList(baseProductModel, pincode));
                productPincodeData.setAvailableForCoD(false);
            }

        } catch (final Exception e) {
            LOG.error(e.getMessage());
        }
        return productPincodeData;

    }

    /**
     * Checks the availability of the products in the cart for the given pincode.
     *
     * @param enteredPincode
     *        : Integer
     * @param cartData
     *        : CartData
     * @return List<ProductPincodeData>
     */
    public List<ProductPincodeData> checkPincodeForCart(final Integer enteredPincode, final CartData cartData) {
        final List<ProductPincodeData> productPincodeDataList = new ArrayList<ProductPincodeData>();
        try {
            for (final OrderEntryData entry : cartData.getEntries()) {
                final ProductPincodeData productPincodeData = new ProductPincodeData();

                final ProductModel productModel = productService.getProductForCode(entry.getBaseProductCode());

                productPincodeData.setProductCode(productModel.getCode());
                productPincodeData.setProductSizeVariant(entry.getProduct().getCode());
                if (productModel.getIsAvailableForCoD().booleanValue()) {
                    if (productModel.getCodPinCodes() == null || productModel.getCodPinCodes().isEmpty()
                            && productModel.getDeliveryPinCodes() == null || productModel.getDeliveryPinCodes().isEmpty()) {

                        productPincodeData.setAvailableForEnteredPincode(false);
                        productPincodeData.setAvailableForCoD(false);
                    } else {
                        productPincodeData.setAvailableForEnteredPincode(checkDeliveryPinCodeList(productModel, enteredPincode));
                        productPincodeData.setAvailableForCoD(checkCodPinCodeList(productModel, enteredPincode)
                                && !productModel.getEGift().booleanValue()
                                && !productModel.getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code")));
                    }
                } else {
                    productPincodeData.setAvailableForEnteredPincode(checkDeliveryPinCodeList(productModel, enteredPincode));
                    productPincodeData.setAvailableForCoD(false);
                }
                productPincodeDataList.add(productPincodeData);
            }
        } catch (final Exception e) {
            LOG.error(e.getMessage());
        }
        return productPincodeDataList;
    }

    /**
     * Check pincode for cart api.
     *
     * @param enteredPincode
     *        the entered pincode
     * @return the list
     */
    public List<ProductPincodeData> checkPincodeForCartApi(final Integer enteredPincode) {
        final CartModel cartModel = cartService.getSessionCart();
        final CartData cartData = cartConverter.convert(cartModel);
        return checkPincodeForCart(enteredPincode, cartData);

    }

    /**
     * Check delivery status on checkout.
     *
     * @param enteredPincode
     *        the entered pincode
     * @param cartData
     *        the cart data
     * @return true, if successful
     */
    public boolean checkDeliveryStatusOnCheckout(final Integer enteredPincode, final CartData cartData) {
        boolean isProductsAvailableForDelivery = false;

        for (final OrderEntryData entry : cartData.getEntries()) {
            final ProductModel productModel = productService.getProductForCode(entry.getBaseProductCode());

            isProductsAvailableForDelivery = checkDeliveryPinCodeList(productModel, enteredPincode);

        }
        return isProductsAvailableForDelivery;
    }

    /**
     * Check delivery status on order.
     *
     * @param enteredPincode
     *        the entered pincode
     * @param order
     *        the order
     * @return true, if successful
     */
    public boolean checkDeliveryStatusOnOrder(final Integer enteredPincode, final OrderModel order) {
        boolean isProductsAvailableForDelivery = false;

        for (final AbstractOrderEntryModel entry : order.getEntries()) {
            final ProductModel productModel = getBaseProduct(entry.getProduct());

            isProductsAvailableForDelivery = checkDeliveryPinCodeList(productModel, enteredPincode);

        }
        return isProductsAvailableForDelivery;
    }

    /**
     * Checks if the COD is available for the product on the given pincode.
     *
     * @param productModel
     *        : ProductData
     * @param enteredPincode
     *        : Integer
     * @return boolean
     */
    private boolean checkCodPinCodeList(final ProductModel productModel, final Integer enteredPincode) {
        return isProductAvailableAtUserPincode(productModel.getCodPinCodes(), enteredPincode);
    }

    private boolean isProductAvailableAtUserPincode(final List<PincodesListModel> pincodeList, final Integer enteredPincode) {
        if (Collections.isEmpty(pincodeList)) {
            return true;
        }
        final String userPincode = String.valueOf(enteredPincode);
        for (final PincodesListModel pincodesListModel : pincodeList) {
            for (final String pincode : pincodesListModel.getPincodes()) {
                if (pincode.equals(userPincode)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * Checks if the product can be delivered on the given pincode.
     *
     * @param productModel
     *        : ProductData
     * @param enteredPincode
     *        : Integer
     * @return boolean
     */
    private boolean checkDeliveryPinCodeList(final ProductModel productModel, final Integer enteredPincode) {
        return isProductAvailableAtUserPincode(productModel.getDeliveryPinCodes(), enteredPincode);
    }

    /**
     * This method is to check the COD availability as a payment option.
     *
     * @param cart
     *        : CartData
     * @return boolean
     */
    public boolean isCodPaymentAvailableForCart(final CartData cart) {
        return checkCartPriceRangeForCod(cart) && !isCartHasFcc(cart) && !isCartHasGv(cart) && isCartAvailableForCOD(cart);
    }

    /**
     * Method to check if cart meets the price range for COD.
     *
     * @param cart
     *        : CartData
     * @return boolean
     */
    public boolean checkCartPriceRangeForCod(final CartData cart) {
        return cart.getTotalPrice().getValue().doubleValue() > Double.parseDouble(Config.getParameter("cod.min.amount") != null ? Config
                .getParameter("cod.min.amount") : "500")
                && cart.getTotalPrice().getValue().doubleValue() <= Double
                        .parseDouble(Config.getParameter("cod.max.amount") != null ? Config.getParameter("cod.max.amount") : "10000");
    }

    /**
     * Method to check if cart has any products as FCC.
     *
     * @param cart
     *        : CartData
     * @return boolean
     */
    public boolean isCartHasFcc(final CartData cart) {

        if (null != cart.getEntries()) {
            for (final OrderEntryData entry : cart.getEntries()) {
                if (entry.getProduct().getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to check if cart has any products as Gift or EGift.
     *
     * @param cart
     *        : CartData
     * @return boolean
     */
    public boolean isCartHasGv(final CartData cart) {
        if (null != cart.getEntries()) {
            for (final OrderEntryData entry : cart.getEntries()) {
                if (entry.getProduct().isEGift() || entry.getProduct().isIsGiftProduct()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is to check the COD availability when the customer add or change his address on checkout page.
     *
     * @param cart
     *        the cart
     * @return true, if is cart available for cod
     */
    public boolean isCartAvailableForCOD(final CartData cart) {
        boolean cartAvailableForCOD = true;

        if (cart.getDeliveryAddress() != null) {
            final int postalcode = Integer.parseInt(cart.getDeliveryAddress().getPostalCode());
            for (final OrderEntryData entry : cart.getEntries()) {
                final ProductModel productModel = productService.getProductForCode(entry.getBaseProductCode());

                if (!entry.getProduct().isIsAvailableForCoD() || !checkCodPinCodeList(productModel, Integer.valueOf(postalcode))) {
                    cartAvailableForCOD = false;
                    break;
                }
            }
        } else {
            cartAvailableForCOD = false;
        }

        return cartAvailableForCOD;
    }

    /**
     * This method is to check the COD availability when the customer add or change his address on checkout page.
     *
     * @param pincode
     *        the pincode
     * @param cart
     *        the cart
     * @return true, if is cart available for cod
     */
    public boolean isCartAvailableForCOD(final String pincode, final CartData cart) {
        boolean cartAvailableForCOD = true;

        final int postalcode = Integer.parseInt(pincode);
        for (final OrderEntryData entry : cart.getEntries()) {
            final ProductModel productModel = productService.getProductForCode(entry.getBaseProductCode());

            if (!entry.getProduct().isIsAvailableForCoD() || !checkCodPinCodeList(productModel, Integer.valueOf(postalcode))) {
                cartAvailableForCOD = false;
                break;
            }
        }

        return cartAvailableForCOD && !isCartHasFcc(cart) && !isCartHasGv(cart);
    }

    /**
     * Gets the cod status for product.
     *
     * @param productCode
     *        the product code
     * @param enteredPincode
     *        the entered pincode
     * @return the cod status for product
     */
    public ProductPincodeData getCodStatusForProduct(final String productCode, final Integer enteredPincode) {

        try {
            final ProductModel product = productService.getProductForCode(productCode);
            final ProductPincodeData productPincodeData = checkPincodeForProduct(enteredPincode, product,getBaseProduct(product));
            return productPincodeData != null ? productPincodeData : null;
        } catch (final Exception e) {
            LOG.error("Failed to get cod status of product", e);
            return null;
        }
    }

    /**
     * Gets the base product.
     *
     * @param product
     *        the product
     * @return the base product
     */
    protected ProductModel getBaseProduct(final ProductModel product) {
        if (product instanceof VariantProductModel) {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    /**
     * Checks if is cod payment available for cart.
     *
     * @param cart
     *        the cart
     * @return true, if is cod payment available for cart
     */
    public boolean isCodPaymentAvailableForCart(final CartModel cart) {
        return checkCartPriceRangeForCod(cart) && !isCartHasFcc(cart) && !isCartHasGv(cart) && isCartAvailableForCOD(cart);
    }

    /**
     * Checks if is cart available for cod.
     *
     * @param cart
     *        the cart
     * @return true, if is cart available for cod
     */
    public boolean isCartAvailableForCOD(final CartModel cart) {
        boolean cartAvailableForCOD = true;
        if (cart.getDeliveryAddress() != null) {
            final int postalcode = Integer.parseInt(cart.getDeliveryAddress().getPostalcode());
            for (final AbstractOrderEntryModel entry : cart.getEntries()) {
                final ProductModel productModel = this.getBaseProduct(entry.getProduct());
                if (productModel.getIsAvailableForCoD() != null && !productModel.getIsAvailableForCoD().booleanValue()
                        || !checkCodPinCodeList(productModel, Integer.valueOf(postalcode))) {
                    cartAvailableForCOD = false;
                    break;
                }
            }
        } else {
            cartAvailableForCOD = false;
        }
        return cartAvailableForCOD;
    }

    /**
     * Checks if is cart has gv.
     *
     * @param cart
     *        the cart
     * @return true, if is cart has gv
     */
    public boolean isCartHasGv(final CartModel cart) {
        if (null != cart.getEntries()) {
            for (final AbstractOrderEntryModel entry : cart.getEntries()) {
                final ProductModel product = entry.getProduct();
                if (product != null
                        && (this.getBaseProduct(product).getEGift().booleanValue() || this.getBaseProduct(product).getIsGiftProduct()
                                .booleanValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if is cart has fcc.
     *
     * @param cart
     *        the cart
     * @return true, if is cart has fcc
     */
    public boolean isCartHasFcc(final CartModel cart) {

        if (null != cart.getEntries()) {
            for (final AbstractOrderEntryModel entry : cart.getEntries()) {
                if (entry.getProduct() != null && entry.getProduct().getCode().equalsIgnoreCase(Config.getParameter("fcc.product.code"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check cart price range for cod.
     *
     * @param cart
     *        the cart
     * @return true, if successful
     */
    public boolean checkCartPriceRangeForCod(final CartModel cart) {
        return cart.getTotalPrice() != null
                && cart.getTotalPrice().doubleValue() > Double.parseDouble(Config.getParameter("cod.min.amount") != null ? Config
                        .getParameter("cod.min.amount") : "500")
                && cart.getTotalPrice().doubleValue() <= Double.parseDouble(Config.getParameter("cod.max.amount") != null ? Config
                        .getParameter("cod.max.amount") : "10000");
    }

    /**
     * Checks if is cart available for cod.
     *
     * @param cartModel
     *        the cart
     * @param enteredPincode
     * @return CartPincodeCODAvailabilityData
     */
    public CartPincodeCODAvailabilityData isCartAndPincodeAvailableForCOD(final CartModel cartModel, final Integer enteredPincode) {
        boolean cartProductsAvailableForCOD = true;
        final CartPincodeCODAvailabilityData cartPincodeCODAvailabilityData = new CartPincodeCODAvailabilityData();
        if (null != enteredPincode) {
            final List<ProductPincodeData> productPincodeDataList = new ArrayList<ProductPincodeData>();
            if (CollectionUtils.isNotEmpty(cartModel.getEntries())) {
                for (final AbstractOrderEntryModel entry : cartModel.getEntries()) {
                    final ProductModel productModel = this.getBaseProduct(entry.getProduct());
                    final ProductPincodeData productPincodeData = checkPincodeForProduct(enteredPincode, productModel,productModel);
                    if (productModel.getIsAvailableForCoD() != null && !productModel.getIsAvailableForCoD().booleanValue()
                            || !productPincodeData.isAvailableForCoD()) {
                        cartProductsAvailableForCOD = false;
                    }
                    productPincodeDataList.add(productPincodeData);
                }
                final boolean availableForCoD = cartProductsAvailableForCOD && checkCartPriceRangeForCod(cartModel)
                        && !isCartHasFcc(cartModel) && !isCartHasGv(cartModel);
                cartPincodeCODAvailabilityData.setAvailableForCoD(availableForCoD);
                cartPincodeCODAvailabilityData.setProductPincodeCODAvailability(productPincodeDataList);
            }
        }
        return cartPincodeCODAvailabilityData;
    }

	/**
	 * @param pinCode
	 * @param sessionCart
	 * @param baseProducts
	 * @return
	 */
	public List<ProductPincodeData> checkPincodeForCart(Integer enteredPincode, CartData sessionCart,Set<ProductModel> baseProducts) {

		final List<ProductPincodeData> productPincodeDataList = new ArrayList<>();
		try {
			for (final OrderEntryData entry : sessionCart.getEntries()) {
				final ProductPincodeData productPincodeData = new ProductPincodeData();

				ProductModel productModel = null;

				for (ProductModel baseProduct : baseProducts) {
					if (baseProduct.getCode().equals(entry.getBaseProductCode())) {
						productModel = baseProduct;
						break;
					}
				}
				if (productModel != null) {
					productPincodeData.setProductCode(productModel.getCode());
					productPincodeData.setProductSizeVariant(entry.getProduct().getCode());
					if (productModel.getIsAvailableForCoD().booleanValue()) {
						if (productModel.getCodPinCodes() == null
								|| productModel.getCodPinCodes().isEmpty() && productModel.getDeliveryPinCodes() == null
								|| productModel.getDeliveryPinCodes().isEmpty()) {

							productPincodeData.setAvailableForEnteredPincode(false);
							productPincodeData.setAvailableForCoD(false);
						} else {
							productPincodeData.setAvailableForEnteredPincode(
									checkDeliveryPinCodeList(productModel, enteredPincode));
							productPincodeData.setAvailableForCoD(checkCodPinCodeList(productModel, enteredPincode)
									&& !productModel.getEGift().booleanValue() && !productModel.getCode()
											.equalsIgnoreCase(Config.getParameter("fcc.product.code")));
						}
					} else {
						productPincodeData
								.setAvailableForEnteredPincode(checkDeliveryPinCodeList(productModel, enteredPincode));
						productPincodeData.setAvailableForCoD(false);
					}
				}
				productPincodeDataList.add(productPincodeData);
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage());
		}
        return productPincodeDataList;
	}
	
}
