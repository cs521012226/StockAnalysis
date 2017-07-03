package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.charles.app.crawler.UrlCrawler;
import org.charles.app.dao.BoardDataDao;
import org.charles.app.dao.CompanyDao;
import org.charles.app.dao.StockDao;
import org.charles.app.enums.TradeType;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Company;
import org.charles.app.pojo.Stock;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 从网页链接爬取龙虎榜数据
 * @author Charles
 *
 */
public class BoardDataCrawler implements UrlCrawler {
	private static Logger logger = Logger.getLogger(BoardDataCrawler.class);
	
	private String url;		//"http://data.10jqka.com.cn/market/longhu/"
	
	private CompanyDao companyDao;
	private StockDao stockDao;
	private BoardDataDao boardDataDao;
	
	@Override
	public void craw() {
		//删除三个月前的数据
		boardDataDao.deleteBeforeDate(DateUtil.addMonth(new Date(), 3), true);
		
		List<BoardData> data = parser();
		saveData(data);
	}


	public List<BoardData> parser() {
		
		Document doc = HtmlUtil.getDoc(url);
		/**
		 * 解析左边栏
		 */
		Elements stockRow = doc.select(".leftcol .twrap .m-table tr");
		
		Map<String, Stock> stockMap = new HashMap<String, Stock>();
		
		for(Element e : stockRow){
			
			Elements link = e.select("a");
			
			String stockCode = link.attr("stockcode");
			String stockName = link.text();
			
			Stock stock = new Stock(stockCode, stockName);
			
			stockMap.put(stockCode, stock);
		}
		
		/**
		 * 获取日期
		 */
		Elements dateEls = doc.select(".table-tit input.m_text_date.startday");
		String boardDateStr = dateEls.val();
		Date boardDate = DateUtil.convertStringToDate(boardDateStr);

		
		/**
		 * 解析右边栏
		 */
		Elements stockcont = doc.select(".rightcol .stockcont[stockcode]");
		
		List<BoardData> rs = new ArrayList<BoardData>(stockMap.size() * 10);
		
		for(Element e : stockcont){
			String stockCode = e.attr("stockcode");		//股票代码
			String reason = StringUtil.split("\\s*：\\s*", e.child(0).text(), 1);	//上榜原因
			
			Stock stock = stockMap.get(stockCode);
			
			if(stock == null){
				throw BusinessException.define("股票编码不存在");
			}
			
			Elements trs = e.select("table.m-table.m-table-nosort.mt10 tbody");
			
			Element buyList = trs.first();
			Elements row = buyList.select("tr");
			
			setRowData(rs, row, TradeType.BUY, stock, reason, boardDate);
			
			/*for(Element data : row){
				String cmpName = data.select("a").attr("title");
				String buyMoney = data.select("td:eq(1)").text();
				String saleMoney = data.select("td:eq(2)").text();
				
				Elements label = data.select("label");
				String style = label == null ? "" : label.text();
				
				BoardData scb = new BoardData(stock.getStockCode(), stock.getStockName(), cmpName, 
						new BigDecimal(buyMoney), new BigDecimal(saleMoney), BoardData.RankType.BUY);
				scb.setReason(reason);
				scb.setStyle(style);
				rs.add(scb);
			}*/
			
			Element saleList = trs.last();
			row = saleList.select("tr");
			
			setRowData(rs, row, TradeType.SALE, stock, reason, boardDate);
			
			/*for(Element data : row){
				String cmpName = data.select("a").attr("title");
				String buyMoney = data.select("td:eq(1)").text();
				String saleMoney = data.select("td:eq(2)").text();
				
				Elements label = data.select("label");
				String style = label == null ? "" : label.text();
				
				BoardData scb = new BoardData(stock.getStockCode(), stock.getStockName(), cmpName, 
						new BigDecimal(buyMoney), new BigDecimal(saleMoney), BoardData.RankType.SALE);
				scb.setReason(reason);
				scb.setStyle(style);
				rs.add(scb);
			}*/
			
		}
		
		return rs;
	}
	
	public void setRowData(List<BoardData> rs, Elements row, TradeType tradeType, Stock stock, String reason, Date boardDate){
		for(Element data : row){
			String cmpName = StringUtil.trim(data.select("a").attr("title"));
			String buyMoney = StringUtil.trim(data.select("td:eq(1)").text());
			String saleMoney = StringUtil.trim(data.select("td:eq(2)").text());
			
			Elements label = data.select("label");
			String style = StringUtil.trim(label.text());
			
			BoardData scb = new BoardData(stock.getStockCode(), stock.getStockName(), cmpName, 
					new BigDecimal(buyMoney), new BigDecimal(saleMoney), tradeType);
			
			scb.setBoardDate(boardDate);
			scb.setReason(reason);
			scb.setStyle(style);
			rs.add(scb);
		}

	}
	
	/**
	 * 保存数据
	 * @author YeChao
	 * @param data
	 */
	public void saveData(List<BoardData> data){
		
		List<Company> compList = companyDao.findAll(null);
		Map<String, Company> compCache = new HashMap<String, Company>();
		for(Company c : compList){
			compCache.put(c.getCmpName(), c);
		}
		
		List<Stock> stockList = stockDao.findAll(null);
		Map<String, Stock> stockCache = new HashMap<String, Stock>();
		for(Stock c : stockList){
			stockCache.put(c.getStockCode(), c);
		}
		
		for(BoardData bd : data){
			String stockCode = bd.getStockCode();
			String stockName = bd.getStockName();
			String companyName = bd.getCompanyName();
			
			Stock newStock = stockCache.get(stockCode);
			if(newStock == null){
				newStock = new Stock(stockCode, stockName);
				stockDao.save(newStock);
				stockCache.put(stockCode, newStock);
			}
			
			Company newCmp = compCache.get(companyName);
			if(newCmp == null){
				newCmp = new Company();
				newCmp.setCmpName(companyName);
				newCmp.setStyle(bd.getStyle());
				companyDao.save(newCmp);
				compCache.put(companyName, newCmp);
			}
			
			bd.setStockCode(newStock.getStockCode());
			bd.setCmpCode(newCmp.getCmpCode());
			logger.info(bd);
			boardDataDao.save(bd);
			
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public CompanyDao getCompanyDao() {
		return companyDao;
	}


	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}


	public StockDao getStockDao() {
		return stockDao;
	}


	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}


	public BoardDataDao getBoardDataDao() {
		return boardDataDao;
	}


	public void setBoardDataDao(BoardDataDao boardDataDao) {
		this.boardDataDao = boardDataDao;
	}
}
