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
public class FacebookTokenResponse
{
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "bio")
    private String bio;
    @JsonProperty(value = "first_name")
    private String first_name;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonProperty(value = "last_name")
    private String last_name;

    @JsonProperty(value = "link")
    private String link;

    @JsonProperty(value = "locale")
    private String locale;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "timezone")
    private String timezone;

    @JsonProperty(value = "updated_time")
    private String updated_time;

    @JsonProperty(value = "verified")
    private String verified;

    @JsonProperty(value = "email")
    private String email;

    public FacebookTokenResponse()
    {

    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @return the bio
     */
    public String getBio()
    {
        return bio;
    }

    /**
     * @param bio
     *            the bio to set
     */
    public void setBio(final String bio)
    {
        this.bio = bio;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name()
    {
        return first_name;
    }

    /**
     * @param first_name
     *            the first_name to set
     */
    public void setFirst_name(final String first_name)
    {
        this.first_name = first_name;
    }

    /**
     * @return the gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(final String gender)
    {
        this.gender = gender;
    }

    /**
     * @return the last_name
     */
    public String getLast_name()
    {
        return last_name;
    }

    /**
     * @param last_name
     *            the last_name to set
     */
    public void setLast_name(final String last_name)
    {
        this.last_name = last_name;
    }

    /**
     * @return the link
     */
    public String getLink()
    {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(final String link)
    {
        this.link = link;
    }

    /**
     * @return the locale
     */
    public String getLocale()
    {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(final String locale)
    {
        this.locale = locale;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @return the timezone
     */
    public String getTimezone()
    {
        return timezone;
    }

    /**
     * @param timezone
     *            the timezone to set
     */
    public void setTimezone(final String timezone)
    {
        this.timezone = timezone;
    }

    /**
     * @return the updated_time
     */
    public String getUpdated_time()
    {
        return updated_time;
    }

    /**
     * @param updated_time
     *            the updated_time to set
     */
    public void setUpdated_time(final String updated_time)
    {
        this.updated_time = updated_time;
    }

    /**
     * @return the verified
     */
    public String getVerified()
    {
        return verified;
    }

    /**
     * @param verified
     *            the verified to set
     */
    public void setVerified(final String verified)
    {
        this.verified = verified;
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
}
