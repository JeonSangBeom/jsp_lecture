package com.jjang051.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;


@WebServlet("/member/IdCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IdCheckController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		MemberDao memberDao = new MemberDao();
		int result = memberDao.idCheck(user_id);
		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("count",result); //{count:0}
		Gson gson = new Gson();
		String json = (String)gson.toJson(resultMap);
		request.setAttribute("json", json);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/id_check.jsp");
		dispatcher.forward(request, response);
	}
}







