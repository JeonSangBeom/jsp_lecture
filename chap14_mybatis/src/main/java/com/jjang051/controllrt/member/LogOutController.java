package com.jjang051.controllrt.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jjang051.util.ScriptWriter;

@WebServlet("/member/LogOut.do")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogOutController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//님 안녕히 가세요....
		String loggedName = (String)session.getAttribute("loggedName");
		ScriptWriter.alertAndNext(response, loggedName+"님 안녕히 가세요", "../board/Index.do");
		session.invalidate();
	}
}
