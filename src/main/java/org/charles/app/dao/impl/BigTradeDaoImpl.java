package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.charles.app.dao.BigTradeDao;
import org.charles.app.pojo.dto.BigTrade;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 大单追踪
 * @author Charles
 *
 */
public class BigTradeDaoImpl extends NamedParameterJdbcDaoSupport implements BigTradeDao{
	
	@Override
	public void delete() throws BusinessException {
		getJdbcTemplate().update("delete from big_trade");
	}

	@Override
	public void save(BigTrade p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("big_trade");
		u.value("trade_date", p.getTradeDate()); //成交时间
		u.value("stock_code", p.getStockCode()); //股票代码
		u.value("stock_name", p.getStockName()); //股票简称
		u.value("price", p.getPrice()); //成交价格
		u.value("volume", p.getVolume()); //成交量(股)
		u.value("amount", p.getAmount()); //成交额(万元)
		u.value("type", p.getType()); //大单性质
		u.value("updown_percent", p.getUpdownPercent()); //涨跌幅
		u.value("updown_price", p.getUpdownPrice()); //涨跌额

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}
	@Override
	public void saveBatch(List<BigTrade> pl) throws BusinessException {
		String sql = "insert into big_trade(trade_date, stock_code, stock_name, price, volume, amount, type, updown_percent, updown_price)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			BigTrade p = pl.get(i);
			
			params.add(new Object[]{p.getTradeDate(),
				p.getStockCode(),
				p.getStockName(),
				p.getPrice(),
				p.getVolume(),
				p.getAmount(),
				p.getType(),
				p.getUpdownPercent(),
				p.getUpdownPrice()});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
