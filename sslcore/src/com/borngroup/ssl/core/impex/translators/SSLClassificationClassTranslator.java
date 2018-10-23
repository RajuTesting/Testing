package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.catalog.jalo.classification.ClassificationClass;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.classification.ClassificationSystemService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author I318976
 *
 */
public class SSLClassificationClassTranslator extends AbstractValueTranslator
{

	private static final Logger LOG = Logger.getLogger(SSLClassificationClassTranslator.class);

	@Autowired
	ModelService modelService;
	@Autowired
	ClassificationSystemService classificationSystemService;






	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.impex.jalo.translators.AbstractValueTranslator#exportValue(java.lang.Object)
	 */
	@Override
	public String exportValue(final Object value) throws JaloInvalidParameterException
	{
		return value == null ? "" : value.toString();
	}




	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.impex.jalo.translators.AbstractValueTranslator#importValue(java.lang.String,
	 * de.hybris.platform.jalo.Item)
	 */
	@Override
	public Object importValue(final String value, final Item item) throws JaloInvalidParameterException
	{
		ClassificationClass ret = null;
		if (StringUtils.isNotEmpty(value))
		{
			ClassificationSystemVersionModel activeCatalogVersion = null;
			ClassificationClassModel classificationClass = null;
			classificationSystemService = (ClassificationSystemService) Registry.getApplicationContext().getBean(
					"classificationSystemService");
			modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");

			try
			{
				activeCatalogVersion = classificationSystemService.getSystemVersion("sslClassification", "1.0");
				classificationClass = classificationSystemService.getClassForCode(activeCatalogVersion, value);
				ret = (ClassificationClass) modelService.getSource(classificationClass);

			}
			catch (final UnknownIdentifierException unknownId)
			{
				if (LOG.isDebugEnabled())
				{

					LOG.debug("classification class does not exist and the exception is :" + unknownId);
					LOG.debug("classification class does not exist and hence creating the same with id:" + value);
				}

				classificationClass = new ClassificationClassModel();
				classificationClass.setCode(value);
				classificationClass.setName(value, Locale.ENGLISH);
				classificationClass.setCatalogVersion(activeCatalogVersion);
				modelService.save(classificationClass);
				ret = (ClassificationClass) modelService.getSource(classificationClass);

			}
			catch (final Exception exc)
			{
				LOG.error("exception thrown " + exc);
			}
		}

		return ret;
	}

}
