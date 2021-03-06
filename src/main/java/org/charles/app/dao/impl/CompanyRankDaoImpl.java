package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.CompanyRankDao;
import org.charles.app.pojo.dto.CompanyRank;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.CipherUtil;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public class CompanyRankDaoImpl extends NamedParameterJdbcDaoSupport implements CompanyRankDao{
	
	@Override
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException {
		getJdbcTemplate().update("delete from company_rank where create_date >= ?", new Object[]{ DateUtil.convertDateToString(date) });
		if(andNow){
			getJdbcTemplate().update("delete from company_rank where create_date = ?", new Object[]{ DateUtil.convertDateToString(new Date()) });
		}
	}

	@Override
	public void save(CompanyRank p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("company_rank");
		u.value("cmp_code", CipherUtil.MD5(p.getCmpName())); //营业部名称
		u.value("rank_count", p.getRankCount()); //上榜次数
		u.value("amount", p.getAmount()); //合计动用资金（万）
		u.value("rank_count_year", p.getRankCountYear()); //年内上榜次数
		u.value("buy_stock_count", p.getBuyStockCount()); //年内买入股票只数

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}
	@Override
	public void saveBatch(List<CompanyRank> pl) throws BusinessException {
		String sql = "insert into company_rank(cmp_code, rank_count, amount, rank_count_year, buy_stock_count, period, create_date) values(?,?,?,?,?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			CompanyRank p = pl.get(i);
			
			params.add(new Object[]{
					CipherUtil.MD5(p.getCmpName()), 
					p.getRankCount(), 
					p.getAmount(), 
					p.getRankCountYear(), 
					p.getBuyStockCount(), 
					p.getPeriod().toString(),
					new Date()});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
