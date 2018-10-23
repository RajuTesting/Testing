/**
 *
 */
package com.borngroup.ssl.core.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author tiruchendurai.s
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleTokenResponse
{

    @JsonProperty(value = "audience")
    private String audience;

    @JsonProperty(value = "user_id")
    private String user_id;

    @JsonProperty(value = "scope")
    private String scope;

    @JsonProperty(value = "expires_in")
    private String expires_in;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "issued_to")
    private String issued_to;

    @JsonProperty(value = "verified_email", required = false)
    private String verified_email;

    @JsonProperty(value = "access_type")
    private String access_type;

    @JsonProperty(value = "sub")
    private String sub;

    @JsonProperty(value = "name")
    private String fullName;

    @JsonProperty(value = "given_name")
    private String firstName;

    @JsonProperty(value = "family_name")
    private String lastName;


    /**
     * @return the audience
     */
    public String getAudience()
    {
        return audience;
    }


    /**
     * @param audience
     *            the audience to set
     */
    public void setAudience(final String audience)
    {
        this.audience = audience;
    }


    /**
     * @return the user_id
     */
    public String getUser_id()
    {
        return user_id;
    }


    /**
     * @param user_id
     *            the user_id to set
     */
    public void setUser_id(final String user_id)
    {
        this.user_id = user_id;
    }


    /**
     * @return the scope
     */
    public String getScope()
    {
        return scope;
    }


    /**
     * @param scope
     *            the scope to set
     */
    public void setScope(final String scope)
    {
        this.scope = scope;
    }


    /**
     * @return the expires_in
     */
    public String getExpires_in()
    {
        return expires_in;
    }


    /**
     * @param expires_in
     *            the expires_in to set
     */
    public void setExpires_in(final String expires_in)
    {
        this.expires_in = expires_in;
    }


    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }


    /**
     *
     */
    public GoogleTokenResponse()
    {
        // YTODO Auto-generated constructor stub
    }


    /**
     * @return the issued_to
     */
    public String getIssued_to()
    {
        return issued_to;
    }


    /**
     * @param issued_to
     *            the issued_to to set
     */
    public void setIssued_to(final String issued_to)
    {
        this.issued_to = issued_to;
    }


    /**
     * @return the access_type
     */
    public String getAccess_type()
    {
        return access_type;
    }


    /**
     * @param access_type
     *            the access_type to set
     */
    public void setAccess_type(final String access_type)
    {
        this.access_type = access_type;
    }


    /**
     * @return the verified_email
     */
    public String getVerified_email()
    {
        return verified_email;
    }


    /**
     * @param verified_email
     *            the verified_email to set
     */
    public void setVerified_email(final String verified_email)
    {
        this.verified_email = verified_email;
    }


	/**
	 * @return the sub
	 */
	public String getSub() {
		return sub;
	}


	/**
	 * @param sub the sub to set
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}


	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
