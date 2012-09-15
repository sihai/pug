/**
 * pug
 */
package com.iacrqq.pug.dao;

import com.iacrqq.pug.domain.UserDO;

/**
 * 
 * @author sihai
 *
 */
public interface UserDAO {

	/**
	 * 
	 * @param user
	 */
	void insert(UserDO user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	UserDO get(Long id);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	UserDO get(String name);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	UserDO getByEmail(String email);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	boolean update(UserDO user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
}
