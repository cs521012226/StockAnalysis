package org.charles.app.crawler.goodnews.vo;

public class LinkMsg {
	private String text;
	private String visitUrl;
	
	public LinkMsg(String text, String visitUrl) {
		this.text = text;
		this.visitUrl = visitUrl;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getVisitUrl() {
		return visitUrl;
	}
	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}
	
}
