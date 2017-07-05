package org.charles.app.service.impl;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.service.StockAnalysisService;

/**
 * 股票分析、统计数据服务层
 * @author Charles
 *
 */
public class StockAnalysisServiceImpl implements StockAnalysisService{

	private StockAnalysisDao stockAnalysisDao;

	public StockAnalysisDao getStockAnalysisDao() {
		return stockAnalysisDao;
	}

	public void setStockAnalysisDao(StockAnalysisDao stockAnalysisDao) {
		this.stockAnalysisDao = stockAnalysisDao;
	}

}
