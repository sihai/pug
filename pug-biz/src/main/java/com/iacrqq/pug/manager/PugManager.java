/**
 * pug
 */
package com.iacrqq.pug.manager;

import java.util.List;

import com.iacrqq.pug.domain.PointDO;
import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.model.PugQueryModel;
import com.iacrqq.pug.model.ResultModel;

/**
 * 
 * @author sihai
 *
 */
public interface PugManager {

	/**
	 * 
	 * @param pug
	 * @throws ValidateException TODO
	 */
	void add(PugDO pug) throws ValidateException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	PugDO get(Long id);
	
	/**
	 * 
	 * @param queryModel
	 * @return
	 */
	ResultModel<PugDO> query(PugQueryModel queryModel);
	
	/**
	 * 
	 * @param pug
	 * @return
	 * @throws ValidateException TODO
	 */
	boolean update(PugDO pug) throws ValidateException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 
	 * @param point
	 * @throws ValidateException
	 */
	void add(PointDO point) throws ValidateException;
	
	/*
	 * 
	 */
	List<PointDO> getPonintList(Long pugId);
}
