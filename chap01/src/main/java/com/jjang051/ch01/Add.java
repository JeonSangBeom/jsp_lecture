package com.jjang051.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Add") // Add - 요청 주소 
//우리가 만든 자바의 결과를 웹을 통해 돌려 받고 싶을때
//@WebServlet - (자바에서 쓰고 있는 매핑된 주소) - 톰캣이 웹에서 /Add를 쓰면 매핑된 주소를 찾아 결과를 보여준다
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    public Add() {
        super();
    }
    // 아래 두 메서드(doGet,doPost)를 톰캣이 받아서 처리 해주는 것
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num01 = 10;
		int num02 = 20;
		int sum = num01+num02;
		
		PrintWriter out = response.getWriter(); //응답을 해준다(/Add로 요청을 받았을때) - response:대답하다
		// 아래 내용을 출력
		out.println("<html>");
		out.println("<body>");
		out.println(num01+"+"+num02+"="+sum);
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
