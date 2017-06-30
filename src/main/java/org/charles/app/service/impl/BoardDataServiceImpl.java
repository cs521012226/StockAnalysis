/*package org.charles.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.charles.app.board.parser.BoardDataParser;
import org.charles.app.crawler.UrlCrawler;
import org.charles.app.dao.BoardDataDao;
import org.charles.app.dao.CompanyDao;
import org.charles.app.dao.StockDao;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Company;
import org.charles.app.pojo.Stock;
import org.charles.app.service.BoardDataService;
import org.charles.framework.exp.BusinessException;

public class BoardDataServiceImpl implements BoardDataService{
	
	private static Logger logger = Logger.getLogger(BoardDataServiceImpl.class);
	
	private CompanyDao companyDao;
	private StockDao stockDao;
	private BoardDataDao boardDataDao;
	
	@Override
	public List<BoardData> queryListByDate(String date) throws BusinessException {
		return boardDataDao.queryListByDate(date);
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}


	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public BoardDataDao getBoardDataDao() {
		return boardDataDao;
	}

	public void setBoardDataDao(BoardDataDao boardDataDao) {
		this.boardDataDao = boardDataDao;
	}

	public BoardDataParser getBoardDataParser() {
		return boardDataParser;
	}

	public void setBoardDataParser(BoardDataParser boardDataParser) {
		this.boardDataParser = boardDataParser;
	}
}
*/