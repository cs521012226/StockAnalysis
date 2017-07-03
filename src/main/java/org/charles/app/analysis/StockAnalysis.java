package org.charles.app.analysis;

import org.charles.app.msg.TextTemplate;

/**
 * 龙虎榜分析
 * @author Charles
 *
 */
public interface StockAnalysis {

	/** 
	 * 分析操作
	 * @Author: Charles
	 * @param beginDate	开始日期
	 * @param endDate	结束日期
	 * @param textTemplate	结果模版
	 */
	public void analyze(String beginDate, String endDate, TextTemplate textTemplate);
}
