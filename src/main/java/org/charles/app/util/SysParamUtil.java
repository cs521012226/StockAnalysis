package org.charles.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.charles.app.pojo.SysParam;
import org.charles.app.service.SysParamQuery;

/**
 * 系统参数工具类
 * @author Charles
 *
 */
public class SysParamUtil {
	
	private static List<SysParamQuery> sysParamQueryList;
	public void setSysParamQueryList(List<SysParamQuery> sysParamQueryList) {
		SysParamUtil.sysParamQueryList = sysParamQueryList;
	}
	private SysParamUtil(){}
	
	private static Map<String, List<SysParam>> source = new HashMap<String, List<SysParam>>();
	
	/**
	 * 根据参数编码获取相关参数
	 * @param key 参数编码
	 * @return
	 */
	public static List<SysParam> getByCode(String key){
		// TODO 后续加入缓存算法, 提升缓存命中率
		return source.get(key);
	}
	
	/**
	 * 获取指定参数详细信息 
	 * @param code
	 * @param value
	 * @return
	 */
	public static SysParam getByPrimaryKey(String code, Object value){
		List<SysParam> list = getByCode(code);
		SysParam sp = null; 
		if(list != null && !list.isEmpty()){
			for (SysParam sysParam : list) {
				if(sysParam.getValue().equals(value)){
					sp = sysParam;
					break;
				}
			}
		}
		return sp;
	}
	
	/**
	 * 获取单个参数值，如果是列表则返回第一个
	 * @param code
	 * @param value
	 * @return
	 */
	public static SysParam getByPrimaryKeyForOne(String code){
		List<SysParam> list = getByCode(code);
		SysParam sp = null; 
		if(list != null && !list.isEmpty()){
			sp = list.get(0);
		}
		return sp;
	}
	
	/**
	 * 获取指定编码的参数集合
	 * @param codes
	 * @return
	 */
	public static HashMap<String, List<SysParam>> getByCodes(String[] codes){
		HashMap<String, List<SysParam>> list = new HashMap<String, List<SysParam>>();
		for (String c : codes) {
			if(getByCode(c) != null){
				list.put(c, getByCode(c));
			}
		}
		return list;
	}
	
	/**
	 * 刷新参数
	 */
	public synchronized static void refresh(){
		try {
			// 清理内存旧数据
			source.clear();
			
			for(SysParamQuery sysQuery : sysParamQueryList){
				// 处理新数据并加入内存缓存
				List<SysParam> all = sysQuery.querySysParamList();
				List<SysParam> temp = null;
				SysParam item;
				
				for(int i = all.size() - 1; i >= 0 ; i--){
					item = all.get(i);
					
					String code = item.getCode();
					temp = source.get(code);
					
					if(temp != null){
						temp.add(item);
					}else{
						temp = new ArrayList<SysParam>();
						temp.add(item);
						source.put(code, temp);
					}
					all.remove(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
