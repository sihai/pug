/**
 * pug
 */
package com.iacrqq.pug.manager.impl;

import com.iacrqq.pug.dao.UserDAO;
import com.iacrqq.pug.domain.UserDO;
import com.iacrqq.pug.exception.ValidateException;
import com.iacrqq.pug.manager.UserManager;
import com.iacrqq.pug.qq.api.domain.QQUserInfo;
import com.iacrqq.util.PasswordUtil;
import com.iacrqq.util.StringUtil;

/**
 * 
 * @author sihai
 *
 */
public class UserManagerImpl implements UserManager {

	private UserDAO userDAO;
	
	@Override
	public void add(UserDO user) throws ValidateException {
		validate(user);
		userDAO.insert(user);
	}

	@Override
	public UserDO get(Long id) {
		return userDAO.get(id);
	}

	@Override
	public UserDO get(String name) {
		return userDAO.get(name);
	}

	@Override
	public UserDO getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

	@Override
	public boolean update(UserDO user) throws ValidateException {
		validate(user);
		return userDAO.update(user);
	}

	@Override
	public boolean delete(Long id) {
		return userDAO.delete(id);
	}

	@Override
	public UserDO syncUserFromQQ(QQUserInfo qqUserInfo) {
		String name = qqUserInfo.getNickname();
		UserDO user = get(name);
		if(null == user) {
			user = new UserDO();
		}
		user.setFromWhere(UserDO.USER_FROM_QQ);
		user.setSex(StringUtil.equals(UserDO.QQ_SEX_MALE, qqUserInfo.getGender()) ? 0L : 1L);
		user.setLogo(qqUserInfo.getFigureurl());
		user.setGrade(0L);
		user.setType(0L);
		user.setStatus(0L);
		user.setIsDeleted(false);
		try {
			if(null == user.getId()) {
				add(user);
			} else {
				update(user);
			}
		} catch (ValidateException e) {
			throw new RuntimeException("Not possiable !!!");
		}
		return user;
	}
	
	@Override
	public void register(UserDO user) throws ValidateException {
		validate(user);
		UserDO tmp = get(user.getName());
		if(null != tmp) {
			throw new ValidateException(String.format("用户名:%s重复,请选择其他用户名", user.getName()));
		}
		tmp = getByEmail(user.getEmail());
		if(null != tmp) {
			throw new ValidateException(String.format("用户Email:%s重复,请选择其他Email", user.getEmail()));
		}
		
		user.setPassword(PasswordUtil.encrypt(user.getPassword()));
		
		add(user);
	}
	
	@Override
	public UserDO login(String name, String password) throws ValidateException {
		if(StringUtil.isBlank(name)) {
			throw new ValidateException("请填写用户名！");
		}
		
		if(StringUtil.isBlank(password)) {
			throw new ValidateException("请填写密码！");
		}
		
		UserDO user = get(name);
		if(null == name) {
			throw new ValidateException("用户名或密码不正确！");
		}
		if(!PasswordUtil.equals(password, user.getPassword())) {
			throw new ValidateException("帐号或密码不正确！");
		}
		
		return user;
	}

	private void validate(UserDO user) throws ValidateException {
		
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
