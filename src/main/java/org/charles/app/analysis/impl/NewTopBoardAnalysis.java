package org.charles.app.analysis.impl;

import java.util.List;

import org.charles.app.dao.BoardDataDao;
import org.charles.app.msg.TextTemplate;
import org.charles.app.pojo.BoardData;

/**
 * 创新高且上龙虎榜的股票
 * @author Charles
 *
 */
public class NewTopBoardAnalysis extends AbstractBoardAnalysis {
	
	private BoardDataDao boardDataDao;
	
	@Override
	public void analyze(String beginDate, String endDate, TextTemplate textTemplate) {
		List<BoardData> data = boardDataDao.findNewTopBoard(beginDate);
		
		StringBuilder sb = new StringBuilder("创新高且上榜的股票: ");
		if(data == null || data.isEmpty()){
			sb.append("无");
		}else{
			for(BoardData bd : data){
				sb.append(bd.getStockCode()).append(bd.getStockName());
			}
		}
		
		textTemplate.appendText(sb.toString());
	}

	public BoardDataDao getBoardDataDao() {
		return boardDataDao;
	}

	public void setBoardDataDao(BoardDataDao boardDataDao) {
		this.boardDataDao = boardDataDao;
	}
	
}
