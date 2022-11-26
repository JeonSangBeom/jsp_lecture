package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Gugudan")
public class Gugudan_07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Gugudan_07() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int dan =Integer.parseInt( request.getParameter("dan") ); // 단으로 받아 오자 가져오자(jsp에서)
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr><th>"+dan+"단</th></tr>");
		out.println("</thead>");
		out.println("<tbody>");
			for(int i=1;i<10;i++) {
				out.println("<tr><td>"+dan+"x"+i+"="+dan*i+"</td></tr>");
			}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href='javascript:history.back();'>돌아가기</a>");
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}