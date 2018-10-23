package com.borngroup.ssl.core.gst.dao;

import java.util.List;

import com.borngroup.ssl.core.model.GstTaxModel;
import com.borngroup.ssl.fulfilmentprocess.model.ShippingExcludedProdModel;
import com.borngroup.tax.model.ShippingChargesModel;

/**
 * <p>
 * <p>
 * GSTTaxCalculationDAO.java : DAO introduced to calculate GST tax.�
 * <p>
 * Created By : anupam.srivastava@nagarro.com. �
 *
 * @author�Ssl �
 */
public interface GSTTaxCalculationDAO {

    /**
     * @return List<SslTaxModel>
     */
    List<GstTaxModel> findTaxByTaxCategoryId(String skuCode, Double productPrice);

    /**
     *
     * @param excludedSku
     * @return listOfexcludedSku
     */
    List<ShippingExcludedProdModel> getListofExcludedSku(List<String> excludedSku);

    /**
     *
     * @param taxPercent
     * @return
     */
    ShippingChargesModel getShippingSKUOnPercent(double taxPercent);

}
