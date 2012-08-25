/**
 * pug
 */
package com.iacrqq.pug.dao;

import java.util.List;

import com.iacrqq.pug.domain.PointDO;

/**
 * 
 * @author sihai
 *
 */
public interface PointDAO {

	/**
	 * 
	 * @param ponit
	 */
	void insert(PointDO ponit);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	PointDO get(Long id);
	
	/**
	 * 
	 * @param pug
	 * @return
	 */
	List<PointDO> getAll(Long pug);
	
	/**
	 * 
	 * @param ponit
	 * @return
	 */
	boolean update(PointDO point);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
}
