package org.charles.app.msg;

/**
 * 文本模版
 * @author Charles
 *
 */
public interface TextTemplate {

	/** 
	 * 追加文本
	 * @Author: Charles
	 * @param text
	 */
	public void appendText(String text);
	
	/** 
	 * 情况数据
	 * @Author: Charles
	 */
	public void clear();
	/** 
	 * 格式化获取的文本
	 * @Author: Charles
	 * @return
	 */
	public String format();
}
