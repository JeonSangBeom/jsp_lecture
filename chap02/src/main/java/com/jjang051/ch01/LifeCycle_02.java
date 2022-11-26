package com.jjang051.ch01;

//init / destroy 의 용도 단순 테스트
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LifeCycle")
public class LifeCycle_02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 // 변수 생성	
	int initCount = 0;
	int doGetCount = 0;
	int destroyCount = 0;
    public LifeCycle_02() {
        super();
    }
    //나중엔 통합되 service(?)를 사용한다
	public void init(ServletConfig config) throws ServletException {
		initCount++; // 실행될때 한번만 작동
		System.out.println("initCount==="+initCount);
	}
	public void destroy() {
		destroyCount++;// 실행될때만 작동
		System.out.println("destroyCount==="+destroyCount);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetCount++;//실행하는 순간마다 작동
		System.out.println("doGetCount==="+doGetCount);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}
}