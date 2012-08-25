/**
 * pug
 */
package com.iacrqq.pug.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.iacrqq.pug.dao.PugDAO;
import com.iacrqq.pug.domain.PugDO;
import com.iacrqq.pug.model.PugQueryModel;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author sihai
 *
 */
public class PugDAOImpl implements PugDAO {

	private SqlMapClient sqlMapClient;
	
	@Override
	public void insert(PugDO pug) {
		try {
			sqlMapClient.insert("pug.dao.insert", pug);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PugDO get(Long id) {
		try {
			return (PugDO)sqlMapClient.queryForObject("pug.dao.get", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PugDO> query(PugQueryModel queryModel) {
		try {
			return (List<PugDO>)sqlMapClient.queryForList("pug.dao.query", queryModel);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Long count(PugQueryModel queryModel) {
		try {
			return (Long)sqlMapClient.queryForObject("pug.dao.count", queryModel);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean update(PugDO pug) {
		// FIXME
		try {
			return sqlMapClient.update("pug.dao.update", pug) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delete(Long id) {
		// FIXME
		try {
			return sqlMapClient.delete("pug.dao.delete", id) == 1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}
