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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.iacrqq.pug.web.LoginContext;
import com.iacrqq.util.DateUtil;
import com.iacrqq.util.FileUtil;
import com.iacrqq.util.StringUtil;
import com.iacrqq.util.VersionUtil;

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

	protected static final String _MENU_INDEX_ = "_MENU_INDEX_";
	protected static final String _MEMBER_NAV_INDEX_ = "_MEMBER_NAV_INDEX_";
	protected static final String _EVENT_NAV_INDEX_ = "_EVENT_NAV_INDEX_";
	protected static final String _COMMUNITY_NAV_INDEX_ = "_COMMUNITY_NAV_INDEX_";
	protected static final int _INDEX_ = 0;
	protected static final int _ME_ = 1;
	protected static final int _COMMUNITY_ = 2;
	protected static final int _ACTIVITY_ = 3;
	protected static final int _IDEA_ = 4;

	public static final String DUMP_FORMAT_TYPE_XML = "xml";
	public static final String DUMP_FORMAT_TYPE_JSON = "json";
	public static final String DUMP_FORMAT_TYPE_RSS = "rss";

	protected static final String UPLOAD_FILE_HOLDER_KEY = "_upload_file_holder_";

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
			context.put("DateUtil", new DateUtil());
			context.put("StringUtil", new StringUtil());
			context.put("VersionUtil", new VersionUtil());
			context.put("FileUtil", new FileUtil());
			context.put("StringEscapeUtils", new StringEscapeUtils());
			context.put("isLogined", isLogined(request.getSession()));
			context.put("loginContext", request.getSession().getAttribute(LOGIN_CONTEXT));

			String queryString = request.getQueryString();
			queryString = StringUtil.isBlank(queryString) ? "" : "?" + queryString;
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
}
