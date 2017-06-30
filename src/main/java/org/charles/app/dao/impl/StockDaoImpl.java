package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.charles.app.dao.StockDao;
import org.charles.app.pojo.Stock;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 股票表
 * @author Charles
 *
 */
public class StockDaoImpl extends NamedParameterJdbcDaoSupport implements StockDao{
	
	@Override
	public List<Stock> findAll(Stock param) throws BusinessException {
		String sql = "select stock_code,stock_name from stock m ";
		
		List<Stock> rs = getJdbcTemplate().query(sql, new RowMapper<Stock>() {
			
			@Override
			public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stock m = new Stock();
				m.setStockCode(rs.getString("stock_code")); //代码
				m.setStockName(rs.getString("stock_name")); //名字
				return m;
			}
		});	
		return rs;
	}

	@Override
	public Stock save(Stock param) throws BusinessException {
		SqlHelper.Insert u = SqlHelper.getInsert("stock");
		u.value("stock_code", param.getStockCode()); //代码
		u.value("stock_name", param.getStockName()); //名字

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
		
		return param;
	}
	
}
