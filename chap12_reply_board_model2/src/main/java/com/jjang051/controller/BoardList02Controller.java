package com.jjang051.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.PageDto;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;

@WebServlet("/BoardList02.do")
public class BoardList02Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardList02Controller() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyBoardDao replyBoardDao = new ReplyBoardDao();
		String tempClickPage = request.getParameter("clickPage");
		PageDto pageDto = new PageDto();
		if(tempClickPage==null) {
			tempClickPage = "1";
		}
		pageDto.setClickPage( Integer.parseInt(tempClickPage) );
		
		pageDto.setTotalPage( replyBoardDao.getTotal() ); //db에 저장되어 있는 페이지 갯수
		
	
		//startPage  clickPage 0~3 ===  1
		//startPage  clickPage 4~6 ===  4
		//startPage  clickPage 7~9 ===  7
		
		//clickPage = 1   start = 1   /  end = 5
		//clickPage = 2   start = 6   /  end = 10
		//clickPage = 3   start = 11  /  end = 15
		
		int start = pageDto.getStart();
		int end =   pageDto.getEnd();
		
		
		System.out.println("start==="+start+"/ end==="+end+"/ lastPage==="+pageDto.getLastPage()+"/endPage==="+pageDto.getEndPage());
		
		
		ArrayList<ReplyBoardDto> boardList = replyBoardDao.getAllList(start,end);
		request.setAttribute("boardList", boardList);
		//페이지 관련 속성들
		request.setAttribute("pageDto", pageDto);
		
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("board_list02.jsp");
		dispatcher.forward(request, response);
	}
}




