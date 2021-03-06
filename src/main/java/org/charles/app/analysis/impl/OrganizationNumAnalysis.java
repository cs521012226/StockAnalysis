package org.charles.app.analysis.impl;

import java.util.Date;
import java.util.List;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.enums.TradeType;
import org.charles.app.msg.TextTemplate;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Stock;
import org.charles.framework.define.Constants;

/**
 * 龙虎榜机构数量分析
 * @author Charles
 *
 */
public class OrganizationNumAnalysis extends AbstractBoardAnalysis {
	
	private StockAnalysisDao stockAnalysisDao;
	
	@Override
	public void analyze(Date beginDate, Date endDate, TextTemplate textTemplate) {
		List<BoardData> list = stockAnalysisDao.findCmpCount(beginDate, endDate, Stock.SPECIAL_ORG_KEY);
		int limit = 3;
		
		StringBuilder buyList = new StringBuilder();
		StringBuilder saleList = new StringBuilder();
		for(BoardData bd : list){
			if(bd.getCmpCount() < limit){
				continue;
			}
			
			StringBuilder temp = null;
			if(TradeType.BUY.eq(bd.getRankType())){
				temp = buyList;
			}else{
				temp = saleList;
			}
			temp.append(Constants.SEP).append(bd.getStockCode()).append(bd.getStockName()).append("(").append(bd.getCmpCount()).append(")");
		}
		if(buyList.length() > 0){
			buyList.delete(0, Constants.SEP.length());
			buyList.insert(0, "[").append("]");
		}else{
			buyList.delete(0, buyList.length());
			buyList.append("无");
		}
		if(saleList.length() > 0){
			saleList.delete(0, Constants.SEP.length());
			saleList.insert(0, "[").append("]");
		}else{
			saleList.delete(0, buyList.length());
			saleList.append("无");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("专用机构大于").append(limit)
		.append("的股票, B：").append(buyList.toString()).append(" S: ")
		.append(saleList.toString());
		
		textTemplate.appendText(sb.toString());
	}

	public StockAnalysisDao getStockAnalysisDao() {
		return stockAnalysisDao;
	}

	public void setStockAnalysisDao(StockAnalysisDao stockAnalysisDao) {
		this.stockAnalysisDao = stockAnalysisDao;
	}

}
