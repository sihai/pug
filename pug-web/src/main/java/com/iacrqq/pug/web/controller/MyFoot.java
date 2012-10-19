/**
 * pug
 */
package com.iacrqq.pug.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.manager.UserManager;

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
