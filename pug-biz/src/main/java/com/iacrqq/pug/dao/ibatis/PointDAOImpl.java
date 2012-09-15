/**
 * pug
 */
package com.iacrqq.pug.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.iacrqq.pug.dao.PointDAO;
import com.iacrqq.pug.domain.PointDO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author sihai
 *
 */
public class PointDAOImpl implements PointDAO {

	private SqlMapClient sqlMapClient;
	
	@Override
	public void insert(PointDO ponit) {
		try {
			sqlMapClient.insert("point.dao.insert", ponit);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PointDO get(Long id) {
		try {
			return (PointDO)sqlMapClient.queryForObject("point.dao.get", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PointDO> getAll(Long pug) {
		try {
			return (List<PointDO>)sqlMapClient.queryForList("point.dao.getAll", pug);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(PointDO point) {
		// FIXME
		try {
			return sqlMapClient.update("point.dao.update", point) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delete(Long id) {
		// FIXME
		try {
			return sqlMapClient.delete("point.dao.delete", id) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}
