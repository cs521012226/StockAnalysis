package org.charles.app.analysis.impl;

import java.util.Date;
import java.util.List;

import org.charles.app.analysis.StockAnalysis;
import org.charles.app.analysis.StockAnalysisManage;
import org.charles.app.msg.TextTemplate;
import org.charles.app.msg.impl.MessageTextTemplate;
import org.charles.app.util.SysDateUtil;
import org.charles.framework.util.DateUtil;

/**
 * 股票分析管理
 * @author Charles
 *
 */
public class BaseAnalysisManage implements StockAnalysisManage {
	
	private List<StockAnalysis> analysisList;
	
	@Override
	public TextTemplate process() {
		Date date = SysDateUtil.getStockDate();
		
		TextTemplate textTemplate = new MessageTextTemplate();
		textTemplate.appendText("日期: " + DateUtil.convertDateToString(date));
		
		for(StockAnalysis analysis : analysisList){
			analysis.analyze(date, date, textTemplate);
		}
		
//		DataOutput dataOutput = new FileDataOutput(textTemplate.format(), "D:/DEV/analysisResult.txt");
//		dataOutput.execute();
		return textTemplate;
	}

	public List<StockAnalysis> getAnalysisList() {
		return analysisList;
	}

	public void setAnalysisList(List<StockAnalysis> analysisList) {
		this.analysisList = analysisList;
	}

}
