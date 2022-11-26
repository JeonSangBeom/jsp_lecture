package com.jjang051.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.util.ScriptWriter;


@WebServlet("/board/AllDelete.do")
public class AllDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllDeleteController() {
        super();
    }
    // 일괄 삭제
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkList =  request.getParameterValues("delete_check");
		ReplyBoardDao boardDao = new ReplyBoardDao();
		int result = 0;
	
		//foreach와 같은 사용법
		for(String item: checkList) {
			System.out.println(item);
			boardDao.allDeleteBoard(Integer.parseInt(item));
			result++;
		}
		if(result > 0) {
			ScriptWriter.alertAndNext(response, "삭제되었습니다.","../board/List.do");
			//response.sendRedirect("../board/List.do");
		}
	}
}
