package org.charles.app.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.charles.app.enums.TradeType;

import com.alibaba.fastjson.JSONObject;

/**
 * 股票龙虎榜实体
 * @author Charles
 *
 */
public class BoardData {
	
	private String stockCode;  //股票代码
	private String cmpCode;  //股票名字
	private String reason;  //上榜原因
	private BigDecimal buyMoney;  //买入额（万）
	private BigDecimal saleMoney;  //卖出额（万）
	private String rankType;  //排名类型
	private Date boardDate;  //上榜日期
	private Date createDate;  //添加日期
	private String stockName; 		//股票名字
	private String companyName;		//机构名称
	private String style;		//机构风格
	private int cmpCount;		//机构数量
	
	private Integer rankCountD;		//日内排名
	private Integer rankCountW;		//周内排名
	
	private BigDecimal moneyPercent;
	
	private Integer cmpCountB;		//买的机构数量
	private Integer cmpCountS;		//卖出机构数量
	private BigDecimal buyMoneyB;  //买入额（万）
	private BigDecimal saleMoneyB;  //卖出额（万）
	private BigDecimal buyMoneyS;  //买入额（万）
	private BigDecimal saleMoneyS;  //卖出额（万）
	public BoardData(){
		
	}
	
	public BoardData(String stockCode, String stockName, String companyName, BigDecimal buyMoney, BigDecimal saleMoney, TradeType tradeType){
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.companyName = companyName;
		this.buyMoney = buyMoney;
		this.saleMoney = saleMoney;
		this.rankType = tradeType.toString();
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public BigDecimal getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}



	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}

	public String getCmpCode() {
		return cmpCode;
	}


	public void setCmpCode(String cmpCode) {
		this.cmpCode = cmpCode;
	}


	public String getRankType() {
		return rankType;
	}


	public void setRankType(String rankType) {
		this.rankType = rankType;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getCmpCount() {
		return cmpCount;
	}

	public void setCmpCount(int cmpCount) {
		this.cmpCount = cmpCount;
	}

	public BigDecimal getBuyMoneyB() {
		return buyMoneyB;
	}

	public void setBuyMoneyB(BigDecimal buyMoneyB) {
		this.buyMoneyB = buyMoneyB;
	}

	public BigDecimal getSaleMoneyB() {
		return saleMoneyB;
	}

	public void setSaleMoneyB(BigDecimal saleMoneyB) {
		this.saleMoneyB = saleMoneyB;
	}

	public BigDecimal getBuyMoneyS() {
		return buyMoneyS;
	}

	public void setBuyMoneyS(BigDecimal buyMoneyS) {
		this.buyMoneyS = buyMoneyS;
	}

	public BigDecimal getSaleMoneyS() {
		return saleMoneyS;
	}

	public void setSaleMoneyS(BigDecimal saleMoneyS) {
		this.saleMoneyS = saleMoneyS;
	}

	public Integer getRankCountD() {
		return rankCountD;
	}

	public void setRankCountD(Integer rankCountD) {
		this.rankCountD = rankCountD;
	}

	public Integer getRankCountW() {
		return rankCountW;
	}

	public void setRankCountW(Integer rankCountW) {
		this.rankCountW = rankCountW;
	}

	public Integer getCmpCountB() {
		return cmpCountB;
	}

	public void setCmpCountB(Integer cmpCountB) {
		this.cmpCountB = cmpCountB;
	}

	public Integer getCmpCountS() {
		return cmpCountS;
	}

	public void setCmpCountS(Integer cmpCountS) {
		this.cmpCountS = cmpCountS;
	}

	public BigDecimal getMoneyPercent() {
		return moneyPercent;
	}

	public void setMoneyPercent(BigDecimal moneyPercent) {
		this.moneyPercent = moneyPercent;
	}

}
