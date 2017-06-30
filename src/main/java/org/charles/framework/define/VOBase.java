package org.charles.framework.define;

import java.util.ArrayList;
import java.util.List;

/**
 * 底层对接协议
 * @date 2014年11月5日
 */
public class VOBase {

	
	private int total;			// 数据总行数
	private List<?> items = new ArrayList<Object>();	// 数据主体
	
	public VOBase() {}
	
	public VOBase(int total, List<?> items) {
		init(total, items);
	}
	
	public void init(int total, List<?> items){
		this.total = total;
		this.items = items;
	}
	
	public boolean itemsIsEmpty(){
		return total == 0;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
}
