package org.charles.framework.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @date 2014年10月21日
 * @author YeChao
 * @description 字符串工具类
 */
public class StringUtil {

	private static final char[] chars = new char[36];
	
	static {
		for (int i = 0; i < chars.length; i++) {
			if (i < 10) {
				chars[i] = (char) (i + 48); // 0-9数字
			} else {
				chars[i] = (char) (i + 55); // A-Z字母
			}
		}
	}
	
	/**
	 * 是否为空
	 * @author	YeChao
	 * @param src
	 * @return
	 */
	public static boolean isBlank(Object o) {
        if(o == null){
        	return true;
        }
        if(o instanceof String){
        	return ((String) o).trim().isEmpty();
        }
        if(o instanceof Collection){
        	return ((Collection<?>) o).isEmpty();
        }
        if(o instanceof Map){
        	return ((Map<?, ?>) o).isEmpty();
        }
        return false;
    }
	
	/**
	 * 是否为空
	 * @author YeChao
	 * @param src
	 * @return
	 */
	public static boolean isBlank(char src) {
        return isBlank(String.valueOf(src));
    }
	
	/**
	 * 匹配参数格式化
	 * <p>例：StringUtil.format("{0}-{1}-{2}","2014","12","21") 返回： "2014-12-21"</p>
	 * @author	YeChao
	 * @param pattern
	 * @param args
	 * @return
	 */
	public static String format(String pattern, Object... args){
		return MessageFormat.format(pattern, args);
	}
	
	/**
	 * 去除左右空格
	 * @author	YeChao
	 * @param o
	 * @return
	 */
	public static String trim(Object o){
		if(o == null){
			return "";
		}
		if(o instanceof String){
			return ((String) o).trim();
		}
		return o.toString().trim();
	}
	
	/**
	 * 首字母转大写
	 * @author YeChao
	 * @param word
	 * @return
	 */
	public static String toFirstUpperCase(String word){
		if (isBlank(word)) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
	
	
	/**
	 * 通过分隔符连接字符串数组
	 * @author YeChao
	 * @param strArr		字符串数组
	 * @param separator		分隔符
	 * @return
	 */
	public static String join(String separator, String... strArr){
		StringBuilder sb = new StringBuilder();
		for(String s : strArr){
			sb.append(separator);
			sb.append(s);
		}
		if(sb.length() > 0){
			return sb.substring(separator.length());
		}
		return "";
	}
	
	public static String join(String separator, List<String> strArr){
		return join(separator, strArr.toArray(new String[strArr.size()]));
	}
	
	public static String split(String separator, String target, int index){
		String[] arr = target.split(separator);
		if(arr.length == 0){
			return "";
		}
		
		index = index < 0 ? 0 : index >= arr.length ? arr.length - 1 : index;
		
		return arr[index];
	}
	
	/** 
     * 获取[0-9]|[A-Z]范围内随机产生的字符串
     * @param len 长度
     * @return 
     * @throws CoderException 
     */  
    public static String randomString(int len)  {  
    	return randomString("", len);
    }
    /** 
     * 获取[0-9]|[A-Z]随机产生的字符串
     * @param prefix 前缀
     * @param len 长度
     * @return 
     * @throws CoderException 
     */  
    public static String randomString(String prefix, int len)  {  
		StringBuffer result = new StringBuffer(prefix);
		Random r = new Random();
		for (int i = 0; i < len; i++) {
			result.append(chars[r.nextInt(chars.length)]);
		}
		return result.toString();
    }  
}
