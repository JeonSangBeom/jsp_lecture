package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter; // 스트림

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet_01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public HelloServlet_01() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 응답을할때 내가 보내는 형식이 html이라는 것을 보낼 수 있는데 위가 그런 경우
		// setContentType = 내가 보내는 타입이 어떤 것인지(text에 html이다 ;charset=UTF-8 : 한글 인식시킬때)
		PrintWriter out = response.getWriter(); 
		// 써서 보내고 싶을땐 PrintWriter을 받아 온다 response에 있는 객체에서 getWriter객체를 얻어 올 수 있다 
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>안녕 서블릿</h1>");//원래 한들을 인식하지 못하지만 위에 utf-8dmf TJtj rksmd
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}