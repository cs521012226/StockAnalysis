package org.charles.app.timer.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;

/**
 * 龙虎榜数据获取任务
 * @author YeChao
 */
public class BoardDataExtractTask extends TimerTask{
	
	private static Logger logger = Logger.getLogger(BoardDataExtractTask.class);
	
	private UrlCrawler boardDataCrawler;
	

	@Override
	public void run() {
		logger.info("爬取龙虎榜数据开始...");
		boardDataCrawler.craw();
		logger.info("爬取龙虎榜数据结束...");
	}

	public UrlCrawler getBoardDataCrawler() {
		return boardDataCrawler;
	}
	public void setBoardDataCrawler(UrlCrawler boardDataCrawler) {
		this.boardDataCrawler = boardDataCrawler;
	}
	
}
