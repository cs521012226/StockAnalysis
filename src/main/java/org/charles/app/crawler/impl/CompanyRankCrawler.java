package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.charles.app.dao.CompanyDao;
import org.charles.app.dao.CompanyRankDao;
import org.charles.app.enums.Period;
import org.charles.app.pojo.Company;
import org.charles.app.pojo.dto.CompanyRank;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.DateUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取营业部排名
 * @author YeChao
 * 2017年6月28日
 */
public class CompanyRankCrawler extends BasePageCrawler<CompanyRank> {
	private static Logger logger = Logger.getLogger(CompanyRankCrawler.class);
	
	private CompanyRankDao companyRankDao;
	private CompanyDao companyDao;
	private Period period;

	@Override
	protected void process(){
		companyRankDao.deleteBeforeDate(DateUtil.addMonth(new Date(), 1), true);
		
		List<Company> compList = companyDao.findAll(null);
		Map<String, Company> compCache = new HashMap<String, Company>();
		for(Company c : compList){
			compCache.put(c.getCmpName(), c);
		}
		saveUnit(Period.DAY, compCache);
		saveUnit(Period.WEEK, compCache);
		saveUnit(Period.MONTH, compCache);
	}
	
	public Unit<CompanyRank> parser(int startPageNumber, int endPageNumber){
		
		List<CompanyRank> rs = new ArrayList<CompanyRank>();
		Unit<CompanyRank> data = new Unit<CompanyRank>();
		
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
				
				Element cmpEl = r.child(colIndex++);
				String cmpName = StringUtil.trim(cmpEl.child(0).attr("title"));		//营业部名称
				String style = StringUtil.trim(cmpEl.select("label").text());		//操作风格
				Integer rankCount = Integer.valueOf(r.child(colIndex++).text());	//上榜次数
				BigDecimal amount = convertUnit(r.child(colIndex++).text());		//合计动用资金
				Integer rankCountYear = Integer.valueOf(r.child(colIndex++).text());	//年内上榜次数
				Integer buyStockCount = Integer.valueOf(r.child(colIndex++).text());		//年内买入股票只数
				
				CompanyRank cr = new CompanyRank();
				cr.setCmpName(cmpName);
				cr.setStyle(style);
				cr.setRankCount(rankCount);
				cr.setAmount(amount);
				cr.setRankCountYear(rankCountYear);
				cr.setBuyStockCount(buyStockCount);
				cr.setPeriod(period);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", period = " + period + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}
	
	private void saveUnit(Period period, Map<String, Company> compCache){
		this.period = period;
		List<CompanyRank> data = getData();
		for(CompanyRank cr : data){
			Company newCmp = compCache.get(cr.getCmpName());
			if(newCmp == null){
				newCmp = new Company();
				newCmp.setCmpName(cr.getCmpName());
				newCmp.setStyle(cr.getStyle());
				companyDao.save(newCmp);
				compCache.put(cr.getCmpName(), newCmp);
			}
		}
		companyRankDao.saveBatch(data);
	}

	private BigDecimal convertUnit(String text){
		BigDecimal rs = new BigDecimal(text.replaceAll("万|亿", ""));
		if(text.contains("亿")){
			rs = new BigDecimal(rs.doubleValue() * 10000);
		}
		return rs;
	}

	private String getType(){
		switch (period) {
		case DAY:
			return "1";
		case WEEK:
			return "2";
		case MONTH:
			return "3";
		default:
			return "1";
		}
	}

	public CompanyRankDao getCompanyRankDao() {
		return companyRankDao;
	}

	public void setCompanyRankDao(CompanyRankDao companyRankDao) {
		this.companyRankDao = companyRankDao;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
}
