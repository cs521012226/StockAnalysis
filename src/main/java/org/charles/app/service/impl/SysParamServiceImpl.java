package org.charles.app.service.impl;

import java.util.List;

import org.charles.app.dao.SysParamDao;
import org.charles.app.pojo.SysParam;
import org.charles.app.service.SysParamQuery;
import org.charles.app.service.SysParamService;
import org.charles.framework.exp.BusinessException;

/**
 * 系统参数服务层
 * @author Charles
 *
 */
public class SysParamServiceImpl implements SysParamService, SysParamQuery{

	private SysParamDao sysParamDao;
	
	@Override
	public List<SysParam> querySysParamList() {
		return queryAll(null);
	}

	@Override
	public List<SysParam> queryAll(SysParam p) throws BusinessException {
		return sysParamDao.queryAll(p);
	}

	@Override
	public void save(SysParam p) throws BusinessException {
		sysParamDao.save(p);
	}

	@Override
	public void delete(String code, String value) throws BusinessException {
		sysParamDao.delete(code, value);
	}

	@Override
	public void update(SysParam p, String code, String value) throws BusinessException {
		sysParamDao.update(p, code, value);
	}

	public SysParamDao getSysParamDao() {
		return sysParamDao;
	}

	public void setSysParamDao(SysParamDao sysParamDao) {
		this.sysParamDao = sysParamDao;
	}

}
