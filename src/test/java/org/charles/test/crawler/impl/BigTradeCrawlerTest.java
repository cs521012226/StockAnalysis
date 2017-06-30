package org.charles.test.crawler.impl;

import org.charles.app.crawler.UrlCrawler;
import org.charles.test.define.TestModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BigTradeCrawlerTest extends TestModel{
	
	@Autowired
	private UrlCrawler bigTradeCrawler;

	@Test
	public void testCraw() {
		
		bigTradeCrawler.craw();
	}

}
