/**
 *
 */
package com.borngroup.ssl.core.services;

import java.io.File;
import java.io.IOException;

/**
 * Service class interface to provide methods to send wallet credits bulk upload process status report.
 *
 * @author shilpaverma
 *
 */
public interface SslWalletCreditsBulkUploadReportService {

    /**
     * @param file
     *        the file
     * @param uploadStatus
     *        the uploadStatus
     * @throws IOException
     *         the IOException
     */
    void sendErrorReport(File file, String uploadStatus) throws IOException;
}
