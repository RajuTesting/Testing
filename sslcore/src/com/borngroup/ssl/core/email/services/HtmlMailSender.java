package com.borngroup.ssl.core.email.services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.velocity.VelocityContext;

/**
 * <p>
 * HtmlMailSender.java : HtmlMailSender class which helps to create HTML e-mails with pre-configured to/from fields.<br>
 * Contains methods for building up a text/HTML message and sending that mail message.
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 * <p>
 *
 * @author Ssl
 */
public interface HtmlMailSender {

    /**
     * Main method to create html email
     *
     * @param velocityContext : Velocity context object
     * @param templateCode : Template code
     * @param subject : Main subject
     * @param to : TO email ids
     * @param from : From email id
     * @return Html Email object
     * @throws EmailException : Email exception
     */

    HtmlEmailDTO createHtmlEmail(VelocityContext velocityContext, String templateCode, String subject, String to, String from)
            throws EmailException;

    /**
     * Other method to prepare html email if replyto and cc, bcc is required
     *
     * @param velocityContext : Velocity context object
     * @param templateCode : Template code
     * @param subject : Email subject
     * @param to : EmailId's for recipients in TO
     * @param replyTo : EmailId's for recipients in Reply TO
     * @param cc : EmailId's for recipients in CC
     * @param bcc :EmailId's for recipients in Bcc
     * @param from : From email id
     * @return :HtmlEmail instance
     * @throws EmailException : Email exception
     */
    HtmlEmailDTO createHtmlEmail(VelocityContext velocityContext, String templateCode, String subject, String to, String replyTo,
            String cc, String bcc, String from) throws EmailException;

    /**
     * Main methos to send email
     *
     * @param email Html email object
     * @throws EmailException : Email exception
     */
    void sendEmail(HtmlEmail email) throws EmailException;
}
