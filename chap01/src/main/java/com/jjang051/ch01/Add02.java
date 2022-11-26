package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Add02") // Add - 요청 주소 
//@WebServlet - 자바에서 쓰고 있는 매핑
public class Add02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public Add02() {
        super();
    }
    // 아래 두 메서드를 톰캣이 받아서 처리 해주는 것
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num01 = Integer.parseInt(request.getParameter("num01"));//requset.getParameter("num01")- 이것으로 요청 받을 수 있다
		// 넘어오는 것은 전부 스트링이기 때문에 Integer.parseInt을 사용하여 형변환 필요 - 현재 없으니 url위치에 쿼리스트링 이용하여 값 입력
		int num02 = Integer.parseInt(request.getParameter("num02"));
		int sum = num01+num02;
		//db를 받게 되면 여기서 db조회
		//조회 결과를 받아서 있으면 response(응답) 로 나가기
		
		PrintWriter out = response.getWriter(); //응답을 해준다(요청을 받았을때)
		out.println("<html>");// html을 하나 보내주겠다
		out.println("<body>");
		out.println(num01+"+"+num02+"="+sum);
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
