package org.charles.app.pojo;

import com.alibaba.fastjson.JSONObject;

/**
 * 营业部、机构
 * @author Charles
 *
 */
public class Company {
	
	private String cmpCode;  //代码
	private String cmpName;  //名字
	private String style;  //机构风格
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (cmpName == null ? 0 : cmpName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof Company)) return false;
		
		Company t = (Company) other;
		return (this.cmpName == null ? this.cmpName == t.cmpName : this.cmpName.equals(t.cmpName));
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	public String getCmpCode() {
		return cmpCode;
	}
	public void setCmpCode(String cmpCode) {
		this.cmpCode = cmpCode;
	}
	public String getCmpName() {
		return cmpName;
	}
	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
