package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 라디오 넘어오는 값 확인
@WebServlet("/RadioServlet")
public class RadioServlet_05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RadioServlet_05() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();//응답하기 그린 그림으로
		String gender = request.getParameter("gender"); // 모든 요청은 string로 넘어 온다
		out.println("<html>");
		out.println("<body>");
		out.println("<p>");
		out.println("당신의 성별은 "+gender+"입니다."); // 라디오의 값은 value의 값으로 넘어온다
		out.println("</p>");
		out.println("<a href='javascript:history.back();'>돌아가기</a>");
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}