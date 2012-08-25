/**
 * pug
 */
package com.iacrqq.pug.dao.ibatis;

import java.sql.SQLException;

import com.iacrqq.pug.dao.UserDAO;
import com.iacrqq.pug.domain.UserDO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author sihai
 *
 */
public class UserDAOImpl implements UserDAO {

	private SqlMapClient sqlMapClient;
	
	@Override
	public void insert(UserDO user) {
		try {
			sqlMapClient.insert("user.dao.insert", user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDO get(Long id) {
		try {
			return (UserDO)sqlMapClient.queryForObject("user.dao.get", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public UserDO get(String name) {
		try {
			return (UserDO)sqlMapClient.queryForObject("user.dao.getByName", name);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDO getByEmail(String email) {
		try {
			return (UserDO)sqlMapClient.queryForObject("user.dao.getByEmail", email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(UserDO user) {
		// FIXME
		try {
			return sqlMapClient.update("user.dao.update", user) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delete(Long id) {
		// FIXME
		try {
			return sqlMapClient.delete("user.dao.delete", id) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}
