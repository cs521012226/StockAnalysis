package org.charles.wechat.handler;

import org.charles.wechat.vo.AbstractMessage;
import org.charles.wechat.vo.EventType;
import org.charles.wechat.vo.MsgType;
import org.charles.wechat.vo.TextMsg;
import org.dom4j.Document;

/**
 * 关注事件
 * @author YeChao
 */
public class SubscribeEventHandler extends AbstractMsgHandler{

	@Override
	protected boolean canHandle(MsgType msgType, EventType eventType) {
		return EventType.SUBSCRIBE.equals(eventType);
	}

	@Override
	protected AbstractMessage handleInternal(Document inDoc) {
		TextMsg textMsg = new TextMsg();
		textMsg.parseDom(inDoc);
		textMsg.setContent("被人关注了SubscribeEventHandler");
		textMsg.exchangeUser(textMsg);
		
		System.out.println("被人关注了SubscribeEventHandler");
		
		return textMsg;
//		return null;
	}

	
}
