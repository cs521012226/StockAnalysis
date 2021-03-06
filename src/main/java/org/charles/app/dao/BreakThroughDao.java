package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.pojo.dto.BreakThrough;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface BreakThroughDao {
	
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException;
	
	public void saveBatch(List<BreakThrough> p) throws BusinessException ;
	
}
