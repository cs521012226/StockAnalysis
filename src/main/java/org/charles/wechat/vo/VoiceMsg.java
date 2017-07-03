package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 语音消息
 * @author YeChao
 */
public class VoiceMsg extends AbstractMessage {

	private String mediaId;
	private String format;
	private String recognition;
	private String msgId;
	
	public VoiceMsg(){
		super(MsgType.VOICE);
	}
	
	
	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setMediaId(getCDATA(root, "MediaId"));
		setFormat(getCDATA(root, "Format"));
		setRecognition(getCDATA(root, "Recognition"));
		setMsgId(getCDATA(root, "MsgId"));
	}
	
	/**
	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[voice]]></MsgType>
<Voice>
<MediaId><![CDATA[media_id]]></MediaId>
</Voice>
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
		
		Element voiceEl = root.addElement("Voice");
		voiceEl.addElement("MediaId").addCDATA(getMediaId());
		return result;
	}


	public String getMediaId() {
		return mediaId;
	}


	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


	public String getRecognition() {
		return recognition;
	}


	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
}
