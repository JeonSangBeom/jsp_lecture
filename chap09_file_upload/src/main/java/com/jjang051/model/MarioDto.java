package com.jjang051.model;

public class MarioDto {
	//
	private int no;
	private String title;
	private String bg;
	private String contents;
	private String link;
	private String marioImg;
	private String marioRealImg;
	public MarioDto() {	}
	public MarioDto(int no, String title, String bg, String contents, String link, String marioImg,
			String marioRealImg) {
		super();
		this.no = no;
		this.title = title;
		this.bg = bg;
		this.contents = contents;
		this.link = link;
		this.marioImg = marioImg;
		this.marioRealImg = marioRealImg;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMarioImg() {
		return marioImg;
	}
	public void setMarioImg(String marioImg) {
		this.marioImg = marioImg;
	}
	public String getMarioRealImg() {
		return marioRealImg;
	}
	public void setMarioRealImg(String marioRealImg) {
		this.marioRealImg = marioRealImg;
	}
	@Override
	public String toString() {
		return "MarioDto [no=" + no + ", title=" + title + ", bg=" + bg + ", contents=" + contents + ", link="
				+ link + ", marioImg=" + marioImg + ", marioRealImg=" + marioRealImg + "]";
	}
}