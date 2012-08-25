/**
 * pug
 */
package com.iacrqq.pug.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iacrqq.pug.dao.PointDAO;
import com.iacrqq.pug.dao.PugDAO;
import com.iacrqq.pug.domain.PointDO;
import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.manager.PugManager;
import com.iacrqq.pug.model.PugQueryModel;
import com.iacrqq.pug.model.ResultModel;

/**
 * 
 * @author sihai
 *
 */
public class PugManagerImpl implements PugManager {

	@Autowired
	private PugDAO pugDAO;
	@Autowired
	private PointDAO pointDAO;
	
	@Override
	public void add(PugDO pug) throws ValidateException {
		validate(pug);
		pugDAO.insert(pug);
		List<PointDO> pointList = pug.getPointList();
		if(null != pointList) {
			for(PointDO point: pointList) {
				point.setPug(pug);
				pointDAO.insert(point);
			}
		}
	}

	@Override
	public PugDO get(Long id) {
		PugDO pug = pugDAO.get(id);
		pug.setPointList(pointDAO.getAll(id));
		return pug;
	}

	@Override
	public ResultModel<PugDO> query(PugQueryModel queryModel) {
		return ResultModel.buildResultModel(queryModel, pugDAO.query(queryModel), pugDAO.count(queryModel));
	}

	@Override
	public boolean update(PugDO pug) throws ValidateException {
		validate(pug);
		return pugDAO.update(pug);
	}

	@Override
	public boolean delete(Long id) {
		return pugDAO.delete(id);
	}

	@Override
	public void add(PointDO point) throws ValidateException {
		validatePoint(point);
		pointDAO.insert(point);
	}
	
	@Override
	public List<PointDO> getPonintList(Long pugId) {
		return pointDAO.getAll(pugId);
	}
	
	private void validate(PugDO pug) throws ValidateException {
		
	}
	
	private void validatePoint(PointDO point) throws ValidateException {
		
	}

	public void setPugDAO(PugDAO pugDAO) {
		this.pugDAO = pugDAO;
	}

	public void setPointDAO(PointDAO pointDAO) {
		this.pointDAO = pointDAO;
	}
}
