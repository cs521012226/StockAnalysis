package org.charles.app.analysis.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.charles.app.analysis.StockAnalysis;
import org.charles.app.analysis.StockAnalysisManage;
import org.charles.app.msg.DataOutput;
import org.charles.app.msg.TextTemplate;
import org.charles.app.msg.impl.FileDataOutput;
import org.charles.app.msg.impl.MessageTextTemplate;
import org.charles.framework.util.DateUtil;

/**
 * 股票分析管理
 * @author Charles
 *
 */
public class BaseAnalysisManage implements StockAnalysisManage {
	
	private List<StockAnalysis> analysisList;
	
	@Override
	public void process() {
		String date = getDate();
		
		TextTemplate textTemplate = new MessageTextTemplate();
		
		for(StockAnalysis analysis : analysisList){
			analysis.analyze(date, date, textTemplate);
		}
		
		DataOutput dataOutput = new FileDataOutput(textTemplate.format(), "D:/DEV/analysisResult.txt");
		dataOutput.execute();
	}
	
	public String getDate(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 5);
		c.set(Calendar.DAY_OF_MONTH, 30);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		return DateUtil.convertDateToString(c.getTime());
	}

	public List<StockAnalysis> getAnalysisList() {
		return analysisList;
	}

	public void setAnalysisList(List<StockAnalysis> analysisList) {
		this.analysisList = analysisList;
	}

}
