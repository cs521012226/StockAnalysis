package org.charles.framework.util;

import java.security.MessageDigest;

/**
 * 散列加密
 * @author Charles
 *
 */
public class CipherUtil {

	
	/** 
	 * MD5加密
	 * @Author: Charles
	 * @param text
	 * @return
	 */
	public static String MD5(String text){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(text.getBytes());
			
			return bytes2hex(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/** 
	 * sha1散列加密
	 * @Author: Charles
	 * @param text
	 * @return
	 */
	public static String SHA1(String text){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA1");
			byte[] result = md.digest(text.getBytes());
			
			return bytes2hex(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	
	/** 
	 * 字节数组转16进制字符串
	 * @Author: Charles
	 * @param bytes
	 * @return
	 */
	public static String bytes2hex(byte[] bytes) {  
        final String HEX = "0123456789abcdef";  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        for (byte b : bytes)  
        {  
        	// 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
         // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));  
        }  
        return sb.toString();  
    }  
}
