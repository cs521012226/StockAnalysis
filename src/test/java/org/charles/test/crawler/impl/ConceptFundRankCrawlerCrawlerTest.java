package org.charles.test.crawler.impl;

import org.charles.app.crawler.UrlCrawler;
import org.charles.test.define.TestModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConceptFundRankCrawlerCrawlerTest extends TestModel{
	
	@Autowired
	private UrlCrawler conceptFundRankCrawler;

	@Test
	public void testCraw() {
		
		conceptFundRankCrawler.craw();
	}

}
