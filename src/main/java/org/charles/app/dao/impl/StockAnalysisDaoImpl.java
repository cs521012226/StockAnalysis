package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.enums.Period;
import org.charles.app.pojo.BoardData;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 股票分析DAO
 * @author Charles
 *
 */
public class StockAnalysisDaoImpl extends NamedParameterJdbcDaoSupport implements StockAnalysisDao{

	@Override
	public List<BoardData> findCmpCount(Date beginDate, Date endDate, String cmpCode) throws BusinessException {
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select s.stock_code,s.stock_name,bd.rank_type,count(bd.cmp_code) as cmp_count");
		sb.append(" from board_data bd");
		sb.append(" inner join stock s on bd.stock_code = s.stock_code");
		sb.append(" where bd.cmp_code = :cmpCode");
		if(beginDate != null){
			sb.append(" and bd.create_date >= :beginDate");
			param.put("beginDate", DateUtil.convertDateToString(beginDate));
		}
		if(endDate != null){
			sb.append(" and bd.create_date <= :endDate");
			param.put("endDate", DateUtil.convertDateToString(endDate));
		}
		sb.append(" group by s.stock_code,s.stock_name,bd.rank_type");
		sb.append(" order by bd.rank_type,cmp_count desc,s.stock_code");
		
		param.put("cmpCode", cmpCode);
		
		return getNamedParameterJdbcTemplate().query(sb.toString(), param, new RowMapper<BoardData>(){

			@Override
			public BoardData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockName(rs.getString("stock_name"));//股票名字
				m.setStockCode(rs.getString("stock_code")); //股票代码
				m.setRankType(rs.getString("rank_type")); //排名类型
				m.setCmpCount(rs.getInt("cmp_count")); //总数
				
				return m;
			}
			
		});
	}


	@Override
	public List<BoardData> findNewTopBoardGroup(Date date) throws BusinessException {
		
		StringBuilder sb = new StringBuilder("select nt.stock_code,nt.stock_name");
		sb.append(" from board_data bd ");
		sb.append(" inner join new_top nt ");
		sb.append(" on bd.stock_code = nt.stock_code");
		sb.append(" where bd.create_date = ? and nt.create_date = ?");
		sb.append(" group by nt.stock_code,nt.stock_name");
		
		String dateStr = DateUtil.convertDateToString(date);
		
		return getJdbcTemplate().query(sb.toString(), new Object[]{ dateStr, dateStr }, new RowMapper<BoardData>(){
			
			@Override
			public BoardData mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockName(rs.getString("stock_name"));//股票名字
				m.setStockCode(rs.getString("stock_code")); //股票代码
//				m.setRankType(rs.getString("rank_type")); //排名类型
				return m;
			}
			
		});
	}
	@Override
	public List<BoardData> findNewTopBoard(Date date) throws BusinessException {
		
		StringBuilder sb = new StringBuilder("select nt.stock_code,nt.stock_name");
				sb.append(",(select c.cmp_name From company c where c.cmp_code = bd.cmp_code) cmp_name");
				sb.append(",bd.reason,bd.rank_type,bd.buy_money,bd.sale_money,c.style");
				sb.append(",ifnull(cr.rank_count_d, 0) rank_count_d,ifnull(cr2.rank_count_w, 0) rank_count_w");
				sb.append(" from board_data bd ");
				sb.append(" inner join new_top nt on bd.stock_code = nt.stock_code ");
				sb.append(" inner join company c on bd.cmp_code = c.cmp_code");
				sb.append(" left join (");
				sb.append("select crd.rank_count as rank_count_d,crd.cmp_code from company_rank crd");
				sb.append(" where crd.create_date = :date and crd.period = 'DAY'");
				sb.append(" group by crd.rank_count,crd.cmp_code");
				sb.append(") cr on bd.cmp_code = cr.cmp_code");
				sb.append(" left join (");
				sb.append("select crd.rank_count as rank_count_w,crd.cmp_code from company_rank crd");
				sb.append(" where crd.create_date = :date and crd.period = 'WEEK'");
				sb.append(" group by crd.rank_count,crd.cmp_code");
				sb.append(") cr2 on bd.cmp_code = cr2.cmp_code");

				sb.append(" where bd.create_date = :date and nt.create_date = :date");
				sb.append(" order by nt.stock_code,bd.rank_type,bd.buy_money desc");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", DateUtil.convertDateToString(date));
		
		return getNamedParameterJdbcTemplate().query(sb.toString(), params, new RowMapper<BoardData>(){
			
			@Override
			public BoardData mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockCode(rs.getString("stock_code")); //股票代码
				m.setStockName(rs.getString("stock_name"));//股票名字
				m.setCompanyName(rs.getString("cmp_name"));
				m.setReason(rs.getString("reason"));
				m.setRankType(rs.getString("rank_type")); //排名类型
				m.setBuyMoney(rs.getBigDecimal("buy_money"));	
				m.setSaleMoney(rs.getBigDecimal("sale_money"));
				m.setStyle(rs.getString("style"));
				m.setRankCountD(rs.getInt("rank_count_d"));
				m.setRankCountW(rs.getInt("rank_count_w"));
				
				return m;
			}
			
		});
	}
	

	@Override
	public List<BoardData> findBreakThroughBoardGroup(Date date, Period period) throws BusinessException {
		StringBuilder sb = new StringBuilder("select bt.stock_code,bt.stock_name from board_data bd");
		sb.append(" inner join break_through bt on bd.stock_code = bt.stock_code");
		sb.append(" where bd.create_date =  :date and bt.create_date = :date and bt.period = :period");
		sb.append(" group by bt.stock_code,bt.stock_name");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", DateUtil.convertDateToString(date));
		params.put("period", period.toString());
		
		return getNamedParameterJdbcTemplate().query(sb.toString(), params, new RowMapper<BoardData>(){
			
			@Override
			public BoardData mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockCode(rs.getString("stock_code")); //股票代码
				m.setStockName(rs.getString("stock_name"));//股票名字
				return m;
			}
			
		});
	}


	@Override
	public List<BoardData> findBreakThroughBoard(Date date, Period period) throws BusinessException {
		StringBuilder sb = new StringBuilder("select bt.stock_code,bt.stock_name,");
		sb.append("(select c.cmp_name From company c where c.cmp_code = bd.cmp_code) cmp_name,");
		sb.append("bd.reason,bd.rank_type,");
		sb.append("bd.buy_money,bd.sale_money,");
		sb.append("c.style,");
		sb.append(" ifnull(cr.rank_count_d, 0) rank_count_d,ifnull(cr2.rank_count_w, 0) rank_count_w");
		sb.append(" from board_data bd ");
		sb.append(" inner join break_through bt  on bd.stock_code = bt.stock_code");
		sb.append(" inner join company c on bd.cmp_code = c.cmp_code");
		sb.append(" left join (");
		sb.append(" select crd.rank_count as rank_count_d,crd.cmp_code from company_rank crd where crd.create_date = :date and crd.period = 'DAY'");
		sb.append(" group by crd.rank_count,crd.cmp_code");
		sb.append(") cr on bd.cmp_code = cr.cmp_code");
		sb.append(" left join (");
		sb.append(" select crd.rank_count as rank_count_w,crd.cmp_code from company_rank crd where crd.create_date = :date and crd.period = 'WEEK'");
		sb.append(" group by crd.rank_count,crd.cmp_code");
		sb.append(") cr2 on bd.cmp_code = cr2.cmp_code");

		sb.append(" where bd.create_date = :date and bt.create_date = :date and bt.period = :period order by bt.stock_code,bd.rank_type,bd.buy_money desc");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", DateUtil.convertDateToString(date));
		params.put("period", period.toString());
		
		return getNamedParameterJdbcTemplate().query(sb.toString(), params, new RowMapper<BoardData>(){
			
			@Override
			public BoardData mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockCode(rs.getString("stock_code")); //股票代码
				m.setStockName(rs.getString("stock_name"));//股票名字
				m.setCompanyName(rs.getString("cmp_name"));
				m.setReason(rs.getString("reason"));
				m.setRankType(rs.getString("rank_type")); //排名类型
				m.setBuyMoney(rs.getBigDecimal("buy_money"));	
				m.setSaleMoney(rs.getBigDecimal("sale_money"));
				m.setStyle(rs.getString("style"));
				m.setRankCountD(rs.getInt("rank_count_d"));
				m.setRankCountW(rs.getInt("rank_count_w"));
				return m;
			}
			
		});
	}
}
