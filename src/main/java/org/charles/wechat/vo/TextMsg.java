package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 微信文本消息
 * @author YeChao
 */
public class TextMsg extends AbstractMessage {
	private String content;			//  文本消息内容  
	private String msgId;			//  消息id，64位整型  

	public TextMsg(){
		super(MsgType.TEXT);
	}

	/**
	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好]]></Content>
</xml>
	 */
	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setContent(getCDATA(root, "Content"));
		setMsgId(getCDATA(root, "MsgId"));
	}

	/**
	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好]]></Content>
</xml>
	 */
	@Override
	public Document buildDom() {
		Document result = DocumentHelper.createDocument();
		
		Element root = result.addElement("xml");
		
		root.addElement("ToUserName").addCDATA(getToUserName());
		root.addElement("FromUserName").addCDATA(getFromUserName());
		root.addElement("CreateTime").addText(String.valueOf(getCreateTime()));
		root.addElement("MsgType").addCDATA(getMsgType().getType());
		root.addElement("Content").addCDATA(getContent());
		return result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
