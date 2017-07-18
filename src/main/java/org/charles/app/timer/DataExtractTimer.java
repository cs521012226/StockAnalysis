package org.charles.app.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.charles.framework.util.DateUtil;

/**
 * 数据获取时间
 * @author Charles
 *
 */
public class DataExtractTimer {
	
	private static DataExtractTimer self = new DataExtractTimer();
	
	private DataExtractTimer(){
		
	}

	private TimerTask dataExtractTask;
	
	public static void start(){
	}
	
	public static void main(String[] args) {
		
		start();
		
	}
	
	
	/** 
	 * 执行任务时间点
	 * @Author: Charles
	 * @return
	 */
	public static Date getFirstTime(){
//		String dateStr = SysParamUtil.getByPrimaryKeyForOne(SysParamUtil.DATA_EXTRACT_FIRST_TIME).getValue();
		String dateStr = "09:02:00";
		Date date = DateUtil.convertStringToDate(dateStr, DateUtil.Pattern.TIME.getPattern());
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		Calendar nowDate = Calendar.getInstance();
		c.set(Calendar.YEAR, nowDate.get(Calendar.YEAR));
		c.set(Calendar.MONTH, nowDate.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, nowDate.get(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	/** 
	 * 执行任务间隔（1天）
	 * @Author: Charles
	 * @return
	 */
	public static long getPeriodTime(){
//		int hours = SysParamUtil.getByPrimaryKeyForOne(SysParamUtil.DATA_EXTRACT_PERIOD).getIntValue();
//		long period = hours * 60 * 60 * 60 * 1000;
//		return period;
		return 60 * 1000;
	}

	public void setDataExtractTask(TimerTask dataExtractTask) {
		self.dataExtractTask = dataExtractTask;
	}
}
