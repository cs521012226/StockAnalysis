package org.charles.wechat.vo;

/**
 * 消息类型
 * @author YeChao
 */
public enum MsgType {

	/**
	 * 文本消息
	 */
	TEXT("text"),
	
	/**
	 * 图片消息
	 */
	IMAGE("image"),
	
	/**
	 * 语音消息
	 */
	VOICE("voice"),
	
	/**
	 * 视频消息
	 */
	VIDEO("video"),
	/**
	 * 图文消息
	 */
	NEWS("news"),
	
	/**
	 * 小视频消息
	 */
	SHORT_VIDEO("shortvideo"),
	
	/**
	 * 地理位置消息
	 */
	LOCATION("location"),
	
	/**
	 * 链接消息
	 */
	LINK("link"),
	
	/**
	 * 事件类型
	 */
	EVENT("event");
	
	private String type;
	
	MsgType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	/**
	 * 类型判断
	 * @param type
	 * @return
	 */
	public boolean isType(String type){
		return this.type.equalsIgnoreCase(type);
	}

	/**
	 * 获取类型数据
	 * @param type
	 * @return
	 */
	public static MsgType get(String type){
		for(MsgType t : MsgType.values()){
			if(t.type.equalsIgnoreCase(type)){
				return t;
			}
		}
		return null;
	}
}
