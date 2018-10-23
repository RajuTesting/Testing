package com.borngroup.ssl.core.email.services.impl;

import de.hybris.platform.commons.model.renderer.RendererTemplateModel;
import de.hybris.platform.commons.renderer.RendererService;
import de.hybris.platform.util.mail.MailUtils;

import java.io.StringWriter;
import java.util.Collections;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import com.borngroup.ssl.core.email.services.HtmlEmailDTO;
import com.borngroup.ssl.core.email.services.HtmlMailSender;

/**
 * <p>
 * HtmlMailSenderImpl.java : HtmlMailSenderImpl class which helps to create HTML e-mails with pre-configured to/from fields.<br>
 * Contains methods for building up a text/HTML message and sending that mail message.
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 * <p>
 *
 * @author Ssl
 */

public class HtmlMailSenderImpl implements HtmlMailSender {

    /** Logger instance */
    private final Logger LOG = Logger.getLogger(HtmlMailSenderImpl.class);

    /** Renderer service. */

    @Resource
    private RendererService rendererService;

    @Override
    public HtmlEmailDTO createHtmlEmail(final VelocityContext velocityContext, final String templateCode, final String subject,
            final String to, final String from) throws EmailException {
        try {
            final HtmlEmailDTO emailDto = new HtmlEmailDTO();
            final HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
            final RendererTemplateModel bodyTemplate = rendererService.getRendererTemplateForCode(templateCode);
            final StringWriter renderedText = new StringWriter();
            rendererService.render(bodyTemplate, velocityContext, renderedText);
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(renderedText.toString());
            htmlEmail.setFrom(from);
            final InternetAddress fromAdd = new InternetAddress(from);
            htmlEmail.setReplyTo(Collections.singletonList(fromAdd));
            final String[] addresses = to.split(",");
            for (final String email : addresses) {
                final String emailId = email.trim();
                htmlEmail.addTo(emailId);
            }
            emailDto.setHtmlEmail(htmlEmail);
            emailDto.setHtmlText(renderedText.toString());
            return emailDto;
        } catch (final AddressException e) {
            LOG.error("Error in creating mail message", e);
            throw new EmailException("Error in creating  mail message", e);
        }
    }

    @Override
    public HtmlEmailDTO createHtmlEmail(final VelocityContext velocityContext, final String templateCode, final String subject,
            final String to, final String replyTo, final String cc, final String bcc, final String from) throws EmailException {
        try {
            final HtmlEmailDTO htmlEmail = createHtmlEmail(velocityContext, templateCode, subject, to, from);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Hmtl dto prepared successfully" + htmlEmail.toString());
            }
            if (StringUtils.isNotEmpty(replyTo)) {
                final InternetAddress replyToAdd = new InternetAddress(replyTo);
                htmlEmail.getHtmlEmail().setReplyTo(Collections.singletonList(replyToAdd));
            }
            if (StringUtils.isNotEmpty(cc)) {
                final String[] addresses = cc.split(",");
                for (final String address : addresses) {
                    final String emailId = address.trim();
                    htmlEmail.getHtmlEmail().addCc(emailId);
                }
            }
            if (StringUtils.isNotEmpty(bcc)) {
                final String[] addresses = bcc.split(",");
                for (final String address : addresses) {
                    final String emailId = address.trim();
                    htmlEmail.getHtmlEmail().addBcc(emailId);
                }
            }
            htmlEmail.getHtmlEmail().setFrom(from);
            return htmlEmail;
        } catch (final AddressException e) {
            LOG.error("Error in creating mail message", e);
            throw new EmailException("Error in creating  mail message", e);
        }
    }

    @Override
    public void sendEmail(final HtmlEmail email) throws EmailException {
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Sending log event email %s", email));
        }
        email.send();
    }

}
