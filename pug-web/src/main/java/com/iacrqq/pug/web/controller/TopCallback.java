/**
 * pug
 */
package com.iacrqq.pug.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.manager.UserManager;
import com.iacrqq.pug.web.LoginContext;
import com.iacrqq.util.StringUtil;

/**
 * 
 * TOP 入口
 * 
 * @author sihai
 *
 */
public class TopCallback extends AbstractController {

	private static final Log logger = LogFactory.getLog(TopCallback.class);
	
	String TOP_PARAMETERS = "top_parameters";
    String TOP_APPKEY = "top_appkey";
    String TOP_SESSION = "top_session";
    String MODULE_WIDTH = "module_width";
    String SHOP_ID = "shop_id";
    String SELLER_NICK = "seller_nick";
    String SELLER_ID = "seller_id";
    String TOP_SIGN = "top_sign";
    String VISITOR_NICK = "visitor_nick";
    String VISITOR_ID = "visitor_id";
    
	@Autowired
	private UserManager userManager;
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		
		// TOP parameters
        String topAppKey = request.getParameter(TOP_APPKEY);
        String topSessionKey = request.getParameter(TOP_SESSION);
        String topParameters = request.getParameter(TOP_PARAMETERS);

        // 参加校验
        if (StringUtil.isBlank(topAppKey) || StringUtil.isBlank(topSessionKey)
                        || StringUtil.isBlank(topParameters)) {
        	mv = new ModelAndView("error");
			Map<String, Object> context = mv.getModel();
			context.put("errorMsg", "請從TOP登陸！！！");
			return mv;
        }

        Map<String, String> parametersMap = convertBase64StringtoMap(topParameters);
        Long vistorId = Long.valueOf(parametersMap.get(VISITOR_ID));
        String vistorNick = parametersMap.get(VISITOR_NICK);

		mv = new ModelAndView("redirect:/myfoot.jhtml");
		Map<String, Object> context = mv.getModel();
		UserDO user = userManager.syncUserFromTaobao(vistorNick);
		context.put("user", user);
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", "0");
		session.setAttribute("userName", "sihai");
		session.setAttribute("userLogo", "http://www.google.com");
		session.setAttribute(LOGIN_CONTEXT, LoginContext.fromUser(user));
		Cookie cookie = new Cookie("test", "value");
		cookie.setPath("/");
		cookie.setMaxAge(1800);
		response.addCookie(cookie);
		return mv;
	}
	
	public static Map<String, String> convertBase64StringtoMap(String str) {
        BASE64Decoder decoder = new BASE64Decoder();

        if (str == null)

                return null;

        String keyvalues = null;

        try {

                keyvalues = new String(decoder.decodeBuffer(str), "gbk");

        } catch (UnsupportedEncodingException e) {

                logger.error("str=" + str, e);

        } catch (IOException e) {
                logger.error("str=" + str, e);
        }

        String[] keyvalueArray = keyvalues.split("\\&");

        Map<String, String> map = new HashMap<String, String>();

        for (String keyvalue : keyvalueArray) {

                String[] s = keyvalue.split("\\=");

                if (s == null || s.length != 2)

                        return null;

                map.put(s[0], s[1]);

        }

        return map;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
