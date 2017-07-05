package org.charles.app.action;

import java.util.Date;
import java.util.List;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Stock;
import org.charles.app.util.SysDateUtil;
import org.charles.framework.define.JObject;
import org.charles.framework.support.view.FastJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mb/board")
public class BoardAction{
	
	@Autowired
	private StockAnalysisDao stockAnalysisDao;

	@RequestMapping("/orgCount")
	public String orgCount(){
		return "mobile/board/orgCount";
	}
	
	@RequestMapping("/orgCountData")
	public ModelAndView orgCountData(){
		Date date = SysDateUtil.getStockDate();
		List<BoardData> data = stockAnalysisDao.findCmpCount(date, date, Stock.SPECIAL_ORG_KEY, 3);
		JObject jobj = new JObject();
		jobj.setItems(data);
		
		return FastJsonView.build(jobj);
	}
	
	
	@RequestMapping("/newTopBoard")
	public String newTopBoard(){
		System.out.println("newTopBoard");
		return "mobile/board/newTopBoard";
	}
	@RequestMapping("/breakThroughBoard")
	public String breakThroughBoard(){
		System.out.println("breakThroughBoard");
		return "mobile/board/breakThroughBoard";
	}

	
}
