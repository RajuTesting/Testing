/**
 * 
 */
package com.borngroup.ssl.core.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * @author Deepak
 * 
 */
public class DataHubConsumptionFailedEvent extends AbstractEvent
{
	String feedName;
	String rawType;
	String rawData[];


	/**
	 * @param feedName
	 * @param rawType
	 * @param rawData
	 */
	public DataHubConsumptionFailedEvent(final String feedName, final String rawType, final String rawData[])
	{
		super();
		this.feedName = feedName;
		this.rawType = rawType;
		this.rawData = rawData;
	}

	/**
	 * @return the feedName
	 */
	public String getFeedName()
	{
		return feedName;
	}

	/**
	 * @param feedName
	 *           the feedName to set
	 */
	public void setFeedName(final String feedName)
	{
		this.feedName = feedName;
	}

	/**
	 * @return the rawType
	 */
	public String getRawType()
	{
		return rawType;
	}

	/**
	 * @param rawType
	 *           the rawType to set
	 */
	public void setRawType(final String rawType)
	{
		this.rawType = rawType;
	}

	/**
	 * @return the rawData
	 */
	public String[] getRawData()
	{
		return rawData;
	}

	/**
	 * @param rawData
	 *           the rawData to set
	 */
	public void setRawData(final String rawData[])
	{
		this.rawData = rawData;
	}
}
