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
 * @author sihai
 *
 */
public class MyFoot extends AbstractLoginedController {

	@Autowired
	private UserManager userManager;
	
	@Override
	protected ModelAndView handleLogined(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("myFoot");
		return mv;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
