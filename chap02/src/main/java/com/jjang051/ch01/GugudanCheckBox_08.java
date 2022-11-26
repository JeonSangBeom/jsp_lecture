package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;
//체크박스 여러개 체크 하여 구구단 생성하기
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GugudanCheckBox")
public class GugudanCheckBox_08 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GugudanCheckBox_08() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		String select[] = request.getParameterValues("gugudan");

		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>"); // 아래 td가 붙어서 나오게 만들기 위해 위에 따라 빼준다
		for (int j = 0; j < select.length; j++) {
			int dan = Integer.parseInt(select[j]);
			out.println("<td>" + dan + "단</td>");
		}
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");		
				
		for (int i = 1; i < 10; i++) { 
		out.println("<tr>"); 
		for (int j = 0; j <select.length; j++) { 
		int dan = Integer.parseInt(select[j]);
		out.println("<td>" + dan + " X " + i + " = " + dan * i + "</td>"); }		
		out.println("</tr>"); }
						
		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href='javascript:history.back();'>돌아가기</a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}