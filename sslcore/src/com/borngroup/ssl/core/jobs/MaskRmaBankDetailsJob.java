/**
 *
 */
package com.borngroup.ssl.core.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ReturnRequestBankDetailsModel;
import com.borngroup.ssl.core.services.ReturnRequestBankDetailsService;

/**
 * @author tejsharma
 *
 */
public class MaskRmaBankDetailsJob extends AbstractJobPerformable<CronJobModel> {
    /**
     * Class Logger.
     */
    private static final Logger LOG = Logger.getLogger(MaskRmaBankDetailsJob.class);
    /**
     * Character used to mask a digit in account number.
     */
    private static final char MASK_CHAR = '*';

    @Resource(name = "returnRequestBankDetailsService")
    private ReturnRequestBankDetailsService returnRequestBankDetailsService;

    /**
     * @return the returnRequestBankDetailsService
     */
    public ReturnRequestBankDetailsService getReturnRequestBankDetailsService() {
        return returnRequestBankDetailsService;
    }

    /**
     * @param returnRequestBankDetailsService the returnRequestBankDetailsService to set
     */
    public void setReturnRequestBankDetailsService(final ReturnRequestBankDetailsService returnRequestBankDetailsService) {
        this.returnRequestBankDetailsService = returnRequestBankDetailsService;
    }

    @Override
    public PerformResult perform(final CronJobModel arg0) {
        PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        final String ptkubdStr = SslCoreConstants.PERIOD_TO_KEEP_UNMASKED_BANK_DETAILS;
        final int ptkubd = Integer.parseInt(ptkubdStr);

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * ptkubd);
        final Date beforeDate = cal.getTime();

        final List<ReturnRequestBankDetailsModel> bankDetailsToMask = returnRequestBankDetailsService
                .getReturnRequestBankDetailsToMask(beforeDate);
        if (bankDetailsToMask == null) {
            result = new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
            LOG.error("Null was returned when searching for bank details to mask!!");
        } else {
            updateBankDetails(bankDetailsToMask);
        }
        return result;
    }

    /**
     * Method to update account numbers to masked format.
     *
     * @param bankDetailsToMask : List of ReturnRequestBankDetailModel instances for which account number is to be masked.
     */
    private void updateBankDetails(final List<ReturnRequestBankDetailsModel> bankDetailsToMask) {
        final int digToDisplayStart = Integer.parseInt(SslCoreConstants.DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_START);
        final int digToDisplayEnd = Integer.parseInt(SslCoreConstants.DIGITS_TO_DISPLAY_IN_MASKED_AC_NUMBER_END);
        for (final ReturnRequestBankDetailsModel bankDetail : bankDetailsToMask) {
            bankDetail.setAccountNumber(maskAccountNumber(bankDetail.getAccountNumber(), digToDisplayStart, digToDisplayEnd));
            try {
                modelService.save(bankDetail);
            } catch (final ModelSavingException mse) {
                LOG.debug("Unable to save masked account number.");
            }
        }
    }

    /**
     * Method to get masked account number.
     *
     * @param acNumber : Account number to be masked.
     * @param digToDisplayStart : Number of starting digits to display.
     * @param digToDisplayEnd : Number of ending digits to display.
     * @return String, Masked Account Number
     */
    private String maskAccountNumber(final String acNumber, final int digToDisplayStart, final int digToDisplayEnd) {
        final int acNumLen = acNumber.length();
        String maskedAcNumStr = null;
        if (digToDisplayEnd + digToDisplayStart >= acNumLen) {
            maskedAcNumStr = acNumber;
        } else {
            final StringBuilder maskedAcNumber = new StringBuilder(acNumber.substring(0, digToDisplayStart));
            for (int i = 0; i < acNumLen - digToDisplayStart - digToDisplayEnd; i++) {
                maskedAcNumber.append(MASK_CHAR);
            }
            maskedAcNumber.append(acNumber.substring(acNumLen - digToDisplayEnd));
            maskedAcNumStr = maskedAcNumber.toString();
        }
        return maskedAcNumStr;
    }

}
