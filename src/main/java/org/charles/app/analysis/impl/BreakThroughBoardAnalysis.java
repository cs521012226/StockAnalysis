package org.charles.app.analysis.impl;

import java.util.Date;
import java.util.List;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.enums.Period;
import org.charles.app.msg.TextTemplate;
import org.charles.app.pojo.BoardData;
import org.charles.framework.define.Constants;


/**
 * 向上突破且上龙虎榜的股票
 * @author Charles
 *
 */
public class BreakThroughBoardAnalysis extends AbstractBoardAnalysis {
	
	private StockAnalysisDao stockAnalysisDao;
	
	@Override
	public void analyze(Date beginDate, Date endDate, TextTemplate textTemplate) {
		List<BoardData> data = stockAnalysisDao.findBreakThroughBoardGroup(beginDate, Period.DAY_5);
		
		StringBuilder sb = new StringBuilder("向上突破且上榜的股票: ");
		if(data == null || data.isEmpty()){
			sb.append("无");
		}else{
			StringBuilder sub = new StringBuilder();
			for(BoardData bd : data){
				sub.append(Constants.SEP).append(bd.getStockCode()).append(bd.getStockName());
			}
			sub.delete(0, Constants.SEP.length());
			sub.insert(0, "[").append("]");
			sb.append(sub);
		}
		
		textTemplate.appendText(sb.toString());
	}

	public StockAnalysisDao getStockAnalysisDao() {
		return stockAnalysisDao;
	}

	public void setStockAnalysisDao(StockAnalysisDao stockAnalysisDao) {
		this.stockAnalysisDao = stockAnalysisDao;
	}

}
