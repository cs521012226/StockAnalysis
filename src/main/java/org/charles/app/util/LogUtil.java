package org.charles.app.util;

import org.apache.log4j.Logger;
import org.charles.app.dao.OprLogsDao;
import org.charles.app.enums.LogType;
import org.charles.app.pojo.OprLogs;
import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.StringUtil;

/**
 * 系统参数工具类
 * @author Charles
 *
 */
public class LogUtil {
	private static Logger logger = Logger.getLogger(LogUtil.class);
	
	private LogUtil(){}
	private OprLogsDao oprLogsDao;
	public void setOprLogsDao(OprLogsDao oprLogsDao) {
		single.oprLogsDao = oprLogsDao;
	}
	
	private static LogUtil single = new LogUtil();
	
	/**
	 * 异常日志
	 * @author YeChao
	 * 2017年7月3日
	 * @param text
	 */
	public static void expLog(String text){
		logger.error(text);
		
		single.oprLogsDao.save(new OprLogs(LogType.EXCEPTION.toString(), LogType.EXCEPTION.name(), text));
	}
	
	/**
	 * 异常日志
	 * @author YeChao
	 * 2017年7月3日
	 * @param text
	 */
	public static void expLog(Exception e){
		logger.error(e.getMessage(), e);
		
		String code = null;
		String text = null;
		if(e instanceof BusinessException){
			BusinessException be = (BusinessException) e;
			code = be.getCode();
			text = be.getMsg();
		}
		if(StringUtil.isBlank(code)){
			code = LogType.EXCEPTION.toString();
		}
		StringBuilder sb = new StringBuilder();
		for(StackTraceElement ste : e.getStackTrace()){
			sb.append(ste.toString()).append("\n");
		}
		text = sb.toString();
		single.oprLogsDao.save(new OprLogs(LogType.EXCEPTION.toString(), code, text));
	}
	
	
	/**
	 * 操作日志
	 * @author YeChao
	 * 2017年7月3日
	 * @param text
	 */
	public static void oprLog(String text){
		single.oprLogsDao.save(new OprLogs(LogType.OPERATION.name(), LogType.OPERATION.name(), text));
	}
	
}
