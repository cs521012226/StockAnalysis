package org.charles.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.charles.app.dao.BoardDataDao;
import org.charles.app.pojo.BoardData;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.SqlHelper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 * 龙虎榜
 * @author Charles
 *
 */
public class BoardDataDaoImpl extends NamedParameterJdbcDaoSupport implements BoardDataDao{
	
	

	@Override
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException {
		getJdbcTemplate().update("delete from board_data where create_date >= ?", new Object[]{ DateUtil.convertDateToString(date) });
		if(andNow){
			getJdbcTemplate().update("delete from board_data where create_date = ?", new Object[]{ DateUtil.convertDateToString(new Date()) });
		}
	}


	@Override
	public List<BoardData> queryListByDate(Date date) throws BusinessException {
		StringBuilder sb = new StringBuilder("select s.stock_name,");
		sb.append("s.stock_code,");
		sb.append("c.cmp_name,");
		sb.append("c.cmp_code,");
		sb.append("c.style,");
		sb.append("bd.reason,");
		sb.append("bd.buy_money,");
		sb.append("bd.sale_money,");
		sb.append("bd.rank_type,");
		sb.append("bd.board_date");
		sb.append(" from board_data bd"); 
		sb.append(" inner join stock s on bd.stock_code = s.stock_code");
		sb.append(" inner join company c on bd.cmp_code = c.cmp_code");
		sb.append(" where bd.board_date = ?");
		sb.append(" order by bd.stock_code,bd.rank_type");
		
		return getJdbcTemplate().query(sb.toString(), new Object[]{ DateUtil.convertDateToString(date) }, new RowMapper<BoardData>(){

			@Override
			public BoardData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				
				BoardData m = new BoardData();
				m.setStockName(rs.getString("stock_name"));//股票名字
				m.setStockCode(rs.getString("stock_code")); //股票代码
				m.setCmpCode(rs.getString("cmp_code")); //机构代码
				m.setCompanyName(rs.getString("cmp_name")); //机构名字
				m.setReason(rs.getString("reason")); //上榜原因
				m.setBuyMoney(rs.getBigDecimal("buy_money")); //买入额（万）
				m.setSaleMoney(rs.getBigDecimal("sale_money")); //卖出额（万）
				m.setRankType(rs.getString("rank_type")); //排名类型
				m.setBoardDate(DateUtil.convertStringToDate(rs.getString("board_date"))); //上榜日期
				
				return m;
			}
			
		});
	}
	

	@Override
	public void save(BoardData p) throws BusinessException {
		
		SqlHelper.Insert u = SqlHelper.getInsert("board_data");
		u.value("stock_code", p.getStockCode()); //股票代码
		u.value("cmp_code", p.getCmpCode()); //股票名字
		u.value("reason", p.getReason()); //上榜原因
		u.value("buy_money", p.getBuyMoney()); //买入额（万）
		u.value("sale_money", p.getSaleMoney()); //卖出额（万）
		u.value("rank_type", p.getRankType()); //排名类型
		u.value("board_date", p.getBoardDate()); //上榜日期
		u.value("create_date", new Date()); //提交日期

		getNamedParameterJdbcTemplate().update(u.getSql(), u.getParams());
		
	}

	@Override
	public boolean isExistData(Date boardDate) throws BusinessException {
		String sql = "select count(1) from board_data where board_date = ?";
		return getJdbcTemplate().queryForInt(sql, new Object[]{ DateUtil.convertDateToString(boardDate) }) > 0 ? true : false;
	}
	
}
