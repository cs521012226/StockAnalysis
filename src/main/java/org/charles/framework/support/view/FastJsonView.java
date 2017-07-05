package org.charles.framework.support.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSONObject;

public class FastJsonView implements View {
	
	private Object data;
	
	public FastJsonView(Object data){
		this.data = data;
	}
	
	@Override
	public String getContentType() {
		return "text/html; charset=utf-8";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = JSONObject.toJSONString(data);
		response.setContentType(getContentType());
		PrintWriter out = response.getWriter();
		out.println(str);
	}
	
	public static ModelAndView build(Object data){
		return new ModelAndView(new FastJsonView(data));
	}
}
