/**
 * pug
 */
package com.iacrqq.pug.web.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.web.LoginContext;

/**
 * Controller抽象基类，请继承本类
 * 
 * @author sihai
 * 
 */
public abstract class AbstractController implements Controller {
	
	protected static final String PARAMETER_REDIRECT_TO = "rt";
	protected static final String CURRENT_URL = "curl";

	protected static final String OPERATION_KEY = "_operation_";
	protected static final String LOGIN_CONTEXT = "_login_context_";
	protected static final String ACTION_METHOD = "_method_";

	protected static final String NONE_VALUE = "-1";

	protected static final String SUCCEED = "succeed";
	protected static final String ERROR_MSG = "errorMsg";

	public static final String DUMP_FORMAT_TYPE_XML = "xml";
	public static final String DUMP_FORMAT_TYPE_JSON = "json";
	public static final String DUMP_FORMAT_TYPE_RSS = "rss";

	protected static final String UPLOAD_FILE_HOLDER_KEY = "_upload_file_holder_";
	
	protected static final String PARAMETER_PUG_KEY = "_pug_key_";
	protected static final String PUG_KEY = "";

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * // 前置处理 Device device =
		 * (Device)request.getAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE);
		 * // 为了在PC测试扄1�7以取叄1�7 if(!device.isMobile()) { String requestURI =
		 * request.getRequestURI(); if(!requestURI.startsWith("/m")) { return
		 * new ModelAndView(new
		 * StringBuilder("forward:").append("/m").append(requestURI
		 * ).toString()); } }
		 */
		//
		ModelAndView mv = handle(request, response);

		if (mv != null && !mv.getViewName().startsWith("redirect:")) {
			Map<String, Object> context = mv.getModel();
			context.put("StringEscapeUtils", new StringEscapeUtils());
			context.put("isLogined", isLogined(request.getSession()));
			context.put("loginContext", request.getSession().getAttribute(LOGIN_CONTEXT));

			String queryString = request.getQueryString();
			queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
			context.put(CURRENT_URL, new StringBuilder(URLEncoder.encode(request.getRequestURL()
							.toString(), "utf-8")).append(URLEncoder.encode(
							queryString, "utf-8").toString()));
		}
		// 后置处理
		return mv;
	}

	//
	protected abstract ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	protected boolean isLogined(HttpSession session) {
		LoginContext loginContext = (LoginContext) session.getAttribute(LOGIN_CONTEXT);
		if (loginContext == null) {
			return false;
		}

		return loginContext.getIsLogin();
	}

	protected Long getUserId(HttpSession session) {
		LoginContext loginContext = (LoginContext) session.getAttribute(LOGIN_CONTEXT);
		return loginContext.getVistorId();
	}

	protected String getUserNick(HttpSession session) {
		LoginContext loginContext = (LoginContext) session.getAttribute(LOGIN_CONTEXT);
		return loginContext.getVistorNick();
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ValidateException
	 */
	protected void validatePugKey(HttpServletRequest request,
			HttpServletResponse response) throws ValidateException {
		String pugKey = request.getParameter(PARAMETER_PUG_KEY);
		if(StringUtils.isBlank(pugKey)) {
			throw new ValidateException(String.format("Miss parameter %s", PARAMETER_PUG_KEY));
		}
		// FIXME use PasswordUtil
		if(!StringUtils.equals(pugKey, PUG_KEY)) {
			throw new ValidateException(String.format("Wrong value of parameter %s", PARAMETER_PUG_KEY));
		}
	}
}
