package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 视频消息
 * @author YeChao
 */
public class VideoMsg extends AbstractMessage {

	private String mediaId;
	private String thumbMediaId;
	private String msgId;
	
	private String title;
	private String description;
	
	public VideoMsg(){
		super(MsgType.VIDEO);
	}
	
	
	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setMediaId(getCDATA(root, "MediaId"));
		setThumbMediaId(getCDATA(root, "ThumbMediaId"));
		setMsgId(getCDATA(root, "MsgId"));
	}
	
	/**
	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[video]]></MsgType>
<Video>
<MediaId><![CDATA[media_id]]></MediaId>
<Title><![CDATA[title]]></Title>
<Description><![CDATA[description]]></Description>
</Video> 
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
		
		Element videoEl = root.addElement("Video");
		
		videoEl.addElement("MediaId").addCDATA(getMediaId());
		videoEl.addElement("Title").addCDATA(getTitle());
		videoEl.addElement("Description").addCDATA(getDescription());
		return result;
	}


	public String getMediaId() {
		return mediaId;
	}


	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}


	public String getThumbMediaId() {
		return thumbMediaId;
	}


	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
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


}
