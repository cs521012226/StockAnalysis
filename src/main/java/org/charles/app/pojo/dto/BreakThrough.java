package org.charles.app.pojo.dto;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;

/**
 * 向上突破的股票实体
 * @author YeChao
 * 2017年7月4日
 */
public class BreakThrough {

	private String stockCode;	//股票代码	
	private String stockName;	//股票简称	
	private BigDecimal newPrice;		//最新价（元）	
	private BigDecimal amount;	//成交额（元）	
	private Integer volume;		//成交量（股）	
	private String updownPercent;		//涨跌幅（%）
	private String turnoverRate;		//换手率（%）
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public BigDecimal getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public String getUpdownPercent() {
		return updownPercent;
	}
	public void setUpdownPercent(String updownPercent) {
		this.updownPercent = updownPercent;
	}
	public String getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(String turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
}
