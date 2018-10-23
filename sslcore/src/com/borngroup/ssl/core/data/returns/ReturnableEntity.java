/**
 *
 */
package com.borngroup.ssl.core.data.returns;

import de.hybris.platform.returns.model.RefundEntryModel;

/**
 * @author atul2455
 *
 */
public class ReturnableEntity {

	private RefundEntryModel refundEntry;

	/**
	 * @return the refundEntry
	 */
	public RefundEntryModel getRefundEntry() {
		return refundEntry;
	}

	/**
	 * @param refundEntry
	 *            the refundEntry to set
	 */
	public void setRefundEntry(final RefundEntryModel refundEntry) {
		this.refundEntry = refundEntry;
	}

}
