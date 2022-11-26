package com.jjang051.front;

//접두어(ex-/WEB-INF/ , 접미어(ex-.jsp) 붙혀서 view page완성하기
public class ViewResolver {
	private String prefix; // 접두어
	private String suffix; // 접미어
	public ViewResolver() {
		super();
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	// 이 메서드를 호출하면 /WEB-INF/ nextPage  .jsp 
	public String getViewPage(String nextPage) {
		return prefix+nextPage+suffix;
	}
}
