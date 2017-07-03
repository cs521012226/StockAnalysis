package org.charles.app.pojo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 日志实体
 * @author YeChao
 * 2017年7月3日
 */
public class OprLogs {
	private String logType;	/*类型*/
	private String code;	/*异常码*/
	private String content;	/*内容*/
	private Date createDate;	/*日期*/
	
	public OprLogs(String logType, String code, String content){
		this.logType = logType;
		this.code = code;
		this.content = content;
		this.createDate = new Date();
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}


	public String getLogType() {
		return logType;
	}


	public void setLogType(String logType) {
		this.logType = logType;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
