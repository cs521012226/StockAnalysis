package org.charles.app.timer.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;

/**
 * 营业部排名数据获取任务
 * @author YeChao
 */
public class CompanyRankExtractTask extends TimerTask{
	
	private static Logger logger = Logger.getLogger(CompanyRankExtractTask.class);
	
	private UrlCrawler companyRankCrawler;
	

	@Override
	public void run() {
		logger.info("爬取龙虎榜数据开始...");
		companyRankCrawler.craw();
		logger.info("爬取龙虎榜数据结束...");
	}
	public UrlCrawler getCompanyRankCrawler() {
		return companyRankCrawler;
	}
	public void setCompanyRankCrawler(UrlCrawler companyRankCrawler) {
		this.companyRankCrawler = companyRankCrawler;
	}
	
}
