package org.charles.wechat.vo;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 图文消息实体
 * @author YeChao
 */
public class NewsMsg extends AbstractMessage {
	
	public NewsMsg(){
		super(MsgType.NEWS);
	}
	
	
	public class Item{
		private String title;		//	否	图文消息标题
		private String description;		//	否	图文消息描述
		private String picUrl;		//	否	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		private String url;		//	否	点击图文消息跳转链接
		
		public Item(){
			
		}
		
		public Item(String title, String description, String picUrl, String url){
			this.title = title;
			this.description = description;
			this.picUrl = picUrl;
			this.url = url;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	}
	
	
	
	@Override
	public void parseDom(Document doc) {
		throw new UnsupportedOperationException();
	}


	/**
	 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[news]]></MsgType>
<ArticleCount>2</ArticleCount>
<Articles>
<item>
<Title><![CDATA[title1]]></Title> 
<Description><![CDATA[description1]]></Description>
<PicUrl><![CDATA[picurl]]></PicUrl>
<Url><![CDATA[url]]></Url>
</item>
<item>
<Title><![CDATA[title]]></Title>
<Description><![CDATA[description]]></Description>
<PicUrl><![CDATA[picurl]]></PicUrl>
<Url><![CDATA[url]]></Url>
</item>
</Articles>
</xml>
	 */
	@Override
	public Document buildDom() {
		Document result = DocumentHelper.createDocument();
		
		Element root = result.addElement("xml");
		
		root.addElement("ToUserName").addCDATA(getToUserName());
		root.addElement("FromUserName").addCDATA(getFromUserName());
		root.addElement("CreateTime").addText(String.valueOf(getCreateTime()));
		root.addElement("MsgType").addCDATA(getMsgType().getType());
		root.addElement("ArticleCount").addCDATA(String.valueOf(getArticleCount()));
		
		Element articlesEl = root.addElement("Articles");
		for(Item item : articles){
			Element itemEl = articlesEl.addElement("item");
			itemEl.addElement("Title").addCDATA(item.getTitle());
			itemEl.addElement("Description").addCDATA(item.getDescription());
			itemEl.addElement("PicUrl").addCDATA(item.getPicUrl());
			itemEl.addElement("Url").addCDATA(item.getUrl());
		}
		return result;
	}


	private int articleCount;		//	是	图文消息个数，限制为8条以内
	private List<Item> articles = new ArrayList<Item>();
	
	
	public void addItem(String title, String description, String picUrl, String url){
		articles.add(new Item(title, description, picUrl, url));
	}
	
	public void clearItem(){
		articles.clear();
	}


	public int getArticleCount() {
		return articleCount;
	}


	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}


	public List<Item> getArticles() {
		return articles;
	}


	public void setArticles(List<Item> articles) {
		this.articles = articles;
	}
	
}
