package org.charles.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.ConceptFundRankDao;
import org.charles.app.pojo.dto.ConceptFundRank;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 概念资金排名
 * @author Charles
 *
 */
public class ConceptFundRankDaoImpl extends NamedParameterJdbcDaoSupport implements ConceptFundRankDao{
	
	@Override
	public void delete() throws BusinessException {
		getJdbcTemplate().update("delete from concept_fund_rank");
	}

	@Override
	public void save(ConceptFundRank p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("concept_fund_rank");
		u.value("industry", p.getIndustry()); //行业
		u.value("company_count", p.getCompanyCount()); //公司家数
		u.value("industry_index", p.getIndustryIndex()); //行业指数
		u.value("updown_percent", p.getUpdownPercent()); //阶段涨跌幅
		u.value("in_fund", p.getInFund()); //流入资金(亿)
		u.value("out_fund", p.getOutFund()); //流出资金(亿)
		u.value("net_amount", p.getNetAmount()); //净额(亿)

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
	}
	@Override
	public void saveBatch(List<ConceptFundRank> pl) throws BusinessException {
		String sql = "insert into concept_fund_rank(industry, company_count, industry_index, "
				+ "updown_percent, in_fund, out_fund, net_amount, period, create_date) values(?,?,?,?,?,?,?,?,?)";
		
		List<Object[]> params = new ArrayList<Object[]>(pl.size());
		
		for(int i=0; i<pl.size(); i++){
			ConceptFundRank p = pl.get(i);
			
			params.add(new Object[]{
				p.getIndustry(),
				p.getCompanyCount(),
				p.getIndustryIndex(),
				p.getUpdownPercent(),
				p.getInFund(),
				p.getOutFund(),
				p.getNetAmount(),
				p.getPeriod().toString(),
				new Date()
			});
		}
		getJdbcTemplate().batchUpdate(sql, params);
	}
}
