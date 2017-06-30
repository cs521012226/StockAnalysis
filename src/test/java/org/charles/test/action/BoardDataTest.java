package org.charles.test.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.charles.app.board.analysis.StockAnalysisManage;
import org.charles.app.dao.BoardDataDao;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Stock;
import org.charles.app.service.BoardDataService;
import org.charles.framework.util.DateUtil;
import org.charles.test.define.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardDataTest extends TestModel{

	@Autowired
	private BoardDataService boardDataService;
	
	@Autowired
	private BoardDataDao boardDataDao;
	
	@Autowired
	private StockAnalysisManage boardDataAnalysisManage;
	
	@Before
	public void startServer(){
		System.out.println("before");
	}
	
	
	/**
	 * 注册
	 * @author YeChao
	 */
//	@Test
	public void test(){
		
		System.out.println(boardDataService.queryListByDate(getDate()));
		
	}
	
	public String getDate(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 3);
		c.set(Calendar.DAY_OF_MONTH, 28);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return DateUtil.convertDateToString(c.getTime());
	}
	
//	@Test
	public void manage(){
		boardDataAnalysisManage.manage();
	}
//	@Test
	public void findCmpCount(){
		String d = getDate();
		
		List<BoardData> list = boardDataDao.findCmpCount(d, d, Stock.SPECIAL_ORG_KEY, 3);
		System.out.println(list);
	}
}
