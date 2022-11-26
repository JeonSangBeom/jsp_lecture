package com.jjang051.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;

@WebServlet("/member/List.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	MemberDao memberDao = new MemberDao();
    	MemberDto memberDto = new MemberDto();
    	
    	ArrayList<MemberDto> memberList = memberDao.showAllMember();
    	request.setAttribute("memberList", memberList);
    	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/list.jsp");
		dispatcher.forward(request, response);
	}
}
