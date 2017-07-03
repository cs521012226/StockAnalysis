package org.charles.app.pojo;

import com.alibaba.fastjson.JSONObject;

/**
 * 股票实体
 * @author Charles
 *
 */
public class Stock {
	
	/**
	 * 机构专用编号
	 * @Description: 
	 */
	public static final String SPECIAL_ORG_KEY = "f059330f43712b1226e217ba8484211e";
	
	private String stockCode;  //代码
	private String stockName;  //名字

	public Stock(){
		
	}
	
	public Stock(String stockCode, String stockName){
		this.stockCode = stockCode;
		this.stockName = stockName;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (stockCode == null ? 0 : stockCode.hashCode());
		result = 37 * result + (stockName == null ? 0 : stockName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof Stock)) return false;
		
		Stock t = (Stock) other;
		return (this.stockCode == null ? this.stockCode == t.stockCode : this.stockCode.equals(t.stockCode))
				&& (this.stockName == null ? this.stockName == t.stockName : this.stockName.equals(t.stockName));
	}

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
	
}
