package com.jjang051.controller;
// 뷰로 사용할 페이지만 던져두고 끝 visual.jsp 페이지 (확장자 jsp를 가리기 위해) 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VisualForm.do")
public class VisualFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VisualFormController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("visual.jsp");
		dispatcher.forward(request, response);
	}

}