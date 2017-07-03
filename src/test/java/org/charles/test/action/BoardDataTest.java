package org.charles.test.action;

import org.charles.app.analysis.StockAnalysisManage;
import org.charles.app.dao.BoardDataDao;
import org.charles.app.service.BoardDataService;
import org.charles.test.define.TestModel;
import org.junit.Before;
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
	}
	
//	@Test
	public void manage(){
		boardDataAnalysisManage.process();
	}
}
