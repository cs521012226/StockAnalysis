package org.charles.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.charles.app.service.BoardDataService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestAction extends AbstractController{
	
//	private BoardDataService boardDataService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("testAction");
		return null;
	}

	/*public BoardDataService getBoardDataService() {
		return boardDataService;
	}

	public void setBoardDataService(BoardDataService boardDataService) {
		this.boardDataService = boardDataService;
	}*/
	
}
