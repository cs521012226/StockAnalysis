package org.charles.app.analysis.impl;

import java.util.List;

import org.charles.app.analysis.StockAnalysis;
import org.charles.app.msg.TextTemplate;
import org.charles.app.pojo.BoardData;

/**
 * 
 * @author Charles
 *
 */
public abstract class AbstractBoardAnalysis implements StockAnalysis {
	
	private TextTemplate textTemplate;
	private List<BoardData> boardDataList;

	public TextTemplate getTextTemplate() {
		return textTemplate;
	}

	public void setTextTemplate(TextTemplate textTemplate) {
		this.textTemplate = textTemplate;
	}

	public List<BoardData> getBoardDataList() {
		return boardDataList;
	}
	public void setBoardDataList(List<BoardData> boardDataList) {
		this.boardDataList = boardDataList;
	}
}
