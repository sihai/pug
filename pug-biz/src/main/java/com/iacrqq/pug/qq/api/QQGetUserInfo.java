/**
 * pug
 */
package com.iacrqq.pug.qq.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.iacrqq.pug.qq.api.domain.QQUserInfo;
import com.iacrqq.util.StringUtil;

/**
 * 
 * @author sihai
 *
 */
public class QQGetUserInfo {
	
	private static final String API_NAME = "/v3/user/get_info";
	
	public static QQUserInfo getQQUserInfo(String openid, String openkey, String pf) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("openid", openid);
		parameterMap.put("openkey", openkey);
		parameterMap.put("pf", pf);
		String response = QQApiInvoker.invoke(QQApiInvoker.PROTOCOL_HTTP, API_NAME, parameterMap);
		if(StringUtil.isBlank(response)) {
			throw new RuntimeException("Can not get user info from qq");
		} else {
			return (QQUserInfo)JSONObject.toBean(JSONObject.fromObject(response), QQUserInfo.class);
		}
	}
}
