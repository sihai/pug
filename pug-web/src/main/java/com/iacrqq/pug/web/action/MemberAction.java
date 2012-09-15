package com.iacrqq.pug.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.manager.UserManager;
import com.iacrqq.pug.model.JSONResultModel;
import com.iacrqq.pug.web.LoginContext;
import com.iacrqq.pug.web.controller.AbstractController;
import com.iacrqq.util.StringUtil;

public class MemberAction extends AbstractController {

	private static final String LOGIN = "_login_";
	private static final String LOGOUT = "_log_out_";
	private static final String REGISTER = "_register_";
	
	private static final Log logger = LogFactory.getLog(MemberAction.class);
	
	@Autowired
	private UserManager userManager;
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = null;
		String operation = request.getParameter(OPERATION_KEY);
		
		if(StringUtil.equals(operation, REGISTER)) {
			mv = doRegister(request, response);
		} else if(StringUtil.equals(operation, LOGIN)) {
			mv = doLogin(request, response);
		} else if(StringUtil.equals(operation, LOGOUT)) {
			mv = doLogout(request, response);
		} else {
			throw new RuntimeException("Unknown operation");
		}
		
		return mv;
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView doRegister(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		UserDO user = new UserDO();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setSex(0L);
		user.setFromWhere(UserDO.USER_FROM_PUG);
		user.setStatus(0L);
		user.setType(0L);
		user.setGrade(0L);
		user.setIsDeleted(false);
		
		try {
			userManager.register(user);
			session.setAttribute(LOGIN_CONTEXT, LoginContext.fromUser(user));
			return new ModelAndView("redirect:/myfoot.jhtml");
		} catch(ValidateException e) {
			ModelAndView mv = new ModelAndView("login");
			Map<String, Object> context = mv.getModel();
			context.put("user", user);
			mv.getModel().put(ERROR_MSG, e.getMessage());
			return mv;
		}
	}
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		try {
			UserDO user = userManager.login(name, request.getParameter("password"));
			HttpSession session = request.getSession(true);
			session.setAttribute(LOGIN_CONTEXT, LoginContext.fromUser(user));
			String target = "redirect:/myfoot.jhtml";
			String rt = request.getParameter(PARAMETER_REDIRECT_TO);
			if(StringUtil.isNotBlank(rt)) {
				try {
					target = "redirect:" + URLDecoder.decode(rt, "utf-8");
				} catch (UnsupportedEncodingException e) {
					logger.error(e);
				}
			}
			return new ModelAndView(target);
		} catch(ValidateException e) {
			ModelAndView mv = new ModelAndView("login");
			Map<String, Object> context = mv.getModel();
			context.put(ERROR_MSG, e.getMessage());
			context.put("name", name);
			
			return mv;
		}
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(LOGIN_CONTEXT);
		return new ModelAndView("redirect:/login.jhtml");
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
