package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信信息公共元素
 * @author YeChao
 */
public abstract class AbstractMessage {
	
	private String toUserName;
	private String fromUserName;
	private long createTime = System.currentTimeMillis();
	private MsgType msgType;
	
	public AbstractMessage(MsgType msgType){
		this.msgType = msgType;
	}
	
	/**
	 * 解析
	 * @author YeChao
	 * @param doc
	 */
	public abstract void parseDom(Document doc);
	
	
	public abstract Document buildDom();
	
	
	protected String getCDATA(Element el, String tagName){
		return el.elementTextTrim(tagName);
	}
	
	public void exchangeUser(AbstractMessage msg){
		String temp = toUserName;
		msg.toUserName = fromUserName;
		msg.fromUserName = temp;
		/*
		msg.setFromUserName(temp);
		msg.setToUserName(temp);*/
	}
	
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public MsgType getMsgType() {
		return msgType;
	}
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
}
