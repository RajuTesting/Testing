package com.borngroup.ssl.core.gst.dto;

import java.util.List;

import org.apache.solr.common.util.Pair;

import com.borngroup.ssl.core.enums.GstTaxTypes;
import com.borngroup.ssl.core.model.GstTaxModel;

/**
 * <p>
 * <p>
 * GSTTaxCalculationService.java : Service introduced to calculate GST tax.
 * <p>
 * Created By : anupam.srivastava@nagarro.com. �
 *
 * @author�Ssl �
 */
public class TaxComponentDTO {

    private List<Pair<GstTaxTypes, GstTaxModel>> taxComponent;

    /**
     * @return the taxComponent
     */
    public List<Pair<GstTaxTypes, GstTaxModel>> getTaxComponent() {
        return taxComponent;
    }

    /**
     * @param taxComponent
     *        the taxComponent to set
     */
    public void setTaxComponent(final List<Pair<GstTaxTypes, GstTaxModel>> taxComponent) {
        this.taxComponent = taxComponent;
    }

}
