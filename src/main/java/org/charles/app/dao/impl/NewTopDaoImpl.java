package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.NewTopDao;
import org.charles.app.pojo.dto.NewTop;
import org.charles.framework.exp.BusinessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 创新高数据DAO
 * @author Charles
 *
 */
public class NewTopDaoImpl extends NamedParameterJdbcDaoSupport implements NewTopDao{
	
	@Override
	public void delete() throws BusinessException {
		getJdbcTemplate().update("delete from new_top");
	}
	
	@Override
	public void saveBatch(List<NewTop> pl) throws BusinessException {
		String sql = "insert into new_top(stock_code,stock_name,updown_percent,turnover_rate,new_price,prev_top,prev_top_date,create_date)"
				+ " values(?,?,?,?,?,?,?,?)";
		
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			NewTop p = pl.get(i);
			
			params.add(new Object[]{
				p.getStockCode(),
				p.getStockName(),
				p.getUpdownPercent(),
				p.getTurnoverRate(),
				p.getNewPrice(),
				p.getPrevTop(),
				p.getPrevTopDate(),
				new Date()
			});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
