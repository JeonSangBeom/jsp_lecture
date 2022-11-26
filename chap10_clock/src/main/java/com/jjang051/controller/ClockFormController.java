package com.jjang051.controller;

//servlet을 만들때 
//controller을 뒤에 붙어 쓰는게 관례이다
//url mappoing에서 edit을 눌러 .do를 붙여 주어야 한다(나중에 붙일 수도 있음 / 붙이지 않아도 상관은 없으나 대체로 그냥 붙여준다)

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ClockForm.do") // 주석창 이것을(내 마음대로 써도 상관 없다) 호출 하면 아래Dispatcher(송신의 역할)이 보여진다
public class ClockFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ClockFormController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 포워드
		request.setAttribute("test", "servlet에서 넘어온 데이터");// 데이터 넘겨줄 것이 있으면 여기 실어서 넘기기
		RequestDispatcher dispatcher = request.getRequestDispatcher("hublot_form.jsp"); // 송신하는 곳 내가 사용하는 jsp파일을 불러오면 된다(hublot_form.jsp)
		dispatcher.forward(request, response);// 포워드 시켜주기
	}
}





