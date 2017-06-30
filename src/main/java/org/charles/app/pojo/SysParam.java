package org.charles.app.pojo;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统参数
 * @author Charles
 *
 */
public class SysParam {
	
	private String code;  //
	private String value;  //
	private String text;  //
	
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getIntValue(){
		return Integer.valueOf(value);
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
