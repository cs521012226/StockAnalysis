package org.charles.framework.exp;

import java.util.Map;


/**
 * 异常模型
 * @author WZY
 * @Level 
 * 	EXCEPTION, WARNING, INFO, DEBUG
 * 	{@link com.enton.custom.sys.BusinessException.Level }
 * @SysCode
 * 	_0000, _9999, _9998, _9997, _9996, _9995
 * 	{@link com.enton.custom.sys.BusinessException.SysCode}
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 常用异常
	 * @_0000: 响应成功
	 * @_9999: 系统级别未知异常
	 * @_9998: 数据库操作异常(自定义SQL语句执行出错)
	 * @_9997: 登陆超时
	 * @_9996: 数据库操作异常(hibernate)
	 * @_9995: 无权限操作
	 * @_9993: 查询数据为空, 自定义提示信息
	 * @_9992: 请求资源已被拦截, 拒绝连接
	 * @_9991: 系统资源正在初始化, 请稍等
	 * @_9990: 接入终端未绑定系统用户
	 */
	public static final BusinessException _9999, _9998, _9997, _9996, _9995, _9993, _9992, _9991, _9990;
	static{
		_9999 = new BusinessException(SysCode._9999.getLv(), SysCode._9999.getValue(), SysCode._9999.getText());
		_9998 = new BusinessException(SysCode._9998.getLv(), SysCode._9998.getValue(), SysCode._9998.getText());
		_9997 = new BusinessException(SysCode._9997.getLv(), SysCode._9997.getValue(), SysCode._9997.getText());
		_9996 = new BusinessException(SysCode._9996.getLv(), SysCode._9996.getValue(), SysCode._9996.getText());
		_9995 = new BusinessException(SysCode._9995.getLv(), SysCode._9995.getValue(), SysCode._9995.getText());
		_9993 = new BusinessException(SysCode._9993.getLv(), SysCode._9993.getValue(), SysCode._9993.getText());
		_9992 = new BusinessException(SysCode._9992.getLv(), SysCode._9992.getValue(), SysCode._9992.getText());
		_9991 = new BusinessException(SysCode._9991.getLv(), SysCode._9991.getValue(), SysCode._9991.getText());
		_9990 = new BusinessException(SysCode._9990.getLv(), SysCode._9990.getValue(), SysCode._9990.getText());
	}
	
	
	private static class BeanEnum{
		private String value;
		private String text;
		
		public String getValue() {
			return value;
		}
		public String getText() {
			return text;
		}
		
		protected void init(String value, String text) {
			this.value = value;
			this.text = text;
		}
		
		@Override
		public String toString(){
			return getText();
		}
	}
	/**
	 * 错误级别
	 * @author WZY.
	 * @EXCEPTION 异常
	 * @WARNING 警告
	 * @INFO 提示
	 * @DEBUG 调试
	 */
	public static class Level extends BeanEnum{
		protected Level(String value, String text) {
			super.init(value, text);
		}
		
		public static final Level EXCEPTION, WARNING, INFO, DEBUG;
		static {
			EXCEPTION = new Level("EXCEPTION", "异常");
			WARNING = new Level("WARNING", "警告");
			INFO = new Level("INFO", "提示");
			DEBUG = new Level("DEBUG", "调试");
		}
		
		@Override
		public String toString(){
			return this.getValue() + " : " + this.getText();
		}
		
		/**
		 * 是否普通正常级别: WARNING、INFO、DEBUG
		 * @return
		 */
		public boolean isNormal(){
			return equals(Level.WARNING) || equals(Level.INFO) || equals(Level.DEBUG);
		}
		
		public boolean isInfo(){
			return equals(Level.INFO);
		}
		public boolean isWarning(){
			return equals(Level.WARNING);
		}
		public boolean isDebug(){
			return equals(Level.DEBUG);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Level)){
				return false;
			}
			Level o = (Level) obj;
			
			return this.getValue().equals(o.getValue());
		}
	}
	
	/**
	 * 系统级别错误码
	 * @author WZY.
	 * @_0000: 响应成功
	 * @_9999: 系统级别未知异常
	 * @_9998: 数据库操作异常(自定义SQL语句执行出错)
	 * @_9997: 登陆超时
	 * @_9996: 数据库操作异常(hibernate)
	 * @_9995: 无权限操作
	 * @_9993: 查询数据为空, 自定义提示信息
	 * @_9992: 请求资源已被拦截, 拒绝连接
	 * @_9991: 系统资源正在初始化, 请稍等
	 * @_9990: 接入终端未绑定系统用户
	 */
	public static class SysCode extends BeanEnum{
		private String alias;
		private Level lv;
		
		public String getAlias(){
			return this.alias;
		}
		public Level getLv() {
			return lv;
		}
		
		protected SysCode(Level lv, String value, String text, String alias) {
			super.init(value, text);
			this.lv = lv;
			this.alias = alias;
		}
		
		/**
		 * _0000: 响应成功	<br>
		 * _9999: 系统级别未知异常 <br>	
		 * _9998: 数据库操作异常(自定义SQL语句执行出错) <br>
		 * _9997: 登陆超时 <br>
		 * _9996: 数据库操作异常(hibernate) <br>
		 * _9995: 无权限操作 <br>
		 * _9993: 查询数据为空, 自定义提示信息 <br>
		 * _9992: 请求资源已被拦截, 拒绝连接 <br>
		 * _9991: 系统资源正在初始化, 请稍等 <br>
		 * _9990: 接入终端未绑定系统用户 <br>
		 */
		public static final SysCode _0000, _9999, _9998, _9997, _9996, _9995, _9993, _9992, _9991, _9990;
		static{
			_0000 = new SysCode(Level.INFO, "0000", "响应成功.", null);
			_9999 = new SysCode(Level.EXCEPTION, "9999", "系统级别未知异常.", null);
			_9998 = new SysCode(Level.EXCEPTION, "9998", "数据库操作异常(自定义SQL语句执行出错).", null);
			_9997 = new SysCode(Level.EXCEPTION, "9997", "登陆超时, 请重新登陆.", null);
			_9996 = new SysCode(Level.EXCEPTION, "9996", "数据库操作异常(hibernate).", null);
			_9995 = new SysCode(Level.EXCEPTION, "9995", "无权限操作.", null);
			_9993 = new SysCode(Level.EXCEPTION, "9993", "查询数据为空, 自定义提示信息.", null);
			_9992 = new SysCode(Level.EXCEPTION, "9992", "请求资源已被拦截, 拒绝连接.", null);
			_9991 = new SysCode(Level.EXCEPTION, "9991", "系统资源正在初始化, 请稍等.", null);
			_9990 = new SysCode(Level.EXCEPTION, "9990", "接入终端未绑定系统用户", null);
		}
	}
	
	private String code;
	private String msg;
	private Level lv;
	private static Map<String, String> msgData;
	
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Level getLv() {
		return lv;
	}
	public void setLv(Level lv) {
		this.lv = lv;
	}
	
	private BusinessException(Level lv, String code, String msg){
		super(msg);
		this.lv = lv;
		this.code = code;
		this.msg = msg;
	}
	private BusinessException(String code, String msg){
		this(Level.EXCEPTION, code, msg);
	}
	
	/**
	 * 初始化错误信息数据源
	 * @param msgData
	 */
	public synchronized static void initMsgData(Map<String, String> msgData){
		BusinessException.msgData = msgData;
	}
	
	/**
	 * 自定义简单错误信息
	 * @param define	错误信息
	 * @return	异常实例
	 */
	public static BusinessException define(String define){
		return new BusinessException(null, define);
	}
	
	/**
	 * 自定义简单错误信息
	 * @param define	错误信息
	 * @param code 		自定义错误代码
	 * @return	异常实例
	 */
	public static BusinessException define(String code, String define){
		return new BusinessException(code, define);
	}
	/**
	 * 自定义简单错误信息
	 * @param lv		错误级别, 默认是异常级别
	 * @param define	错误信息
	 * @return	异常实例
	 */
	public static BusinessException define(Level lv, String define){
		lv = lv == null ? Level.EXCEPTION : lv;
		return new BusinessException(lv, lv.getValue(), define);
	}
	
	/**
	 * 自定义简单错误信息
	 * @param lv		错误级别, 默认是异常级别
	 * @param code 		自定义错误代码
	 * @param define	错误信息
	 * @return	异常实例
	 */
	public static BusinessException define(Level lv, String code, String define){
		code = code == null ? Level.EXCEPTION.getText() : code;
		return new BusinessException(lv, code, define);
	}
	
	/**
	 * 根据错误码找到并绑定异常信息
	 * @param code	异常代码
	 * @param values	异常信息模版替换值
	 * @return	组装完整的异常信息
	 */
	private static String bind(String code, String... values){
		String modal = msgData.get(code);
		if(modal != null){
			if(values.length > 0){
				for(int i = 0; i < values.length; i++){
					modal = modal.replaceAll("&" + (i + 1), values[i] != null ? values[i] : "");
				}					
			}
		}else{
			modal = code;
		}
		Throwable ta = new Throwable();
		StackTraceElement ste = null;
		for(StackTraceElement temp : ta.getStackTrace()){
			if(temp.getClassName().startsWith("com.enton", 0) && !temp.getMethodName().equals("intercept")){
				ste = temp;
			}
		}
		if(ste == null){
			ste = ta.getStackTrace()[0];
		}
		if(code.equals(modal.trim())){
			return code;
		}
		return code + ": " + modal.trim();
	}
	
	/**
	 * 根据错误码获取错误信息原型
	 * @param code	错误码
	 * @return	错误信息原型
	 */
	public static String getMsg(String code){
		return bind(code, "");
	}
	
	/**
	 * 根据错误码获取错误信息
	 * @param code	错误码
	 * @param values	错误信息原型替换值
	 * @return	错误信息
	 */
	public static String getMsg(String code, String... values){
		return bind(code, values);
	}
	
	/**
	 * 普通错误提示
	 * @param code 错误码
	 * @return 异常实例
	 */
	public static BusinessException info(String code){
		return info(code, "");
	}
	/**
	 * 普通错误提示
	 * @param code	错误码
	 * @param values 错误信息模板值
	 * @return 异常实例
	 */
	public static BusinessException info(String code, String... values){
		return new BusinessException(Level.INFO, code, bind(code, values));
	}
	/**
	 * 获取数据为空普通异常提示
	 * @return
	 */
	public static BusinessException infoByNullForData(){
		return new BusinessException(Level.INFO, "GSHVNOD", bind("GSHVNOD"));
	}
	
	/**
	 * 警告信息
	 * @param code
	 * @return
	 */
	public static BusinessException warn(String code){
		return warn(code, "");
	}
	/**
	 * 警告信息
	 * @param code
	 * @return
	 */
	public static BusinessException warn(String code, String... values){
		return new BusinessException(Level.WARNING, code, bind(code, values));
	}
	
	/**
	 * 调试信息
	 * @param code
	 * @return
	 */
	public static BusinessException debug(String code){
		return debug(code, "");
	}
	/**
	 * 调试信息
	 * @param code
	 * @return
	 */
	public static BusinessException debug(String code, String... values){
		return new BusinessException(Level.DEBUG, code, bind(code, values));
	}
	
	/**
	 * 错误信息
	 * @param code
	 * @return
	 */
	public static BusinessException err(String code){
		return err(code, "");
	}
	/**
	 * 错误信息
	 * @param code
	 * @return
	 */
	public static BusinessException err(String code, String... values){
		return new BusinessException(Level.EXCEPTION, code, bind(code, values));
	}

}
