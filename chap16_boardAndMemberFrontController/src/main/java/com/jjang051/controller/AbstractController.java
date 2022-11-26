package com.jjang051.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjang051.front.ModelAndView;

public interface AbstractController {
	// 인터페이스는 메서드 정의만 해둔다. - 강제 구현을 위해
	// AbstractController를 상속받고 이걸 구현할 Controller에서 requestHandler(메서드)를 구현해서 쓰면 된다.
	public ModelAndView requestHandler(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
