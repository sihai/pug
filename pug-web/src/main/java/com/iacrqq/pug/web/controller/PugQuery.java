/**
 * pug
 */
package com.iacrqq.pug.web.controller;

import java.io.Writer;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.manager.PugManager;
import com.iacrqq.pug.model.BaseQueryModel;
import com.iacrqq.pug.model.JSONResultModel;
import com.iacrqq.pug.model.PugQueryModel;
import com.iacrqq.pug.model.ResultModel;
import com.iacrqq.util.DateUtil;
import com.iacrqq.util.StringUtil;

/**
 * 
 * @author sihai
 *
 */
public class PugQuery extends AbstractLoginedController {

	private static final String QUERY_PUG = "_query_pug_";
	private static final String QUERY_POINT = "_query_point_";
	
	@Autowired
	private PugManager pugManager;

	@Override
	protected ModelAndView handleLogined(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String operation = request.getParameter(OPERATION_KEY);
		JSONResultModel jsonResult = null;
		
		if(StringUtil.equals(operation, QUERY_PUG)) {
			jsonResult = doQueryPug(request, response);
		} else if(StringUtil.equals(operation, QUERY_POINT)) {
			jsonResult = doQueryPoint(request, response);
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
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private JSONResultModel doQueryPug(HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONResultModel jsonResult = new JSONResultModel(true, null, null);
		
		String strCurrentPage = request.getParameter("current_page");
		String strStartTime = request.getParameter("start_time");
		String strEndTime = request.getParameter("end_time");
		String strType = request.getParameter("type");
		
		Long currentPage = 1L;
		Date startTime = null;
		Date endTime = null;
		Long type = null;
		
		if(StringUtil.isNotBlank(strCurrentPage)) {
			currentPage = Long.valueOf(strCurrentPage);
		}
		
		try {
			if(StringUtil.isNotBlank(strStartTime)) {
				startTime = DateUtil.parse(strStartTime, DateUtil.DEFAULT_DATE_FORMAT);
			}
			
			if(StringUtil.isNotBlank(strStartTime)) {
				endTime = DateUtil.parse(strEndTime, DateUtil.DEFAULT_DATE_FORMAT);
			}
		} catch (ParseException e) {
			jsonResult.setIsSucceed(false);
			jsonResult.setErrorMsg(String.format("时间格式错误,正确的时间格式:%s", DateUtil.DEFAULT_DATE_FORMAT));
			return jsonResult;
		}
		
		if(StringUtil.isNotBlank(strType)) {
			type = Long.valueOf(strType);
		}
		
		ResultModel<PugDO> resultModel = pugManager.query(PugQueryModel.buildQueryModel(getUserId(request.getSession()), type, startTime, endTime, currentPage, BaseQueryModel.DEFAULT_PAGE_SIZE));
		jsonResult.setResult(resultModel);
		
		return jsonResult;
	}
	
	private JSONResultModel doQueryPoint(HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONResultModel jsonResult = new JSONResultModel(true, null, null);
		jsonResult.setResult(pugManager.getPonintList(Long.valueOf(request.getParameter("pug"))));
		return jsonResult;
	}
	
	public void setPugManager(PugManager pugManager) {
		this.pugManager = pugManager;
	}
}
