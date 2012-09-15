/**
 * pug
 */
package com.iacrqq.pug.qq.api;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author sihai
 *
 */
public class QQApiInvoker {

	private static final Log logger = LogFactory.getLog(QQApiInvoker.class);
	
	public static final String PROTOCOL_HTTP = "http";
	public static final String PROTOCOL_HTTPS = "https";
	
	private static final String APP_ID = "100652559";
	private static final String APP_KEY = "9c29dbbe74b93efae249273aad651054";
	private static final String GATEWAY = "113.108.20.23";
	
	private static final OpenApiV3 sdk = new OpenApiV3(APP_ID, APP_KEY);
	
	static {
		sdk.setServerName(GATEWAY);
	}
	
	/**
	 * 
	 * @param protocol
	 * @param apiName
	 * @param parameterMap
	 * @return
	 */
	public static String invoke(String protocol, String apiName, Map<String, String> parameterMap) {
		try{
            String respose = sdk.api(apiName, parameterMap, protocol);
            return respose;
        }
        catch (OpensnsException e) {
            logger.error(String.format("Request Failed. code:%d, msg:%s\n", e.getErrorCode(), e.getMessage()));
            return null;
        }
	}
}
