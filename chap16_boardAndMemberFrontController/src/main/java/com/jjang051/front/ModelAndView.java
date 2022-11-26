 package com.jjang051.front;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	// 데이터 실어서 다음페이지로 연결...
	private String nextPage;
	private Map<String, Object> model = new HashMap<>();
	
	//기본생성자
	public ModelAndView() {	}

	//페이지만 받아서 처리하는 생성자
	public ModelAndView(String nextPage) {
		this.nextPage = nextPage;
	}
	
	//다음 페이지 설정하면서 데이터 담아서 보내기(생성자)
	public ModelAndView(String nextPage, String key, Object object) {
		setNextPage(nextPage);  // 아래 만들어둔 메서드
		addObject(key, object); // 아래 만들어둔 메서드
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	//model에 데이터 담기...   
	public void addObject(String key, Object object) {
		model.put(key, object);
	}
}


