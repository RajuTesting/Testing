package com.borngroup.ssl.core.context;
/**
 * 
 */

import java.util.List;

import org.apache.velocity.VelocityContext;

import com.borngroup.ssl.facades.data.SwatchColorErrorData;

/**
 * @author TO-OLAP-10
 *
 */
public class SwatchColorErrorEmailContext  extends VelocityContext{
	private List<SwatchColorErrorData> swatchColorErrorData;
	private int rowCount;
	private int maxRowCount;
	/**
	 * @param data
	 * @param rowCount
	 * @param maxRowCount
	 */
	public SwatchColorErrorEmailContext(List<SwatchColorErrorData> data, int rowCount,
			int maxRowCount) {
		this.swatchColorErrorData = data;
		this.rowCount = rowCount;
		this.maxRowCount = maxRowCount;
	}
	/**
	 * @return the swatchColorErrorData
	 */
	public List<SwatchColorErrorData> getSwatchColorErrorData() {
		return swatchColorErrorData;
	}
	/**
	 * @param swatchColorErrorData the swatchColorErrorData to set
	 */
	public void setSwatchColorErrorData(List<SwatchColorErrorData> swatchColorErrorData) {
		this.swatchColorErrorData = swatchColorErrorData;
	}
	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}
	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * @return the maxRowCount
	 */
	public int getMaxRowCount() {
		return maxRowCount;
	}
	/**
	 * @param maxRowCount the maxRowCount to set
	 */
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}
}