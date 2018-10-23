package com.borngroup.ssl.core.util;

import de.hybris.platform.util.Config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

/**
 * <p>
 * SslUtilityForAddingDigitalSignature.java :Utility Class for adding digital Signature.
 * <p>
 * Created By : shilpa.verma@nagarro.com
 * <p>
 *
 * @author Ssl
 */
public final class SslUtilityForAddingDigitalSignature {
    /** CONSTANT LOGGER. */
    private static final Logger LOG = Logger.getLogger(SslUtilityForAddingDigitalSignature.class);

    /** CONSTANT KEYSTORE_PATH. */
    private static final String KEYSTORE_PATH = "signature.keystore.path";

    /** CONSTANT KEYSTORE_PASSWORD. */
    private static final String KEYSTORE_PASSWORD = "signature.keystore.password";

    /** CONSTANT FOOTER_TEXT1. */
    private static final String FOOTER_TEXT1 = "For Shoppers Stop Limited";

    /** CONSTANT FOOTER_TEXT2. */
    private static final String FOOTER_TEXT2 = "Authorised Signatory";

    /** CONSTANT KEYSTORE_FORMAT. */
    private static final String KEYSTORE_FORMAT = "pkcs12";

    /** CONSTANT PROVIDER. */
    private static final String PROVIDER = "BC";

    /** CONSTANT FORMAT. */
    private static final String FORMAT = "Cp1252";

    /** SINGLETON INSTANCE sslUtility. */
    private static SslUtilityForAddingDigitalSignature sslUtility = new SslUtilityForAddingDigitalSignature();

    /**
     * Private Constructor.
     */
    private SslUtilityForAddingDigitalSignature() {
        // Singleton class
    }

    /**
     * Singleton class get instance.
     *
     * @return SslUtilityForAddingDigitalSignature {@link SslUtilityForAddingDigitalSignature}
     */
    public static SslUtilityForAddingDigitalSignature getInstance() {
        return sslUtility;
    }

    /**
     * Adds Digital Signature to the pdf file with name passed in sourceFileName and generates file with name targetFileName.
     *
     * @param sourceFileName
     *        the sourceFileName
     * @param targetFileName
     *        the targetFileName
     */
    public void addDigitalSignature(final String sourceFileName, final String targetFileName) {
        final String pdfFileName = sourceFileName;
		final String keyStoreFilePath = Config.getString(KEYSTORE_PATH, StringUtils.EMPTY);
		try (FileInputStream file = new FileInputStream(keyStoreFilePath);) {
            final KeyStore keyStore = KeyStore.getInstance(KEYSTORE_FORMAT, PROVIDER);
            final String keyStoreFilePassword = Config.getString(KEYSTORE_PASSWORD, StringUtils.EMPTY);
			keyStore.load(file, keyStoreFilePassword.toCharArray());
            final String alias = keyStore.aliases().nextElement();
            final PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, keyStoreFilePassword.toCharArray());
            final Certificate[] chain = keyStore.getCertificateChain(alias);
            final PdfReader reader = new PdfReader(pdfFileName);
            final PdfStamper stamper = PdfStamper.createSignature(reader, new FileOutputStream(targetFileName), '\0');
            final PdfContentByte contentByte = stamper.getOverContent(reader.getNumberOfPages());
            ColumnText columnText = new ColumnText(contentByte);
            columnText.setSimpleColumn(5f, 20f, 500f, 90f);
            final BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, FORMAT, BaseFont.EMBEDDED);
            final Font font = new Font(baseFont, 10);
            Paragraph paragraph = new Paragraph(new Phrase(40, FOOTER_TEXT1, font));
            columnText.addElement(paragraph);
            columnText.go();
            columnText = new ColumnText(contentByte);
            columnText.setSimpleColumn(5f, 20f, 500f, 50f);
            paragraph = new Paragraph(FOOTER_TEXT2, font);
            columnText.addElement(paragraph);
            columnText.go();
            final PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
            appearance.setVisibleSignature(new Rectangle(400f, 5f, 500f, 65f), reader.getNumberOfPages(), null);
            appearance.setCrypto(privateKey, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
            appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
            reader.close();

            stamper.close();
        } catch (final DocumentException e) {
            LOG.error(String.format("Error adding digital signature %s", e.getMessage()));
        } catch (final IOException e) {
            LOG.error(String.format("Error reading keystore file %s", e.getMessage()));
        } catch (final Exception e) {
            LOG.error(String.format("Error adding digital signature %s", e.getMessage()));
        }
    }

}
