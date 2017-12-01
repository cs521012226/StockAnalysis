package org.charles.app.crawler.goodnews;

import org.charles.app.crawler.goodnews.vo.GoodNewsPrinter;


public class Main {

	public static void main(String[] args) throws Exception{
		GoodNewsCrawler crawler = new GoodNewsCrawler();
		
		crawler.setListener(new GoodNewsPrinter("C:\\Users\\Charles\\Desktop\\stock.html"));
//      crawler.getConf().setTopN(5);
		crawler.start(5);
	}

}
