package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.charles.app.dao.CommonDao;
import org.charles.app.dao.SysParamDao;
import org.charles.app.pojo.SysParam;
import org.charles.framework.define.VOBase;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 系统参数DAO
 * @author Charles
 *
 */
public class SysParamDaoImpl extends NamedParameterJdbcDaoSupport implements SysParamDao {

	private CommonDao commonDao;

	@Override
	public VOBase queryList(SysParam p, int start, int count, boolean hasCount)
			throws BusinessException {
		StringBuilder sql = new StringBuilder("select m.* from sys_param m  ");
		return commonDao.findByPage(sql.toString(), start, count,
				new RowMapper<SysParam>() {

					@Override
					public SysParam mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						SysParam m = new SysParam();
						m.setCode(rs.getString("code")); //Code
						m.setValue(rs.getString("value")); //Value
						m.setText(rs.getString("text")); //Name

						return m;
					}

				});
	}
	
	

	@Override
	public List<SysParam> queryAll(SysParam p) throws BusinessException {
		StringBuilder sql = new StringBuilder("select m.* from sys_param m  ");
		return getNamedParameterJdbcTemplate().query(sql.toString(), new HashMap<String, Object>(), new RowMapper<SysParam>() {

					@Override
					public SysParam mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						SysParam m = new SysParam();
						m.setCode(rs.getString("code")); //Code
						m.setValue(rs.getString("value")); //Value
						m.setText(rs.getString("text")); //Name

						return m;
					}

				});
	}



	@Override
	public void save(SysParam p) throws BusinessException {
		SqlHelper.Insert u = SqlHelper.getInsert("sys_param");

//		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}

	@Override
	public void delete(String code, String value) throws BusinessException {
		SqlHelper.Delete u = SqlHelper.getDelete("sys_param");

//		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}

	@Override
	public void update(SysParam p, String code, String value) throws BusinessException {
		SqlHelper.Update u = SqlHelper.getUpdate("sys_param");

//		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
