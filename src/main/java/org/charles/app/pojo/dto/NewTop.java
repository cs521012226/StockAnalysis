package org.charles.app.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 创新高
 * @author YeChao
 * 2017年6月28日
 */
public class NewTop {
	private String stockCode;	//股票代码	
	private String stockName;	//股票简称	
	private String updownPercent;		//涨跌幅	
	private String turnoverRate;		//换手率	
	private BigDecimal newPrice;		//最新价(元)	
	private BigDecimal prevTop;		//前期高点	
	private Date prevTopDate;		//前期高点日期
	
	
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




	public BigDecimal getNewPrice() {
		return newPrice;
	}


	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}


	public BigDecimal getPrevTop() {
		return prevTop;
	}


	public void setPrevTop(BigDecimal prevTop) {
		this.prevTop = prevTop;
	}


	public Date getPrevTopDate() {
		return prevTopDate;
	}


	public void setPrevTopDate(Date prevTopDate) {
		this.prevTopDate = prevTopDate;
	}
	
}
