package com.jjang051.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.util.ScriptWriter;

@WebServlet("/BoardSearchList.do")
public class BoardSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardSearchListController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		// boardlist에서 지정한 name 가져와서 실어주기

		System.out.println("search_select==>" + search_select);
		System.out.println("search_word==>" + search_word);

		if (search_word.equals("") || search_word == null) {
			ScriptWriter.alertAndBack(response, "검색어를 입력해 주세요.");
		} else {
			ReplyBoardDao replyBoardDao = new ReplyBoardDao();
			ArrayList<ReplyBoardDto> boardList = replyBoardDao.getSearchAllList(search_select, search_word);
			// 넘어온 search_select, search_word를 던져주기 dao에
			
			request.setAttribute("boardList", boardList);
			//위에서 매개변수에 실은것을 넘겨주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("search_list.jsp");
			dispatcher.forward(request, response);
		}
	}
}