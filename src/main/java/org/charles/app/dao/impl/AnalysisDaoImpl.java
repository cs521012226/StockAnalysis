package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.charles.app.dao.AnalysisDao;
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
public class AnalysisDaoImpl extends NamedParameterJdbcDaoSupport implements AnalysisDao{

	@Override
	public List<BoardData> findCmpCount(Date beginDate, Date endDate, String cmpCode, int lg) throws BusinessException {
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select s.stock_code,");
		sb.append(" s.stock_name,");
		sb.append(" bd.rank_type,");
		sb.append(" count(bd.cmp_code) as cmp_count ");
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
		sb.append(" having cmp_count >= :cmpCount");
		sb.append(" order by bd.rank_type,cmp_count desc,s.stock_code");
		
		param.put("cmpCode", cmpCode);
		param.put("cmpCount", lg);
		
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
	public List<BoardData> findNewTopBoard(Date date) throws BusinessException {
		
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
	public List<BoardData> findBreakThroughBoard(Date date) throws BusinessException {
		StringBuilder sb = new StringBuilder("select bt.stock_code,bt.stock_name from break_through bt");
		sb.append(" inner join board_data bd on bt.stock_code = bd.stock_code");
		sb.append(" where bt.create_date = ? and bd.create_date = ?");
		sb.append(" group by bt.stock_code,bt.stock_name");
		
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
	
}
