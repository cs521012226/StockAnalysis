package org.charles.app.service;

import java.util.List;

import org.charles.app.pojo.SysParam;

/**
 * 获取系统参数接口
 * @author YeChao
 */
public interface SysParamQuery {

	/**
	 * 获取并构建参数信息
	 * @author YeChao
	 * @return
	 */
	public List<SysParam> querySysParamList();
}
