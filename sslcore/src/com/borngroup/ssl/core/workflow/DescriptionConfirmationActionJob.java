/**
 * 
 */
package com.borngroup.ssl.core.workflow;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.fest.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.constants.SslCoreConstants;


/**
 * @author Deepak
 * 
 */
public class DescriptionConfirmationActionJob extends DefaultConfirmationActionJob
{
	private static final Logger LOG = Logger.getLogger(DescriptionConfirmationActionJob.class);

	@Autowired
	private ModelService modelService;

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		LOG.debug("Starting DescriptionConfirmationActionJob.......");

		String result = SslCoreConstants.WorkflowConstants.DECISION_SUCCESS;
		String comment = "";

		final List<ItemModel> attachedProducts = action.getAttachmentItems();

		for (final ItemModel itemModel : attachedProducts)
		{
			comment += validateModel(itemModel);
			if (!StringUtils.isEmpty(comment))
			{
				result = SslCoreConstants.WorkflowConstants.DECISION_FAILURE;
			}
		}

		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			if (decision.getCode().contains(result))
			{
				super.markSuccessorToInprogress(decision, comment);
				return decision;
			}
		}
		return null;
	}

	public String validateModel(final ItemModel itemModel)
	{
		String comment = "";
		if (itemModel instanceof ProductModel)
		{
			final ProductModel productModel = (ProductModel) itemModel;
			if (Collections.isEmpty(productModel.getGalleryImages()))
			{
				comment = "Mandetory field GallaryImages is not provided.";
			}
		}
		return comment;
	}

}