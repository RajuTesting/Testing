/**
 *
 */
package com.borngroup.ssl.core.search.solrfacetsearch.provider.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.PropertyFieldValueProviderTestBase;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * SSLLatestApprovedValueProviderTest: This test class tests the implementation of class SSLLatestApprovedValueProvider.
 *
 * Created by harleen.chadha@nagarro.com
 *
 * @author SSL
 */
public class SSLLatestApprovedValueProviderTest extends PropertyFieldValueProviderTestBase {

    private static final String SOLR_PROPERTY = "latestApproved";

    private static final String FIELD_NAME_INDEXING = SOLR_PROPERTY + "_date";

    @Mock
    private IndexedProperty indexedProperty;

    private SSLLatestApprovedValueProvider valueProvider;

    @Before
    public void setUp() throws Exception {
        valueProvider = new SSLLatestApprovedValueProvider();
        configure();
    }

    @Override
    protected String getPropertyName() {
        return SOLR_PROPERTY;
    }

    @Override
    protected void configure() {
        setPropertyFieldValueProvider(valueProvider);
        configureBase();
        ((SSLLatestApprovedValueProvider) getPropertyFieldValueProvider()).setFieldNameProvider(fieldNameProvider);

        final Collection<String> fieldNames = new ArrayList<>();
        fieldNames.add(FIELD_NAME_INDEXING);
        given(fieldNameProvider.getFieldNames(indexedProperty, null)).willReturn(fieldNames);
    }

    @Test
    public void testLatestApprovedDefaultValue() throws FieldValueProviderException {
        final ApparelProductModel baseProduct = new ApparelProductModel();
        baseProduct.setApprovalStatus(ArticleApprovalStatus.APPROVED);
        final Date defaultDate = new Date("01/01/2017");

        final Collection<FieldValue> fieldValues = valueProvider.getFieldValues(indexConfig, indexedProperty, baseProduct);
        for (final FieldValue fieldValue : fieldValues) {
            if (FIELD_NAME_INDEXING.equals(fieldValue.getFieldName())) {
                final Date actualDate = (Date) fieldValue.getValue();
                Assert.assertEquals("Default dates is as per expectation.", defaultDate.getDate(), actualDate.getDate());
            }
        }
    }

    @Test
    public void testLatestApprovedDate() throws FieldValueProviderException {
        final ApparelProductModel baseProduct = new ApparelProductModel();
        baseProduct.setApprovalStatus(ArticleApprovalStatus.APPROVED);
        baseProduct.setApprovalDate(new Date("16/01/2017"));

        final Collection<FieldValue> fieldValues = valueProvider.getFieldValues(indexConfig, indexedProperty, baseProduct);
        for (final FieldValue fieldValue : fieldValues) {
            if (FIELD_NAME_INDEXING.equals(fieldValue.getFieldName())) {
                final Date actualDate = (Date) fieldValue.getValue();
                Assert.assertEquals("Approval Date set has been indexed correctly in Solr.", baseProduct.getApprovalDate().getDate(),
                        actualDate.getDate());
            }
        }
    }
}