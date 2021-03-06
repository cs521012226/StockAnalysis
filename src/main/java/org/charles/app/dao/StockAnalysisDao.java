package org.charles.app.dao;

import java.util.Date;
import java.util.List;

import org.charles.app.enums.Period;
import org.charles.app.pojo.BoardData;
import org.charles.framework.exp.BusinessException;

/**
 * 股票
 * @author Charles
 *
 */
public interface StockAnalysisDao {
	
	/** 
	 * 统计cmpCode机构的数量，并且将数量大于lg的股票返回
	 * @Author: Charles
	 * @param beginDate
	 * @param endDate
	 * @param cmpCode
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findCmpCount(Date beginDate, Date Date, String cmpCode) throws BusinessException;
	
	/**
	 * 获取创新高且上榜的股票汇总
	 * @author YeChao
	 * 2017年7月3日
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findNewTopBoardGroup(Date date) throws BusinessException;
	/**
	 * 获取创新高且上榜的股票列表
	 * @author YeChao
	 * 2017年7月3日
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findNewTopBoard(Date date) throws BusinessException;
	
	/**
	 * 获取向上突破且上榜的股票
	 * @author YeChao
	 * 2017年7月7日
	 * @param date
	 * @param period
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findBreakThroughBoardGroup(Date date, Period period) throws BusinessException;
	/**
	 * 获取向上突破且上榜的股票
	 * @author YeChao
	 * 2017年7月4日
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public List<BoardData> findBreakThroughBoard(Date date, Period period) throws BusinessException;
	
}
