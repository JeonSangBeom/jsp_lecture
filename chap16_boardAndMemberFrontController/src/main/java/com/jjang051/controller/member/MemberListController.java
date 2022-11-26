package com.jjang051.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.controller.AbstractController;
import com.jjang051.front.ModelAndView;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.PageWriter;

public class MemberListController implements AbstractController {

	@Override
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "member/list";
		ModelAndView mav = new ModelAndView(nextPage);
		MemberDao memberDao = new MemberDao();

		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if (tempClickPage == null) {
			tempClickPage = "1";
		}
		int clickPage = Integer.parseInt(tempClickPage);

		int totalPage = memberDao.getTotal(search_select, search_word); // db에 저장되어 있는 페이지 갯수

		int listPerPage = 5; // 한번에 뿌려질 페이지리스트 갯수
		int pageBlock = 3; // pagination에 뿌려질 페이지 숫자 < 1/2/3 > < 4/5/6 >
		
		int start = (clickPage - 1) * listPerPage + 1; // dao 전달되는 변수
		int end = start + listPerPage - 1; // dao 전달되는 변수

		List<MemberDto> memberList = memberDao.getAllMemberList(start, end, search_select, search_word);

		// 페이지 관련 속성들
		//int total,int listPerPage, int pageBlock, int clickPage, String requsetURL
		String page="";
		page = PageWriter.pageWrite(totalPage, listPerPage, pageBlock, clickPage, "MemberList.do");
		
		mav.addObject("page",page);
		mav.addObject("memberList", memberList);
		mav.addObject("totalPage",totalPage);
		
		return mav;
	}

}
