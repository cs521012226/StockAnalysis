package org.charles.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.charles.framework.define.Constants;

/**
 * 文件管理操作工具类
 * @author YeChao
 */
public class FileUtil {
	
	/**通用日志对象**/
	private static Logger logger = Logger.getLogger(FileUtil.class);
	/** 
	 * @Author: YeChao
	 * @Description: 删除指定文件
	 * @param fileFullName	文件名称
	 * @return boolean: 返回是否删除成功
	 */
	public static boolean delFile(String fileFullName){
		File file = null;
		if(!StringUtil.isBlank(fileFullName)){
			file = new File(fileFullName);
			if(file.exists() && file.isFile()){
				return file.delete();
			}
		}
		return false;
	}
	
	/** 
	 * @Author: YeChao
	 * @Description: 删除当前目录及目录内所有目录文件，或只删除该目录下的所有目录及文件
	 * @param dirPath	目录路径
	 * @param isDelThisFloder	是否删除当前文件夹
	 * @return boolean: 	是否删除成功
	 */
	public static boolean delFloders(String dirPath, boolean isDelThisFloder){
		File dir = null;
		
		try{
			if(!StringUtil.isBlank(dirPath)){
				dir = new File(dirPath);
				if(dir.exists() && dir.isDirectory()){
					File[] listFile = dir.listFiles();	//当前目录下的所有文件
					for(File subFileOrDir : listFile){
						if(subFileOrDir.isFile()){
							if(!delFile(subFileOrDir.getCanonicalPath())){
								return false;
							}	//删除文件
						}
						if(subFileOrDir.isDirectory()){
							if(!delFloders(subFileOrDir.getCanonicalPath(), true)){
								return false;
							}
						}
					}
					if(isDelThisFloder){	//是否删除当前文件夹
						dir.delete();
					}
				}
			}
		}catch(IOException e){
			logger.error("删除目录时发生异常", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 创建文件
	 * @author YeChao
	 * @param fullPath	目录或文件全路径
	 * @return
	 */
	public static File createFile(String fullPath){
		final String separator = "/";
		String path = null;
		String fileName = null;
		
		fullPath = fullPath.replaceAll("\\\\+", separator);
		
		int lastSeparator = fullPath.lastIndexOf(separator);
		if(lastSeparator != -1){
			
			fileName = fullPath.substring(lastSeparator + 1);
			path = fullPath.substring(0, lastSeparator + 1);
		}else{
			path = fullPath;
		}
		
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		if(fileName == null){
			return dir;
		}
		
		File file = new File(path + fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return file;
	}
	
	public static void writeFile(String rootPath, String fileName, HttpServletResponse response){
		writeFile(new File(rootPath, fileName), response);
	}
	
	public static void writeFile(File file, HttpServletResponse response){
		try {
        	response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" 
						+ new String(file.getName().getBytes("GBK"), "ISO8859_1"));
				
			writeFile(new FileInputStream(file), response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
/*	public static void writeFile(File file, HttpServletResponse response){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			//流的形式
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" 
					+ new String(file.getName().getBytes("GBK"), "ISO8859_1"));
			
			final int bufferSize = 30 * 1024;		//缓冲设置为30KB
			
			bis = new BufferedInputStream(new FileInputStream(file), bufferSize); // 读入原文件  
			bos = new BufferedOutputStream(response.getOutputStream(), bufferSize); // 写入目标文件  
			
			int by;
			while((by = bis.read()) != -1){
				bos.write(by);
			}
			bos.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if(bos != null){
					bos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
*/	
	public static void writeFile(InputStream input, OutputStream output){
		final int bufferSize = 30 * 1024;		//缓冲设置为30KB
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
        	
			bis = new BufferedInputStream(input, bufferSize); // 读入原文件  
			bos = new BufferedOutputStream(output, bufferSize); // 写入目标文件  
        	
			int by;
            while((by = bis.read()) != -1){
            	bos.write(by);
            }
        	bos.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(bos != null){
					bos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 压缩文件
	 * @author YeChao
	 * @param filePath	文件路径或文件夹路径
	 * @return
	 */
	/*public static boolean zipFile(String filePath){
		return zipFile(new File(filePath));
	}*/
	
	/**
	 * 压缩文件
	 * @author YeChao
	 * @param file		需要压缩的文件或者文件夹
	 * @return
	 */
	/*public static boolean zipFile(File file){
		return zipFile(file, null);
	}*/
	/**
	 * 压缩文件入口
	 * @author YeChao
	 * @param file	要压缩的文件File对象
	 * @return	是否压缩成功
	 */
	/*public static boolean zipFile(File file){
		if(!file.exists()){
			logger.info("不存在目录或文件: " + file.getPath());
			return false;
		}
		final String SUFFIX = ".zip";		//压缩文件后缀名称
		
		ZipOutputStream zos = null;
		try{
			String filePath = file.getCanonicalPath();		//获取将要压缩文件的全路径文件名称
			String zipFileName = null;		//新压缩文件名称
			if(file.isDirectory()){
				zipFileName = filePath + SUFFIX;
			}else if(file.isFile()){
				zipFileName = filePath.substring(0, filePath.lastIndexOf(".")) + SUFFIX;
			}
			zos = new ZipOutputStream(new FileOutputStream(zipFileName));
			recZipFiles("", file, zos);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		}
		return true;
	}*/
	
	/**
	 * 递归读取目录及文件并且压缩入ZipOutputStream流里
	 * @author YeChao
	 * @param relativePath		相对路径，默认应该为""不填写
	 * @param file		将要压缩的文件
	 * @param zos		压缩文件流
	 * @throws IOException
	 */
	/*protected static void recZipFiles(String relativePath, File file, ZipOutputStream zos) throws IOException{
		//如果相对路径为空，则前面不加斜杠或反斜杠
		if(relativePath != null && !relativePath.isEmpty()){
			relativePath = relativePath + File.separator;
		}
		
		//如果是目录
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				recZipFiles(relativePath + file.getName(), f, zos);
			}
		}else if(file.isFile()){		//如果是文件
			BufferedInputStream bis = null;
			
			final int bufferSize = 30 * 1024;	//缓冲区设为30KB
			try{
				//添加进压缩文件列表
				zos.putNextEntry(new ZipEntry(relativePath + file.getName()));
				
				bis = new BufferedInputStream(new FileInputStream(file), bufferSize);
				int len;
				byte[] bytes = new byte[bufferSize];
				while ((len = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, len);	//把文件写入压缩文件里
				}
			}catch(IOException e){
				logger.error("压缩文件失败:" + e.getMessage(), e);
				throw e;
			}finally{
				if (bis != null) {
					bis.close();
				}
			}
		}
	}*/
	
	
	/**
	 * 压缩文件，并且使用密码加密
	 * @author YeChao
	 * @param file		需要压缩的文件或者文件夹
	 * @param password	加密的密码
	 * @return
	 */
	/*public static boolean zipFile(File file, String password){
		if(!file.exists()){
			logger.info("不存在目录或文件: " + file.getPath());
			return false;
		}
		final String SUFFIX = ".zip";		//压缩文件后缀名称
		
		try {
			String filePath = file.getCanonicalPath(); // 获取将要压缩文件的全路径文件名称
			
			String zipFileName = null; // 新压缩文件名称
			if (file.isDirectory()) {
				zipFileName = filePath + SUFFIX;
			} else if (file.isFile()) {
				zipFileName = filePath.substring(0, filePath.lastIndexOf(".")) + SUFFIX;
			}
			
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipParameters params = getZipParams(password);
			if(file.isDirectory()){
				zipFile.addFolder(file, params);
			}else{
				zipFile.addFile(file, params);
			}
			
			
		} catch (IOException e) {
			logger.error("压缩文件失败:" + e.getMessage(), e);
		} catch (ZipException e) {
			logger.error("压缩文件失败:" + e.getMessage(), e);
		}
		
		
		return true;
	}*/
	
	
	/**
	 * 设置压缩文件加密算法
	 * @author YeChao
	 * @param password 加密使用的密码
	 * @return
	 */
	/*protected static ZipParameters getZipParams(String password){
		ZipParameters params = new ZipParameters();
		params.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);			//压缩方式
		params.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);	//压缩级别
		//如果密码不为空，则设置压缩文件密码
		if(!StringUtil.isBlank(password)){
			params.setEncryptFiles(true);
			params.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); //加密方式
			params.setPassword(password);
		}
		return params;
	}*/
	
	
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
