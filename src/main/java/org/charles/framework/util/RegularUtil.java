package org.charles.framework.util;


/**
 * @date 2014年9月3日
 * @author YeChao
 * @description 正则匹配工具类
 */
public class RegularUtil {
	
	public static enum RegExp{
		/**
		 * @description: 手机正则，11位数字
		 */
		PHONE("^(1\\d{10})$"),
		/**
		 * @description: 电话正则， 以0开头的8-18位数字，格式如:02012345678
		 */
		TELPHONE("^(0\\d{7,17})$"),
		/**
		 * @description: Email正则
		 */
		EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
		/**
		 * @description: 身份证号码正则
		 */
		IDEN_CARD("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x))$"),
		/**
		 * @description: 匹配数字
		 */
		NUMBER("\\d+"),
		/**
		 * 匹配小数
		 */
		DOUBLE("\\d+\\.\\d+"),
		/**
		 * 匹配整数
		 */
		INTEGER("\\d+")
		;
		
		private final String regExp;
		RegExp(String regExp){
			this.regExp = regExp;
		}
		public String getRegExp(){
			return this.regExp;
		}
	}
	/**
	 * @description: 基础方法
	 * @param text
	 * @param regExp
	 * @return
	 */
	private static boolean matches(String text, RegExp regExp){
		if(StringUtil.isBlank(text)){
			return false;
		}
		return text.trim().matches(regExp.getRegExp());
	}
	
	/**
	 * @description: 匹配Email
	 * @param val
	 * @return
	 */
	public static boolean matchEmail(String text){
		return matches(text, RegExp.EMAIL);
	}
	
	/**
	 * @description: 匹配手机
	 * @param val
	 * @return
	 */
	public static boolean matchPhone(String text){
		return matches(text, RegExp.PHONE);
	}
	
	/**
	 * @description: 匹配电话
	 * @param text
	 * @return
	 */
	public static boolean matchTelphone(String text){
		return matches(text, RegExp.TELPHONE);
	}
	
	/**
	 * @description: 匹配身份证
	 * @param text
	 * @return
	 */
	public static boolean matchIdentityCard(String text){
		return matches(text, RegExp.IDEN_CARD);
	}
	
	/**
	 * 匹配数字
	 * <p> "12342" -> true
	 * <p> "a2342" -> false
	 * <p> "" -> false
	 * @author	YeChao
	 * @param text
	 * @return
	 */
	public static boolean matchNumber(String text){
		return matches(text, RegExp.NUMBER);
	}
	
	/**
	 * 判断是否小数
	 * @author	YeChao
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
		return matches(str, RegExp.DOUBLE);
	}
}
