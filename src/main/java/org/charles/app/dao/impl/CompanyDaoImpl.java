package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.charles.app.dao.CompanyDao;
import org.charles.app.pojo.Company;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.HashCrypt;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 营业部、机构
 * @author Charles
 *
 */
public class CompanyDaoImpl extends NamedParameterJdbcDaoSupport implements CompanyDao{
	
	@Override
	public List<Company> findAll(Company param) throws BusinessException {
		String sql = "select cmp_code,cmp_name,style from company m";
		
		List<Company> rs = getJdbcTemplate().query(sql, new RowMapper<Company>() {
			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company m = new Company();
				m.setCmpCode(rs.getString("cmp_code")); //代码
				m.setCmpName(rs.getString("cmp_name")); //名字
				m.setStyle(rs.getString("style")); //机构风格
				return m;
			}
		});
		return rs;
	}

	@Override
	public Company save(Company p) throws BusinessException {
		p.setCmpCode(HashCrypt.MD5(p.getCmpName()));
		
		SqlHelper.Insert u = SqlHelper.getInsert("company");
		u.value("cmp_code", p.getCmpCode()); //代码
		u.value("cmp_name", p.getCmpName()); //名字
		u.value("style", p.getStyle()); //机构风格

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
		return p;
	}
	
	
}
