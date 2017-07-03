package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.pojo.dto.BigTrade;
import org.charles.framework.exp.BusinessException;


/**
 * 营业部上榜排名
 * @author Charles
 *
 */
public interface BigTradeDao {
	
	/**
	 * 删除date日期之前的数据
	 * @author YeChao
	 * 2017年7月3日
	 * @param date
	 * @param andNow	是否包括今天
	 * @throws BusinessException
	 */
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException;
	
	public void save(BigTrade p) throws BusinessException ;
	
	public void saveBatch(List<BigTrade> p) throws BusinessException ;
	
}
