package org.charles.app.crawler.goodnews.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 利好消息实体
 * @author Ych
 * 2017年12月1日
 */
public class GoodNewsMsg implements Comparable<GoodNewsMsg>{
	
	private LinkMsg title;
	private String releaseTime;
	private List<LinkMsg> stockMsgList;
	
	public GoodNewsMsg(String title, String releaseTime, String url){
		this.title = new LinkMsg(title, url);
		this.releaseTime = releaseTime;
	}
	
	public void addStockMsg(String stockName, String visitUrl){
		if(stockMsgList == null){
			stockMsgList = new ArrayList<LinkMsg>();
		}
		stockMsgList.add(new LinkMsg(stockName, visitUrl));
	}

	public LinkMsg getTitle() {
		return title;
	}
	public String getReleaseTime() {
		return releaseTime;
	}

	public List<LinkMsg> getStockMsg() {
		return stockMsgList;
	}

	@Override
	public int compareTo(GoodNewsMsg o) {
		if(o != null && o.releaseTime != null){
			return o.releaseTime.compareTo(releaseTime);
		}
		return 0;
	}
	
}
