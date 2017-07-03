package org.charles.app.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.charles.app.util.SysParamUtil;
import org.charles.framework.util.DateUtil;

/**
 * 数据获取时间
 * @author Charles
 *
 */
public class DataExtractTimer {
	
	private static String DATA_EXTRACT_PERIOD = "DATA_EXTRACT_PERIOD";
	private static String DATA_EXTRACT_FIRST_TIME = "DATA_EXTRACT_FIRST_TIME";
	
	private static DataExtractTimer self = new DataExtractTimer();
	
	private DataExtractTimer(){
		
	}

	private TimerTask dataExtractTask;
	
	public static void start(){
		Timer t = new Timer();
		
		Date firstTime = getFirstTime();
		long periodTime = getPeriodTime();
		
		t.scheduleAtFixedRate(self.dataExtractTask, firstTime, periodTime);
		
	}
	
	/** 
	 * 执行任务时间点
	 * @Author: Charles
	 * @return
	 */
	public static Date getFirstTime(){
		String dateStr = SysParamUtil.getByPrimaryKeyForOne(DATA_EXTRACT_FIRST_TIME).getValue();
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
		int hours = SysParamUtil.getByPrimaryKeyForOne(DATA_EXTRACT_PERIOD).getIntValue();
		long period = hours * 60 * 60 * 60 * 1000;
		return period;
	}

	public void setDataExtractTask(TimerTask dataExtractTask) {
		self.dataExtractTask = dataExtractTask;
	}
}
