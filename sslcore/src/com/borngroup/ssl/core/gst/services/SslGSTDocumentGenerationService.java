/**
 * 
 */
package com.borngroup.ssl.core.gst.services;

import java.io.StringReader;

import org.springframework.http.ResponseEntity;

import com.borngroup.tax.enums.GSTDocumentType;

/**
 * @author manikmalhotra
 *
 */
public interface SslGSTDocumentGenerationService {

    ResponseEntity<byte[]> generateGSTDocument(String invoiceNumber, String documentPrefix, GSTDocumentType documentType);
    
    StringReader getBOSPDFData(String invoiceNumber, GSTDocumentType documentType,boolean isDuplicateCopy,boolean isFCCInvoice);
}
