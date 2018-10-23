package com.borngroup.ssl.core.impex.translators;

import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.catalog.jalo.classification.ClassificationAttributeValue;
import de.hybris.platform.catalog.model.classification.ClassificationAttributeValueModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.classification.ClassificationSystemService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author I318976
 *
 */
public class SSLClassificationAttributeValueTranslator extends AbstractValueTranslator {

	private static final Logger LOG = Logger.getLogger(SSLClassificationAttributeValueTranslator.class);
	private static final String COMMA_DEL = ",";

	@Autowired
	ModelService modelService;
	@Autowired
	ClassificationSystemService classificationSystemService;

	@Override
	public String exportValue(final Object value) throws JaloInvalidParameterException {
		return value == null ? "" : value.toString();
	}

	@Override
	public Object importValue(final String value, final Item item) throws JaloInvalidParameterException {
		List<ClassificationAttributeValue> filteredList = null;
		if (StringUtils.isNotEmpty(value)) {
			filteredList = new ArrayList<ClassificationAttributeValue>();
			ClassificationSystemVersionModel activeCatalogVersion = null;

			final String[] values = value.split(COMMA_DEL);
			ClassificationAttributeValueModel valueModel = null;
			classificationSystemService = (ClassificationSystemService) Registry.getApplicationContext()
					.getBean("classificationSystemService");
			modelService = (ModelService) Registry.getApplicationContext().getBean("modelService");

			if (null != item && item instanceof ClassAttributeAssignment) {
				final ClassAttributeAssignment classAttributeAssignment = (ClassAttributeAssignment) item;
				final List<ClassificationAttributeValue> attrValues = classAttributeAssignment.getAttributeValues();
				if (CollectionUtils.isNotEmpty(attrValues)) {
					filteredList.addAll(attrValues);
				}
			}
			for (final String code : values) {
				try {
					if (null == activeCatalogVersion) {
						activeCatalogVersion = classificationSystemService.getSystemVersion("sslClassification", "1.0");
					}
					valueModel = classificationSystemService.getAttributeValueForCode(activeCatalogVersion, code);
					filteredList.add((ClassificationAttributeValue) modelService.getSource(valueModel));
				} catch (final UnknownIdentifierException unknownId) {
					if (LOG.isDebugEnabled()) {

						LOG.debug("classification attribute value does not exist and the exception is :" + unknownId);
						LOG.debug("classification attribute value does not exist and hence creating the same with id:"
								+ code);
					}

					valueModel = new ClassificationAttributeValueModel();
					valueModel.setCode(code);
					valueModel.setName(code, Locale.ENGLISH);
					valueModel.setSystemVersion(activeCatalogVersion);
					modelService.save(valueModel);
					filteredList.add((ClassificationAttributeValue) modelService.getSource(valueModel));
				} catch (final Exception exc) {
					LOG.error("exception thrown " + exc);
				}

			}

		}

		return filteredList;

	}

}
