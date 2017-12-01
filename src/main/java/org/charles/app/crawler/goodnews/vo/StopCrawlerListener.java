package org.charles.app.crawler.goodnews.vo;

/**
 * 爬虫监听接口
 * @author Ych
 * 2017年12月1日
 * @param <E>
 */
public interface StopCrawlerListener<E> {

	/**
	 * 开始爬取前
	 * @author Ych
	 */
	void beforeStart();
	
	/**
	 * 停止后
	 * @author Ych
	 * @param e
	 */
	void end(E e);
}
