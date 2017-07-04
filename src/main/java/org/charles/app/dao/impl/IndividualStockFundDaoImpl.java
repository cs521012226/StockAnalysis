package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.IndividualStockFundDao;
import org.charles.app.pojo.dto.IndividualStockFund;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 个股资金流向（即时）
 * @author Charles
 *
 */
public class IndividualStockFundDaoImpl extends NamedParameterJdbcDaoSupport implements IndividualStockFundDao{
	
	@Override
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException {
		getJdbcTemplate().update("delete from ind_stock_fund where create_date >= ?", 
				new Object[]{ DateUtil.convertDateToString(date) });
		if(andNow){
			getJdbcTemplate().update("delete from ind_stock_fund where create_date = ?", 
					new Object[]{ DateUtil.convertDateToString(new Date()) });
		}
	}

	@Override
	public void save(IndividualStockFund p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("ind_stock_fund");
		u.value("stock_code", p.getStockCode()); //股票代码
		u.value("stock_name", p.getStockName()); //股票简称
		u.value("new_price", p.getNewPrice()); //最新价
		u.value("updown_percent", p.getUpdownPercent()); //涨跌幅
		u.value("turnover_rate", p.getTurnoverRate()); //换手率
		u.value("in_fund", p.getInFund()); //流入资金(元)
		u.value("out_fund", p.getOutFund()); //流出资金(元)
		u.value("net_amount", p.getNetAmount()); //净额(元)
		u.value("amount", p.getAmount()); //成交额(元)
		u.value("in_big_trade", p.getInBigTrade()); //大单流入(元)

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}
	@Override
	public void saveBatch(List<IndividualStockFund> pl) throws BusinessException {
		String sql = "insert into ind_stock_fund(stock_code, stock_name, new_price, updown_percent, "
				+ "turnover_rate, in_fund, out_fund, net_amount, amount, in_big_trade, create_date) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			IndividualStockFund p = pl.get(i);
			
			params.add(new Object[]{
				p.getStockCode(),
				p.getStockName(),
				p.getNewPrice(),
				p.getUpdownPercent(),
				p.getTurnoverRate(),
				p.getInFund(),
				p.getOutFund(),
				p.getNetAmount(),
				p.getAmount(),
				p.getInBigTrade(),
				new Date()
			});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
