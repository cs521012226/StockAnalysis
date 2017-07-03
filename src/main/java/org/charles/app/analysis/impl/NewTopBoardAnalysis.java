package org.charles.app.analysis.impl;

import org.charles.app.dao.BoardDataDao;
import org.charles.app.msg.TextTemplate;

/**
 * 创新高且上龙虎榜的股票
 * @author Charles
 *
 */
public class NewTopBoardAnalysis extends AbstractBoardAnalysis {
	
	private BoardDataDao boardDataDao;
	
	@Override
	public void analyze(String beginDate, String endDate, TextTemplate textTemplate) {
	}

	public BoardDataDao getBoardDataDao() {
		return boardDataDao;
	}

	public void setBoardDataDao(BoardDataDao boardDataDao) {
		this.boardDataDao = boardDataDao;
	}
	
}
