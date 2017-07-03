package org.charles.wechat.vo;

/**
 * 推送消息类型
 * @author YeChao
 */
public enum EventType {
	/**
	 * 关注公众号事件
	 */
	SUBSCRIBE("subscribe"),
	/**
	 * 取消关注公众号事件
	 */
	UNSUBSCRIBE("unsubscribe"),
	/**
	 * 用户已关注后扫描二维码事件
	 */
	SCAN("SCAN"),
	/**
	 * 上报地理位置事件
	 */
	LOCATION("LOCATION"),
	
	/**
	 * 点击菜单时推送事件
	 */
	CLICK("CLICK"),
	/**
	 * 点击菜单跳转链接时推送事件
	 */
	VIEW("VIEW");
	
	private String type;
	
	EventType(String type){
		this.type = type;
	}
	
	/**
	 * 获取类型数据
	 * @param type
	 * @return
	 */
	public static EventType get(String type){
		for(EventType t : EventType.values()){
			if(t.type.equalsIgnoreCase(type)){
				return t;
			}
		}
		return null;
	}
	
	/**
	 * 类型判断
	 * @param type
	 * @return
	 */
	public boolean isType(String type){
		return this.type.equalsIgnoreCase(type);
	}

}
