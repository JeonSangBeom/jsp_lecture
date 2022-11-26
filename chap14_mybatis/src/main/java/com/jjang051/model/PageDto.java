package com.jjang051.model;

public class PageDto {
	private int listPerPage = 5;
	private int listPerPageBlock = 3;
	
	
	private int startPage;
	private int endPage;
	
	private int lastPage;
	private int totalPage;
	private int clickPage;
	
	
	private int start;
	private int end;
	
	
	
	public int getStartPage() {
		return (int)((clickPage-1) / listPerPageBlock)*listPerPageBlock+1;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		if(endPage > lastPage) {
			return lastPage;
			
		} else {
			return startPage + listPerPageBlock - 1;
		}
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLastPage() {
		if(totalPage%listPerPage==0) {
			return lastPage = (int)(Math.ceil(totalPage/listPerPage));
		} else {
			return lastPage = (int)(Math.ceil(totalPage/listPerPage))+1;
		}
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getClickPage() {
		return clickPage;
	}
	public void setClickPage(int clickPage) {
		this.clickPage = clickPage;
	}
	public int getStart() {
		return (clickPage-1)*listPerPage+1;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return start+listPerPage - 1; 
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getListPerPage() {
		return listPerPage;
	}
	public void setListPerPage(int listPerPage) {
		this.listPerPage = listPerPage;
	}
	public int getListPerPageBlock() {
		return listPerPageBlock;
	}
	public void setListPerPageBlock(int listPerPageBlock) {
		this.listPerPageBlock = listPerPageBlock;
	}
	
	
}
