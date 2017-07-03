package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.pojo.BoardData;
import org.charles.framework.exp.BusinessException;

/**
 * 龙虎榜
 * @author Charles
 *
 */
public interface BoardDataDao {
	
	/**
	 * 删除date日期前的数据
	 * @author YeChao
	 * 2017年7月3日
	 * @param date
	 * @param andNow	是否删除今天的数据
	 * @throws BusinessException
	 */
	public void deleteBeforeDate(Date date, boolean andNow) throws BusinessException;
	
	/** 
	 * 查询
	 * @Author: Charles
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> queryListByDate(String date) throws BusinessException;
	
	
	/** 
	 * 统计cmpCode机构的数量，并且将数量大于lg的股票返回
	 * @Author: Charles
	 * @param beginDate
	 * @param endDate
	 * @param cmpCode
	 * @param lg
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findCmpCount(String beginDate, String endDate, String cmpCode, int lg) throws BusinessException;
	
	
	public void save(BoardData e) throws BusinessException;
	
	public boolean isExistData(Date boardDate) throws BusinessException;
}
