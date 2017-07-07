package org.charles.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.enums.Period;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Stock;
import org.charles.app.service.StockAnalysisService;

/**
 * 股票分析、统计数据服务层
 * @author Charles
 *
 */
public class StockAnalysisServiceImpl implements StockAnalysisService{

	private StockAnalysisDao stockAnalysisDao;
	
	@Override
	public List<BoardData> orgCountData(Date date){
		List<BoardData> data = stockAnalysisDao.findCmpCount(date, date, Stock.SPECIAL_ORG_KEY);
		
		List<BoardData> rs = new ArrayList<BoardData>();
		Map<String, BoardData> container = new HashMap<String, BoardData>();
		
		for(BoardData bd : data){
			String stockCode = bd.getStockCode();
			String rankType = bd.getRankType();
			
			BoardData target = container.get(stockCode);
			if(target == null){
				target = bd;
				container.put(stockCode, target);
				rs.add(target);
			}
			if("B".equals(rankType)){
				target.setCmpCountB(bd.getCmpCount());
			}else{
				target.setCmpCountS(bd.getCmpCount());
			}
		}
		return rs;
	}

	@Override
	public List<BoardData> newTopBoardData(Date date) {
		List<BoardData> data = stockAnalysisDao.findNewTopBoard(date);
		
		Map<String, BigDecimal> cache = new HashMap<String, BigDecimal>();
		//先计算总数放进缓存
		for(BoardData d : data){
			String key = d.getStockCode() + "-" + d.getRankType();
			String rankType = d.getRankType();
			
			BigDecimal sumMoney = cache.get(key);
			
			BigDecimal target = null;
			
			if("B".equals(rankType)){
				target = d.getBuyMoney();
			}else{
				target = d.getSaleMoney();
			}
			if(sumMoney == null){
				cache.put(key, target);
				continue;
			}
			cache.put(key, sumMoney.add(target));
		}
		
		//再计算百分比
		for(BoardData d : data){
			String key = d.getStockCode() + "-" + d.getRankType();
			String rankType = d.getRankType();
			
			BigDecimal sumMoney = cache.get(key);
			BigDecimal target = null;
			if("B".equals(rankType)){
				target = d.getBuyMoney();
			}else{
				target = d.getSaleMoney();
			}
			d.setMoneyPercent(new BigDecimal(target.doubleValue() / sumMoney.doubleValue() * 100).setScale(2, RoundingMode.HALF_UP));
		}
		
		return data;
	}
	
	@Override
	public List<BoardData> breakThroughBoardData(Date date, Period period) {
		List<BoardData> data = stockAnalysisDao.findBreakThroughBoard(date, period);
		
		Map<String, BigDecimal> cache = new HashMap<String, BigDecimal>();
		//先计算总数放进缓存
		for(BoardData d : data){
			String key = d.getStockCode() + "-" + d.getRankType();
			String rankType = d.getRankType();
			
			BigDecimal sumMoney = cache.get(key);
			
			BigDecimal target = null;
			
			if("B".equals(rankType)){
				target = d.getBuyMoney();
			}else{
				target = d.getSaleMoney();
			}
			if(sumMoney == null){
				cache.put(key, target);
				continue;
			}
			cache.put(key, sumMoney.add(target));
		}
		
		//再计算百分比
		for(BoardData d : data){
			String key = d.getStockCode() + "-" + d.getRankType();
			String rankType = d.getRankType();
			
			BigDecimal sumMoney = cache.get(key);
			BigDecimal target = null;
			if("B".equals(rankType)){
				target = d.getBuyMoney();
			}else{
				target = d.getSaleMoney();
			}
			d.setMoneyPercent(new BigDecimal(target.doubleValue() / sumMoney.doubleValue() * 100).setScale(2, RoundingMode.HALF_UP));
		}
		
		return data;
	}


	public StockAnalysisDao getStockAnalysisDao() {
		return stockAnalysisDao;
	}

	public void setStockAnalysisDao(StockAnalysisDao stockAnalysisDao) {
		this.stockAnalysisDao = stockAnalysisDao;
	}

}
