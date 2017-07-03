package org.charles.wechat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.charles.wechat.basic.BasicProcess;
import org.charles.wechat.handler.ImageMsgHandler;
import org.charles.wechat.handler.SubscribeEventHandler;
import org.charles.wechat.handler.TextMsgHandler;

public class Main {

	public static void main(String[] args) throws Exception{
		
		String rootPath = "D:/home/rewechat/";
		
		File requestFile = new File(rootPath + "request.xml");
		File responseFile = new File(rootPath + "response.xml");
		
		SubscribeEventHandler subscribeEventHandler = new SubscribeEventHandler();
		TextMsgHandler textMsgHandler = new TextMsgHandler();
		ImageMsgHandler imageMsgHandler = new ImageMsgHandler();
		
		subscribeEventHandler.setNext(textMsgHandler);
		textMsgHandler.setNext(imageMsgHandler);
		
		BasicProcess basicProcess = new BasicProcess();
		basicProcess.setHandle(subscribeEventHandler);
		basicProcess.process(new FileInputStream(requestFile), new FileOutputStream(responseFile));

	}

}
