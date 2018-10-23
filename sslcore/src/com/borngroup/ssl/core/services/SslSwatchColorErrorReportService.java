/**
 * 
 */
package com.borngroup.ssl.core.services;

import com.borngroup.ssl.facades.data.SwatchColorErrorData;

/**
 * @author TO-OLAP-10
 *
 */
public interface SslSwatchColorErrorReportService {
	/**
	 * this method is used to generate swatch color errors report or send the  generated report in configured mails
	 * @param data
	 * @param isJobTriggered
	 * @throws Exception
	 */
	void generateSendErorsXlsReport(SwatchColorErrorData data, boolean isJobTriggered) throws Exception;
}