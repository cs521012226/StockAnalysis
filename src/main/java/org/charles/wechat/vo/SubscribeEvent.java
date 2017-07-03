package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 关注/取消关注 公众号实体
 * @author YeChao
 */
public class SubscribeEvent extends AbstractMessage {
	
	private EventType event;			//  事件类型，subscribe(订阅)、unsubscribe(取消订阅)  
	
	public SubscribeEvent(){
		super(MsgType.EVENT);
	}

	@Override
	public void parseDom(Document doc) {
		Element root = doc.getRootElement();
		
		setToUserName(getCDATA(root, "ToUserName"));
		setFromUserName(getCDATA(root, "FromUserName"));
		setCreateTime(Integer.valueOf(getCDATA(root, "CreateTime")));
		setMsgType(MsgType.get(getCDATA(root, "MsgType")));
		setEvent(EventType.get(getCDATA(root, "Event")));
	}

	@Override
	public Document buildDom() {
		throw new UnsupportedOperationException();
	}

	public EventType getEvent() {
		return event;
	}

	public void setEvent(EventType event) {
		this.event = event;
	}
	
}
