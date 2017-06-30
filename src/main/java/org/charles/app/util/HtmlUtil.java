package org.charles.app.util;

import java.io.IOException;

import org.charles.framework.define.Constants;
import org.charles.framework.exp.BusinessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {

	
	public static Document getDoc(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url).userAgent(Constants.USER_AGENT).timeout(10 * 1000).get();
		} catch (IOException e1) {
			throw BusinessException.define("发送" + url + "请求出错");
		}
		return doc;
	}
}
