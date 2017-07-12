package org.charles.wechat.handler;

import org.charles.app.analysis.StockAnalysisManage;
import org.charles.app.msg.TextTemplate;
import org.charles.wechat.vo.AbstractMessage;
import org.charles.wechat.vo.EventType;
import org.charles.wechat.vo.MsgType;
import org.charles.wechat.vo.TextMsg;
import org.dom4j.Document;

/**
 * 文本消息处理
 * @author YeChao
 */
public class TextMsgHandler extends AbstractMsgHandler{
	
	private StockAnalysisManage stockAnalysisManage;

	@Override
	protected boolean canHandle(MsgType msgType, EventType eventType) {
		return MsgType.TEXT.equals(msgType);
	}

	@Override
	protected AbstractMessage handleInternal(Document inDoc) {
		
		TextTemplate textTpl = stockAnalysisManage.process();
		
		TextMsg textMsg = new TextMsg();
		textMsg.parseDom(inDoc);
		textMsg.setContent(textTpl.format());
		textMsg.exchangeUser(textMsg);
		
		return textMsg;
	}

	public StockAnalysisManage getStockAnalysisManage() {
		return stockAnalysisManage;
	}

	public void setStockAnalysisManage(StockAnalysisManage stockAnalysisManage) {
		this.stockAnalysisManage = stockAnalysisManage;
	}
}
