package org.charles.app.pojo.dto;

import java.math.BigDecimal;

import org.charles.app.enums.Period;

import com.alibaba.fastjson.JSONObject;

/**
 * 机构排行榜
 * @author YeChao
 * 2017年6月28日
 */
public class CompanyRank {
	private String cmpCode;		//营业部代码
	private String cmpName;		//营业部名称
	private String style;		//操作风格
	private Integer rankCount;	//上榜次数
	private BigDecimal amount;		//合计动用资金（万）
	private Integer rankCountYear;	//年内上榜次数
	private Integer buyStockCount;		//年内买入股票只数
	private Period period;	//日期类型
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getCmpName() {
		return cmpName;
	}
	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}


	public Integer getRankCount() {
		return rankCount;
	}

	public void setRankCount(Integer rankCount) {
		this.rankCount = rankCount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getRankCountYear() {
		return rankCountYear;
	}

	public void setRankCountYear(Integer rankCountYear) {
		this.rankCountYear = rankCountYear;
	}

	public Integer getBuyStockCount() {
		return buyStockCount;
	}

	public void setBuyStockCount(Integer buyStockCount) {
		this.buyStockCount = buyStockCount;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getCmpCode() {
		return cmpCode;
	}

	public void setCmpCode(String cmpCode) {
		this.cmpCode = cmpCode;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
