package org.charles.app.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.charles.app.dao.StockAnalysisDao;
import org.charles.app.pojo.BoardData;
import org.charles.app.pojo.Stock;
import org.charles.framework.define.JObject;
import org.charles.framework.support.view.FastJsonView;
import org.charles.framework.util.DateUtil;
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
	public ModelAndView orgCountData(HttpServletRequest request, HttpServletResponse response){
//		Date date = SysDateUtil.getStockDate();
		Date date = DateUtil.addDay(new Date(), -1);
		List<BoardData> data = stockAnalysisDao.findCmpCount(date, date, Stock.SPECIAL_ORG_KEY);
		JObject jobj = new JObject();
		jobj.setItems(data);
		jobj.putOther("date", DateUtil.convertDateToString(date));
		
		return FastJsonView.build(jobj);
	}
	
	
	@RequestMapping("/newTopBoard")
	public String newTopBoard(){
		System.out.println("newTopBoard");
		return "mobile/board/newTopBoard";
	}
	@RequestMapping("/newTopBoardData")
	public ModelAndView newTopBoardData(){
//		Date date = SysDateUtil.getStockDate();
		Date date = DateUtil.addDay(new Date(), -1);
		List<BoardData> data = stockAnalysisDao.findNewTopBoard(date);
		JObject jobj = new JObject();
		jobj.putOther("date", DateUtil.convertDateToString(date));
		jobj.setItems(data);
		
		return FastJsonView.build(jobj);
	}
	
	
	
	@RequestMapping("/breakThroughBoard")
	public String breakThroughBoard(){
		System.out.println("breakThroughBoard");
		return "mobile/board/breakThroughBoard";
	}
	@RequestMapping("/breakThroughBoardData")
	public ModelAndView breakThroughBoardData(){
//		Date date = SysDateUtil.getStockDate();
		Date date = DateUtil.addDay(new Date(), -1);
		List<BoardData> data = stockAnalysisDao.findCmpCount(date, date, Stock.SPECIAL_ORG_KEY);
		JObject jobj = new JObject();
		jobj.setItems(data);
		
		return FastJsonView.build(jobj);
	}
	
}
