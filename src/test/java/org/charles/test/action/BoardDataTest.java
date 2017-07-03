package org.charles.test.action;

import org.charles.app.analysis.StockAnalysisManage;
import org.charles.test.define.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardDataTest extends TestModel{

	@Autowired
	private StockAnalysisManage baseAnalysisManage;
	
	@Before
	public void startServer(){
	}
	
	@Test
	public void manage(){
		baseAnalysisManage.process();
	}
}
