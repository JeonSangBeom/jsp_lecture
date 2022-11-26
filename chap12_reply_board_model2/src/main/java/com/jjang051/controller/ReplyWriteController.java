package com.jjang051.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReplyWrite.do")
public class ReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyWriteController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("reGroup",request.getParameter("reGroup"));
		request.setAttribute("reStep",request.getParameter("reStep"));
		request.setAttribute("reLevel",request.getParameter("reLevel"));
		request.setAttribute("no",request.getParameter("no"));
		// view에서 쿼리스트링으로 보내준 값 받기(바로 실어보내는 방식/ int사용 안하고)
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("reply_write.jsp");
		dispatcher.forward(request, response);
	}

}