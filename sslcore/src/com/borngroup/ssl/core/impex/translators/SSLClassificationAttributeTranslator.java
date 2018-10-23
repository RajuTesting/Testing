package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.catalog.jalo.classification.ClassificationAttribute;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeModel;
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
public class SSLClassificationAttributeTranslator extends AbstractValueTranslator
{

	private static final Logger LOG = Logger.getLogger(SSLClassificationAttributeTranslator.class);

	@Autowired
	ModelService modelService;
	@Autowired
	ClassificationSystemService classificationSystemService;


	@Override
	public Object importValue(final String value, final Item item)
	{
		ClassificationAttribute ret = null;
		if (StringUtils.isNotEmpty(value))
		{
			ClassificationSystemVersionModel activeCatalogVersion = null;
			ClassificationAttributeModel classificationAttribute = null;
			classificationSystemService = (ClassificationSystemService) Registry.getApplicationContext().getBean(
					"classificationSystemService");
			modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");

			try
			{
				activeCatalogVersion = classificationSystemService.getSystemVersion("sslClassification", "1.0");
				classificationAttribute = classificationSystemService.getAttributeForCode(activeCatalogVersion, value);
				ret = (ClassificationAttribute) modelService.getSource(classificationAttribute);
			}
			catch (final UnknownIdentifierException unknownId)
			{
				if (LOG.isDebugEnabled())
				{

					LOG.debug("classification attribute does not exist and the exception is :" + unknownId);
					LOG.debug("classification attribute does not exist and hence creating the same with id:" + value);
				}

				classificationAttribute = new ClassificationAttributeModel();
				classificationAttribute.setCode(value);
				classificationAttribute.setName(value, Locale.ENGLISH);
				classificationAttribute.setSystemVersion(activeCatalogVersion);
				modelService.save(classificationAttribute);
				ret = (ClassificationAttribute) modelService.getSource(classificationAttribute);
			}
			catch (final Exception exc)
			{
				LOG.error("exception thrown " + exc);
			}
		}

		return ret;
	}


	@Override
	public String exportValue(final Object value) throws JaloInvalidParameterException
	{
		return value == null ? "" : value.toString();
	}
}
