package org.charles.wechat.handler;

import org.charles.wechat.vo.AbstractMessage;
import org.charles.wechat.vo.EventType;
import org.charles.wechat.vo.ImageMsg;
import org.charles.wechat.vo.MsgType;
import org.charles.wechat.vo.TextMsg;
import org.dom4j.Document;

/**
 * 文本消息处理
 * @author YeChao
 */
public class ImageMsgHandler extends AbstractMsgHandler{

	@Override
	protected boolean canHandle(MsgType msgType, EventType eventType) {
		return MsgType.IMAGE.equals(msgType);
	}

	@Override
	protected AbstractMessage handleInternal(Document inDoc) {
		ImageMsg msg = new ImageMsg();
		msg.parseDom(inDoc);
		
		
		TextMsg tmsg = new TextMsg();
		tmsg.setContent("你大爷啊ImageMsgHandler");
		
		msg.exchangeUser(tmsg);
		
		return tmsg;
	}

	
}
