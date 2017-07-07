package org.charles.test.analysis;

import org.charles.app.analysis.StockAnalysisManage;
import org.charles.app.util.SysParamUtil;
import org.charles.test.define.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StockAnalysisManageTest extends TestModel{

	@Autowired
	private StockAnalysisManage baseAnalysisManage;
	
	@Before
	public void startServer(){
		//刷新参数
//		SysParamUtil.refresh();
	}
	
	@Test
	public void manage(){
		baseAnalysisManage.process();
	}
}
