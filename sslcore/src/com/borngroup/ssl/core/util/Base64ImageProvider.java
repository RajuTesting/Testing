/**
 *
 */
package com.borngroup.ssl.core.util;

import org.apache.log4j.Logger;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;


/**
 * @author t.balagopalan
 *
 *         Duplicated from sslcommercewebservice
 */
public class Base64ImageProvider extends AbstractImageProvider
{

    private static final Logger LOG = Logger.getLogger(Base64ImageProvider.class);

    @Override
    public Image retrieve(final String src)
    {
        Image img = super.retrieve(src);
        if (img == null)
        {
            try
            {
                // Just separate the encoded base64 string to decode
                // src.split(",")[1]
                final byte[] data = Base64.decode(src.split(",")[1]);
                img = Image.getInstance(data);
                super.store(src, img);
            }
            catch (final Exception ex)
            {
                LOG.error(String.format("Error generating base64 barcode image %s", ex.getMessage()));
            }
        }
        return img;
    }

    @Override
    public String getImageRootPath()
    {
        return "http://sampleurl/img";
    }
}
