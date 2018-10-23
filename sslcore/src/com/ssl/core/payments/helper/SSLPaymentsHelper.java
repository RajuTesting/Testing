/**
 *
 */
package com.ssl.core.payments.helper;

import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.core.model.order.payment.PaytmPaymentInfoModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.borngroup.ssl.core.constants.SslCoreConstants;

/**
 * @author manikmalhotra
 *
 */
public class SSLPaymentsHelper {

    private static final String MOBILE = "mobile";

    private SSLPaymentsHelper() {
        // Do Nothing
    }

    public static boolean isPaytmPaymentAuthorized(final PaymentTransactionModel paymentTransactionModel,
            final PaymentTransactionEntryModel paymentTransactionEntryModel) {
        if (paymentTransactionModel.getInfo() instanceof PaytmPaymentInfoModel) {
            return (paymentTransactionEntryModel.getTransactionStatus().equals(TransactionStatus.ACCEPTED.name()) || paymentTransactionEntryModel
                    .getTransactionStatus().equals(SslCoreConstants.SUCCESS));
        }
        return false;
    }

    public static boolean isPaymentAmountAcceptable(final BigDecimal amountToBeDeducted, final BigDecimal amountDeducted) {
        final int paymentDifference = Math.abs(amountDeducted.subtract(amountToBeDeducted).intValue());
        int allowedVarianceLevel = 1;
        try {
            allowedVarianceLevel = Config.getInt("externalpayment.payment.varianceallowed", 1);
        } catch (final NumberFormatException e) {
            // Ignore Exception
        }
        return paymentDifference <= allowedVarianceLevel;
    }

    public static UiExperienceLevel getUIExperienceLevel(final HttpServletRequest request) {
        final String sourceType = request.getHeader("User-Agent");

        if (StringUtils.isNotEmpty(sourceType) && sourceType.toLowerCase().indexOf(MOBILE) >= 0) {
            return UiExperienceLevel.MOBILE;
        } else {
            return UiExperienceLevel.DESKTOP;
        }
    }
}
