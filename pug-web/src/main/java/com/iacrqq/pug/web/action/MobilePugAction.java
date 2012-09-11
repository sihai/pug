package com.iacrqq.pug.web.action;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.domain.PointDO;
import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.manager.PugManager;
import com.iacrqq.pug.manager.UserManager;
import com.iacrqq.pug.model.JSONResultModel;
import com.iacrqq.pug.web.controller.AbstractController;

public class MobilePugAction extends AbstractController {

	private static final String CREATE_PUG = "_create_pug_";
	private static final String ADD_POINT = "_add_point_";
	
	@Autowired
	private PugManager pugManager;
	@Autowired
	private UserManager userManager;

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		JSONResultModel jsonResult = null;
		
		try {
			//
			UserDO user = validate(request, response);
			String operation = request.getParameter(OPERATION_KEY);
			
			if(StringUtils.equals(operation, CREATE_PUG)) {
				jsonResult = doCreatePug(request, response, user);
			} else if(StringUtils.equals(operation, ADD_POINT)) {
				jsonResult = doAddPoint(request, response, user);
			} else {
				throw new ValidateException("Unknown operation");
			}
		} catch (ValidateException e) {
			jsonResult = new JSONResultModel(false, e.getMessage(), null);
		}
		
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONObject.fromObject(jsonResult).toString());
			writer.flush();
		} finally {
			if(null != writer) {
				writer.close();
			}
		}
		
		return null;
	}

	private JSONResultModel doCreatePug(HttpServletRequest request,
			HttpServletResponse response, UserDO user) {
		
		JSONResultModel jsonResult = new JSONResultModel(true, null, null);
		PugDO pug = new PugDO();
		pug.setName(request.getParameter("name"));
		pug.setOwner(user);
		pug.setDescription(request.getParameter("description"));
		pug.setStatus(0L);
		pug.setType(0L);
		pug.setIsDeleted(false);
		
		// 第一点
		//PointDO point = new PointDO();
		//point.setName(request.getParameter("name"));
		//point.setLatitude(Double.valueOf(request.getParameter("latitude")));
		//point.setLongitude(Double.valueOf(request.getParameter("longitude")));
		//point.setDescription(request.getParameter("point_description"));
		//point.setIsDeleted(false);
		
		//List<PointDO> pointList = new ArrayList<PointDO>(1);
		//pug.setPointList(pointList);
		
		try {
			pugManager.add(pug);
		} catch (ValidateException e) {
			jsonResult.setIsSucceed(false);
			jsonResult.setErrorMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	private JSONResultModel doAddPoint(HttpServletRequest request,
			HttpServletResponse response, UserDO user) {
		
		JSONResultModel jsonResult = new JSONResultModel(true, null, null);
		
		PugDO pug = new PugDO();
		pug.setId(Long.valueOf(request.getParameter("pug")));
		PointDO point = new PointDO();
		point.setName(request.getParameter("name"));
		point.setLatitude(Double.valueOf(request.getParameter("latitude")));
		point.setLongitude(Double.valueOf(request.getParameter("longitude")));
		point.setDescription(request.getParameter("point_description"));
		point.setPug(pug);
		point.setIsDeleted(false);
		
		
		try {
			pugManager.add(point);
		} catch (ValidateException e) {
			jsonResult.setIsSucceed(false);
			jsonResult.setErrorMsg(e.getMessage());
		}
		
		return jsonResult;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	private UserDO validate(HttpServletRequest request,
			HttpServletResponse response) throws ValidateException {
		validatePugKey(request, response);
		UserDO user = userManager.login(request.getParameter("_user_name_"), request.getParameter("_password_"));
		if(null == user) {
			throw new ValidateException("You maybe not use pugapp, or config pugapp correct.");
		}
		return user;
	}
	
	public void setPugManager(PugManager pugManager) {
		this.pugManager = pugManager;
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
