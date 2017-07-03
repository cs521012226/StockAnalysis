package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.charles.app.dao.BigTradeDao;
import org.charles.app.pojo.dto.BigTrade;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取大单追踪数据
 * @author YeChao
 * 2017年6月28日
 */
public class BigTradeCrawler extends BasePageCrawler<BigTrade> {
	private static Logger logger = Logger.getLogger(BigTradeCrawler.class);
	
	private BigTradeDao bigTradeDao;

	@Override
	public void craw() {
		bigTradeDao.delete();
		
		List<BigTrade> rs = getData();
		bigTradeDao.saveBatch(rs);
		
	}
	
	public Unit<BigTrade> parser(int startPageNumber, int endPageNumber){
		
		List<BigTrade> rs = new ArrayList<BigTrade>();
		Unit<BigTrade> data = new Unit<BigTrade>();
		
		while(startPageNumber <= endPageNumber){
			String url = String.format(getUrl(), startPageNumber);
			
			Document doc = HtmlUtil.getDoc(url);
			
			String pageInfo = doc.select(".page_info").text();
			if(!StringUtil.isBlank(pageInfo)){
				int totolPage = Integer.valueOf(pageInfo.split("/")[1]);
				data.setTotolPage(totolPage);
			}
			
			Elements row = doc.select("tbody tr");
			
			for(Element r : row){
				int colIndex = 0;
				
				Date tradeDate = DateUtil.convertStringToDate(r.child(colIndex++).text(), DateUtil.Pattern.DATE_TIME.getPattern());	//成交时间
				String stockCode = r.child(colIndex++).child(0).text();	//股票代码
				String stockName = r.child(colIndex++).child(0).text(); 	//股票简称
				BigDecimal price = new BigDecimal(r.child(colIndex++).text());	//成交价格
				Integer volume = Integer.valueOf(r.child(colIndex++).text());	//成交量(股)
				BigDecimal amount = new BigDecimal(r.child(colIndex++).text());	//成交额(万元)
				String type = r.child(colIndex++).text();		//大单性质
				String updownPercent = r.child(colIndex++).text();		//涨跌幅
				BigDecimal updownPrice = new BigDecimal(r.child(colIndex++).text());		//涨跌额
				
				BigTrade cr = new BigTrade();
				cr.setTradeDate(tradeDate);
				cr.setStockCode(stockCode);
				cr.setStockName(stockName);
				cr.setPrice(price);
				cr.setVolume(volume);
				cr.setAmount(amount);
				cr.setType(type);
				cr.setUpdownPercent(updownPercent);
				cr.setUpdownPrice(updownPrice);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}

	public BigTradeDao getBigTradeDao() {
		return bigTradeDao;
	}

	public void setBigTradeDao(BigTradeDao bigTradeDao) {
		this.bigTradeDao = bigTradeDao;
	}
	
}
