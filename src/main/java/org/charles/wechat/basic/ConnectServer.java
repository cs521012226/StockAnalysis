package org.charles.wechat.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.charles.framework.exp.BusinessException;
import org.charles.framework.util.CipherUtil;
import org.charles.framework.util.StringUtil;

/**
 * 微信服务器接入
 * @author YeChao
 */
public class ConnectServer {

	private static final String token = "cs521012226";
	
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public ConnectServer(HttpServletRequest req, HttpServletResponse resp){
		this.req = req;
		this.resp = resp;
		checkParams();
	}
	
	/**
	 * 检查获取的参数
	 * @author YeChao
	 * @return
	 */
	public boolean checkParams(){
		signature = req.getParameter("signature");
		timestamp = req.getParameter("timestamp");
		nonce = req.getParameter("nonce");
		echostr = req.getParameter("echostr");
		System.out.println("signature = " + signature);
		System.out.println("timestamp = " + timestamp);
		System.out.println("nonce = " + nonce);
		System.out.println("echostr = " + echostr);
		
		if(StringUtil.isBlank(signature)
				|| StringUtil.isBlank(timestamp)
				|| StringUtil.isBlank(nonce)){
			throw BusinessException.define("缺少参数");
		}
		return false;
	}
	
	/**
	 * 检查是否来源于微信服务器
	 * @param signature		微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
	 * @param timestamp		时间戳
	 * @param nonce		随机数  
	 * @return
	 */
	public boolean checkSignature(){
		String[] sort = new String[]{ token, timestamp, nonce };
		Arrays.sort(sort);
		
		String implode = "";
		for(String s : sort){
			implode = implode + s;
		}
		implode = CipherUtil.SHA1(implode);
		
		return implode.equals(signature);
	}
	
	
	/**
	 * 接入成功则响应给微信服务器
	 * @author YeChao
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param out
	 */
	public boolean response(){
		PrintWriter pw = null;
		try {
			if(!checkSignature()){
				throw BusinessException.define("非常微信服务器发来的消息");
			}
			
			if(!StringUtil.isBlank(echostr)){
				pw = new PrintWriter(resp.getOutputStream());
				pw.print(echostr);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.close();
			}
		}
		return false;
	}
}
