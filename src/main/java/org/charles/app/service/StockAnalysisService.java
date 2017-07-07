package org.charles.app.service;

import java.util.Date;
import java.util.List;

import org.charles.app.enums.Period;
import org.charles.app.pojo.BoardData;


/**
 * 股票分析、统计数据服务层
 * @author Charles
 *
 */
public interface StockAnalysisService {

	/**
	 * 专用机构数量
	 * @author YeChao
	 * 2017年7月7日
	 * @param date
	 * @return
	 */
	public List<BoardData> orgCountData(Date date);
	
	
	/**
	 * 创新高且上榜股票数据
	 * @author YeChao
	 * 2017年7月7日
	 * @param date
	 * @return
	 */
	public List<BoardData> newTopBoardData(Date date);
	
	/**
	 * 向上突破均线且上榜股票数据
	 * @author YeChao
	 * 2017年7月7日
	 * @param date
	 * @return
	 */
	public List<BoardData> breakThroughBoardData(Date date, Period period) ;
}
