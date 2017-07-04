package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.BreakThroughDao;
import org.charles.app.pojo.dto.BreakThrough;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.DateUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 向上突破数据DAO
 * @author Charles
 *
 */
public class BreakThroughDaoImpl extends NamedParameterJdbcDaoSupport implements BreakThroughDao{
	
	@Override
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException {
		getJdbcTemplate().update("delete from break_through where create_date >= ?", 
				new Object[]{ DateUtil.convertDateToString(date) });
		if(andNow){
			getJdbcTemplate().update("delete from break_through where create_date = ?", 
					new Object[]{ DateUtil.convertDateToString(new Date()) });
		}
	}
	
	@Override
	public void saveBatch(List<BreakThrough> pl) throws BusinessException {
		String sql = "insert into break_through(stock_code,stock_name,new_price,amount,volume,updown_percent,turnover_rate,period,create_date)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			BreakThrough p = pl.get(i);
			
			params.add(new Object[]{
				p.getStockCode(),
				p.getStockName(),
				p.getNewPrice(),
				p.getAmount(),
				p.getVolume(),
				p.getUpdownPercent(),
				p.getTurnoverRate(),
				p.getPeriod().toString(),
				new Date()
			});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
