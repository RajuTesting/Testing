package com.borngroup.ssl.core.email.services;

import org.apache.commons.mail.HtmlEmail;

/**
 * <p>
 * HtmlEmailDTO.java : Htmlemail Dto.
 * <p>
 * Created By : anupam.srivastava@nagarro.com
 * <p>
 *
 * @author Ssl
 */
public class HtmlEmailDTO {

    /** Html email instance. **/
    private HtmlEmail htmlEmail;

    /** Html text. **/
    private String htmlText;

    /**
     * @return the htmlEmail
     */
    public HtmlEmail getHtmlEmail() {
        return htmlEmail;
    }

    /**
     * @param htmlEmail the htmlEmail to set
     */
    public void setHtmlEmail(final HtmlEmail htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

    /**
     * @return the htmlText
     */
    public String getHtmlText() {
        return htmlText;
    }

    /**
     * @param htmlText the htmlText to set
     */
    public void setHtmlText(final String htmlText) {
        this.htmlText = htmlText;
    }

}
