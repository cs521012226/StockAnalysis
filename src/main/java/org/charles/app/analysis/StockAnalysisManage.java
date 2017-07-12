package org.charles.app.analysis;

import org.charles.app.msg.TextTemplate;

/**
 * 股票分析管理器
 * @author Charles
 *
 */
public interface StockAnalysisManage {

	/** 
	 * 执行分析处理
	 * @Author: Charles
	 */
	public TextTemplate process();
}
