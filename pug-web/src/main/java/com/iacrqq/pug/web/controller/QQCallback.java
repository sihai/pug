/**
 * pug
 */
package com.iacrqq.pug.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.manager.UserManager;
import com.iacrqq.pug.qq.api.QQGetUserInfo;
import com.iacrqq.pug.qq.api.domain.QQUserInfo;
import com.iacrqq.pug.web.LoginContext;
import com.iacrqq.util.StringUtil;

/**
 * 
 * /qqcallback.jhtml?openid=67DC85931D633950165BBFF092696B0F&openkey=416564E0E908BC342E7D7A4A7780FD22&pf=qzone&pfkey=bd58ae0a669edbe180f2259b15d60b73
 * 
 * @author sihai
 *
 */
public class QQCallback extends AbstractController {

	@Autowired
	private UserManager userManager;
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		
		String openId = request.getParameter("openid");
		String openkey = request.getParameter("openkey");
		String pf = request.getParameter("pf");
		String pfkey = request.getParameter("pfkey");
		
		if(StringUtil.isBlank(openId) || StringUtil.isBlank(openkey) || StringUtil.isBlank(pf) || StringUtil.isBlank(pfkey)) {
			mv = new ModelAndView("error");
			Map<String, Object> context = mv.getModel();
			context.put("errorMsg", "請從QQ登陸！！！");
		} else {
			mv = new ModelAndView("redirct:/myfoot.jhtml");
			Map<String, Object> context = mv.getModel();
			QQUserInfo qqUserInfo = QQGetUserInfo.getQQUserInfo(openId, openkey, pf);
			UserDO user = userManager.syncUserFromQQ(qqUserInfo);
			context.put("user", user);
			HttpSession session = request.getSession(true);
			session.setAttribute(LOGIN_CONTEXT, LoginContext.fromUser(user));
		}
		
		return mv;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
