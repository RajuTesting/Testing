/**
 *
 */
package com.borngroup.ssl.core.interceptors;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.borngroup.ssl.core.model.ApparelProductModel;

/**
 * SSLApparelProductPrepareInterceptorTest: This Test class tests whether Approval Date is set when Approval status is set to APPROVED.
 *
 * Created by harleen.chadha@nagarro.com
 *
 * @author SSL
 */
@UnitTest
public class SSLApparelProductPrepareInterceptorTest {

    /** The mock interceptor context. */
    @Mock
    private InterceptorContext mockInterceptorContext;

    /** The ssl apparel product prepare interceptor. */
    private SSLApparelProductPrepareInterceptor sslApparelProductPrepareInterceptor;

    /** The product. */
    private ApparelProductModel product;

    /** The Constant VERSION. */
    private static final String VERSION = "Staged";

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sslApparelProductPrepareInterceptor = new SSLApparelProductPrepareInterceptor();
        product = new ApparelProductModel();
        final CatalogVersionModel catalogVersion = new CatalogVersionModel();
        catalogVersion.setVersion(VERSION);
        product.setCatalogVersion(catalogVersion);
    }

    /**
     * Test product prepare interceptor approval date set.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testProductPrepareInterceptorApprovalDateSet() throws Exception {
        product.setApprovalStatus(ArticleApprovalStatus.APPROVED);
        this.recordInterceptorContextModifyReturns(true);
        sslApparelProductPrepareInterceptor.onPrepare(product, mockInterceptorContext);
        Assert.assertNotNull("Approved for the first time", product.getApprovalDate());
    }

    /**
     * Test product prepare interceptor approval date change.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testProductPrepareInterceptorApprovalDateChange() throws Exception {
        product.setApprovalStatus(ArticleApprovalStatus.APPROVED);
        this.recordInterceptorContextModifyReturns(true);
        final Date productPreviousDate = product.getApprovalDate();
        sslApparelProductPrepareInterceptor.onPrepare(product, mockInterceptorContext);
        Assert.assertNotSame("Approval date has been changed.", product.getApprovalDate(), productPreviousDate);
    }

    /**
     * Test product prepare interceptor approval date not change.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testProductPrepareInterceptorApprovalDateNotChange() throws Exception {
        product.setApprovalStatus(ArticleApprovalStatus.UNAPPROVED);
        this.recordInterceptorContextModifyReturns(true);
        final Date productPreviousDate = product.getApprovalDate();
        sslApparelProductPrepareInterceptor.onPrepare(product, mockInterceptorContext);
        Assert.assertEquals("Approval date hasn't been changed.", product.getApprovalDate(), productPreviousDate);
    }

    /**
     * Record interceptor context modify returns.
     *
     * @param returnValue
     *        the return value
     */
    private void recordInterceptorContextModifyReturns(final boolean returnValue) {
        when(Boolean.valueOf(mockInterceptorContext.isModified(product, ApparelProductModel.APPROVALSTATUS))).thenReturn(
                Boolean.valueOf(returnValue));
    }
}