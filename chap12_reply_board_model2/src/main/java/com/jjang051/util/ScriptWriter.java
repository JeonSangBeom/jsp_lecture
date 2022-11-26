package com.jjang051.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptWriter {
	public static void init(HttpServletResponse response) {
		//response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// 나갈때
	}
	public static void alert(HttpServletResponse response, String alertMgs) throws IOException { // 경고창 띄우는 것
		// 무조건 HttpServletResponse(나가는 것(받아올 것은 없으니 이것만 입력)) 를 먼저 상속을 받고(import)
		// String alertMgs 보여야할 메시지는 매개변수로 지정
		init(response); // 반복되는 부분으로 위에 따로 메서드 만들어 두기
		PrintWriter out = response.getWriter();//ioexception이 뜨는데 만들어(던져) 두기 위 throws
		out.println("<script>");
		out.println("alert('"+alertMgs+"');");
		out.println("</script>");
	}

	public static void alertAndBack(HttpServletResponse response, String alertMgs) throws IOException {// 경고창 띄우고 뒤로 가기
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+alertMgs+"');");
		out.println("history.back();"); // 추가 입력
		out.println("</script>");
	}

	public static void alertAndNext(HttpServletResponse response, String alertMgs, String nextURL) throws IOException { // 경고창 띄우고 다른 페이지 이동
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+alertMgs+"');");
		out.println("location.href='"+nextURL+"';");// 추가 입력
		out.println("</script>");
	}
}