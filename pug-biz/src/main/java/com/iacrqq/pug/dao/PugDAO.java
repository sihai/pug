/**
 * pug
 */
package com.iacrqq.pug.dao;

import java.util.List;

import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.model.PugQueryModel;

/**
 * 
 * @author sihai
 *
 */
public interface PugDAO {

	/**
	 * 
	 * @param pug
	 */
	void insert(PugDO pug);
	
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
	List<PugDO> query(PugQueryModel queryModel);
	
	/**
	 * 
	 * @param queryModel
	 * @return
	 */
	Long count(PugQueryModel queryModel);
	
	/**
	 * 
	 * @param pug
	 * @return
	 */
	boolean update(PugDO pug);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
}
