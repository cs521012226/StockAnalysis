package org.charles.app.pojo.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 机构排行榜
 * @author YeChao
 * 2017年6月28日
 */
public class CompanyRank {

	private String cmpName;		//机构名称
	private String rankCountDay;	//今日上榜次数
	private String rankCountWeek;	//近一周上榜次数
	private String rankCountMonth;	//近一月上榜次数
	
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

	public String getRankCountDay() {
		return rankCountDay;
	}

	public void setRankCountDay(String rankCountDay) {
		this.rankCountDay = rankCountDay;
	}

	public String getRankCountWeek() {
		return rankCountWeek;
	}

	public void setRankCountWeek(String rankCountWeek) {
		this.rankCountWeek = rankCountWeek;
	}

	public String getRankCountMonth() {
		return rankCountMonth;
	}

	public void setRankCountMonth(String rankCountMonth) {
		this.rankCountMonth = rankCountMonth;
	}
	
}
