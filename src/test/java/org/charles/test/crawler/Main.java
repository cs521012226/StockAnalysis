package org.charles.test.crawler;

import org.charles.app.crawler.impl.BoardDataCrawler;

public class Main {

	public static void main(String[] args) {
//		CompanyRankCrawler cr = new CompanyRankCrawler();
//		cr.craw();
		
		BoardDataCrawler bdc = new BoardDataCrawler();
		bdc.craw();
	}

}
