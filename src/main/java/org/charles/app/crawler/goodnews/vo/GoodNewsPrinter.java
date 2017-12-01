package org.charles.app.crawler.goodnews.vo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class GoodNewsPrinter implements StopCrawlerListener<List<GoodNewsMsg>>{
	
	public String filePath;
	
	public GoodNewsPrinter(String filePath){
		this.filePath = filePath;
	}

	@Override
	public void beforeStart() {
		
	}

	@Override
	public void end(List<GoodNewsMsg> data) {
		File file = new File(filePath);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			
			pw.println("<html>");
			pw.println("<head></head><style type=\"text/css\">.stockMsgLink{padding: 0 10px;}</style>");
			pw.println("<body>");
			Collections.sort(data);
			
			for(GoodNewsMsg mu : data){
				LinkMsg title = mu.getTitle();
				String releaseTime = mu.getReleaseTime();		//发布时间
				
				List<LinkMsg> stockMsgs = mu.getStockMsg();
				
				pw.println("<h3>");
				pw.println("<span>发布时间：" + releaseTime + "</span>");
				pw.print("<a href=\"" + title.getVisitUrl() + "\" target=\"_blank\">" + title.getText() + "</a>");
				pw.println("</h3>");
				if(stockMsgs != null){
					pw.println("<div>");
					for(LinkMsg stockMsg : stockMsgs){
						pw.println("<a class=\"stockMsgLink\" href=\"" + stockMsg.getVisitUrl() + "\" target=\"_blank\">" + stockMsg.getText() + "</a>");
					}
					pw.println("</div>");
				}
			}
			pw.println("<body>");
			pw.println("</html>");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.close();
			}
		}
	}
}
