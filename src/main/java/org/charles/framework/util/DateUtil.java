package org.charles.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {
	
	public static enum Pattern{
		DATE8_TIME6("yyyyMMddHHmmss", ""),
		DATE_TIME("yyyy-MM-dd HH:mm:ss", ""),
		DATE("yyyy-MM-dd", ""),
		DATE8("yyyyMMdd", ""),
		DATE6("yyMMdd", ""),
		TIME("HH:mm:ss", ""),
		TIME6("HHmmss", ""),
		;
		
		private final String pattern;
		private final String desc;
		
		@Override
		public String toString(){
			return this.desc;
		}
		
		Pattern(String pattern, String desc){
			this.pattern = pattern;
			this.desc = desc;
		}
		
		public String getPattern(){
			return this.pattern;
		}
		
		public String getDesc(){
			return this.desc;
		}
	}
	private static SimpleDateFormat createSDF(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);	//严格模式，设置 1996-13-3 不能转为1997-1-3
		return sdf;
	}
	
	private static Calendar createCld(){
		return Calendar.getInstance();
	}
	
	/**
	 * 获取当前时间毫秒指定后n位
	 * @param n 指定后n位,取值范围1-8
	 */
	public static int getMilliSecond(int n){
		if(n < 1) n = 1;
		if(n > 8) n = 8;
		long mod = (long) Math.pow(10.0, (double)n);
		return (int) (new Date().getTime() % mod);
	}
	/**
	 * 获取当前时间毫秒后6位
	 */
	public static int getMilliSecond6(){
		return getMilliSecond(6);
	}
	
	/**
	 * 日期转换：String日期转为Date日期
	 * <p> 例1: "2014-10-21" -> Date
	 * @author YeChao
	 * @param date
	 * @return	Date
	 */
	public static Date convertStringToDate(String date){
		return convertStringToDate(date, Pattern.DATE.getPattern());
	}
	/**
	 * 日期转换：String日期转为Date日期
	 * <p> 例1: "2014-10-21", "yyyy-MM-dd" -> Date
	 * @author YeChao
	 * @param String date
	 * @param String pattern
	 * @return	Date
	 */
	public static Date convertStringToDate(String date, String pattern){
		try {
			return createSDF(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null; 
	}
	/**
	 * 日期转换：String 日期转为 int 日期
	 * <p> 例1: "2014-10-21" -> 20141021
	 * @author	YeChao
	 * @param date
	 * @return
	 */
	public static int convertStringToInteger(String date){
		return convertDateToInteger(convertStringToDate(date));
	}
	/**
	 * 日期转换：String 日期转为 int 日期
	 * <p> 例1: "2014-10-21","yyyy-MM-dd","yyyyMMdd" -> 20141021
	 * <p> 例2: "2014年10月21日","yyyy年MM月dd日","yyMMdd" -> 141021
	 * <p> 例3: "2014-10-21 10:59","yyyy-MM-dd HH:mm","yyMMddHHmm" -> 1410211059
	*/
	public static int convertStringToInteger(String date, String inFormat, String outFormat){
		return convertDateToInteger(convertStringToDate(date, inFormat), outFormat);
	}
	/**
	 * 日期转换：Date日期转为String日期
	 * @author	YeChao
	 * @param date
	 * @return String 格式："2014-10-21"
	 */
	public static String convertDateToString(Date date){
		return convertDateToString(date, Pattern.DATE.getPattern());
	}
	/**
	 * 日期转换：Date日期转为String日期
	 * @author	YeChao
	 * @param date
	 * @param pattern	
	 * @return String 格式：自定义
	 */
	public static String convertDateToString(Date date, String pattern){
		return createSDF(pattern).format(date);
	}
	/**
	 * 日期转换：Date日期转为int日期
	 * @author	YeChao
	 * @param date
	 * @return String 格式："2014-10-21"
	 */
	public static int convertDateToInteger(Date date){
		return convertDateToInteger(date, Pattern.DATE8.getPattern());
	}
	/**
	 * 日期转换：Date日期转为int日期
	 * @author	YeChao
	 * @param date
	 * @param pattern
	 * @return String 格式：自定义
	 */
	public static int convertDateToInteger(Date date, String pattern){
		return Integer.valueOf(createSDF(pattern).format(date));
	}
	/**
	 * 日期转换：int日期转为String日期
	 * <p>例1: 20141021 -> "2014-10-21"
	 * @author	YeChao
	 * @param date
	 * @return String
	 */
	public static String convertIntegerToString(int date){
		return convertDateToString(convertIntegerToDate(date));
	}
	/**
	 * 日期转换：int日期转为String日期
	 * <p> 例1: 20141021,"yyyyMMdd","yyyy-MM-dd" -> "2014-10-21"
	 * <p> 例2: 141021,"yyMMdd","yyyy年MM月dd日" -> "2014年10月21日"
	 * <p> 例3: 1410211059,"yyMMddHHmm","yyyy-MM-dd HH:mm" -> "2014-10-21 10:59"
	 * @author	YeChao
	 * @param date	int类型日期
	 * @param inFormat	date输入的格式
	 * @param outFormat	想要输出的格式
	 * @return
	 */
	public static String convertIntegerToString(int date, String inFormat, String outFormat){
		return convertDateToString(convertIntegerToDate(date, inFormat), outFormat);
	}
	
	/**
	 * 日期转换：int日期转为Date日期
	 * @author	YeChao
	 * @param dateInt	如：20141011
	 * @return Date
	 */
	public static Date convertIntegerToDate(int dateInt){
		return convertIntegerToDate(dateInt, Pattern.DATE8.getPattern());
	}
	/**
	 * 日期转换：int日期转为Date日期
	 * @author	YeChao
	 * @param dateInt	如：20141011
	 * @param pattern	格式
	 * @return Date
	 */
	public static Date convertIntegerToDate(int dateInt, String pattern){
		try {
			return createSDF(pattern).parse(String.valueOf(dateInt));
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	/**
	 * 获取该日期这个月的最后一天（等同与这个月有多少天）
	 * <p> 例1: "2014-02-21"  返回: 28或29（看年份）
	 * <p> 例2: "2014-08-17"  返回: 31
	 * @author	YeChao
	 * @param date	yyyy-MM-dd 的日期格式, 如：2014-11-11
	 * @return int	天数
	 */
	public static int getLastDayOfMonth(String date){
		Calendar calendar = createCld();
		calendar.setTime(convertStringToDate(date));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取该日期这个月的最后一天（等同于这个月有多少天）
	 * <p> 例1: 20140221  返回: 28或29（看年份）
	 * <p> 例2: 20140817  返回: 31
	 * @author	YeChao
	 * @param date	int 的日期格式, 如：20141122
	 * @return int	天数
	 * @see getLastDayOfMonth(String date)
	 */
	public static int getLastDayOfMonth(int date){
		Calendar calendar = createCld();
		int year = date/10000;
		int month = (date - year * 10000)/100;
		int day = date - (year * 10000 + month * 100);
		calendar.set(year, month - 1, day);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取该日期在该年份里的第几天（1月1日到该日期的天数）
	 * <p> 例1: "2014-01-31"	返回: 31
	 * <p> 例2: "2014-02-13"	返回: 44（31+13）
	 * @author	YeChao
	 * @param date	yyyy-MM-dd 的日期格式, 如："2014-11-11"
	 * @return int 天数
	 */
	public static int getDayOfYear(String date){
		Calendar calendar = createCld();
		calendar.setTime(convertStringToDate(date));
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	/**
	 * 获取该日期 天位
	 * <p> 例1: "2014-01-31"	返回: 31
	 * <p> 例2: "2014-02-13"	返回: 13
	 * @author	YeChao
	 * @param date	yyyy-MM-dd 的日期格式, 如："2014-11-11"
	 * @return int 天数
	 */
	public static int getDayOfMonth(String date){
		Calendar calendar = createCld();
		calendar.setTime(convertStringToDate(date));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取该日期是星期几
	 * @author	YeChao
	 * @param date	yyyy-MM-dd 的日期格式, 如：2014-11-11
	 * @return	int 星期数（0：周日、1：周一、6：周六）
	 */
	public static int getDayOfWeek(String date){
		Calendar calendar = createCld();
		calendar.setTime(convertStringToDate(date));
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}
	/**
	 * 获取两个日期相隔的天数，参数顺序随意
	 * <p> 例1: "2008-1-20", "2008-1-25" -> 6(包括date1那天) 
	 * @author	YeChao
	 * @param date1	日期1 
	 * @param date2	日期2 
	 * @return 相隔的天数 
	 */
	public static int getDaySpace(String date1, String date2){
		long from = convertStringToDate(date1).getTime();
		long to = convertStringToDate(date2).getTime();
		return (int)(Math.abs(from - to) / (1000 * 60 * 60 * 24)) + 1;
	}
	/**
	 * 获取两个日期相隔的天数，参数顺序随意
	 * <p> 例1: 20080120, 20080125 -> 6(包括date1那天) 
	 * @author	YeChao
	 * @param date1	日期1
	 * @param date2	日期2
	 * @return 相隔的天数 
	 */
	public static int getDaySpace(int date1, int date2){
		long from = convertIntegerToDate(date1).getTime();
		long to = convertIntegerToDate(date2).getTime();
		return (int)(Math.abs(from - to) / (1000 * 60 * 60 * 24)) + 1;
	}
	
	/**
	 * 获取两个日期相隔的月数，参数顺序随意
	 * <p> 例1: "2012-11-11", "2014-04-11" -> 17
	 * @param date1	开始日期的格式
	 * @param date2	结束日期的格式
	 * @return 返回相隔月数
	 */
	public static int getMonthSpace(String date1, String date2){
		Calendar c1 = createCld();
		Calendar c2 = createCld();
		
		c1.setTime(convertStringToDate(date1));
		c2.setTime(convertStringToDate(date2));
		
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		
		if(year1 == year2){	//同一年直接返回月份相减
			return Math.abs(month1 - month2);
		}
		
		int resultMonth = 0;
		//比较日期大小
		int ct = c1.compareTo(c2);
		if(ct > 0){	
			//c1 > c2
			resultMonth = Math.abs(year1 - year2) * 12 + month1 - month2;
		}else if(ct < 0){
			//c2 > c1
			resultMonth = Math.abs(year1 - year2) * 12 + month2 - month1;
		}else{
			//c2 == c1
			resultMonth = 0;
		}
		return resultMonth;
	}
	/**
	 * 获取两个日期相隔的月数，参数顺序随意
	 * <p> 例1: 20121111, 20140411 -> 17
	 * @param date1	开始日期的格式
	 * @param date2	结束日期的格式
	 * @return 返回相隔月数
	 */
	public static int getMonthSpace(int date1, int date2){
		String dateStr1 = convertIntegerToString(date1);
		String dateStr2 = convertIntegerToString(date1);
		return getMonthSpace(dateStr1, dateStr2);
	}
	
	/**
	 * 获取日期加上x天数之后的日期
	 * 如果 addDays为正整数, 则返回传入日期之后的日期
	 * 如果 addDays为负整数, 则返回传入日期之前的日期
	 * @author 梁翼杰
	 * @param originalDate 原日期 格式为 yyyyMMdd
	 * @param addDays 天数
	 * @return 返回日期加(减)上天数之后(前)的日期
	 */
	public static int getAddDayDate(int originalDate, int addDays){
		Calendar cl = createCld();
		cl.setTime(convertIntegerToDate(originalDate));
		cl.add(Calendar.DATE, addDays);
		return convertDateToInteger(cl.getTime());
	}	
	
	/**
	 * 获取日期加上x月之后的日期
	 * 如果 addMonths为正整数, 则返回传入日期之后的日期
	 * 如果 addMonths为负整数, 则返回传入日期之前的日期
	 * @author 梁翼杰
	 * @param originalDate 原日期 格式为 yyyyMMdd
	 * @param addMonths 月数
	 * @return 返回日期加(减)上月数之后(前)的日期
	 */
	public static int getAddMonthDate(int originalDate, int addMonths) {
		Calendar cl = createCld();
		cl.setTime(convertIntegerToDate(originalDate));
		cl.add(Calendar.MONTH, addMonths);
		return convertDateToInteger(cl.getTime());
	}
	
	/**
	 * 获取日期加上x年之后的日期
	 * 如果 addYears为正整数, 则返回传入日期之后的日期
	 * 如果 addYears为负整数, 则返回传入日期之前的日期
	 * @author 梁翼杰
	 * @param originalDate 原日期 格式为 yyyyMMdd
	 * @param addYears 年数
	 * @return 返回日期加(减)上年数之后(前)的日期
	 */
	public static int getAddYearDate(int originalDate, int addYears) {
		Calendar cl = createCld();
		cl.setTime(convertIntegerToDate(originalDate));
		cl.add(Calendar.YEAR, addYears);
		return convertDateToInteger(cl.getTime());
	}
	
	/**
	 * 获取日期加上x天数之后的日期
	 * @author	YeChao
	 * @param inputDate	格式为 yyyy-MM-dd
	 * @param addDays	天数
	 * @return	输出格式为 yyyy-MM-dd
	 */
	public static String getAddDayDate(String inputDate, int addDays) {
		Calendar cl = createCld();
		cl.setTime(convertStringToDate(inputDate));
		cl.add(Calendar.DATE, addDays);
		return convertDateToString(cl.getTime());
	}
	
	/**
	 * 获取日期加上x月之后的日期
	 * @author	YeChao
	 * @param inputDate	格式为 yyyy-MM-dd
	 * @param addMonths	月数
	 * @return	输出格式为 yyyy-MM-dd
	 */
	public static String getAddMonthDate(String inputDate, int addMonths) {
		Calendar cl = createCld();
		cl.setTime(convertStringToDate(inputDate));
		cl.add(Calendar.MONTH, addMonths);
		return convertDateToString(cl.getTime());
	}
	
	/**
	 * 获取日期加上x年之后的日期
	 * @author	YeChao
	 * @param inputDate	格式为 yyyy-MM-dd
	 * @param addYears	年数
	 * @return	输出格式为 yyyy-MM-dd
	 */
	public static String getAddYearDate(String inputDate, int addYears) {
		Calendar cl = createCld();
		cl.setTime(convertStringToDate(inputDate));
		cl.add(Calendar.YEAR, addYears);
		return convertDateToString(cl.getTime());
	}
	
	/** 
	 * 获取指定日期
	 * @Author: Charles
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date createDate(int year, int month, int day){
		Calendar cl = createCld();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month - 1);
		cl.set(Calendar.DATE, day);
		return cl.getTime();
	}
	
	/** 
	 * 获取指定时间
	 * @Author: Charles
	 * @param hour24
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date createTime(int hour24, int minute, int second){
		Calendar cl = createCld();
		cl.set(Calendar.HOUR_OF_DAY, hour24);
		cl.set(Calendar.MINUTE, minute);
		cl.set(Calendar.SECOND, second);
		return cl.getTime();
	}
}
