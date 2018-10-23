/**
 *
 */
package com.borngroup.ssl.core.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

/**
 * @author t.balagopalan
 *
 *         Removed from original location.
 */
public class SslUtilityForPDF {
    private static SslUtilityForPDF sslUtilityForPdf = new SslUtilityForPDF();

    public static final String CSS = "div {width: 500px; height: 500px;} tr { text-align: center; } th { background-color: lightgreen; padding: 3px; } td {background-color: lightblue;  padding: 3px; } strong {display: inline-block; width: 50px; border: 1px solid #000; text-align: center; }";
	static Logger LOG = Logger.getLogger(SslUtilityForPDF.class);

    private SslUtilityForPDF() {
        // Singleton class
    }

    /**
     * Singleton class get instance
     *
     * @return SslStorefrontUtilityForPDF
     */
    public static SslUtilityForPDF getInstance() {
        return sslUtilityForPdf;
    }

    /**
     * Generate iText Barcode128 for the passed in text and convert to Base64 string
     *
     * @param barcodeText
     *        - Barcode text to be converted as barcode
     * @return String - Base64 string.
     * @throws IOException
     */
    public String getiTextBarcode128AsBase64(final String barcodeText) throws IOException {
        final Barcode128 iTextBarcode = new Barcode128();
        iTextBarcode.setCode(barcodeText);

        final java.awt.Image awtImage = iTextBarcode.createAwtImage(Color.BLACK, Color.WHITE);

        final BufferedImage orderBI = new BufferedImage(awtImage.getWidth(null), awtImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
        orderBI.getGraphics().drawImage(awtImage, 0, 0, null);

        final ByteArrayOutputStream imageBaos = new ByteArrayOutputStream();
        ImageIO.write(orderBI, "png", imageBaos);
        imageBaos.flush();

        final String returnString = Base64.encodeBytes(imageBaos.toByteArray());
        imageBaos.close();

        return returnString;
    }

    /**
     * Gets the iText PDF Pipeline object to generate PDF
     *
     * @param document
     * @param pdfWriter
     * @return Pipeline<?>
     */
    public Pipeline getPipeline(final Document document, final PdfWriter pdfWriter, final float fontSize) {
        FontFactory.registerDirectories();
        final XmlFontProvider fontProvider = new XmlFontProvider("", fontSize);
        final CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        final HtmlPipelineContext htmlContext = new Base64HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new Base64ImageProvider());

        final CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        return new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, pdfWriter)));
    }

    public Pipeline getPipelineForReturnRequest(final Document document, final PdfWriter pdfWriter, final float fontSize) {
        FontFactory.registerDirectories();
        final XmlFontProvider fontProvider = new XmlFontProvider("", fontSize);
        final CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        final HtmlPipelineContext htmlContext = new Base64HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new Base64ImageProvider());

        final CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        final CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
        cssResolver.addCss(cssFile);
        return new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, pdfWriter)));
    }

    /**
     * Gets the Response file object in bytes
     *
     * @param pdfSourceFileName
     * @param pdfTargetFileName
     * @return ResponseEntity<byte[]>
     * @throws IOException
     */
    public ResponseEntity<byte[]> getPdfFileResponse(final String pdfSourceFileName, final String pdfTargetFileName) throws IOException {
        // FileInputStream object
        final File file = new File(pdfSourceFileName);
        final byte[] bFile = new byte[(int) file.length()];
        try(final FileInputStream fileInputStream = new FileInputStream(file);){
        fileInputStream.read(bFile);
        }catch(final IOException ie){
			LOG.error(ie.getMessage());
        }
        // delete the generated file
        file.delete();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(pdfTargetFileName, pdfTargetFileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<byte[]>(bFile, headers, HttpStatus.OK);
    }

    /**
     * Gets the unique file name.
     *
     * @param pdfFileName
     *        - PDF Filename
     * @return String
     */
    public String getRandomFileName(final String pdfFileName) {
        final UUID uuid = UUID.randomUUID();
        return String.format("%s_%s", uuid.toString(), pdfFileName);
    }

    /**
     * Gets the merged string from the VM template
     *
     * @param vmFileName
     * @param velocityContext
     * @return String
     */
    public String mergeVmTemplate(final String vmFileName, final VelocityContext velocityContext) {
        /* first, get and initialize an engine */
        final VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        /* next, get the Template */
        final Template t = ve.getTemplate(vmFileName);

        /* now render the template into a StringWriter */
        final StringWriter writer = new StringWriter();
        t.merge(velocityContext, writer);
        return writer.toString();
    }
}
