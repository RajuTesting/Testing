/** * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Ankit			      NA						Base version : Override HeaderInitTask to create temp dummy file while Hot Folder is running.
 *  																					File will be deleted after import in Cleanup Task. Except code indicated through comments, remaining are from OOB
 *
 *

 **/

package com.borngroup.ssl.core.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.HeaderInitTask;
import de.hybris.platform.acceleratorservices.dataimport.batch.util.SequenceIdParser;
import de.hybris.platform.acceleratorservices.util.RegexParser;
import de.hybris.platform.util.Config;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.borngroup.ssl.core.constants.SslCoreConstants;




public class SslHeaderInitTask extends HeaderInitTask
{
	private SequenceIdParser sequenceIdParser;
	private RegexParser languageParser;
	private String fallbackLanguage;
	private static String DATA_DIR = Config.getParameter("HYBRIS_DATA_DIR");
	private static final Logger LOG = Logger.getLogger(SslHeaderInitTask.class);

	@Override
	public BatchHeader execute(final BatchHeader header)
	{
		header.setSequenceId(sequenceIdParser.getSequenceId(header.getFile()));
		final String language = languageParser.parse(header.getFile().getName(), 1);
		header.setLanguage(language == null ? fallbackLanguage : language);

		//Call function to create dummy file
		createDummyFile();

		return header;
	}


	//Creates a dummy file to indicate that hot folder is running
	private void createDummyFile()
	{

		final File dummyFile = new File(DATA_DIR + SslCoreConstants.HOT_FOLDER_DUMMY_FILE);
		try
		{
			dummyFile.createNewFile();
		}
		catch (final Exception ex)
		{
			LOG.error("Error while creating dummy file");
		}
	}

	/**
	 * @param sequenceIdParser
	 *           the sequenceIdParser to set
	 */
	@Override
	@Required
	public void setSequenceIdParser(final SequenceIdParser sequenceIdParser)
	{
		this.sequenceIdParser = sequenceIdParser;
	}

	/**
	 * @param fallbackLanguage
	 *           the fallbackLanguage to set
	 */
	@Override
	@Required
	public void setFallbackLanguage(final String fallbackLanguage)
	{
		Assert.hasText(fallbackLanguage);
		this.fallbackLanguage = fallbackLanguage;
	}

	/**
	 * @param languageParser
	 *           the languageParser to set
	 */
	@Override
	@Required
	public void setLanguageParser(final RegexParser languageParser)
	{
		this.languageParser = languageParser;
	}

	/**
	 * @return the sequenceIdParser
	 */
	@Override
	protected SequenceIdParser getSequenceIdParser()
	{
		return sequenceIdParser;
	}

	/**
	 * @return the languageParser
	 */
	@Override
	protected RegexParser getLanguageParser()
	{
		return languageParser;
	}

	/**
	 * @return the fallbackLanguage
	 */
	@Override
	protected String getFallbackLanguage()
	{
		return fallbackLanguage;
	}

}
