package org.charles.wechat.handler;

import org.charles.wechat.vo.AbstractMessage;
import org.charles.wechat.vo.EventType;
import org.charles.wechat.vo.MsgType;
import org.charles.wechat.vo.TextMsg;
import org.dom4j.Document;

/**
 * 文本消息处理
 * @author YeChao
 */
public class TextMsgHandler extends AbstractMsgHandler{

	@Override
	protected boolean canHandle(MsgType msgType, EventType eventType) {
		return MsgType.TEXT.equals(msgType);
	}

	@Override
	protected AbstractMessage handleInternal(Document inDoc) {
		TextMsg textMsg = new TextMsg();
		textMsg.parseDom(inDoc);
		textMsg.setContent("撒的发生的发生TextMsgHandler");
		textMsg.exchangeUser(textMsg);
		
		return textMsg;
	}

	
}
