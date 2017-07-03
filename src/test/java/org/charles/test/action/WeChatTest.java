package org.charles.test.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.charles.test.define.TestModel;
import org.charles.wechat.basic.BasicProcess;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WeChatTest extends TestModel{

	@Autowired
	private BasicProcess basicProcess;
	
	@Before
	public void startServer(){
	}
	
	@Test
	public void process() throws Exception{
		
		String rootPath = "D:/home/rewechat/";
		
		File requestFile = new File(rootPath + "request.xml");
		File responseFile = new File(rootPath + "response.xml");
		
		basicProcess.process(new FileInputStream(requestFile), new FileOutputStream(responseFile));
	}
}
