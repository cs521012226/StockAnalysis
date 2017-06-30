package org.charles.app.service;

import java.util.List;

import org.charles.app.pojo.BoardData;
import org.charles.framework.exp.BusinessException;

/**
 * 龙虎榜数据服务
 * @author Charles
 *
 */
public interface BoardDataService {

	/** 
	 * 查询列表
	 * @Author: Charles
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> queryListByDate(String date) throws BusinessException ;
}
