package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.charles.app.dao.BreakThroughDao;
import org.charles.app.enums.Period;
import org.charles.app.pojo.dto.BreakThrough;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.NumberUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 向上突破的股票数据
 * @author YeChao
 * 2017年6月28日
 */
public class BreakThroughCrawler extends BasePageCrawler<BreakThrough> {
	private static Logger logger = Logger.getLogger(BreakThroughCrawler.class);
	
	private Period period;
	private BreakThroughDao breakThroughDao;

	@Override
	public void craw() {
		breakThroughDao.deleteBeforeDate(DateUtil.addDay(new Date(), 3), true);
		
		saveUnit(Period.DAY_10);
		saveUnit(Period.DAY_20);
		saveUnit(Period.DAY_30);
		saveUnit(Period.DAY_60);
		saveUnit(Period.DAY_90);
	}
	
	@Override
	public Unit<BreakThrough> parser(int startPageNumber, int endPageNumber){
		
		List<BreakThrough> rs = new ArrayList<BreakThrough>();
		Unit<BreakThrough> data = new Unit<BreakThrough>();
		
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
				BigDecimal newPrice = NumberUtil.convertUnit(r.child(colIndex++).text(), NumberUtil.Unit.YUAN);		//最新价（元）	
				BigDecimal amount = NumberUtil.convertUnit(r.child(colIndex++).text(), NumberUtil.Unit.WANG);	//成交额（万）	
				BigDecimal volume = NumberUtil.convertUnit(r.child(colIndex++).text(), NumberUtil.Unit.YUAN);		//成交量（股）	
				String updownPercent = StringUtil.trim(r.child(colIndex++).text());		//涨跌幅（%）
				String turnoverRate = StringUtil.trim(r.child(colIndex++).text());		//换手率（%）
				
				BreakThrough cr = new BreakThrough();
				cr.setStockCode(stockCode);
				cr.setStockName(stockName);
				cr.setNewPrice(newPrice);
				cr.setAmount(amount);
				cr.setVolume(volume.intValue());
				cr.setUpdownPercent(updownPercent);
				cr.setTurnoverRate(turnoverRate);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", period = " + period + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}
	
	public BreakThroughDao getBreakThroughDao() {
		return breakThroughDao;
	}

	public void setBreakThroughDao(BreakThroughDao breakThroughDao) {
		this.breakThroughDao = breakThroughDao;
	}

	private void saveUnit(Period period){
		this.period = period;
		List<BreakThrough> rs = getData();
		breakThroughDao.saveBatch(rs);
	}

	private String getType(){
		switch (period) {
		case DAY_10:
			return "10";
		case DAY_20:
			return "20";
		case DAY_30:
			return "30";
		case DAY_60:
			return "60";
		case DAY_90:
			return "90";
		default:
			return "5";
		}
	}
}
