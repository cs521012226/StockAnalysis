package org.charles.app.pojo.dto;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;

/**
 * 个股资金流向
 * @author YeChao
 * 2017年6月28日
 */
public class IndividualStockFund {

	private String stockCode;	//股票代码	
	private String stockName; 	//股票简称	
	private BigDecimal newPrice;	//最新价	
	private String updownPercent;		//涨跌幅
	private String turnoverRate;		//换手率	
	private BigDecimal inFund;		//流入资金(元)	
	private BigDecimal outFund;		//流出资金(元)	
	private BigDecimal netAmount;	//净额(元)	
	private BigDecimal amount;		//成交额(元)	
	private BigDecimal inBigTrade;		//大单流入(元)
	
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getInBigTrade() {
		return inBigTrade;
	}

	public void setInBigTrade(BigDecimal inBigTrade) {
		this.inBigTrade = inBigTrade;
	}
}
