package org.charles.wechat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.charles.wechat.basic.BasicProcess;
import org.charles.wechat.basic.ConnectServer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 接收微信推送消息
 * @author YeChao
 */
public class MsgReceiveAction extends AbstractController{
//	private static Logger logger = Logger.getLogger(MsgReceiveAction.class);
	private BasicProcess basicProcess;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		basicProcess.process(req.getInputStream(), resp.getOutputStream());

		ConnectServer connectServer = new ConnectServer(req, resp);
		connectServer.response();
		return null;
	}

	public void setBasicProcess(BasicProcess basicProcess) {
		this.basicProcess = basicProcess;
	}
	
}
