package org.charles.app.dao;

import org.charles.app.pojo.OprLogs;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface OprLogsDao {
	
	public void delete() throws BusinessException;
	
	public void save(OprLogs p) throws BusinessException ;
	
	
}
