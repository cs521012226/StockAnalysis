package org.charles.app.pojo.dto;

import java.math.BigDecimal;

import org.charles.app.enums.Period;

import com.alibaba.fastjson.JSONObject;

/**
 * 概念资金
 * @author YeChao
 * 2017年6月30日
 */
public class ConceptFundRank {

	private String industry;		//行业	
	private Integer companyCount;		//公司家数	
	private BigDecimal industryIndex;		//行业指数	
	private String updownPercent;		//阶段涨跌幅	
	private BigDecimal inFund;		//流入资金(亿)
	private BigDecimal outFund;		//流出资金(亿)	
	private BigDecimal netAmount;	//净额(亿)
	private Period period;	//日期类型
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public Integer getCompanyCount() {
		return companyCount;
	}
	public void setCompanyCount(Integer companyCount) {
		this.companyCount = companyCount;
	}
	
	public BigDecimal getIndustryIndex() {
		return industryIndex;
	}

	public void setIndustryIndex(BigDecimal industryIndex) {
		this.industryIndex = industryIndex;
	}

	public String getUpdownPercent() {
		return updownPercent;
	}
	public void setUpdownPercent(String updownPercent) {
		this.updownPercent = updownPercent;
	}
	public BigDecimal getInFund() {
		return inFund;
	}
	public void setInFund(BigDecimal inFund) {
		this.inFund = inFund;
	}
	public BigDecimal getOutFund() {
		return outFund;
	}
	public void setOutFund(BigDecimal outFund) {
		this.outFund = outFund;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
	
}
