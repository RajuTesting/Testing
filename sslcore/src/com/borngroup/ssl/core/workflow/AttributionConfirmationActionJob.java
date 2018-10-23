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
import org.springframework.beans.factory.annotation.Autowired;

import com.borngroup.ssl.core.constants.SslCoreConstants;


/**
 * @author Deepak
 *
 */
public class AttributionConfirmationActionJob extends DefaultConfirmationActionJob
{
    private static final Logger LOG = Logger.getLogger(AttributionConfirmationActionJob.class);

    @Autowired
    private ModelService        modelService;

    @Override
    public WorkflowDecisionModel perform(final WorkflowActionModel action)
    {
        LOG.debug("Starting AttributionConfirmationActionJob.......");

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
        boolean validFlag = true;
        final StringBuilder comment = new StringBuilder("Mandatory ");
        if (itemModel instanceof ProductModel)
        {
            final ProductModel productModel = (ProductModel) itemModel;

            if (StringUtils.isEmpty(productModel.getName()))
            {
                comment.append("Identifier ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getDescription()))
            {
                comment.append("Description ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getShortName()))
            {
                comment.append("Short_Name ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getBrandInfo()))
            {
                comment.append("BrandInfo ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getCareInstructions()))
            {
                comment.append("CareInstructions ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getShipType()))
            {
                comment.append("ShipType ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getShippingInfo()))
            {
                comment.append("ShippingInfo ");
                validFlag = false;
            }

            if (StringUtils.isEmpty(productModel.getGiftAgeGroup()))
            {
                comment.append("GiftAgeGroup ");
                validFlag = false;
            }
            if (StringUtils.isEmpty(productModel.getGiftTo()))
            {
                comment.append("GiftTo ");
                validFlag = false;
            }

            comment.append("is not provided.");
        }
        if (validFlag)
        {
            return "";
        }

        return comment.toString();
    }

}
