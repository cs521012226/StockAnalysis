package org.charles.app.crawler.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;
import org.charles.app.enums.Period;
import org.charles.app.pojo.dto.CompanyRank;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取营业部排名
 * @author YeChao
 * 2017年6月28日
 */
public class CompanyRankCrawler implements UrlCrawler {
	private static Logger logger = Logger.getLogger(CompanyRankCrawler.class);
	private String url;		//"http://data.10jqka.com.cn/ifmarket/lhbyyb/type/%s/tab/sbcs/field/sbcs/sort/desc/page/%s/";

	@Override
	public void craw() {
		try {
			List<CompanyRank> rs = getData();
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<CompanyRank> getData() throws Exception{
		ExecutorService es = Executors.newFixedThreadPool(3);
		Future<List<CompanyRank>> f1 = es.submit(new Runner(Period.DAY));
		Future<List<CompanyRank>> f2 = es.submit(new Runner(Period.WEEK));
		Future<List<CompanyRank>> f3 = es.submit(new Runner(Period.MONTH));
		es.shutdown();
		
		List<CompanyRank> dataSource = f1.get();
		dataSource.addAll(f2.get());
		dataSource.addAll(f3.get());
		
		Map<String, CompanyRank> dataMap = new LinkedHashMap<String, CompanyRank>();
		
		for(CompanyRank cr : dataSource){
			String cmpName = cr.getCmpName();
			
			if(!dataMap.containsKey(cmpName)){
				dataMap.put(cmpName, cr);
			}
			CompanyRank srcCmpRank = dataMap.get(cmpName);
			if(StringUtil.isBlank(srcCmpRank.getRankCountDay())){
				srcCmpRank.setRankCountDay(cr.getRankCountDay());
			}
			if(StringUtil.isBlank(srcCmpRank.getRankCountWeek())){
				srcCmpRank.setRankCountWeek(cr.getRankCountWeek());
			}
			if(StringUtil.isBlank(srcCmpRank.getRankCountMonth())){
				srcCmpRank.setRankCountMonth(cr.getRankCountMonth());
			}
		}
		
		List<CompanyRank> rs = new ArrayList<CompanyRank>(dataMap.size());
		for(Map.Entry<String, CompanyRank> rc : dataMap.entrySet()){
			rs.add(rc.getValue());
		}
		return rs;
	}
	
	
	private class Runner implements Callable<List<CompanyRank>>{
		
		private Period period;
		public Runner(Period period){
			this.period = period;
		}

		@Override
		public List<CompanyRank> call() throws Exception {
			return CompanyRankCrawler.this.parser(period);
		}
	}
	
	
	public List<CompanyRank> parser(Period period){
		
		String type = null;
		switch (period) {
		case WEEK:
			type = "2";
			break;
		case MONTH:
			type = "3";
			break;
		default:
			type = "1";
			break;
		}
		List<CompanyRank> rs = new ArrayList<CompanyRank>();
		
		int totolPage = -1;
		int pageNumber = 1;
		
		while(totolPage == -1 || pageNumber <= totolPage){
			String url = String.format(this.url, type, pageNumber);
			
			Document doc = HtmlUtil.getDoc(url);
			
			if(totolPage == -1){
				String pageInfo = doc.select(".page_info").text();
				totolPage = Integer.valueOf(pageInfo.split("/")[1]);
			}
			
			Elements row = doc.select("td.tl.rel");
			
			for(Element r : row){
				String cmpName = r.child(0).attr("title");
				String rankCount = r.nextElementSibling().text();
				
				CompanyRank cr = new CompanyRank();
				
				switch (period) {
				case WEEK:
					cr.setCmpName(cmpName);
					cr.setRankCountWeek(rankCount);
					break;
				case MONTH:
					cr.setCmpName(cmpName);
					cr.setRankCountMonth(rankCount);
					break;
				default:
					cr.setCmpName(cmpName);
					cr.setRankCountDay(rankCount);
					break;
				}
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", startPageNumber = " + pageNumber);
			pageNumber++;
		}
		return rs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
