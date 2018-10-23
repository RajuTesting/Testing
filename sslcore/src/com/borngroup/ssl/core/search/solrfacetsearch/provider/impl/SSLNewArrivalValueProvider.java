/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.util.Config;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.borngroup.ssl.core.model.ApparelProductModel;
import com.borngroup.ssl.core.model.ApparelStyleVariantProductModel;

/**
 * SSLLatestApprovedValueProvider: This class indexes the ApprovalDate field of ApparelProductModel. If the value is null/beyond specific days Limit then a default
 * value as 0 is indexed so that all the null field values have the same indexed value.
 *
 * 
 * @author Techouts
 */
public class SSLNewArrivalValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    /** The field name provider. */
    private FieldNameProvider fieldNameProvider;

    /** The Constant LOG. */
    protected static final Logger LOG = Logger.getLogger(SSLNewArrivalValueProvider.class);

    private static final long NUMBER_OF_DAYS=Config.getInt("solr.sort.newarrival.noofdays",100);
    private static final String SEASON_CODE = "solr.sort.newarrival.seasoncode";
    private static final String BOOST_SEASON_CODE_VALUE = "solr.boost.newarrival.seasoncode";
    private static final double DEFAULT_BOOST_VALUE_FOR_SEASON_CODE = 1.3;

    @SuppressWarnings("deprecation")
	@Override
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
		final Collection<FieldValue> fieldValues = new ArrayList<>();
        ApparelProductModel apparelModel = null;
        if (model instanceof ApparelStyleVariantProductModel) {
            model = ((ApparelStyleVariantProductModel) model).getBaseProduct();
        }

        if (model instanceof ApparelProductModel) {
            apparelModel = (ApparelProductModel) model;
            if (apparelModel.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED)) {
                Date approvalDate = null;
                double approvedDays=0;
                if (apparelModel.getApprovalDate() != null) {
                    final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        approvalDate = dateFormatter.parse(dateFormatter.format(apparelModel.getApprovalDate()));
                        final long calValue =NUMBER_OF_DAYS-TimeUnit.DAYS.convert(new Date().getTime()-approvalDate.getTime(), TimeUnit.MILLISECONDS);
                        approvedDays =calValue<0?0:calValue;
                        approvedDays = this.getPopularityForMatchedSeasonCode(apparelModel, approvedDays);
                        LOG.debug("Popularity With Season Code: " + approvedDays);

                    } catch (final ParseException e) {
                        LOG.debug("Error occurred during parsing Approval Date. So, indexing default value is 0." + e);
                    }
                } 
                fieldValues.addAll(createFieldValue(approvedDays, indexedProperty));
            }
        }
        return fieldValues;
    }

    /**
	 * Creates the field value.
	 *
	 * @param approvedDays the approvedDays
	 * @param indexedProperty the indexed property
	 * @return fieldValues
	 */
	private Collection<? extends FieldValue> createFieldValue(final Double approvedDays, final IndexedProperty indexedProperty) {
		final List<FieldValue> fieldValues = new ArrayList<>();
        final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
        for (final String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, approvedDays));
        }
        return fieldValues;
    }
	
	 /**
     * Gets the approvalDays rank for products which have matching season code with listed season codes in
     * {@link PopularityRankValueProvider#SEASON_CODE}.
     *
     * @param apparelModel       {@link ApparelProductModel}.
     * @param originalApprovalDays original approval Days.
     * @return new approvalDays rank.
     */
    private double getPopularityForMatchedSeasonCode(ApparelProductModel apparelModel, double originalApprovalDays) {
        double approvalDays = originalApprovalDays;
        final String seasonCode = apparelModel.getSeasonCode();
        if (StringUtils.isNotEmpty(Config.getParameter(SEASON_CODE)) && StringUtils.isNotEmpty(seasonCode)) {
            boolean flag = Stream.of(Config.getParameter(SEASON_CODE).split(",")).anyMatch(seasonCode::contains);
            approvalDays = flag ? approvalDays * Config.getDouble(BOOST_SEASON_CODE_VALUE, DEFAULT_BOOST_VALUE_FOR_SEASON_CODE) : approvalDays;
        }
        return approvalDays;
    }

    /**
     * Gets the field name provider.
     *
     * @return the fieldNameProvider
     */
    public FieldNameProvider getFieldNameProvider() {
        return fieldNameProvider;
    }

    /**
     * Sets the field name provider.
     *
     * @param fieldNameProvider
     *        the fieldNameProvider to set
     */
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}