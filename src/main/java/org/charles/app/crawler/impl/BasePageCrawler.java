package org.charles.app.crawler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;
import org.charles.framework.exp.BusinessException;

/**
 * 分页爬取数据抽象类
 * @author YeChao
 * 2017年6月30日
 * @param <T>
 */
public abstract class BasePageCrawler<T> implements UrlCrawler{
	protected Logger logger = Logger.getLogger(getClass());
	private String url;
	
	public List<T> getData() {
		
		int pageNumber = 1;
		int pageSize = 10;
		int endPage = 1;
		
		Unit<T> firstUnit = parser(pageNumber, endPage);
		int totolPage = firstUnit.getTotolPage();
		pageNumber = endPage + 1;
		
		List<Future<Unit<T>>> futureList = new ArrayList<Future<Unit<T>>>();
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
		
		List<T> dataSource = firstUnit.getData();
		try {
			for(Future<Unit<T>> fu : futureList){
					dataSource.addAll(fu.get().getData());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw BusinessException.define(e.getMessage());
		}
		return dataSource;
	}
	
	protected abstract Unit<T> parser(int startPageNumber, int endPageNumber);
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	private class Runner implements Callable<Unit<T>>{
		
		private int startPageNumber;
		private int endPageNumber;
		public Runner(int startPageNumber, int endPageNumber){
			this.startPageNumber = startPageNumber;
			this.endPageNumber = endPageNumber;
		}

		@Override
		public Unit<T> call() throws Exception {
			return BasePageCrawler.this.parser(startPageNumber, endPageNumber);
		}
	}
	
	protected class Unit<K>{
		private List<K> data;
		private int totolPage;
		public List<K> getData() {
			return data;
		}
		public int getTotolPage() {
			return totolPage;
		}
		public void setData(List<K> data) {
			this.data = data;
		}
		public void setTotolPage(int totolPage) {
			this.totolPage = totolPage;
		}
	}
	
}
