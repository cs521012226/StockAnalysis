package org.charles.app.action;

import org.charles.wechat.basic.BasicProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAction{
	
	@Autowired
	private BasicProcess basicProcess;

	@RequestMapping("/mb/home")
	public String index(){
		return "mobile/home/index";
	}

	
}
