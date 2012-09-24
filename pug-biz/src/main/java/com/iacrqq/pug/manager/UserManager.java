/**
 * pug
 */
package com.iacrqq.pug.manager;

import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.qq.api.domain.QQUserInfo;

/**
 * 
 * @author sihai
 *
 */
public interface UserManager {

	/**
	 * 
	 * @param user
	 * @throws ValidateException
	 */
	void add(UserDO user) throws ValidateException;
	
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
	 * @throws ValidateException
	 */
	boolean update(UserDO user) throws ValidateException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 
	 * @param qqUserInfo
	 * @return
	 */
	UserDO syncUserFromQQ(QQUserInfo qqUserInfo);
	
	/**
	 * 
	 * @param nick
	 * @return
	 */
	UserDO syncUserFromTaobao(String nick);
	
	/**
	 * 
	 * @param user
	 * @throws ValidateException
	 */
	void register(UserDO user) throws ValidateException;
	
	/**
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws ValidateException
	 */
	UserDO login(String name, String password) throws ValidateException;
}
