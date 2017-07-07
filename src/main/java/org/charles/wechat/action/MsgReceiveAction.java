package org.charles.wechat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.charles.wechat.basic.BasicProcess;
import org.charles.wechat.basic.ConnectServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接收微信推送消息
 * @author YeChao
 */
@Controller
@RequestMapping("/wechat/msg")
public class MsgReceiveAction {
//	private static Logger logger = Logger.getLogger(MsgReceiveAction.class);
	@Autowired
	private BasicProcess basicProcess;
	
	@RequestMapping("/receive")
	public void receive(HttpServletRequest req, HttpServletResponse resp){
		ConnectServer connectServer = new ConnectServer(req, resp);
		connectServer.response();
	}
	
}
