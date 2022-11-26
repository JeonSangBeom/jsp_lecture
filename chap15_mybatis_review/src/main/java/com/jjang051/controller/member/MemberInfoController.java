package com.jjang051.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;

@WebServlet("/member/MemberInfo.do")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberInfoController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = null;
		memberDto = memberDao.getSelectOne(id);
		request.setAttribute("memberInfo", memberDto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/member_info.jsp");
		dispatcher.forward(request, response);
	}
}








