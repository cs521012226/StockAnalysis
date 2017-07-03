package org.charles.wechat.vo;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 自定义菜单事件
 * @author YeChao
 */
public class MenuEvent extends AbstractMessage {
	private EventType event;			//  事件类型，CLICK、VIEW
	private String eventKey;			//  当Event是CLICK时：事件KEY值，与自定义菜单接口中KEY值对应；当Event是VIEW时：  事件KEY值，设置的跳转URL 
	private String menuId;				//
	
	public MenuEvent(){
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
		setEventKey(getCDATA(root, "EventKey"));
		setMenuId(getCDATA(root, "MenuId"));
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

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
