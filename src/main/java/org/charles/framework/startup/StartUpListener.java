package org.charles.framework.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.charles.app.timer.DataExtractTimer;
import org.charles.app.util.SysParamUtil;

/**
 * 服务器启动
 * @author Charles
 *
 */
public class StartUpListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//刷新参数
		SysParamUtil.refresh();
		
		//定时任务
//		DataExtractTimer.start();         
		
	}
}
