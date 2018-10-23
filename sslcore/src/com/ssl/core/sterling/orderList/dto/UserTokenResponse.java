/**
 *
 */
package com.ssl.core.sterling.orderList.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pankajgandhi
 *
 */
public class UserTokenResponse {

    @JsonProperty("UserToken")
    private String userToken;

    /**
     * @return the userToken
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * @param userToken
     *        the userToken to set
     */
    public void setUserToken(final String userToken) {
        this.userToken = userToken;
    }

}
