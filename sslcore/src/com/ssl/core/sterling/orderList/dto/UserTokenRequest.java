/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

/**
 * @author pankajgandhi
 *
 */
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class UserTokenRequest {

    @JsonProperty("LoginID")
    private String loginId;

    @JsonProperty("Password")
    private String password;

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     *        the loginId to set
     */
    public void setLoginId(final String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *        the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

}
