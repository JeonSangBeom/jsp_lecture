package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ParamServlet")
public class ParamServlet_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ParamServlet_04() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 받기(기본)
		request.setCharacterEncoding("UTF-8");// 받을때(들어온 값) 글자가 깨지지 않게 해주는 법 
		response.setContentType("text/html; charset=UTF-8");// 보낼때 한글이 깨질 수 있으니 만들어 두기
		
		String userId = request.getParameter("user_id");// 요청 받기 getParameter(메서드에 기록한 name값)
		int userAge =  Integer.parseInt( request.getParameter("user_age") ); // 형변환
		// 변수 생성
		String isAdult = "어른"; // 조건 전 변수 
		if(userAge<20)  isAdult = "미성년자"; // 만약 20보다 적으면 미성년자
		PrintWriter out =  response.getWriter(); // 응답하기
		out.println("<html>");
		out.println("<body>");
		out.println("<p>");
		out.println("당신의 아이디는 "+userId+"이고 나이는 "+userAge+"입니다. 당신은 "+isAdult);
		out.println("</p>");
		out.println("<a href='javascript:history.back();'>돌아가기</a>");//'javascript:history.back();' 전 단계 뒤로가기
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// post는 값을 못 읽어 올 수 있어 request(요청이 들어온 것을 처리하는 것) 를 해줘야 한다
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("user_id");
		int userAge =  Integer.parseInt( request.getParameter("user_age") );
		String isAdult = "어른";
		if(userAge<20)  isAdult = "미성년자";
		PrintWriter out =  response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>");
		out.println("당신의 아이디는 "+userId+"이고 나이는 "+userAge+"입니다. 당신은 "+isAdult);
		out.println("</p>");
		out.println("<a href='javascript:history.back();'>돌아가기</a>");
		out.println("</body>");
		out.println("</html>");
	}
}