package org.charles.wechat.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.charles.framework.define.Constants;
import org.charles.framework.util.FileUtil;
import org.charles.wechat.handler.AbstractMsgHandler;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 基础微信公众平台处理入口
 * @author YeChao
 */
public class BasicProcess {
	public static Logger logger = Logger.getLogger(BasicProcess.class);
	
	private AbstractMsgHandler handle;
	
	/**
	 * 格式化xml为字符串
	 * @author YeChao
	 * @param doc
	 * @return
	 */
	protected String buildXml(Document doc){
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		OutputFormat fmt = OutputFormat.createPrettyPrint();
		//设置文件编码
		fmt.setEncoding(Constants.ENCODING);
		// 设置换行
		fmt.setNewlines(true);
		
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(bao, fmt);
			writer.write(doc);
			writer.flush();
		} catch(Exception e){
			logger.error(e.getMessage(), e);
		} 
		
		String result = null;
		try {
			result = bao.toString(Constants.ENCODING);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		//去掉<?xml 头信息
		result = result.replaceAll("<\\?([^>]*)>\\s+", "");
		
		return result;
	}
	
	/**
	 * 微信业务处理主要入口
	 * @author YeChao
	 */
	public void process(InputStream in, OutputStream out){
		BufferedReader read = null;
		BufferedWriter write = null;
		try {
			read = new BufferedReader(new InputStreamReader(in, Constants.ENCODING));
			write = new BufferedWriter(new OutputStreamWriter(out, Constants.ENCODING));
			
			
			//读取input数据
			String inputData = FileUtil.readToStr(read);
			logger.info("receive input message: \n" + inputData);
			// 解析输入数据为XML
			Document inDoc = DocumentHelper.parseText(inputData);
			// 各种微信消息推送过来后，通过消息或事件类型进行分发，拿到处理链
			
			Document outDoc = handle.handle(inDoc);
			
			//返回XML不为空时才响应、例如事件推送消息可响应可不响应
			if(outDoc != null){
				// 最终响应的xml
				String resultXml = buildXml(outDoc);
				logger.info("response output message: \n" + resultXml);
				// 响应数据
				FileUtil.writeFromStr(resultXml, write);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(read != null){
					read.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(write != null){
					write.flush();
					write.close();
				}
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public AbstractMsgHandler getHandle() {
		return handle;
	}

	public void setHandle(AbstractMsgHandler handle) {
		this.handle = handle;
	}
}
