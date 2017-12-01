package org.charles.app.crawler.goodnews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.charles.app.crawler.goodnews.vo.GoodNewsMsg;
import org.charles.app.crawler.goodnews.vo.StopCrawlerListener;
import org.charles.framework.util.DateUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 同花顺利好消息爬虫
 * @author Ych
 * 2017年12月1日
 */
public class GoodNewsCrawler extends BreadthCrawler{

	private StopCrawlerListener<List<GoodNewsMsg>> listener;
	private List<GoodNewsMsg> goodNewsMsgs = Collections.synchronizedList(new ArrayList<GoodNewsMsg>());
	
	public GoodNewsCrawler(){
		super("GoodNewsCrawler", true);
		
		addSeed(new CrawlDatum("http://stock.10jqka.com.cn").meta("depth", 1));
        
        String nowStr = DateUtil.convertDateToString(new Date(), DateUtil.Pattern.DATE8.getPattern());
        
        addRegex("http://stock.10jqka.com.cn/" + nowStr + "/.*html");
	}

	
	@Override
	public void visit(Page page, CrawlDatums arg1) {
		Elements els = page != null ? page.select(".news_tag.news_tag1") : null;
    	if(els == null || els.isEmpty()){
    		return;
    	}
    	String releaseTime = page.selectText("#pubtime_baidu");
    	String title = page.selectText(".atc-head h1");
    	GoodNewsMsg goodnews = new GoodNewsMsg(title, releaseTime, page.url());
    	
    	els = page.select("a.singleStock");
    	
    	System.out.println("URL: " + page.url() + ", pushTime: " + releaseTime + ", depth: " + page.meta("depth"));
    	
    	Iterator<Element> it = els.iterator();
    	while(it.hasNext()){
    		Element alink = it.next();
    		String stockName = alink.text();
    		String visitUrl = alink.attr("href");
    		
    		Element sib = alink.nextElementSibling();
    		String stockCode = sib.text();
    		
    		goodnews.addStockMsg(stockName + "(" + stockCode + ")", visitUrl);
    	}
    	goodNewsMsgs.add(goodnews);
	}
	
	 @Override
	public void start(int depth) throws Exception {
		if (listener != null) {
			listener.beforeStart();
		}
		super.start(depth);
	}


	@Override
    protected void afterParse(Page page, CrawlDatums next) {
	    //当前页面的depth为x，则从当前页面解析的后续任务的depth为x+1
	    int depth = 1;
	    //如果在添加种子时忘记添加depth信息，可以通过这种方式保证程序不出错
	    try {
	        depth = page.metaAsInt("depth");
	    } catch (Exception ex) {
	
	    }
	    depth++;
	    next.meta("depth", depth);
    }

	@Override
	public void afterStop() {
		if(listener != null){
			listener.end(goodNewsMsgs);
		}
	}


	public void setListener(StopCrawlerListener<List<GoodNewsMsg>> listener) {
		this.listener = listener;
	}
	
}
