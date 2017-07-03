package org.charles.app.dao;

import java.util.List;

import org.charles.app.pojo.dto.BigTrade;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface BigTradeDao {
	
	public void delete() throws BusinessException;
	
	public void save(BigTrade p) throws BusinessException ;
	
	public void saveBatch(List<BigTrade> p) throws BusinessException ;
	
}
