package org.charles.app.util;

import java.util.Calendar;
import java.util.Date;

import org.charles.framework.util.DateUtil;

/**
 * 系统业务日期工具
 * @author YeChao
 * 2017年7月5日
 */
public class SysDateUtil {

	/**
	 * 获取股票数据最新日期
	 * @author YeChao
	 * 2017年7月5日
	 * @return
	 */
	public static Date getStockDate(){
		
		/*String dateStr = SysParamUtil.getByPrimaryKeyForOne(SysParamUtil.DATA_EXTRACT_FIRST_TIME).getValue();
		Date date = DateUtil.convertStringToDate(dateStr, DateUtil.Pattern.TIME.getPattern());
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		Calendar nowDate = Calendar.getInstance();
		c.set(Calendar.YEAR, nowDate.get(Calendar.YEAR));
		c.set(Calendar.MONTH, nowDate.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, nowDate.get(Calendar.DAY_OF_MONTH));
		
		Date runTime = c.getTime();
		Date nowTime = nowDate.getTime();
		if(nowTime.compareTo(runTime) > 0){
			return nowTime;
		}else{
			return DateUtil.addDay(nowTime, -1);
		}*/
		return new Date();
	}
}
