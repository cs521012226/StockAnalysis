package org.charles.app.timer.task;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;

/**
 * 数据获取任务
 * @author YeChao
 */
public class DataExtractTask{
	
	private static Logger logger = Logger.getLogger(DataExtractTask.class);
	
	private UrlCrawler boardDataCrawler;
	private UrlCrawler companyRankCrawler;
	private UrlCrawler bigTradeCrawler;
	private UrlCrawler conceptFundRankCrawler;
	private UrlCrawler individualStockFundCrawler;
	private UrlCrawler newTopCrawler;
	private UrlCrawler breakThroughCrawler;
	

	public void run() {
		crawData(boardDataCrawler, "龙虎榜数据");
		crawData(companyRankCrawler, "营业部排名数据");
		crawData(bigTradeCrawler, "大单追踪数据");
		crawData(conceptFundRankCrawler, "概念资金数据");
		crawData(individualStockFundCrawler, "个股资金数据");
		crawData(newTopCrawler, "创新高股票数据");
		crawData(breakThroughCrawler, "向上突破股票数据");
	}
	
	public void crawData(UrlCrawler crawer, String title){
		logger.info("爬取" + title + "开始...");
		crawer.craw();
		logger.info("爬取" + title + "结束...");
	}

	public UrlCrawler getBoardDataCrawler() {
		return boardDataCrawler;
	}

	public void setBoardDataCrawler(UrlCrawler boardDataCrawler) {
		this.boardDataCrawler = boardDataCrawler;
	}

	public UrlCrawler getCompanyRankCrawler() {
		return companyRankCrawler;
	}

	public void setCompanyRankCrawler(UrlCrawler companyRankCrawler) {
		this.companyRankCrawler = companyRankCrawler;
	}

	public UrlCrawler getBigTradeCrawler() {
		return bigTradeCrawler;
	}

	public void setBigTradeCrawler(UrlCrawler bigTradeCrawler) {
		this.bigTradeCrawler = bigTradeCrawler;
	}

	public UrlCrawler getConceptFundRankCrawler() {
		return conceptFundRankCrawler;
	}

	public void setConceptFundRankCrawler(UrlCrawler conceptFundRankCrawler) {
		this.conceptFundRankCrawler = conceptFundRankCrawler;
	}

	public UrlCrawler getIndividualStockFundCrawler() {
		return individualStockFundCrawler;
	}

	public void setIndividualStockFundCrawler(UrlCrawler individualStockFundCrawler) {
		this.individualStockFundCrawler = individualStockFundCrawler;
	}

	public UrlCrawler getNewTopCrawler() {
		return newTopCrawler;
	}

	public void setNewTopCrawler(UrlCrawler newTopCrawler) {
		this.newTopCrawler = newTopCrawler;
	}

	public UrlCrawler getBreakThroughCrawler() {
		return breakThroughCrawler;
	}

	public void setBreakThroughCrawler(UrlCrawler breakThroughCrawler) {
		this.breakThroughCrawler = breakThroughCrawler;
	}
}
