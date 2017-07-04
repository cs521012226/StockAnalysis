package org.charles.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @date 2014年9月4日
 * @author YeChao
 * @description 数字格式化工具类
 */
public class NumberUtil {
	
	public static enum Pattern{
		/**
		 * @description: 四舍五入固定保留两位小数
		 * 例1: (1234.12556) ==> (1234.13)
		 * 例2: (123464) ==> (123464.00)
		 */
		DECIMAL("0.00"),
		/**
		 * @description: 四舍五入根据是否有小数再保留两位小数
		 * 例1: (1234.12556) ==> (1234.13)
		 * 例2: (123464) ==> (123464)
		 */
		STANDART("#.##"),
		/**
		 * @description: 四舍五入根据是否有小数再保留四位小数
		 * 例1: (1234.12556) ==> (1234.1256)
		 * 例2: (123464) ==> (123464)
		 */
		HIGH_POSITION("#.####"),
		/**
		 * @description: 金钱格式 四舍五入根据是否有小数再保留两位小数
		 * 例1: (1234.12556) ==> (1,234.13)
		 * 例2: (123464) ==> (123,464.00)
		 * 例3: (0) ==> (0.00)
		 */
		MONEY("###,###,##0.00"),
		/**
		 * @description: 时间格式
		 * 例1: (88888) ==> (88888)
		 * 例2: (14) ==> (000014)
		 * 例3: (1) ==> (000001)
		 */
		TIME("000000");
		
		
		private final String pattern;
		
		Pattern(String pattern){
			this.pattern = pattern;
		}
		
		public String getPattern(){
			return this.pattern;
		}
	}
	
	public static String format(Number value){
		return format(value, Pattern.STANDART);
	}
	
	public static String format(Number value, Pattern pattern){
		return format(value, pattern.getPattern());
	}
	
	public static String format(Number value, String pattern){
		DecimalFormat d = new DecimalFormat(pattern);
		d.setRoundingMode(RoundingMode.HALF_UP);
		return d.format(value);
	}
	
	public static String format(String value){
		return format(value, Pattern.STANDART);
	}
	
	/**
	 * 格式化金钱
	 * @param value
	 * @return
	 */
	public static String formatMoney(String value) {
		return format(value, Pattern.MONEY);
	}
	/**
	 * 格式化金钱(没有千分位)
	 * @param value
	 * @return
	 */
	public static String formatMoney2(String value) {
		return format(value, Pattern.DECIMAL);
	}
	
	/**
	 * 格式化时间 HHmmss
	 * @param value
	 * @return
	 */
	public static String formatTime(String value) {
		return format(value, Pattern.TIME);
	}
	
	public static String format(String value, Pattern pattern){
		return format(value, pattern.getPattern());
	}
	
	public static String format(String value, String pattern){
		try{
			return format(new BigDecimal(value), pattern);
		}catch(NumberFormatException e){
			return format(-1, pattern);
		}
	}
	
	public static enum Unit{
		YUAN("元"),
		WANG("万"),
		YI("亿");
		
		private String text;
		Unit(String text){
			this.text = text;
		}
		public String getText(){
			return text;
		}
	}
	
	/**
	 * 转换单位
	 * @author YeChao
	 * 2017年7月4日
	 * @param text
	 * @param unit
	 * @return
	 */
	public static BigDecimal convertUnit(String text, Unit unit){
		text = text.trim();
		StringBuilder sb = new StringBuilder();
		for(Unit u : Unit.values()){
			sb.append("|").append(u.getText());
		}
		
		BigDecimal rs = new BigDecimal(text.replaceAll(sb.substring(1), ""));
		if(text.contains(Unit.YUAN.getText())){
			switch(unit){
			case WANG : 
				rs = new BigDecimal(rs.doubleValue() / 10000);
				break;
			case YI : 
				rs = new BigDecimal(rs.doubleValue() / (10000 * 10000));
				break;
			default:
				break;
			}
		}else if(text.contains(Unit.WANG.getText())){
			switch(unit){
			case YUAN : 
				rs = new BigDecimal(rs.doubleValue() * 10000);
				break;
			case YI : 
				rs = new BigDecimal(rs.doubleValue() / 10000);
				break;
			default:
				break;
			}
		}else if(text.contains(Unit.YI.getText())){
			switch(unit){
			case YUAN : 
				rs = new BigDecimal(rs.doubleValue() * (10000 * 10000));
				break;
			case WANG : 
				rs = new BigDecimal(rs.doubleValue() * 10000);
				break;
			default:
				break;
			}
		}
		return rs;
	}
	
	public static String random(int bit){
		Random r = new Random();
		StringBuilder guid = new StringBuilder();
		for(int i = 1 ; i <= bit ; i++){
			String n = Integer.toHexString(r.nextInt(16));
			guid.append(n);
		}
		return guid.toString();
	}
}
