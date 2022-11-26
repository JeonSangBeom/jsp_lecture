package com.jjang051.ch01;
// 요청 보내보기
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Method")
public class Method_03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Method_03() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//설정
		
		String name = request.getParameter("user_name");//jsp의 user_name(name)에 있는 값(value)을 받아오겠다 /  
		PrintWriter out = response.getWriter();//그린 걸로 응답
		out.println("<h1>GET 방식 받아서 처리"+name+"</h1>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("user_name");
		PrintWriter out = response.getWriter();
		out.println("<h1>POST 방식 받아서 처리"+name+"</h1>");
	}
}