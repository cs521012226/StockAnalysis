package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;
import org.charles.app.pojo.dto.IndividualStockFund;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取个股资金流向数据
 * @author YeChao
 * 2017年6月28日
 */
public class IndividualStockFundCrawler implements UrlCrawler {
	private static Logger logger = Logger.getLogger(IndividualStockFundCrawler.class);
	private String url = "http://data.10jqka.com.cn/funds/ggzjl/field/zdf/order/desc/page/%s/ajax/1/";

	@Override
	public void craw() {
		try {
			List<IndividualStockFund> rs = getData();
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<IndividualStockFund> getData() throws Exception{
		
		int pageNumber = 1;
		int pageSize = 10;
		int endPage = 1;
		
		Unit firstUnit = parser(pageNumber, endPage);
		int totolPage = firstUnit.getTotolPage();
		pageNumber = endPage + 1;
		
		List<Future<Unit>> futureList = new ArrayList<Future<Unit>>();
		ExecutorService es = Executors.newFixedThreadPool(totolPage/pageSize > 0 ? totolPage/pageSize : 1);
		while(true){
			endPage = pageNumber + pageSize;
			if(endPage >= totolPage){
				futureList.add(es.submit(new Runner(pageNumber, totolPage)));
				break;
			}
			
			futureList.add(es.submit(new Runner(pageNumber, endPage)));
			pageNumber = endPage + 1;
		}
		es.shutdown();
		
		List<IndividualStockFund> dataSource = firstUnit.getData();
		for(Future<Unit> fu : futureList){
			dataSource.addAll(fu.get().getData());
		}
		return dataSource;
	}
	
	public Unit parser(int startPageNumber, int endPageNumber){
		
		List<IndividualStockFund> rs = new ArrayList<IndividualStockFund>();
		Unit data = new Unit();
		
		while(startPageNumber <= endPageNumber){
			String url = String.format(this.url, startPageNumber);
			
			Document doc = HtmlUtil.getDoc(url);
			
			String pageInfo = doc.select(".page_info").text();
			if(!StringUtil.isBlank(pageInfo)){
				int totolPage = Integer.valueOf(pageInfo.split("/")[1]);
				data.setTotolPage(totolPage);
			}
			
			Elements row = doc.select("tbody tr");
			
			for(Element r : row){
				int colIndex = 1;
				String stockCode = r.child(colIndex).child(0).text();	//股票代码	
				String stockName = r.child(colIndex).child(0).text(); 	//股票简称	
				BigDecimal newPrice = new BigDecimal(r.child(colIndex).text());	//最新价	
				String updownPercent = r.child(colIndex).text();		//涨跌幅
				String turnoverRate = r.child(colIndex).text();		//换手率	
				BigDecimal inFund = new BigDecimal(r.child(colIndex).text());		//流入资金(元)	
				BigDecimal outFund = new BigDecimal(r.child(colIndex).text());		//流出资金(元)	
				BigDecimal netAmount = new BigDecimal(r.child(colIndex).text());	//净额(元)	
				BigDecimal amount = new BigDecimal(r.child(colIndex).text());		//成交额(元)	
				BigDecimal inBigTrade = new BigDecimal(r.child(colIndex).text());		//大单流入(元)
				
				IndividualStockFund cr = new IndividualStockFund();
				cr.setStockCode(stockCode);
				cr.setStockName(stockName);
				cr.setNewPrice(newPrice);
				cr.setUpdownPercent(updownPercent);
				cr.setTurnoverRate(turnoverRate);
				cr.setInFund(inFund);
				cr.setOutFund(outFund);
				cr.setNetAmount(netAmount);
				cr.setAmount(amount);
				cr.setInBigTrade(inBigTrade);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}
	
	private class Runner implements Callable<Unit>{
		
		private int startPageNumber;
		private int endPageNumber;
		public Runner(int startPageNumber, int endPageNumber){
			this.startPageNumber = startPageNumber;
			this.endPageNumber = endPageNumber;
		}

		@Override
		public Unit call() throws Exception {
			return IndividualStockFundCrawler.this.parser(startPageNumber, endPageNumber);
		}
	}
	
	private class Unit{
		private List<IndividualStockFund> data;
		private int totolPage;
		public List<IndividualStockFund> getData() {
			return data;
		}
		public void setData(List<IndividualStockFund> data) {
			this.data = data;
		}
		public int getTotolPage() {
			return totolPage;
		}
		public void setTotolPage(int totolPage) {
			this.totolPage = totolPage;
		}
	}
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
