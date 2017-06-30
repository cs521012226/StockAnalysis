package org.charles.app.pojo.dto;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;

/**
 * 大单追踪
 * @author YeChao
 * 2017年6月28日
 */
public class BigTrade {

	private String tradeDate;	//成交时间
	private String stockCode;	//股票代码
	private String stockName; 	//股票简称
	private BigDecimal price;	//成交价格
	private Integer volume;	//成交量(股)
	private BigDecimal amount;	//成交额(万元)
	private String type;		//大单性质
	private String updownPercent;		//涨跌幅
	private String updownPrice;		//涨跌额
	
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}


	public String getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
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


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getVolume() {
		return volume;
	}


	public void setVolume(Integer volume) {
		this.volume = volume;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUpdownPercent() {
		return updownPercent;
	}


	public void setUpdownPercent(String updownPercent) {
		this.updownPercent = updownPercent;
	}


	public String getUpdownPrice() {
		return updownPrice;
	}


	public void setUpdownPrice(String updownPrice) {
		this.updownPrice = updownPrice;
	}
}
