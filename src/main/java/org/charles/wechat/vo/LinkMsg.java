package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 视频消息
 * @author YeChao
 */
public class LinkMsg extends AbstractMessage {

	private String title;
	private String description;
	private String url;
	private String msgId;
	
	public LinkMsg(){
		super(MsgType.LINK);
	}
	
	
	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setTitle(getCDATA(root, "Title"));
		setDescription(getCDATA(root, "Description"));
		setUrl(getCDATA(root, "Url"));
		setMsgId(getCDATA(root, "MsgId"));
	}
	@Override
	public Document buildDom() {
		/*Document result = DocumentHelper.createDocument();
		
		Element root = result.addElement("xml");
		
		root.addElement("ToUserName").addCDATA(getToUserName());
		root.addElement("FromUserName").addCDATA(getFromUserName());
		root.addElement("CreateTime").addText(String.valueOf(getCreateTime()));
		root.addElement("MsgType").addCDATA(getMsgType().getType());
		root.addElement("PicUrl").addCDATA(getPicUrl());
		root.addElement("MediaId").addCDATA(getMediaId());
		root.addElement("MsgId").addCDATA(getMsgId());
		return result;*/
		return null;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


}
