package com.jjang051.controller;

// json만 던져놓는 경우 나머지는 프론트에서 알아서 하게 만드는 경우
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jjang051.model.ClockDao;
import com.jjang051.model.ClockDto;
@WebServlet("/ClockJsonList.do") //url-mapping
public class ClockJsonListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ClockJsonListController() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ClockDto> clockList = null;
		ClockDao clockDao = new ClockDao();
		clockList = clockDao.getAllList();//dao에서 받아온 list를 clocklist에 실어주고
		
		HashMap<String,ArrayList<ClockDto>> hashMap = new HashMap<>();
		hashMap.put("clockList", clockList); // (key,vlaue(clocklist로 리턴되는 값))
		
		Gson gson= new Gson();
		//{"clockList":[]} -> 이런식으로 들어가게 될 것
		String clockJsonList = gson.toJson(hashMap); //해쉬맵을 json으로 처리 하겠단 뜻 그 후 clockJsonList에 실어 두기 
		
		request.setAttribute("clockJsonList", clockJsonList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("clock_list.jsp"); // json의 정보가 넘어가는 jsp파일엔 주석이 있으면 안된다
		dispatcher.forward(request, response);
	}
}







