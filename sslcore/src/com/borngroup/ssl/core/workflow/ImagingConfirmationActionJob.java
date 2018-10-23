/**
 * 
 */
package com.borngroup.ssl.core.workflow;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.fest.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;


/**
 * @author Deepak
 * 
 *         This class will be used to do any validation required on the task performed Further this class will
 *         explicitly mark the next action to in-progress status
 */
public class ImagingConfirmationActionJob extends DefaultConfirmationActionJob
{
	private static final Logger LOG = Logger.getLogger(ImagingConfirmationActionJob.class);

	@Autowired
	private ModelService modelService;

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		LOG.debug("Starting ImagingConfirmationActionJob.......");

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
		final String comment = "Mandatory field marked * must be provided";
		boolean inValid = false;
		if (itemModel instanceof ApparelProductModel)
		{
			final ProductModel productModel = (ProductModel) itemModel;
			if (Collections.isEmpty(productModel.getGalleryImages()) && productModel.getThumbnail() == null
					&& Collections.isEmpty(productModel.getThumbnails()))
			{
				inValid = true;
			}
			if (!(Collections.isEmpty(productModel.getVariants())))
			{
				for (final VariantProductModel variantProductModel : productModel.getVariants())
				{
					inValid = validateVariants(variantProductModel);
					if (inValid)
					{
						break;
					}
				}
			}
		}
		if (inValid)
		{
			return comment;
		}

		return "";
	}

	/**
	 * @param variantProductModel
	 * @param comment
	 */
	private boolean validateVariants(final VariantProductModel variantProductModel)
	{
		if (Collections.isEmpty(variantProductModel.getGalleryImages()) && variantProductModel.getThumbnail() == null
				&& Collections.isEmpty(variantProductModel.getThumbnails()))
		{
			return true;
		}
		return false;
	}

}