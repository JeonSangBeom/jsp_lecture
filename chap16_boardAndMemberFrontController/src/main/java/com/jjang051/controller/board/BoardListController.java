package com.jjang051.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.PageWriter;

//POJO (Plain Old Java Object) 순수 자바 클래스
public class BoardListController implements AbstractController {
	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nextPage = "board/board_list"; //넘길 페이지
		ModelAndView mav = new ModelAndView(nextPage);
		
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if(search_select==null || search_select=="") search_select="";
		if(search_word==null || search_word=="") search_word="";
		if(tempClickPage==null) {
			tempClickPage = "1";
		}
		int clickPage = Integer.parseInt(tempClickPage);
		
		int totalPage = replyBoardDao.getTotal(search_select,search_word); //db에 저장되어 있는 페이지 갯수
		
		int listPerPage = 5;
		int pageBlock = 3;
		
		int start = (clickPage-1)*listPerPage+1;
		int end = start+listPerPage -1 ;
		
		List<ReplyBoardDto> boardList = replyBoardDao.getAllList(start,end,search_select,search_word);
		
		//페이지 관련 속성들
		//int total,int listPerPage, int pageBlock, int clickPage, String requsetURL
		String page="";
		
		
		if(search_word!=null && search_word!="") {
			page = PageWriter.pageWrite(totalPage, listPerPage, pageBlock, clickPage, 
					"BoardList.do?search_select="+search_select+"&search_word="+search_word);
			//검색결과가 있을떄 달고 갈 수 있게 
		} else {
			page = PageWriter.pageWrite(totalPage, listPerPage, pageBlock, clickPage, "BoardList.do");
		}
		mav.addObject("boardList", boardList);
		mav.addObject("page",page);
		mav.addObject("totalPage",totalPage);
		mav.addObject("listPerPage",listPerPage);
		mav.addObject("clickPage",clickPage);
		
		
		return mav;
	}
}
