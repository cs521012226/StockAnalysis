package org.charles.app.crawler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.charles.app.enums.Period;
import org.charles.app.pojo.dto.ConceptFundRank;
import org.charles.app.util.HtmlUtil;
import org.charles.framework.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取概念资金排行数据（目前只支持3日排行）
 * @author YeChao
 * 2017年6月28日
 */
public class ConceptFundRankCrawler extends BasePageCrawler<ConceptFundRank> {
	private static Logger logger = Logger.getLogger(ConceptFundRankCrawler.class);

	private Period period = Period.DAY_3;
	
	@Override
	public void craw() {
		try {
			List<ConceptFundRank> rs = getData();
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Unit<ConceptFundRank> parser(int startPageNumber, int endPageNumber){
		
		List<ConceptFundRank> rs = new ArrayList<ConceptFundRank>();
		Unit<ConceptFundRank> data = new Unit<ConceptFundRank>();
		
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
				String industry = r.child(colIndex++).child(0).text();		//行业	
				Integer companyCount = Integer.valueOf(r.child(colIndex++).text());		//公司家数	
				BigDecimal industryIndex = new BigDecimal(r.child(colIndex++).text());		//行业指数	
				String updownPercent = r.child(colIndex++).text();		//阶段涨跌幅	
				BigDecimal inFund = new BigDecimal(r.child(colIndex++).text());		//流入资金(亿)
				BigDecimal outFund = new BigDecimal(r.child(colIndex++).text());		//流出资金(亿)	
				BigDecimal netAmount = new BigDecimal(r.child(colIndex++).text());	//净额(亿)
				
				ConceptFundRank cr = new ConceptFundRank();
				cr.setIndustry(industry);
				cr.setCompanyCount(companyCount);
				cr.setIndustryIndex(industryIndex);
				cr.setUpdownPercent(updownPercent);
				cr.setInFund(inFund);
				cr.setOutFund(outFund);
				cr.setNetAmount(netAmount);
				
				rs.add(cr);
			}
			logger.info(Thread.currentThread().getId() + ", startPageNumber = " + startPageNumber);
			startPageNumber++;
		}
		data.setData(rs);
		return data;
	}
	
	private String getType(){
		switch (period) {
		case DAY_5:
			return "5";
		case DAY_10:
			return "10";
		case DAY_20:
			return "20";
		default:
			return "3";
		}
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriodStr(String period) {
		this.period = Period.get(period);
	}
	
}
