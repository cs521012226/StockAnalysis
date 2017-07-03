package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 地理位置消息
 * @author YeChao
 */
public class LocationMsg extends AbstractMessage {

	private String locationX;
	private String locationY;
	private String scale;
	private String label;
	private String msgId;
	
	public LocationMsg(){
		super(MsgType.LOCATION);
	}
	
	
	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setLocationX(getCDATA(root, "Location_X"));
		setLocationY(getCDATA(root, "Location_Y"));
		setScale(getCDATA(root, "Scale"));
		setLabel(getCDATA(root, "Label"));
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


	public String getLocationX() {
		return locationX;
	}


	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}


	public String getLocationY() {
		return locationY;
	}


	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}


	public String getScale() {
		return scale;
	}


	public void setScale(String scale) {
		this.scale = scale;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
