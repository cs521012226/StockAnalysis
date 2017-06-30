package org.charles.app.service;

import java.util.List;

import org.charles.app.pojo.SysParam;
import org.charles.framework.exp.BusinessException;

/**
 * 系统参数服务层
 * @author YeChao
 */
public interface SysParamService {

	public List<SysParam> queryAll(SysParam p) throws BusinessException ;
	
	public void save(SysParam p) throws BusinessException;

	public void delete(String code, String value) throws BusinessException;

	public void update(SysParam p, String code, String value) throws BusinessException;
	
}
