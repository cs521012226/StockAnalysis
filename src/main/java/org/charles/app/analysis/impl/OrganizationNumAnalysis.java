package org.charles.app.analysis.impl;

import java.util.List;

import org.charles.app.dao.BoardDataDao;
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
	
	private BoardDataDao boardDataDao;
	
	@Override
	public void analyze(String beginDate, String endDate, TextTemplate textTemplate) {
		int limit = 3;
		
		List<BoardData> list = boardDataDao.findCmpCount(beginDate, endDate, Stock.SPECIAL_ORG_KEY, limit);
		
		StringBuilder buyList = new StringBuilder();
		StringBuilder saleList = new StringBuilder();
		for(BoardData bd : list){
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

	public BoardDataDao getBoardDataDao() {
		return boardDataDao;
	}

	public void setBoardDataDao(BoardDataDao boardDataDao) {
		this.boardDataDao = boardDataDao;
	}
	
}
