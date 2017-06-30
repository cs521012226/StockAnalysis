package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
public class IndividualStockFundCrawler extends BasePageCrawler<IndividualStockFund> {
	private static Logger logger = Logger.getLogger(IndividualStockFundCrawler.class);
//	private String url = "http://data.10jqka.com.cn/funds/ggzjl/field/zdf/order/desc/page/%s/ajax/1/";

	@Override
	public void craw() {
		try {
			List<IndividualStockFund> rs = getData();
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Unit<IndividualStockFund> parser(int startPageNumber, int endPageNumber){
		
		List<IndividualStockFund> rs = new ArrayList<IndividualStockFund>();
		Unit<IndividualStockFund> data = new Unit<IndividualStockFund>();
		
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
				int colIndex = 1;
				String stockCode = r.child(colIndex++).child(0).text();	//股票代码	
				String stockName = r.child(colIndex++).child(0).text(); 	//股票简称	
				BigDecimal newPrice = new BigDecimal(r.child(colIndex++).text());	//最新价	
				String updownPercent = r.child(colIndex++).text();		//涨跌幅
				String turnoverRate = r.child(colIndex++).text();		//换手率	
				BigDecimal inFund = convertUnit(r.child(colIndex++).text());		//流入资金(元)	
				BigDecimal outFund = convertUnit(r.child(colIndex++).text());		//流出资金(元)	
				BigDecimal netAmount = convertUnit(r.child(colIndex++).text());	//净额(元)	
				BigDecimal amount = convertUnit(r.child(colIndex++).text());		//成交额(元)	
				BigDecimal inBigTrade = convertUnit(r.child(colIndex++).text());		//大单流入(元)
				
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
	
	private BigDecimal convertUnit(String text){
		BigDecimal rs = new BigDecimal(text.replaceAll("万|亿", ""));
		if(text.contains("亿")){
			rs = new BigDecimal(rs.intValue() * 10000);
		}
		return rs;
	}
}
