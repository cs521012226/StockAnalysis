package org.charles.app.dao;

import java.util.List;

import org.charles.app.pojo.SysParam;
import org.charles.framework.define.VOBase;
import org.charles.framework.exp.BusinessException;

/**
 * 系统参数DAO
 * @author Charles
 *
 */
public interface SysParamDao {

	public VOBase queryList(SysParam p, int start, int limit, boolean hasCount)
			throws BusinessException;
	
	public List<SysParam> queryAll(SysParam p) throws BusinessException;

	public void save(SysParam p) throws BusinessException;

	public void delete(String code, String value) throws BusinessException;

	public void update(SysParam p, String code, String value) throws BusinessException;
}
