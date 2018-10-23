/**
 *  Modification History:
 *
 *  Version						Author					Task_ID					Description
 *  ================================================================================================
 *  0.1							Deepak Jondhlekar 	SSL-47					Base Version
 *	 0.2							Midhun Bose 			SSL-47					Refactoring Code
 *
 */
package com.borngroup.ssl.core.event;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.enums.WorkflowActionStatus;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.borngroup.ssl.core.constants.SslCoreConstants;
import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelSizeVariantProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;


/**
 *
 */
public class ProductCreateEventListener extends AbstractEventListener<AfterItemCreationEvent>
{
    private static final Logger       LOG                  = Logger.getLogger(ProductCreateEventListener.class);
    @Resource(name = "newestWorkflowService")
    private WorkflowService           workflowService;
    @Resource
    private WorkflowTemplateService   workflowTemplateService;
    @Resource
    private WorkflowProcessingService workflowProcessingService;
    @Autowired
    private UserService               userService;
    private ModelService              modelService;
    private WorkflowTemplateModel     workflowTemplateModel;
    private WorkflowModel             workflow;
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService     flexibleSearchService;

    /* 0.2 SSL-47 Start */
    private static final String       workflowTemplateCode = SslCoreConstants.CoreConstants.workflowTemplateCode;

    /* 0.2 SSL-47 End */

    @SuppressWarnings("deprecation")
    @Override
    protected void onEvent(final AfterItemCreationEvent event)
    {
        final String shouldListen = Config.getParameter("workflow.trigger.enabled") != null ? Config
                .getParameter("workflow.trigger.enabled") : "false";
        if ("true".equalsIgnoreCase(shouldListen) && event != null && event.getTypeCode() != null
                && !event.getTypeCode().isEmpty() && validEventCode(event.getTypeCode()))
        {
            final ItemModel attachedModel = getModelForPK(PK.parse(event.getSource().toString()));

            if (attachedModel instanceof ApparelProductModel)
            {
                workflowTemplateModel = workflowTemplateService.getWorkflowTemplateForCode(workflowTemplateCode);
                workflow = workflowService.createWorkflow(workflowTemplateModel, attachedModel,
                        userService.getUserForUID("productmanager"));
                modelService.save(workflow);
                for (final WorkflowActionModel action : workflow.getActions())
                {
                    action.setStatus(WorkflowActionStatus.PAUSED);
                    modelService.save(action);
                }
            }
            else if (attachedModel instanceof ApparelStyleVariantProductModel)
            {
                if (StringUtils.isEmpty(((ApparelStyleVariantProductModel) attachedModel).getSizeCode()))
                {
                    startWorkflow(attachedModel);
                }
            }
            else if (attachedModel instanceof ApparelSizeVariantProductModel)
            {
                startWorkflow(attachedModel);
            }
        }

    }

    /**
     * @param typeCode
     * @return
     */
    private boolean validEventCode(final String typeCode)
    {
        if (ApparelProductModel._TYPECODE.equals(typeCode) || ApparelStyleVariantProductModel._TYPECODE.equals(typeCode)
                || ApparelSizeVariantProductModel._TYPECODE.equals(typeCode))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @param attachedModel
     */
    private void startWorkflow(final ItemModel attachedModel)
    {
        final ProductModel productModel = getBaseProduct((ProductModel) attachedModel);
        final List<WorkflowActionModel> workflowActionModels = getWorkflows();
        for (final WorkflowActionModel workflowActionModel : workflowActionModels)
        {
            if ((workflowActionModel.getAttachmentItems()).contains(productModel))
            {
                workflowProcessingService.startWorkflow(workflowActionModel.getWorkflow());
                LOG.debug("Product Enrichment Workflow Started.");
            }
        }
    }

    /**
     * @return
     * 
     */
    private List<WorkflowActionModel> getWorkflows()
    {
        final Map params = new HashMap();
        params.put("status", WorkflowActionStatus.PAUSED);
        params.put("user", userService.getUserForUID("productimagingexpertuser"));

        /*final String query = "SELECT tbl.action FROM ("
                + "{{SELECT {actions:PK} action FROM {WORKFLOWACTION as actions} WHERE {actions:status}=?status AND {actions:principalAssigned}=?user }}"
                + " UNION ALL "
                + "{{SELECT {actions:PK} action FROM {WORKFLOWACTION as actions}, {PrincipalGroupRelation as rel} WHERE {actions:status}=?status AND "
                + "{actions:principalAssigned}={rel:target} AND {rel:source} = ?user}}" + ") tbl";*/

        final String query = "select {wa.pk} from { WORKFLOWACTION as wa join WorkflowActionStatus as wstat on {wa.status} = {wstat.pk} join principal as prin on {wa.principalAssigned} = {prin.pk} } where {prin.uid} = 'productimagingexpertgroup' and {wstat.code}='paused' ";
        final SearchResult<WorkflowActionModel> searchResult = (SearchResult<WorkflowActionModel>) flexibleSearchService.search(
                query).getResult();
        final List<WorkflowActionModel> actions = searchResult.getResult();
        return actions;
    }



    private ItemModel getModelForPK(final PK pk)
    {
        return modelService.get(pk);
    }

    protected ProductModel getBaseProduct(final ProductModel product)
    {
        if (product instanceof VariantProductModel)
        {
            return getBaseProduct(((VariantProductModel) product).getBaseProduct());
        }
        return product;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
