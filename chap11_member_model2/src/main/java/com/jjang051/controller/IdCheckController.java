package com.jjang051.controller;

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


@WebServlet("/IdCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IdCheckController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		MemberDao memberDao = new MemberDao();// 아이디만 넘기면 돼서 dto는 필요 없다
		int result = memberDao.idCheck(user_id); // dao에 정의해둔 것		
		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("count",result); //{count:0}(이렇게 담겨 있을 것이다)
		Gson gson = new Gson();
		String json = (String)gson.toJson(resultMap);
		request.setAttribute("json", json); // jsp에 넘겨줄 것 "json"에 실기
		RequestDispatcher dispatcher = request.getRequestDispatcher("id_check.jsp");
		dispatcher.forward(request, response);
	}
}

