package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.charles.app.dao.NewTopDao;
import org.charles.app.enums.Period;
import org.charles.app.pojo.dto.NewTop;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取创新高股票数据
 * @author YeChao
 * 2017年6月28日
 */
public class NewTopCrawler extends BasePageCrawler<NewTop> {
	private static Logger logger = Logger.getLogger(NewTopCrawler.class);
	
	private Period period;
	private NewTopDao newTopDao;

	@Override
	protected void process(){
		newTopDao.deleteBeforeDate(DateUtil.addMonth(new Date(), 3), true);
		
		period = Period.MONTH;
		List<NewTop> rs = getData();
		saveData(rs);
		
		period = Period.HALF_YEAR;
		rs = getData();
		saveData(rs);
		
		period = Period.YEAR;
		rs = getData();
		saveData(rs);
		
		period = Period.HIS;
		rs = getData();
		saveData(rs);
	}
	
	@Override
	public Unit<NewTop> parser(int startPageNumber, int endPageNumber){
		
		List<NewTop> rs = new ArrayList<NewTop>();
		Unit<NewTop> data = new Unit<NewTop>();
		
		String type = getType();
		
		while(startPageNumber <= endPageNumber){
			String url = String.format(getUrl(), type, startPageNumber);
			
			Document doc = HtmlUtil.getDoc(url);
			
			String pageInfo = doc.select(".page_info").text();
			if(!StringUtil.isBlank(pageInfo)){
				int totolPage = Integer.valueOf(pageInfo.split("/")[1]);
				data.setTotolPage(totolPage);
			}
			
			Elements row = doc.select("tbody tr");
			
			for(Element r : row){
				int colIndex = 1;
				String stockCode = StringUtil.trim(r.child(colIndex++).child(0).text());	//股票代码	
				String stockName = StringUtil.trim(r.child(colIndex++).child(0).text());	//股票简称	
				String updownPercent = StringUtil.trim(r.child(colIndex++).text());		//涨跌幅	
				String turnoverRate = StringUtil.trim(r.child(colIndex++).text());		//换手率	
				BigDecimal newPrice = new BigDecimal(r.child(colIndex++).text());		//最新价(元)	
				BigDecimal prevTop = new BigDecimal(r.child(colIndex++).text());		//前期高点	
				Date prevTopDate = DateUtil.convertStringToDate(r.child(colIndex++).text());		//前期高点日期
				
				NewTop cr = new NewTop();
				cr.setStockCode(stockCode);
				cr.setStockName(stockName);
				cr.setUpdownPercent(updownPercent);
				cr.setTurnoverRate(turnoverRate);
				cr.setNewPrice(newPrice);
				cr.setPrevTop(prevTop);
				cr.setPrevTopDate(prevTopDate);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", period = " + period + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}
	
	public void saveData(List<NewTop> data){
		newTopDao.saveBatch(data);
	}
	
	public NewTopDao getNewTopDao() {
		return newTopDao;
	}

	public void setNewTopDao(NewTopDao newTopDao) {
		this.newTopDao = newTopDao;
	}

	private String getType(){
		switch (period) {
		case MONTH:
			return "4";
		case HALF_YEAR:
			return "3";
		case YEAR:
			return "2";
		case HIS:
			return "1";
		default:
			return "4";
		}
	}
}
