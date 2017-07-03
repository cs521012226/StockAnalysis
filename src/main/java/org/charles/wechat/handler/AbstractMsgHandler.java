package org.charles.wechat.handler;

import org.charles.framework.util.StringUtil;
import org.charles.wechat.vo.AbstractMessage;
import org.charles.wechat.vo.EventType;
import org.charles.wechat.vo.MsgType;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 消息处理程序
 * @author YeChao
 */
public abstract class AbstractMsgHandler {
	
	private AbstractMsgHandler next;
	
	public Document handle(Document inDoc){
		Element root = inDoc.getRootElement();
		MsgType msgType = MsgType.get(root.element("MsgType").getTextTrim());	//消息类型
		
		EventType eventType = null;
		Element eventEl = root.element("Event");
		if(eventEl != null && StringUtil.isBlank(eventEl.getTextTrim())){
			eventType = EventType.get(eventEl.getTextTrim());	//事件类型
		}
		
		AbstractMessage msg = null;
		if(canHandle(msgType, eventType)){
			msg = handleInternal(inDoc);
			return msg.buildDom();
		}else if(next != null){
			return next.handle(inDoc);
		}
		return null;
	}
	

	/**
	 * 是否处理
	 * @author YeChao
	 * @param msgType
	 * @param eventType
	 * @return
	 */
	protected abstract boolean canHandle(MsgType msgType, EventType eventType);
	
	/**
	 * 内部处理
	 * @author YeChao
	 * 2017年7月3日
	 * @param inDoc
	 * @return
	 */
	protected abstract AbstractMessage handleInternal(Document inDoc);


	public AbstractMsgHandler getNext() {
		return next;
	}

	public void setNext(AbstractMsgHandler next) {
		this.next = next;
	}
	
}
