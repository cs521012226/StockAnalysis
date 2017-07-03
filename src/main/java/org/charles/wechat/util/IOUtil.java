package org.charles.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.charles.framework.define.Constants;

public class IOUtil {
	
	public static Logger logger = Logger.getLogger(IOUtil.class);
	
	
	/**
	 * 读取input数据
	 * @author YeChao
	 * @return
	 */
	public static String readToStr(InputStream input) throws IOException{
		return readToStr(new InputStreamReader(input));
	}
	
	/**
	 * 读取input数据
	 * @author YeChao
	 * @return
	 */
	public static String readToStr(Reader read) throws IOException{
		StringBuilder text = new StringBuilder();
		
		int len;
		while((len = read.read()) != -1){
			text.append((char) len);
		}
		
		return text.toString();
	}
	
	
	/**
	 * 写数据
	 * @author YeChao
	 * @param data
	 * @throws IOException
	 */
	public static void writeFromStr(String data, OutputStream output) throws IOException{
		writeFromStr(data, new OutputStreamWriter(output, Constants.ENCODING));
	}
	
	/**
	 * 写数据
	 * @author YeChao
	 * @param data
	 * @throws IOException
	 */
	public static void writeFromStr(String data, Writer writer) throws IOException{
		writer.write(data);
	}
}
