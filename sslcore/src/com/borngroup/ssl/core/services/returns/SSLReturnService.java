/**
 *
 */
package com.borngroup.ssl.core.services.returns;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.returns.ReturnService;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.List;

import com.borngroup.ssl.core.data.returns.ReturnableEntity;

/**
 * @author atul2455
 *
 */
public interface SSLReturnService extends ReturnService {

    public ReturnRequestModel generateReturnRequestPerODC(final List<ReturnableEntity> returnableEntities, final OrderModel orderModel);

    public CsTicketModel createCSTicket(final ReturnRequestModel returnRequest, final AbstractOrderModel orderModel);

    public void setBankDetails(ReturnRequestModel request, String accountName, String accountNumber, String bankName, String ifscCode);

    public void createModificationEntry(final ReturnRequestModel returnRequest, final CsTicketModel csTicket, final UserModel csAgent);

    public void createConsignments(final ReturnRequestModel returnRequestModel);

    public void sendNotifications(final List<ReturnRequestModel> returnRequestList);

    public void updateCsTicket(ReturnRequestModel returnRequest, double codAmount);

    public Integer getReturnDays(ProductModel product);

    /**
     * Return true if bank details update mail has been sent to customer successfully
     * 
     * @param returnRequest {@link ReturnRequestModel}
     * @param isPriceDifferenceBankDetails
     * @return boolean
     */
    boolean sendBankDetailsUpdateLinkEmail(ReturnRequestModel returnRequest, boolean isPriceDifferenceBankDetails);
}
