/**
 *
 */
package com.borngroup.ssl.core.util;



/**
 * @author Venkatesh.K
 *
 */
public interface SSLLoyaltyOAuthConstants {

	//public static final int SERVER_PORT = Config.getParameter("ssl.loyalty.server.port"9001);
	//public static final int SSL_PORT = Config.getParameter("ssl.loyalty.ssl.port")9002;
	//static final String DOMAIN_NAME = Config.getParameter("ssl.loyalty.domain.name")"192.168.1.86";
	public static final String OAUTH_BASE_URL = "ssl.loyalty.oauth.token.url".intern();
	public static final String ACCESS_TOKEN_URL =  "ssl.loyalty.accesstoken.url".intern();
	//public static final String CUSTOMERS_URL = Config.getParameter("ssl.loyalty.customers.url");
	public static final String SECRET_KEY_PARAM = "ssl.loyalty.secretkey.param".intern();
	public static final String CLIENT_ID_PARAM = "ssl.loyalty.clientid.param".intern();
	public static final String GRANT_TYPE_PARAM = "ssl.loyalty.granttype.param".intern();
	//public static final String STATE_PARAM = Config.getParameter("ssl.loyalty.state.param");

	public static final String CLIENT_ID = "ssl.loyalty.clientid".intern();
	// Use your own client id
	public static final String CLIENT_SECRET = "ssl.loyalty.client.secret".intern();
	public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "ssl.loyalty.granttype.clientcredentials".intern();
	public static final String GRANT_TYPE_PASSWORD ="ssl.loyalty.granttype.password".intern();
	public static final String SSL_LOYALTY_API_TLS_ENABLED = "ssl.loyalty.api.tls.enabled".intern();
	public static final String SSL_LOYALTY_API_TLS_TRUSTEVERYONE_ENABLED = "ssl.loyalty.api.tls.trusteveryone.enabled".intern();
	public static final String API_AUTHENTICATION_ENABLED = "ssl.loyalty.api.authentication.enabled".intern();   
	public static final String ACCESS_TOKEN_FORCEFETCH_ENABLED = "ssl.loyalty.accesstoken.forcefetch.enabled".intern();   
	
	public static final String REFRESH_TOKEN = "refresh_token";
	
	public static final String SSL_ESBAPI_CONNECTION_TIMEOUT="ssl.esbapi.connection.timeout".intern();
    public static final String SSL_ACCESSTOKEN_CONNECTION_TIMEOUT="ssl.accesstoken.connection.timeout".intern();
    public static final String SSL_ACCESSTOKEN_RETRY_ENABLED = "ssl.accesstoken.retry.enabled".intern();

}
