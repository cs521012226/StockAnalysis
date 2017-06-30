package org.charles.app.msg.impl;

import java.util.ArrayList;
import java.util.List;

import org.charles.app.msg.TextTemplate;
import org.charles.framework.define.Constants;

/**
 * 短信模版
 * @author Charles
 *
 */
public class MessageTextTemplate implements TextTemplate {
	
	private List<String> data = new ArrayList<String>();
	
	@Override
	public void appendText(String text) {
		data.add(text);
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public String format() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<data.size(); i++){
			sb.append(i + 1).append(": ").append(data.get(i)).append(Constants.LF);
		}
		
		return sb.toString();
	}
	
}
