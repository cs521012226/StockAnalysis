package org.charles.test.crawler;

import org.charles.app.timer.task.DataExtractTask;
import org.charles.test.define.TestModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DateExtractTest extends TestModel{

	@Autowired
	private DataExtractTask dataExtractTask;
	
	
	@Test
	public void process() throws Exception{
		dataExtractTask.run();
	}
}
