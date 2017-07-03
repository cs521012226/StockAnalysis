package org.charles.app.enums;

/**
 * 日志类型
 * @author YeChao
 * 2017年7月3日
 */
public enum LogType {
	EXCEPTION("EXP_ERROR"),
	OPERATION("OPR_ERROR");
	private String name;
	
	LogType(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
