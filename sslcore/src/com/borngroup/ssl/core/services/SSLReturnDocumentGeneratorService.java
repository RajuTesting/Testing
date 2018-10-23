/**
 *
 */
package com.borngroup.ssl.core.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;

import com.borngroup.ssl.core.data.SSLReturnEntryDTO;
import com.borngroup.ssl.core.data.SSLWarehouseAddressDTO;
import com.borngroup.ssl.fulfilmentprocess.returns.data.SearchRMARequest;

/**
 * @author bhavya jain
 *
 */
public interface SSLReturnDocumentGeneratorService {
    /**
     * Takes the Invoice Number, Return Number and List of Return Entries as Input and prints the Return Form
     *
     * @return PDF
     */
    ResponseEntity<byte[]> printReturnRequestForm(String returnNumber, String orderNumber, String orderShippingDate,
            List<SSLReturnEntryDTO> list, SSLWarehouseAddressDTO warehouse);

    /**
     * Creates and Returns the Excel Workbook for the Return Requests
     *
     * @param searchRMARequest
     * @param string
     *
     * @return Workbook
     */
    public XSSFWorkbook getExcelForReturn(SearchRMARequest searchRMARequest, String string);

    /**
     * Validates Header in CSV File
     *
     * @param headers
     *        List of Headers present in CSV
     * @return Returns Error
     */
    String validateHeaders(String[] headers);

    /**
     * Processes CSV Row
     *
     * @param csvRowData
     *        Row of CSV
     * @return Count with Status
     * @throws IOException
     */
    Map<String, Integer> processCSVData(List<String> csvRowData, FileWriter fileWriter) throws IOException;

    /**
     * @param scanner
     * @return
     * @throws IOException
     */
    Map<String, Integer> uploadCSV(Scanner scanner) throws IOException;
    
    /**
     * Returns a workbook with open RMAs i.e. RMAs with associated bank details.
     * 
     * @param stockLocationCode
     * @return XSSFWorkbook, sheet with open RMAs which have associated bank details with them.
     */
    public XSSFWorkbook getSheetForOpenRMAsWithBankDetails(final String stockLocationCode);

    public Path getLogFilePath();
}
