package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//간단한 정의//
//jsp는 일반적인 html 파일에 java파일을 쓰는 것
//Servlet은 java파일에 html파일을 쓰는 것(매핑된 것을 보내기 위해)
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Hello() {
        super();
    }// get 파일 형식이면 아래로 request - 브라우저에서 요청을 보내오는 것을 받을때(String로 넘어온다) response - 요청을 받아서 처리하는 것(응답)
    //보통 request로 정보 값을 받아와 response로 값을 내보낸다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); //대답한다 - 화면에 그려준 것으로
		out.println("<html>");// 보내주겠다
		out.println("<body>");
		out.println("<h1>Hello Jsp Get</h1>");
		out.println("</body>");
		out.println("</html>");
	}// post면 html 형식으로 아래를 보여 주겠다
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); //화면에 그려줄때
		out.println("<html>");// 보내주겠다
		out.println("<body>");
		out.println("<h1>Hello Jsp Post</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
