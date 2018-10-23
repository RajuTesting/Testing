/**
 *
 */
package com.borngroup.ssl.core.util;

import org.apache.log4j.Logger;

import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


/**
 * @author t.balagopalan
 * 
 * Duplicated from sslcommercewebservice
 */
public class Base64HtmlPipelineContext extends HtmlPipelineContext
{

    private static final Logger LOG = Logger.getLogger(Base64HtmlPipelineContext.class);

    public Base64HtmlPipelineContext()
    {
        super(null);
    }

    public Base64HtmlPipelineContext(final CssAppliers cssAppliers)
    {
        super(cssAppliers);
    }

    @Override
    public HtmlPipelineContext clone()
    {
        HtmlPipelineContext ctx = null;
        try
        {
            ctx = super.clone();
            ctx.setImageProvider(new Base64ImageProvider());
        }
        catch (final Exception e)
        {
            LOG.error(e.getMessage());
        }
        return ctx;
    }
}
