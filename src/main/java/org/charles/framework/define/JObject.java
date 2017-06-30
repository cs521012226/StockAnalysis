package org.charles.framework.define;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.charles.framework.exp.BusinessException;

import com.alibaba.fastjson.JSONObject;

/**
 * 通用对接协议
 * @author Administrator
 *
 */
public class JObject extends VOBase{
	
	private static Logger logger = Logger.getLogger(JObject.class);
	
	private boolean success = true;	// 操作是否成功
	private String code = "0000";	// 操作响应码
	private String title = "响应成功";	// 操作信息标题
	private String msg;				// 操作信息
	private Map<String, Object> other;// 其他附加信息
	
	public void init(boolean success, String code, String title, String msg, int total, List<?> items){
		this.success = success;
		this.code = code;
		this.title = title;
		this.msg = msg;
		super.init(total, items);
	}
	
	@Override
	public void init(int total, List<?> items){
		init(true, BusinessException.SysCode._0000.getValue(), null, null, total, items);
	}
	/**
	 * 初始化普通定制数据
	 */
	public JObject(){}
	
	/**
	 * 初始化数据列表数据
	 * @param success
	 * @param code
	 * @param title
	 * @param msg
	 */
	public JObject(boolean success, String code, String title, String msg){
		init(success, code, title, msg, 0, null);
	}
	
	/******************** 初始化操作信息 ********************/
	public void initMsg(boolean success, String code, String title, String msg){
		this.success = success;
		this.code = code;
		this.title = title;
		this.msg = msg;
	}
	public void initMsg(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
	public void initMsg(boolean success, String code, String msg){
		this.success = success;
		this.code = code;
		this.msg = msg;
	}
	public void initMsg(BusinessException e){
		if(e.getLv().isNormal()){
			initMsg(true, e.getCode(), e.getLv().getText(), e.getMsg());
			if(e.getLv().isDebug()){
				logger.debug(e.getMsg(), e);
			}else if(e.getLv().isWarning()){
				logger.warn(e.getMsg(), e);
			}else{
				logger.info(e.getMsg());
			}
		}else{
			logger.error(e.getMsg(), e);
			initMsg(false, e.getCode(), e.getLv().getText(), e.getMsg());
		}
	}
	public void initMsg(SQLException e){
		logger.error(e.getMessage(), e);
		initMsg(false, BusinessException.SysCode._9998.getValue(), BusinessException.SysCode._9998.getLv().getText(), BusinessException.SysCode._9998.getText() + ". " + e.getMessage());
	}
	public void initMsg(Exception e){
		logger.error(e.getMessage(), e);
		initMsg(false, BusinessException.SysCode._9999.getValue(), BusinessException.SysCode._9999.getLv().getText(), BusinessException.SysCode._9999.getText() + ". " + e.getMessage());
	}
	
	/**
	 * 转换成标准的JSON格式数据
	 */
	@Override
	public String toString() {
		return JSONObject.toJSONString(toMap(0));
	}
	
	/**
	 * 定制返回结果模型
	 * @param flag	结果模型标识
	 * <br>0	返回全部字段(默认)
	 * <br>1	最简模型 / 数据模型: success、numRows、items
	 * <br>2	最简信息模型: success、msg、other
	 * <br>3	信息模型: success、code、title、msg、other
	 * <br>4	图表模型: success、xAxis、series、msg、other
	 * @return
	 */
	public String toString(int flag){
		return JSONObject.toJSONString(toMap(flag));
	}
	
	public Map<String, Object> toMap(int flag){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		map.put("success", success);
		
		if(flag == 1){
			map.put("total", getTotal());
			map.put("items", getItems());
		}else if(flag == 2){
			map.put("code", code);
			map.put("msg", msg);
			map.put("other", other);
		}else if(flag == 3){
			map.put("code", code);
			map.put("title", title);
			map.put("msg", msg);
			map.put("other", other);
		}else{
			map.put("total", getTotal());
			map.put("items", getItems());
			map.put("code", code);
			map.put("title", title);
			map.put("msg", msg);
			map.put("other", other);
		}
		
		return map;
	}
	
	/**
	 * 转换前台树形需要的树形格式
	 * @param flag
	 * @param label
	 * @return
	 */
	public String toJsonTree(String label){
		Map<String, Object> map = toMap(1);
		map.put("label", label);
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 添加附加返回信息
	 * @param key
	 * @param value
	 */
	public void putOther(String key, Object value){
		if(other == null){
			other = new LinkedHashMap<String, Object>();
		}
		other.put(key, value);
	}
	/**
	 * 查找附加信息
	 * @param key
	 * @return
	 */
	public Object findOther(Object key){
		return other == null ? null : other.get(key);
	}
	
	/**
	 * 清除已有设置
	 */
	public void clear(){
		this.success = true;
		this.code = null;
		this.msg = null;
		this.title = null;
		this.setTotal(0);
		if(this.other != null && !this.other.isEmpty()){
			this.other.clear();
		}
		if(this.getItems() != null && !this.getItems().isEmpty()){
			this.getItems().clear();
		}
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getOther() {
		return other;
	}
	
}
