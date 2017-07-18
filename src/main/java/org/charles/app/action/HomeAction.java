package org.charles.app.action;

import org.charles.app.timer.task.DataExtractTask;
import org.charles.framework.define.JObject;
import org.charles.framework.support.view.FastJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mb/home")
public class HomeAction{
	
	@Autowired
	private DataExtractTask dataExtractTask;

	@RequestMapping("/")
	public String index(){
		return "mobile/home/index";
	}
	
	
	@RequestMapping("/refresh")
	public ModelAndView refresh(){
		JObject jobj = new JObject();
		dataExtractTask.run();
		
		return FastJsonView.build(jobj);
	}
	
}
