package com.iacrqq.pug.web.action;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.domain.PointDO;
import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.manager.PugManager;
import com.iacrqq.pug.model.JSONResultModel;
import com.iacrqq.pug.web.controller.AbstractLoginedController;
import com.iacrqq.util.StringUtil;

public class PugAction extends AbstractLoginedController {

	private static final String CREATE_PUG = "_create_pug_";
	private static final String ADD_POINT = "_add_point_";
	
	@Autowired
	private PugManager pugManager;
	
	@Override
	protected ModelAndView handleLogined(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String operation = request.getParameter(OPERATION_KEY);
		JSONResultModel jsonResult = null;
		
		if(StringUtil.equals(operation, CREATE_PUG)) {
			jsonResult = doCreatePug(request, response);
		} else if(StringUtil.equals(operation, ADD_POINT)) {
			jsonResult = doAddPoint(request, response);
		} else {
			throw new RuntimeException("Unknown operation");
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
			HttpServletResponse response) {
		
		JSONResultModel jsonResult = new JSONResultModel(true, null, null);
		UserDO user = new UserDO();
		user.setId(getUserId(request.getSession()));
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
			HttpServletResponse response) {
		
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
	
	public void setPugManager(PugManager pugManager) {
		this.pugManager = pugManager;
	}
}
